package com.cnooc.lca.excel;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.nutz.lang.Strings;

import com.cnooc.lca.excel.parser.ExcelParser;
import com.cnooc.lca.model.InfluenceNames;
import com.cnooc.lca.model.NameToUuidMap;
import com.cnooc.lca.model.T_Cycle;

/**
 * 通用excel文档解析类
 * @author gaoxl
 * <p><code>
 	mei_600_new : {
		type : "com.cnooc.lca.electricity.excel.CommonTemplate",
		fields  : {
			sheetIndex : 1,
			name : "煤电",
			unit : "600MW",
			totalConsumption : "G,121",
			consumptions : {
				"电厂建设" : "B313",
				"原料开采" : "B314",
				"原料运输" : "B315",
				"发电" :     "B316",
				"电厂退役" : "B317",
				"废弃物处理" : "B318"
			},
			influences : {"全球变暖" : "F227", "酸化" : "F238", "富营养化" : "F244", "粉尘" : "F245", "光化学臭氧" : "F253", "加权总计" : "F276"},
			// 分工序的影响潜能（加权）
			procInfluences : {
				"电厂建设" : "D313",
				"原料开采" : "D314",
				"原料运输" : "D315",
				"发电" :     "D316",
				"电厂退役" : "D317",
				"废弃物处理" : "D318"
			},
			emissions : {
				"CO2" : {
					"电厂建设" : {"建材生产安装":"G,122", "建材生产安装":"G,122"}
				},
				"CH4" : {
					"电厂建设" : {"建材生产安装":"G,122", "建材生产安装":"G,122"}
				}
			}
		}
	}
	</code></p>
 */
public class CommonTemplate implements ITemplate{

	private Logger logger = Logger.getLogger(this.getClass());
	
	/**
	 * excel文件名
	 */
	private String excelName;
	
	/**
	 * excel表页序号，从0开始
	 */
	private int sheetIndex;
	
	/**
	 * 发电站名称
	 */
	private String name;
	
	/**
	 * 电站机组
	 */
	private String unit;
	
	/**
	 * 发电方式的英文编码
	 */
	private String code;
	
	/**
	 * 综合能耗
	 */
	private String totalConsumption;
	
	/**
	 * 影响潜能集合
	 * <p>key 影响潜能名称, value 影响潜能单元格位置</p>
	 */
	private Map<String, String> influences;
	
	
	/**
	 * 分工序的影响潜能（加权）
	 * <p>key 统一后的工序, value 加权的影响潜能值</p>
	 */
	private Map<String, String> procInfluences;
	
	/**
	 * <p>综合能耗集合</p>
	 * <p>{统一后的工序,  值}</p>
	 */
	private Map<String, String> consumptions;
	
	/**
	 * <p>排放集合</p>
	 * <p>key 排放物名称(CO2)， value 电厂周期内对应的排放物集合</p>
	 * <code>
		emissions : {
				"CO2" : {
					"电厂建设" : {"建材生产安装":"G,122", "建材生产安装":"G,122"}
				},
				"CH4" : {
					"电厂建设" : {"建材生产安装":"G,122", "建材生产安装":"G,122"}
				}
			}
	 </code>
	 */
	private Map<String, Map<String, Map<String, String>>> emissions;
	
	
	@Override
	public T_Cycle createcycle() {
		T_Cycle cycle = new T_Cycle();
		cycle.setSheetIndex(this.getSheetIndex());
		cycle.setName(this.getName());
		cycle.setUnit(this.getUnit());
		cycle.setCode(this.getCode());
		
		// 读取excel文件中的数据
		ExcelParser parser = ExcelFactory.me().getParser(excelName);
		logger.debug("读取生命周期数据：,  excel=" + excelName + ", sheet=" + sheetIndex + ", " + this.name + "(" + unit + ")");
		
		// 读取综合能耗
		double totalConsumption = getCellValue(parser, getSheetIndex(), getTotalConsumption()); 
		cycle.setTotalConsumption(totalConsumption);
		logger.debug("综合能耗=" + totalConsumption);
		
		logger.debug("读取分阶段的综合能耗数据");
		if(consumptions != null){
			Map<String, Double> consumptionMap = new LinkedHashMap<>();
			Set<String> procNames = consumptions.keySet();
			for(String procName : procNames){
				String cellPos = consumptions.get(procName);
				if(Strings.isEmpty(cellPos)) {
					logger.warn("---工序--- " + procName + ", 未配置单元格位置");
					continue;
				}
				double value = getCellValue(parser, getSheetIndex(), cellPos);
				logger.debug("---工序--- " + procName + " : " + value);
				consumptionMap.put(procName, value);
				
				NameToUuidMap.me().addName(NameToUuidMap.Type.PROCEDURE, procName);
			}
			cycle.setConsumptionMap(consumptionMap);
		}else{
			logger.error("没有配置分阶段的综合能耗数据！！！");
		}
		
		// 读取影响潜能
		Map<String, Double> influenceMap = new LinkedHashMap<>();
		Set<String> keySet = influences.keySet();
		Iterator<String> iter = keySet.iterator();
		while(iter.hasNext()){
			String infName = iter.next();
			double infValue = getCellValue(parser, getSheetIndex(), influences.get(infName));
			influenceMap.put(infName, infValue);
			NameToUuidMap.me().addName(NameToUuidMap.Type.INFLUENCE, infName);
			
			logger.debug("影响潜能 : " + infName + " = " + infValue);
		}
		cycle.setInfluenceMap(influenceMap);
		
		logger.debug("读取分阶段的影响潜能数据");
		if(procInfluences != null){
			Map<String, Double> procInfluenceMap = new LinkedHashMap<>();
			Set<String> procNames = procInfluences.keySet();
			for(String procName : procNames){
				String cellPos = procInfluences.get(procName);
				if(Strings.isEmpty(cellPos)) {
					logger.warn("---工序--- " + procName + ", 未配置单元格位置");
					continue;
				}
				double value = getCellValue(parser, getSheetIndex(), cellPos);
				logger.debug("---工序--- " + procName + " : " + value);
				procInfluenceMap.put(procName, value);
			}
			cycle.setProcInfluenceMap(procInfluenceMap);
		}else{
			logger.error("没有配置分阶段的综合能耗数据！！！");
		}
		
		
		// 读取排放数据 {排放物, {工序， 排放值}}
		Map<String, Map<String, Double>> emissionMap = new LinkedHashMap<>();
		Set<String> emiNameSet = emissions.keySet();
		for(String emiName : emiNameSet){
			logger.debug("读取排放物数据 --- " + emiName);
			
			Map<String, Double> aEmissionMap = new LinkedHashMap<>();
			
			// 第一层为 统一后的阶段
			Map<String, Map<String, String>> unitedProcMap = emissions.get(emiName);
			Set<String> unitedProcNames = unitedProcMap.keySet();
			for(String unitedProcName : unitedProcNames ){
				// 第二层为原始的阶段 
				Map<String, String> origProcMap = unitedProcMap.get(unitedProcName);
				Set<String> origProcNames = origProcMap.keySet();
				
				double unitedProcValue = 0; 
				for(String origProcName : origProcNames){
					if(Strings.isEmpty(origProcName)) continue;
					
					String cellPos = origProcMap.get(origProcName);
					if(Strings.isEmpty(cellPos)) {
						logger.warn("---原始工序--- " + origProcName + ", 未配置单元格位置");
						continue;
					}
					
					double origProcValue = getCellValue(parser, getSheetIndex(), cellPos);
					unitedProcValue += origProcValue;
					logger.debug("---原始工序--- " + origProcName + " = " + origProcValue);
				}
				logger.debug("合并工序--- " + unitedProcName + " = " + unitedProcValue);
				aEmissionMap.put(unitedProcName, unitedProcValue);
			}
			emissionMap.put(emiName, aEmissionMap);
		}
		cycle.setEmissionMap(emissionMap);
		
		return cycle;
	}
	
	private double getCellValue(ExcelParser parser, int sheetIndex, String cellPos){
		String column = "";
		int row = -1;
		double result = 0.0;

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
			result = parser.getCellValue(sheetIndex, row, column); 
		}catch (Exception e) {
			logger.error("读取单元格内数据出错, excel=" + excelName + ", sheet=" + sheetIndex + ", pos=" + column + row , e);
		}
		return result;
	}
	
	
	public String getExcelName() {
		return excelName;
	}

	public void setExcelName(String excelName) {
		this.excelName = excelName;
	}

	public int getSheetIndex() {
		return sheetIndex;
	}

	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(String totalConsumption) {
		this.totalConsumption = totalConsumption;
	}

	public Map<String, String> getInfluences() {
		return influences;
	}

	public void setInfluences(Map<String, String> influences) {
		this.influences = influences;
	}

	public Map<String, Map<String, Map<String, String>>> getEmissions() {
		return emissions;
	}

	public void setEmissions(Map<String, Map<String, Map<String, String>>> emissions) {
		this.emissions = emissions;
	}

	public Map<String, String> getProcInfluences() {
		return procInfluences;
	}

	public void setProcInfluences(Map<String, String> procInfluences) {
		this.procInfluences = procInfluences;
	}

	public Map<String, String> getConsumptions() {
		return consumptions;
	}

	public void setConsumptions(Map<String, String> consumptions) {
		this.consumptions = consumptions;
	}

	
	
	
}
