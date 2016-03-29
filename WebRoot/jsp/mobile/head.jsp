<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%
    	String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>
<!DOCTYPE html>
<style>
#ui-header {
	    width: 100%;
	    height: 46px;
	    line-height: 46px;
	    z-index: 3;
	    bottom:20px;
	    z-index: 1030;
	    left: 0;
	    top: 0;
	    right: 0;
	}
#ui-header .fixed {
    display: block;
    margin: 0 auto;
    min-width: 320px;
    height: 45px;
    top: 0;
    border-bottom: 1px solid #305196;
    box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.25);
    -moz-box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.25);
    -webkit-box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.25);
    background-image: -webkit-gradient(linear, left top, left bottom, from(#6DACED), to(#3C63B9));
    background-image: -webkit-linear-gradient(#6DACED, #3C63B9);
    background-image: -moz-linear-gradient(#6DACED, #3C63B9);
    background-image: -ms-linear-gradient(#6DACED, #3C63B9);
    background-image: -o-linear-gradient(#6DACED, #3C63B9);
    background-image: linear-gradient(#6DACED, #3C63B9);
    /* opacity: 0.95; */
}
#popmenu {
    /*cursor: pointer;*/
    display: block;
    position: relative;
    text-align: center;
    width: 200px;
    margin: 0 auto;
}
.ui-title {
    min-height: 46px;
    text-align: center;
    font-size: 16px;
    font-weight: normal;
    display: block;
    margin: 10px 70px 0;
    padding: 0;
    text-overflow: ellipsis;
    overflow: hidden;
    white-space: nowrap;
    outline: 0 !important;
    display: none;
    text-indent: -10px;
    color: #FFF;
    text-shadow: 0 1px #3C63B9;
}
#ui-header .fixed a {
    color: #000000;
    text-decoration: none;
}
.ui-btn-left_pre {
    position: absolute;
    top: 0px;
    left: 0px;
    background: url(<%=path%>/jsp/mobile/img/themes/pre2.png) no-repeat center center;
    display: block;
    width: 50px;
    height: 44px;
    text-indent: -9999px;
    margin: 0;
    padding: 0;
    background-size: 24px auto;
}
.ui-btn-right_home {
    position: absolute;
    top: 0px;
    right: 0px;
    background: url(../images/themes/home2.png) no-repeat center center;
    display: block;
    width: 50px;
    height: 44px;
    text-indent: -9999px;
    margin: 0;
    padding: 0;
    background-size: 24px auto;
}
#overlay {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: #fff;
    opacity: 0;
    filter: alpha(opacity=0);
    display: none;
    z-index: 4;
}   
</style>
<div id="ui-header">
	<div class="fixed">
		<span class="ui-title" id="popmenu"><font style="font-size: 18px;" id="title">1</font></span>
		<a class="ui-btn-left_pre" href="javascript:"
			onclick="window.history.go(-1)">返回</a>
	</div>
</div>
<div id="overlay"></div>
<script type="text/javascript">
	document.getElementById("title").innerHTML=document.title;
</script>