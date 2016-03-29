<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="/images/favicon.ico"></c:url>" rel="shortcut icon">
<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

<!-- <div data-role="page" id="pageone">
  <div data-role="header">
    <div data-role="navbar">
      <ul>
        <li><a href="javascript:void(0)" class="ui-btn-active ui-state-persist" data-icon="arrow-d">未缴费</a></li>
        <li><a href="javascript:void(0)" data-icon="arrow-u">已缴费</a></li>
      </ul>
    </div>
  </div>

  <div data-role="content">
  	 
  </div>
  <div data-role="content">
  	 
  </div>
</div> -->

<div data-role="page" id="index">
<div data-role="content">
     <ul data-role="listview" id="circle-news-list">
    	<li><a>第一页</a></li>
    	<li><a>第一页</a></li>
    	<li><a>第一页</a></li>
    	<li><a>第一页</a></li>
    	<li><a>第一页</a></li>
    	<li><a>第一页</a></li>
     </ul>            
</div>
</div>

<div data-role="page" id="class-page">
<div data-role="content">
     <ul data-role="listview" id="class-news-list">
    	<li><a>第二页</a></li>
    	<li><a>第二页</a></li>
    	<li><a>第二页</a></li>
    	<li><a>第二页</a></li>
    	<li><a>第二页</a></li>
    	<li><a>第二页</a></li>
    	<li><a>第二页</a></li>
     </ul>            
</div>
</div>
</body>
<script type="text/javascript">
$(function(){
    $("#circle-news-list").bind("swipeleft",function(){
        $.mobile.changePage("#class-page", {transition:"slide",reverse:true}, true, true);
    });
    $("#class-news-list").bind("swiperight",function(){
        $.mobile.changePage("#index", {transition:"slide",reverse:true}, true, true);
    });
});
</script>
</html>
			