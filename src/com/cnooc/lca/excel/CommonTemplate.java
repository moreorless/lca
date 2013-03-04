package com.cnooc.lca.excel;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.log4j.Logger;

import com.cnooc.lca.excel.parser.ExcelParser;
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
			influences : {"温室" : "G,177", "xxx" : "G,177"},
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
	 * 综合能耗
	 */
	private String totalConsumption;
	
	/**
	 * 影响潜能集合
	 * <p>key 影响潜能名称, value 影响潜能单元格位置</p>
	 */
	private Map<String, String> influences;
	
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
		
		// 读取excel文件中的数据
		ExcelParser parser = ExcelFactory.me().getParser(excelName);
		logger.debug("读取生命周期数据：,  excel=" + excelName + ", sheet=" + sheetIndex + ", " + this.name + "(" + unit + ")");
		
		// 读取综合能耗
		double totalConsumption = getCellValue(parser, getSheetIndex(), getTotalConsumption()); 
		cycle.setTotalConsumption(totalConsumption);
		logger.debug("综合能耗=" + totalConsumption);
		
		// 读取影响潜能
		Map<String, Double> influenceMap = new LinkedHashMap<>();
		Set<String> keySet = influences.keySet();
		Iterator<String> iter = keySet.iterator();
		while(iter.hasNext()){
			String infName = iter.next();
			double infValue = getCellValue(parser, getSheetIndex(), influences.get(infName));
			influenceMap.put(infName, infValue);
			
			logger.debug("影响潜能 : " + infName + " = " + infValue);
		}
		cycle.setInfluenceMap(influenceMap);
		
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
					String cellPos = origProcMap.get(origProcName);
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
		String column = cellPos.split(",")[0];
		int row = Integer.parseInt(cellPos.split(",")[1]);
		double result = 0.0;
		try{
			result = parser.getCellValue(sheetIndex, row, column); 
		}catch (Exception e) {
			logger.error("读取单元格内数据出错, excel=" + excelName + ", sheet=" + sheetIndex + ", pos=" + column+row , e);
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

}
