<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>chart demo</title>
<script type="text/javascript" src="${base}/amchart/swfobject.js"></script>
</head>
<body>
	<div id="amcharts_1261636719173">You need to upgrade your Flash Player</div>
	<script type="text/javascript">
		var so = new SWFObject("${base}/amchart/amcolumn_1.6.0.1/amcolumn/amcolumn.swf", "amcolumn", "600", "400", "8", "#FFFFFF");
		so.addVariable("path", "amcolumn/");
		so.addVariable("settings_file", encodeURIComponent("${base}/WEB-INF/page/demo/amline_settings.xml"));
		so.addVariable("data_file", encodeURIComponent("${base}/"));
		so.write("amcharts_1261636719173");
	</script>
</body>
</html>