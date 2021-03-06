package com.cnooc.lca.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.ptg.Ptg;
import org.nutz.json.Json;
import org.nutz.lang.Files;
import org.nutz.lang.Streams;

import com.cnooc.lca.common.GlobalConfig;
import com.cnooc.lca.excel.parser.ExcelParser;
import com.cnooc.lca.model.T_Cycle;
import com.cnooc.lca.model.T_Cycle_MiniGas;
import com.cnooc.lca.service.CycleType;


/**
 * 各阶段基础参数的配置文件解析模板
 * 天然气产业链生命周期使用这种类型的模板
 * 定义各个阶段的能耗、排放的基础数据
 * @author gaoxl
 *
 */
public class ProcedureTemplate{
	private Logger logger = Logger.getLogger(this.getClass());
	private String name;
	
	/**
	 * excel文件名
	 */
	private String excelName;
	
	private List<ProcedureParam> procedures;
	
	
	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ProcedureParam> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<ProcedureParam> procedures) {
		this.procedures = procedures;
	}

	/**
	 * 加载生命周期（即存储的各个方案）
	 * @param cycleType
	 * @return
	 */
	public List<T_Cycle> loadCycles(CycleType cycleType) {
		
		List<T_Cycle> cycleList = new LinkedList<>();
		
		String code = cycleType.getCode();
		// 读取excel文件中的数据
		ExcelParser parser = ExcelFactory.me().getParser(cycleType.getExcel());
		
		// 加载生命周期配置文件
		String confFile = getCycleConfigFile(cycleType);
		
		
		// 从excel表加载参数值
		Iterator<ProcedureParam> iter = procedures.iterator();
		while(iter.hasNext()){
			ProcedureParam procParam = iter.next();
			for(ProcedureParamItem pItem : procParam.getItems()){
				double _cvalue = getCellValue(parser, pItem.getConsumption());
				double _evalue = getCellValue(parser, pItem.getEmission());
				pItem.setConsumptionValue(_cvalue);
				pItem.setEmissionValue(_evalue);
			}
		}
		
		
		try{
			String jsonStr = Files.read(confFile);
			List<T_Cycle_MiniGas> savedCycleList = (List<T_Cycle_MiniGas>)Json.fromJson(jsonStr);
			if(savedCycleList != null){
				Iterator<T_Cycle_MiniGas> gasCycleIter = savedCycleList.iterator();
				while(gasCycleIter.hasNext()){
					T_Cycle_MiniGas gasCycle = gasCycleIter.next();
					T_Cycle newCycle = createCycle(cycleType, gasCycle.getName(), 
							gasCycle.getProcedureIndexStr(), gasCycle.getTransDistStr());
					cycleList.add(newCycle);
				}
			}
			/*
			BufferedReader br = new BufferedReader(new InputStreamReader(  
					new FileInputStream(confFile)));  
			
			for (String line = br.readLine(); line != null; line = br.readLine()) {  
				String[] strArr = line.split("\\|");
				String cycleName = strArr[0];

				int sepIndex = line.indexOf("|");
				String procedureIndexStr = line.substring(sepIndex + 1);
				
				cycleList.add(createCycle(cycleType, cycleName, procedureIndexStr));
				
			}  
			br.close(); 
			*/
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cycleList;
	}
	
	/**
	 * 
	 * @param cycleType
	 * @param cycleName		
	 * @param procedureIndexStr	工序序号集合
	 * @param transDistStr		传输距离集合，如果后续还有定制需求，则将此项扩展为map结构，存放一个方案的参数项
	 * @return
	 */
	public T_Cycle createCycle(CycleType cycleType, String cycleName, String procedureIndexStr, String transDistStr){
		T_Cycle cycle = new T_Cycle(cycleType);
		cycle.setName(cycleName);
		cycle.setProcedureIndexStr(procedureIndexStr);
		cycle.setTransDistStr(transDistStr);
		
		// 解析生命周期中的各个阶段，计算综合能耗和排放
		Map<String, Double> consumptionMap = new HashMap<>();
		Map<String, Map<String, Double>> emissionMap = new HashMap<>();
		
		List<ProcedureParamItem[]> procedureParamItemList = new LinkedList<>();
		String[] strArr = procedureIndexStr.split("\\|");
		for(int col = 0; col < strArr.length; col++){
			Double comsumptionValue = 0d;
			Double emissionValue = 0d;
			
			ProcedureParam procParam = procedures.get(col);
			String[] indexStr = strArr[col].split(",");
			
			ProcedureParamItem[] paramItems = new ProcedureParamItem[indexStr.length];
			for(int row = 0; row < indexStr.length; row++){
				
				ProcedureParamItem pItem = procParam.getItems().get(Integer.parseInt(indexStr[row]));
				paramItems[row] = pItem;

				/**
				 *  这里做了一个特殊处理，如果col==2，对应"运输"列，对距离做了重新计算
				 *  以现在设置的距离除以1000
				 */
				if(col == 2){
					String[] transDistArr = transDistStr.split(",");
					if(transDistArr[row] == ""|| transDistArr[row] == null || transDistArr[row].length() == 0) 
						comsumptionValue = 0.0;
					else
						comsumptionValue += pItem.getConsumptionValue() * Integer.parseInt(transDistArr[row]) / 1000;
					//emissionValue += pItem.getEmissionValue() * Integer.parseInt(transDistArr[row]) / 1000;
				}else{
					comsumptionValue += pItem.getConsumptionValue();
					emissionValue += pItem.getEmissionValue();
				}
				
			}
			
			procedureParamItemList.add(paramItems);
			consumptionMap.put(procParam.getName(), comsumptionValue);
			
			// 排放沿用原来的结构
			if(emissionMap.containsKey("total")){
				emissionMap.get("total").put(procParam.getName(), emissionValue);
			}else{
				Map<String, Double> totleMap = new HashMap<>();
				totleMap.put(procParam.getName(), emissionValue);
				emissionMap.put("total", totleMap);
			}
		}
		
		cycle.setProcedureParamItemList(procedureParamItemList);
		cycle.setConsumptionMap(consumptionMap);
		cycle.setEmissionMap(emissionMap);
		return cycle;
	}
	
	private double getCellValue(ExcelParser parser, String cellPos){
		String column = "";
		int row = -1;
		double result = 0.0;

		String sheetIndex = cellPos.split(",")[0];
		cellPos = cellPos.split(",")[1];
		
		try{
			int rowIndex = 0;
			for(rowIndex = 0; rowIndex< cellPos.length(); rowIndex++){
				char c = cellPos.charAt(rowIndex);
				if(c >= '0' && c <= '9'){
					break;
				}
				column += c;
			}
			
			row = Integer.parseInt(cellPos.substring(rowIndex));
			result = parser.getCellValue(Integer.parseInt(sheetIndex), row, column); 
		}catch (Exception e) {
			logger.error("读取单元格内数据出错, excel=" + getExcelName() + ", sheet=" + sheetIndex + ", pos=" + column + row , e);
		}
		return result;
	}
	
	/***
	 * 保存生命周期配置
	 * 文件格式  (生命周期名称|阶段1序号|阶段2序号1,阶段2序号2|...|阶段n序号)
	 * 示例：方案1|2|1|1,2|3
	 * @param cycleType
	 */
	public void saveCycleConfig(CycleType cycleType){
		List<T_Cycle> cycleList = cycleType.getCycleList();
		
		List<T_Cycle_MiniGas> gasList = new LinkedList<>();
		Iterator<T_Cycle> iter = cycleList.iterator();
		while(iter.hasNext()){
			T_Cycle cycle = iter.next();
			T_Cycle_MiniGas miniCycle = new T_Cycle_MiniGas(cycle);
			gasList.add(miniCycle);
		}
		
		String jsonStr = Json.toJson(gasList);
		
		/*
		StringBuffer buf = new StringBuffer();
		Iterator<T_Cycle> iter = cycleList.iterator();
		while(iter.hasNext()){
			T_Cycle cycle = iter.next();
			StringBuffer tmp = new StringBuffer();
			tmp.append(cycle.getName()).append("|");
			tmp.append(cycle.getProcedureIndexStr());
			buf.append(tmp).append("\n");
		}
		*/
		
		String fileName = getCycleConfigFile(cycleType);
		File file = new File(fileName);
		Files.createFileIfNoExists(file);
		
		try{
			Writer wt = Streams.fileOutw(file);
			wt.write(jsonStr);
			wt.flush();
			wt.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getCycleConfigFile(CycleType cycleType){
		return GlobalConfig.getContextValue("conf.dir") + File.separator + cycleType.getCode() + ".txt";
	}
	
	
}
