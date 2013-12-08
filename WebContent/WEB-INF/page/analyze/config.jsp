<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <title>中海油碳里程分析系统</title>
    <link type="text/css" rel="stylesheet" href="${base}/css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="${base}/css/index.css"/>
	<style type="text/css">
		.seperator{height:20px;}
		#wrap {padding:5px 10px}
		.alert {margin-bottom : 0}
	</style>
  </head>
  <body>
  	<%--
  	<c:import url="/includes/header.jsp">
  		<c:param name="currentNav">config</c:param>
  	</c:import>
  	 --%>
  	<div id="wrap">
		<c:if test='${param.saveOk}'>
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>保存成功</strong>
		</div>
		</c:if>
		<c:if test='${param.restoreOk}'>
		<div class="alert alert-success">
			<button type="button" class="close" data-dismiss="alert">&times;</button>
			<strong>恢复初始值成功</strong>
		</div>
		</c:if>
		<form action="${base}/cycle/saveConfig?cycletype=${param.cycletype}" class="form-horizontal" method="post" id="myForm">
		    <div class="control-group">
		    	<label class="control-label">
		    		<b>
		    			<c:choose>
		    				<c:when test="${param.cycletype=='electric'}">发电方式选择</c:when>
		    				<c:when test="${param.cycletype=='transport'}">交通燃料选择</c:when>
		    				<c:when test="${param.cycletype=='gas'}"></c:when>
		    			</c:choose>
		    		</b>
		    	</label>
				<div class="controls">
			    <select id="sheet-selector">
			    	<c:forEach items="${curCycleType.paramConfigure.sheets}" var="wSheet">
			    	<option value="${wSheet.index}">${wSheet.name}</option>
			    	</c:forEach>
			    </select>
		    	</div>
		    </div>
		    <div id="param-box">
		    <c:forEach items="${curCycleType.paramConfigure.sheets}" var="wSheet">
		    	<%--
		    		<label class="label label-info">${wSheet.name}</label>
		    	 --%>
		    	<div id="sheet_${wSheet.index}" style="display:none">
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
		    	</div>
		    </c:forEach>
		    
		    <c:if test="${param.cycletype == 'transport' }">
		    <div class="control-group">
		    	<label class="control-label" for="inputEmail"><b>进口LNG车选定</b></label>
		    	<div class="controls">
		    	<label class="radio inline">
		    		<input type="radio" name="lng_conf" value="local"
		    			<c:if test="${lng_conf == 'local'}">checked</c:if>/>国内视角
	    		</label>
	    		<label class="radio inline">
		    		<input type="radio" name="lng_conf" value="global"
		    		<c:if test="${lng_conf == 'global'}">checked</c:if>/>全球视角
	    		</label> 
		    	</div>
		    </div>
		    </c:if>
		    
		    </div>
		    <div class="control-group">
    			<div class="controls">
    				<a href="javascript://" id="btn-save" class="btn btn-primary">保存</a>
    				<a href="javascript://" id="btn-cancel" class="btn">取消</a>
				</div>
			</div>
		</form>
	
	</div>
  	
  	<!-- Modal -->
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
    <h3 id="myModalLabel">恢复初始值</h3>
  </div>
  <div class="modal-body">
    <p>恢复初始值后，自定义的参数项将会全部恢复初始值，确认执行该操作？</p>
  </div>
  <div class="modal-footer">
    <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button>
    <button class="btn btn-warning" id="btn-restore">执行初始化</button>
  </div>
</div>
  	
  	<%--
  	<%@ include file="/includes/footer.jsp" %>
    --%>
   <script type="text/javascript" src="${base}/js/jquery.js"></script>
   <script type="text/javascript" src="${base}/js/bootstrap.js"></script>

   <script type="text/javascript">
   	$(document).ready(function(){
   		setTimeout(function(){
   			$("#tip_area").hide('slow');	
   		}, 5000);
   		
   		$("#btn-restore").click(function(){
   			$.ajax({
   				url : '${base}/cycle/restoreExcel',
   				type : 'post',
   				dataType : 'json',
   				success : function(){
   					window.location = '${base}/cycle/config?cycletype=${param.cycletype}&restoreOk=true';
   				},
   				error : function(){
   					
   				}
   			});
   		});
   		
   		$('#btn-save').click(function(){
   			$.ajax({
   				url : '${base}/cycle/saveConfig?cycletype=${param.cycletype}',
   				type : 'post',
   				dataType : 'json',
   				data : $("#myForm").serialize(),
   				success : function(){
   					window.parent.location.reload();
   				},
   				error : function(){
   					alert('保存参数出错');
   				}
   			});
   			
   		});
   		$('#btn-cancel').click(function(){
   			window.parent.ParamDialog.close();
   		});
   		
   		$('#sheet_' + $('#sheet-selector').val()).show();
   		$('#sheet-selector').change(function(){
   			$('#param-box').children().hide();
   			$('#sheet_' + $(this).val()).show();
   		});
   		
   	});
   </script>

  </body>
</html>