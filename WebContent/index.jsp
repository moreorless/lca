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
	
}
</style>
</head>

<body>
	<div id="idnex-wrap">
		<div class="index-banner">
			<div class="index-banner-inner">
				<h1 class="index-banner-title">中海油碳里程分析系统</h1>
			</div>
		</div>

		<ul class="index-list">
			<li>
				<a href="${base}/cycle/stat?cycletype=electric&target=consumption&statBy=generator">
					<div  style="background-color:#7daeeb">
					<img src="${base}/images/banner/Oil_industry-03-128.png" alt="" width="128px" height="128px" />
					</div>
					<h4>发电周期</h4>
				</a>
			</li>	
			<li>
				<a href="${base}/cycle/stat?cycletype=transport&target=consumption&statBy=generator">
					<div style="background-color:#87b33e">
						<img src="${base}/images/banner/Oil_industry-02-128.png" alt="" width="128px" height="128px" />	
					</div>
					<h4>交通燃料</h4>
				</a>
			</li>
			<li>
				<a href="${base}/cycle/gasView?cycletype=gas&target=consumption&statBy=generator">
					<div style="background-color:#f7af4a">
					<img src="${base}/images/banner/refinery-128.png" alt="" width="128px" height="128px" />
					</div>
					<h4>天然气产业链</h4>
				</a>
			</li>
		</ul>

	<div>

</body>
</html>
