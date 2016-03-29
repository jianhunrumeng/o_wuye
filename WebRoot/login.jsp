<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="物业管理系统登录">
    <meta name="author" content="yangchw">
    <link rel="stylesheet" href='<c:url value="/css/login/reset.css"></c:url>'>
    <link rel="stylesheet" href='<c:url value="/css/login/supersized.css"></c:url>'>
    <link rel="stylesheet" href='<c:url value="/css/login/style.css"></c:url>'>
    <script type="text/javascript" src="<c:url value='/js/jquery-1.9.1.min.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/js/jquery.cookie.js'></c:url>"></script>
    <script type="text/javascript"
            src="<c:url value='/js/login/supersized.3.2.7.min.js'></c:url>"></script>
    <script type="text/javascript"
            src="<c:url value='/js/login/supersized-init.js'></c:url>"></script>
    <script type="text/javascript" src="<c:url value='/js/login/login.js'></c:url>"></script>
    <title>物业管理系统</title>
    <%
        String ip = request.getRemoteAddr();
    %>
    <LINK rel="shortcut icon" type=image/x-icon href="<c:url value="/images/logo.ico"></c:url>">
    <script type="text/javascript">
        var ip = '<%=ip%>';
        //禁止被框架
        if (self != top)window.top.location.href = 'login.jsp';
        /*$(document).ready(function(){
         $("#login_button").click(function(){
         var username = $("#account").val();
         var password = $("#password").val();
         $.post('login!login.action', {
         account : username,
         password : password
         }, function(data) {
         var result = eval("(" + data + ")");
         if (result.success) {
         location.href = "index.jsp";
         } else {
         $("#login_button").css("background-color","#368ee0");
         $("#login_button").html("登陆");
         $("#login_button").css("cursor","pointer");
         $("#err_tips").html(result.msg);
         $("#password").val("");
         if ($('#err_area').hasClass('dn')) {
         $("#err_area").removeClass("dn");
         }
         }
         });

         }
         }*/
    </script>
</head>
<body>
<div class="page-container">
    <h1><a href="#" class="dib"> <img src="images/we7logo.png"> </a></h1>

    <form action="" method="post">
        <div class="login-err-panel dn" id="err_area">
            <span class="icon-wrapper"><i class="icon24-login err"
                                          style="margin-top:-.2em;*margin-top:0;"></i></span>
            <span id="err_tips">您输入的帐号或者密码不正确，请重新输入</span>
        </div>
        <input type="text" id="account" name="username" class="username" placeholder="账号">
        <input type="password" id="password" name="password" class="password" placeholder="密码">

        <div class="login-help-panel">
            <a id="rememberUser" class="login-remember-pwd">
                <input type="checkbox" id="rememberUserIcon" class="icon24-login checkbox"/>记住我
            </a>
            <!--                     <a class="login-forget-pwd" href="jsp/forgetpwd/forgetPwd.jsp">无法登录？</a> -->
        </div>
        <button id="login_button" type="button" value="登录">登录</button>
        <div id="error" class="error"><span>+</span></div>
    </form>
</div>
<div class="footer">
    <span style="color: #757575">Copyright&nbsp;@2015 &nbsp;物业管理系统</span>
</div>
</body>
</html>