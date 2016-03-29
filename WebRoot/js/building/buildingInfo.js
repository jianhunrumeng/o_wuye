$(document).ready(function() {
	var buildingId = $("#hidden_building_id").val();
	getBuilding(buildingId);
});

function getBuilding(buildingId) {
	$.post(
	    "buildingAction!getBuilding.action",
	    {"inParma" : JSON.stringify({"buildingId" :buildingId})},
	    function(data) {
	    	data = eval("("+data+")");
	    	if (data.result == "true") {
	    		des(data);
			}else {
				alert("保存失败"+data.msg);
			}
	    	
	    }
	);
}

function des(data){
	var dataList = data.data;
	if (dataList != null){
		var building = dataList[0];
		var community = building.community;
		
		if (community != null){
			$("#communityName").text(community.communityName);
		}
		var ownerBuildingEntt = building.ownerBuildingEntt;
		$("#buildingName").text(building.buildingName);
		if (ownerBuildingEntt != null){
			//是单元
			$("#ownerBuilding").text(ownerBuildingEntt.buildingName);
		}else{
			$("#ownerBuilding").text("");
		}
		
		
		$("#buildingPhone").text(building.phone);
		$("#floorCount").text(building.floorCount);
		$("#stairArea").text(building.stairArea);
		if (building.finishTime != null && building.finishTime != "null" && building.finishTime != ""){
			$("#finishTime").text(building.finishTime.substr(0,10));
		}
		
		$("#userableArea").text(building.userableArea);
		$("#afforestArea").text(building.afforestArea);
		$("#ownershipType").text(building.ownershipType);
		$("#buildingStructure").text(building.buildingStructure);
		$("#upgradeCondition").text(building.upgradeCondition);
		
		
		if (building.statusCd == "1000"){
			$("#pp_status").text("有效");
		}else if (building.statusCd == "1100"){
			$("#pp_status").text("无效");
		}else{
			$("#pp_status").text("未知状态");
		}
		
		var leaderInfo = building.partyInfo;
		if (leaderInfo != null){
			$("#pp_leader_name").text(leaderInfo.partyName);
			$("#pp_leader_cert_type").text(leaderInfo.certTypeName);
			$("#pp_leader_cert_nbr").text(leaderInfo.certNbr);
			$("#pp_leader_phone").text(leaderInfo.mobile);
			$("#pp_leader_qq").text(leaderInfo.qq);
			$("#pp_leader_wx").text(leaderInfo.weiXin);
			$("#pp_email").text(leaderInfo.email);
		}
		
	}
}