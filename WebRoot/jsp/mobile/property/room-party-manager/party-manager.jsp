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

<title>住户管理</title>
</head>
<body ng-app="meterReadingApp" ng-controller="meterReadingController">
<div class="container-fluid">
	<div>
		<form class="form-horizontal"  role="form">
			<br/>
			<div>
				<span>小区：</span>
				<select class="form-control" ng-model="community.communityId" ng-change="queryBuilding(community.communityId)"
					ng-options="community.communityId as community.communityName for community in communitys" ></select>
			</div>
			<br/>
			<div>
				<span>楼栋：</span>
				<select class="form-control" ng-model="building.buildingId" ng-change="changeBuildingQryRoom(building.buildingId)"
					ng-options="building.buildingId as building.buildingName for building in buildings" ></select>
			</div>
			<br/>
			<div>
				<span>单元：</span>
				<select class="form-control" ng-model="buildingUnit.buildingId" ng-change="changeBuildingQryRoom(buildingUnit.buildingId)"
					ng-options="buildingUnit.buildingId as buildingUnit.buildingName for buildingUnit in buildingUnits" ></select>
			</div>
		</form>
	</div>
	<table class="table table-hover">
		<tr>
			<th>楼层</th>
			<th>房间号</th>
			<th>操作</th>
		</tr>
		<tr ng-repeat="room in rooms">
			<td>{{ room.floor }}</td>
			<td>{{ room.roomNbr }}</td>
			<td><button ng-click="addParty(room)" class="btn defined-role-btn">新增</button></td>
		</tr>
	</table>
	<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog"
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <form class="form-horizontal" name="myForm" novalidate>
	            	<div class="form-group">
	            		<label class="col-sm-2 control-label">姓名：</label>
					  	<input class="col-sm-10" type="text" name="partyName" class="form-control" ng-model="partyInfo.partyName" required>
						<span style="color:red" >*</span>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">证件类型：</label>
					  	<select class="col-sm-10"  name="certType" class="form-control" ng-model="partyInfo.certType" required>
							<option selected="selected" value="10">身份证</option>
							<option value="11">营业执照</option>
					  	</select>
					  	<span style="color:red" >*</span>
					</div>
					<div class="form-group">
	            		<label class="col-sm-2 control-label">证件号码：</label>
					  	<input class="col-sm-10" type="text" name="certNbr" class="form-control" ng-model="partyInfo.certNbr" required>
						<span style="color:red" >*</span>
					</div>
					<div class="form-group">
	            		<label class="col-sm-2 control-label">手机号：</label>
					  	<input class="col-sm-10" type="text" name="mobile"  class="form-control" ng-model="partyInfo.mobile" required>
						<span style="color:red" >*</span>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">住户类型：</label>
					  	<select class="col-sm-10"  name="relType" class="form-control" ng-model="partyInfo.relType" required>
							<option selected="selected" value="10">住户</option>
							<option value="11">其他</option>
					  	</select>
					  	<span style="color:red" >*</span>
					</div>
				</form>
			</div>
	         <div class="modal-footer" >
	            <button class="btn btn-primary" ng-disabled="!myForm.$valid" ng-click="onSave()">
  					确定
	            </button>
	         </div>
	      </div>
	   </div>
	</div>
</div>
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
<script type="text/javascript" src="<c:url value='/jsp/mobile/property/room-party-manager/party-manager-controller.js'></c:url>"></script>
</body>
</html>