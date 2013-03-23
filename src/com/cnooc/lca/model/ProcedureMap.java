package com.cnooc.lca.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ProcedureMap {

	private ProcedureMap(){}
	private static ProcedureMap _instance = new ProcedureMap();
	public static ProcedureMap me(){
		return _instance;
	}
	
	private Map<String, List<String>> procedureList = new LinkedHashMap<>();
	public Map<String, List<String>> getProcedureList() {
		return procedureList;
	}

	public void setProcedureList(Map<String, List<String>> procedureList) {
		this.procedureList = procedureList;
	}
	
	
	
}
