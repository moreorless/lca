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
		<form action="${base}/cycle/saveConfig" class="form-horizontal">
		  <fieldset>
		    <legend style="font-size:16px">自定义项目参数  <label style="float:right; color:#005580"><b>${curCycleType.name}</b></label></legend>
		    
		    
		    <div class="control-group">
		    	<label class="control-label" for="inputEmail">参数1</label>
		    	<div class="controls">
		    		<input type="text" id="inputEmail" />
		    	</div>
		    </div>
		    
		    <div class="control-group">
		    	<label class="control-label" for="inputEmail2">参数2</label>
		    	<div class="controls">
		    		<input type="text" id="inputEmail2" />
		    	</div>
		    </div>
		    
		    <div class="control-group">
    			<div class="controls">
				    <button type="submit" class="btn">Submit</button>
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