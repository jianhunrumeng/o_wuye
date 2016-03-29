<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		String companyId=(String)session.getAttribute("companyId");
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
<link rel="stylesheet" type="text/css" href="<c:url value='/css/public/jquery-ui.css'></c:url>" media="all">
<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/defined-address.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/room/roomEdit.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/My97DatePicker/WdatePicker.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/strUtil.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/jquery-ui.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/community-building-search.js'></c:url>"></script>


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
					 		<label class="control-label" for="community_name">小区</label>
					 		<div class="controls">
								<input type="text" id="community_name" name="community_name"  placeholder="请搜索小区名称" data-id="">
								<span class="maroon">*</span>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="building_name">楼栋</label>
					 		<div class="controls">
								<input type="text" id="building_name" name="building_name"  placeholder="请搜索楼栋名称" data-id="">
								<span class="maroon">*</span>
							</div>
						</div>
				 		<div class="control-group">
					 		<label class="control-label" for="roomNbr">房间号</label>
					 		<div class="controls">
								<input type="text" id="roomNbr" name="roomNbr" >
								<span class="maroon">*</span>
								<span id="roomNbrValidNull" style="display:none;" ></span>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="buildingTypeName">房屋类型</label>
					 		<div class="controls">
								<input type="text" id="buildingTypeName" name="buildingTypeName" >
							</div>
						</div>
						
						<div class="control-group">
					 		<label class="control-label" for="floor">所在楼层</label>
					 		<div class="controls">
								<input type="text" id="floor" name="floor" >
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="housingOrientation">房屋朝向</label>
					 		<div class="controls">
								<input type="text" id="housingOrientation" name="housingOrientation" >
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="usingState">使用状态</label>
					 		<div class="controls">
								<select name="type" id="usingState" class="defined-select">
									<option selected="selected" value="10">待租</option>
									<option value="10">待售</option>
									<option value="10">待租售</option>
									<option value="10">出租</option>
									<option value="10">自用</option>
									<option value="10">出售</option>
							  	</select>
							</div>
						</div>
				 	</div>
				 	<div class="span6">
				 		<div class="control-group">
					 		<label class="control-label" for="upgradeCondition">装修状态</label>
					 		<div class="controls">
								<select name="type" id="upgradeCondition" class="defined-select">
									<option selected="selected" value="10">已装修</option>
									<option value="11">在装修</option>
									<option value="12">毛胚</option>
									<option value="13">在建</option>
							  	</select>
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
								<input type="text" id="housingFeeRate" name="housingFeeRate" >
								<span id="housingFeeRateValid" class="maroon">*</span>
							</div>
						</div>
						
						<div class="control-group">
					 		<label class="control-label" for="pp_zip_code">装修起始日期</label>
					 		<div class="controls">
					 			<input type="text" id="upgradeStartDate" name="upgradeStartDate" class="Wdate"  onClick="WdatePicker()">
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="upgradeEndDate">装修结束日期</label>
					 		<div class="controls">
					 			<input type="text" id="upgradeEndDate" name="upgradeEndDate" class="Wdate"  onClick="WdatePicker()">
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="housedDate">入住时间</label>
					 		<div class="controls">
					 			<input type="text" id="housedDate" name="housedDate" class="Wdate"  onClick="WdatePicker()">
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="statuCd">是否有效</label>
					 		<div class="controls">
					 			<select name="type" id="statuCd" class="defined-select">
									<option selected="selected" value="100">在用</option>
									<option value="1100">失效</option>
							  	</select>
							  	<span class="maroon">*</span>
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
								<input type="text" id="partyName" name="partyName" >
								<span class="maroon">*</span>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="certType">证件类型</label>
					 		<div class="controls">
								<select name="type" id="certType" class="defined-select">
									<option selected="selected" value="10">身份证</option>
									<option value="11">营业执照</option>
							  	</select>
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="certNbr">证件号码</label>
					 		<div class="controls">
								<input type="text" id="certNbr" name="certNbr" >
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="userAccount">注册手机号</label>
					 		<div class="controls">
								<input type="text" id="userAccount" name="userAccount" >
								<span class="maroon">*</span>
							</div>
						</div>
			 		</div>
			 		<div class="span6">
			 			<div class="control-group">
					 		<label class="control-label" for="mobile">联系电话</label>
					 		<div class="controls">
								<input type="text" id="mobile" name="mobile" >
							</div>
						</div>
						<div class="control-group">
					 		<label class="control-label" for="homePhone">家庭电话</label>
					 		<div class="controls">
								<input type="text" id="homePhone" name="homePhone" >
							</div>
						</div>
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
<div>
<input type="hidden" id="property_company_name" name="property_company_name"  placeholder="请搜索物业公司名称" data-id="<%=companyId%>">
</div>
</body>
</html>