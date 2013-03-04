package com.cnooc.lca.service;

import java.util.List;

/**
 * 配置文件cycleconfig.js中表示一个生命周期的配置
 * 
 * <p><code> {
					name : "发电",
					cycleNameList : ["mei_600"],
					excel : "electricity.xls"
			} </code></p>
 * 
 * @author gaoxl
 *
 */
public class CycleBean {

	private String name;
	private List<String> cycleNameList;
	private String excel;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getCycleNameList() {
		return cycleNameList;
	}
	public void setCycleNameList(List<String> cycleNameList) {
		this.cycleNameList = cycleNameList;
	}
	public String getExcel() {
		return excel;
	}
	public void setExcel(String excel) {
		this.excel = excel;
	}
	
	
	
}
