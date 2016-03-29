var quanjuPrivilege = "";
$(document).ready(function() {
	var hRoleId = $("#hidden_role_id").val();
	getRole(hRoleId);
});

function getRole(roleId) {
	$.post(
	    "roleAction!getRole.action",
	    {"comingParams" : roleId},
	    function(data) {
	    	data = eval("("+data+")");
	    	if (data.isExit == "yes") {
	    		$("#xs_role_name").text(data.role.roleName);
		    	var statucStr = '失效';
		    	if (data.role.statusCd == '1000') {
		    		var statucStr = '生效';
		    	}
		    	$("#xs_role_status").text(statucStr);
		    	quanjuPrivilege = data.privilegeList;
		    	loadPrivilegeList();
	    	} else if (data.isExit == "no") {
	    		$.sucmodal("提交失败", "请于管理员联系",2000);
	    	}
	    	
	    }
	);
}

function loadPrivilegeList(){
	
	var privilegeName = $("input[name=keywords]").val();
	$.post(
		"roleAction!getPrivilegePageCount.action",
		{comingParams : privilegeName},
		function(data){
			data = eval("("+data+")");
			$("#demo").myPagination({
				currPage: 1,
				pageSize: 5,
				pageCount:data.pageCount,
				ajax:{
					on: true,                        //开启状态
		            // url: "mbook!getMBookByCondition.action?type="+type+"&keyword="+keyword,            //访问服务器地址
					url: "roleAction!getPrivilegeList.action",
		            dataType: 'json',                //返回类型
		            callback:'callBack',
		            param:{"on":true, comingParams : privilegeName},
		            ajaxStart: function() {
		                return false;
		            },onClick:function(page){
		            	alert(page);
		            }
				}
			});
		}
	);
}

function callBack(data){
	console.info(quanjuPrivilege);
	$("#listTable>tbody").children().remove();
	data = eval("("+data+")");
	$(data).each(function(ix,ite){
		var check_str = "";
		for (var i = 0; i < quanjuPrivilege.length; i ++) {
			var privilege = quanjuPrivilege[i];
			if (privilege.privilegeId == ite.privilegeId) {
				check_str = "checked"; 
				break;
			}
		}
		var trStr = '<tr>';
		if (check_str == "") {			
			trStr += '<td class="with-checkbox"><input type="checkbox" name="check"  value="'+ite.privilegeId+'" /></td>';
		} else {			
			trStr += '<td class="with-checkbox"><input type="checkbox" name="check" checked="checked" value="'+ite.privilegeId+'" /></td>';
		}
		
		trStr += '<td>'+ite.privilegeName+'</td>';
		if (ite.statusCd == '1000') {
			trStr += '<td>有效</td>';
		} else {
			trStr += '<td>失效</td>';
		}
		
		trStr += '<td style="text-align: center;">';
		if (check_str == "") {			
			trStr += '<a href="javascript:jsonInRole('+ite.privilegeId+');"  class="btn defined-role-btn">加入角色</a>';
		} else {			
			trStr +='<a href="javascript:quitRole('+ite.privilegeId+');" class="btn defined-role-btn">退出角色</a>';
		}
		trStr += '</td>';
		trStr += '</tr>';
		$("#listTable>tbody").append(trStr);
	});
}
//加入角色
function jsonInRole(privilegeId) {
	var roleId = $("#hidden_role_id").val();
	var chk_value =[]; 
	chk_value.push(privilegeId);
	var objMap = {"roleId" : roleId, "chkValue" : chk_value};
	$.post(
			"roleAction!joinInRole.action",
			{"comingParams": JSON.stringify(objMap)},
			function(data){
				data = eval("("+data+")");
				if(data.key == "success"){
					$.sucmodal("提交成功", "加入角色成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
}
//退出角色
function quitRole(privilegeId) {
	var roleId = $("#hidden_role_id").val();
	var chk_value =[]; 
	chk_value.push(privilegeId);
	var objMap = {"roleId" : roleId, "chkValue" : chk_value};
	$.altmodal("删除", "您确定退出角色吗?",1000,function(){
		$.post(
			"roleAction!quitRole.action",
			{"comingParams": JSON.stringify(objMap)},
			function(data){
				data = eval("("+data+")");
				if(data.key == "success"){
					$.sucmodal("提交成功", "退出角色成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
	});
}
//多个加入角色
function allJoinInRole() {
	var roleId = $("#hidden_role_id").val();
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要操作",2000);
		return;
	}
	var objMap = {"roleId" : roleId, "chkValue" : chk_value};
	$.post(
		"roleAction!joinInRole.action",
		{"comingParams":JSON.stringify(objMap)},
		function(data){
			data = eval("("+data+")");
			if(data.key == "success"){
				$.sucmodal("提交成功", "权限加入成功。",2000,function(){
					location.reload();
            	});
			}else{
				$.sucmodal("提交失败", "请于管理员联系",2000);
			}
		}
	);
}
//多个退出角色
function allQuitRole() {
	var roleId = $("#hidden_role_id").val();
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要操作",2000);
		return;
	}
	var objMap = {"roleId" : roleId, "chkValue" : chk_value};
	$.altmodal("删除", "您确定要退出所选的权限吗?",1000,function(){
		$.post(
			"roleAction!quitRole.action",
			{"comingParams":JSON.stringify(objMap)},
			function(data){
				data = eval("("+data+")");
				if(data.key == "success"){
					$.sucmodal("提交成功", "权限退出成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
	});
}