package com.cnooc.lca.model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


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
	private String name;
	
	/**
	 * 电站机组
	 */
	private String unit;
	
	/**
	 * 工序集合
	 */
	private List<T_Procedure> procedures;
	
	/**
	 * 综合能耗
	 */
	private double totalConsumption;
	
	/**
	 * 影响潜能集合
	 */
	private Map<String, Double> influenceMap;
	
	/**
	 * 排放集合  <code>{排放物, {工序， 排放值}}</code>
	 */
	private Map<String, Map<String, Double>> emissionMap;
	
	
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
	public List<T_Procedure> getProcedures() {
		return procedures;
	}
	public void setProcedures(List<T_Procedure> procedures) {
		this.procedures = procedures;
	}
	
	public double getTotalConsumption() {
		return totalConsumption;
	}
	public void setTotalConsumption(double totalConsumption) {
		this.totalConsumption = totalConsumption;
	}
	
	
	
	
	public Map<String, Double> getInfluenceMap() {
		return influenceMap;
	}
	public void setInfluenceMap(Map<String, Double> influenceMap) {
		this.influenceMap = influenceMap;
	}
	
	

	public Map<String, Map<String, Double>> getEmissionMap() {
		return emissionMap;
	}
	public void setEmissionMap(Map<String, Map<String, Double>> emissionMap) {
		this.emissionMap = emissionMap;
	}
	/**
	 * 从各个工序计算综合能耗
	 * @return
	 */
	public void calcTotalConsumption(){
		this.totalConsumption = 0;
		Iterator<T_Procedure> iter = this.procedures.iterator();
		while(iter.hasNext()){
			this.totalConsumption += iter.next().getTotalConsumption();
		}
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
