<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp"%>
 
<div class="container-fluid">
  <div class="row-fluid">
    <div class="span2">
      <div id="emission_sel_area">
      	<div class="control-group">
		    <div class="controls">
		    	<!-- 
				<c:forEach items="${influnceNames }" var="influnceName" >
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
				   ${influnceName }
				</label>
				</c:forEach>
				 -->
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">全球变暖
				</label>
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">酸化
				</label>
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">富营养化
				</label>
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">粉尘
				</label>
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">光化学臭氧
				</label>
				<label class="radio">
				  <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2" checked="checked"><b>总计</b>				  
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
		<c:forEach items="${influnceNames }" var="influnceName">
		
		<tr>
		<td width="100" align="center">${influnceName}</td>
			<c:forEach items="${cycleList}" var="cycle">
			<td>${cycle.influenceMap[influnceName]}</td>
		</c:forEach>
		</tr>
		
		</c:forEach>
		
	</tbody>
</table>