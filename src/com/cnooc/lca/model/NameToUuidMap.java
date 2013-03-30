package com.cnooc.lca.model;

import java.util.LinkedHashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * 系统内使用的名称与uuid的映射
 * 因为配置文件中许多地方直接使用了中文名与值的map结构配置，所以为了便于页面传值，定义了中文名称与uuid的映射
 * @author gaoxl
 *
 */
public class NameToUuidMap {

	/***
	 * 映射的类型
	 */
	public enum Type{
		/**
		 * 影响潜能名称
		 */
		INFLUENCE,	
		/**
		 * 工序名称
		 */
		PROCEDURE	
	}
	
	
	public NameToUuidMap(){}
	
	
	/**
	 * 名称与uuid的映射
	 */
	private Map<Type, Map<String, String>> nameToUuidMap = new LinkedHashMap<>();
	
	/**
	 * uuid与名称的映射
	 */
	private Map<Type, Map<String, String>> uuidToNameMap = new LinkedHashMap<>();
	
	private Set<Type> containedTypes = new HashSet<>();
	/**
	 * 添加名字
	 * @param name
	 */
	public void addName(Type type, String name){
		if(!containedTypes.contains(type)){
			nameToUuidMap.put(type, new LinkedHashMap<String, String>());
			uuidToNameMap.put(type, new LinkedHashMap<String, String>());
			containedTypes.add(type);
		}
		
		if(nameToUuidMap.get(type).containsKey(name)) return;
		
		String uuid = UUID.randomUUID().toString();
		nameToUuidMap.get(type).put(name, uuid);
		uuidToNameMap.get(type).put(uuid, name);
	}
	
	public String getName(Type type, String uuid){
		return uuidToNameMap.get(type).get(uuid);
	}
	
	public String getUuid(Type type, String name){
		return nameToUuidMap.get(type).get(name);
	}

	public Map<String, String> getNameToUuidMap(Type type) {
		return nameToUuidMap.get(type);
	}

	public Map<String, String> getUuidToNameMap(Type type) {
		return uuidToNameMap.get(type);
	}
	
	
}
