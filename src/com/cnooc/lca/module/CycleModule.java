package com.cnooc.lca.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.cnooc.lca.common.GlobalConfig;
import com.cnooc.lca.excel.ExcelFactory;
import com.cnooc.lca.excel.ProcedureParam;
import com.cnooc.lca.excel.ProcedureTemplate;
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
		if(curCycleType.getNameToUuidMap() != null){
			request.setAttribute("influenceNameToUuid", curCycleType.getNameToUuidMap().getNameToUuidMap(NameToUuidMap.Type.INFLUENCE));
		}
		
		
		if(curCycleType.getNameToUuidMap() != null){
			request.setAttribute("procNameToUuid", curCycleType.getNameToUuidMap().getNameToUuidMap(NameToUuidMap.Type.PROCEDURE));
		}
		
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
	// @Ok("redirect:/cycle/config?saveOk=true&cycletype=${p.cycletype}")
	@Ok("json")
	@Fail("json")
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
	
	@At	
	@Ok("json")
	public List<T_Cycle> listCycles(@Param("cycletype") String cycleTypeCode){
		CycleType curCycleType = cycleService.getCycleType(cycleTypeCode);
		return curCycleType.getCycleList();
	}
	
	/**
	 * 天然气产业链视图
	 */
	@At
	@Ok("jsp:page.gas.view")
	public void gasView(HttpServletRequest request, Ioc ioc){
		CycleType cycleType = cycleService.getCycleType("gas");
		
		String templateName = cycleType.getTemplateName();
		ProcedureTemplate tp = ioc.get(ProcedureTemplate.class, templateName);
		
		List<ProcedureParam> procedures = tp.getProcedures();
		
		request.setAttribute("curCycleType", cycleType);
		request.setAttribute("procedures", procedures);
		
	}
	
	/**
	 * 复制文件夹
	 * 
	 * @param oldPath
	 * @param newPath
	 */
	public void copyFolder(String oldPath, String newPath) {
		try {
			(new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (int i = 0; i < file.length; i++) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + file[i]);
				} else {
					temp = new File(oldPath + File.separator + file[i]);
				}
				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);
					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
				}
			}
		} catch (Exception e) {
			System.out.println("复制整个文件夹内容操作出错");
			e.printStackTrace();
		}
	}

	/**
	 * 备份bak文件夹，按照时间创建目录
	 */
	public void backupBakFolder() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_mm_dd_hh_mm_ss");
		Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
		String str = formatter.format(curDate);

		String bakHistoryPath = GlobalConfig.getContextValue("web.dir")
				+ File.separator + "upload" + File.separator + "bak_history"
				+ File.separator + str;

		String bakPath = GlobalConfig.getContextValue("web.dir")
				+ File.separator + "upload" + File.separator + "bak";

		copyFolder(bakPath, bakHistoryPath);
	}

	@At
	// @Ok("redirect:/cycle/config?saveOk=true&cycletype=${p.cycletype}")
	@Ok("json")
	@Fail("json")
	public void uploadDataFiles(Ioc ioc, HttpServletRequest request,
			@Param("cycletype") String cycleTypeCode,
			@Param("::params.") Map<String, String> paramMap) {

		CycleType curCycleType = cycleService.getCycleType(cycleTypeCode);
		String excelFileName = curCycleType.getExcel();

		File file = new File(GlobalConfig.getContextValue("web.dir")
				+ File.separator + "upload" + File.separator + "bak"
				+ File.separator + excelFileName);
		if (file.exists())
			file.delete();

		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					System.out.println("表单参数名:" + item.getFieldName()
							+ "，表单参数值:" + item.getString("UTF-8"));
				} else {
					if (item.getName() != null && !item.getName().equals("")) {
						System.out.println("上传文件的大小:" + item.getSize());
						System.out.println("上传文件的类型:" + item.getContentType());
						// item.getName()返回上传文件在客户端的完整路径名称
						System.out.println("上传文件的名称:" + item.getName());

						item.write(file);
						request.setAttribute("upload.message", "上传文件成功！");
					} else {
						request.setAttribute("upload.message", "没有选择上传文件！");
					}
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "上传文件失败！");
		}

		restoreExcel();
		backupBakFolder();

	}
	
	
}
