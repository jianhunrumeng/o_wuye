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
<jsp:include page="/jsp/public/head.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/js/role/rolePrivilegeDetail.js'></c:url>"></script>
<title>物业管理-角色管理</title>
<style type="text/css">
.rpd-detail {
	margin: 5px;
	padding: 5px;
	height: 30px;
	line-height: 30px;
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
            <div class="span10">
              <h3>角色详情</h3>
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
				 		<label class="control-label" for="role_status">状态：<span id="xs_role_status"></span></label>
				 		<div class="controls">
							
						</div>
					</div>
			 	</div>
			 	<div class="defined-title">
			 		<span>权限信息</span>
			 	</div>
			 	<div class="box-content">
			 		<div id="role_privilege_detail">
			 		<span class="rpd-detail">系统管理</span><span class="rpd-detail">用户管理</span><span class="rpd-detail">物业公司管理</span>
			 		</div>
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