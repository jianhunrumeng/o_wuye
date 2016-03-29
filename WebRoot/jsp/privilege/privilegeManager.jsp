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
<script type="text/javascript" src="<c:url value='/js/privilege/privilegeManager.js'></c:url>"></script>
<title>物业管理-权限管理</title>
</head>
<body onload="qryPrivilegeList()">
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span4">
              <h3><i class="fa fa-suitcase"></i>权限管理</h3>
            </div>
            <div class="span8">
              <form id="form1" name="form1">
                <div class="form-horizontal pull-right">
                  <input id="n-keyword-input" name="n-keywords" class="input-medium" placeholder="请输入关键词" type="text">
                      <!--<input id="property_company_name" name="property_company_name" class="input-medium" placeholder="请搜索物业公司名称" type="text" style="display: none;" data-id="">
					  -->
				  <input type="button" class="btn" id="search" onclick="qryPrivilegeList()" value="查询">
                </div>
              </form>
            </div>
          </div>
          <div class="box-content">
            <div class="row-fluid">
              <div class="span12 control-group">
                <div class="span7"> <a class="btn" href="addPrivilegeInfo.jsp"><i class="icon-plus"></i>新增权限</a> 
                <a class="btn" href="javascript:removeAllPri();"><i class="icon-remove"></i>删除权限</a> 
                <a class="btn" href="javascript:location.reload()"><i class="icon-refresh"></i>刷新</a> </div>
              </div>
            </div>
            <div class="row-fluid dataTables_wrapper">
              <table id="listTable" class="table table-bordered table-hover dataTable table-striped ajax-checkbox" ajax-url="/Wemedical/Diagnosis" ajax-length="0">
                <thead>
                  <tr>
                  	<th class="with-checkbox">
                  	<input class="check_all" type="checkbox"></th>
                  	<th class="span2"> 名称</th>
                    <th class="span2"> 权限路径</th>
                    <th class="span2"> 上级权限</th>
                    <th class="span2"> 权限类型</th>
                    <th class="span2"> 状态</th>
                    <th class="span2"> 创建时间</th>
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
</div>
<div id="fallr-overlay"></div>
	</body>
</html>