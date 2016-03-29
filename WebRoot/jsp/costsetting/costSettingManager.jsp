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
			href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>"
			media="all" />
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
			src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>">
</script>
		<script type="text/javascript"
			src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>">
</script>
		<script type="text/javascript"
			src="<c:url value='/js/modal.js'></c:url>">
</script>
		<script type="text/javascript"
			src="<c:url value='/js/jquery.myPagination.js'></c:url>">
</script>

<%--<script type="text/javascript"
			src="<c:url value='/js/community/communityRelSetting.js'></c:url>">
</script>
		--%><title>物业管理-费用设置</title>
		<style type="text/css">
.value-input {
	width: 200px;
}

.defined-input-border-del {
	color: black;
	border: 1px solid red;
}

.defined-line {
	text-decoration: line-through;
}

.new-add {
	color: green;
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
								<h3>
									<i class="icon-edit"></i>费用相关设置
								</h3>
							</div>
							<div class="box-content" style="width: 65%">
							</div>
							<a href="accItemTypeSettingManager.jsp" 
								class="tile tile-themed defined-modal" data-attrid="1"
								data-attrname="费用设置"> <i class="fa fa-ticket"></i>
								<div class="tile-info">
									<strong>费用设置</strong>
								</div> </a>
							<a href="meterSpecSettingManager.jsp" 
								class="tile tile-themed defined-modal" data-attrid="1"
								data-attrname="费表类型设置"> <i class="fa fa-ticket"></i>
								<div class="tile-info">
									<strong>费表类型设置</strong>
								</div> </a>
							<a href="meterInstSettingManager.jsp" 
								class="tile tile-themed defined-modal" data-attrid="1"
								data-attrname="费表设置"> <i class="fa fa-ticket"></i>
								<div class="tile-info">
									<strong>费表设置</strong>
								</div> </a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>