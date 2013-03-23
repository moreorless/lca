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

	private static final String StatBy_GENERATOR = "generator";
	private static final String StatBy_PROCEDURE = "procedure";
	
	/**
	 * 能耗统计图
	 * @param cycleCode		生命周期编码(发电、交通燃料、天然气产业链)
	 * @param generatorCode	发电方式编码
	 * @return
	 */
	@At
	@Ok("raw:xml")
	public String consumption(@Param("cycletype") String cycleCode, @Param("generatorCode") String generatorCode,
			@Param("statBy") String statBy){
		//return Files.read(demoDataPath + "consumption_data.xml");
		
		CycleType cycleType = cycleService.getCycleType(cycleCode);
		
		if(StatBy_GENERATOR.equals(statBy)){
			return cycleChartService.getConsumptionChart(cycleType);
		}
		return cycleChartService.getConsumptionChartGroupByGenerator(cycleType, generatorCode);
		
	}
	
	/**
	 * 排放统计
	 * @param cycleCode		生命周期编码(发电、交通燃料、天然气产业链)
	 * @param generatorCode 发电方式编码(煤电、气电...)
	 * @param statItem		统计项（CO2, CH4, 总计）
	 * @return
	 */
	@At
	@Ok("raw:xml")
	public String emission(@Param("cycletype") String cycleCode, @Param("generatorCode") String generatorCode,
			@Param("emissionType") String emissionType, @Param("statBy") String statBy){
		CycleType cycleType = cycleService.getCycleType(cycleCode);
		
		if(StatBy_GENERATOR.equals(statBy)){
			return cycleChartService.getEmissionChart(cycleType, emissionType);
		}
		return cycleChartService.getEmissionChartGroupByGenerator(cycleType, generatorCode, emissionType);
	}
	/**
	 * 影响潜能统计
	 * @param cycleCode
	 * @param generatorCode
	 * @return
	 */
	@At
	@Ok("raw:xml")
	public String influence(@Param("cycletype") String cycleCode, @Param("generatorCode") String generatorCode, @Param("statBy") String statBy){
		CycleType cycleType = cycleService.getCycleType(cycleCode);
		
		if(StatBy_GENERATOR.equals(statBy)){
			return cycleChartService.getInfluenceChart(cycleType);
		}
		return cycleChartService.getInfluenceChartGroupByGenerator(cycleType, generatorCode);
		
	}
	
}
