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
      <%--
      <div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
      --%>
      <div id="charts-containter" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
    </div>
  </div>
</c:if>

  <%--按发电方式统计 --%>
<c:if test="${param.statBy == 'generator' }">
	<div class="row-fluid">
	<div class="span2">
		<c:forEach items="${influnceNames }" var="influnceName" varStatus="stat">
			<label class="radio">
			  <input type="radio" name="influenceRadios" value="${influenceNameToUuid[influnceName]}"<c:if test="${stat.last}">checked</c:if> />
			   ${influnceName }
			</label>
		</c:forEach>
	
	</div>
	<div class="span10">
	<div id="charts-containter" style="min-width: 400px; height: 400px; margin: 0 auto"></div>
	</div>
	</div>
</c:if>
</div>

<div class="seperator"></div>


<%-- 按工序统计 --%>
<c:if test="${ param.statBy  != 'generator' }">
<table id='table_stat' class="table table-bordered table-condensed">
	<thead>
		<tr bgcolor="#C1CDC1">
			<td align="center" style="text-align:center;vertical-align:middle"></td>
			<c:forEach items="${procNameToUuid}" var="proc">
			<td style="text-align:center;vertical-align:middle"><b>${proc.key}</b></td>
			</c:forEach>
			<td style="text-align:center;vertical-align:middle"><b>总计</b></td>
		</tr>
	</thead>
	<tbody >
		<c:forEach items="${cycleList}" var="cycle">
		<tr>
			<td style="text-align:center; min-width:50px">${cycle.name}<c:if test="${fn:length(cycle.unit) > 0}">(${cycle.unit})</c:if></td>
			<c:forEach items="${procNameToUuid}" var="proc">
			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.procInfluenceMap[proc.key]}" pattern="#.##E0" minFractionDigits="2" /></td>
			</c:forEach>

			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.totalInfluence}" pattern="#.##E0" minFractionDigits="2" /></td>
		</tr>
		</c:forEach>
		
	</tbody>
</table>
</c:if>

  <%--按发电方式统计 --%>
<c:if test="${param.statBy == 'generator' }">
<table id='table_influence' class="table table-bordered table-condensed">
	<thead>
		<tr bgcolor="#C1CDC1">
			<td align="center" style="text-align:center;vertical-align:middle"></td>
			<c:forEach items="${cycleList}" var="cycle">
			<td style="text-align:center;vertical-align:middle"><b>${cycle.name}${cycle.unit}</b></td>
			</c:forEach>
		</tr>
	</thead>
	<tbody >
		<c:forEach items="${influnceNames }" var="influnceName">

		<tr >
		<td align="center" width="75px" style="text-align:center;vertical-align:middle"><b>${influnceName}</b></td>
			<c:forEach items="${cycleList}" var="cycle">
			<td style="text-align:center; min-width:50px">
				<c:if test="${cycle.influenceMap[influnceName] == 0 }">
					0.00
				</c:if>
				<c:if test="${cycle.influenceMap[influnceName] != 0 }">
				<fmt:formatNumber value="${cycle.influenceMap[influnceName] }" pattern="#.##E0" minFractionDigits="2"  />
				</c:if>
			</td>
		</c:forEach>
		</tr>

		</c:forEach>

	</tbody>
</table>

</c:if>


