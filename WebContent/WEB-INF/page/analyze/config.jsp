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
		<div id="tip_area" style="height:40px;line-height:40px;" class="alert-success">信息保存成功</div>
		</c:if>
		<form action="${base}/cycle/saveConfig?cycletype=${param.cycletype}" class="form-horizontal" method="post">
		  <fieldset>
		    <legend style="font-size:16px">自定义项目参数  <label style="float:right; color:#005580"><b>${curCycleType.name}</b></label></legend>
		    
		    <c:forEach items="${curCycleType.paramConfigure.sheets}" var="wSheet">
		    	<a href="#" class="btn btn-primary disabled">${wSheet.name}</a>
		    	<c:forEach items="${wSheet.cells}" var="wCell">
		    		<div class="control-group<c:if test="${wCell.formula }"> error</c:if><c:if test="${!wCell.formula }"> success</c:if>">
				    	<label class="control-label" for="inputEmail"><b>${wCell.paramName}</b></label>
				    	<div class="controls">
				    		<input type="text" value="<fmt:formatNumber value='${wCell.value }' pattern='.000' />"
				    			name="params.${wCell.sheetIndex}_${wCell.column}_${wCell.row}" 
				    			<c:if test="${wCell.formula }">disabled</c:if>/>
				    		<span class="help-inline"><c:if test="${wCell.formula }">中间结果，不允许修改</c:if></span>
				    	</div>
				    </div>		
		    	</c:forEach>
		    </c:forEach>
		    
		    <div class="control-group">
    			<div class="controls">
				    <button type="submit" class="btn">保存</button>
				</div>
			</div>
		  </fieldset>
		</form>
	
	</div>
  	
  	<%@ include file="/includes/footer.jsp" %>
   <script type="text/javascript" src="${base}/js/jquery.js"></script>
   <script type="text/javascript" src="${base}/js/bootstrap.js"></script>

   <script type="text/javascript">
   	$(document).ready(function(){
   		setTimeout(function(){
   			$("#tip_area").hide('slow');	
   		}, 3000);
   		
   	});
   </script>

  </body>
</html>