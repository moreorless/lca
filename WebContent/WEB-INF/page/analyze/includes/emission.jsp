<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp"%>

<div class="container-fluid">
  <div class="row-fluid">
    <div class="span2">
      <div id="emission_sel_area">
      	<div class="control-group">
		    <div class="controls">
				<label class="radio">
				<input type="radio" name="optionsRadios" id="optionsRadios1" value="option1">CO2
				</label>
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">CH4
				</label>
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2" checked><b>总计</b>
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
		<td width="100" align="center">排放</td>
			<c:forEach items="${cycleList}" var="cycle">
			<td>${cycle.totalConsumption}</td>
		</c:forEach>
		</tr>
	</tbody>
</table>