<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.net.*" %>
    <%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    	String roleId = request.getParameter("roleId");
    	String roleName = request.getParameter("roleName");
    	if (roleName != null) {
    		roleName = URLDecoder.decode(request.getParameter("roleName"),"utf-8");
    	}
    	String statusCd = request.getParameter("statusCd");
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/jsp/public/head.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/js/role/roleInfoEdit.js'></c:url>"></script>

<title>物业管理-角色管理</title>
</head>
<body>
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span10">
              <h3>新增角色</h3>
            </div>
            <div class="span2">
				<a class="btn pull-right" href="Javascript:window.history.go(-1)">返回</a>
			</div>
          </div>
          <div class="box-content">
             <form id="roleform" method="post" class="form-horizontal form-validate" novalidate >
         	 	<div class="defined-title">
			 		<span>基本信息</span>
			 		<input type="hidden" value="<%=roleId%>" id="hidden_role_id" />
			 		<input type="hidden" value="<%=roleName%>" id="hidden_role_name" />
			 		<input type="hidden" value="<%=statusCd%>" id="hidden_role_statusCd" />
					</div>
			 	<div class="box-content">
			 		<div class="span6">
				 		<div class="control-group">
					 		<label class="control-label" for="role_name">角色名称</label>
					 		<div class="controls">
								<input type="text" id="role_name" name="role_name" >
								<span class="maroon">*</span>
							</div>
						</div>
					</div>
					<div class="span6">
						<div class="control-group">
					 		<label class="control-label" for="role_status">状态</label>
					 		<div class="controls">
								<select name="type" id="role_status" class="defined-select">
										<option selected="selected" value="1000">生效</option>
										<option value="1100">失效</option>
								  	</select>
								<span class="maroon">*</span>
							</div>
						</div>
					</div>
			 	</div>
			 	<div class="defined-title">
			 		<span>权限信息</span>
			 	</div>
			 	<div class="control-group">
			 	<div class="zTreeDemoBackground left" style="border-style:solid; border-width:1px; border-color:#000">
			 		<ul id="cityTree" class="ztree"></ul>
			 	</div>
			 	</div>
			 	<div class="control-group">
					<div class="controls" style="text-align:center;">
						<input id="submit_btn" type="button" class="btn btn-primary" value="提交">
						<input id="reset_btn" type="button" class="btn"  value="重置">
					</div>
				</div>
         	 </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div id="fallr-overlay"> </div>
</body>
</html>