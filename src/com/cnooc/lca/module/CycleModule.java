package com.cnooc.lca.module;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.cnooc.lca.excel.ExcelFactory;
import com.cnooc.lca.excel.WriterConfig;
import com.cnooc.lca.excel.parser.ExcelParser;
import com.cnooc.lca.model.NameToUuidMap;
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
	
	@Inject("refer:cycleService")
	private CycleService cycleService;
	
	@Inject("refer:writerConfig")
	private WriterConfig writerConfig;
	/**
	 * 进入“行业数据分析页面”
	 * @param ioc
	 * @param request
	 */
	@At
	@Ok("jsp:page.analyze.stat")
	public void stat(Ioc ioc, HttpServletRequest request, @Param("cycletype") String cycleTypeCode){
		CycleService cycleService = ioc.get(CycleService.class);
		List<CycleType> cycleTypeList = cycleService.getCycleTypeList();
		request.getSession().setAttribute(CYCLETYPE_LIST, cycleTypeList);
		
		CycleType curCycleType;
		// 如果没有设置生命周期类型，默认设置第一个
		if(cycleTypeCode == null){
			curCycleType = cycleTypeList.get(0);
		}else{
			curCycleType = cycleService.getCycleType(cycleTypeCode);
		}
		
		request.setAttribute("curCycleType", curCycleType);
		
		// 读取综合能耗数据
		List<T_Cycle> cycleList = curCycleType.getCycleList();
		request.setAttribute("cycleList", cycleList);
		
		request.setAttribute("influnceNames", curCycleType.getInflunceNames());
		request.setAttribute("influenceNameToUuid", NameToUuidMap.me().getNameToUuidMap(NameToUuidMap.Type.INFLUENCE));
		
		
		request.setAttribute("procNameToUuid", NameToUuidMap.me().getNameToUuidMap(NameToUuidMap.Type.PROCEDURE));
		
		request.setAttribute("procedureNames", cycleService.getProcedureList(cycleTypeCode));
		
	}
	
	
	@At
	@Ok("jsp:page.analyze.config")
	public void config(Ioc ioc, HttpServletRequest request, @Param("cycletype") String cycleType){
		// 读取配置信息
		logger.info("读取配置信息");
		
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
	@Ok("redirect:/cycle/config?saveOk=true&cycletype=${p.cycletype}")
	public void saveConfig(Ioc ioc, HttpServletRequest request, @Param("cycletype") String cycleTypeCode, @Param("::params.")Map<String, String> paramMap){
		
		CycleType curCycleType = cycleService.getCycleType(cycleTypeCode);
		String excelFileName = curCycleType.getExcel();
		ExcelParser excelParser = ExcelFactory.me().getParser(excelFileName);
		excelParser.setAutoCommit(false);
		
		// 保存配置
		for(String key : paramMap.keySet()){
			String[] keyitems = key.split("_");
			int sheetIndex = Integer.parseInt(keyitems[0]);
			String column = keyitems[1];
			int row = Integer.parseInt(keyitems[2]);
			try{
				double value = Double.parseDouble(paramMap.get(key));
				excelParser.setCellValue(sheetIndex, row, column, value);
				logger.debug("保存自定义项目参数, sheet=" + sheetIndex + ", cell=" + column + row + ", value=" + value);
				
			}catch (Exception e) { 
				logger.error("保存值错误 value=" + paramMap.get(key), e);
			}
		}
		
		excelParser.updateBatch();
		excelParser.setAutoCommit(true);
		
		cycleService.reloadCycleTypeList(cycleTypeCode);				// 重新加载配置文件
		writerConfig.load();
	}
	/**
	 * 恢复excel文件
	 * <p>访问路径${base}/cycle/restoreExcel</p>
	 */
	@At
	@Ok("json")
	public void restoreExcel(){
		ExcelFactory.me().restoreExcelFile();
		
		cycleService.loadCycleTypeList();				// 重新加载配置文件
		writerConfig.load();
	}
	
}
