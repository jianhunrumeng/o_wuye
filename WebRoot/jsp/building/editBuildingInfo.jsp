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
<link rel="stylesheet" type="text/css" href="<c:url value='/css/public/jquery-ui.css'></c:url>" media="all">
	
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/building/buildingEdit.js'></c:url>"></script> 
<script type="text/javascript"
	src="<c:url value='/My97DatePicker/WdatePicker.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/jquery-ui.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/selectSearchCom.js'></c:url>"></script>
<title>楼栋/单元管理</title>
<style type="text/css">
.defined-title {
	height: 40px;
	width: 100%;
	margin-left: 20px;
	border-bottom-width: 1px;
	border-bottom-color: blue;
}

.defined-title span {
	font-size: 16px;
}

.defined-select {
	width: 228px;
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
											<label class="control-label" for="pp_company">物业公司</label>
											<div class="controls">
												<input type="text" id="pp_company" name="pp_company" data-id="" placeholder="请搜索物业公司名称">
												<span class="maroon">*</span>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="community">小区</label>
											<div class="controls">
												<input type="text" id="community" name="community" data-id="" placeholder="请搜索小区名称">
												<span class="maroon">*</span>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="buildingName">楼栋/单元名称</label>
											<div class="controls">
												<input type="text" id="buildingName" name="buildingName" class="{required:true,minlength:5,messages:{required:'请输入内容'}}">
												<span class="maroon">*</span>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="ownerBuilding">所属楼栋</label>
											<div class="controls">
												<select name="type" id="ownerBuilding" >
												</select> 
											</div>
											
										</div>
										<div class="control-group">
											<label class="control-label" for="buildingPhone">联系电话</label>
											<div class="controls">
												<input type="text" id="buildingPhone" name="buildingPhone" class="{required:true,minlength:5,messages:{required:'请输入内容'}}">
												<!-- <span class="maroon">*</span> -->
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="floorCount">楼层数</label>
											<div class="controls">
												<input type="text" id="floorCount" name="floorCount"> 
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="stairArea">楼梯面积</label>
											<div class="controls">
												<input type="text" id="stairArea"
													name="stairArea"> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="finishTime">竣工日期</label>
											<div class="controls">
												<input type="text" id="finishTime" name="finishTime" class="Wdate"  onClick="WdatePicker()">
												
											</div>
										</div>
										
										
									</div>
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="userableArea">使用面积</label>
											<div class="controls">
												<input type="text" id="userableArea" name="userableArea">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="afforestArea">绿化面积</label>
											<div class="controls">
												<input type="text" id="afforestArea" name="afforestArea">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="ownershipType">权属类型</label>
											<div class="controls">
												<select name="type" id="ownershipType">
													
												</select> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="buildingStructure">房屋结构</label>
											<div class="controls">
												<select name="type" id="buildingStructure">
													
												</select> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="upgradeCondition">装修状况</label>
											<div class="controls">
												<select name="type" id="upgradeCondition">
												</select> 
											</div>
											
										</div>
										

										<div class="control-group">
											<label class="control-label" for="pp_status">有效标志</label>
											<div class="controls">
												<select name="type" id="pp_status" class="defined-select">
													<option selected="selected" value="1000">生效</option>
													<option value="1100">失效</option>
												</select> <span class="maroon">*</span>
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
												<input type="text" id="pp_leader_name" name="pp_leader_name">
												<!-- <span class="maroon">*</span> -->
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_cert_type">证件类型</label>
											<div class="controls">
												<select name="type" id="pp_leader_cert_type"
													class="defined-select">
													<option selected="selected" value="10">身份证</option>
													<option value="11">营业执照</option>
												</select>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_cert_nbr">证件号码</label>
											<div class="controls">
												<input type="text" id="pp_leader_cert_nbr"
													name="pp_leader_cert_nbr">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_phone">联系电话</label>
											<div class="controls">
												<input type="text" id="pp_leader_phone"
													name="pp_leader_phone"><!--  <span class="maroon">*</span> -->
											</div>
										</div>
									</div>
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="pp_leader_qq">QQ</label>
											<div class="controls">
												<input type="text" id="pp_leader_qq" name="pp_leader_qq">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_wx">微信</label>
											<div class="controls">
												<input type="text" id="pp_leader_wx" name="pp_leader_wx">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_email">Email</label>
											<div class="controls">
												<input type="text" id="pp_email" name="pp_email">
											</div>
										</div>

									</div>
								</div>
								<div class="control-group">
									<div class="controls">
										<input id="submit_btn" type="button" class="btn btn-primary"
											value="提交"> <!-- <input id="reset_btn" type="button"
											class="btn" value="重置"> -->
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