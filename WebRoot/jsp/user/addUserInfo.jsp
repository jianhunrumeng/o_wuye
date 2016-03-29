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
<jsp:include page="/jsp/public/head.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/js/user/userManager.js'></c:url>"></script>
<title>物业管理-用户管理</title>
</head>
<body>
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span10">
              <h3>新增用户</h3>
            </div>
            <div class="span2">
				<a class="btn pull-right" href="Javascript:window.history.go(-1)">返回</a>
			</div>
          </div>
          <div class="box-content">
             <form id="propertyCompanyform" method="post" class="form-horizontal form-validate" novalidate >
         	 	<div class="defined-title">
			 		<span>基本信息</span>
			 	</div>
			 	<div class="box-content">
			 		<div class="span6">
				 		<div class="control-group">
					 		<label class="control-label" for="user_account">账号（手机号）</label>
					 		<div class="controls">
								<input type="text" id="user_account" name="user_account" >
								<span class="maroon">*</span>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="user_name">姓名</label>
					 		<div class="controls">
								<input type="text" id="user_name" name="user_name" >
								<span class="maroon">*</span>
							</div>
						</div>
						
						<div class="control-group">
					 		<label class="control-label" for="user_cert_type">证件类型</label>
					 		<div class="controls">
								<select name="type" id="user_cert_type" class="defined-select">
									<option selected="selected" value="10">身份证</option>
									<option value="11">营业执照</option>
							  	</select>
								<span class="maroon">*</span>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="user_cert_nbr">证件号码</label>
					 		<div class="controls">
								<input type="text" id="user_cert_nbr" name="user_cert_nbr" >
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="user_status">有效标志</label>
					 		<div class="controls">
								<select name="type" id="user_status" class="defined-select">
									<option selected="selected" value="1000">生效</option>
									<option value="1100">失效</option>
							  	</select>
								<span class="maroon">*</span>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="user_type">用户类型</label>
					 		<div class="controls">
								<select name="type" id="user_type" class="defined-select">
									<option selected="selected" value="">请选择</option>
									<option value="10">系统管理员</option>
									<option value="11">物业用户</option>
							  	</select>
								<span class="maroon">*</span>
							</div>
						</div>
				 	</div>
				 	<div class="span6">
						<div class="control-group">
					 		<label class="control-label" for="user_qq">QQ</label>
					 		<div class="controls">
								<input type="text" id="user_qq" name="user_qq" >
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="user_email">Email</label>
					 		<div class="controls">
								<input type="text" id="user_email" name="user_email" >
							</div>
						</div>
						
						<div class="control-group">
					 		<label class="control-label" for="user_wx">微信</label>
					 		<div class="controls">
								<input type="text" id="user_wx" name="user_wx" >
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="user_area">地区</label>
					 		<div class="controls">
								<select name="type" id="area_province"  style="width: 100px;">
									<option selected="selected" value="10">北京</option>
							  	</select>
							  	<select name="type" id="area_city" style="width: 100px;">
									
							  	</select>
							  	<select name="type" id="area_area" style="width: 100px;">
									<option selected="selected" value="110109">门头沟区</option>
							  	</select>
								<span class="maroon">*</span>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="user_address">地址详情</label>
					 		<div class="controls">
								<input type="text" id="user_address" name="user_address" style="width: 290px;">
								<span class="maroon">*</span>
							</div>
						</div>
				 	</div>
			 	</div>
			 	<div id="organization">
			 		<div class="defined-title">
				 		<span>单位信息</span>
				 	</div>
				 	<div class="box-content">
				 		<div class="span6">
				 			<div class="control-group pc-parent-div">
						 		<label class="control-label" for="property_company_name">物业公司</label>
						 		<div class="controls">
									<input type="text" id="property_company_name" name="property_company_name"  placeholder="请搜索物业公司名称" data-id="">
									<span class="maroon">*</span>
								</div>
							</div>
				 		</div>
				 		<div class="span6">
				 			<div class="control-group">
						 		<label class="control-label" for="community_name">小区</label>
						 		<div class="controls">
									<input type="text" id="community_name" name="community_name"  placeholder="请搜索小区名称" data-id="">
								</div>
							</div>
				 		</div>
				 	</div>
			 	</div>
			 	<div class="defined-title">
			 		<span>角色信息</span>
			 	</div>
			 	<div class="box-content">
			 		<div class="span1"></div>
			 		<div class="span11">
			 			<c:forEach items="${sessionScope.roleList}" var="role">
			 				<label class="checkbox inline"><input type="checkbox" value="${role.roleId }" name="check">${role.roleName }</label>
			 			</c:forEach>
			 		</div>
			 	</div>
			 	<div class="control-group">
					<div class="controls">
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