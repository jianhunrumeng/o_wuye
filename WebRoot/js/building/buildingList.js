function loadRoleList() {
	var buildingName = $("input[name=keywords]").val();
	$.post("buildingAction!getBuildingCount.action", {
		inParma : buildingName
	}, function(data) {
		data = eval("(" + data + ")");
		$("#demo").myPagination({
			currPage : 1,
			pageSize : 5,
			pageCount : data.pageCount,
			ajax : {
				on : true, // 开启状态
				// url:
				// "mbook!getMBookByCondition.action?type="+type+"&keyword="+keyword,
				// //访问服务器地址
				url : "buildingAction!getBuilding.action",
				dataType : 'json', // 返回类型
				callback : 'callBack',
				param : {
					"on" : true,
					inParma : JSON.stringify({"buildingName":buildingName})
				},
				ajaxStart : function() {
					return false;
				},
				onClick : function(page) {
					alert(page);
				}
			}
		});
	});
}

function callBack(data) {
	$("#listTable>tbody").children().remove();
	data = eval("(" + data + ")");
	if (data.result != "true") {
		alert("保存失败" + data.msg);
	} else {
		$(data.data)
		.each(
				function(ix, ite) {
					var trStr = '<tr>';
					trStr += '<td class="with-checkbox"><input type="checkbox" name="check" value="'
							+ ite.buildingId + '" /></td>';
					
					if (ite.community != null) {
						trStr += '<td>' + ite.community.communityName
								+ '</td>';
					} else {
						trStr += '<td></td>';
					}
					
					if(ite.ownerBuildingEntt != null){
						//单元
						trStr += '<td>' + ite.ownerBuildingEntt.buildingName
						+ '</td>';//楼栋列
						trStr += '<td>' + ite.buildingName + '</td>';//单元列
					}else{
						//楼栋
						trStr += '<td>' + ite.buildingName + '</td>';
						trStr += '<td></td>';//单元列为空
					}
					if(ite.phone != null && ite.phone != "null"){
						trStr += '<td>' + ite.phone + '</td>';
					}else{
						trStr += '<td></td>';
					}
					
					if (ite.partyInfo != null && typeof ite.partyInfo.partyName != "undefined") {
						trStr += '<td>' + ite.partyInfo.partyName + '</td>';
						trStr += '<td>' + ite.partyInfo.mobile + '</td>';
					} else {
						trStr += '<td></td>';
						trStr += '<td></td>';
					}

					if (ite.statusCd == '1000') {
						trStr += '<td>有效</td>';
					} else {
						trStr += '<td>失效</td>';
					}

					trStr += '<td><a href="editBuildingInfo.jsp?buildingId='
							+ ite.buildingId
							+ '" class="btn defined-role-btn" id="btn_edit_building_'
							+ ite.buildingId + '">修改</a>';
					// trStr +='<a
					// href="updateRolePrivilegeInfo.jsp?roleId='+ite.roleId+'"
					// class="btn defined-role-btn"
					// id="btn_edit_p_"'+ite.roleId+'>修改权限</a>';
					/*trStr += '<a href=javascript:removeCommunity('
							+ ite.communityId
							+ ',"'
							+ ite.communityName
							+ '"); class="btn defined-role-btn"  id="btn_del_community_"'
							+ ite.communityId + '>删除</a>';*/
					trStr += '<a href="buildingInfo.jsp?buildingId='
							+ ite.buildingId
							+ '" class="btn defined-buildingId-btn" id="btn_info_building_'
							+ ite.buildingId + '">详情</a></td>';
					trStr += '</tr>';
					$("#listTable>tbody").append(trStr);
				});
	}
	
}

function removeBuilding(buildingId,buildingName) {
	var chk_value = [];
	chk_value.push(buildingId);
	$.altmodal("删除", "您确定要删除("+buildingName+")吗?",1000,function(){
		$.post(
			"buildingAction!removeBuilding.action",
			{"inParma": JSON.stringify(chk_value)},
			function(data){
				data = eval("("+data+")");
				if(data.result == "true"){
					$.sucmodal("提交成功", "删除成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
	});
}

function removeBuilding() {
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要删除的楼栋",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的楼栋吗?",1000,function(){
		$.post(
			"buildingAction!removeBuilding.action",
			{"inParma":JSON.stringify(chk_value)},
			function(data){
				data = eval("("+data+")");
				if(data.result == "true"){
					$.sucmodal("提交成功", "删除成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
	});
}
