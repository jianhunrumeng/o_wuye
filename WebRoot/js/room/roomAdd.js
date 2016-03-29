$(document).ready(function() {
	$("#submit_btn").bind("click", saveRooms);
	
	var company = new CompanyObj("#pp_company").add(new ComunityObj("#community",{select: function(){
		$("#building").attr("disabled",false);
		$("#building").selectDiv("val",{communityId:$("#community").attr("data-id")});
	},init: function(){
		$("#building").attr("disabled",true);
	},clear:function(){
		$("#building").attr("disabled",true);
	}}));
	company.complete();
	
});
function addRow(obj){
	$(obj).parents("#rooms a").attr("disabled","true");
	var cloneRow = $(obj).parents(".room-row").clone();
	
	$("#rooms").append(cloneRow);
}
function removeRow(obj){
	var len = $("#rooms .room-row").length ;
	if (len != 1){
		$(obj).parents(".room-row").remove();
	}
}
function saveRooms(){
	if (StrUtil.isEmpty($(".proCitySelAll").data("buildingId"))){
		$.sucmodal("提示", "请选择楼栋");
		return;
	}
	var buildingId = $(".proCitySelAll").data("buildingId");
	var unitId = $(".proCitySelAll").data("unitId");
	var buildingName = $(".proCitySelAll").data("buildingName");
	var unitName = $(".proCitySelAll").data("unitName");
	var jsarray = new Array();
	
	var inparam = {data:{}};
	var isSave = true;
	$(".room-row").each(function(index,element){
		var roomNbr = $(this).find("input[name='roomNbr']").val();
		if (StrUtil.isEmpty(roomNbr)){
			$.sucmodal("提示", "房间号不能为空");
			isSave = false;
			return false;
		}
		var ownerName = $(this).find("input[name='ownerName']").val();
		var ownerTel = $(this).find("input[name='ownerTel']").val();
		var floor = $(this).find("input[name='floor']").val();//所在楼层
		if (!StrUtil.isPositiveInt(floor)){
			$.sucmodal("提示", "所在楼层应为正整数");
			isSave = false;
			return false;
		}
		for (var i = 0; i < jsarray.length; i++){
			if (roomNbr == jsarray[i].roomNbr){
				$.sucmodal("提示", "房间号重复了");
				isSave = false;
				return false;
			}
		}
		var room = {
				building : {}
		};
		var partyInfo = {};
		var user= {};
		if (StrUtil.isEmpty(unitId)){
			room.building.buildingId=buildingId;
		}else{
			room.building.buildingId=unitId;
		}
		
		room.roomNbr = roomNbr;
		room.floor=floor;
		if (!StrUtil.isEmpty(ownerTel) && StrUtil.isEmpty(ownerName)){
			$.sucmodal("提示", "请填写户主名称");
			isSave = false;
			return false;
		}
		if (!StrUtil.isEmpty(ownerTel)){
			user.account = ownerTel;
			partyInfo.user = user;
		}
		if (!StrUtil.isEmpty(ownerName)){
			partyInfo.partyName = ownerName;
			room.partyInfo = partyInfo;
		}
		
		
		jsarray.push(room);
		//building.buildingName = ;
	});
	
	if (!isSave){
		return;
	}
	
	inparam.data=jsarray;
	$.post("roomAction!saveRoom.action", {
		inParma : JSON.stringify(inparam)
	}, function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			$.sucmodal("提示", data.msg, function() {
				location.href = "roomManager.jsp";
			});
		} else {
			alert("保存失败" + data.msg);
		}
	});
}

function clickOk(){
	if (StrUtil.isEmpty($(".proCitySelAll").data("buildingId"))){
		$.sucmodal("提示", "请选择楼栋");
		return;
	}
	
	$("#desCompany").text($("#pp_company").val());
	$("#desCommunity").text($("#community").val());
	var buildingId = $(".proCitySelAll").data("buildingId");
	var unitId = $(".proCitySelAll").data("unitId");
	var buildingName = $(".proCitySelAll").data("buildingName");
	var unitName = $(".proCitySelAll").data("unitName");
	if (StrUtil.isEmpty(unitId) && unitId != buildingId){
		$("#desBuilding").text(buildingName);
	}else{
		$("#desBuilding").text(buildingName+"-"+unitName);
	}
}
