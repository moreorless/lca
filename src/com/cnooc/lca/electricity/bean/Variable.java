package com.cnooc.lca.electricity.bean;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * 变量
 * @author gaoxl
 *
 */
@Table("t_variable")
public class Variable {

	/**
	 * 变量编号(英文+数字)
	 */
	@Column
	private String code;
	
	/**
	 * 变量中文名
	 */
	@Column
	private String name;
	
	/**
	 * 描述
	 */
	@Column
	private String description;
	
	/**
	 * 影响因子
	 */
	private List<Variable> factorList;
	
	/**
	 * 影响因子转为string存表
	 */
	@Column
	private String factors;
	
	/**
	 * 变量类型 : 原始数据， 中间结果， 最终数据
	 */
	@Column
	private VariableType type;
	
	
	@Column
	private boolean isVisible;
	
	/**
	 * 变量值
	 */
	@Column
	private double value;
	
	/**
	 * 计算公式
	 */
	@Column
	private String func;
	
	
}
