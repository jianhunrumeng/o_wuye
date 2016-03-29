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
<script type="text/javascript" src="<c:url value='/js/parking/parkingManager.js'></c:url>"></script>
<style type="text/css">
.defined-role-btn {
	margin-left: 5px;
}
</style>
<title>车位管理-用户管理</title>
</head>
<body>
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span4">
              <h3><i class="fa fa-key"></i>车位管理</h3>
            </div>
            <div class="span8">
              <form id="form1" name="form1">
                <div class="form-horizontal pull-right">
                   <select name="type" id="search-type"
						class="input-small" style="width: 100px">
						<option selected="selected" value="parking-nbr">车位编码</option>
						<option value="parking-status">车位状态</option>
						<option value="community">小区</option>
				  </select>
                  <input id="keyword-input" name="keywords" class="input-medium" placeholder="请输入关键词" type="text">
                  <select name="type" id="parking-status-value"
						class="input-small" style="width: 168px; display: none;">
						<option selected="selected" value="0">请选择</option>
						<option value="1100">已被用</option>
						<option value="1000">未使用</option>
				  </select>
                  <input type="button" class="btn" id="search" onclick="loadRoleList()" value="查询">
                </div>
              </form>
            </div>
          </div>
          <div class="box-content">
            <div class="row-fluid">
              <div class="span12 control-group">
                <div> <a class="btn" href="addParkingInfo.jsp;"  data-toggle="modal" class="tile tile-themed"><i class="icon-plus"></i>新增车位</a> 
                 <a class="btn" href="javascript:removeAllParking();"><i class="icon-remove"></i>删除车位</a> 
                 <a class="btn" href="javascript:location.reload()"><i class="icon-refresh"></i>刷新</a>
                  </div>
              </div>
            </div>
            <div class="row-fluid dataTables_wrapper">
              <table id="listTable" class="table table-bordered table-hover dataTable table-striped ajax-checkbox">
                <thead>
                  <tr>
                  	<th class="with-checkbox"><input class="check_all"
												type="checkbox"></th>
                  	<th >车位编号</th>
                  	<th >位置</th>
                  	<th >车位类型</th>
                  	<th >车牌号</th>
                  	<th >所属房间</th>
                  	<th >状态</th>
                    <th class="span2">操作</th>
                  </tr>
                </thead>
                <tbody>
                    <tr>
                    	<td></td>
                    	<td>C001</td>
                    	<td>东区1排15号</td>
                    	<td>普通</td>
                    	<td>琼ILY1314</td>
                    	<td>世贸雅苑/A栋24E</td>
                    	<td>未使用</td>
                    	<td><a href="addParkingInfo.jsp?parkingId=1" class="btn">修改</a><a class="btn">详情</a></td>
                    </tr>	
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