$(document).ready(function() {
	loadParkTypeList();
});
//填出框
function addParkTypeModel(parkingTypeId) {
	
	if (parkingTypeId != null) {		//表示修改
		$("#hidden_party_type_id").val(parkingTypeId);
		$.post("parkingAction!getParkingType.action",{
			"inParma" : JSON.stringify({
				"parkingTypeId" : parkingTypeId})
		},function(data) {
			data = eval("(" + data + ")");
			if (data.result == "true") {
				var parkingTypes = data.data;
				if (parkingTypes != null){
					var parkingType = parkingTypes[0];
					$("#park_type_name").val(parkingType.parkingTypeName);
					$("#park_price").val(parkingType.price);
					$("#park_remark").val(parkingType.remark);
					$("#hidden_party_type_id").val(parkingType.parkingTypeId);
				}
			} else {
				alert("保存失败" + data.msg);
			}
		});
	} else{
		$("#park_type_name").val("");
		$("#park_price").val("");
		$("#park_remark").val("");
		$("#hidden_party_type_id").val("");
	}
	$("#myModal1").modal("show");
}

//保存修改车位类型
function saveParkType () {
	var parkingTypeId = $("#hidden_party_type_id").val();
	
	var community_id = $("input[name='hidden_community_id']").val();
	var park_type_name = $("#park_type_name").val();
	var park_price = $("#park_price").val();
	var park_remark = $("#park_remark").val();
	
	var obj = {community:{}};
	obj.parkingTypeId=parkingTypeId;
	obj.parkingTypeName = park_type_name;
	obj.community.communityId = community_id;
	obj.price= park_price;
	obj.remark = park_remark;
	var jsarray = new Array();
	var inparam = {data:{}};
	jsarray.push(obj);
	inparam.data=jsarray;
	
	$.post("parkingAction!saveParkingType.action",{
		"inParma" : JSON.stringify(inparam)
	},function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			location.href="parkingTypeManager.jsp?communityId="+community_id;
		} else {
			alert("保存失败" + data.msg);
		}
	});
}
//删除车位类型
function removeParkType(ptId) {
	$.post("parkingAction!removeParkingType.action",{
		"inParma" : ptId
	},function(data) {
		data = eval("("+data+")");
	});
}

//查询车位类型列表
function loadParkTypeList() {
	var parkTypeName = $("input[name=keywords]").val();
	var communityId =$("#hidden_community_id").val();
	
	$.post("parkingAction!getParkingType.action", {
		inParma : JSON.stringify({"parkingTypeName":parkTypeName,"communityId":communityId})
	}, function(data) {
		data = eval("(" + data + ")");
		$("#demo").myPagination({
			currPage : 1,
			pageSize : 5,
			pageCount : data.pageCount,
			ajax : {
				on : true, // 开启状态
				url : "parkingAction!getParkingType.action",
				dataType : 'json', // 返回类型
				callback : 'callBack',
				param : {
					"on" : true,
					inParma : JSON.stringify({"parkTypeName":parkTypeName,"communityId":communityId})
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
					trStr += '<td class="with-checkbox"><input type="checkbox" name="check" value="'+ite.parkingTypeId+'" /></td>';
					trStr += '<td>'+ite.parkingTypeName+'</td>';
					trStr += '<td>'+ite.price+'</td>';
					trStr += '<td>'+ite.remark+'</td>';
					trStr += '<td>';
						trStr += '<a href="javascript:addParkTypeModel('+ite.parkingTypeId+');" class="btn">修改</a>';
//						trStr += '<a href="javascript:removeParkType('+ite.parkingTypeId+');" class="btn">删除</a>';
					trStr += '</td>';
					$("#listTable>tbody").append(trStr);
				});
	}
	
}

function removeAllParkType() {
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要删除的车位类型",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的车位类型吗?",1000,function(){
		$.post(
			"parkingAction!removeParkingType.action",
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