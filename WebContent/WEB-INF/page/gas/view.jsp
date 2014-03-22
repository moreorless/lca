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
.modal-body{padding:0}
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
					href="${base}/cycle/gasView?cycletype=${param.cycletype}&target=consumption&statBy=${param.statBy}">综合能耗统计</a>
				</li>
				<li
					<c:if test="${param.target == 'emission' }">class="active"</c:if>>
					<a
					href="${base}/cycle/gasView?cycletype=${param.cycletype}&target=emission&statBy=${param.statBy}">碳排放统计</a>
				</li>
			</ul>
		</div>
		<div>
		
		<div class="row-fluid" style="border-bottom:2px solid #ccc">
			<div class="span8" style="border-right : 1px solid #ccc">
				
				<div class="row-fluid" style="width:540px;height:260px">
				<c:forEach items="${procedures}" var="procedure" varStatus="status">
					<div style="float:left;width:100px;height:200px;padding:2px">
						<h4 style="text-align:center;"><c:out value="${procedure.name }" /></h4>
						<ul class="vote-list">
						<c:forEach items="${procedure.items}" var="procedureItem" varStatus="itemStatus">
							<c:if test="${param.target == 'consumption' }">
								<c:set var="title" value="${procedureItem.consumptionValue }"></c:set>
							</c:if>
							<c:if test="${param.target == 'emission' }">
								<c:set var="title" value="${procedureItem.emissionValue }"></c:set>
							</c:if>
							<li title="${title}" id="vote_${status.index}_${itemStatus.index}" multi="${procedure.multiple}" procId="${status.index}" procItemId="${itemStatus.index}">
								<span>
								<c:out value="${procedureItem.name }" />
								</span>
								<i class="fa fa-check icon-ok icon-white"></i>
							</li>
						</c:forEach>	
						</ul>
						
					</div>
					<c:if test="${not status.last}">
					<div style="float:left;width:32px;height:200px;line-height:200px;vertical-align:middle;">
						<div style="height:16px"></div>
						<img src="${base}/images/br_next_16.png" width="16" height="16" style="margin-left:4px;"></img>
					</div>
					</c:if>
				</c:forEach>
				</div>
				<div class="row-fluid">
					<form class="form-inline" style='margin-bottom:10px' id="cycle_form"
						action="${base}/cycle/insertGasCycle?cycletype=${param.cycletype}&target=${param.target}" method="post">
					<div class="row-fluid" style="padding-bottom:5px">
						<div class="span2">
							<code>运输距离设置</code>&nbsp;&nbsp;&nbsp;&nbsp;
						</div>
						<div class="span3">
							<label>水运</label> 
							<input type="text" id="transby_water" value="1000" style="width:40px"/>&nbsp;km
						</div> 
						<div class="span3">
							<label>陆运</label> 
							<input type="text" id="transby_land" value="1000" style="width:40px"/>&nbsp;km
						</div>
						<div class="span4">	
							<label>管道</label> 
							<input type="text" id="transby_pipe" value="1000" style="width:40px"/>&nbsp;km
							<!-- 
							<button class="btn btn-info btn-mini" type="button" id="btn-set-dist">修改</button>
							 -->
							<!-- 
							<a href="javascript://"><img src="${base}/images/button_ok.png" title="保存设置"></img></a>
							 -->
						</div>
					</div>
					<div class="row-fluid">
					<div class="span2">
						<code>方案保存与对比</code>
					</div>
					 <div class="span10">
					<input type="text" id="scheme_name" name="name" class="input-xlarge" placeholder="在这里输入方案名称"/>
					<input type="hidden" name="procedureIndexStr" id="input-procedureIndexStr"/>
					<input type="hidden" name="transDistStr" id="input-transDistStr"/>
					<button class="btn btn-primary" type="button" id="btn-save">保存</button>
					<button class="btn btn-info" type="button" id="btn-analyze">对比</button>
					</div>
					</div>
					</form>
				</div>
			</div>
			<div class="span4" style="padding:10px;">
				<div id="chart-container"></div>
			</div>
		</div>
		<div class="row-fluid">
			<table class="table">
				<thead>
					<tr>
						<th width="200px">方案名称</th>
						<c:forEach items="${procedures}" var="procedure" varStatus="status">
							<th width="100px">${procedure.name}</th>
						</c:forEach>
						<th width="50px">删除</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${curCycleType.cycleList}" var="cycle" varStatus="rowStatus">
						<tr id="tr_${rowStatus.index}" index="${rowStatus.index}">
							<td>${cycle.name}</td>
							<c:forEach items="${cycle.procedureParamItemList}" var="paramItems" varStatus="status">
							<c:if test="${fn:length(paramItems) == 1}">
							<td>${paramItems[0].name}</td>
							</c:if>
							<c:if test="${fn:length(paramItems) > 1}">
							<td><c:forEach items="${paramItems}" var="paramItem" varStatus="status">${paramItem.name}<c:if test="${not status.last}">, </c:if></c:forEach></td>
							</c:if>
							</c:forEach>
							<td><a href="javascript://" onclick="TableHandler.delRow(${rowStatus.index})" title="删除"><i class="icon-remove"></i></a></td>
						</tr>
											
					</c:forEach>
				</tbody>
			</table>
		</div>
		
	</div>
	
	</div>
	
	<div class="modal hide fade" id="analyze-modal">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">&times;</button>
			<h3>方案对比分析</h3>
		</div>
		<div class="modal-body">
			<iframe id="listFrame"
				src="${base}/cycle/gasStat?cycletype=${param.cycletype}&target=${param.target}&statBy=generator"
				frameBorder="0" border="0" width="100%" height="100%"></iframe>
		</div>
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
	
	<%@ include file="/includes/uploadfile.jsp" %>
	
	
	<%@ include file="/includes/footer.jsp"%>
	<script type="text/javascript" src="${base}/js/jquery.js"></script>
	<script type="text/javascript" src="${base}/js/bootstrap.js"></script>

	<script type="text/javascript"
		src="${base}/js/bootstrap-modalmanager.js"></script>
	<script type="text/javascript" src="${base}/js/bootstrap-modal.js"></script>

	<script type="text/javascript"
		src="${base}/js/highcharts/highcharts.js"></script>

	<script type="text/javascript">
	var VoteHandler = {
		procMap : [],		// 记录是否选中
		procParam : [],		// 记录参数值
		procNames : [],		// 记录阶段名称
		transdist : [1000, 1000, 1000],   // 三种运输方式的默认距离，都设置为1000
		initProcMap : function(){
			<c:forEach items="${procedures}" var="procedure" varStatus="procStatus">
			this.procMap[${procStatus.index}] = [];
			this.procParam[${procStatus.index}] = [];
			this.procNames[${procStatus.index}] = '${procedure.name}';
			
			<c:forEach items="${procedure.items}" var="procedureItem" varStatus="itemStatus">
				this.procMap[${procStatus.index}][${itemStatus.index}] = false;
				<c:if test="${param.target == 'consumption'}">
				this.procParam[${procStatus.index}][${itemStatus.index}] = ${procedureItem.consumptionValue};
				</c:if>
				<c:if test="${param.target == 'emission' }">
				this.procParam[${procStatus.index}][${itemStatus.index}] = ${procedureItem.emissionValue};
				</c:if>
			</c:forEach>
			</c:forEach>
			
			// 按照选定方案项初始化，若为选定，使用第一个
			<c:if test="${empty selectedCycleIndex}">
				<c:set var="selectedCycleIndex" value="0" />
			</c:if>
			TableHandler.selectRow(${selectedCycleIndex});
			
		},
		loadVoteData : function(cycle_scheme){
			this.reset();
			
			// 设置方案名称
			$('#scheme_name').val(cycle_scheme.name);
			
			// 设置选中项
			var procIndexStr = cycle_scheme.procedureIndexStr;
			var procArr = procIndexStr.split('|');
			for(var i = 0; i < procArr.length; i++){
				var itemArr = procArr[i].split(',');
				for(var j = 0; j < itemArr.length; j++){
					var itemIndex = parseInt(itemArr[j], 10);
					this.procMap[i][itemIndex] = true;
					$('#vote_'+ i + '_' + itemIndex).addClass('se');
				}
			}
			
			// 设置传输距离
			var transDistStr = cycle_scheme.transDistStr;
			var distArr = transDistStr.split(',');
			$('#transby_water').val(distArr[0]);
			$('#transby_land').val(distArr[1]);
			$('#transby_pipe').val(distArr[2]);
			this.setTransDist();
			
		},
		reset : function(){
			for(var col = 0; col < this.procMap.length; col++){
				for(var row = 0; row < this.procMap[col].length; row++){
					this.procMap[col][row] = false;
					$('#vote_'+ col + '_' + row).removeClass('se');
				}
			}
		},
		init : function(){
			this.initProcMap();
			
			$('.vote-list li').click(function(){
				var multi = $(this).attr('multi');
				var procId = parseInt($(this).attr('procId'), 10);
				var procItemId = parseInt($(this).attr('procItemId'), 10);
				
				if(multi == 'true'){		// 复选
					// 当前已选中，则取消选中
					if(VoteHandler.procMap[procId][procItemId]){
						VoteHandler.procMap[procId][procItemId] = false;
						$(this).removeClass('se');
					}else{
						VoteHandler.procMap[procId][procItemId] = true;
						$(this).addClass('se');
					}
				}else{						// 单选
					if(!VoteHandler.procMap[procId][procItemId]){
						// 取消已选中的
						for(var i = 0; i < VoteHandler.procMap[procId].length; i++){
							if(VoteHandler.procMap[procId][i]){
								VoteHandler.procMap[procId][i] = false;
								$('#vote_'+ procId + '_' + i).removeClass('se');
								break;
							}
						}
						
						VoteHandler.procMap[procId][procItemId] = true;
						$(this).addClass('se');
					}
				}
				VoteHandler.refreshData();
				
				$('#input-procedureIndexStr').val(VoteHandler.getProcedureIndexStr());
			});
			
		},
		getProcedureIndexStr : function(){
			var indexStr = "";
			for(var col = 0; col < this.procMap.length; col++){
				var $hasOne = false;
				for(var row = 0; row < this.procMap[col].length; row++){
					if(this.procMap[col][row]){
						if($hasOne) {
							indexStr += ',';
						}
						indexStr += row;
						$hasOne = true;
					}
				}
				if(col < this.procMap.length - 1){
					indexStr += '|';
				}
			}
			
			return indexStr;
		},
		/**
		*  刷新数据
		*/
		refreshData : function(){
			var seriesData = [];    // [['开采', 1], ['制备', 1], ['运输', 1], ['利用', 1]]
			for(var col = 0; col < this.procParam.length; col++){
				var $value = 0;
				for(var row = 0; row < this.procMap[col].length; row++){
					if(this.procMap[col][row]){
						
						// 这里做了一个特殊处理，如果col=2，对应"运输"列，对距离做了重新计算
						// 以现在设置的距离除以1000
						if(col == 2){
							$value += this.procParam[col][row] * this.transdist[row] / 1000;
						}else{
							$value += this.procParam[col][row];
						}
					}
				}
				seriesData.push([this.procNames[col], $value]);
			}
			
			// 刷新统计图
			ChartHandler.refresh(seriesData);
		},
		// 设置运输距离
		setTransDist : function(){
			this.transdist[0] = $('#transby_water').val();
			this.transdist[1] = $('#transby_land').val();
			this.transdist[2] = $('#transby_pipe').val();
			
			$('#input-transDistStr').val(this.transdist);
			
			// 修改完成后，刷新统计图
			this.refreshData();
		}
		
	};
	
   	var ChartHandler = {
   		init : function(){
   			$("#chart-container").highcharts(
   					{
   				        chart: {
   				            plotBackgroundColor: null,
   				            plotBorderWidth: null,
   				            plotShadow: false,
   				         	spacing : [10, 10, 10, 10],
   				         	margin : [10, 10, 10, 10],
   				            width : 300,
   				            height : 300
   				        },
   				        title: {
   				            text: null,
   				         	margin : [0, 0, 0, 0],
   				         	verticalAlign : 'bottom'
   				        },
   				        tooltip: {
   				    	    pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
   				        },
   				        plotOptions: {
   				            pie: {
   				                allowPointSelect: true,
   				                cursor: 'pointer',
   				                dataLabels: {
   				                    enabled: true,
   				                    color: '#000000',
   				                    connectorColor: '#000000',
   				                    format: '<b>{point.name}</b><br/>{point.percentage:.1f} %'
   				                }
   				            }
   				        },
   				        series: [{
   				            type: 'pie',
   				            name: '占比',
   				            data: [
   				            ]
   				        }]
   				    });
   		},
   		refresh : function(seriesData){
   			$('#chart-container').highcharts().series[0].setData(seriesData, true);
   		}
   	}
   	
   	var TableHandler = {
   		selectedRow : 0,
   		cycleList : [],
   		init : function(){
   			<c:forEach items="${curCycleType.cycleList}" var="cycle">
   			this.cycleList.push(
   					{
   						name:'${cycle.name}', 
   						procedureIndexStr : '${cycle.procedureIndexStr}',
   						transDistStr : '${cycle.transDistStr}'
   					});
   			</c:forEach>
   			
   			$('table tr').click(function(){
   				TableHandler.selectRow(parseInt($(this).attr('index')));
   			});
   		},
   		// 选中行	
   		selectRow : function(rowIndex){
   			$("#tr_" + this.selectedRow).css({'background-color':'#fff'});
   			$("#tr_" + rowIndex).css({'background-color':'#0088cc'});
   			this.selectedRow = rowIndex;
   			
   			if(this.cycleList.length > 0){
   				VoteHandler.loadVoteData(this.cycleList[rowIndex]);
   			}
   			VoteHandler.refreshData();
   			
   			$('#input-procedureIndexStr').val(VoteHandler.getProcedureIndexStr());
   		},
   		delRow : function(rowIndex){
   			if(confirm('确定删除选中行吗？')){
	   			window.location = "${base}/cycle/delGasCycle?rowIndex=" + rowIndex + "&cycletype=${param.cycletype}&target=${param.target}";
   			}
   		},
   		isCycleNameExist : function(cycleName){
   			for(var i = 0; i < this.cycleList.length; i++){
   				if(this.cycleList[i].name == cycleName){
   					return true;
   				}
   			}
   			return false;
   		},
   		getCurCycleName : function(){
   			return this.cycleList[this.selectedRow].name;
   		}
   	}
   	
   	var BtnHandler = {
   		init : function(){
   			
   			$('#btn-analyze').click(function(){
   				$('#analyze-modal').modal({
					width : '70%',
					height : ($(window).height() - 40) + 'px',
					modalOverflow : true,
					top : '20px'
				});
   			});
   			
   			$('#btn-set-dist').click(function(){
   				VoteHandler.setTransDist();
   			});
   			
   			$('#transby_water, #transby_land, #transby_pipe').change(function(){
   				var regex = /^[1-9]+[0-9]*]*$/;
   				var transIds = ['#transby_water', '#transby_land', '#transby_pipe'];
   				for(var index in transIds){
   					var $input = $(transIds[index]);
	   				if(!regex.test($input.val())){
	   					$input.css({color:'#b94a48'});
	   					alert('运输距离必须为整数，请确认！');
	   					
	   					return;
	   				}
	   				$input.css({color:'#468847'});
   				}
   				
   				VoteHandler.setTransDist();
   			});
   			
   			$('#btn-save').click(function(){
   				// 判断是否重名
   				var schemeName = $('#scheme_name').val(); 
   				if(schemeName.trim() == ''){
   					alert('请输入方案名称');
					return;
   				}
   				
   				if(TableHandler.isCycleNameExist(schemeName) && TableHandler.getCurCycleName() != schemeName){
   					if(!confirm('该方案名称已存在，继续保存将覆盖现有方案，是否继续？')){
	   					return;
   					}
   				}
   				$('#cycle_form').submit();
   				
   			});
   			
   		}
   	}
   	
  	$(document).ready(function(){
  		BtnHandler.init();
  		TableHandler.init();
  		ChartHandler.init();
  		VoteHandler.init();
  		
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
  	});
	</script>
</body>
</html>