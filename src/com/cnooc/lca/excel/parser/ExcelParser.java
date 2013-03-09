package com.cnooc.lca.excel.parser;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
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
public class ExcelParser {
	private Logger logger = Logger.getLogger(this.getClass());
	
	private String filepath;
	
	private HSSFWorkbook wb;

	/**
	 * 修改时是否自动提交，为了提高批量修改的效率
	 */
	private boolean autoCommit = true;
	
	/**
	 * 批量提交的队列
	 */
	private Queue<WritableCell> commitQueue = new LinkedList<>();
	
	public ExcelParser(String path) {
		this.filepath = path;
		
		FileInputStream fis = null;
		POIFSFileSystem fs = null;
		try{
			
			fis = new FileInputStream(this.filepath);
			fs = new POIFSFileSystem(fis); // 利用poi读取excel文件流		
			
			wb = new HSSFWorkbook(fs); // 读取excel工作簿
		}catch (Exception e) {
			logger.error("读取excel文件出错, file = " + filepath, e);
			if(fis != null){
				try{
					fis.close();
				}catch (Exception err) {
					err.printStackTrace();
				}
			}
		}finally{
		}
		
	}

	
	/**
	 * @param sheetIndex (表单)
	 * @param columnIndex （栏）
	 * @param rowIndex (行)
	 * @return value （单元格内容）
	 * example：
	 * 		input:	rowIndex = 0, columnIndex = "A"("a")
	 * 		output: cell
	 * @throws IOException 
	 * 
	 */
	public HSSFCell getCell(int sheetIndex, int rowIndex, String columnIndex) throws IOException {

		double value = 0;
		
		HSSFSheet sheet = wb.getSheetAt(sheetIndex); // 读取excel的sheet，0表示读取第一个、1表示第二个.....

		HSSFRow row = sheet.getRow(rowIndex-1); // 取出sheet中的某一行数据

		if (row == null) {
			return null;
		}
		HSSFCell cell = row.getCell(columnIndex.toLowerCase().toCharArray()[0] - 'a'); // 获取该行中的一个单元格对象

		return cell;
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
	
	/**
	 * 是否自动提交
	 * @param autoCommit
	 */
	public void setAutoCommit(boolean autoCommit){
		this.autoCommit = autoCommit;
	}
	
	/**
	 * 写入单元格
	 * @param sheetIndex
	 * @param rowIndex
	 * @param columnIndex
	 * @param value
	 * @throws IOException
	 */
	public void setCellValue(int sheetIndex, int row, String column,
			double value) throws IOException {
		
		synchronized (commitQueue) {
			commitQueue.offer(new WritableCell(sheetIndex, column, row, value));
			
			if(autoCommit){
				updateBatch();
			}
		}
	}
	
	/**
	 * 提交修改
	 * @return
	 */
	public boolean updateBatch(){

		synchronized (commitQueue) {
			WritableCell writeEle = commitQueue.poll();
			while(writeEle != null){
				
				int sheetIndex = writeEle.getSheetIndex();
				int rowIndex = writeEle.getRow();
				String columnIndex = writeEle.getColumn();
				Object value = writeEle.getValue();
				
				HSSFSheet sheet = wb.getSheetAt(sheetIndex);
				HSSFRow row = sheet.getRow(rowIndex - 1);
				
				if(row != null){
					HSSFCell cell = row.getCell(columnIndex.toLowerCase().toCharArray()[0] - 'a');
					switch (cell.getCellType()) {
					
					case HSSFCell.CELL_TYPE_NUMERIC:
						cell.setCellValue((double)value);
						break;
					case HSSFCell.CELL_TYPE_BLANK:
						cell.setCellValue(0);
						break;
					default:
						
					}
			}
				
			writeEle = commitQueue.poll();
		}
			
		try{
			
			// 写入后重新计算公式
			HSSFFormulaEvaluator.evaluateAllFormulaCells(wb);
			FileOutputStream fileOut = new FileOutputStream(this.filepath);
			wb.write(fileOut);
			fileOut.close();
		}catch (Exception e) {
			logger.error("写入excel文件错误, " + filepath, e);
			return false;
		}
		
		}
		return true;
	}

	public static void main(String[] args) {
		try {
			ExcelParser excelReader = new ExcelParser("d://test.xls");
			
			excelReader.setAutoCommit(false);
			
			double value = excelReader.getCellValue(0, 3, "d");
			System.out.println("\tBefore modify:\t D3=" + excelReader.getCellValue(0, 3, "d"));
			System.out.println("\tBefore modify:\t D4=" + excelReader.getCellValue(0, 4, "d"));
			System.out.println("\tBefore modify:\t G6=" + excelReader.getCellValue(0, 6, "g"));
			
			excelReader.setCellValue(0, 3, "d", 200.3);
			excelReader.setCellValue(0, 4, "d", 10001.2);
			
			excelReader.updateBatch();
			
			value = excelReader.getCellValue(0, 3, "d");
			
			System.out.println("\tAfter modify:\t" + value);
			System.out.println("\tAfter modify:\t D4=" + excelReader.getCellValue(0, 4, "d"));
			System.out.println("\tAfter modify:\t G6=" + excelReader.getCellValue(0, 6, "g"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
