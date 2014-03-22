package com.cnooc.lca.model;

/**
 * 为存放天然气方案设计的类，复制了T_Cycle中的几个属性
 * @author gaoxl
 *
 */
public class T_Cycle_MiniGas {

	public T_Cycle_MiniGas(T_Cycle cycle){
		this.name = cycle.getName();
		this.procedureIndexStr = cycle.getProcedureIndexStr();
		this.transDistStr = cycle.getTransDistStr();
	}
	
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 天然气产业链使用的运输距离数据，按照gas/procedures.js配置的运输次序写入，以逗号分割，默认值1000,1000,1000
	 */
	private String transDistStr;
	
	/**
	 * 天然气产业链使用的工序序号集合(阶段1序号|阶段2序号1,阶段2序号2|...|阶段n序号)
	 */
	private String procedureIndexStr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTransDistStr() {
		return transDistStr;
	}

	public void setTransDistStr(String transDistStr) {
		this.transDistStr = transDistStr;
	}

	public String getProcedureIndexStr() {
		return procedureIndexStr;
	}

	public void setProcedureIndexStr(String procedureIndexStr) {
		this.procedureIndexStr = procedureIndexStr;
	}
	
	
	
}
