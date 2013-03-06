package com.cnooc.lca.module;

import java.io.File;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.cnooc.lca.common.GlobalConfig;

/**
 * 统计图
 * @author gaoxl
 *
 */
@IocBean()
@InjectName
@At("/chart")
public class CycleChartModule {

	private String demoDataPath = GlobalConfig.getContextValue("web.dir") + File.separator + "common"
			+ File.separator + "amchart" + File.separator + "stat" + File.separator;
	@At
	@Ok("raw:xml")
	public String consumption(){
		return Files.read(demoDataPath + "consumption_data.xml");
	}
	
	@At
	@Ok("raw:xml")
	public String emission(){
		return Files.read(demoDataPath + "emission_data.xml");
	}
	
	@At
	@Ok("raw:xml")
	public String influence(){
		return Files.read(demoDataPath + "influence_data.xml");
	}
	
}
