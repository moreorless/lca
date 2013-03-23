<%@ page contentType="text/html;charset=utf-8" language="java" %>
<%@ include file="/includes/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  	<link rel = "Shortcut Icon" href="${base }/favicon.ico" />
    <title>中海油碳理层分析系统</title>
    <link type="text/css" rel="stylesheet" href="${base}/css/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="${base}/css/index.css"/>
    <link type="text/css" rel="stylesheet" href="${base}/css/stat.css"/>
	<style type="text/css">
		.seperator{height:10px;}
	</style>
  </head>
  <body>
  	<c:import url="/includes/header.jsp">
  		<c:param name="currentNav">statistic</c:param>
  	</c:import>
  	
  	<div id="wrap" class="container">
  		<div>
  		<ul class="nav nav-pills" style="margin:10px 0 10px 0">
		  <li <c:if test="${param.target == 'consumption' }">class="active"</c:if>>
		    <a href="${base}/cycle/stat?cycletype=${param.cycletype}&target=consumption">综合能耗统计</a>
		  </li>
		  <li <c:if test="${param.target == 'emission' }">class="active"</c:if>>
		  	<a href="${base}/cycle/stat?cycletype=${param.cycletype}&target=emission">碳排放统计</a>
		  </li>
		  <li <c:if test="${param.target == 'influence' }">class="active"</c:if>>
		  	<a href="${base}/cycle/stat?cycletype=${param.cycletype}&target=influence">影响潜能统计</a>
		  </li>
		  
		  <li style="float:right;color:#005580"><b>${curCycleType.name}</b></li>
		</ul>
		</div>
  		<c:choose>
   			<c:when test="${param.target == 'consumption'}">
		  		<c:import url="/WEB-INF/page/analyze/includes/consumption.jsp">
		  		</c:import>
   			</c:when>
   			<c:when test="${param.target == 'emission'}">
				<c:import url="/WEB-INF/page/analyze/includes/emission.jsp">
		  		</c:import>
   			</c:when>
   			<c:when test="${param.target == 'influence'}">
   				<c:import url="/WEB-INF/page/analyze/includes/influence.jsp">
		  		</c:import>
   			</c:when>
   		</c:choose>
  	
  	</div>
  	
  <%@ include file="/includes/footer.jsp" %>
   <script type="text/javascript" src="${base}/js/jquery.js"></script>
   <script type="text/javascript" src="${base}/js/bootstrap.js"></script>
   <script type="text/javascript" src="${base}/amchart/swfobject.js"></script>
   
   <script type="text/javascript">
   
   var ChartHandler = {
		swfPath : "${base}/amchart/amcolumn_1.6.0.1/amcolumn/amcolumn.swf",
		setting_file : "${base}/common/amchart/stat/amcolumn_settings.xml",
		data_file : "${base}/common/amchart/stat/amcolumn_data.xml",
		so : null,
		init : function(){
			
			this.so = new SWFObject(this.swfPath, "amcolumn", "100%", "340", "8", "#FFFFFF");
			this.so.addVariable("path", "${base}/amchart/");
			
			<c:choose>
	   			<c:when test="${param.target == 'consumption'}">
	   			setting_file = "${base}/common/amchart/stat/consumption_settings.xml";
	   			data_file = "${base}/chart/consumption"  + "?cycletype=${param.cycletype}";
	   			
	   			var generatorCode = $("input:radio[name='consumptionRadios']:checked").val();
	   			data_file += ("&generatorCode=" + generatorCode);
	   			</c:when>
	   			<c:when test="${param.target == 'emission'}">
	   			setting_file = "${base}/common/amchart/stat/emission_settings.xml";
	   			data_file = "${base}/chart/emission"  + "?cycletype=${param.cycletype}";
	   			
				var statItem = $("input[type='radio'][name='emissionRadios']:checked").val();
	   			data_file += ("&statItem=" + statItem);
	   			</c:when>
	   			<c:when test="${param.target == 'influence'}">
	   			setting_file = "${base}/common/amchart/stat/influence_settings.xml";
	   			data_file = "${base}/chart/influence"  + "?cycletype=${param.cycletype}";
	   			
	   			var statItem = $("input[type='radio'][name='influenceRadios']:checked").val();
	   			data_file += ("&statItem=" + statItem);
	   			</c:when>
	   		</c:choose>
			
			this.so.addVariable("settings_file", encodeURIComponent(setting_file));
			this.so.addVariable("data_file", encodeURIComponent(data_file));
			
			this.so.addParam("wmode", "opaque");
			this.so.write("amcharts");
		},
		
		/**
		* 重新加载综合能耗统计图
		*/
		reloadConsumptionChart : function(){
			data_file = "${base}/chart/consumption"  + "?cycletype=${param.cycletype}";
			
			var generatorCode = $("input:radio[name='consumptionRadios']:checked").val();
   			data_file += ("&generatorCode=" + generatorCode);
   			this.so.addVariable("data_file", encodeURIComponent(data_file));
   			this.so.write("amcharts");
		},
		/**
		* 重新加载影响潜能统计图
		*/
		reloadInfluenceChart : function(){
   			data_file = "${base}/chart/influence"  + "?cycletype=${param.cycletype}";
   			
   			var statItem = $("input:radio[name='influenceRadios']:checked").val();
   			data_file += ("&statItem=" + statItem);
   			this.so.addVariable("data_file", encodeURIComponent(data_file));
   			
   			//document.getElementById('amcolumn').reloadData();
   			//document.getElementById('amcolumn').reloadAll();		// 只刷新数据未生效
   			this.so.write("amcharts");
		},
		/**
		* 重新加载排放统计图
		*/
		reloadEmissionChart : function(){
   			data_file = "${base}/chart/emission"  + "?cycletype=${param.cycletype}";
   			
   			var statItem = $("input:radio[name='emissionRadios']:checked").val();
   			data_file += ("&statItem=" + statItem);
   			this.so.addVariable("data_file", encodeURIComponent(data_file));
   			
   			//document.getElementById('amcolumn').reloadData();
   			//document.getElementById('amcolumn').reloadAll();		// 只刷新数据未生效
   			this.so.write("amcharts");
		}
		
   };
   
   $(document).ready(function(){
	   ChartHandler.init();
	   
	   /**
	   * 根据选择的radio，联动高亮表格的对应行
	   */
	   function highlightTR(radioGroupName, tableId){
		   var radios = document.getElementsByName(radioGroupName);
		   if(radios.length < 1) return;
		   var tb = document.getElementById(tableId);
		   if(!tb) return;
		   for( var i = 1; i < tb.rows.length; i++ ) 
			   tb.rows[i].bgColor = "white";
			
		   for( var i = 0; i < radios.length; i++ ) 
		   { 
			   if(radios[i].checked){
				   tb.rows[i+1].bgColor = '#1AE6E6';
				   break;
			   } 
		   } 
	   }
	   
	   $("input:radio[name='consumptionRadios']").click(function(){
		   ChartHandler.reloadConsumptionChart();
		   highlightTR('consumptionRadios', 'table_consumption');
	   });
	   highlightTR('consumptionRadios', 'table_consumption');
	   
	   $("input:radio[name='influenceRadios']").click(function(){
		   ChartHandler.reloadInfluenceChart();
		   highlightTR('influenceRadios', 'table_influence');
	   });
	   highlightTR('influenceRadios', 'table_influence');
	   
	   $("input:radio[name='emissionRadios']").click(function(){
		   ChartHandler.reloadEmissionChart();
		   highlightTR('emissionRadios', 'table_emission');
	   });
	   highlightTR('emissionRadios', 'table_emission');
   });
   
	</script>
  </body>
</html>