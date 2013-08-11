package com.cnooc.lca.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.cnooc.lca.chart.HighChart;
import com.cnooc.lca.chart.Serie;
import com.cnooc.lca.model.Contant;
import com.cnooc.lca.model.NameToUuidMap;
import com.cnooc.lca.model.T_Cycle;

@IocBean
public class HighChartsService {
	
	@Inject("refer:cycleService")
	private CycleService cycleService;
	
	private static final String DEFAULT_INF_NAME = "加权总计";
	
	/**
	 * 生成综合能耗的柱状图数据(横轴为发电方式)
	 * @return
	 */
	public HighChart getConsumptionChart(CycleType cycleType){
		HighChart chart = new HighChart();
		LinkedList<String> xAxis = new LinkedList<>();
		LinkedList<Serie> series = new LinkedList<>();
		
		List<T_Cycle> cycleList = cycleType.getCycleList();
		
		// 获取阶段
		List<String> procedures = cycleService.getProcedureList(cycleType.getCode());
		Map<String, Serie> serieMap = new HashMap<String, Serie>();
		for(String procedure : procedures){
			Serie serie = new Serie();
			serie.setName(procedure);
			series.add(serie);											// 添加数据序列
			
			serieMap.put(procedure, serie);
		}
		
		Iterator<T_Cycle> iter = cycleList.iterator();
		while(iter.hasNext()){
			T_Cycle cycle = iter.next();
			
			xAxis.add(getCycleName(cycle));		// 横轴
			Map<String, Double> consumptionMap = cycle.getConsumptionMap();
			
			for(String procName : consumptionMap.keySet()){
				Serie serie = serieMap.get(procName);
				serie.getData().add(fixDecimal(consumptionMap.get(procName)));		// 添加数据
			}
		}
		
		chart.setxAxis(xAxis);
		chart.setSeries(series);
		
		return chart;
	}
	
	/**
	 * 生成综合能耗的柱状图数据（选中发电方式，按工序分组统计）
	 * @param cycleType
	 * @param generatorCode		发电方式编码
	 * @return
	 */
	public HighChart getConsumptionChartGroupByGenerator(CycleType cycleType, String generatorCode){
		
		T_Cycle cycle = cycleType.getCycle(generatorCode);
		Map<String, Double> consumptionMap = cycle.getConsumptionMap();		// 能耗数据

		HighChart chart = new HighChart();
		LinkedList<String> xAxis = new LinkedList<>();
		LinkedList<Serie> series = new LinkedList<>();
		
		Serie serie = new Serie();
		serie.setName(cycle.getName());
		
		Iterator<String> iter = consumptionMap.keySet().iterator();
		while(iter.hasNext()){
			String name = iter.next();   			// 工序名字
			
			xAxis.add(name);
			serie.getData().add(fixDecimal(consumptionMap.get(name)));
		}
		series.add(serie);
		
		chart.setxAxis(xAxis);
		chart.setSeries(series);
		return chart;
	}
	
	
	/**
	 * 生成排放的统计图
	 * @return
	 */
	public HighChart getEmissionChart(CycleType cycleType, String emissionType){
		HighChart chart = new HighChart();
		LinkedList<String> xAxis = new LinkedList<>();
		LinkedList<Serie> series = new LinkedList<>();
		
		List<T_Cycle> cycleList = cycleType.getCycleList();
		for(T_Cycle cycle : cycleList){
			xAxis.add(getCycleName(cycle));
		}
		
		// 获取阶段
		List<String> procedureList = cycleService.getProcedureList(cycleType.getCode());
		Map<String, Serie> serieMap = new HashMap<String, Serie>();
		for(String procedure : procedureList){
			Serie serie = new Serie();
			serie.setName(procedure);
			series.add(serie);											// 添加数据序列
			
			serieMap.put(procedure, serie);
		}
		
		if(procedureList.size() > 1){
			for(String procedure : procedureList){
				for(T_Cycle cycle : cycleList){
					double emissionValue = cycle.getEmissionMap().get(emissionType).get(procedure);
					serieMap.get(procedure).getData().add((fixDecimal(emissionValue)));
				}
			}
		}else{
			// 没有工序数据，则直接取总计
			for(T_Cycle cycle : cycleList){
				double emissionValue = 0;
				if("total".equals(emissionType)){
					emissionValue = cycle.getTotalEmission();
				}else{
					emissionValue = cycle.getTotalEmissionMap().get(emissionType);
				}
				
				serieMap.get(procedureList.get(0)).getData().add((fixDecimal(emissionValue)));
			}
		}
		
		chart.setxAxis(xAxis);
		chart.setSeries(series);
		
		return chart;
	}
	
	/**
	 * 按发电方式统计所有排放类型在各阶段的对比复合直线图
	 * @param cycleType
	 * @param generatorCode
	 * @return
	 */
	public HighChart getEmissionClusteredChartByGenerator(CycleType cycleType, String generatorCode){
		HighChart chart = new HighChart();
		LinkedList<String> xAxis = new LinkedList<>();
		LinkedList<Serie> series = new LinkedList<>();
		
		T_Cycle cycle = cycleType.getCycle(generatorCode);
		
		String[] emissionTypes = new String[]{"CO2", "CH4", "N2O"};
		
		List<String> procedureList = cycleService.getProcedureList(cycleType.getCode());
		for(String procedure : procedureList){
			xAxis.add(procedure);
		}
		
		for(String emissionType : emissionTypes){
			
			Serie serie = new Serie();
			serie.setName(emissionType);
			series.add(serie);											// 添加数据序列
			
			Map<String, Double> emissionMap = cycle.getEmissionMap().get(emissionType);
			Iterator<String> iter = emissionMap.keySet().iterator();
			
			int weight = Contant.getEmisstionWeight(emissionType);
			
			while(iter.hasNext()){
				String procedure = iter.next();   			// 工序名字
				double emissionValue = fixDecimal(emissionMap.get(procedure) * weight);
				
				serie.getData().add(emissionValue);
			}
		}
		
		chart.setxAxis(xAxis);
		chart.setSeries(series);
		
		return chart;
	}
	
	/**
	 * 生成影响潜能的统计图数据
	 * @param cycleType
	 * @param infItem
	 * @return
	 */
	public HighChart getInfluenceChart_ByType(CycleType cycleType, String infItem){
		HighChart chart = new HighChart();
		LinkedList<String> xAxis = new LinkedList<>();
		LinkedList<Serie> series = new LinkedList<>();
		
		List<T_Cycle> cycleList = cycleType.getCycleList();
		
		for(T_Cycle cycle : cycleList){
			xAxis.add(getCycleName(cycle));
		}
		
		
		String infName = DEFAULT_INF_NAME;
		
		if(!Strings.isEmpty(infItem)){
			infName = cycleType.getNameToUuidMap().getName(NameToUuidMap.Type.INFLUENCE, infItem);
		}

		Serie serie = new Serie();
		serie.setName(infName);
		
		Iterator<T_Cycle> iter = cycleList.iterator();
		while(iter.hasNext()){
			T_Cycle cycle = iter.next();
			
			String name = cycle.getName();   		// 生命周期名字
			double influence = fixDecimal(cycle.getInfluenceMap().get(infName));
			serie.getData().add(influence);
		}
		series.add(serie);
		chart.setxAxis(xAxis);
		chart.setSeries(series);
		
		return chart;
	}
	
	
	/**
	 * 生成影响潜能的柱状图数据（选中发电方式，按工序分组统计）
	 * @param cycleType
	 * @param generatorCode
	 * @return
	 */
	public HighChart getInfluenceChartGroupByGenerator(CycleType cycleType, String generatorCode){
		HighChart chart = new HighChart();
		LinkedList<String> xAxis = new LinkedList<>();
		LinkedList<Serie> series = new LinkedList<>();
		
		T_Cycle cycle = cycleType.getCycle(generatorCode);
		
		Map<String, Double> influenceMap = cycle.getProcInfluenceMap();
		for(String procName : influenceMap.keySet()){
			xAxis.add(procName);
		}
		
		Serie serie = new Serie();
		serie.setName(getCycleName(cycle));
		for(String procName : influenceMap.keySet()){
			double influence = fixDecimal(influenceMap.get(procName));
			serie.getData().add(influence);
		}
			
		series.add(serie);
		chart.setxAxis(xAxis);
		chart.setSeries(series);
		return chart;
	}
	
	private double fixDecimal(double value){
		BigDecimal b = new BigDecimal(String.valueOf(value));
		BigDecimal divisor = BigDecimal.ONE;
		MathContext mc = new MathContext(5);
		return b.divide(divisor, mc).doubleValue();
	}

	private String getCycleName(T_Cycle cycle){
		String name = cycle.getName();
		
		// 为保证显示效果，子类型进行换行
		if(!Strings.isEmpty(cycle.getUnit())) name += ("<br/>" + cycle.getUnit());
		return name;
	}
}
