package com.cnooc.lca.electricity.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

/**
 * 工序
 * @author gaoxl
 *
 */
@Table("t_procedure")
public class Procedure {

	/**
	 * 工序名称
	 */
	@Column
	private String name;
	
	
	/**
	 * 第几部工序
	 */
	@Column
	private int index;
	
}
