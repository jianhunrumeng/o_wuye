<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String parkingId = request.getParameter("parkingId");
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
<script type="text/javascript" src="<c:url value='/js/public/property-community-search.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/parking/editParking.js'></c:url>"></script>
<title>车位管理-角色管理</title>
</head>
<body>
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span10">
             <h3><% if(parkingId != null) {%>修改<% }else { %>新增<% } %>车位</h3>
            </div>
            <div class="span2">
				<a class="btn pull-right" href="Javascript:window.history.go(-1)">返回</a>
			</div>
          </div>
          <div class="box-content">
             <form id="parkingform" method="post" class="form-horizontal form-validate" novalidate >
         	 	<div class="defined-title">
			 		<span>车位信息</span>
			 		<input type="hidden" id="hidden_parking_id" value="<%=parkingId%>">
			 	</div>
			 	<div class="box-content">
			 		<div class="control-group">
				 		<label class="control-label" for="parking_nbr">车位编码</label>
				 		<div class="controls">
							<input type="text" id="parking_nbr" name="parking_nbr" >
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
							<input type="text" id="building_name" name="building_name"  placeholder="请搜索楼栋名称" data-id="">
							<!-- <span class="maroon">*</span> -->
						</div>
					</div>
					<div class="control-group">
				 		<label class="control-label" for="room">房间</label>
				 		<div class="controls">
							<input type="text" id="room" name="room"  placeholder="请搜索房间名称" data-id="">
							<!-- <span class="maroon">*</span> -->
						</div>
					</div>
					<div class="control-group">
				 		<label class="control-label" for="parking_type">车位类型</label>
				 		<div class="controls">
							<select name="type" id="parking_type" class="defined-select">
									<option selected="selected" value="10">A</option>
									<option value="11">B</option>
							  	</select>
							<span class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
				 		<label class="control-label" for="parking_position">位置</label>
				 		<div class="controls">
							<input type="text" id="parking_position" name="parking_position" >
							<!-- <span class="maroon">*</span> -->
						</div>
					</div>
					<div class="control-group">
				 		<label class="control-label" for="plate_nbr">车牌号</label>
				 		<div class="controls">
							<input type="text" id="plate_nbr" name="plate_nbr" >
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<input id="submit_btn" type="button" class="btn btn-primary" onclick="saveParking()" value="提交">
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