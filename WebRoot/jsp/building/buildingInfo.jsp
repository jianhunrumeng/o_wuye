<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String buildingId = request.getParameter("buildingId");
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/bootstrap/css/bootstrap-responsive.css'></c:url>"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/style.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/bootstrap/css/todc_bootstrap.css'></c:url>"
	media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/themes.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/font/css/font-awesome.css'></c:url>"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/inside.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/defined-style.css'></c:url>" media="all">
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/building/buildingInfo.js'></c:url>"></script>
<title>楼栋/单元详情</title>
<style type="text/css">
.rpd-detail {
	margin: 5px;
	padding: 5px;
	height: 30px;
	line-height: 30px;
}
</style>
</head>
<body>
	<div id="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="box">
						<div class="box-title">
							<div class="span10">
								<h3>楼栋/单元</h3>
							</div>
							<div class="span2">
								<a class="btn pull-right"
									href="Javascript:window.history.go(-1)">返回</a>
							</div>
						</div>
						<div class="box-content">
							<form id="communityform" method="post"
								class="form-horizontal form-validate" novalidate>
								<div class="defined-title">
									<span>基本信息</span>
									<input type="hidden" value="<%=buildingId %>" id="hidden_building_id" />
								</div>
								
								<div class="box-content">
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="communityName">小区</label>
											<div class="controls">
												<p id="communityName" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="buildingName">楼栋/单元名称</label>
											<div class="controls">
												<p id="buildingName" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="ownerBuilding">所属楼栋</label>
											<div class="controls">
												<p id="ownerBuilding" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="buildingPhone">联系电话</label>
											<div class="controls">
												<p id="buildingPhone" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="floorCount">楼层数</label>
											<div class="controls">
												<p id="floorCount" /> 
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="stairArea">楼梯面积</label>
											<div class="controls">
												<p id="stairArea" /> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="finishTime">竣工日期</label>
											<div class="controls">
												<p id="finishTime" /> 
											</div>
										</div>
										
										
									</div>
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="userableArea">使用面积</label>
											<div class="controls">
												<p id="userableArea" /> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="afforestArea">绿化面积</label>
											<div class="controls">
												<p id="afforestArea" /> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="ownershipType">权属类型</label>
											<div class="controls">
												<p id="ownershipType" /> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="buildingStructure">房屋结构</label>
											<div class="controls">
												<p id="buildingStructure" /> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="upgradeCondition">装修状况</label>
											<div class="controls">
												<p id="upgradeCondition" />
											</div>
										</div>
										

										<div class="control-group">
											<label class="control-label" for="pp_status">有效标志</label>
											<div class="controls">
												<p id="pp_status" />
											</div>
										</div>
									</div>
								</div>
								<div class="defined-title">
									<span>负责人信息</span>
								</div>
								<div class="box-content">
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="pp_leader_name">姓名</label>
											<div class="controls">
												<p  id="pp_leader_name">
												
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_cert_type">证件类型</label>
											<div class="controls">
												<p  id="pp_leader_cert_type">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_cert_nbr">证件号码</label>
											<div class="controls">
												<p  id="pp_leader_cert_nbr">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_phone">联系电话</label>
											<div class="controls">
												<p  id="pp_leader_phone"> 
											</div>
										</div>
									</div>
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="pp_leader_qq">QQ</label>
											<div class="controls">
												<p  id="pp_leader_qq">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_wx">微信</label>
											<div class="controls">
												<p  id="pp_leader_wx">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_email">Email</label>
											<div class="controls">
												<p  id="pp_email">
											</div>
										</div>

									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="fallr-overlay"></div>
</body>
</html>