$(document).ready(function() {
	$("#submit_btn").bind("click", saveRoleInfo);
	var roleId=$("#hidden_role_id").val();
	var roleName = $("#hidden_role_name").val();
	var statusCd = $("#hidden_role_statusCd").val();
	$("#role_name").change(function() {
		$("#tip_role_name").text("*");
	});
	if(roleId!=null&&roleId!=""&&roleId!="null"){
		$("#reset_btn").css({"display":"none"});
		$("#role_name").val(decodeURI(roleName));//角色名称
	    $("#role_status option[value='"+statusCd+"']").attr("selected",true);//状态
	}
	initPrivilegeTree();
});
var treeNodes; 
function initPrivilegeTree() {
	var roleId = $("#hidden_role_id").val();
	$.ajax({  
        async : false,  
        cache:false,  
        type: 'POST', 
        data: {comingParams:roleId},
        dataType : "json",  
        url: "privilegeAction!getPrivilegeTree.action",//请求的action路径  
        error: function () {//请求失败处理函数  
            alert('请求失败');  
        },  
        success:function(data){ //请求成功后处理函数。    
        	data = eval("(" + data + ")");
            treeNodes = data.data;   //把后台封装好的简单Json格式赋给treeNodes  
        }  
    });
	var zTreeDemo = $.fn.zTree.init($("#cityTree"),setting, treeNodes);
}

var setting = {
	check: {
		enable: true,
		chkStyle: "checkbox",
		chkboxType: {"Y":"ps","N":"ps"}
	},
	data: {
		simpleData: {
			enable: true
		}
	}
};

function count() {
	var zTree = $.fn.zTree.getZTreeObj("cityTree"),
	checkCount = zTree.getCheckedNodes(true).length,
	nocheckCount = zTree.getCheckedNodes(false).length,
	changeCount = zTree.getChangeCheckedNodes().length;
	alert('选中：'+checkCount+';未选中：'+nocheckCount);
}

//保存角色信息
function saveRoleInfo() {
	var role_id = $("#hidden_role_id").val();
	var role_name = $("#role_name").val();
	var role_status = $("#role_status option:selected").val();
	
	if (role_name == "") {
		$("#tip_role_name").text("角色名称不能为空");
		return;
	}
	var params = {};
	params.roleName = role_name;
	params.statusCd	= role_status;
	var zTree = $.fn.zTree.getZTreeObj("cityTree");
	var pri = zTree.getCheckedNodes(true);
	if (pri.length >0) {
		var jsarray = new Array();
		for(i=0;i<pri.length;i++) {
			var privilege = {};
			privilege.privilegeId = pri[i].id;
			jsarray.push(privilege);
		}
		params.rolePrivileges = jsarray;
	}
	
	if (role_id == "0" || role_id=="null") {
		$.post("roleAction!saveRole.action",{
			comingParams : JSON.stringify(params)
		},function(data) {
			data = eval("(" + data + ")");
			if (data.result == "isExit") {
				$("#tip_role_name").text("角色名称已经存在");
			} else if (data.result = "true") {
				$.sucmodal("提示","角色保存成功" , function(){
					location.href="roleManager.jsp";
				});
			} else {
				alert("保存失败");
			}
		});
	} else {
		params.roleId = role_id;
		$.post("roleAction!saveRole.action",{
			comingParams : JSON.stringify(params)
		},function(data) {
			data = eval("(" + data + ")");
			if (data.result == "isExit") {
				$("#tip_role_name").text("角色名称已经存在");
			} else if (data.result = "true") {
				$.sucmodal("提示","角色修改成功" , function(){
					location.href="roleManager.jsp";
				});
			} else {
				alert("修改失败");
			}
		});
	}
}