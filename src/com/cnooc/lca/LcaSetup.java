package com.cnooc.lca;

import java.io.File;

import org.apache.log4j.Logger;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;

import com.cnooc.lca.common.GlobalConfig;
import com.cnooc.lca.service.CycleService;

public class LcaSetup implements Setup{
	Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void destroy(NutConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(NutConfig config) {
		// 首先设置系统环境变量
		String webdir = new File(config.getAppRoot()).toPath().normalize().toString(); //未使用getCanonicalPath，避免需要捕获异常
		
		// 初始化系统环境变量
		GlobalConfig.addContextValue("context.path", config.getServletContext().getContextPath());
		GlobalConfig.addContextValue("web.dir", webdir);
		GlobalConfig.addContextValue("webinf.dir", webdir + "/WEB-INF");
		GlobalConfig.addContextValue("conf.dir", webdir + "/WEB-INF/_conf");
		
		
		// 系统启动时，构建数据模型
		CycleService cycleService = config.getIoc().get(CycleService.class);
		cycleService.loadCycleTypeList();
		
	}

}
