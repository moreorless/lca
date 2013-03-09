package com.cnooc.lca.service;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.nutz.ioc.Ioc;

import com.cnooc.lca.excel.CommonTemplate;
import com.cnooc.lca.excel.ExcelFactory;
import com.cnooc.lca.excel.parser.ExcelParser;
import com.cnooc.lca.excel.parser.WritableExcel;
import com.cnooc.lca.model.T_Cycle;

/**
 * 生命周期类型 （发电/交通燃料/天然气）
 * @author gaoxl
 * <p><code>"electric" : {
			name : "发电",
			cycleNamelist : ["mei_600"],
			excel : "electricity.xls"
			}
	</code></p>
 */
public class CycleType {

	/**
	 * 生命周期代码
	 */
	private String code;
	/**
	 * 生命周期名称
	 */
	private String name;
	
	/**
	 * 对应的excel文件名
	 */
	private String excel;
	
	/**
	 * 该类型下的生命周期列表
	 */
	private List<String> cycleNameList;
	
	
	private List<T_Cycle> cycleList;
	
	/**
	 * 生命周期类型对应的参数配置 
	 */
	private WritableExcel paramConfigure;
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

	public List<String> getCycleNameList() {
		return cycleNameList;
	}

	public void setCycleNameList(List<String> cycleNamelist) {
		this.cycleNameList = cycleNamelist;
	}

	public List<T_Cycle> getCycleList() {
		return cycleList;
	}

	public void setCycleList(List<T_Cycle> cycleList) {
		this.cycleList = cycleList;
	}

	
	
	
	public WritableExcel getParamConfigure() {
		return paramConfigure;
	}

	public void setParamConfigure(WritableExcel paramConfigure) {
		this.paramConfigure = paramConfigure;
	}

	/**
	 * 重新加载数据，一般在excel表格中的数据变化后调用
	 * <p>该方法会重新读取excel文件，并更新内存中的数据</p>
	 */
	public void reloadData(Ioc ioc){
		// 清空生命周期数据
		this.cycleList.clear();
		
		// 因为是reload，cycleNamelist中的数据已经填充，且不会发生变化。
		Iterator<String> cycleIter = cycleNameList.iterator();
		while(cycleIter.hasNext()){
			String cycleName = cycleIter.next();
			CommonTemplate tp = ioc.get(CommonTemplate.class, cycleName);
			
			// 设置tp对应的excel文件名
			tp.setExcelName(excel);
			
			T_Cycle t_cycle = tp.createcycle();
			this.cycleList.add(t_cycle);
		}
	}
	
	/**
	 * 获取该类型下影响潜能的名称列表
	 * @return
	 */
	public Set<String> getInflunceNames(){
		Set<String> infNameSet = new LinkedHashSet<>();
		for(T_Cycle t_cycle : cycleList){
			Set<String> theInfNameSet = t_cycle.getInfluenceMap().keySet();
			infNameSet.addAll(theInfNameSet);
		}
		return infNameSet;
	}
	
}
