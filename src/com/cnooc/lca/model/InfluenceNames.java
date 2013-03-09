package com.cnooc.lca.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 影响潜能的名字
 * 因为配置文件中没有定义影响潜能的代码，所以为了便于页面传值，定义了影响潜能名字与uuid的映射
 * @author gaoxl
 *
 */
public class InfluenceNames {

	private InfluenceNames(){}
	
	private static InfluenceNames _instance = new InfluenceNames();
	
	public static InfluenceNames me(){
		return _instance;
	}
	
	/**
	 * 影响潜能与uuid的映射
	 */
	private Map<String, String> nameToUuidMap = new HashMap<>();
	
	private Map<String, String> uuidToNameMap = new HashMap<>();

	/**
	 * 添加影响潜能名字
	 * @param influenceName
	 */
	public void addInfluenceName(String influenceName){
		if(nameToUuidMap.containsKey(influenceName)) return;
		
		String uuid = UUID.randomUUID().toString();
		nameToUuidMap.put(influenceName, uuid);
		uuidToNameMap.put(uuid, influenceName);
	}
	
	public String getInfluenceName(String uuid){
		return uuidToNameMap.get(uuid);
	}
	
	public String getUuid(String infName){
		return nameToUuidMap.get(infName);
	}

	public Map<String, String> getNameToUuidMap() {
		return nameToUuidMap;
	}

	public Map<String, String> getUuidToNameMap() {
		return uuidToNameMap;
	}
	
	
	
}
