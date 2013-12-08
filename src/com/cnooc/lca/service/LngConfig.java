package com.cnooc.lca.service;

import java.io.File;

import org.nutz.lang.Files;

import com.cnooc.lca.common.GlobalConfig;

/**
 * 进口LNG电动车配置
 * @author gaoxl
 *
 */
public class LngConfig {
	String confFile = GlobalConfig.getContextValue("conf.dir") + File.separator + "lng.conf";
	
	public static String LOCAL = "local";
	public static String GLOBAL = "global";
	
	private boolean _loaded = false;
	private String conf = LOCAL;

	private LngConfig(){
		if(!_loaded){
			this.load();
			_loaded = true;
		}
		
	}
	
	private static LngConfig instance = new LngConfig();
	
	public static LngConfig getInstance(){
		return instance;
	}
	
	public String getConf() {
		return conf;
	}

	public void setConf(String conf) {
		if(conf.equals(LOCAL) || conf.equals(GLOBAL)){
			this.conf = conf;
		}
	}
	
	public void save(){
		Files.write(confFile, this.conf);
	}
	public void load(){
		this.conf = Files.read(confFile);
	}
}
