<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- c:redirect url="/cycle/stat?cycletype=electric&target=consumption" /-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>LCA</title>
<link rel="Shortcut Icon" href="${base }/favicon.ico" />
<link type="text/css" rel="stylesheet" href="${base}/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/index.css" />

<style type="text/css">
body {
	margin: 0px;
	padding: 0px;
	background-image: url(images/bg5.jpg);
	background-size: cover;
	width: 100%;
}
</style>
</head>

<body>
	<div style="width: 100%; height: 100%; position: relative;">
		<div
			onclick="location.href='${base}/cycle/stat?cycletype=electric&target=consumption'"
			style="cursor: pointer; width: 22%; height: 40%;  position: fixed; _position: absolute; top: 35%; margin-left: 8%; z-index: 100;"></div>
		<div
			onclick="location.href='${base}/cycle/stat?cycletype=transport&target=consumption'"
			style="cursor: pointer; width: 22%; height: 40%;  position: fixed; _position: absolute; top: 35%; margin-left: 40%; z-index: 100;"></div>
		<div
			onclick="location.href='${base}/cycle/stat?cycletype=gas&target=consumption'"
			style="cursor: pointer; width: 22%; height: 40%;  position: fixed; _position: absolute; top: 35%; margin-left: 72%; z-index: 100;"></div>
		<div>
</body>
</html>
