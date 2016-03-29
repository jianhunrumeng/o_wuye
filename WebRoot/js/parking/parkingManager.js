$(document).ready(function() {
	$("#search-type").change(function() {
		var v = $(this).val();
		if (v == "parking-status") {
			$("#parking-status-value").css({"display":"inline-block"});
			$("#keyword-input").css({"display":"none"});
		} else {
			$("#keyword-input").css({"display":"inline-block"});
			$("#parking-status-value").css({"display":"none"});
		}
	});
});
/*
//保存修改车位类型
function saveParking () {
	var hidden_parking_id = $("#hidden_parking_id").val();
	if (hidden_parking_id != "") {
		//表示做修改
	}
	var parking_nbr = $("#parking_nbr").val();								//车位编码
	var property_company_id = $("#property_company_name").attr("data-id");  //物业公司ID
	if (property_company_id == "") {
		$.sucmodal("提示", "请搜索物业公司");
		return;
	}
	var community_id = $("#community_name").attr("data-id");			//小区ID
	if (community_id == "") {
		$.sucmodal("提示", "请搜索小区");
		return;
	}
	var building_id = $("#building_name").attr("data-id");			//搂到单元ID
	if (building_id == "") {
		$.sucmodal("提示", "请搜索楼栋单元");
		return;
	}
	var room_id = $("#room").attr("data-id");			//房间ID
	if (room_id == "") {
		$.sucmodal("提示", "请搜索房间");
		return;
	}
	var parking_type = $("#parking_type option:selected").val();			//车位类型
	
	var parking_position = $("input[name='parking_position']").val();		//位置
	var plate_nbr = $("#plate_nbr").val();									//车牌号

	
	var obj = {vehicle : {}, room : {}, parking : {}, parkingType : {}, community : {}};
	
	obj.vehicle.plateNbr = plate_nbr;
	obj.room.roomId = room_id;
	obj.parking.parkingNbr = parking_nbr;
	obj.parking.parkingPosition = parking_position;
	obj.parkingType.parkingTypeId = parking_type;
	obj.community.communityId = community_name_id;
	
	$.post("",{
		"comingParams" : JSON.stringify(obj)
	},function(data) {
		data = eval("("+data+")");
	});
}*/


//查询车位类型列表
function loadParkingList() {
	var search_content = $("input[name=keywords]").val();
	var search_type = $("#search-type option:selected").val();
	var obj = {};
	if (search_content != "") {
		if (search_type == "parking-nbr") {
			obj.parkingNbr = search_content;
		} else if (search_type == "parking-status") {
			obj.statusCd = search_content;
		} else {
			obj.communityId = search_content;
		}
	}
	$.post("", {
		comingParams : JSON.stringify(obj)
	}, function(data) {
		data = eval("(" + data + ")");
		$("#demo").myPagination({
			currPage : 1,
			pageSize : 5,
			pageCount : data.pageCount,
			ajax : {
				on : true, // 开启状态
				url : "",
				dataType : 'json', // 返回类型
				callback : 'callBack',
				param : {
					"on" : true,
					comingParams : JSON.stringify(obj)
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
		alert("查询失败" + data.msg);
	} else {
		$(data.data)
		.each(
				function(ix, ite) {
					var trStr = '<tr>';
					trStr += '<td class="with-checkbox"><input type="checkbox" name="check" value="'+ite.parkingId+'" /></td>';
					trStr += '<td>'+ite.parkingNbr+'</td>';
					trStr += '<td>'+ite.vehicleRel.plateNbr+'</td>';
					trStr += '<td></td>';
					trStr += '<td>'+ite.vehicleRel.room.roomName+'</td>';
					if (ite.statusCd == "1000") {   //状态值定义
						trStr += '<td>使用</td>';
					} else {						
						trStr += '<td></td>';
					}
					trStr += '<td>';
						trStr += '<a href="javascript:addParkingInfo.jsp?parkingId='+ite.parkingId+';" class="btn">修改</a>';
						trStr += '<a href="javascript:removeParking('+ite.parkingId+');" class="btn">删除</a>';
					trStr += '</td>';
					$("#listTable>tbody").append(trStr);
				});
	}
	
}
/*
//删除车位类型
function removeParking(pId) {
	$.altmodal("删除", "您确定要删除车位信息吗?",1000,function(){
		$.post("",{
			"comingParams" : pId
		},function(data) {
			data = eval("("+data+")");
		});
	});
}
*/
function removeAllParking() {
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要删除的车位信息",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的车位信息吗?",1000,function(){
		$.post(
			"parkingAction!removeParking.action",
			{"inParma":JSON.stringify(chk_value)},
			function(data){
				data = eval("("+data+")");
				if(data.key == "success"){
					$.sucmodal("提交成功", "车位删除成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
	});
}