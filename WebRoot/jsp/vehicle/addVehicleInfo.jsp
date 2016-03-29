<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String vehicleId = request.getParameter("vehicleId");
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
<link rel="stylesheet" type="text/css" href="<c:url value='/css/defined-style.css'></c:url>" media="all">
<link rel="stylesheet" type="text/css" href="<c:url value='/css/public/jquery-ui.css'></c:url>" media="all">
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/jquery-ui.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/property-community-search.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/vehicle/vehicleManager.js'></c:url>"></script>
<title>车辆管理-角色管理</title>
</head>
<body>
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span10">
              <h3><% if(vehicleId != null) {%>修改<% }else { %>新增<% } %>车辆</h3>
            </div>
            <div class="span2">
				<a class="btn pull-right" href="Javascript:window.history.go(-1)">返回</a>
			</div>
          </div>
          <div class="box-content">
             <form id="parkingform" method="post" class="form-horizontal form-validate" novalidate >
         	 	<div class="defined-title">
			 		<span>车辆信息</span>
			 		<input type="hidden" id="hidden_vehicle_id" value="<%=vehicleId %>">
			 	</div>
			 	<div class="box-content">
			 		<div class="control-group">
				 		<label class="control-label" for="plate_nbr">车牌号</label>
				 		<div class="controls">
							<input type="text" id="plate_nbr" name="plate_nbr" >
							<span class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
				 		<label class="control-label" for="property_company_name">物业公司</label>
				 		<div class="controls">
							<input type="text" id="property_company_name" name="property_company_name"  placeholder="请搜索物业公司名称" data-id="">
							<span class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
				 		<label class="control-label" for="community_name">小区</label>
				 		<div class="controls">
							<input type="text" id="community_name" name="community_name"  placeholder="请搜索小区名称" data-id="">
							<span class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
				 		<label class="control-label" for="building_name">楼栋/单元</label>
				 		<div class="controls">
							<input type="text" id="building_name" name="building_name"  placeholder="请搜索小区名称" data-id="">
							<span class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
				 		<label class="control-label" for="room">所属房间</label>
				 		<div class="controls">
							<input type="text" id="room" name="room"  placeholder="请搜索房间名称" data-id="">
							<span class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
				 		<label class="control-label" for="room">车位</label>
				 		<div class="controls">
							<select name="type" id="parking" class="defined-select">
									<option selected="selected" value="10">A</option>
									<option value="11">B</option>
							  	</select>
							<span class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<input id="submit_btn" type="button" class="btn btn-primary" onclick="saveVehicle()" value="提交">
							<input id="reset_btn" type="button" class="btn"  value="重置">
						</div>
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