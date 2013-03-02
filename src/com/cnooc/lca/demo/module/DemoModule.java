package com.cnooc.lca.demo.module;

import java.util.Random;

import org.nutz.dao.Dao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.alibaba.druid.stat.DruidDataSourceStatManager;
import com.cnooc.lca.demo.bean.Pet;

@IocBean()
@InjectName
@At("/demo")
public class DemoModule {
	
	@At
	@Ok("jsp:page.demo.chart")
	public void chart(){
		
	}
}
