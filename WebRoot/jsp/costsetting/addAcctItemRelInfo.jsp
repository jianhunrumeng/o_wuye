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
		<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-responsive.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/todc_bootstrap.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/themes.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/tight-style.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/font/css/font-awesome.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/inside.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/defined-style.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/public/jquery-ui.css'></c:url>" media="all">
		<title>费用类型设置</title>
		<style type="text/css">
.defind-label{
	margin-left:1px;
	margin-right:0px;
	display: inline;
}
.defind-div{
 	display: inline-block;
}
.defined-input{
	width:100px;
}
.defined-select {
	width: 228px;
}
.box .box-content {*zoom:1;
	padding: 20px;
	/* background: #fff; */
}
.defined-modal-input{
	position: absolute;
    z-index:1051;
}
</style>
	</head>
	<body ng-app="acctItemRelApp" ng-controller="acctItemRelController">
		<div id="main">
			<div class="container-fluid">
				<div class="row-fluid">
					<div class="span12">
						<div class="box">
							<div class="box-title">
								<div class="span10">
									<h3>
										费用设置
									</h3>
								</div>
								<div class="span2">
									<a class="btn pull-right"
										href="Javascript:window.history.go(-1)">返回</a>
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12">
									<form class="box-content pull-right" name="myForm" novalidate>
										<table  class="table table-hover">
											<tr class="row-fluid ">
												<!--<td>
													<select ng-model="selectValue" style="width: 100px;"
													ng-change="show(selectValue)"
													ng-options="item.id as item.name for item in data"></select>
												</td>
												<td>
													<label class="defind-label">物业公司</label>
													<input class="defined-input" type="text" id="temp_pp_company" class="input-medium  search-query" placeholder="输入择物业公司"
													ng-model="propertyCompany.value"
													ng-change="resetCommpanyChild()"
													ui-event="{autocompletecreate:'changeClass(propertyCompanys)'}"
													ui-autocomplete="propertyCompanys" required>
													<span style="color:red" >*</span> 
												</td>
												<td>
													<label class="defind-label">小区</label>
													<input  class="defined-input" type="text" id="temp_community" class="input-medium search-query" placeholder="输入择小区"
													ng-model="community.companyName"
													ng-change="resetBuildingChild()"
													ui-event="{autocompletecreate:'changeClass(communitys)'}"
													ui-autocomplete="communitys" required>
													<span style="color:red" >*</span>
												</td>
												<td ng-show="showBuilding" >
													<label class="defind-label">楼栋/单元</label>
													<input class="defined-input" type="text" id="buillding" class="input-medium search-query" placeholder="输入择楼栋/单元"
													ng-model="building.buildingName"
													ui-event="{autocompletecreate:'changeClass(buildings)'}"
													ui-autocomplete="buildings" required>
													<span style="color:red" >*</span>
												</td>
												<td ng-show="showFloor" >
													<label class="defind-label">楼层</label>
													<select class="defined-input" ng-model="floor.floorId" class="input-medium search-query"
													ng-options="floor.floorId as floor.floorName  for floor in floors"
													></select>
													<span style="color:red" >*</span>
												</td>
											-->
											<input class="defined-input" type="text" ng-click="showQueryModel()" class="input-medium search-query" placeholder="点我搜搜更懂您!"/>
											</tr>
										</table>
									</form>			
								</div>
							</div>
							<div class="row-fluid">
								<div class="span12" style="margin-left:50px;">
									<div>
										<h4> 基本信息</h4>
									</div>
									<div class="defind-div">
										<div class="box-content pull-left">
											<span>所属物业公司：{{propertyCompany.label}}</span>
											<span>所属小区：{{community.label}}</span>
											<span ng-show="showBuilding">所属楼栋：{{building.label}}</span>
											<span ng-show="showFloor">所属楼层：{{floor.floorId}}</span>
										</div>
									</div>
								</div>
							</div>
							<div class="box-content">
								<form id="communityform" method="post"
									class="form-horizontal form-validate" name="myForm1" novalidate>
									<table class="table table-hover" id="acctItemTypes">
										<tr class="row-fluid accItemRelRow" id="acctItemRel{{acctItemRel.acciItemRelId}}" ng-repeat="acctItemRel in acctItemRels">
											<td width="20%">
												<label class="defind-label">费用大类</label>
												<select ng-model="acctItemRel.acctItemTypeId" style="width: 50%" 
								               	ng-change="queryAcctItemType(acctItemRel.acctItemTypeId,{{acctItemRel.acciItemRelId}})"
								               	ng-options="acctItemRel.acctItemTypeId as 
												acctItemRel.acctTypeName for acctItemRel in acctItemTypeUps{{acctItemRel.acciItemRelId}}" required></select>
												<span style="color: red">*</span>
											</td>
											<td  width="20%">
												<label class="defind-label">费用细类</label>
												<select style="width: 50%"
												ng-model="acctItemRel.childAcctItemTypeId"
												ng-options="acctItemRel.acctItemTypeId as
												acctItemRel.acctTypeName for acctItemRel in acctItemTypes{{acctItemRel.acciItemRelId}}"
												required></select> 
												<span style="color:red">*</span>
											</td>
											<td width="20%">
												<label class="defind-label">收费标准</label>
													<input type="number" step="any" id="price"  ng-model="acctItemRel.price" style="width: 30%" required>
												<label class="defind-label"  id="unit{{acctItemRel.acciItemRelId}}"></label>
													<span style="color:red">*</span>
											</td>
											<td width="20%">
												<label class="defind-label">计算方法</label>
													<select  style="width: 50%" id="acctItemTypeName" 
													ng-model="acctItemRel.attrValue" 
													ng-options="acctItemRel.attrValue as
													acctItemRel.attrValueName for acctItemRel in caculateMethods"
													></select>  
											</td>
											<td>
												<a class="btn"  ng-click="addAcctItemRel()" >
														<i class="icon-plus" ></i>新增
													</a>
													<a class="btn"  ng-click='removeAcctItemRel(acctItemRel)'>
														<i class="icon-minus"></i>删除
													</a>
											</td>
										</tr>
									</table>
									<div class="controls">
											<input id="submit_btn" ng-disabled="!myForm1.$valid" type="button" class="btn btn-primary"
											ng-click="onSave()"
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
<div class="modal fade hide" id="queryModal" tabindex="-1" role="dialog"
	   aria-labelledby="myModalLabel" aria-hidden="true">
	   <div class="modal-dialog">
	      <div class="modal-content">
	         <div class="modal-header">
	   		<form class="form-horizontal" name="myForm" novalidate>
				<div class="form-group">
					<label class="col-sm-2 control-label">定位条件:</label>
					<select class="col-sm-10" ng-model="selectValue"
					ng-change="show(selectValue)"
					ng-options="item.id as item.name for item in data"></select>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">物业公司</label>
					<input class="col-sm-10" type="text" id="temp_pp_company" class="input-medium  search-query" placeholder="输入择物业公司"
					ng-model="propertyCompany.value"
					ng-change="resetCommpanyChild()"
					ui-event="{autocompletecreate:'changeClass(propertyCompanys)'}"
					ui-autocomplete="propertyCompanys" required>
					<span style="color:red" >*</span> 
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">小区</label>
					<input  class="col-sm-10" type="text" id="temp_community" class="input-medium search-query" placeholder="输入择小区"
					ng-model="community.companyName"
					ng-change="resetBuildingChild()"
					ui-event="{autocompletecreate:'changeClass(communitys)'}"
					ui-autocomplete="communitys" required>
					<span style="color:red" >*</span>
				</div>
				<div class="form-group" ng-show="showBuilding" >
					<label class="col-sm-2 control-label">楼栋/单元</label>
					<input class="col-sm-10" type="text" id="buillding" class="input-medium search-query" placeholder="输入择楼栋/单元"
					ng-model="building.buildingName"
					ui-event="{autocompletecreate:'changeClass(buildings)'}"
					ui-autocomplete="buildings" required>
					<span style="color:red" >*</span>
				</div>
				<div class="form-group" ng-show="showFloor" >
					<label class="col-sm-2 control-label">楼层</label>
					<select class="col-sm-10" ng-model="floor.floorId" class="input-medium search-query"
					ng-options="floor.floorId as floor.floorName  for floor in floors"
					></select>
					<span style="color:red" >*</span>
				</div>
			</form>
	  		</div>
	      </div>
	   </div>
</div>
</body>
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/jquery-ui.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/angularJS/angular-ie8-1.4.7.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/json2.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/defined-checkbox.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.myPagination.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/component/message-dialog-comp/js/message-dialog.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/common/common.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/costsetting/costsetting-service.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/jsp/mobile/property/property-service.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/selectSearchCom.js'></c:url>"></script>
<%--<script type="text/javascript" src="<c:url value='/js/costsetting/acctItemRelAdd.js'></c:url>"></script>--%>
<script type="text/javascript" src="<c:url value='/js/costsetting/add-acctitemrel-manager-controller.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/common/DirectiveUtil.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/costsetting/autocomplete.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/costsetting/event.js'></c:url>"></script>
</html>