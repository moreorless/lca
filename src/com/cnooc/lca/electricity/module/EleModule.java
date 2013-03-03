package com.cnooc.lca.electricity.module;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.cnooc.lca.electricity.bean.Station;
import com.cnooc.lca.electricity.excel.CommonTemplate;
import com.cnooc.lca.electricity.excel.T_Cell;
import com.cnooc.lca.electricity.excel.T_Cycle;
import com.cnooc.lca.electricity.excel.T_Position;
import com.cnooc.lca.electricity.excel.T_Procedure;
import com.cnooc.lca.electricity.excel.Template;
import com.cnooc.lca.electricity.excel.parser.ExcelReader;


@IocBean(create="init")
@InjectName
@At("/ele")
public class EleModule {
	private Logger logger = Logger.getLogger(this.getClass());
	
	ExcelReader excelReader;
	
	public void init(){
		excelReader = new ExcelReader("d://LCA总计算模型-2013-01-08.xls");
	}
	
	/**
	 * 进入“行业数据分析页面”
	 * @param ioc
	 * @param request
	 */
	@At
	@Ok("jsp:page.analyze.stat")
	public void stat(Ioc ioc, HttpServletRequest request){
		
		// mei_600生命周期模板
		Template mei_600 = ioc.get(Template.class, "mei_600");
		
		CommonTemplate mei_600_new = ioc.get(CommonTemplate.class, "mei_600_new");
		
		T_Cycle cycle_m600 = createCycle(mei_600);
		request.setAttribute("cycle", cycle_m600);
		
		
		if(cycle_m600 != null && cycle_m600.getProcedures().size() > 0);
		T_Procedure t_procedure = cycle_m600.getProcedures().get(0);
		// 获取全部能耗指标
		request.setAttribute("consumptionNames", t_procedure.getConsumptionNames());
		// 获取全部排放指标
		request.setAttribute("emissionNames", t_procedure.getEmissionNames());
	}
	
	@At
	@Ok("jsp:page.analyze.config")
	public void config(){
		// 读取配置信息
		logger.info("读取配置信息");
	}
	
	@At
	@Ok("redirect:/ele/config?saveOk=true")
	public void saveConfig(HttpServletRequest request, @Param("..")Station station){
		// 保存配置
		logger.info("保存配置信息");
	}
	
	
	private T_Cycle createCycle(Template template){
		T_Cycle cycle = new T_Cycle();
		cycle.setSheetIndex(template.getSheetIndex());
		cycle.setStationName(template.getStationName());
		cycle.setUnit(template.getUnit());
		cycle.setProcedures(new LinkedList<T_Procedure>());
		
		// 解析工序
		Iterator<Map<String, Object>> iter = template.getProcedures().iterator();
		while(iter.hasNext()){
			Map<String, Object> procedure = iter.next();
			String procedureName = (String)procedure.get("name");
			List<Map<String, String>> consumption = (List<Map<String, String>>)procedure.get("consumption");
			List<Map<String, String>> emission = (List<Map<String, String>>)procedure.get("emission");
			
			T_Procedure t_procedure = new T_Procedure();
			t_procedure.setName(procedureName);
			t_procedure.setConsumption(new LinkedList<T_Cell>());
			t_procedure.setEmission(new LinkedList<T_Cell>());
			
			logger.debug("工序 ：" + procedureName);
			for(Map<String, String> item : consumption){
				String name = item.get("name");
				String cell = item.get("cell");
				String columnIndex = cell.split(",")[0];
				int rowIndex = Integer.parseInt(cell.split(",")[1]);
				
				double cellValue = 0;
				try{
					cellValue = excelReader.getCellValue(template.getSheetIndex(), rowIndex, columnIndex);
				}catch (Exception e) {
					logger.error("读取单元格内容出错, " + cell, e);
				}
				logger.debug("能耗 - " + item.get("name") + " - " + item.get("cell") + " - " + cellValue);
				
				T_Cell t_cell = new T_Cell();
				t_cell.setName(name);
				t_cell.setValue(cellValue);
				t_cell.setPosition(new T_Position(rowIndex, columnIndex));
				t_procedure.getConsumption().add(t_cell);
			}
			
			for(Map<String, String> item : emission){
				String name = item.get("name");
				String cell = item.get("cell");
				String columnIndex = cell.split(",")[0];
				int rowIndex = Integer.parseInt(cell.split(",")[1]);
				
				double cellValue = 0;
				try{
					cellValue = excelReader.getCellValue(template.getSheetIndex(), rowIndex, columnIndex);
				}catch (Exception e) {
					logger.error("读取单元格内容出错, " + cell, e);
				}
				logger.debug("排放 - " + item.get("name") + " - " + item.get("cell") + " - " + cellValue);
				
				T_Cell t_cell = new T_Cell();
				t_cell.setName(name);
				t_cell.setValue(cellValue);
				t_cell.setPosition(new T_Position(rowIndex, columnIndex));
				t_procedure.getEmission().add(t_cell);
			}
			
			cycle.getProcedures().add(t_procedure);
		}
		return cycle;
	}
	
	
}
