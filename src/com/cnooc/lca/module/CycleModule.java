package com.cnooc.lca.module;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.cnooc.lca.electricity.bean.Station;
import com.cnooc.lca.model.T_Cycle;
import com.cnooc.lca.service.CycleService;
import com.cnooc.lca.service.CycleType;

@IocBean()
@InjectName
@At("/cycle")
public class CycleModule {
	private Logger logger = Logger.getLogger(this.getClass());
	
	// 所有的生命周期类型，及其数据
	private static final String CYCLETYPE_LIST = "session_cycletype_list";
	
	// {stat, config}
	private static final String SESSION_CURRENT_NAV = "currentNav";
	
	// {electric, transport, gas}
	private static final String SESSION_CURRENT_CYCLETYPE = "currentCycle";
	
	// {consumption, emission, influence}
	private static final String SESSION_CURRENT_CONTENT = "currentContent";  
	
	/**
	 * 进入“行业数据分析页面”
	 * @param ioc
	 * @param request
	 */
	@At
	@Ok("jsp:page.analyze.stat")
	public void stat(Ioc ioc, HttpServletRequest request, @Param("cycletype") String cycleType){
		CycleService cycleService = ioc.get(CycleService.class);
		List<CycleType> cycleTypeList = cycleService.getCycleTypeList();
		request.getSession().setAttribute(CYCLETYPE_LIST, cycleTypeList);
		
		CycleType curCycleType;
		// 如果没有设置生命周期类型，默认设置第一个
		if(cycleType == null){
			curCycleType = cycleTypeList.get(0);
		}else{
			curCycleType = cycleService.getCycleType(cycleType);
		}
		
		request.setAttribute("curCycleType", curCycleType);
		
		// 读取综合能耗数据
		List<T_Cycle> cycleList = curCycleType.getCycleList();
		request.setAttribute("cycleList", cycleList);
	}
	
	
	@At
	@Ok("jsp:page.analyze.config")
	public void config(Ioc ioc, HttpServletRequest request, @Param("cycletype") String cycleType){
		// 读取配置信息
		logger.info("读取配置信息");
		
		CycleService cycleService = ioc.get(CycleService.class);
		List<CycleType> cycleTypeList = cycleService.getCycleTypeList();
		request.getSession().setAttribute(CYCLETYPE_LIST, cycleTypeList);
		
		CycleType curCycleType;
		// 如果没有设置生命周期类型，默认设置第一个
		if(cycleType == null){
			curCycleType = cycleTypeList.get(0);
		}else{
			curCycleType = cycleService.getCycleType(cycleType);
		}
		
		request.setAttribute("curCycleType", curCycleType);
		
	}
	
	@At
	@Ok("redirect:/cycle/config?saveOk=true")
	public void saveConfig(HttpServletRequest request, @Param("..")Station station){
		// 保存配置
		logger.info("保存配置信息");
	}
	
}