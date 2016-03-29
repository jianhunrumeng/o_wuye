/**
 * 初始化.
 */
$(document).ready(function() {
	$("#submit_btn").bind("click", savePrivilege);
	var privilegeId=$("#hidden_privilege_id").val();
	if(privilegeId!=null&&privilegeId!=""&&privilegeId!="null"){
		$("#reset_btn").css({"display":"none"});
		getPrivilege(privilegeId);
	} else {
		getParentPrivilege(privilegeId);
	}
});

/**
 * 上级权限处理。
 */
function getParentPrivilege(privilegeId){
	var priType = '101';
	$.post("privilegeAction!getParentPrivilege.action",{jsondata : priType}, function(data) {
		var ret = eval("(" + data + ")");
		if (ret.result == "true"){
			var opt = "<option value=''>请选择</option>";
			$(ret.data).each(function(ix, ite){
				var selected = "";
				if (privilegeId != null && privilegeId == ite.privilegeId) {				
					selected = "selected = 'selected'";
				}
				opt += "<option value='"+ite.privilegeId+"' "+selected+">"+ite.privilegeName+"</option>";
			}); 
			$("#pri_parent").html("");
			$("#pri_parent").append(opt);
		}else{
			alert(data.msg);
		}
	});
}

/**
 * 权限信息保存。
 * @return {TypeName} 
 */
function savePrivilege() {
	var pri_name = $("#pri_name").val(); // 公司名称
	if (pri_name == "" || pri_name=="null" || pri_name==null){
		$.sucmodal("提示", "权限名称必填");
		return;
	}
	var pri_path = $("#pri_path").val();//权限路径
	
	var pri_status = $("#pri_status option:selected").val();
	var pri_parent = $("#pri_parent option:selected").val();
	var pri_type = $("#pri_type option:selected").val();
	
	var privilege= {};
	var jsarray = new Array();
	var inparam = {data:{}};
	
	privilege.privilegeName = pri_name;//权限名称
	privilege.statusCd = pri_status;// 权限状态
	privilege.path = pri_path;// 权限路径
	privilege.parentPrivilegeId = pri_parent;// 上级权限
	privilege.privilegeType = pri_type;//权限类型
	var privilegeId=$("#hidden_privilege_id").val();
	if(privilegeId!=null&&privilegeId!=""&&privilegeId!="null"){
		privilege.privilegeId = privilegeId;
	}

	jsarray.push(privilege);
	inparam.data=jsarray;
	$.post("privilegeAction!savePrivilege.action", {
		jsondata : JSON.stringify(inparam)
	}, function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			$.sucmodal("提示", data.msg, function() {
				location.href = "privilegeManager.jsp";
			});
		} else {
			alert("保存失败" + data.msg);
		}
	});
}


function getPrivilege(privilegeId) {
	var jsondata={};
	jsondata.privilegeId=privilegeId;
	jsondata.isQueryOne="yes";
	$.post(
	    "privilegeAction!qryPrivilegeList.action",
	    {jsondata : JSON.stringify(jsondata)},
	    function(data) {
	    	data = eval("("+data+")");
	    	if (data!=null&&data.result=="true") {
	    		var privilege=data.pageInfo.dataList[0];
	    		if (privilege!=null && privilege!=""){
	    			$("#pri_name").val(privilege.privilegeName);//权限名称
	    			$("#pri_path").val(privilege.path);//权限路径
	    			$("#pri_type option[value='"+privilege.privilegeType+"']").attr("selected",true);//权限类型
	    			$("#pri_status option[value='"+privilege.statusCd+"']").attr("selected",true);//状态
	    			getParentPrivilege(privilege.parentPrivilegeId);
	    			//$("#pri_parent option[value='"+privilege.parentPrivilegeId+"']").attr("selected",true);//上级权限
	    		}
	    	} else if (data.result == "false") {
	    		$.sucmodal("查询失败", "请于管理员联系"+data.msg,2000);
	    	}
	    	
	    }
	);
}