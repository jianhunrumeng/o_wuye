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
#list-content span {
	width: 210px;
	height: 25px;
	line-height: 25px;
	display: block;
}
#list-content span:hover {
	background-color: #ccc;
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
            <div class="row-fluid">
              <!-- 	<div style="position:relative;">
					<span style="margin-left:100px;width:18px;overflow:hidden;">
					<select style="width:118px;margin-left:-100px" id="wuye-select">
					
					</select></span><input name="box" oninput="OnInput (event)" onpropertychange="OnPropChanged (event)"  style="width:100px;position:absolute;left:0px;">
				</div> -->
				<input name="box" onclick="showH(event)" oninput="OnInput (event)" onpropertychange="OnPropChanged (event)">
            	<div id="list-content" style="width: 210px; height: 100%; border:1px solid red;">
            		<div><span>ABC</span></div>
            		<div><span>ABC</span></div>
            		<div><span>ABC</span></div>
            	</div>
            </div>
          </div>
          
          <input type="text" value="abc" id="test" style="border: none;" disabled="disabled">
          
        </div>
      </div>
    </div>
  </div>
</div>
<div id="fallr-overlay"> </div>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
	
	  function OnInput (event) {
            //console.info ("The new content: " + event.target.value);
            dealVal(event.target.value);
       }
    // Internet Explorer
       function OnPropChanged (event) {
            if (event.propertyName.toLowerCase () == "value") {
                //console.info ("The new content: " + event.srcElement.value);
                dealVal(event.srcElement.value);
            }
       }
       
       function dealVal(val) {
       		$("#list-content").html("");
       		var str = "";
       		if (val =="A") {
       			str += "<div><span>A</span></div>";
       			str += "<div><span>AA</span></div>";
       			str += "<div><span>AAA</span></div>";
       		} else if (val == "B") {
       			str += "<div><span>B</span></div>";
       			str += "<div><span>BB</span></div>";
       			str += "<div><span>BBB</span></div>";
       		} else if (val == "C") {
       			str += "<div><span>C</span></div>";
       			str += "<div><span>CC</span></div>";
       			str += "<div><span>CCC</span></div>";
       		} else {
       			str += "<div><span>没有</span></div>";
       		}
       		$("#list-content").append(str);
       		loadClick();
       }
       function loadClick() {
       	$("#list-content span").click(function() {
			var t = $(this).text();
			$("input[name='box']").val(t);
			$(this).parent().parent().css("display","none");
		});
       }
       
       function showH(event) {
       		var e = event.srcElement.value;
       		$("#list-content").css("display","block");
       		console.info(e);
       }
</script>
</body>
</html>