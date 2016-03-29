<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String companyId = request.getParameter("companyId");
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
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/public/defined-address.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/company/propertyCompanyInfo.js'></c:url>"></script>

<title>物业管理-物业公司管理</title>
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
<body onload="P.xskn()">
	<div id="main">
		<div class="container-fluid">
			<div class="row-fluid">
				<div class="span12">
					<div class="box">
						<div class="box-title">
							<div class="span10">
								<h3>修改物业公司</h3>
							</div>
							<div class="span2">
								<a class="btn pull-right"
									href="Javascript:window.history.go(-1)">返回</a>
							</div>
						</div>
						<div class="box-content">
							<form id="propertyCompanyform" method="post"
								class="form-horizontal form-validate" novalidate>
								<div class="defined-title">
									<span>基本信息</span> <input type="hidden" value="<%=companyId%>"
										id="hidden_company_id" />
								</div>
								<div class="box-content">
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="pp_name">物业公司名称</label>
											<div class="controls">
												<p id="pp_name" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_abb">简称</label>
											<div class="controls">
												<p id="pp_abb" />
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="pp_office_phone">公司电话</label>
											<div class="controls">
												<p id="pp_office_phone" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_fax">公司传真</label>
											<div class="controls">
												<p id="pp_fax" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_status">有效标志</label>
											<div class="controls">
												<p id="pp_status" />
											</div>
										</div>
									</div>
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="pp_bank">开户银行</label>
											<div class="controls">
												<p id="pp_bank" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_account_name">开户名称</label>
											<div class="controls">
												<p id="pp_account_name" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_account">账号</label>
											<div class="controls">
												<p id="pp_account" />
											</div>
										</div>

										<div class="control-group">
											<label class="control-label" for="pp_zip_code">邮编</label>
											<div class="controls">
												<p id="pp_zip_code" />
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_address">地址</label>
											<div class="controls">
												<p id="pp_address" />
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
												<p id="pp_leader_name">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_cert_type">证件类型</label>
											<div class="controls">
												<p id="pp_leader_cert_type">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_cert_nbr">证件号码</label>
											<div class="controls">
												<p id="pp_leader_cert_nbr">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_phone">联系电话</label>
											<div class="controls">
												<p id="pp_leader_phone">
											</div>
										</div>
									</div>
									<div class="span6">
										<div class="control-group">
											<label class="control-label" for="pp_leader_qq">QQ</label>
											<div class="controls">
												<p id="pp_leader_qq">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_wx">微信</label>
											<div class="controls">
												<p id="pp_leader_wx">
											</div>
										</div>
										<div class="control-group">
											<label class="control-label" for="pp_leader_email">Email</label>
											<div class="controls">
												<p id="pp_email">
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