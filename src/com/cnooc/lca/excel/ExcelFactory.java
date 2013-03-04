package com.cnooc.lca.excel;

import java.io.File;

import com.cnooc.lca.common.GlobalConfig;
import com.cnooc.lca.excel.parser.ExcelParser;

/**
 * excel工厂类，单例
 * @author gaoxl
 *
 */
public class ExcelFactory {

	private static ExcelFactory _instance = new ExcelFactory();
	private ExcelFactory(){}
	
	// excel文件名
	public static String folderPath = GlobalConfig.getContextValue("web.dir") + File.separator +"upload";
	
	public static String electricityExcelFile = "electricity.xls";
	public static String transportExcelFile = "transport.xls";
	public static String gasExcelFile = "gas.xls";
	
	private static boolean _firstLoad = true;
	public static ExcelFactory me(){
		if(_firstLoad){
			_instance.loadExcelTp();
			_firstLoad = false;
		}
		return _instance;
	}
	
	
	/**
	 *加载excel模板 
	 */
	public void loadExcelTp(){
		
	}
	
	/**
	 * 获取excel文件解析器
	 * @param excelName excel文件名
	 * @return
	 */
	public ExcelParser getParser(String excelName){
		return new ExcelParser(folderPath + File.separator + excelName);
	}
	
}
