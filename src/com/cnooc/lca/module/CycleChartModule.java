package com.cnooc.lca.module;

import java.io.File;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.cnooc.lca.common.GlobalConfig;
import com.cnooc.lca.service.CycleChartService;
import com.cnooc.lca.service.CycleService;
import com.cnooc.lca.service.CycleType;

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
	
	@Inject("refer:cycleChartService")
	private CycleChartService cycleChartService;

	
	@Inject("refer:cycleService")
	private CycleService cycleService;
	
	@At
	@Ok("raw:xml")
	public String consumption(@Param("cycletype") String cycleCode){
		//return Files.read(demoDataPath + "consumption_data.xml");
		
		CycleType cycleType = cycleService.getCycleType(cycleCode);
		return cycleChartService.getConsumptionChart(cycleType);
		
	}
	
	@At
	@Ok("raw:xml")
	public String emission(@Param("cycletype") String cycleCode, @Param("statItem") String statItem){
		CycleType cycleType = cycleService.getCycleType(cycleCode);
		return cycleChartService.getEmissionChart(cycleType, statItem);
	}
	
	@At
	@Ok("raw:xml")
	public String influence(@Param("cycletype") String cycleCode, @Param("statItem") String statItem){
		CycleType cycleType = cycleService.getCycleType(cycleCode);
		return cycleChartService.getInfluenceChart(cycleType, statItem);
		
	}
	
	
	
}
