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
<script type="text/javascript"
	src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript"
	src="<c:url value='/js/jquery.myPagination.js'></c:url>"></script>

<script type="text/javascript" src="<c:url value='/js/community/communityRelSetting.js'></c:url>"></script>
<title>物业管理-小区设置</title>
<style type="text/css">
	.value-input {
 	width: 200px;
 }
 .defined-input-border-del {
 	color:black;
 	border:1px solid red;
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
								<i class="icon-edit"></i>小区相关设置
							</h3>
						</div>
						<div class="box-content" style="width: 65%">
							<div id="relSetting" class="block block-tiles block-tiles-animated clearfix">
								<input type="hidden" value="<%=communityId %>" id="hidden_community_id" />
							</div>	
						</div>
						<% if (communityId != null) { %>
						<div class="box-content" style="width: 65%">
							<div id="relSetting" class="block block-tiles block-tiles-animated clearfix">
								<a href="parkingTypeManager.jsp?communityId=<%=communityId %>" data-toggle="modal" class="tile tile-themed defined-modal" data-attrId="" data-attrName=""> <i class="fa fa-ticket"></i><div class="tile-info"><strong>车位设置</strong></div> </a>';
							</div>	
						</div>
						<% } %>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="myModal1" class="modal hide fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="roleForm" style="margin-left:0"
					class="form-horizontal form-validate form-modal"
					novalidate="novalidate">
					<input type="hidden" id="hidden_attr_id" name="hidden_attr_id" value="0">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true">×</button>
						<h4 class="modal-title">
							<i class="fa fa-key"></i>设置
						</h4>
					</div>
					<div  class="modal-body" >
						<div class="span1"></div>
						<div class="span4" id="id_modal-body">
							
						</div>
						<div class="span1">
						</div>
		          		
					</div> 
					<div class="modal-footer">
						<button type="button" class="btn btn-primary"
							onClick="saveAttrValue()">提交</button>
						<button type="button" class="btn btn-default" data-dismiss="modal"
							aria-hidden="true">取消</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div id="fallr-overlay"></div>
</body>
</html>