function clickSearch(){
	var roomName = $("input[name=keywords]").val();
	var inParma={};
	inParma.roomName=roomName;
	$.post(
		"roomAction!getRoom.action",
		{inParma : JSON.stringify(inParma)},
		function(data){
			data = eval("("+data+")");
			if(data!=null&&data.result=="true"){
				$("#demo").myPagination({
				currPage: 1,
				pageSize: 5,
				pageCount:data.pageCount,
				ajax:{
					on: true,                        //开启状态
		            // url: "mbook!getMBookByCondition.action?type="+type+"&keyword="+keyword,            //访问服务器地址
					url: "roomAction!getRoom.action",
		            dataType: 'json',                //返回类型
		            callback:'callBack',
		            param:{"on":true, inParma : JSON.stringify(inParma)},
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
	$(data.data).each(function(ix,ite){
		var trStr = '<tr>';
			trStr += '<td class="with-checkbox"><input type="checkbox" name="check" value="'+ite.roomId+'" /></td>';
			//小区
			if (ite.community != null ){
				trStr += '<td>'+StrUtil.strnull(ite.community.communityName)+'</td>';
			}else{
				trStr += '<td></td>';
			}
			
			if(ite.building != null){
				var buildingName = StrUtil.strnull(ite.building.buildingName);
				if (ite.unit != null){
					buildingName += "-" + StrUtil.strnull(ite.unit.buildingName);
				}
				trStr += '<td>'+buildingName+'</td>';
			}else{
				trStr += '<td></td>';
			}
			/*if(ite.building != null && ite.building.buildingName!=null){
				trStr += '<td>'+ite.building.buildingName+'</td>';
			}else{
				trStr += '<td></td>';
			}*/
			trStr += '<td>'+StrUtil.strnull(ite.roomNbr)+'</td>';
			//trStr += '<td>'+strUtil.Strnull(ite.partyInfo.partyName)+'</td>';
			if(ite.ownerInfo != null ){
				trStr += '<td>'+StrUtil.strnull(ite.ownerInfo.partyName)+'</td>';
				trStr += '<td>'+StrUtil.strnull(ite.ownerInfo.account)+'</td>';
				
			}else{
				trStr += '<td></td><td></td>';
			}
//		if (ite.statusCd == '1000') {
//			trStr += '<td>有效</td>';
//		} else {
//			trStr += '<td>失效</td>';
//		}
//		trStr += '<td>'+ite.upgradeCondition+'</td>';
//		trStr += '<td>'+""+'</td>';
		if (ite.housingFeeRate != null){
			trStr += '<td>'+ite.housingFeeRate+'</td>';
		}else{
			trStr += '<td></td>';
		}
		
		if (ite.statusName != null){
			trStr += '<td>'+ite.statusName+'</td>';
		}else{
			trStr += '<td></td>';
		}
		
//		trStr += '<td>'+ite.upgradeEndDate.substring(0,10)+'</td>';
//		trStr += '<td>'+ite.upgradeStartDate.substring(0,10)+'</td>';
//		trStr += '<td>'+ite.housedDate.substring(0,10)+'</td>';
		trStr +='<td><a href="editRoomInfo.jsp?roomId='+ite.roomId+'" class="btn defined-role-btn" >修改</a>';
//		trStr += '<td><a href="javascript:updatePropertyCompany('+ite.companyId+');" data-toggle="modal" class="btn defined-role-btn" id="btn_edit_role_"'+ite.companyId+'>修改</a>';
//		trStr +='<a href=javascript:removePropertyCompany('+ite.companyId+',"'+ite.companyName+'"); class="btn defined-role-btn"  id="btn_del_role_"'+ite.companyId+'>删除</a>';
		trStr +='<a href="roomInfo.jsp?roomId='+ite.roomId+'" class="btn defined-role-btn" >详情</a></td>';
		trStr += '</tr>';
		$("#listTable>tbody").append(trStr);
	});
}


function removePropertyCompany() {
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要删除的房间!",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的房间信息吗?",1000,function(){
		$.post(
				"roomAction!removeRoom.action",
				{inParma: JSON.stringify(chk_value)},
				function(data){
					data = eval("("+data+")");
					if(data.key == "success"){
						$.sucmodal("提交成功", "房间删除成功。",2000,function(){
							location.reload();
		            	});
					}else{
						$.sucmodal("提交失败", "请于管理员联系"+data.msg,2000);
					}
				}
			);
	});
}
