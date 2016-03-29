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
<script type="text/javascript" src="<c:url value='/js/public/StrUtil.js'></c:url>"></script>

<title>物业管理-用户管理</title>
</head>
<body onload="qryUser();">
<div id="main">
  <div class="container-fluid">
    <div class="row-fluid">
      <div class="span12">
        <div class="box">
          <div class="box-title">
            <div class="span4">
              <h3><i class="fa fa-suitcase"></i>用户管理</h3>
            </div>
            <div class="span8">
              <form id="form1" name="form1">
                <div class="form-horizontal pull-right">
                  <select name="type" id="search-type"
						class="input-small" style="width: 100px">
						<option selected="selected" value="all">===请选择===</option>
						<option value="account">帐号</option>
						<option value="name">姓名</option>
						<option value="company">物业公司</option>
				  </select>
                  
                  	  <input id="n-keyword-input" name="n-keywords" class="input-medium" placeholder="请输入关键词" type="text">
                      <input id="property_company_name" name="property_company_name" class="input-medium" placeholder="请搜索物业公司名称" type="text" style="display: none;" data-id="">
					  <input type="button" class="btn" id="search" onclick="qryUser();" value="查询">
                </div>
              </form>
            </div>
          </div>
          <div class="box-content">
            <div class="row-fluid">
              <div class="span12 control-group">
                <div class="span7"> <a class="btn" href="addUserInfo.jsp"><i class="icon-plus"></i>新增用户</a> 
                <a class="btn" href="javascript:removeAllUser();"><i class="icon-plus"></i>删除用户</a>
                <a class="btn" href="javascript:location.reload()"><i class="icon-plus"></i>批量新增</a> 
                <a class="btn" href="javascript:location.reload()"><i class="icon-refresh"></i>刷新</a> </div>
              </div>
            </div>
            <div class="row-fluid dataTables_wrapper">
              <table id="listTable" class="table table-bordered table-hover dataTable table-striped ajax-checkbox" ajax-url="/Wemedical/Diagnosis" ajax-length="0">
                <thead>
                  <tr>
                  	<th class="with-checkbox"><input class="check_all"
												type="checkbox"></th>
                  	<th class="span2"> 帐号</th>
                    <th class="span2"> 姓名 </th>
                    <th class="span2"> 手机号码 </th>
                    <th class="span2"> 类型</th>
                    <th class="span2"> 物业公司 </th>
                    <th class="span1"> 状态</th>
                    <th class="span2"> 操作</th>
                  </tr>
                </thead>
                <tbody>
                	<!--<tr>
                		<td class="with-checkbox"><input type="checkbox" name="check" value="1" /></td>
                		<td>18417173358</td>
                		<td>张三</td>
                		<td>18417173358</td>
                		<td></td>
                		<td>田园物业</td>
                		<td>生效</td>
                		<td><input type="button" value="修改" class="btn edit" id="btn_edit">
                			<input type="button" value="删除" class="btn del" id="btn_del">
                			<input type="button" value="详情" class="btn edit" id="btn_edit">
                		</td>
                	</tr>
                	<tr>
                		<td class="with-checkbox"><input type="checkbox" name="check" value="1" /></td>
                		<td>18417173358</td>
                		<td>李四</td>
                		<td>18417173358</td>
                		<td></td>
                		<td>田园物业</td>
                		<td>生效</td>
                		<td><input type="button" value="修改" class="btn edit" id="btn_edit">
                			<input type="button" value="删除" class="btn del" id="btn_del">
                			<input type="button" value="详情" class="btn edit" id="btn_edit">
                		</td>
                	</tr>
                -->
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