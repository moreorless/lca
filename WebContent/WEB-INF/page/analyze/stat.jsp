<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<link rel = "Shortcut Icon" href="${base }/favicon.ico" />
    <title>中海油碳理层分析系统</title>
    <link type="text/css" rel="stylesheet" href="${base}/css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="${base}/css/index.css"/>
	<style type="text/css">
		.seperator{height:20px;}
	</style>
  </head>
  <body>
  	<c:import url="/includes/header.jsp">
  		<c:param name="currentNav">statistic</c:param>
  	</c:import>
  	
  	<div id="wrap" class="container">
  		
  		<ul class="nav nav-pills">
		  <li <c:if test="${param.target == 'consumption' }">class="active"</c:if>>
		    <a href="${base}/cycle/stat?cycletype=${param.cycletype}&target=consumption">综合能耗统计</a>
		  </li>
		  <li <c:if test="${param.target == 'emission' }">class="active"</c:if>>
		  	<a href="${base}/cycle/stat?cycletype=${param.cycletype}&target=emission">碳排放统计</a>
		  </li>
		  <li <c:if test="${param.target == 'influence' }">class="active"</c:if>>
		  	<a href="${base}/cycle/stat?cycletype=${param.cycletype}&target=influence">影响潜能统计</a>
		  </li>
		  
		  <li style="float:right;color:#005580"><b>${curCycleType.name}</b></li>
		</ul>
  		
  		<c:import url="/WEB-INF/page/analyze/includes/consumption.jsp">
  		</c:import>
  	
  	</div>
  	
  <%@ include file="/includes/footer.jsp" %>
   <script type="text/javascript" src="${base}/js/jquery.js"></script>
   <script type="text/javascript" src="${base}/js/bootstrap.js"></script>
   <script type="text/javascript" src="${base}/amchart/swfobject.js"></script>
   
   <script type="text/javascript">
		
   		<c:choose>
   		</c:choose>
		<c:if test="${param.target == 'consumption'}">
		</c:if>
		
		var so = new SWFObject("${base}/amchart/amcolumn_1.6.0.1/amcolumn/amcolumn.swf", "amcolumn", "100%", "400", "8", "#FFFFFF");
		so.addVariable("path", "${base}/amchart/");
		so.addVariable("settings_file", encodeURIComponent("${base}/common/amchart/stat/amcolumn_settings.xml"));
		so.addVariable("data_file", encodeURIComponent("${base}/common/amchart/stat/amcolumn_data.xml"));
		
		so.addParam("wmode", "opaque");
		so.write("amcharts");
	</script>
  </body>
</html>