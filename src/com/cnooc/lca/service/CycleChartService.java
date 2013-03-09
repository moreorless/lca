package com.cnooc.lca.service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;

import com.cnooc.lca.model.InfluenceNames;
import com.cnooc.lca.model.T_Cycle;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 统计图服务类
 * @author gaoxl
 *
 */
@IocBean
public class CycleChartService {

	public static String[] colors = new String[]{
			"#F54E62",
			"#F0BB0B", // 黄
			"#00CCCB", // 浅蓝
			"#80D24C", // 浅绿
			"#7953DA", // 淡紫
			"#4FB5DD", // 淡蓝
			"#FF58FC", // 粉色
			"#7C334E", // 紫色
			"#517F96", // 
	};
	
	private int colorIndex = 0;
	// 顺序选取颜色
	public String getColorString(){
		colorIndex = colorIndex % colors.length;

		return colors[colorIndex++];
	}

	private static final int DEFAULT_SCALE = 3; // 默认保留3为有效数字 
	
	private static final String DEFAULT_INF_NAME = "加权总计";
	
	
	@Inject("refer:cycleService")
	private CycleService cycleService;
	
	/**
	 * 生成综合能耗的柱状图数据（使用dom4j）
	 * @return xml string
	 */
	public String getConsumptionChart(CycleType cycleType){
		
		List<T_Cycle> cycleList = cycleType.getCycleList();
		
		Document document = DocumentHelper.createDocument();
		Element chartEle = document.addElement("chart");
		Element seriesEle = chartEle.addElement("series");
		Element graphsEle = chartEle.addElement("graphs");
		Element consumptionGraphEle = graphsEle.addElement("graph").addAttribute("title", "能耗");
		int i = 0;
		
		Iterator<T_Cycle> iter = cycleList.iterator();
		
		Map<String, String> colorMap = new HashMap<>();	// 不用类型的颜色映射
		colorIndex = 0;
		
		while(iter.hasNext()){
			T_Cycle cycle = iter.next();
			
			String name = cycle.getName();   		// 生命周期名字
			String columnColor = "";
			if(colorMap.containsKey(name)){			// 相同发电类型、不同机组使用同样的颜色。
				columnColor = colorMap.get(name);
			}else{
				columnColor = getColorString();
				colorMap.put(name, columnColor);
			}
			
			if(!Strings.isEmpty(cycle.getUnit())) name += ("(" + cycle.getUnit() + ")");
			seriesEle.addElement("value").addAttribute("xid",""+i).addText(name);
			
			
			BigDecimal bigDecimal = new BigDecimal(cycle.getTotalConsumption());
			
			consumptionGraphEle.addElement("value").addAttribute("xid", ""+i)
				.addAttribute("color", columnColor)
				.addText(bigDecimal.setScale(DEFAULT_SCALE, RoundingMode.HALF_UP).toString());
			
			i++;
		}
		
		return document.asXML();
	}
	
	/**
	 * 生成影响潜能的统计图数据
	 * @param cycleType
	 * @param infItem
	 * @return
	 */
	public String getInfluenceChart(CycleType cycleType, String infItem){
		List<T_Cycle> cycleList = cycleType.getCycleList();
		
		Document document = DocumentHelper.createDocument();
		Element chartEle = document.addElement("chart");
		Element seriesEle = chartEle.addElement("series");
		Element graphsEle = chartEle.addElement("graphs");
		Element consumptionGraphEle = graphsEle.addElement("graph").addAttribute("title", "能耗");
		int i = 0;
		
		Iterator<T_Cycle> iter = cycleList.iterator();
		
		Map<String, String> colorMap = new HashMap<>();	// 不用类型的颜色映射
		colorIndex = 0;
		
		while(iter.hasNext()){
			T_Cycle cycle = iter.next();
			
			String name = cycle.getName();   		// 生命周期名字
			String columnColor = "";
			if(colorMap.containsKey(name)){			// 相同发电类型、不同机组使用同样的颜色。
				columnColor = colorMap.get(name);
			}else{
				columnColor = getColorString();
				colorMap.put(name, columnColor);
			}
			
			if(!Strings.isEmpty(cycle.getUnit())) name += ("(" + cycle.getUnit() + ")");
			seriesEle.addElement("value").addAttribute("xid",""+i).addText(name);
			
			String infName = DEFAULT_INF_NAME;

			if(!Strings.isEmpty(infItem)){
				infName = InfluenceNames.me().getInfluenceName(infItem);
			}
				
			double influence = cycle.getInfluenceMap().get(infName);
			
			consumptionGraphEle.addElement("value").addAttribute("xid", ""+i)
				.addAttribute("color", columnColor)
				.addText(fixDecimal(influence));
			
			i++;
		}
		
		return document.asXML();
	}
	
	/**
	 * 生成排放的统计图
	 * @return
	 */
	public String getEmissionChart(CycleType cycleType, String statItem){
	
		List<T_Cycle> cycleList = cycleType.getCycleList();
		
		Document document = DocumentHelper.createDocument();
		Element chartEle = document.addElement("chart");
		Element seriesEle = chartEle.addElement("series");
		
		// 初始化x轴
		Iterator<T_Cycle> iter = cycleList.iterator();
		int xid = 1;
		while(iter.hasNext()){
			T_Cycle cycle = iter.next();
			
			String name = cycle.getName();   		// 生命周期名字
			
			if(!Strings.isEmpty(cycle.getUnit())) name += ("(" + cycle.getUnit() + ")");
			seriesEle.addElement("value").addAttribute("xid",""+ xid++).addText(name);
		}
		
		Element graphsEle = chartEle.addElement("graphs");
		List<String> procedureList = cycleService.getProcedureList(cycleType.getCode());
		
		int gid = 1;
		for(String procedure : procedureList){
			Element graphEle = graphsEle.addElement("graph").addAttribute("gid", gid+"").addAttribute("title", procedure);
			xid = 1;
			for(T_Cycle cycle : cycleList){
				
				double emissionValue = 0;
				if("total".equals(statItem)){
					emissionValue = cycle.getTotalEmission();		// 总排放
				}else{
					emissionValue = cycle.getMergedEmissionMap().get(statItem);
				}
				
				graphEle.addElement("value").addAttribute("xid", xid + "").addText(fixDecimal(emissionValue));
				xid++;
			}
			gid++;
		}
		
		return document.asXML();
	}

	private String fixDecimal(double value){
		
		/*BigDecimal bigDecimal = new BigDecimal(value);
		return bigDecimal.setScale(DEFAULT_SCALE, RoundingMode.HALF_UP).toString();
		*/
		return String.valueOf(value);
		
	}
	
}
