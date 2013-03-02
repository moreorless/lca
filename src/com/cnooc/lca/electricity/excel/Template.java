package com.cnooc.lca.electricity.excel;

import java.util.List;
import java.util.Map;

public class Template {

	
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
	 * 工序集合
	 */
	private List<Map<String, Object>> procedures;

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

	public List<Map<String, Object>> getProcedures() {
		return procedures;
	}

	public void setProcedures(List<Map<String, Object>> procedures) {
		this.procedures = procedures;
	}
	
	
	
}
