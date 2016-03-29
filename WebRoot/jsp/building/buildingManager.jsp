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
<script type="text/javascript" src="<c:url value='/js/building/buildingList.js'></c:url>"></script>
<title>物业管理-楼栋单元管理</title>
</head>
<body onload="loadRoleList()">
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span4">
              <h3><i class="fa fa-key"></i>楼栋单元管理</h3>
            </div>
            <div class="span8">
              <form id="form1" name="form1">
                <!-- <div class="form-horizontal pull-right">
                  <input id="keyword-input" name="keywords" class="input-medium" placeholder="请输入楼栋/单元名称" type="text">
                  <input type="button" class="btn" id="search" onclick="loadRoleList()" value="查询">
                </div> -->
              </form>
            </div>
          </div>
          <div class="box-content">
            <div class="row-fluid">
              <div class="span12 control-group">
                <div> <a class="btn" href="addBuildingInfo.jsp"><i class="icon-plus"></i>新增楼栋/单元</a> 
                 <a class="btn" href="javascript:removeBuilding()"><i class="icon-remove"></i>删除楼栋/单元</a> 
                <a class="btn" href="javascript:location.reload()"><i class="icon-refresh"></i>刷新</a> </div>
              </div>
            </div>
            <div class="row-fluid dataTables_wrapper">
              <table id="listTable" class="table table-bordered table-hover dataTable table-striped ajax-checkbox" 
              ajax-url="/Wemedical/Diagnosis" ajax-length="0">
                <thead>
                  <tr>
                  	<th class="with-checkbox"><input class="check_all" id="check_all"
												type="checkbox"></th>
					          <!-- <th class="span2">物业公司名称</th> -->
                  	<th >小区名称</th>
                  	<th >楼栋名称</th>
                  	<th >单元名称</th>
                  	<th >电话</th>
                  	<th >负责人</th>
                  	<th >负责人电话</th>
                  	<th >状态</th>
                    <th class="span2">操作</th>
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