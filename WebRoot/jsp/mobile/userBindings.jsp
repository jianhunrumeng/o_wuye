<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String wxid = request.getParameter("wxid");
%>
<!DOCTYPE html>
<html>
<head>
<link href="<c:url value="/images/favicon.ico"></c:url>" rel="shortcut icon">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://apps.bdimg.com/libs/jquery/1.10.2/jquery.min.js"></script>
<script src="http://apps.bdimg.com/libs/jquerymobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
 
</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header" data-theme="b">
  <h1>用户绑定信息</h1>
  </div>

  <div data-role="content" data-overlay-theme="b">
  	  <ul data-role="listview">
  	  	 <li><label>手机号</label><input type="text" name="account"/></li>
  	  	 <li style="display: none;" id="pw-li"><label>密码</label><input type="text" name="password" /></li>
  	  	 <li style="display: none;" id="code-li"><label>验证码</label><input type="text" name="securyCode" /></li>
  	  	 <li id="agree-li"><label style="font-size: 14px;"><input type="checkbox" name="agree" id="agree"/>我已阅读并同意《XX用户服务条款》和《XX用户信息保护条款》</label></li>
  	  	 <li style="display: none;" id="u-bindings"><input type="button" value="绑定" onclick="userBindings()"/></li>
  	  	 <li id="c-phone"><input type="button" value="校验手机号" onclick="checkMobilePhone()" /></li>
  	  	 <li style="display: none;" id="connu-info"><input type="button" value="继续" onclick="connuInfo()" /></li>
  	  	 <li class="bos-info"><fieldset data-role="fieldcontain">
	        <label for="propertyCompany">物业公司</label>
	        <select name="propertyCompany" id="propertyCompany">
	         <option value="0">请选择</option>
	         <option value="tue">星期二</option>
	        </select>
	      </fieldset>
	      </li>
	      <li class="bos-info">
	      <fieldset data-role="fieldcontain">
	        <label for="community">小区</label>
	        <select name="community" id="community">
	         <option value="mon">请选择</option>
	         <option value="tue">星期二</option>
	        </select>
	      </fieldset>
	      </li>
	      <li class="bos-info">
	      <fieldset data-role="fieldcontain">
	        <label for="day">楼栋/单元</label>
	        <select name="day" id="day">
	         <option value="0">请选择</option>
	         <option value="tue">星期二</option>
	        </select>
	      </fieldset>
	      </li>
	      <li class="bos-info">
	      <fieldset data-role="fieldcontain">
	        <label for="room">房间</label>
	        <select name="room" id="room">
	         <option value="0">请选择</option>
	         <option value="tue">星期二</option>
	        </select>
	      </fieldset>
	      </li>
  	  	 <li style="display: none;" id="info-submit"><input type="button" value="提交" onclick="infoSubmit()" /></li>
  	  </ul>
  	  <p>
  	  	<a href="#page1" id="a-1" data-rel="dialog" style="display: none;" data-transition="flip"></a>
  	  	<a href="#page2" id="a-2" data-rel="dialog" style="display: none;" data-transition="flip"></a>  
        <a href="#page3" id="a-3" data-rel="dialog" style="display: none;" data-transition="flip"></a>  
        <a href="#page4" id="a-4" data-rel="dialog" style="display: none;" data-transition="flip"></a>
        <a href="#page5" id="a-5" data-rel="dialog" style="display: none;" data-transition="flip"></a>
        <a href="#page6" id="a-6" data-rel="dialog" style="display: none;" data-transition="flip"></a>
        <a href="#page7" id="a-7" data-rel="dialog" style="display: none;" data-transition="flip"></a> 
		<a href="#page8" id="a-8" data-rel="dialog" style="display: none;" data-transition="flip"></a>                          
        </p>
  	
  </div>
  
</div>
<div data-role="page" id="page1">  
    <div data-role="header"  data-theme="b">  
        <h1>温馨提示</h1>  
    </div>  
    <div data-role="content">  
        <p>  
        改手机号码已经和其他微信绑定。
        </p> 
    </div>  
</div>
<div data-role="page" id="page2">  
    <div data-role="header"  data-theme="b">  
        <h1>温馨提示</h1>  
    </div>  
    <div data-role="content">  
        <p>  
        请阅读相关条款，并且勾选同意.  
        </p> 
    </div>  
</div>
<div data-role="page" id="page3">  
    <div data-role="header"  data-theme="b">  
        <h1>温馨提示</h1>  
    </div>  
    <div data-role="content">  
        <p>  
        绑定成功  
        </p> 
        <input type="button" value="进入主菜单" onclick="goMainMenu();"/>
    </div>  
</div>
<div data-role="page" id="page4">  
    <div data-role="header"  data-theme="b">  
        <h1>温馨提示</h1>  
    </div>  
    <div data-role="content">  
        <p>  
        手机号码不能为空! 
        </p> 
  	  
    </div>  
</div>
<div data-role="page" id="page5">  
    <div data-role="header"  data-theme="b">  
        <h1>温馨提示</h1>  
    </div>  
    <div data-role="content">  
        <p>  
        手机号码不合法,请重新输入! 
        </p> 
  	  
    </div>  
</div> 
<div data-role="page" id="page6">  
    <div data-role="header"  data-theme="b">  
        <h1>温馨提示</h1>  
    </div>  
    <div data-role="content">  
        <p>  
        合法帐号，请发送手机验证码。 
        </p> 
  	    <input type="button" class="selector" value="获取验证码" id="send_code" onclick="sendCode(this)">
    </div>  
</div>
<div data-role="page" id="page7">  
    <div data-role="header"  data-theme="b">  
        <h1>温馨提示</h1>  
    </div>  
    <div data-role="content">  
        <p>  
       手机号系统中不存在，是否继续绑定？是，请点击发送验证码，否，请点击取消绑定。 
        </p> 
  	    <input type="button" class="selector" value="获取验证码" id="send_code_1" onclick="sendCode(this)">
  	    <input type="button" class="selector" value="取消绑定" id="cancle_bindings">
    </div>  
</div>
<div data-role="page" id="page8">  
    <div data-role="header"  data-theme="b">  
        <h1>温馨提示</h1>  
    </div>  
    <div data-role="content">  
        <p id="p-tip">  
       	验证码不正确。 
        </p> 
    </div>  
</div>          
</body>
<script type="text/javascript">
var checkCode = "";
var seconds = 60;
var f_seconds = 5;
var intervalId,buttonObj;
var successPhone = "";
 $(document).ready(function(){
 	/* if (!is_weixin()) {
 		$("body").html("请在微信上打开");
 	} */
    $(".bos-info").css({"display":"none"});     
 	$("#cls_btn, #cancle_bindings").bind('click',function(){
		$('.ui-dialog').dialog('close');    
	});   
 	$("#send_code_1").bind('click',function() {
 		$('.ui-dialog').dialog('close'); 
 		$("#code-li, #connu-info").css({"display":"block"});
 		$("#c-phone").css({"display":"none"});
 		$("input[name='account']").attr("disabled",true);
 	});
   });
function checkMobilePhone() {
	var mp = $("input[name='account']").val();
	if (!($("#agree").is(':checked'))) {
		$("#a-2").trigger("click"); 
		return;
	}
	if (mp == "") {
		$("#a-4").trigger("click"); 
	} else {
	    var telReg = !!mp.match(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/);
	    if (telReg == false) {
	    	$("#a-5").trigger("click"); 
	    } else {
	    	$.post("checkUserInfo",{
	    		"type" : "checkPhone",
	    		"mobilePhone" : mp
	    	},function(data) {
		    	if (data == "yes") {
		    		$("#a-6").trigger("click"); 
		    	} else if (data == "isHas") {
		    		$("#a-1").trigger("click");
		    	} else {
		    		$("#a-7").trigger("click"); 
		    	}
	    	});
	    }
	}
}
function userBindings() {
	var mp =  $("input[name='account']").val();
	if (!($("#agree").is(':checked'))) {
		$("#a-2").trigger("click");
		return;
	}
	var pw = $("input[name='password']").val();
	if (pw == "") {
		$("#p-tip").text("请输入密码"); 
		$("#a-8").trigger("click");
		return;
	} 
	var code = $("input[name='securyCode']").val(); 
	if (code == "") {
		$("#p-tip").text("请输入手机验证码"); 
		$("#a-8").trigger("click"); 
		return;
	} else if (code == checkCode) {
		$("#p-tip").text("手机验证码不正确"); 
		$("#a-8").trigger("click"); 
		return;
	}
	$.post("checkUserInfo",{
			"type" : "checkPassword",
			"password" : pw,
			"mobilePhone" : mp
		},function(data) {
			if (data == "no") {
				$("#p-tip").text("密码错误，请重新输入"); 
				$("#a-8").trigger("click");
				isError = "yes";
				return;
			} else {
				showLoader("绑定中...");
				$.post("userOpendBindings",
					{
						"type" : "simpleBindings",
						"mobilePhone" : mp
					},
					function(data) {
						if (data == "yes") {
							showLoader("绑定成功");
							successPhone = mp;
						} else {
							showLoader("绑定失败");
						}
					} 
				);
			}
		});
}
function showLoader(context) {  
    //显示加载器.for jQuery Mobile 1.2.0  
    $.mobile.loading('show', {  
        text: context, //加载器中显示的文字  
        textVisible: true, //是否显示文字  
        theme: 'b',        //加载器主题样式a-e  
        textonly: false,   //是否只显示文字  
        html: ""           //要显示的html内容，如图片等  
    });
    if ("绑定成功" == context) {
    	intervalId = setInterval(function() {
    		 $.mobile.loading('hide'); 
    		 $("#a-3").trigger("click");
    	},1000);
    } else if ("绑定失败") {
    	 intervalId = setInterval(function() {
    		 $.mobile.loading('hide'); 
    	},1000);
    }

}

function connuInfo() {
	if (!($("#agree").is(':checked'))) {
		$("#a-2").trigger("click"); 
		return;
	}
	var code = $("input[name='securyCode']").val(); 
	if (code == "") {
		$("#a-8").trigger("click"); 
		return;
	}
	$("#code-li, #connu-info, #agree-li").css({"display":"none"});
	$("#info-submit, .bos-info").css({"display":"block"});
}
function sendCode(thi) {
	checkCode = Math.floor(Math.random()*900000 + 100000);
	console.info("验证码："+checkCode);
	var phone = $("input[name='account']").val();
	$(thi).attr("disabled",true);
	$(thi).val("正在发送，请稍候...").button("refresh");
	buttonObj = $(thi);
	if (true) {
		$(thi).val("验证码发送成功").button("refresh");
		$("#pw-li, #code-li, #u-bindings").css({"display":"block"});
   		$("#c-phone").css({"display":"none"});
   		$("input[name='account']").attr("disabled",true);
		intervalId = setInterval("ticker()",1000);
	} else {
		$(thi).val("验证码发送失败").button("refresh");
		intervalId = setInterval("f_ticker()",1000);
	}
}
function ticker(){
	seconds --;
	if(seconds > 55){
		//提示消息显示5秒钟
	}else if(seconds>0){
		$(buttonObj).val(seconds+"秒后可重新获取").button("refresh");
	}else{
		clearInterval(intervalId);
		$(buttonObj).attr("disabled",false);
		$(buttonObj).val("获取验证码").button("refresh");
		seconds = 60;
		buttonObj = null;
	}
}
function f_ticker(){
	f_seconds --;
	if(f_seconds > 3){
		//提示消息显示5秒钟
	}else if(f_seconds>0){
		$(buttonObj).val(f_seconds+"秒后可重新获取").button("refresh");
	}else{
		clearInterval(intervalId);
		$(buttonObj).attr("disabled",false);
		$(buttonObj).val("获取验证码").button("refresh");
		f_seconds = 5;
		buttonObj = null;
	}
}
function goMainMenu() {
	location.href = "userBindings?type=userBindings&account="+successPhone+"&wxid=<%=wxid%>";
}
function is_weixin(){
	var ua = navigator.userAgent.toLowerCase();
	if(ua.match(/MicroMessenger/i)=="micromessenger") {
		return true;
 	} else {
		return false;
	}
}
</script>
</html>
				