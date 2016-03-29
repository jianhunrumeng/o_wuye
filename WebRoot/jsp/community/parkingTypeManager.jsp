<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    	String communityId = request.getParameter("communityId");
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
<script type="text/javascript" src="<c:url value='/js/modal.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/community/parkingTypeManager.js'></c:url>"></script>
<style type="text/css">
.defined-role-btn {
	margin-left: 5px;
}
</style>
<title>车位类型管理-用户管理</title>
</head>
<body>
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span4">
              <h3><i class="fa fa-key"></i>车位类型管理</h3>
            </div>
            <div class="span8">
              <form id="form1" name="form1">
                <div class="form-horizontal pull-right">
                  <input type="hidden" id="hidden_community_id" name="hidden_community_id" value="<%=communityId%>">
                  <input id="keyword-input" name="keywords" class="input-medium" placeholder="请输入关键词" type="text">
                  <input type="button" class="btn" id="search" onclick="loadRoleList()" value="查询">
                </div>
              </form>
            </div>
          </div>
          <div class="box-content">
            <div class="row-fluid">
              <div class="span12 control-group">
                <div> <a class="btn" href="javascript:addParkTypeModel(null);"  data-toggle="modal" class="tile tile-themed"><i class="icon-plus"></i>新增</a> 
                 <a class="btn" href="javascript:removeAllParkType();"><i class="icon-remove"></i>删除</a> 
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
                  	<th class="span3">车位类型</th>
                  	<th class="span3">收费标准（元/月）</th>
                  	<th class="span5">备注</th>
                    <th class="span1">操作</th>
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
<div id="myModal1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<form id="roleForm" style="margin-left:0" class="form-horizontal form-validate form-modal" novalidate="novalidate">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="role-model-title"><i class="fa fa-key"></i>新增车位类型</h4>
				</div>
				<div class="modal-body">
					<div class="control-group">
						<input type="hidden" id="hidden_party_type_id" value="0"/>
						<label class="control-label" for="price">车位类型</label>
						<div class="controls">
							<input type="text" placeholder="请输入车位类型" name="park_type_name" id="park_type_name" class="input-medium"/>
							<span id="tip_pt_name" class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="role_status">收费标准</label>
						<div class="controls">
							<input type="text" placeholder="请输入费用标准" name="park_price" id="park_price" class="input-medium"/>
							<span id="tip_fy" class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="role_status">备注</label>
						<div class="controls">
							<textarea rows="3"
							 name="park_remark" id="park_remark"  ></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onClick="saveParkType()">提交</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="fallr-overlay"> </div>
</body>
</html>