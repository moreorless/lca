package com.cnooc.lca.test;

import java.util.Map;
import java.util.Set;

import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.lang.Dumps;
import org.nutz.mapl.Mapl;

import com.cnooc.lca.service.CycleBean;


public class IocTest {

	public static void main(String args[]){
		
		JsonLoader reader = new JsonLoader("com/cnooc/lca/test/cycleconfig.js");
		Map configMap = reader.getMap().get("cycleconfig");

		for(String key : (Set<String>)configMap.keySet()){
			System.out.println(key);
			
			// 
			Object obj = configMap.get(key);
			CycleBean cycleBean = (CycleBean)Mapl.maplistToObj(obj, CycleBean.class);
			
			System.out.println( " ----  name = " + cycleBean.getName());
			System.out.println( " ----  excel = " + cycleBean.getExcel());
			
			System.out.println(" --- cycleNameList = " + cycleBean.getCycleNameList());
			
		}
		
		
	}
}
