function loadRoleList() {
	var communityName = $("input[name=keywords]").val();
	$.post("communityAction!getCommunityCount.action", {
		inParma : JSON.stringify({"communityName":communityName})
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
				url : "communityAction!getCommunity.action",
				dataType : 'json', // 返回类型
				callback : 'callBack',
				param : {
					"on" : true,
					inParma : JSON.stringify({"communityName":communityName})
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
							+ ite.communityId + '" /></td>';
					if (ite.propertyCompany != null) {
						trStr += '<td>' + ite.propertyCompany.companyName
								+ '</td>';
					} else {
						trStr += '<td></td>';
					}

					trStr += '<td>' + ite.communityName + '</td>';
					if (ite.communityInfo != null) {
						trStr += '<td>' + ite.communityInfo.officePhone
								+ '</td>';
					} else {
						trStr += '<td></td>';
					}
					if (ite.partyInfo != null) {
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

					trStr += '<td><a href="addCommunityInfo.jsp?communityId='
							+ ite.communityId
							+ '" class="btn defined-role-btn" id="btn_edit_community_'
							+ ite.communityId + '">修改</a>';
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
					trStr += '<a href="communityInfo.jsp?communityId='
							+ ite.communityId
							+ '" class="btn defined-communityId-btn" id="btn_info_community_'
							+ ite.communityId + '">详情</a>';
					trStr += '<a href="communityRelManager.jsp?communityId='
						+ ite.communityId
						+ '" class="btn defined-communityId-btn" id="btn_info_community_'
						+ ite.communityId + '">相关设置</a></td>';
					trStr += '</tr>';
					$("#listTable>tbody").append(trStr);
				});
	}
	
}

function removeCommunity(communityId,communityName) {
	var chk_value = [];
	chk_value.push(communityId);
	$.altmodal("删除", "您确定要删除("+communityName+")吗?",1000,function(){
		$.post(
			"communityAction!removeCommunity.action",
			{"inParma": JSON.stringify(chk_value)},
			function(data){
				data = eval("("+data+")");
				if(data.result == "true"){
					$.sucmodal("提交成功", "小区删除成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
	});
}

function removeCommunity() {
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要删除的小区",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的小区吗?",1000,function(){
		$.post(
			"communityAction!removeCommunity.action",
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
