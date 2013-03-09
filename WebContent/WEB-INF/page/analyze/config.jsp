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
		<c:if test='${param.saveOk}'>
		<div id="tip_area" style="height:40px;line-height:40px;" class="alert-success">保存成功</div>
		</c:if>
		<c:if test='${param.restoreOk}'>
		<div id="tip_area" style="height:40px;line-height:40px;" class="alert-success">恢复成功</div>
		</c:if>
		<form action="${base}/cycle/saveConfig?cycletype=${param.cycletype}" class="form-horizontal" method="post">
		  <fieldset>
		    <legend style="font-size:16px;height:50px;line-height:50px;">
		    	<label style="float:left;height:60px;line-height:60px;">自定义项目参数</label>  
		    	<label style="float:right; color:#005580;height:60px;line-height:60px;"><b>${curCycleType.name}</b></label>
		    
		    </legend>
		    
		    <c:forEach items="${curCycleType.paramConfigure.sheets}" var="wSheet">
		    	<label class="label label-info">${wSheet.name}</label>
		    	<c:forEach items="${wSheet.cells}" var="wCell">
		    		<div class="control-group<c:if test="${wCell.formula }"> error</c:if><c:if test="${!wCell.formula }"> </c:if>">
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
				    <button type="submit" class="btn btn-primary">保存</button>
				    <button type="button" class="btn" id="btn-restore">恢复初始值</button>
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
   		
   		$("#btn-restore").click(function(){
   			$.ajax({
   				url : '${base}/cycle/restoreExcel',
   				success : function(){
   					window.location = '${base}/cycle/config?cycletype=${param.cycletype}&restoreOk=true';
   				},
   				error : function(){
   					
   				}
   			});
   		});
   		
   	});
   </script>

  </body>
</html>