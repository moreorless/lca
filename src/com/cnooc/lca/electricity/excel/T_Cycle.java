package com.cnooc.lca.electricity.excel;

import java.util.Iterator;
import java.util.List;

/**
 * 全周期模板
 * @author gaoxl
 *
 */
public class T_Cycle {

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
	private List<T_Procedure> procedures;
	
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
	public List<T_Procedure> getProcedures() {
		return procedures;
	}
	public void setProcedures(List<T_Procedure> procedures) {
		this.procedures = procedures;
	}
	
	/**
	 * 获取总排放
	 * @return
	 */
	public double getTotalConsumption(){
		double totalConsumption = 0;
		Iterator<T_Procedure> iter = this.procedures.iterator();
		while(iter.hasNext()){
			totalConsumption += iter.next().getTotalConsumption();
		}
		return totalConsumption;
	}
	
	/**
	 * 合并全周期各个工序的能耗
	 * @return
	 */
	public List<T_Cell> mergeConsumptions(){
		// TODO: to be continue.
		return null;
	}
	
	/**
	 * 合并全周期各个工序的排放
	 * @return
	 */
	public List<T_Cell> mergeEmissions(){
		// TODO: to be continue.
		return null;
	}
	
	
}
