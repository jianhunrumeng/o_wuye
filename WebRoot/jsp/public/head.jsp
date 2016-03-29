<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap.css'></c:url>" media="all" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/bootstrap-responsive.css'></c:url>" media="all" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'></c:url>" media="all">
	<link rel="stylesheet" type="text/css" href="<c:url value='/bootstrap/css/todc_bootstrap.css'></c:url>" media="all">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/themes.css'></c:url>" media="all">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/font/css/font-awesome.css'></c:url>" media="all" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/ztree/zTreeStyle.css'></c:url>" media="all">
	<script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/bootstrap/js/bootstrap.min.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery.cookie.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/modal.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/json2.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/public/defined-checkbox.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery.myPagination.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/public/jquery-ui.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/jquery.cookie.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/index/application.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/public/property-community-search.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/public/defined-address.js'></c:url>"></script>
	<!-- 树形结构js -->
	<script type="text/javascript" src="<c:url value='/js/ztree/jquery.ztree.core-3.5.js'></c:url>"></script>
	<script type="text/javascript" src="<c:url value='/js/ztree/jquery.ztree.excheck-3.5.js'></c:url>"></script>
	
