<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp"%>
 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span2">
      <div id="emission_sel_area">
      	<div class="control-group">
		    <div class="controls">
				<c:forEach items="${influnceNames }" var="influnceName" varStatus="stat">
				<label class="radio">
				  <input type="radio" name="influenceRadios" value="${influenceNameToUuid[influnceName]}"<c:if test="${stat.last}">checked</c:if>>
				   ${influnceName }
				</label>
				</c:forEach>
				<!-- 
				<label class="radio">
				  <input type="radio" name="influenceRadios" value="全球变暖">全球变暖
				</label>
				<label class="radio">
				  <input type="radio" name="influenceRadios" value="酸化">酸化
				</label>
				<label class="radio">
				  <input type="radio" name="influenceRadios" value="富营养化">富营养化
				</label>
				<label class="radio">
				  <input type="radio" name="influenceRadios" value="粉尘">粉尘
				</label>
				<label class="radio">
				  <input type="radio" name="influenceRadios" value="光化学臭氧">光化学臭氧
				</label>
				<label class="radio">
				  <input type="radio" name="influenceRadios" value="总计" checked="checked"><b>总计</b>				  
				</label>
				
				-->
		    </div>
		  </div>
      </div>
   <div style="float:left;color:#005580"><b>环境影响潜值单位:kg-eq</b></div>
	</div>
    <div class="span10">
      <div id="amcharts" style="text-align:center">You need to upgrade your Flash Player</div>
    </div>
  </div>
</div>

<div class="seperator"></div>
		
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