<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp"%>
 
<div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
<div class="seperator"></div>
		
<table class="table table-striped table-bordered table-condensed">
	<thead>
		<tr>
			<td width="100" align="center"></td>
			<c:forEach items="${cycleList}" var="cycle">
			<td>${cycle.name}</td>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<tr>
		<td width="100" align="center">综合能耗</td>
			<c:forEach items="${cycleList}" var="cycle">
			<td>${cycle.totalConsumption}</td>
		</c:forEach>
		</tr>
	</tbody>
</table> 	