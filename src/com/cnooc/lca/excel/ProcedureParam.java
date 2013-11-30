package com.cnooc.lca.excel;

import java.util.List;
public class ProcedureParam {

	private String name;
	private boolean multipul;
	private List<ProcedureParamItem> items;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isMultipul() {
		return multipul;
	}
	public void setMultipul(boolean multipul) {
		this.multipul = multipul;
	}
	public List<ProcedureParamItem> getItems() {
		return items;
	}
	public void setItems(List<ProcedureParamItem> items) {
		this.items = items;
	}
	
	
}


