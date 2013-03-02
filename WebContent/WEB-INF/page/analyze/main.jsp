<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>LCA</title>
    <link type="text/css" rel="stylesheet" href="${base}/css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="${base}/css/index.css"/>
	<style type="text/css">
		.seperator{height:20px;}
	</style>
  </head>
  <body>
  	<%@ include file="/includes/header.jsp" %>
  	<div id="wrap" class="container">
  		
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
		var so = new SWFObject("${base}/amchart/amcolumn_1.6.0.1/amcolumn/amcolumn.swf", "amcolumn", "600", "400", "8", "#FFFFFF");
		so.addVariable("path", "amcolumn/");
		so.addVariable("settings_file", encodeURIComponent("${base}/amchart/amcolumn_settings.xml"));
		so.addVariable("data_file", encodeURIComponent("${base}/amchart/amcolumn_data.txt"));
		so.write("amcharts");
	</script>
  </body>
</html>