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
<script type="text/javascript" src="<c:url value='/js/user/userManager.js'></c:url>"></script>
<script type="text/javascript" src="<c:url value='/js/public/defined-checkbox.js'></c:url>"></script>
<title>物业管理-用户管理</title>
<style type="text/css">
 .value-input {
 	width: 100%;
 }
 .defined-input-border-del {
 	color:black;
 	border:1px solid red;
 }
 .defined-line {
 	text-decoration: line-through;
 }
 .new-add {
 	color: green;
 }
</style>
</head>
<body>
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
                  <select name="type" id="type"
						class="input-small">
						<option selected="selected" value="account">帐号</option>
						<option value="userName">姓名</option>
						<option value="wuyegongsi">物业公司</option>
				  </select>
                  <input id="keyword-input" name="keywords" class="input-medium" placeholder="请输入关键词" type="text">
                  <input type="button" class="btn" id="search" onclick="clickSearch()" value="查询">
                </div>
              </form>
            </div>
          </div>
          <div class="box-content">
            <div class="row-fluid" id ="defined-content">
            	<div><a class="icon-remove defined-del"></a><a class="icon-edit defined-edit"></a><input type="text" value="abc" class="value-input" style="border: none;" disabled="disabled" action-data="normal" old-value="abc"></div>
            	<div><a class="icon-remove defined-del"></a><a class="icon-edit defined-edit"></a><input type="text" value="abc" class="value-input" style="border: none;" disabled="disabled" action-data="normal" old-value="abc"></div>
            	<div><a class="icon-remove defined-del"></a><a class="icon-edit defined-edit"></a><input type="text" value="abc" class="value-input" style="border: none;" disabled="disabled" action-data="normal" old-value="abc"></div>
          		<div><a class="icon-remove defined-del"></a><a class="icon-edit defined-edit"></a><input type="text" value="abc" class="value-input" style="border: none;" disabled="disabled" action-data="normal" old-value="abc"></div>
            	<div><a class="icon-remove defined-del"></a><a class="icon-edit defined-edit"></a><input type="text" value="abc" class="value-input" style="border: none;" disabled="disabled" action-data="normal" old-value="abc"></div>
            	<div><a class="icon-remove defined-del"></a><a class="icon-edit defined-edit"></a><input type="text" value="abc" class="value-input" style="border: none;" disabled="disabled" action-data="normal" old-value="abc"></div>
          		<div><a class="icon-plus defined-add"></a></div>
          	</div>
          	<a class="btn" href="javascript:tj();">提交</a>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
<div id="fallr-overlay"> </div>
<script type="text/javascript">
	$(document).ready(function(){
		$(".defined-del").click(function() {
			var v = $(this);
			if (v.hasClass("icon-remove")) {
			//v.siblings().attr("disabled",false).css("border-color","red");
			v.siblings("input").css({"color": "red"});
			v.siblings("input").addClass("defined-line");
			v.removeClass("icon-remove").addClass("icon-retweet");
			v.siblings("input").attr("action-data","del");
			} else {
				//v.siblings().attr("disabled",false).css("border-color","red");
				v.siblings("input").css({"color": "black"});
				v.siblings("input").removeClass("defined-line");
				v.removeClass("icon-retweet").addClass("icon-remove");
				
				var oldValue = v.siblings("input").attr("old-value");
				var newValue = v.siblings("input").attr("old-value");
				if (oldValue = oldValue) {
					v.siblings("input").attr("action-data","edit");
				} else {
					v.siblings("input").attr("action-data","normal");
				}
			}
		});
		$(".defined-edit").click(function() {
			var v = $(this);
			v.siblings("input").attr("disabled",false);
			v.siblings("input").css({"border":"1px solid green"});
			v.siblings("input").attr("action-data","edit");
		});
		$(".value-input").change(function() {
			$(this).attr("disabled",true);
			$(this).css({"border":"0px solid green"});
		});
		$(".defined-add").click(function() {
			var v = $(this);
			v.parent().before("<div><a class='icon-minus new-del'></a><a class='icon-ok new-add'></a><input type='text' value='' class='value-input' style='border-color : green;' action-data='add'></div>");
			newAdd();
		});
		
	});
	function newAdd() {
		$(".new-add").click(function() {
			var v = $(this);
			v.siblings("input").attr("disabled",true).css({"border":"none"});
		});
		$(".new-del").click(function() {
			var v =$(this);
			v.parent().remove();
		});
	}
	
	function tj() {
		var dc = $("#defined-content").children("div");
		$.each(dc,function(n,value) {
			console.info(value);
		});
		var chArr = dc.children();
		for (var i = 0; i < chArr.length-1; i ++) {
			var chObj = chArr[i];
			console.info(chObj);
		}
	}
</script>
</body>
</html>