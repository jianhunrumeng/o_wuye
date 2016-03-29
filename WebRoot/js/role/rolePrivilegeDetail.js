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
		    	$("#role_privilege_detail").html("");
		    	for (var i = 0; i < data.privilegeList.length; i ++) {
		    		var spanStr = "";
		    		spanStr += "<span class='rpd-detail'>"+data.privilegeList[i].privilegeName+"</span>";
		    		$("#role_privilege_detail").append(spanStr);
		    	}
		  
	    	} else if (data.isExit == "no") {
	    		$.sucmodal("提交失败", "请于管理员联系",2000);
	    	}
	    	
	    }
	);
}