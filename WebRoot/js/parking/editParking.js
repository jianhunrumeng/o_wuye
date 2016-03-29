$(document).ready(function() {
	// $("#submit_btn").bind("click", saveCommunity);
	var parking_id = $("#hidden_parking_id").val();
	if (parking_id != null && parking_id != "" && parking_id != "null") {
		getParking(parking_id);
	};
});

function getParking(parking_id){
	$.post("parkingAction!getParking.action", {
		"inParma" : JSON.stringify({
			"parkingId" : parking_id
		})
	}, function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			des(data);
		} else {
			alert("保存失败" + data.msg);
		}

	});
}

function des (data) {
	var dataList = data.data;
	if (dataList != null) {
		var parkingType = dataList[0];

	}
}

function saveParking(){
	var parking_nbr = $("#parking_nbr").val();
	if (parking_nbr == null || parking_nbr == "null" || parking_nbr == ""){
		$.sucmodal("提示", "车位编码不能为空");
		return;
	}

	var communityId = $("#community_name").attr("data-id");
	if (communityId == null || communityId == "null" || communityId == ""){
		$.sucmodal("提示", "小区不能为空");
		return;
	}

	var roomId = $("#room").attr("data-id");
	var parkingType = $("#parking_type option:selected").val();
	if (parkingType == null || parkingType == "null" || parkingType == ""){
		$.sucmodal("提示", "车位类型不能为空");
		return;
	}

	var parkingPosition = $("#parking_position").val();
	var plateNbr = $("#plate_nbr").val();
	if (plateNbr != null && plateNbr != "" && plateNbr != "null") {
		if (roomId == null || roomId == "null" || roomId == ""){
			$.sucmodal("提示", "请选择车辆对应房间");
			return;
		}

	};
	var jsarray = new Array();
	var parking = {
			community : {},
			parkingType:{},
			vehicleRel :{
				room :{}
			}
	};
	parking.parkingNbr=parking_nbr;
	parking.community.communityId=communityId;
	if (roomId == null || roomId == "null" || roomId == "") {
		parking.vehicleRel.room.roomId=roomId;
	};
	parking.parkingType.parkingTypeId=parkingType;
	parking.parkingPosition=parkingPosition;

	parking.vehicleRel.plateNbr=plateNbr;
	jsarray.push(parking);

	var inparam = {data:{}};
	inparam.data = jsarray;
	$.post("parkingAction!saveParking.action", {
		inParma : JSON.stringify(inparam)
	}, function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			$.sucmodal("提示", data.msg, function() {
				location.href = "parkingManager.jsp";
			});
		} else {
			alert("保存失败" + data.msg);
		}
	});
}
