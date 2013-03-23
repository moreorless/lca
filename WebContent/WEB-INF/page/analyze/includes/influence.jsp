<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp"%>
 
<div>
<%-- 按工序统计 --%>
<c:if test="${ param.statBy  != 'generator' }">
  <div class="row-fluid">
    <div class="span2">
      <div id="generator_sel_area">
      	<div class="control-group">
		    <div class="controls">
				<!-- 
				<c:forEach items="${influnceNames }" var="influnceName" varStatus="stat">
				<label class="radio">
				  <input type="radio" name="influenceRadios" value="${influenceNameToUuid[influnceName]}"<c:if test="${stat.last}">checked</c:if>>
				   ${influnceName }
				</label>
				</c:forEach>
				 -->
				 <div class="controls">
					<c:forEach items="${cycleList}" var="cycle"  varStatus="stat">
						<label class="radio">
						<input type="radio" name="generatorRadios" value="${cycle.code}" <c:if test="${stat.first}">checked</c:if>>
							${cycle.name}<c:if test="${fn:length(cycle.unit) > 0}">(${cycle.unit})</c:if>
						</label>
					</c:forEach>
			    </div>
				
		    </div>
		  </div>
      </div>
   <!-- div style="float:left;color:#005580"><b>环境影响潜值单位:kg-eq</b></div-->
	</div>
    <div class="span10">
      <div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
    </div>
  </div>
</c:if>

  <%--按发电方式统计 --%>
<c:if test="${param.statBy == 'generator' }">
	<div class="row-fluid">
	<div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
	</div>
</c:if>
</div>

<div class="seperator"></div>
		
<table id='table_stat' class="table table-bordered table-condensed">
	<thead>
		<tr bgcolor="#C1CDC1">
			<td align="center" style="text-align:center;vertical-align:middle"></td>
			<c:forEach items="${procNameToUuid}" var="proc">
			<td style="text-align:center;vertical-align:middle"><b>${proc.key}</b></td>
			</c:forEach>
			<td>总计</td>
		</tr>
	</thead>
	<tbody >
		<c:forEach items="${cycleList}" var="cycle">
		<tr>
			<td style="text-align:center; min-width:50px">${cycle.name}<c:if test="${fn:length(cycle.unit) > 0}">(${cycle.unit})</c:if></td>
			<c:forEach items="${procNameToUuid}" var="proc">
			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.procInfluenceMap[proc.key]}" pattern="#.###" minFractionDigits="3"  /></td>
			</c:forEach>

			<td><fmt:formatNumber value="${cycle.totalInfluence}" pattern="#.###" minFractionDigits="3"  /></td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>