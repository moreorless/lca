<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp"%>
 
<div>
  <div class="row-fluid">
    <div class="span2">
      <div>
      	<div class="control-group">
		    <div class="controls">
				
				<c:forEach items="${cycleList}" var="cycle"  varStatus="stat">
					<label class="radio">
					<input type="radio" name="consumptionRadios" value="${cycle.code}" <c:if test="${stat.first}">checked</c:if>>
						${cycle.name}<c:if test="${fn:length(cycle.unit) > 0}">(${cycle.unit})</c:if>
					</label>
				</c:forEach>
		    </div>
		  </div>
      	
      	<!-- 
	      <div class="control-group">
			    <div class="controls">
					
					<c:forEach items="${procNameToUuid}" var="proc">
						<label class="radio">
						<input type="radio" name="classifyRadios" value="${proc.value}">
							${proc.key}
						</label>
					</c:forEach>
			    </div>
			  </div>
		   -->
      </div>
    </div>
    <div class="span10">
      <div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
    </div>
  </div>
</div>

<div class="seperator"></div>
		
<table id='table_consumption'  class="table table-bordered table-condensed">
	<thead>
		<tr bgcolor="#C1CDC1">
			<td align="center" style="text-align:center;vertical-align:middle"></td>
			<c:forEach items="${procNameToUuid}" var="proc">
			<td style="text-align:center;vertical-align:middle"><b>${proc.key}</b></td>
			</c:forEach>
			<td>总计</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${cycleList}" var="cycle">
		<tr>
			<td style="text-align:center; min-width:50px">${cycle.name}<c:if test="${fn:length(cycle.unit) > 0}">(${cycle.unit})</c:if></td>
			<c:forEach items="${procNameToUuid}" var="proc">
			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.consumptionMap[proc.key]}" pattern="#.###" minFractionDigits="3"  /></td>
			</c:forEach>

			<td><fmt:formatNumber value="${cycle.totalConsumption}" pattern="#.###" minFractionDigits="3"  /></td>
		</tr>
		</c:forEach>
	</tbody>
</table>