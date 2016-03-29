$(document).ready(function() {
	
});

//保存修改车辆信息
function saveVehicle () {
	var hidden_vehicle_id = $("#hidden_vehicle_id").val();
	if (hidden_vehicle_id != "") {
		//代表修改
	}
	var plate_nbr = $("#plate_nbr").val();								//车牌号
	if (plate_nbr == "") {
		$.sucmodal("提示", "车牌号不能为空");
		return;
	}
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
	var parking_id = $("#parking option:selected").val();			//车位
	if (parking_id == "") {
		$.sucmodal("提示", "请选择车位");
		return;
	}
	
	var obj = {vehicle : {}, room : {}, parking : {}};
	obj.vehicle.vehicleId = hidden_vehicle_id;
	obj.vehicle.plateNbr = plate_nbr;
	obj.room.roomId = community_id;
	obj.parking.parkingId = parking_id;
	
	$.post("",{
		"comingParams" : JSON.stringify(obj)
	},function(data) {
		data = eval("("+data+")");
	});
}


//查询车辆类型列表
function loadVehicleList() {
	var search_content = $("input[name=keywords]").val();
	var search_type = $("#search-type option:selected").val();
	var obj = {};
	if (search_content != "") {
		if (search_type == "plate-nbr") {
			obj.plateNbr = search_content;
		} else if (search_type == "room") {
			obj.room = search_content;
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
					trStr += '<td class="with-checkbox"><input type="checkbox" name="check" value="'+ite.vehicleId+'" /></td>';
					trStr += '<td>'+ite.plateNbr+'</td>';
					trStr += '<td>'+ite.roomName+'</td>';  //这样要不要把小区/搂到单元/房间
					trStr += '<td>车位类型</td>';
					trStr += '<td>';
						trStr += '<a href="addVehicleInfo.jsp?vehicleId='+ite.vehicleId+';" class="btn">修改</a>';
						trStr += '<a href="javascript:removeVehicle('+ite.vehicleId+');" class="btn">删除</a>';
					trStr += '</td>';
					$("#listTable>tbody").append(trStr);
				});
	}
	
}

//删除车位类型
function removeVehicle(vId) {
	$.altmodal("删除", "您确定要删除车辆信息吗?",1000,function(){
		$.post("",{
			"comingParams" : vId
		},function(data) {
			data = eval("("+data+")");
		});
	});
}

function removeAllVehicle() {
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要删除的车辆信息",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的车辆信息吗?",1000,function(){
		$.post(
			"",
			{"comingParams":JSON.stringify(chk_value)},
			function(data){
				data = eval("("+data+")");
				if(data.key == "success"){
					$.sucmodal("提交成功", "车辆删除成功。",2000,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", "请于管理员联系",2000);
				}
			}
		);
	});
}