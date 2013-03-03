package com.cnooc.lca.electricity.excel;

import java.util.List;
import java.util.Map;

/**
 * 通用excel文档解析类
 * @author gaoxl
 * <p><code>
 	mei_600_new : {
		type : "com.cnooc.lca.electricity.excel.CommonTemplate",
		fields  : {
			sheetIndex : 1,
			stationName : "煤电",
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

	/**
	 * excel表页序号，从0开始
	 */
	private int sheetIndex;
	
	/**
	 * 发电站名称
	 */
	private String stationName;
	
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
		// TODO Auto-generated method stub
		return null;
	}
	
	public int getSheetIndex() {
		return sheetIndex;
	}




	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}




	public String getStationName() {
		return stationName;
	}




	public void setStationName(String stationName) {
		this.stationName = stationName;
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
