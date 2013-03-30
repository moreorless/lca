package com.cnooc.lca.excel;

import com.cnooc.lca.model.T_Cycle;
import com.cnooc.lca.service.CycleType;

/**
 * excel文档模板接口
 * 提供从文档中读取数据，封装T_Cycle对象的功能
 * @author gaoxl
 */
public interface ITemplate {
	
	/**
	 * 从当前模板创建生命周期模型T_Cycle
	 * @return
	 */
	public T_Cycle createcycle(CycleType cycleType); 
}
