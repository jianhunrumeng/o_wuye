$(document).ready(function() {
	$("#login_button").bind("click", login);
	$("#rememberUser").bind("click", rememberUser);
	if(getBrowser()=="MSIE"){
		$("#ie9-tips").show().find("#stopSuggestA").click(function(){
			$("#ie9-tips").hide()
		})
	}
	getCookie();
	$(document).keydown(function(event) {
		if (event.which == 13) {
			login();
		}
	});
	$.post(
		"userLogin!checkUser.action",
		function(data){
			if(data){
				location.href = "index.jsp";
			}
		}
	);
});

/*检测浏览器内核*/
function getBrowser() 
{ 
   if(navigator.userAgent.indexOf("MSIE")>0) { return "MSIE"; } 
   if(isFirefox=navigator.userAgent.indexOf("Firefox")>0){ return "Firefox"; } 
   if(isSafari=navigator.userAgent.indexOf("Safari")>0) { return "Safari"; }  
   if(isCamino=navigator.userAgent.indexOf("Camino")>0){ return "Camino"; } 
   if(isMozilla=navigator.userAgent.indexOf("Gecko/")>0){ return "Gecko"; }   
} 

/**
 * 登陆方法
 */
function login() {
	var username = $("#account").val();
	var password = $("#password").val();
	if (username == null || username == "") {
		$("#err_tips").html("你还没有输入帐号！");
		if ($('#err_area').hasClass('dn')) {
			$("#err_area").removeClass("dn");
		}
		$("#account").focus().select();
		return;
	}
	if (password == null || password == "") {
		$("#err_tips").html("你还没有输入密码！");
		if ($('#err_area').hasClass('dn')) {
			$("#err_area").removeClass("dn");
		}
		$("#password").focus().select();
		return;
	}
	var browser = navigator.userAgent;
	$("#login_button").css("background","#BABDC0");
	$("#login_button").css("cursor","default");
	$("#login_button").html("登陆中");
	$.post('wuyeUserLogin/userLogin.action', {
		account : username,
		password : password
	}, function(data) {
		var result = eval("(" + data + ")");
		if (result.result) {
			location.href = "index.jsp";
		} else {
			alert(result.result);
		}
	});

}
/**
 * 记住用户名
 */
function rememberUser() {
	if ($("#rememberUserIcon").hasClass("icon24-login checkbox")) {
		$("#rememberUserIcon").removeClass("icon24-login checkbox");
		$("#rememberUserIcon").addClass("icon24-login checkbox_checked");
		setCookie();
	} else if ($("#rememberUserIcon").hasClass("icon24-login checkbox_checked")) {
		$("#rememberUserIcon").removeClass("icon24-login checkbox_checked");
		$("#rememberUserIcon").addClass("icon24-login checkbox");
		delCookie();
	}
}

/**
 * 设置Cookie
 */
function setCookie() {
	var username = $("#account").val();
	var cookieVal = "username=" + username + "&remember=true";
	$.cookie('qiduwxuser', cookieVal);
}

/**
 * 删除Cookie
 */
function delCookie() {
	$.cookie('qiduwxuser', null);
}

/**
 * 读取Cookie
 */
function getCookie() {
	var ruser = $.cookie('qiduwxuser');
	if (ruser != null) {
		var user = ruser.split('&')[0].split("=")[1];
		var remember = ruser.split('&')[1].split("=")[1];
		if ( remember == "true") {
			$("#rememberUserIcon").removeClass("icon24-login checkbox");
			$("#rememberUserIcon").addClass("icon24-login checkbox_checked");
			$("#account").val(user);
		}
	}
}
