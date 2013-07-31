package com.cnooc.lca.demo.module;

import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

@IocBean()
@InjectName
@At("/demo")
public class DemoModule {
	
	@At
	@Ok("jsp:page.demo.chart")
	public void chart(){
		
	}
	
	@At
	@Ok("jsp:page.demo.highcharts")
	public void highcharts(){
		
	}
}
