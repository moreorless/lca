<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
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
		  <li class="active">
		    <a href="#">综合能耗统计</a>
		  </li>
		  <li><a href="#">碳排放统计</a></li>
		  <li><a href="#">影响潜能统计</a></li>
		</ul>
  		
  		<div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
  		<div class="seperator"></div>
  		<div><b>${cycle.stationName}</b>(${cycle.unit})</div>
  		
		<table class="table table-striped table-bordered table-condensed">
			<thead>
				<tr>
					<td width="500" align="center">工序</td>
					<c:forEach items="${consumptionNames}" var="consumptionName">
					<td>${consumptionName}</td>
					</c:forEach>
					<c:forEach items="${emissionNames}" var="emissionName">
					<td>${emissionName}</td>
					</c:forEach>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${cycle.procedures}" var="procedure">
				<tr>
					<td width="500" align="center">${procedure.name }</td>
					<c:forEach items="${procedure.consumption}" var="cCell">
					<td>${cCell.value}</td>
					</c:forEach>
					<c:forEach items="${procedure.emission}" var="eCell">
					<td>${eCell.value}</td>
					</c:forEach>
				</tr>
				</c:forEach>
				
			</tbody>
		</table>  	
  	
  	</div>
  	
  	<%@ include file="/includes/footer.jsp" %>
   <script type="text/javascript" src="${base}/js/jquery.js"></script>
   <script type="text/javascript" src="${base}/js/bootstrap.js"></script>
   <script type="text/javascript" src="${base}/amchart/swfobject.js"></script>
   
   <script type="text/javascript">
		var so = new SWFObject("${base}/amchart/amcolumn_1.6.0.1/amcolumn/amcolumn.swf", "amcolumn", "100%", "400", "8", "#FFFFFF");
		so.addVariable("path", "${base}/amchart/");
		
		so.addVariable("settings_file", encodeURIComponent("${base}/common/amchart/stat/amcolumn_settings.xml"));
		so.addVariable("data_file", encodeURIComponent("${base}/common/amchart/stat/amcolumn_data.xml"));
		
		so.addParam("wmode", "opaque");
		so.write("amcharts");
	</script>
  </body>
</html>