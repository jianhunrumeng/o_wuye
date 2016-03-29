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

<title>公告管理</title>
</head>
<body ng-app="noticeManagerApp" ng-controller="noticeManagerController">
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span4">
              <h3><i class="fa fa-key"></i>公告管理</h3>
            </div>
            <div class="span8">
              <form id="form1" name="form1">
                <div class="form-horizontal pull-right">
					<select class="input-small" style="width: 100px" ng-model="communityId"
							ng-options="community.communityId as community.communityName for community in communitys">
						<option value="">选择小区...</option>
					</select>
					<select class="input-small" style="width: 100px" ng-model="noticeType" ng-options="noticeType.attrValue as noticeType.attrValueName for noticeType in noticeTypes">
						<option value="">选择类型...</option>
					</select>
                  	<input type="button" class="btn" id="search" ng-click="queryNotice()" value="查询">
                </div>
              </form>
            </div>
          </div>
          <div class="box-content">
            <div class="row-fluid">
              <div class="span12 control-group">
                <div> 
                 <a class="btn" href="javascript:removeNotice()"><i class="icon-remove"></i>删除</a> </div>
              </div>
            </div>
            <div class="row-fluid dataTables_wrapper">
              <table id="listTable" class="table table-bordered table-hover dataTable table-striped ajax-checkbox" 
              ajax-url="/Wemedical/Diagnosis" ajax-length="0">
                <thead>
                  <tr>
                  	<th class="with-checkbox"><input class="check_all" id="check_all" type="checkbox"></th>
					<th >通知类型</th>
					<th >主题</th>
					<th >发送对象</th>
					<th >创建人</th>
					<th >创建时间</th>
					<th >状态</th>
					<th class="span3">操作</th>
                  </tr>
                </thead>
                <tbody>
                	<tr ng-repeat="notice in notices">
		           		<td class="with-checkbox"><input type="checkbox" name="check" value="{{notice}}" /></td>
						<td>{{ notice.noticeType }}</td>
						<td>{{ notice.title }}</td>
						<td>{{ notice.classId }}</td>
						<td>{{ notice.createStaff }}</td>
						<td>{{ notice.createDate }}</td>
						<td>{{ notice.statusCd }}</td>
						<td>
							<a href="" class="btn defined-communityId-btn">查看</a>
							<a href="" class="btn defined-communityId-btn">修改</a>
						</td>
		           </tr>
                </tbody>
              </table>
              <div class="sabrosus" id="demo"></div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="roleForm" style="margin-left:0" class="form-horizontal form-validate form-modal" novalidate="novalidate">
				<input type="hidden" id="aid" name="aid" value="89929">
				<input type="hidden" id="urls" name="urls" value="/Newmembertrade/Index?aid=89929">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title"><i class="fa fa-key"></i>角色</h4>
				</div>
				<div class="modal-body">
					<div class="control-group">
						<label class="control-label" for="price">角色名称</label>
						<div class="controls">
							<input type="text" placeholder="请输入角色名称" name="role_name" id="role_name" class="input-medium" data-rule-required="true">
							<span id="tip_role_name" class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="role_status">状态</label>
						<div class="controls">
							<select name="type" id="role_status" style="width: 170px;">
								<option selected="selected" value="1000">生效</option>
								<option value="1100">失效</option>
						  	</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onClick="saveRoleInfo()">提交</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
			</form>
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
<script type="text/javascript" src="<c:url value='/jsp/notice/notice-service.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/jsp/mobile/property/property-service.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/jsp/notice/notice-manager/notice-manager-controller.js'></c:url>"></script>
</body>
</html>