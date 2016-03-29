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
<script type="text/javascript" src="<c:url value='/js/role/roleInfoList.js'></c:url>"></script>
<style type="text/css">
.defined-role-btn {
	margin-left: 5px;
}
</style>
<title>物业管理-用户管理</title>
</head>
<body onload="loadRoleList()">
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span4">
              <h3><i class="fa fa-key"></i>角色管理</h3>
            </div>
            <div class="span8">
              <form id="form1" name="form1">
                <div class="form-horizontal pull-right">
                  <input id="keyword-input" name="keywords" class="input-medium" placeholder="请输入关键词" type="text">
                  <input type="button" class="btn" id="search" onclick="loadRoleList()" value="查询">
                </div>
              </form>
            </div>
          </div>
          <div class="box-content">
            <div class="row-fluid">
              <div class="span12 control-group">
                <div> <a class="btn" href="addRoleInfo.jsp" ><i class="icon-plus"></i>新增角色</a> 
                 <a class="btn" href="javascript:removeAllRole();"><i class="icon-remove"></i>删除角色</a> 
                <a class="btn" href="javascript:location.reload()"><i class="icon-refresh"></i>刷新</a> </div>
              </div>
            </div>
            <div class="row-fluid dataTables_wrapper">
              <table id="listTable" class="table table-bordered table-hover dataTable table-striped ajax-checkbox">
                <thead>
                  <tr>
                  	<th class="with-checkbox"><input class="check_all"
												type="checkbox"></th>
                  	<th class="span2"> 角色名称</th>
                  	<th class="span2">创建时间</th>
                  	<th class="span2">修改时间</th>
                  	<th class="span2">状态</th>
                    <th class="span2"> 操作</th>
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
					<h4 class="modal-title" id="role-model-title"><i class="fa fa-key"></i>新增角色</h4>
				</div>
				<div class="modal-body">
					<div class="control-group">
						<input type="hidden" id="hidden_role_id" value="0"/>
						<label class="control-label" for="price">角色名称</label>
						<div class="controls">
							<input type="text" placeholder="请输入角色名称" name="role_name" id="role_name" class="input-medium" data-rule-required="true">
							<span id="tip_role_name" class="maroon">*</span>
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="role_status">状态</label>
						<div class="controls">
							<select name="type" id="role_status" style="width: 170px;">
								<option value="1000">生效</option>
								<option value="1100">失效</option>
						  	</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" onClick="saveRoleInfo()">提交</button>
					<button type="button" class="btn btn-default" data-dismiss="modal" aria-hidden="true">取消</button>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="fallr-overlay"> </div>
</body>
</html>