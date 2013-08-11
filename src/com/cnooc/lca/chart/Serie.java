package com.cnooc.lca.chart;

import java.util.LinkedList;

public class Serie {

	private String name;
	private LinkedList<Number> data = new LinkedList<>();
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LinkedList<Number> getData() {
		return data;
	}
	public void setData(LinkedList<Number> data) {
		this.data = data;
	}
	
	
}
