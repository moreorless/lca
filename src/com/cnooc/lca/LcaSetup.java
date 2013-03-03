package com.cnooc.lca;

import org.apache.log4j.Logger;
import org.nutz.dao.Dao;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.mvc.NutConfig;
import org.nutz.mvc.Setup;
import org.nutz.resource.Scans;

public class LcaSetup implements Setup{
	Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public void destroy(NutConfig arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(NutConfig config) {
		
	}

}
