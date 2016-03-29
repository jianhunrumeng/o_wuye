$(document).ready(function() {
	var hRoomId = $("#hidden_room_id").val();
	if(hRoomId!=null&&hRoomId!=""){
		getRoom(hRoomId);
	}
});


function getRoom(roomId) {
	var inParma={};
	inParma.roomId=roomId;
	inParma.isQueryOne="yes";
	$.post(
	    "roomAction!getRoomList.action",
	    {jsondata : JSON.stringify(inParma)},
	    function(data) {
	    	data = eval("("+data+")");
	    	if (data!=null&&data.result=="true") {
	    		var room=data.pageInfo.dataList[0];
	    		if(room!=null&&room!=""){
	    			 $("#roomNbr").text(room.roomNbr);			//房间号
					 $("#housingFeeRate").text(room.housingFeeRate);				//物业折扣率
					 $("#statuCd").text(room.statusName);
					 if(room.partyInfo!=null&&room.partyInfo!=""){
						$("#partyName").text(room.partyInfo.partyName);				//业主
						$("#certType").text(room.partyInfo.certTypeName);//证件类型
					 	$("#certNbr").text(room.partyInfo.certNbr);//证件号
					 	$("#mobile").text(room.partyInfo.mobile);//联系电话
					 	$("#homePhone").text(room.partyInfo.homePhone);//家庭电话
					 }else{
						$("#partyName").text("");				//业主
						$("#certType").text("");//证件类型
					 	$("#certNbr").text("");//证件号
					 	$("#mobile").text("");//联系电话
					 	$("#homePhone").text("");//家庭电话
					 }
					 if(room.user!=null&&room.user!=""){
						 $("#userAccount").text(room.user.account);	
					 }else{
						 $("#userAccount").text("");	//注册手机号
					 }
					 if(room.buildingType!=""&&room.buildingType!=null){
						  $("#buildingTypeName").text(room.buildingType.buildingTypeName);	//房屋类型
					 }else{
						 $("#buildingTypeName").text("");
					 }
					
					 $("#floor").text(room.floor);//楼层
					 $("#housingOrientation").text(room.housingOrientation);//房屋朝
					  $("#usingState").text(room.usingStateName);//使用状态
					 $("#upgradeCondition").text(room.upgradeCondition);//装修状态
					 $("#upgradeStartDate").text(room.upgradeStartDate);//装修起始日期
					 $("#upgradeEndDate").text(room.upgradeEndDate);//装修结束日期
					 $("#housedDate").text(room.housedDate);//入住时
	    		}
	    	} else if (data.result == "false") {
	    		$.sucmodal("查询失败", "请于管理员联系"+data.msg,2000);
	    	}
	    	
	    }
	);
}