<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String roomId = request.getParameter("roomId");
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
<script type="text/javascript" src="<c:url value='/js/public/defined-address.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/room/roomInfo.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/My97DatePicker/WdatePicker.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/strUtil.js'></c:url>"></script>


<title>物业管理-房间管理</title>
<style type="text/css">
	.defined-title {
		height: 40px;
		width:	100%;
		margin-left: 20px;
		border-bottom-width: 1px;
		border-bottom-color: blue;
	}
	.defined-title span {
		font-size: 16px;
	}
	.defined-select {
		width: 228px;
	}
	
</style>
</head>
<body  onload="P.xskn()">
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span10">
              <h3>新增房间信息</h3>
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
					 		<label class="control-label" for="roomNbr">房间号</label>
					 		<div class="controls">
					 			<p id="roomNbr" />
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="buildingTypeName">房屋类型</label>
					 		<div class="controls">
								<p id="buildingTypeName" name="buildingTypeName" />
							</div>
						</div>
						
						<div class="control-group">
					 		<label class="control-label" for="floor">所在楼层</label>
					 		<div class="controls">
								<p id="floor" name="floor" />
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="housingOrientation">房屋朝向</label>
					 		<div class="controls">
								<p id="housingOrientation" />
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="usingState">使用状态</label>
					 		<div class="controls">
								<p name="type" id="usingState" ></p>
							</div>
						</div>
				 	</div>
				 	<div class="span6">
				 		<div class="control-group">
					 		<label class="control-label" for="upgradeCondition">装修状态</label>
					 		<div class="controls">
								<p id="upgradeCondition" ></p>
							</div>
						</div>
						<%--<div class="control-group">
					 		<label class="control-label" for="pp_account_name">物业费收费标准</label>
					 		<div class="controls">
								<input type="text" id="pp_account_name" name="pp_account_name" >
								<span class="maroon">*</span>
							</div>
						</div>
						--%><div class="control-group">
					 		<label class="control-label" for="pp_account">物业费折扣率</label>
					 		<div class="controls">
								<p id="housingFeeRate" >
							</div>
						</div>
						
						<div class="control-group">
					 		<label class="control-label" for="pp_zip_code">装修起始日期</label>
					 		<div class="controls">
					 			<p  id="upgradeStartDate" />
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="upgradeEndDate">装修结束日期</label>
					 		<div class="controls">
					 			<p id="upgradeEndDate" />
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="housedDate">入住时间</label>
					 		<div class="controls">
					 			<p  id="housedDate" >
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="statuCd">是否有效</label>
					 		<div class="controls">
					 			<p id="statuCd" >
							  	</p>
							</div>
						</div>
				 	</div>
			 	</div>
			 	<div class="defined-title">
			 		<span>业主信息</span>
			 	</div>
			 	<div class="box-content">
			 		<div class="span6">
			 			<div class="control-group">
					 		<label class="control-label" for="partyName">姓名</label>
					 		<div class="controls">
								<p id="partyName" name="partyName" />
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="certType">证件类型</label>
					 		<div class="controls">
								<p id="certType" >
							  	</p>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="certNbr">证件号码</label>
					 		<div class="controls">
								<p  id="certNbr" />
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="userAccount">注册手机号</label>
					 		<div class="controls">
								<p id="userAccount" />
							</div>
						</div>
			 		</div>
			 		<div class="span6">
			 			<div class="control-group">
					 		<label class="control-label" for="mobile">联系电话</label>
					 		<div class="controls">
								<p id="mobile" />
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="homePhone">家庭电话</label>
					 		<div class="controls">
								<p id="homePhone" />
							</div>
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
<div><input type="hidden" value="<%=roomId %>" id="hidden_room_id" />
</div>
</body>
</html>