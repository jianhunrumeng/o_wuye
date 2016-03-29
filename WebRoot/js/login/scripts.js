jQuery(document).ready(function() {
    $("#login_button").bind("click", login);
	$("#rememberUserIcon").bind("click", rememberUser);
	getCookie();
	$(document).keydown(function(event) {
		if (event.which == 13) {
			login();
		}
	});
	$.post(
		"login!checkUser.action",
		function(data){
			if(data){
				location.href = "index.jsp";
			}
		}
	);
    //***************
    $('.page-container form .username, .page-container form .password').keyup(function(){
        $(this).parent().find('.error').fadeOut('fast');
    });
  
});

/**
 * 登陆方法
 */
function login() {
  var parents = $(this).parents(".page-container form");
  var username = $('#account').val();
  var password = $('#password').val();
  if(username == null || username == "") {
      $('#error').fadeOut('fast', function(){
          $(this).css('top', '27px');
      });
      $('#error').fadeIn('fast', function(){
          $(this).parent().find('.username').focus();
      });
      return false;
  }
  if(password == null || password == "") {
	  $('#error').fadeOut('fast', function(){
          $(this).css('top', '96px');
      });
	  $('#error').fadeIn('fast', function(){
          $(this).parent().find('.password').focus();
      });
      return false;
  }
	var browser = navigator.userAgent;
	/*$("#login_button").css("background","#BABDC0");
	$("#login_button").css("cursor","default");
	$("#login_button").html("登陆中");*/
	$.post('login.action', {
		account : username,
		password : password
	}, function(data) {
		var result = eval("(" + data + ")");
		if (result.success) {
			location.href = "index.jsp";
		} else {
			$("#login_button").css("background-color","#ef4300");
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
/**
 * 记住用户名
 */
function rememberUser() {
	if ($("#rememberUserIcon").get(0).checked==true) {
		setCookie();
	} else if ($("#rememberUserIcon").get(0).checked==false) {
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
			$("#rememberUserIcon").attr("checked",true);
//			$("#rememberUserIcon").removeClass("icon24-login checkbox");
//			$("#rememberUserIcon").addClass("icon24-login checkbox_checked");
			$("#account").val(user);
		}
	}
}

