package com.cnooc.lca.model;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


public class T_Procedure{
	/**
	 * 工序名称
	 */
	private String name;
	
	/**
	 * 单元格位置
	 */
	private T_Position position;
	
	/**
	 * 能耗列表
	 */
	private List<T_Cell> consumption;
	/**
	 * 排放列表
	 */
	private List<T_Cell> emission;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public T_Position getPosition() {
		return position;
	}
	public void setPosition(T_Position position) {
		this.position = position;
	}
	public List<T_Cell> getConsumption() {
		return consumption;
	}
	public void setConsumption(List<T_Cell> consumption) {
		this.consumption = consumption;
	}
	public List<T_Cell> getEmission() {
		return emission;
	}
	public void setEmission(List<T_Cell> emission) {
		this.emission = emission;
	}
	
	/**
	 * 读取能耗的指标名列表
	 * @return
	 */
	public List<String> getConsumptionNames(){
		LinkedList<String> names = new LinkedList<>();
		Iterator<T_Cell> iter = this.consumption.iterator();
		while(iter.hasNext()){
			names.add(iter.next().getName());
		}
		return names;
 	}
	
	/**
	 * 读取排放的指标名列表
	 * @return
	 */
	public List<String> getEmissionNames(){
		LinkedList<String> names = new LinkedList<>();
		Iterator<T_Cell> iter = this.emission.iterator();
		while(iter.hasNext()){
			names.add(iter.next().getName());
		}
		return names;
 	}
	
	/**
	 * 获取当前工序的全部能耗
	 * @return
	 */
	public double getTotalConsumption(){
		double totalConsumption = 0;
		Iterator<T_Cell> iter = this.consumption.iterator();
		while(iter.hasNext()){
			if(iter.next().getValue() instanceof Double)
			totalConsumption += (Double)iter.next().getValue();
		}
		return totalConsumption;
	}
	
}