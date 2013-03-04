package com.cnooc.lca.model;


/**
 * 单元格
 */
public class T_Cell{
	/**
	 * 单元格代表的数据名称
	 */
	private String name;
	/**
	 * 单元格的数据值
	 */
	private Object value;
	
	/**
	 * 单元格位置
	 */
	private T_Position position;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public T_Position getPosition() {
		return position;
	}
	public void setPosition(T_Position position) {
		this.position = position;
	}
}
