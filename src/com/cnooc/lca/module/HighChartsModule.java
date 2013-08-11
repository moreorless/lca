package com.cnooc.lca.module;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.cnooc.lca.chart.HighChart;
import com.cnooc.lca.service.CycleService;
import com.cnooc.lca.service.CycleType;
import com.cnooc.lca.service.HighChartsService;

/**
 * 统计图
 * @author gaoxl
 *
 */
@IocBean()
@InjectName
@At("/highcharts")
public class HighChartsModule {
	
	@Inject
	private HighChartsService highChartsService;

	
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
	@Ok("json")
	public HighChart consumption(@Param("cycletype") String cycleCode, @Param("generatorCode") String generatorCode,
			@Param("statBy") String statBy){
		CycleType cycleType = cycleService.getCycleType(cycleCode);
		
		if(StatBy_GENERATOR.equals(statBy)){
			return highChartsService.getConsumptionChart(cycleType);
		}
		return highChartsService.getConsumptionChartGroupByGenerator(cycleType, generatorCode);
		
	}
	
	/**
	 * 排放统计
	 * @param cycleCode		生命周期编码(发电、交通燃料、天然气产业链)
	 * @param generatorCode 发电方式编码(煤电、气电...)
	 * @param statItem		统计项（CO2, CH4, 总计）
	 * @return
	 */
	@At
	@Ok("json")
	public HighChart emission(@Param("cycletype") String cycleCode, @Param("generatorCode") String generatorCode,
			@Param("emissionType") String emissionType, @Param("statBy") String statBy){
		CycleType cycleType = cycleService.getCycleType(cycleCode);
		
		if(StatBy_GENERATOR.equals(statBy)){
			return highChartsService.getEmissionChart(cycleType, emissionType);
		}
		return highChartsService.getEmissionClusteredChartByGenerator(cycleType, generatorCode);
					
	}
	/**
	 * 影响潜能统计
	 * @param cycleCode
	 * @param generatorCode
	 * @return
	 */
	@At
	@Ok("json")
	public HighChart influence(@Param("cycletype") String cycleCode, @Param("generatorCode") String generatorCode,
			@Param("statBy") String statBy, @Param("infItem") String infItem){
		CycleType cycleType = cycleService.getCycleType(cycleCode);
		
		if(StatBy_GENERATOR.equals(statBy)){
			return highChartsService.getInfluenceChart_ByType(cycleType, infItem);
		}
		return highChartsService.getInfluenceChartGroupByGenerator(cycleType, generatorCode);
		
	}
	

}
