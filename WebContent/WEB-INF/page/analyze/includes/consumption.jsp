<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp"%>
 
<div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
<div class="seperator"></div>
		
<table class="table table-striped table-bordered table-condensed">
	<thead>
		<tr bgcolor="#C1CDC1">
			<td align="center" style="text-align:center;vertical-align:middle"></td>
			<c:forEach items="${cycleList}" var="cycle">
			<td style="text-align:center;vertical-align:middle"><b>${cycle.name}${cycle.unit}</b></td>
			</c:forEach>
		</tr>
	</thead>
	<tbody>
		<tr>
		<td align="center" width="75px" style="text-align:center;vertical-align:middle"><b>综合能耗</b></td>
			<c:forEach items="${cycleList}" var="cycle">
			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.totalConsumption}" pattern="#.###" minFractionDigits="3"  /></td>
		</c:forEach>
		</tr>
	</tbody>
</table>