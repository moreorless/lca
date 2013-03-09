<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp"%>

<div class="container-fluid">
  <div class="row-fluid">
    <div class="span2">
      <div id="emission_sel_area">
      	<div class="control-group">
		    <div class="controls">
				<label class="radio">
				<input type="radio" name="emissionRadios" value="CO2">CO2
				</label>
				<label class="radio">
				  <input type="radio" name="emissionRadios" value="CH4">CH4
				</label>
				<label class="radio">
				  <input type="radio" name="emissionRadios" value="total" checked><b>总计</b>
				</label>
		    </div>
		  </div>
      
      </div>
    </div>
    <div class="span10">
      <div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
    </div>
  </div>
</div>

<div class="seperator"></div>
		
<table id='table_emission' class="table table-bordered table-condensed">
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
			<td align="center" width="75px" style="text-align:center;vertical-align:middle"><b>CO2</b></td>
			
			<c:forEach items="${cycleList}" var="cycle">
			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.mergedEmissionMap['CO2']}" pattern="#.##" minFractionDigits="2"  /></td>
			</c:forEach>
			
		</tr>
		
		<tr>
			<td align="center" width="75px" style="text-align:center;vertical-align:middle"><b>CH4</b></td>
			
			<c:forEach items="${cycleList}" var="cycle">
			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.mergedEmissionMap['CH4']}" pattern="#.##" minFractionDigits="2"  /></td>
			</c:forEach>
			
		</tr>
		
		<tr>
			<td align="center" width="75px" style="text-align:center;vertical-align:middle"><b>总计</b></td>
			
			<c:forEach items="${cycleList}" var="cycle">
			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.totalEmission}" pattern="#.##" minFractionDigits="2"  /></td>
			</c:forEach>
			
		</tr>
	</tbody>
</table>