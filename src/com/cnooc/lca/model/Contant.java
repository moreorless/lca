package com.cnooc.lca.model;

/**
 * 计算常量
 * @author gaoxl
 *
 */
public class Contant {

	public static int EMISSION_WEIGHT_CO2 = 1;
	public static int EMISSION_WEIGHT_CH4 = 25;
	public static int EMISSION_WEIGHT_N2O = 298;
	
	/**
	 * 获取不同气体排放计算的系数
	 * @param emisstion
	 * @return
	 */
	public static int getEmisstionWeight(String emisstion){
		int weight = 1;
		switch (emisstion) {
		case "CO2":
			weight = EMISSION_WEIGHT_CO2;
			break;
		case "CH4":
			weight = EMISSION_WEIGHT_CH4;
			break;
			
		case "N2O":
			weight = EMISSION_WEIGHT_N2O;
			break;
		
		default:
			break;
		}
		return weight;
	}
}
