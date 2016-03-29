$(document).ready(function() {
	$("#organization").hide();
	$("#submit_btn").bind("click",saveUser);
	$("#user_type").change(function() {
		var userTypeValue = $(this).val();
		if (userTypeValue == "11") {
			$("#organization").show();
		} else {
			$("#organization").hide();
		}
	});
	$("#search-type").change(function() {
		var st = $(this).val();
		if (st == "wuyegongsi") {
			$("#property_company_name").css({"display":"inline"});
			$("#n-keyword-input").css({"display":"none"});
		} else {
			$("#property_company_name").css({"display":"none"});
			$("#n-keyword-input").css({"display":"inline"});
		}
	});
});


function qryUser() {
	var searchtype = $("#search-type option:selected").val();//查询类型
	var account;
	var companyName;
	var partyName;
	if (searchtype=='company') {
		companyName = $("#property_company_name").val() ;
	} else if(searchtype=='name') {
		partyName = $("#n-keyword-input").val() ;
	} else if(searchtype=='account'){
		account = $("#n-keyword-input").val() ;
		searchtype = 'user';
	}
	
	var jsondata={};
	jsondata.qryType = searchtype;
	jsondata.account = account;
	jsondata.partyName = partyName;
	jsondata.companyName = companyName;
	$.post(
		"userAction!qryUserList.action",
		{comingParams : JSON.stringify(jsondata)},
		function(data){
			data = eval("("+data+")");
			if(data!=null&&data.result=="true"){
				$("#demo").myPagination({
				currPage: 1,
				pageSize: 5,
				pageCount:data.pageInfo.totalPageCount,
				ajax:{
					on: true,                        //开启状态
					url: "userAction!qryUserList.action",
		            dataType: 'json',                //返回类型
		            callback:'callBack',
		            param:{"on":true, comingParams : JSON.stringify(jsondata)},
		            ajaxStart: function() {
		                return false;
		            },onClick:function(page){
		            	alert(page);
		            }
				}
			});
			}else{
				alert("查询失败:"+data.msg);
			}
		}
	);
}

function callBack(data){
	$("#listTable>tbody").children().remove();
	data = eval("("+data+")");
	$(data.pageInfo.dataList).each(function(ix,ite){
		var trStr = '<tr>';
		var strValue = ite.userId 
		if (!StrUtil.isEmpty(ite.partyInfo)){
			strValue = strValue + ";" + StrUtil.strnull(ite.partyInfo.partyInfoId);
		}else{
			strValue = strValue + ";";
		}
		
		if (!StrUtil.isEmpty(ite.address)){
			strValue = strValue + StrUtil.strnull(ite.address.addressId);
		}
		trStr += '<td class="with-checkbox"><input type="checkbox" name="check" value="'+strValue +'" /></td>';
		trStr += '<td>'+ite.account+'</td>';
		if (!StrUtil.isEmpty(ite.partyInfo)) {
			trStr += '<td>'+StrUtil.strnull(ite.partyInfo.partyName)+'</td>';
		} else {
			trStr += '<td></td>';
		}
		
		if (!StrUtil.isEmpty(ite.partyInfo)) {
			trStr += '<td>'+StrUtil.strnull(ite.partyInfo.mobile)+'</td>';
		} else {
			trStr += '<td></td>';
		}
		
		if(ite.userType =='10') {
			trStr += '<td>系统管理员</td>';
		} else if (ite.userType == '11') {
			trStr += '<td>物业用户</td>';
		} else {
			trStr += '<td>未知</td>';
		}
		
		trStr += '<td>'+ite.account+'</td>';
		trStr += '<td>' + StrUtil.strnull(ite.statusName) + '</td>';
		/*if (ite.statusCd =='1000') {
			trStr += '<td>生效</td>';
		} else {
			trStr += '<td>失效</td>';
		}*/
		
		trStr += '<td><a href="addUserInfo.jsp?userId='+ite.userId+'" data-toggle="modal" class="btn defined-role-btn" id="btn_edit_user_"'+ite.userId+'>修改</a>';
		trStr +='<a href="addUserInfo.jsp?userId='+ite.userId+'" class="btn defined-role-btn" >详情</a></td>';
		trStr += '</tr>';
		$("#listTable>tbody").append(trStr);
	});
}

function saveUser() {
	var user_account = $("input[name='user_account']").val();		//帐号（手机号）
	if (user_account == "") {
		$.sucmodal("提示", "帐号（手机号）必填");
		return;
	}
	var reg = /^(13[0-9]|14[0-9]|15[0-9]|18[0-9])\d{8}$/i;
	if(!reg.test(user_account))
	{
  		$.sucmodal("提示", "手机号格式不正确！");
  		return;
	}
	var user_name = $("input[name='user_name']").val();		//姓名
	if (user_name == "") {
		$.sucmodal("提示", "用户姓名必填");
		return;
	}
	var user_cert_type = $("#user_cert_type option:selected").val();		//证件类型
	var user_cert_nbr = $("input[name='user_cert_nbr']").val();		//证件号码
	var user_status = $("#user_status option:selected").val();		//用户状态
	var user_type = $("#user_type option:selected").val();			//用户类型
	if (user_type == "") {
		$.sucmodal("提示", "用户类型必填必填");
		return;
	}
	
	if(user_cert_type =='10') { //证件类型是身份证时要验证下身份证号码是否符合格式
		var reg2 = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		if(!reg2.test(user_cert_nbr))  {  
       		$.sucmodal("提示","身份证输入不合法");  
       		return;  
   		}  
	}
	
	var user_qq = $("input[name='user_qq']").val();		//QQ
	var user_email = $("input[name='user_email']").val();		//email
	var user_wx = $("input[name='user_wx']").val();		//微信
	var area_area = $("#area_area option:selected").val();		//地区
	if (area_area == "") {
		$.sucmodal("提示", "地区必填");
		return;
	}
	var user_address = $("input[name='user_address']").val();		//详情地址
	if (user_address == "") {
		$.sucmodal("提示", "详情地址必填");
		return;
	}
	var user_company_id = $("#property_company_name").attr("data-id");		//物业公司
	if (user_company_id == "" && user_type == "11") {
		$.sucmodal("提示", "系统类型是物业用户，物业公司必填");
		return;
	}
	var user_community_id = $("#community_name").attr("data-id");	//小区
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	});
	
	var jsondata = {user : {}, propertyCompany: {},community:{},partyInfoUser: {},role:[],address:{},area:{}};
	jsondata.user.account = user_account;		//帐号
	jsondata.user.statusCd = user_status;		//状态
	jsondata.user.userType = user_type;		//用户类型
	jsondata.area.areaId = area_area;			//地区
	jsondata.partyInfoUser.partyName = user_name;	//姓名
	jsondata.partyInfoUser.certType = user_cert_type;	//证件类型
	jsondata.partyInfoUser.certNbr = user_cert_nbr;	//证件号码
	jsondata.partyInfoUser.weiXin=user_wx;		//微信
	jsondata.partyInfoUser.email=user_email;			//email
	jsondata.partyInfoUser.qq=user_qq;				//qq
	jsondata.address.detailAddress = user_address;  //详情地址
	jsondata.propertyCompany.companyId = user_company_id;	//物业公司
	jsondata.community.communityId = user_community_id;		//小区
	jsondata.role = chk_value;						//角色
	$.post("userAction!saveUser.action",{
		comingParams : JSON.stringify(jsondata)
	},function(data) {
		data = eval("(" + data + ")");
		if (data.key == "success") {
			$.sucmodal("提示", data.msg, function() {
				location.href = "userManager.jsp";
			});
		} else {
			alert("保存失败" + data.msg);
		}
	});
}

/**
 * 用户删除。
 */
function removeAllUser() {
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要删除的用户",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的用户吗?",1000,function(){
		$.post(
			"userAction!removeUser.action",
			{comingParams:JSON.stringify(chk_value)},
			function(data){
				data = eval("("+data+")");
				if(data.result == "true"){
					$.sucmodal("提交成功", data.msg,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", data.msg);
				}
			}
		);
	});
}

