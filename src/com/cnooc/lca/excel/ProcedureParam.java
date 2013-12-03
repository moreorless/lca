package com.cnooc.lca.excel;

import java.util.List;
public class ProcedureParam {

	private String name;
	private boolean multiple;
	private List<ProcedureParamItem> items;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMultiple() {
		return multiple;
	}
	public void setMultiple(boolean multiple) {
		this.multiple = multiple;
	}
	public List<ProcedureParamItem> getItems() {
		return items;
	}
	public void setItems(List<ProcedureParamItem> items) {
		this.items = items;
	}
	
	
}


