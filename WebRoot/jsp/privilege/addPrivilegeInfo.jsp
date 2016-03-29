<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String privilegeId = request.getParameter("privilegeId");
    %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/jsp/public/head.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/js/privilege/privilegeEdit.js'></c:url>"></script>

<title>物业管理-权限管理</title>
</head>
<body>
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span10">
              <h3>新增权限</h3>
            </div>
            <div class="span2">
				<a class="btn pull-right" href="Javascript:window.history.go(-1)">返回</a>
			</div>
          </div>
          <div class="box-content">
             <form id="privilegeform" method="post" class="form-horizontal form-validate" novalidate >
         	 	<div class="defined-title">
			 		<span>基本信息</span>
			 		<input type="hidden" value="<%=privilegeId%>" id="hidden_privilege_id" />
					</div>
			 	<div class="box-content">
			 		<div class="span6">
						<div class="control-group">
					 		<label class="control-label" for="pri_name">权限名称</label>
					 		<div class="controls">
								<input type="text" id="pri_name" name="pri_name" >
								<span class="maroon">*</span>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="pri_path">权限路径</label>
					 		<div class="controls">
								<input type="text" id="pri_path" name="pri_path" >
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="pri_type">权限类型</label>
					 		<div class="controls">
								<select name="type" id="pri_type" class="defined-select">
									<option selected="selected" value="101">菜单权限</option>
									<option value="102">功能权限</option>
							  	</select>
								<span class="maroon">*</span>
							</div>
						</div>
					</div>
			 		<div class="span6">
				 		<div class="control-group">
					 		<label class="control-label" for="pri_status">状态</label>
					 		<div class="controls">
								<select name="type" id="pri_status" class="defined-select">
									<option selected="selected" value="1000">生效</option>
									<option value="1100">失效</option>
							  	</select>
								<span class="maroon">*</span>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="pri_parent">上级权限</label>
					 		<div class="controls">
								<select name="type" id="pri_parent" class="defined-select">
							  	</select>
								<span class="maroon">*</span>
							</div>
						</div>
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