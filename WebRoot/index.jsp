<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<!-- Apple devices fullscreen -->
<meta name="apple-mobile-web-app-capable" content="yes" />
<!-- Apple devices fullscreen -->
<meta name="apple-mobile-web-app-status-bar-style"
	content="black-translucent" />
<base target="mainFrame" />
<jsp:include page="/jsp/public/head.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/index/index.css'></c:url>" media="all" />
<script type="text/javascript" src="<c:url value='/js/index/menu.js'></c:url>"></script>

<title>物业管理系统</title>
<LINK rel="shortcut icon" type=image/x-icon
	href="<c:url value="/images/logo.png"></c:url>">
<style type="text/css">
/*左侧菜单*/
/*一级菜单*/
.menu-first {
	height: 42px;
	line-height: 42px;
	background-color: #e9e9e9;
	border-top: 1px solid #efefef;
	border-bottom: 1px solid #e1e1e1;
	padding: 0;
	font-size: 16px;
	font-weight: normal;
	padding-left: 28px;
}

.menu-first i {
	font-size: 16px;
	line-height: 20px;
}
/*一级菜单鼠标划过状态*/
.menu-first:hover {
	text-decoration: none;
	background-color: #d6d4d5;
	border-top: 1px solid #b7b7b7;
	border-bottom: 1px solid #acacac;
}
/*二级菜单*/
.menu-second li a {
	background-color: #f6f6f6;
	height: 31px;
	line-height: 31px;
	border-top: 1px solid #efefef;
	border-bottom: 1px solid #efefef;
	font-size: 14px;
	padding-left: 42px;
}
/*二级菜单鼠标划过样式*/
.menu-second li a:hover {
	text-decoration: none;
	background-color: #66c3ec;
	border-top: 1px solid #83ceed;
	border-bottom: 1px solid #83ceed;
	border-left: 3px solid #66c3ec;
}
/*二级菜单选中状态*/
.menu-second-selected {
	background-color: #cccccc;
	height: 31px;
	line-height: 31px;
	color : #222;
	border-top: 1px solid #83ceed;
	border-bottom: 1px solid #83ceed;
	border-right: 3px solid #f8881c;
	border-left: 3px solid #66c3ec;
}
</style>
<script type="text/javascript">
	if (self != top)
		window.top.location.href = 'index.jsp';
	var submitting = false;
	function feedbackSubmit() {
		if (submitting) {
			//return false;
		}
		submitting = true;
		var $data = {
			feedback : $('#feedback-text').val(),
			email : $('#feedback-input').val(),
			url : parent.document.getElementById("mainFrame").contentWindow.location.href,
			from : 'server'
		};
		$('#submit_but').text('提交中...');
		$('#submit_but').removeClass('btn-primary');
		$.post('/site/feedback', $data, function(rs) {
			$("#feedback").modal('hide');
			if (200 == rs.code) {
				G.ui.tips.suc(rs.error);
				$('#feedback-text').val('');
				$('#feedback-input').val('');
			} else {
				G.ui.tips.err(rs.error);
			}
			$('#submit_but').text('提交');
			$('#submit_but').addClass('btn-primary');
		}, 'json');
	}
	//退出函数
	function logout() {
		$.post("logout.action", function(data) {
			location.href = "index.jsp";
		});
	}
	function clearConfig() {
		$.post("wccfg!clearConfig.action", function(data) {
			if (data) {
				window.top.location.href = 'index.jsp';
			}
		});

	}
</script>
</head>
<body onload="P.skn()">
	<div id="navigation">
		<div class="container-fluid">
			<div>
				<a href="index.jsp" style="cursor:pointer" target="_self" id="brand"></a>
				<a href="javascript:void(0);" class="toggle-nav" rel="tooltip"
					data-placement="bottom" id="menu-handle" title="隐藏菜单"><i
					class="fa fa-bars" style="line-height: 30px;"></i>
				</a>
			</div>
			<ul class='main-nav'>
				<li class='active'><a target="_self"> <span>管理平台</span> <i
						class="icon-angle-down"></i> </a></li>
				<c:if test="${session.weNo!=null }">
					<li><a href="javascript:clearConfig();">公众帐号管理</a>
					</li>
				</c:if>
				<li><a href="help/index.html" target="_blank">功能介绍</a></li>
			</ul>


			<div class="user">
				<ul class="icon-nav">
					<!-- <li class='dropdown'>
                        <a href="#" class='dropdown-toggle' data-toggle="dropdown" title="消息" style="display:none;"><i class="icon-envelope"></i><span class="label label-lightred">4</span></a>
                    </li>
                    <li class="dropdown sett" style="display:none;">
                        <a href="#" class='dropdown-toggle' data-toggle="dropdown" title="系统设置"><i class="icon-cog"></i></a>
                    </li> -->
					<li class='dropdown colo' style="height:40px"><a href="#"
						class='dropdown-toggle' style="cursor:pointer;height:20px"
						data-toggle="dropdown" title="选择颜色"><i class="fa fa-tint"></i>
					</a>
						<ul class="dropdown-menu pull-right theme-colors">
							<li class="subtitle">颜色选择</li>
							<li><span class='red'></span> <span class='orange'></span> <span
								class='green'></span> <span class="brown"></span> <span
								class="blue"></span> <span class='lime'></span> <span
								class="teal"></span> <span class="purple"></span> <span
								class="pink"></span> <span class="magenta"></span> <span
								class="grey"></span> <span class="darkblue"></span> <span
								class="lightred"></span> <span class="lightgrey"></span> <span
								class="satblue"></span> <span class="satgreen"></span></li>
						</ul></li>
				</ul>
				<div class="dropdown" style="left:15px"></div>
				<!-- <div class="dropdown" style="left:15px" >
                 <a  href="logout.jsp" target="_self" style="width: 40px; height: 27px;line-height:27px;overflow:hidden; " >退出</a>                
                </div> -->
				<div class="dropdown" style="left:15px">
					<a href="javascript:logout();" target="_self"
						style="height: 27px;line-height:27px;overflow:hidden;">退出 <i
						class="fa fa-sign-out"></i>
					</a>
				</div>
			</div>
		</div>
	</div>
	<div style="width: 100% ;height: 94%">
		<div class="container-fluid" id="content">
			<div id="left">
				<div id="menu" class="sidebar-menu">
					<!--<a href="#primary1" class="nav-header menu-first collapsed"
						data-toggle="collapse"><i class="icon-list-alt"></i>系统管理</a>
					<ul id="primary1" class="nav nav-list collapse menu-second in">
						<li><a href="jsp/role/roleManager.jsp"><i
								class="icon-user"></i>角色管理</a>
						</li>
						<li><a href="jsp/user/userManager.jsp"><i
								class="icon-user"></i>用户管理</a>
						</li>
						<li><a href="jsp/privilege/privilegeManager.jsp"><i
								class="icon-user"></i>权限管理</a>
						</li>
					</ul>
					<a href="#primary2" class="nav-header menu-first collapsed"
						data-toggle="collapse"><i class="icon-list-alt"></i>基础资料管理</a>
					<ul id="primary2" class="nav nav-list collapse menu-second in">
						<li><a href="jsp/company/propertyCompanyManager.jsp"> <i
								class="icon-user"> </i>物业公司管理 </a></li>
						<li><a href="jsp/community/communityManager.jsp"><i
								class="icon-user"></i>小区设置</a>
						</li>
						<li><a href="jsp/community/communityRelManager.jsp"><i
								class="icon-user"></i>小区相关设置</a>
						</li>
						<li><a href="jsp/building/buildingManager.jsp"><i
								class="icon-user"></i>楼栋单元管理</a>
						</li>
						<li><a href="jsp/room/roomManager.jsp"><i
								class="icon-user"></i>房间管理</a>
						</li>
						<li><a href="jsp/parking/parkingManager.jsp"><i
								class="icon-user"></i>车位管理</a>
						</li>
						<li><a href="jsp/vehicle/vehicleManager.jsp"><i
								class="icon-user"></i>车辆管理</a>
						</li>
					</ul>
					<a href="#primary3" class="nav-header menu-first collapsed"
						data-toggle="collapse"><i class="icon-list-alt"></i>收费管理</a>
					<ul id="primary3" class="nav nav-list collapse menu-second in">
						<li><a href="jsp/costsetting/costSettingManager.jsp"> <i
								class="icon-user"> </i>收费设置 </a></li>
					</ul>
				--></div>

			</div>

			<div class="right">

				<div class="main">
					<iframe frameborder="0" id="mainFrame" name="mainFrame"
						src="jsp/user/userManager.jsp"
						style="background:url('images/loading.gif') center no-repeat;"></iframe>
				</div>

			</div>

		</div>
		<div id="ascrail2000"
			style="width: 7px; z-index: 9002; position: absolute; top: 40px; left: 192px; height: 332px; display: block; opacity: 0;">
			<div
				style="position: relative; top: 0px; float: right; width: 5px; height: 306px; border: 1px solid rgb(255, 255, 255); border-top-left-radius: 5px; border-top-right-radius: 5px; border-bottom-right-radius: 5px; border-bottom-left-radius: 5px; background-color: rgb(66, 66, 66); background-clip: padding-box;"></div>
		</div>
		<script>
			$(document).ready(function() {

				// 绑定菜单提示语切换

				$('#menu-handle').click(function() {

					switchMenu(this);
				});
				P.skn();
				// 设置皮肤色
			});

			// 切换菜单提示语

			function switchMenu(obj) {
				if ('隐藏菜单' == $(obj).attr('title')) {
					$(obj).attr('title', '显示菜单');
				} else {
					$(obj).attr('title', '隐藏菜单');
				}
			}
			//退出
			function logout() {
				$.post("userLogin!logoutFun.action", function(data) {
					data = eval("("+data+")");
					if (data.result) {
						location.href = "login.html";
					}
				})
			}
		</script>
		<!-- 用户反馈 -->
	</div>
</body>
</html>
