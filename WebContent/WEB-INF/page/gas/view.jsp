<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="Shortcut Icon" href="${base }/favicon.ico" />
<title>中海油碳里程分析系统</title>
<link type="text/css" rel="stylesheet" href="${base}/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/index.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/stat.css" />
<style type="text/css">
.seperator {
	height: 10px;
}
</style>
</head>
<body>
	<c:import url="/includes/header.jsp">
		<c:param name="currentNav">statistic</c:param>
	</c:import>

	<div id="wrap" class="container">
		<div>
			<ul class="nav nav-pills" style="margin: 10px 0 10px 0">
				<li
					<c:if test="${param.target == 'consumption' }">class="active"</c:if>>
					<a
					href="${base}/cycle/gasView?cycletype=${param.cycletype}&target=consumption&statBy=${param.statBy}">综合能耗统计</a>
				</li>
				<li
					<c:if test="${param.target == 'emission' }">class="active"</c:if>>
					<a
					href="${base}/cycle/gasView?cycletype=${param.cycletype}&target=emission&statBy=${param.statBy}">碳排放统计</a>
				</li>
			</ul>
		</div>
		<div>
		
		<div class="row-fluid">
			<div class="span8">
				
				<div class="row-fluid" style="500px">
				<c:forEach items="${procedures}" var="procedure">
					<div style="float:left; margin-left:40px;">
						<h4 style="text-align:center"><c:out value="${procedure.name }" /></h4>
						
						<c:forEach items="${procedure.items}" var="procedureItem">
							<c:if test="${param.target == 'consumption' }">
								<c:set var="title" value="${procedureItem.consumptionValue }"></c:set>
							</c:if>
							<c:if test="${param.target == 'emission' }">
								<c:set var="title" value="${procedureItem.emissionValue }"></c:set>
							</c:if>
							
							<div style="background-color:#ccc; padding:4px; margin-bottom:10px;width:100px;"
								title="${title}">
								<c:if test="${!procedure.multipul }">
									<input type="radio" name="radio_paramItem"></input>
								</c:if>
								<c:if test="${procedure.multipul }">
									<input type="checkbox" name="chk_paramItem"></input>
								</c:if>
								<span><c:out value="${procedureItem.name }" /></span>
							</div>
						</c:forEach>	
					</div>
				</c:forEach>
				</div>
			</div>
			<div class="span4">
				
			</div>
		</div>
		<div class="row-fluid">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>方案</th>
						<th>明细</th>
						<th>删除</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>
		
	</div>

	<div class="modal hide fade" id="config-modal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>自定义项目参数</h3>
		</div>
		<div class="modal-body">
			<iframe id="listFrame"
				src="${base}/cycle/config?cycletype=${param.cycletype}"
				frameBorder="0" border="0" width="100%" height="100%"></iframe>
		</div>
	</div>
	</div>
	<%@ include file="/includes/footer.jsp"%>
	<script type="text/javascript" src="${base}/js/jquery.js"></script>
	<script type="text/javascript" src="${base}/js/bootstrap.js"></script>

	<script type="text/javascript"
		src="${base}/js/bootstrap-modalmanager.js"></script>
	<script type="text/javascript" src="${base}/js/bootstrap-modal.js"></script>

	<script type="text/javascript"
		src="${base}/js/highcharts/highcharts.js"></script>

	<script type="text/javascript">
   
  
	</script>
</body>
</html>