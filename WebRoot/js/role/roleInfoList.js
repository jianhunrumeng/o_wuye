function loadRoleList(){
	var roleName = $("input[name=keywords]").val();
	$.post(
		"roleAction!getRoleList.action",
		{comingParams : roleName},
		function(data){
			data = eval("("+data+")");
			$("#demo").myPagination({
				currPage: 1,
				pageSize: 5,
				pageCount:data.pageInfo.totalPageCount,
				ajax:{
					on: true,                        //开启状态
		            // url: "mbook!getMBookByCondition.action?type="+type+"&keyword="+keyword,            //访问服务器地址
					url: "roleAction!getRoleList.action",
		            dataType: 'json',                //返回类型
		            callback:'callBack',
		            param:{"on":true, comingParams : roleName},
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
	$("#listTable>tbody").children().remove();
	data = eval("("+data+")");
	$(data.pageInfo.dataList).each(function(ix,ite){
		var trStr = '<tr>';
		trStr += '<td class="with-checkbox"><input type="checkbox" name="check" value="'+ite.roleId+'" /></td>';
		trStr += '<td>'+ite.roleName+'</td>';
		trStr += '<td>'+ite.createDate+'</td>';
		trStr += '<td>'+ite.updateDate+'</td>';
		if (ite.statusCd == '1000') {
			trStr += '<td>有效</td>';
		} else {
			trStr += '<td>失效</td>';
		}

		trStr += '<td><a href="addRoleInfo.jsp?roleId='+ite.roleId+'&roleName='+encodeURI(encodeURI(ite.roleName))+'&statusCd='+ite.statusCd+'" data-toggle="modal" class="btn defined-role-btn" id="btn_edit_role_"'+ite.roleId+'>修改</a>';
		//trStr +='<a href="updateRolePrivilegeInfo.jsp?roleId='+ite.roleId+'" class="btn defined-role-btn" id="btn_edit_p_"'+ite.roleId+'>修改权限</a>';
		//trStr +='<a href=javascript:removeRole('+ite.roleId+',"'+ite.roleName+'"); class="btn defined-role-btn"  id="btn_del_role_"'+ite.roleId+'>删除角色</a>';
		trStr +='<a href="roleDetailInfo.jsp?roleId='+ite.roleId+'" class="btn defined-role-btn" >详情</a></td>';
		trStr += '</tr>';
		$("#listTable>tbody").append(trStr);
	});
}

function updateRole(roleId) {
	$("#myModal1").modal("show");
	$("#role-model-title").html('<i class="fa fa-key"></i>修改角色');
	$("#hidden_role_id").val(roleId);
	$.post("roleAction!getRole.action",{
		comingParams : roleId
	},function(data){
		data = eval("("+data+")");
		$("#role_name").val(data.role.roleName);
		$("#hidden_role_id").val(data.role.roleId);
		var status = data.role.statusCd;
		
		$("#role_status option[value='"+status+"']").attr("selected",true);
		
	});
}

function addRoleMode() {
	$("#myModal1").modal("show");
	$("#role-model-title").html('<i class="fa fa-key"></i>新增角色');
}

function removeRole(roleId,roleName) {
	var chk_value = [];
	chk_value.push(roleId);
	$.altmodal("删除", "您确定要删除("+roleName+")角色吗?",1000,function(){
		$.post(
			"roleAction!removeRole.action",
			{"comingParams": JSON.stringify(chk_value)},
			function(data){
				data = eval("("+data+")");
				if(data.key == "success"){
					$.sucmodal("提交成功", "角色删除成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
	});
}

function removeAllRole() {
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要删除的角色",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的角色吗?",1000,function(){
		$.post(
			"roleAction!removeRole.action",
			{"comingParams":JSON.stringify(chk_value)},
			function(data){
				data = eval("("+data+")");
				if(data.key == "success"){
					$.sucmodal("提交成功", "角色删除成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
	});
}