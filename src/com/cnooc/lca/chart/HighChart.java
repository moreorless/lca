package com.cnooc.lca.chart;

import java.util.LinkedList;

public class HighChart {

	private LinkedList<String> xAxis;
	
	private LinkedList<Serie> series;

	public LinkedList<String> getxAxis() {
		return xAxis;
	}

	public void setxAxis(LinkedList<String> xAxis) {
		this.xAxis = xAxis;
	}

	public LinkedList<Serie> getSeries() {
		return series;
	}

	public void setSeries(LinkedList<Serie> series) {
		this.series = series;
	}


	
}
