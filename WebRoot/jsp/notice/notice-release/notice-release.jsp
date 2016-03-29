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
<link rel="stylesheet" type="text/css" href="<c:url value='/component/bootstrap-wysihtml5/css/bootstrap-wysihtml5-0.0.2.css'></c:url>" media="all">
<title>公告/通知发布</title>
</head>
<body ng-app="noticeReleaseApp" ng-controller="noticeReleaseController">
<!-- 头部 -->
<jsp:include page="${path}/jsp/mobile/head.jsp"></jsp:include>
<div class="container-fluid">
	<form class="form-horizontal"> 
		<br/>
		<div class="form-group">
			<label class="col-sm-2 control-label">发送对象：</label>
			<!--<input class="col-sm-10 form-control" type="text" style="width:60%" ng-model="">-->
			<div class="col-sm-10 btn-group">
				<button type="button" class="btn btn-default" ng-click="choose(1)">小区</button>
				<button type="button" class="btn btn-default" ng-click="choose(2)">楼栋</button>
				<button type="button" class="btn btn-default" ng-click="choose(3)">单元</button>
				<button type="button" class="btn btn-default" ng-click="choose(4)">房号</button>
			</div>
		</div>
		<br/>
		<div class="form-group">
			<label class="col-sm-2 control-label"></label>
			<!--<input class="col-sm-10 form-control" type="text" style="width:60%" ng-model="">-->
			<div class="col-sm-10 btn-group">
				<div class="dropdown">
				   <button type="button" class="btn dropdown-toggle" id="dropdownMenu1" 
				      data-toggle="dropdown">
				      	查看
				      <span class="caret"></span>
				   </button>
				   <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1" style="width: 300px">
				      <li role="presentation">
				         <div id="chosedDiv">
				         	<strong ng-bind="choseTitle"></strong>
				         	<span style="word-break:normal; width:auto; display:block; white-space:pre-wrap;word-wrap : break-word ;overflow: hidden;"  ng-bind="choseValue"></span>
				         </div>
				      </li>
				   </ul>
				</div>
			</div>
		</div>
		<br/>
		<div class="form-group">
			<label class="col-sm-2 control-label">类型：</label>
			<select class="col-sm-10 form-control" ng-model="notice.noticeType" ng-options="noticeType.attrValue as noticeType.attrValueName for noticeType in noticeTypes">
			</select>
		</div>
		<br/>
		<div class="form-group">
			<label class="col-sm-2 control-label">主题：</label>
			<input class="col-sm-10 form-control" type="text"ng-model="notice.title">
		</div>
	</form>
	<textarea id="textarea" placeholder="输入内容 ..." style="width: 70%; height: 30%" ng-model="notice.content"></textarea>
	<div clas="btn-group">
		<button id="draft" data-loading-text="保存中..." autocomplete="off"   type="button" class="btn btn-default" ng-click="saveDraft()">存为草稿</button>
		<button id="send" data-loading-text="发送中..." autocomplete="off"  type="button" class="btn btn-primary" ng-click="send()">发送</button>
	</div>
</div>
<div class="modal fade hide" id="myModal" tabindex="-1" role="dialog" style="height:70%;"
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	            <form class="form-horizontal"  role="form">
					<br/>
					<div id="community" ng-if="flag!=1">
						<span>小区：</span>
						<select class="form-control" ng-model="community.communityId" ng-change="changeCommunity(community.communityId)"
							ng-options="community.communityId as community.communityName for community in communitys" ></select>
					</div>
					<br/>
					<div id="floor" ng-if="flag==3 || flag==4">
						<span>楼栋：</span>
						<select class="form-control" ng-model="building.buildingId" ng-change="changeBuilding(building.buildingId)"
							ng-options="building.buildingId as building.buildingName for building in buildings" ></select>
					</div>
					<br/>
					<div id="room" ng-if="flag==4">
						<span>单元：</span>
						<select class="form-control" ng-model="buildingUnit.buildingId" ng-change="changeBuildingUnit(buildingUnit.buildingId)"
							ng-options="buildingUnit.buildingId as buildingUnit.buildingName for buildingUnit in buildingUnits" ></select>
					</div>
				</form>
				<div class="pull-right">
					<button class="btn btn-primary" ng-click="onSure()">
  					确定
	            	</button>
				</div>
				<div>
					<table id="table" class="table table-hover">
						<thead>
				           <tr>
					           <th class="with-checkbox"><input id="checkAll" class="check_all" type="checkbox"></th>
					           <th ng-if="flag==1">小区</th>
					           <th ng-if="flag==2">楼栋</th>
					           <th ng-if="flag==3">单元</th>
					           <th ng-if="flag==4">楼层</th>
					           <th ng-if="flag==4">房号</th>
				           </tr>
				       </thead>
				       <tbody style="height:200px;">
				           <tr ng-if="flag==1" ng-repeat="community in communitys">
				           		<td class="with-checkbox"><input type="checkbox" name="check" value="{{community}}" /></td>
								<td>{{ community.communityName }}</td>
				           </tr>
				           <tr ng-if="flag==2" ng-repeat="building in buildings">
				           		<td class="with-checkbox"><input type="checkbox" name="check" value="{{building}}" /></td>
				           		<td>{{ building.buildingName }}</td>
				           </tr>
				           <tr ng-if="flag==3" ng-repeat="buildingUnit in buildingUnits">
				           		<td class="with-checkbox"><input type="checkbox" name="check" value="{{buildingUnit}}" /></td>
				           		<td>{{ buildingUnit.buildingName }}</td>
				           </tr>
				           <tr ng-if="flag==4" ng-repeat="room in rooms">
				           		<td class="with-checkbox"><input type="checkbox" name="check" value="{{room}}" /></td>
				           		<td>{{ room.floor }}</td>
								<td>{{ room.roomNbr }}</td>
				           </tr>
				       </tbody>
			    	</table>
				</div>
			</div>
	      </div>
	   </div>
</div>
<script type="text/javascript" src="<c:url value='/component/bootstrap-wysihtml5/js/wysihtml5-0.3.0.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/angularJS/angular-ie8-1.4.7.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/component/bootstrap-wysihtml5/js/bootstrap-wysihtml5-0.0.2.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/json2.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/defined-checkbox.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.myPagination.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/component/message-dialog-comp/js/message-dialog.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/common/common.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/jsp/notice/notice-service.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/jsp/mobile/property/property-service.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/jsp/notice/notice-release/notice-release-controller.js'></c:url>"></script>
</body>

</html>