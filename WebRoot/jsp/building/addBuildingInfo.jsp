<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	href="<c:url value='/css/tight-style.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/font/css/font-awesome.css'></c:url>"
	media="all" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/inside.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/defined-style.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css"
	href="<c:url value='/css/public/jquery-ui.css'></c:url>" media="all">


<script type="text/javascript"
	src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/public/jquery-ui.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/public/selectSearchCom.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/building/buildingAdd.js'></c:url>"></script>
<title>楼栋/单元详情</title>
<style type="text/css">
.rpd-detail {
	margin: 5px;
	padding: 5px;
	height: 30px;
	line-height: 30px;
}

.defind-label {
	margin-left: 15px;
	display: inline;
}

.defind-div {
	display: inline-block;
}
.building-row{
	padding-top:5px;
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
						<div class="row-fluid">
							<div class="span12">
								<div class="box-content pull-right ">
									<div class="defind-div">
										<label class="defind-label">物业公司</label> <input
											id="pp_company" type="text" class="input-medium"
											placeholder="输入选择物业公司">
									</div>
									<div class="defind-div">
										<label class="defind-label">小区</label> <input id="community"
											type="text" class="input-medium" placeholder="输入选择小区">
									</div>
									<div class="defind-div">
										<!-- <a class="btn pull-right">确定</a> -->
										<input type="button" class="btn" id="search"
											onclick="clickOk()" value="确定">
									</div>
								</div>
							</div>
						</div>
						<div class="row-fluid" style="margin-left:40px">
							<div class="span2" >
								<h4>基本信息</h4>
							</div>
						</div>
						<div class="row-fluid" style="margin-left:40px">

							<div class="span3">
								<span>所属物业公司：</span><span id="desCompany"></span>
							</div>
							<div class="span3">
								<span>所属小区：</span><span id="desCommunity"></span>
							</div>
						</div>
						<!-- 						<div class="defined-title ">
									
									
								</div> -->
						<div class="box-content">
							<form id="buildingform" method="post"
								class="form-horizontal form-validate" novalidate>
								<div id="buildings" class="control-group"
									style="margin-bottom: 100px;">
									<div class="row-fluid building-row">
										<div class="span12">
											<div class="span3 defind-div ">
												<label class="defind-label" for="buildingName">楼栋/单元名称</label> <input
													type="text" style="width: 40%" id="buildingName" name="buildingName"
													class=""> <span class="maroon">*</span>
											</div>
											<div class="defind-div span2" style="margin-left:10px">
												<label class="defind-label" for="ownerBuilding">所属楼栋</label>
												<select name="type" id="ownerBuilding" style="width: 50%;"></select>
											</div>
											<div class="span2 defind-div " style="margin-left:10px">
												<label class="defind-label" for="floorCount">楼层数</label> <input type="text"
													style="width: 20%" id="floorCount" name="floorCount">
											</div>
											<div class="defind-div span2">
												<a class="btn" href="javascript:void(0)"
													onclick="addRow(this)"><i class="icon-plus"></i>新增</a> <a
													class="btn" href="javascript:void(0)"
													onclick="removeRow(this)"><i class="icon-minus"></i>删除</a>
											</div>
										</div>
									</div>
								</div>
								<div class="controls">
										<input id="submit_btn" type="button" class="btn btn-primary"
											value="提交">
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