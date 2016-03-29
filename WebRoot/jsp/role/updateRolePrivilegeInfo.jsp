<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String roleId = request.getParameter("roleId");
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
<script type="text/javascript" src="<c:url value='/js/role/rolePrivilegeInfoEdit.js'></c:url>"></script>
<style type="text/css">
.defined-role-btn {
	margin-left: 5px;
}
</style>
<title>物业管理-角色权限管理</title>
</head>
<body>
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span10">
              <h3><i class="fa fa-key"></i>角色权限管理</h3>
            </div>
            <div class="span2">
				<a class="btn pull-right" href="Javascript:window.history.go(-1)">返回</a>
			</div>
          </div>
          <div class="box-content">
          	<div class="defined-title">
		 		<span>角色信息</span>
		 	 </div>
		   <div class="box-content">
			 		<div class="control-group">
				 		<label class="control-label" for="role_name">角色名称：<span id="xs_role_name"></span></label>
				 		<input type="hidden" value="<%=roleId %>" id="hidden_role_id" />
				 		<div class="controls">
							
						</div>
					</div>
					<div class="control-group">
		 	 		<label class="control-label" for="role_name">状态：<span id="xs_role_status">有效</span></label>
			 		<div class="controls">
						
					</div>
		 	 	</div>
			 	</div>
          </div>
          <div class="box-content">
            <div class="row-fluid">
              <div class="span6 control-group">
                <div> <a class="btn" href="javascript:allJoinInRole();"><i class="icon-plus"></i>加入角色</a> 
                 <a class="btn" href="javascript:allQuitRole();"><i class="icon-remove"></i>退出角色</a> 
                 <a class="btn" href="javascript:location.reload()"><i class="icon-refresh"></i>刷新</a> </div>
              </div>
              <div class="span6 control-group">
              <form id="form1" name="form1">
                <div class="form-horizontal pull-right">
                  <input id="keyword-input" name="keywords" class="input-medium" placeholder="请输入关键词" type="text">
                  <input type="button" class="btn" id="search" onclick="loadPrivilegeList()" value="查询">
                </div>
              </form>
            </div>
            </div>
            <div class="row-fluid dataTables_wrapper">
              <table id="listTable" class="table table-bordered table-hover dataTable table-striped ajax-checkbox" ajax-url="/Wemedical/Diagnosis" ajax-length="0">
                <thead>
                  <tr>
                  	<th class="with-checkbox"><input class="check_all"
												type="checkbox"></th>
                  	<th class="span10">角色名称</th>
                  	<th class="span1">状态</th>
                    <th class="span1"> 操作</th>
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
<div id="fallr-overlay"> </div>
</body>
</html>