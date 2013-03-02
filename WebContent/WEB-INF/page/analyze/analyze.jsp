<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>LCA</title>
    <link type="text/css" rel="stylesheet" href="${base}/css/bootstrap.css" />
  </head>
  <body>
  	<div id="amcharts_1261636719173" style="height: 100%;">You need to upgrade your Flash Player</div>
  	
  	
  	<script type="text/javascript" src="${base}/amchart/swfobject.js"></script>
  	
	<script type="text/javascript">
		var so = new SWFObject("${base}/amchart/amcolumn_1.6.0.1/amcolumn/amcolumn.swf", "amcolumn", "100%", "100%", "8", "#FFFFFF");
		so.addVariable("path", "${base}/amchart/");
		so.addVariable("settings_file", encodeURIComponent("${base}/jsp/good/amline_settings.xml"));
		so.addVariable("data_file", encodeURIComponent("${base}/good/getColumn/${param.command}.nut"));
		so.write("amcharts_1261636719173");
	</script>
  
  </body>
  
 </html>