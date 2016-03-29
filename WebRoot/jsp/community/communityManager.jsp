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
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-responsive.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/todc_bootstrap.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/themes.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/font/css/font-awesome.css'></c:url>" media="all" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/inside.css'></c:url>" media="all">
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/defined-checkbox.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/jquery.myPagination.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/community/communityList.js'></c:url>"></script>
<title>物业管理-小区设置</title>
</head>
<body onload="loadRoleList()">
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span4">
              <h3><i class="fa fa-key"></i>小区设置</h3>
            </div>
            <div class="span8">
              <form id="form1" name="form1">
                <div class="form-horizontal pull-right">
                  <input id="keyword-input" name="keywords" class="input-medium" placeholder="请输入小区名称" type="text">
                  <input type="button" class="btn" id="search" onclick="loadRoleList()" value="查询">
                </div>
              </form>
            </div>
          </div>
          <div class="box-content">
            <div class="row-fluid">
              <div class="span12 control-group">
                <div> <a class="btn" href="addCommunityInfo.jsp"><i class="icon-plus"></i>新增小区</a> 
                 <a class="btn" href="javascript:removeCommunity()"><i class="icon-remove"></i>删除小区</a> 
                <a class="btn" href="javascript:location.reload()"><i class="icon-refresh"></i>刷新</a> </div>
              </div>
            </div>
            <div class="row-fluid dataTables_wrapper">
              <table id="listTable" class="table table-bordered table-hover dataTable table-striped ajax-checkbox" 
              ajax-url="/Wemedical/Diagnosis" ajax-length="0">
                <thead>
                  <tr>
                  	<th class="with-checkbox"><input class="check_all" id="check_all"
												type="checkbox"></th>
					<th >物业公司名称</th>
					<th >小区名称</th>
					<th >小区电话</th>
					<th >负责人</th>
					<th >负责人电话</th>
					<th >状态</th>
					<th class="span3">操作</th>
                  </tr>
                </thead>
                <tbody>
                	
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
<div id="myModal1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
<div id="fallr-overlay"> </div>
</body>
</html>