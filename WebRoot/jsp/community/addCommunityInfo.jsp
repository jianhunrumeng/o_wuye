<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String communityId = request.getParameter("communityId");
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
	src="<c:url value='/js/community/communityEdit.js'></c:url>"></script> 
	<%if(communityId == null) {%>
<script type="text/javascript"
	src="<c:url value='/js/public/defined-address.js'></c:url>"></script>
<%} else { %>
<script type="text/javascript"
	src="<c:url value='/js/public/edit-address.js'></c:url>"></script>
<%} %>
<script type="text/javascript" src="<c:url value='/js/public/jquery-ui.js'></c:url>"></script>
<title>小区设置</title>
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
								<h3>新增小区</h3>
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
									<input type="hidden" value="<%=communityId %>" id="hidden_community_id" />
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
											<label class="control-label" for="pp_name">小区名称</label>
											<div class="controls">
												<input type="text" id="pp_name" name="pp_name" class="{required:true,minlength:5,messages:{required:'请输入内容'}}">
												<span class="maroon">*</span>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_abb">简称</label>
											<div class="controls">
												<input type="text" id="pp_abb" name="pp_abb"> 
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="pp_office_phone">公司电话</label>
											<div class="controls">
												<input type="text" id="pp_office_phone"
													name="pp_office_phone"> <span class="maroon">*</span>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_fax">公司传真</label>
											<div class="controls">
												<input type="text" id="pp_fax" name="pp_fax">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_ca">小区占地面积</label>
											<div class="controls">
												<input type="text" id="pp_ca" name="pp_ca">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_cba">建筑面积</label>
											<div class="controls">
												<input type="text" id="pp_cba" name="pp_cba">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_cfa">绿化面积</label>
											<div class="controls">
												<input type="text" id="pp_cfa" name="pp_cfa">
											</div>
										</div>
									</div>
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="pp_bank">开户银行</label>
											<div class="controls">
												<select name="type" id="pp_bank" class="defined-select">
													<!-- <option selected="selected" value="10">中国工商银行</option>
													<option value="11">中国农业银行</option>
													<option value="11">中国邮政银行</option>
													<option value="11">中国交通银行</option>
													<option value="11">中国招商银行</option> -->
												</select> <span class="maroon">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_account_name">开户名称</label>
											<div class="controls">
												<input type="text" id="pp_account_name"
													name="pp_account_name"> 
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_account">账号</label>
											<div class="controls">
												<input type="text" id="pp_account" name="pp_account">
												
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="pp_zip_code">邮编</label>
											<div class="controls">
												<input type="text" id="pp_zip_code" name="pp_zip_code">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_address">地址</label>
											<div class="controls">
												<select name="type" id="area_province" style="width: 100px;">
													<option selected="selected" value="10">北京</option>
												</select> <select name="type" id="area_city" style="width: 100px;">

												</select> <select name="type" id="area_area" style="width: 100px;">
													<option selected="selected" value="110109">门头沟区</option>
												</select> <span class="maroon">*</span>
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_address">地址详情</label>
											<div class="controls">
												<input type="text" id="pp_address" name="pp_address"
													style="width: 290px;"> <span class="maroon">*</span>
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
												<span class="maroon">*</span>
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
													name="pp_leader_phone"> <span class="maroon">*</span>
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
											value="提交"> <input id="reset_btn" type="button"
											class="btn" value="重置">
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