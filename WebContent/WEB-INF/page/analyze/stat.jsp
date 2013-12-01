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
.seperator {
	height: 10px;
}
</style>
</head>
<body>
	<c:import url="/includes/header.jsp">
		<c:param name="currentNav">statistic</c:param>
	</c:import>

	<div id="wrap" class="container">
		<div>
			<ul class="nav nav-pills" style="margin: 10px 0 10px 0">
				<li
					<c:if test="${param.target == 'consumption' }">class="active"</c:if>>
					<a
					href="${base}/cycle/stat?cycletype=${param.cycletype}&target=consumption&statBy=${param.statBy}">综合能耗统计</a>
				</li>
				<li
					<c:if test="${param.target == 'emission' }">class="active"</c:if>>
					<a
					href="${base}/cycle/stat?cycletype=${param.cycletype}&target=emission&statBy=${param.statBy}">碳排放统计</a>
				</li>

				<c:if test="${param.cycletype=='electric'}">
					<li
						<c:if test="${param.target == 'influence' }">class="active"</c:if>>
						<a
						href="${base}/cycle/stat?cycletype=${param.cycletype}&target=influence&statBy=${param.statBy}">影响潜能统计</a>
					</li>
				</c:if>

				<!-- 
		  <li style="float:right;color:#005580"><b>${curCycleType.name}</b></li>
		   -->
				<li style="float: right">
					<ul class="breadcrumb" style="margin: 0">
						<c:if
							test="${param.cycletype == 'electric' || param.cycletype == 'transport'}">
							<li><a
								href="${base}/cycle/stat?cycletype=${param.cycletype}&target=${param.target}&emissionType=${param.emissionType}&generatorCode=${param.generatorCode }&statBy=procedure">
									按阶段统计 </a> <span class="divider">/</span></li>
						</c:if>
						<li><a
							href="${base}/cycle/stat?cycletype=${param.cycletype}&target=${param.target}&emissionType=${param.emissionType}&generatorCode=${param.generatorCode }&statBy=generator">
								<c:if test="${param.cycletype == 'electric'}">
			  	按发电方式统计
			  	</c:if> <c:if test="${param.cycletype == 'transport'}">
			  	按交通燃料统计
			  	</c:if>
						</a></li>
					</ul>
				</li>
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

	<div class="modal hide fade" id="config-modal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>自定义项目参数</h3>
		</div>
		<div class="modal-body">
			<iframe id="listFrame"
				src="${base}/cycle/config?cycletype=${param.cycletype}"
				frameBorder="0" border="0" width="100%" height="100%"></iframe>
		</div>
	</div>

	<div id="upload-modal" class="modal hide fade" tabindex="-1"
		role="dialog" aria-labelledby="uploadModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="uploadModalLabel">上传源数据</h3>
		</div>

		<IFRAME name="fm1" id="fm1" src="#" style="display: none;"> </IFRAME>
		<IFRAME name="fm2" id="fm2" src="#" style="display: none;"> </IFRAME>
		<IFRAME name="fm3" id="fm3" src="#" style="display: none;"> </IFRAME>

		<div class="modal-body">

			<div class="control-group">
				<form target="fm1" id="formUploadElec" name="form1"
					action="${base}/cycle/uploadDataFiles?cycletype=electric" enctype="multipart/form-data"
					method="post">
					<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发电周期源数据</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="file" name="FileElec" id="FileElec" size="30"
						UNSELECTABLE="on" />
					<input type="submit" class="btn btn-small btn-info" name="upElecSubmit"
						value="上传" onclick="alert('上传成功！')"/>
				</form>

				<form target="fm2" id="formUploadTransport" name="form2"
					action="${base}/cycle/uploadDataFiles?cycletype=transport" enctype="multipart/form-data"
					method="post">
					<b>交通燃料周期源数据</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="file" name="FileTransport" id="FileTransport" size="30"
						UNSELECTABLE="on" " />
					<input type="submit" class="btn btn-small btn-info" name="upTransportSubmit"
						value="上传" onclick="alert('上传成功！')"/>
				</form>
				<form target="fm3" id="formUploadGas" name="form3"
					action="${base}/cycle/uploadDataFiles?cycletype=gas" enctype="multipart/form-data"
					method="post">
					<b>天然气产业链源数据</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="file" name="FileGas" id="FileGas" size="30"
						UNSELECTABLE="on"/>
					<input type="submit" class="btn btn-small btn-info" name="upGasSubmit"
						value="上传" onclick="alert('上传成功！')"/>
				</form>
			</div>
		</div>
	</div>


	<%@ include file="/includes/footer.jsp"%>
	<script type="text/javascript" src="${base}/js/jquery.js"></script>
	<script type="text/javascript" src="${base}/js/bootstrap.js"></script>

	<script type="text/javascript"
		src="${base}/js/bootstrap-modalmanager.js"></script>
	<script type="text/javascript" src="${base}/js/bootstrap-modal.js"></script>

	<script type="text/javascript"
		src="${base}/js/highcharts/highcharts.js"></script>

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
				var generatorCode = $(
						"input:radio[name='generatorRadios']:checked").val();
				if (generatorCode) { // 页面没有发电方式选择框时，该值为undefined
					data_file += ("&generatorCode=" + generatorCode);
				}
				return data_file;
			},

			reloadChart : function(dataPath) {
				$
						.ajax({
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
			getInfluenceDataFile : function() {
				var data_file = "${base}/highcharts/influence"
						+ "?cycletype=${param.cycletype}&statBy=${param.statBy}";
				var generatorCode = $(
						"input:radio[name='generatorRadios']:checked").val();
				if (generatorCode) { // 页面没有发电方式选择框时，该值为undefined
					data_file += ("&generatorCode=" + generatorCode);
				}

				var infItem = $("input:radio[name='influenceRadios']:checked")
						.val();
				if (infItem) {
					data_file += ("&infItem=" + infItem);
				}

				return data_file;
			},
			/**
			 * 重新加载影响潜能统计图
			 */
			reloadInfluenceChart : function() {
				var dataPath = this.getInfluenceDataFile();

				this.reloadChart(dataPath);
			},
			getEmissionDataFile : function() {
				var data_file = "${base}/highcharts/emission"
						+ "?cycletype=${param.cycletype}&statBy=${param.statBy}";

				var generatorCode = $(
						"input:radio[name='generatorRadios']:checked").val();
				if (generatorCode) { // 页面没有发电方式选择框时，该值为undefined
					data_file += ("&generatorCode=" + generatorCode);
				}

				if ($('#emission_sel')) {
					var emissionType = $("#emission_sel").val();
					if (emissionType) {
						data_file += ("&emissionType=" + emissionType);
					}
				}

				if ($('#emission_radios')) {
					var emissionType = $(
							"input:radio[name='emission_radios']:checked")
							.val();
					if (emissionType) {
						data_file += ("&emissionType=" + emissionType);
					}
				}

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

		var ParamDialog = {
			close : function() {
				$('#config-modal').modal('hide');
				$('#upload-modal').modal('hide');
			}
		}

		$(document)
				.ready(
						function() {

							$('#header-btn-param-config').click(function() {
								$('#config-modal').modal({
									width : '55%',
									height : ($(window).height() - 40) + 'px',
									modalOverflow : true,
									top : '20px'
								});
								//{width:'65%', height:($(window).height() - 40) + 'px', modalOverflow:true, top:'20px', backdrop:true});
							});
		
							$('#header-btn-upload').click(function() {
								$('#upload-modal').modal({
									width : '45%',
									height : ($(window).height() - 500) + 'px',
									modalOverflow : true,
									top : '600px'
								});
								//{width:'65%', height:($(window).height() - 40) + 'px', modalOverflow:true, top:'20px', backdrop:true});
							});

							ChartHandler.init();

							/**
							 * 根据选择的radio，联动高亮表格的对应行
							 */
							function highlightTR(radioGroupName, tableId) {
								var radios = document
										.getElementsByName(radioGroupName);
								if (radios.length < 1)
									return;
								var tb = document.getElementById(tableId);
								if (!tb)
									return;
								for ( var i = 1; i < tb.rows.length; i++)
									tb.rows[i].bgColor = "white";

								for ( var i = 0; i < radios.length; i++) {
									if (radios[i].checked) {
										tb.rows[i + 1].bgColor = '#1AE6E6';
										break;
									}
								}
							}

							function loadChart() {
								<c:choose>
								<c:when test="${param.target == 'consumption'}">
								ChartHandler.reloadConsumptionChart();
								</c:when>
								<c:when test="${param.target == 'emission'}">
								ChartHandler.reloadEmissionChart();
								</c:when>
								<c:when test="${param.target == 'influence'}">
								ChartHandler.reloadInfluenceChart();
								</c:when>
								</c:choose>
							}

							$("input:radio[name='generatorRadios']").click(
									function() {
										loadChart();
										highlightTR('generatorRadios',
												'table_stat');
									});

							loadChart();
							highlightTR('generatorRadios', 'table_stat');

							// 排放类型切换的事件处理
							$(
									'#emission_sel, #chartType_sel, input:radio[name="emission_radios"]')
									.change(
											function() {
												var wLocation = "${base}/cycle/stat?cycletype=${param.cycletype}&target=${param.target}&statBy=${param.statBy}";

												var emissionType = $(
														'#emission_sel').val();
												if (emissionType) {
													wLocation += ("&emissionType=" + emissionType);
												}
												if ($('#emission_radios')) {
													var emissionType = $(
															"input:radio[name='emission_radios']:checked")
															.val();
													if (emissionType) {
														wLocation += ("&emissionType=" + emissionType);
													}
												}

												var generatorCode = $(
														"input:radio[name='generatorRadios']:checked")
														.val();
												if (generatorCode) { // 页面没有发电方式选择框时，该值为undefined
													wLocation += ("&generatorCode=" + generatorCode);
												}

												var chartType = $(
														"#chartType_sel").val();
												if (chartType) {
													wLocation += ("&chartType=" + chartType);
												}

												window.location = wLocation;
											});

							// 影响潜能切换的事件处理
							$("input:radio[name='influenceRadios']")
									.click(
											function() {
												ChartHandler
														.reloadInfluenceChart();

												var radios = document
														.getElementsByName('influenceRadios');
												var tb = document
														.getElementById('table_influence');
												for ( var i = 1; i < tb.rows.length; i++)
													tb.rows[i].bgColor = "white";

												for ( var i = 0; i < radios.length; i++) {
													if (radios[i].checked)
														tb.rows[i + 1].bgColor = '#1AE6E6';
												}
											});

						});
	</script>
</body>
</html>