package com.cnooc.lca.excel;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.nutz.http.sender.PostSender;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.json.Json;

import com.cnooc.lca.excel.parser.ExcelParser;
import com.cnooc.lca.excel.parser.WritableCell;
import com.cnooc.lca.excel.parser.WritableExcel;
import com.cnooc.lca.excel.parser.WritableSheet;
import com.cnooc.lca.model.T_Position;
import com.cnooc.lca.service.CycleService;
import com.cnooc.lca.service.CycleType;

/**
 * excel文件写入配置
 * @author gaoxl
 *
 */
@IocBean
public class WriterConfig {
	
	private Logger logger = Logger.getLogger(this.getClass());

	private static String WRITER_CONFIG_FILE = "writerconfig.js";
	
	@Inject("refer:cycleService")
	private CycleService cycleService;
	
	/**
	 * 加载配置
	 */
	public void load(){
		JsonLoader reader = new JsonLoader(WRITER_CONFIG_FILE);
		Map<String, Object> writerConfig = reader.getMap().get("writerconfig");
		
		logger.debug("加载自定义项目配置文件  --  start");
		// 解析配置文件
		for(String cycleTypeCode : writerConfig.keySet()){
			CycleType cycleType = cycleService.getCycleType(cycleTypeCode);
			
			Map excelMap = (Map)writerConfig.get(cycleTypeCode);
			String excelFile = (String)excelMap.get("excel");
			List sheets = (List)excelMap.get("sheets");
			
			ExcelParser parser = ExcelFactory.me().getParser(excelFile);
			if(parser == null) continue;
			
			WritableExcel wExcel = new WritableExcel();
			wExcel.setName(cycleType.getCode());
			for(Object sheet:sheets){
				WritableSheet wSheet = new WritableSheet();
				
				Map sheetMap = (Map)sheet;
				int sheetIndex = (int)sheetMap.get("index");
				String sheetName = (String)sheetMap.get("name");
				wSheet.setIndex(sheetIndex);
				wSheet.setName(sheetName);
				logger.debug("表格 -- " + sheetName);
				
				Map<String, String> paramMap = (Map)sheetMap.get("params");
				
				for(String paramName : paramMap.keySet()){
					String posStr = paramMap.get(paramName);
					T_Position cellPos = parsePosition(posStr);
					
					
					double cellValue = 0;
					try{
						cellValue = parser.getCellValue(sheetIndex, cellPos.getRow(), cellPos.getColumn());
					}catch (Exception e) {
						logger.error("读取自定义配置参数项单元格初始值错误, sheet=" + sheetIndex 
								+ ", pos=(" + posStr + ")", e);
					}
					WritableCell wCell = new WritableCell(sheetIndex, cellPos.getColumn(), cellPos.getRow(), cellValue);
					logger.debug("参数 -- name=" + paramName + ", pos=" + posStr + ", value=" + cellValue);
					wCell.setDescription(paramName);
					wCell.setParamName(paramName);
					wSheet.addCell(wCell);
				}
				wExcel.addSheet(wSheet);
				
				// 设置到生命周期类型中
				cycleType.setParamConfigure(wExcel);
				
				logger.debug("加载自定义项目配置文件  --  end");
			}
		}
		
	}
	
	/**
	 * G18 解析出 列 和 行
	 * @param posStr
	 * @return {row, column}
	 */
	private T_Position parsePosition(String posStr){
		int row = 0;
		String column = "";
		
		int rowIndex = 0;
		for(rowIndex = 0; rowIndex< posStr.length(); rowIndex++){
			char c = posStr.charAt(rowIndex);
			if(c >= '0' && c <= '9'){
				break;
			}
			column += c;
		}
		
		row = Integer.parseInt(posStr.substring(rowIndex));
		
		return new T_Position(row, column);
	}
}
