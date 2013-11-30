package com.cnooc.lca.excel;

public class ProcedureParamItem {

	private String name;
	private String consumption;
	private String emission;
	private String depends;
	
	private double consumptionValue;
	
	private double emissionValue;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getConsumption() {
		return consumption;
	}
	public void setConsumption(String consumption) {
		this.consumption = consumption;
	}
	public String getEmission() {
		return emission;
	}
	public void setEmission(String emission) {
		this.emission = emission;
	}
	public String getDepends() {
		return depends;
	}
	public void setDepends(String depends) {
		this.depends = depends;
	}
	public double getConsumptionValue() {
		return consumptionValue;
	}
	public void setConsumptionValue(double consumptionValue) {
		this.consumptionValue = consumptionValue;
	}
	public double getEmissionValue() {
		return emissionValue;
	}
	public void setEmissionValue(double emissionValue) {
		this.emissionValue = emissionValue;
	}
	
	
	
	
}
