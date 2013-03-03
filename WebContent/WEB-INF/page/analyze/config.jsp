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
  		<c:param name="currentNav">config</c:param>
  	</c:import>
  	<div id="wrap" class="container">
		<c:if test="${param.saveOk == true }">
		<div>信息保存成功</div>
		</c:if>
		<form action="${base}/ele/saveConfig">
		  <fieldset>
		    <legend>自定义项目参数</legend>
		    <label>Label name</label>
		    <input type="text" placeholder="Type something…">
		    <span class="help-block">Example block-level help text here.</span>
		    <label class="checkbox">
		      <input type="checkbox"> Check me out
		    </label>
		    <button type="submit" class="btn">Submit</button>
		  </fieldset>
		</form>
	
	</div>
  	
  	<%@ include file="/includes/footer.jsp" %>
   <script type="text/javascript" src="${base}/js/jquery.js"></script>
   <script type="text/javascript" src="${base}/js/bootstrap.js"></script>

   

  </body>
</html>