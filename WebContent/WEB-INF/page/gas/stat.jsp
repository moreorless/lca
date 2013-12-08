<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/includes/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="Shortcut Icon" href="${base }/favicon.ico" />
<title>中海油碳里程分析系统</title>
<link type="text/css" rel="stylesheet" href="${base}/css/bootstrap.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/index.css" />
<link type="text/css" rel="stylesheet" href="${base}/css/stat.css" />
<style type="text/css">
body{overflow:hidden;}
.wrap{width:100%;height:100%;overflow:hidden;}
</style>
</head>
<body>
<div class="wrap">

<div id="charts-containter" style="height:350px;margin:0 auto"></div>
</div>

<script type="text/javascript" src="${base}/js/jquery.js"></script>
<script type="text/javascript" src="${base}/js/highcharts/highcharts.js"></script>

	<script type="text/javascript">
		var ChartHandler = {
			chartOptions : null,
			init : function() {
				var chartTitle = '';
				var yAxisTitle = '';
				<c:choose>
				<c:when test="${param.target == 'consumption'}">
				chartTitle = '综合能耗统计(kgce/kwh)';
				</c:when>
				<c:when test="${param.target == 'emission'}">
				chartTitle = '碳排放统计(g/kwh)';
				</c:when>
				<c:when test="${param.target == 'influence'}">
				chartTitle = '影响潜能统计';
				</c:when>
				</c:choose>

				this.chartOptions = {
					chart : {
						type : 'column'
					},
					title : {
						text : chartTitle,
						align : 'left',
						x : 50,
						style : {
							color : '#3E576F',
							fontSize : '14px',
							fontWeight : 'bold'
						}
					},
					xAxis : {
						categories : []
					},
					yAxis : {
						min : 0,
						title : {
							text : yAxisTitle
						},
						stackLabels : {
							enabled : false,
							style : {
								fontWeight : 'bold',
								color : (Highcharts.theme && Highcharts.theme.textColor)
										|| 'gray'
							}
						}
					},
					legend : {
						align : 'right',
						x : -10,
						verticalAlign : 'top',
						y : 20,
						floating : true,
						backgroundColor : (Highcharts.theme && Highcharts.theme.legendBackgroundColorSolid)
								|| 'white',
						borderColor : '#CCC',
						borderWidth : 1,
						shadow : false,
						credits : {
							enabled : false
						}
					},
					tooltip : {
						formatter : function() {
							return '<b>' + this.x + '</b><br/>'
									+ this.series.name + ': ' + this.y
									+ '<br/>' + 'Total: '
									+ this.point.stackTotal;
						}
					},
					plotOptions : {
						column : {
							stacking : 'normal',
							dataLabels : {
								enabled : false,
								color : (Highcharts.theme && Highcharts.theme.dataLabelsColor)
										|| 'white'
							}
						}
					},
					series : []
				};
			},
			getConsumptionDataFile : function() {
				var data_file = "${base}/highcharts/consumption"
						+ "?cycletype=${param.cycletype}&statBy=${param.statBy}";
				return data_file;
			},

			reloadChart : function(dataPath) {
				$.ajax({
							url : dataPath,
							dataType : 'json',
							success : function(chartObj) {
								ChartHandler.chartOptions.xAxis.categories = chartObj.xAxis;
								ChartHandler.chartOptions.series = chartObj.series;
								$("#charts-containter").highcharts(
										ChartHandler.chartOptions);
							},
							error : function() {
								console.log("统计图错误");
							}
						});
			},
			/**
			 * 重新加载综合能耗统计图
			 */
			reloadConsumptionChart : function() {
				var dataPath = this.getConsumptionDataFile();
				this.reloadChart(dataPath);
			},
			
			getEmissionDataFile : function() {
				var data_file = "${base}/highcharts/emission"
						+ "?cycletype=${param.cycletype}&statBy=${param.statBy}";
				return data_file;
			},
			/**
			 * 重新加载排放统计图
			 */
			reloadEmissionChart : function() {
				var dataPath = this.getEmissionDataFile();
				this.reloadChart(dataPath);
			}

		};
		
		$(document).ready(function(){
			ChartHandler.init();
			<c:choose>
			<c:when test="${param.target == 'consumption'}">
			ChartHandler.reloadConsumptionChart();
			</c:when>
			<c:when test="${param.target == 'emission'}">
			ChartHandler.reloadEmissionChart();
			</c:when>
			</c:choose>
		});
	</script>
</body>
</html>