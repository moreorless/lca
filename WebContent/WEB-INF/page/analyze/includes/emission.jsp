<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp"%>

<c:if test="${param.emissionType==null || param.emissionType==''}">
	<c:set var="curEmissionType" value="total"></c:set>
</c:if>
<c:if test="${param.emissionType!=null && param.emissionType!=''}">
	<c:set var="curEmissionType" value="${param.emissionType}"></c:set>
</c:if>

<c:if test="${param.chartType==null}">
	<c:set var="chartType" value="clustered"></c:set>
</c:if>
<c:if test="${param.chartType!=null}">
	<c:set var="chartType" value="${param.chartType}"></c:set>
</c:if>


<c:if test="${fn:length(param.generatorCode) == 0 || param.generatorCode == 'undifined'}">
	<c:set var="curGeneratorCode" value="${cycleList[0].code}"></c:set>
</c:if>
<c:if test="${fn:length(param.generatorCode) > 0}">
	<c:set var="curGeneratorCode" value="${param.generatorCode}"></c:set>
</c:if>

<div>

<%-- 按工序统计 --%>
<c:if test="${ param.statBy  != 'generator' }">
  <div class="row-fluid">
    <div class="span2">
    	<select id="chartType_sel" style="width:100%">
			<option value="stackcolumn" <c:if test="${chartType == 'stackcolumn'}">selected</c:if>>纵向堆积</option>
			<option value="clustered" <c:if test="${chartType == 'clustered'}">selected</c:if>>横向堆积</option>
		</select>
	
      <div id="generator_sel_area">
      	<div class="control-group">
		    <div class="controls">
				<c:forEach items="${cycleList}" var="cycle"  varStatus="stat">
					<label class="radio">
					<input type="radio" name="generatorRadios" value="${cycle.code}" <c:if test="${curGeneratorCode == cycle.code}">checked</c:if>>
						${cycle.name}<c:if test="${fn:length(cycle.unit) > 0}">(${cycle.unit})</c:if>
					</label>
				</c:forEach>
		    </div>
		  </div>
      </div>
    </div>
    <div class="span10">
      <div style="height:40px"></div>
      <div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
    </div>
  </div>
</c:if>

<%--按发电方式统计 --%>
<c:if test="${param.statBy == 'generator' }">
	<div class="row-fluid">
	<div class="span2">
		<%--
		<select id="emission_sel" style="width:100%">
			<option value="CO2" <c:if test="${curEmissionType == 'CO2'}">selected</c:if>>CO2</option>
			<option value="CH4" <c:if test="${curEmissionType == 'CH4'}">selected</c:if>>CH4</option>
			<option value="N2O" <c:if test="${curEmissionType == 'N2O'}">selected</c:if>>N2O</option>
			<option value="total" <c:if test="${curEmissionType == 'total'}">selected</c:if>>总计</option>
		</select>
		 --%>
		 <label class="radio">
			  <input type="radio" name="emission_radios" value="CO2"<c:if test="${curEmissionType == 'CO2'}">checked</c:if>></input>CO2
		 </label>
		 <label class="radio">
			   <input type="radio" name="emission_radios" value="CH4"<c:if test="${curEmissionType == 'CH4'}">checked</c:if>></input>CH4
		</label>
		<label class="radio">	   
			   <input type="radio" name="emission_radios" value="N2O"<c:if test="${curEmissionType == 'N2O'}">checked</c:if>></input>N2O
		</label>
		<label class="radio">
			   <input type="radio" name="emission_radios" value="total"<c:if test="${curEmissionType == 'total'}">checked</c:if>></input>总计
		</label>
		 
    </div>
    <div class="span10">
    <c:if test="${ param.statBy  != 'generator' }">
      <div style="height:40px"></div>
      </c:if>
      <div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
    </div>
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
			<td style="text-align:center;vertical-align:middle"><b>总计</b></td>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${cycleList}" var="cycle">
		<tr>
			<td style="text-align:center; min-width:50px"><b>${cycle.name}<c:if test="${fn:length(cycle.unit) > 0}">(${cycle.unit})</c:if></b></td>
			<c:forEach items="${procNameToUuid}" var="proc">
			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.emissionMap[curEmissionType][proc.key]}" pattern="#.##E0" minFractionDigits="2"  /></td>
			</c:forEach>

			<td style="text-align:center; min-width:50px"><fmt:formatNumber value="${cycle.totalEmission}" pattern="#.##E0" minFractionDigits="2" /></td>
		</tr>
		</c:forEach>
	</tbody>
</table>