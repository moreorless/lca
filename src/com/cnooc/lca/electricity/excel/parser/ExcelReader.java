package com.cnooc.lca.electricity.excel.parser;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * Excel 表格 操作类
 * 
 * @author jianglei
 * 
 */
public class ExcelReader {
	private String filepath;

	public ExcelReader(String path) {
		this.filepath = path;
	}
	
	/**
	 * @param sheetIndex (表单)
	 * @param columnIndex （栏）
	 * @param rowIndex (行)
	 * @return value （单元格内容）
	 * example：
	 * 		input:	rowIndex = 0, columnIndex = "A"("a")
	 * 		output: value = 0 (in excel)
	 * @throws IOException 
	 * 
	 */
	public double getCellValue(int sheetIndex, int rowIndex, String columnIndex) throws IOException {
		
		double value = 0;
		FileInputStream fis;
		POIFSFileSystem fs;
		fis = new FileInputStream(this.filepath);
		fs = new POIFSFileSystem(fis); // 利用poi读取excel文件流		
		
		HSSFWorkbook wb = new HSSFWorkbook(fs); // 读取excel工作簿
		HSSFSheet sheet = wb.getSheetAt(sheetIndex); // 读取excel的sheet，0表示读取第一个、1表示第二个.....
		
		HSSFRow row = sheet.getRow(rowIndex-1); // 取出sheet中的某一行数据
		
		if (row != null) {
			HSSFCell cell = row.getCell(columnIndex.toLowerCase().toCharArray()[0] - 'a'); // 获取该行中的一个单元格对象
			switch(cell.getCellType()){
			case HSSFCell.CELL_TYPE_FORMULA:
				value = cell.getNumericCellValue(); 				
				break;
			case HSSFCell.CELL_TYPE_NUMERIC:					
				value = cell.getNumericCellValue();// 一般的数据类型在excel中读出来都为float型
				break;
			case HSSFCell.CELL_TYPE_STRING:
				value = 0;
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				value = 0;
				break;
			default:
				value = 0;				
			}
		}
		
		return value;
	}
	
	public static void main(String[] args) {
		try {
			ExcelReader ExcelReader = new ExcelReader("d://LCA总计算模型-2013-01-08.xls");
			double value = ExcelReader.getCellValue(1, 44, "n");
			System.out.println("\t" + value);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
