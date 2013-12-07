
<%@ page contentType="text/html;charset=utf-8" language="java"%>
<%@ include file="/includes/taglibs.jsp"%>
<!-- Fixed navbar -->
<c:if test="${param.cycletype == null }">
	<c:set var="cycletype" value="${session_cycletype_list[0].code}"></c:set>
</c:if>
<c:if test="${param.target == null }">
	<c:set var="target" value="consumption"></c:set>
</c:if>

<div class="navbar navbar-fixed-top" id="nav">
	<div class="navbar-inner">
		<div class="container">
			<button type="button" class="btn btn-navbar" data-toggle="collapse"
				data-target=".nav-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="brand" id="logo_bar" href="${base}">中海油碳里程分析系统</a>
			<div class="nav-collapse collapse">
				<ul class="nav">
					<li
						<c:if test="${param.currentNav == 'statistic'}">class="active"</c:if>>
						
						<c:if test="${empty param.target }">
							<c:set var="_target" value="consumption" />
						</c:if>
						<c:if test="${not empty param.target }">
							<c:set var="_target" value="${param.target}" />
						</c:if>
						
						<c:if test="${param.cycletype != 'gas' }">
						<a href="${base}/cycle/stat?cycletype=${param.cycletype}&target=${_target}&statBy=generator">
							<img src="${base}/images/stat.png" width="16" height="16" />&nbsp;行业数据分析
						</a>
						</c:if>					
						 <c:if test="${param.cycletype == 'gas' }">
						<a
								href="${base}/cycle/gasView?cycletype=${param.cycletype}&target=${_target}&statBy=generator">
							<img src="${base}/images/stat.png" width="16" height="16" />&nbsp;行业数据分析
						</a>
						</c:if>
						
					</li>
					<li
						<c:if test="${param.currentNav == 'config'}">class="active"</c:if>>
						<%-- 
                	<a href="${base}/cycle/config?cycletype=${param.cycletype}"><img src="${base}/images/config_16.png" width="16" height="16"/>&nbsp;自定义项目</a></li>
                	--%> <a href="javascript://"
						id="header-btn-param-config"><img
							src="${base}/images/config_16.png" width="16" height="16" />&nbsp;自定义项目</a>
					</li>
					<li
						<c:if test="${param.currentNav == 'upload'}">class="active"</c:if>>
						<a href="#uploadModal" id="header-btn-upload"><img
							src="${base}/images/upload.png" width="16" height="16" />&nbsp;上传源数据</a>
					</li>
				</ul>

				<div class="btn-group" style="float: right">
					<a class="btn btn-info dropdown-toggle" data-toggle="dropdown"
						href="#">选择<span class="caret"></span>
					</a>
					<ul class="dropdown-menu">
						<c:forEach items="${session_cycletype_list}" var="cycletype">
							<c:if test="${param.currentNav == 'statistic' }">
								<li><c:if test="${cycletype.code != 'gas' }">
										<a
											href="${base}/cycle/stat?cycletype=${cycletype.code}&target=consumption&statBy=generator">${cycletype.name}</a>
									</c:if> <c:if test="${cycletype.code == 'gas' }">
										<a
											href="${base}/cycle/gasView?cycletype=${cycletype.code}&target=consumption&statBy=generator">${cycletype.name}</a>
									</c:if></li>
							</c:if>
							<c:if test="${param.currentNav == 'config' }">
								<li><a
									href="${base}/cycle/config?&cycletype=${cycletype.code}">${cycletype.name}</a></li>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<div
					style="float: right; height: 40px; line-height: 40px; padding-left: 10px;">
					<b>${curCycleType.name}生命周期&nbsp;&nbsp;&nbsp;&nbsp;</b>
				</div>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
</div>

