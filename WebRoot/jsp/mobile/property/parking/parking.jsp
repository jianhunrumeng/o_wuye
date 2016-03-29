<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="user-scalable=no, width=device-width, initial-scale=1.0" />
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-responsive.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/todc_bootstrap.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/themes.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/font/css/font-awesome.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/inside.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/component/message-dialog-comp/css/message-dialog.css'></c:url>" media="all">

<title>车位查询</title>
</head>
<body ng-app="parkingApp" ng-controller="parkingController">
<!-- 头部 -->
<jsp:include page="${path}/jsp/mobile/head.jsp"></jsp:include>
	<form class="form-horizontal">
		<div class="form-group">
		
			<select class="input-small">
				<option value="parkingNbr">车位号</option>
				<option value="plateNbr">车牌号</option>
			</select>
			<input style="width:30%" type="text" ng-model="nbr">
			<button type="submit" class="btn" contenteditable="true" ng-click="queryParking()">查找</button>
		</div>
	</form>
	<table class="table table-hover">
		<tr>
			<th>小区</th>
			<th>车位号</th>
			<th>车位位置</th>
			<th>车牌号</th>
		</tr>
		<tr ng-repeat="parking in parkings">
			<td>{{ parking.community.communityName }}</td>
			<td>{{ parking.parkingNbr }}</td>
			<td>{{ parking.parkingPosition }}</td>
			<td>{{ parking.vehicleRel.plateNbr }}</td>
		</tr>
	</table>
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/angularJS/angular-ie8-1.4.7.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/json2.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/defined-checkbox.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.myPagination.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/component/message-dialog-comp/js/message-dialog.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/common/common.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/jsp/mobile/property/property-service.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/jsp/mobile/property/parking/parking-controller.js'></c:url>"></script>
</body>
</html>