$(document).ready(function() {
	$("#submit_btn").bind("click", saveRoom);
	var hRoomId = $("#hidden_room_id").val();
	if(hRoomId!=null&&hRoomId!=""){
		getRoom(hRoomId);
	}
});

//保存物业公司方法
function saveRoom() {
	var communityId = $("#community").attr("data-id");
	if(communityId==""||communityId==null){
		$.sucmodal("提示","请选择小区");
		return;
	}
	var buildingId= $("#building_name").attr("data-id");
	if(buildingId==null||buildingId==null){
		$.sucmodal("提示","请选择楼栋");
	}
	var roomNbr = $("#roomNbr").val();			//房间号
	if (roomNbr == "") {
		$.sucmodal("提示","房间号必填");
		return;
	}
	var housingFeeRate = $("#housingFeeRate").val();				//物业折扣率
	if (housingFeeRate == "") {
		$.sucmodal("提示","物业折扣率必填");
		return;
	}
	var partyName = $("#partyName").val();				//业主
	if (partyName == "") {
		$.sucmodal("提示","业主必填");
		return;
	}
	var userAccount = $("#userAccount").val();				//注册手机号
	if (userAccount == "") {
		$.sucmodal("提示","注册手机号必填");
		return;
	}

	var buildingTypeName = $("#buildingTypeName").val();	//房屋类型
	var floor = $("#floor").val();//楼层
	var housingOrientation = $("#housingOrientation").val();//房屋朝
	var usingState = $("#usingState").val();//使用状态
	var upgradeCondition = $("#upgradeCondition").val();//装修状态
	var upgradeStartDate = $("#upgradeStartDate").val();//装修起始日期
	var upgradeEndDate = $("#upgradeEndDate").val();//装修结束日期
	var housedDate = $("#housedDate").val();//入住时
	var certType = $("#certType").val();//证件类型
	var certNbr = $("#certNbr").val();//证件号
	var mobile = $("#mobile").val();//联系电话
	var homePhone = $("#homePhone").val();//家庭电话
				
	var jsarray = new Array();
	var room={community:{},building:{},buildingType:{},partyInfo:{},user:{}};
	var inparam = {data:{}};
	room.community.communityId=communityId;
	room.building.buildingId=buildingId;
	room.roomNbr=roomNbr;
	room.housingFeeRate=housingFeeRate;
	room.statuCd=statuCd;
	room.buildingType.buildingTypeName=buildingTypeName;
	room.floor=floor;
	room.housingOrientation=housingOrientation;
	room.usingState=usingState;
	room.upgradeCondition=upgradeCondition;
	room.upgradeStartDate=upgradeStartDate;
	room.upgradeEndDate=upgradeEndDate;
	room.housedDate=housedDate;
	
	//用户
	room.user.account=userAccount;
	//业主信息
	room.partyInfo.partyName=partyName;
	room.partyInfo.certType=certType;
	room.partyInfo.certNbr=certNbr;
	room.partyInfo.mobile=mobile;
	room.partyInfo.homePhone=homePhone;
	
	
	var hRoomId = $("#hidden_room_id").val()
	if(hRoomId!=null&&hRoomId!=""){
		room.roomId=hRoomId;
	}
//	inParma.organization.upOrgId="0";//物业公司没有上级组织
	jsarray.push(room);
	inparam.data=jsarray;

//	inParma.address.addressId="312";
//	var pzJson = "{'pp_leader_name':'"+pp_leader_name+"','area_area':'"+area_area+"'}";
	$.post("roomAction!addRoom.action",{
		inParma : JSON.stringify(inParma)
	},function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			$.sucmodal("提示",data.msg , function(){
				location.href="roomManager.jsp";
			});
		}else {
			alert("保存失败"+data.msg);
		}
	} );
	
}

function getRoom(roomId) {
	var inParma={};
	inParma.roomId=roomId;
	inParma.isQueryOne="yes";
	$.post(
	    "roomAction!getRoomList.action",
	    {inParma : JSON.stringify(inParma)},
	    function(data) {
	    	data = eval("("+data+")");
	    	if (data!=null&&data.result=="true") {
	    		var room=data.pageInfo.dataList[0];
	    		if(room!=null&&room!=""){
	    			 $("#roomNbr").val(room.roomNbr);			//房间号
					 $("#housingFeeRate").val(room.housingFeeRate);				//物业折扣率
					 $("#statuCd option[value='" + room.statuCd + "']")
								.attr("selected", true);
					 $("#partyName").val(room.partyInfo.partyName);				//业主
					 $("#userAccount").val(room.user.account);				//注册手机号
					 $("#buildingTypeName").val(room.buildingType.buildingTypeName);	//房屋类型
					 $("#floor").val(room.floor);//楼层
					 $("#housingOrientation").val(room.housingOrientation);//房屋朝
					  $("#usingState option[value='" + room.usingState + "']")
								.attr("selected", true);//使用状态
					 $("#upgradeCondition").val(room.upgradeCondition);//装修状态
					 $("#upgradeStartDate").val(room.upgradeStartDate);//装修起始日期
					 $("#upgradeEndDate").val(room.upgradeEndDate);//装修结束日期
					 $("#housedDate").val(room.housedDate);//入住时
					 $("#certType").val(room.certType);//证件类型
					 $("#certNbr").val(room.certNbr);//证件号
					 $("#mobile").val(room.mobile);//联系电话
					 $("#homePhone").val(room.homePhone);//家庭电话
	    		}
	    	} else if (data.result == "false") {
	    		$.sucmodal("查询失败", "请于管理员联系"+data.msg,2000);
	    	}
	    	
	    }
	);
}