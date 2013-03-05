package com.cnooc.lca.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.mapl.Mapl;

import com.cnooc.lca.excel.CommonTemplate;
import com.cnooc.lca.model.T_Cycle;

/**
 * 从cycleconfig.js解析系统中各个生命周期的配置
 * @author gaoxl
 *
 */
@IocBean
public class CycleService {

	private Logger logger = Logger.getLogger(this.getClass());
	
	@Inject("refer:$Ioc")
	private Ioc ioc;
	
	/**
	 * 系统内的生命周期类型列表
	 */
	private List<CycleType> cycleTypeList = new LinkedList<>();
	
	private boolean _loaded = false;
	
	/**
	 * 获取系统内生命周期类型列表，有序
	 * @return
	 */
	public List<CycleType> getCycleTypeList(){
		loadCycleTypeList();
		return cycleTypeList;
	}
	
	/**
	 * 获取生命周期类型
	 * @param cycleCode 编码
	 * @return
	 */
	public CycleType getCycleType(String cycleCode){
		for(CycleType cType : cycleTypeList){
			if(cycleCode.equals(cType.getCode())){
				return cType;
			}
		}
		return null;
	}
	
	/**
	 * 从配置文件加载生命周期类型列表
	 */
	public void loadCycleTypeList(){
		if(_loaded){
			return;
		}
		cycleTypeList.clear();
		
		// 解析cycleconfig.js
		JsonLoader reader = new JsonLoader("cycleconfig.js");
		Map configMap = reader.getMap().get("cycleconfig");

		logger.debug("读取全局配置文件 cycleconfig.js");
		for(String code : (Set<String>)configMap.keySet()){
			CycleType cycleType = (CycleType)Mapl.maplistToObj(configMap.get(code), CycleType.class);
			cycleType.setCode(code);
			
			
			logger.debug(code);
			logger.debug( " ----  name = " + cycleType.getName());
			logger.debug( " ----  excel = " + cycleType.getExcel());
			logger.debug(" --- cycleNameList = " + cycleType.getCycleNameList());
			
			String excelName = cycleType.getExcel();
			List<String> cycleNameList = cycleType.getCycleNameList();
			// 加载各个生命周期(mei_600, hedian, ...)
			List<T_Cycle> cycleList = new LinkedList<>();
			
			for(String cycleName : cycleNameList){
				try{
					CommonTemplate tp = ioc.get(CommonTemplate.class, cycleName);
					// 设置tp对应的excel文件名
					tp.setExcelName(excelName);
					
					T_Cycle t_cycle = tp.createcycle();
					cycleList.add(t_cycle);
				}catch (Exception e) {
					logger.error("加载生命周期项目出错：" + cycleName, e);
				}
				
			}
			cycleType.setCycleList(cycleList);
			
			cycleTypeList.add(cycleType);
		}
		
		_loaded = true;
	}
	
	/**
	 * 重新加载指定的生命周期类型
	 * <p>在修改原始数据时调用</p>
	 * @cycleTypeCode	生命周期类型编码(electirc/transport/gas)
	 */
	public void reloadCycleTypeList(String cycleTypeCode){
		CycleType cycleType = getCycleTypeByCode(cycleTypeCode);
		
		cycleType.reloadData(ioc);
	}
	
	private CycleType getCycleTypeByCode(String cycleTypeCode){
		Iterator<CycleType> iter = cycleTypeList.iterator();
		while(iter.hasNext()){
			CycleType cType = iter.next(); 
			if(cType.getCode().equals(cycleTypeCode)){
				return cType;
			}
		}
		
		logger.error("查找生命周期类型出错：" + "类型编码" + cycleTypeCode + "未找到！！！");
		return null;
	}
	
}
