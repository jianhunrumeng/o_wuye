$(document).ready(
		function() {
			var attrCd = [ 'right_type', 'building_type',
					'building_structure', 'upgrade_condition', 'use',
					'house_state', 'housing_orientation', 'using_state',
					'upgrade_state' ];
			$.post("communityAction!getRelSetting.action", {
				"inParma" : JSON.stringify(attrCd)
			}, function(data) {
				data = eval("(" + data + ")");
				if (data.result == "true") {
					des(data);
				} else {
					$.sucmodal("查询失败", "请于管理员联系", 2000);
				}
			});
			
			$.post();
		});

function des(data) {
	var dataList = data.data;
	if (dataList != null) {
		$(dataList)
				.each(
						function(ix, ite) {
							var hr = '<a href="javascript:void(0)" data-toggle="modal" class="tile tile-themed defined-modal" data-attrId="'
									+ ite.attrId
									+ '" data-attrName="'
									+ ite.attrName
									+ '"> <i'
									+ ' class="fa fa-ticket"></i><div class="tile-info"><strong>'
									+ ite.attrName + '</strong></div> </a>';
							$("#relSetting").append(hr);
							/*if ((ix + 1) % 4 == 0) {
								$("#relSetting").append("<br/>");
							}*/

						});
		definedModal();
	}
}
function definedModal() {
	$(".defined-modal").click(function() {
		var community_id = $("#hidden_community_id").val();
		var v_attrId = $(this).attr("data-attrId");
		var v_attrName = $(this).attr("data-attrName");
		$(".modal-title").html('<i class="fa fa-key"></i>'+v_attrName+'设置');
		$("#hidden_attr_id").val(v_attrId);
		$.post("communityAction!getAttrValue.action", {
			"inParma" : JSON.stringify({
				"attrId" : v_attrId,
				"communityId" : community_id
			})
		}, function(data) {
			desValue(data);
		});
		
	});
}
function desValue(data) {
	var community_id = $("#hidden_community_id").val();
	data = eval("(" + data + ")");
	if (data.result != "true") {
		alert("查询失败" + data.msg);
	} else {
		var ht = '';
		$(data.data).each(function(ix, ite) {
			ht += '<div style="margin: 3px;"><a'; 
			if((community_id != null && community_id != "null" && community_id != "")){
				if ((ite.communityId != null && ite.communityId != "null" && ite.communityId != "")){
					//只能修改小区自有属性
					ht += ' class="icon-remove defined-del" style="margin-left:2px; margin-right:2px;"></a><a class="icon-edit defined-edit" style="margin-left:2px; margin-right:2px;"></a><input type="text" value="'+ite.attrValueName+'" class="value-input" style="border: none;"  action-data="KEEP" old-value="'+ite.attrValueName+'" data-id="'+ite.attrValueId+'" disabled="disabled"></div>';
				}else{
					ht += ' style="margin-left:2px; margin-right:2px;"></a><a  style="margin-left:2px; margin-right:2px;"></a><input type="text" value="'+ite.attrValueName+'" class="value-input" style="border: none;"  action-data="KEEP" old-value="'+ite.attrValueName+'" data-id="'+ite.attrValueId+'" disabled="disabled"></div>';
				}
			}else{
				ht += ' class="icon-remove defined-del" style="margin-left:2px; margin-right:2px;"></a><a class="icon-edit defined-edit" style="margin-left:2px; margin-right:2px;"></a><input type="text" value="'+ite.attrValueName+'" class="value-input" style="border: none;"  action-data="KEEP" old-value="'+ite.attrValueName+'" data-id="'+ite.attrValueId+'" disabled="disabled"></div>';
			}
			
			
		});
		ht += '<div style="margin: 3px;"><a class="icon-plus defined-add" style="margin-left:2px; margin-right:2px;"></a></div>';
		$('#id_modal-body').html("");
		$('#id_modal-body').append(ht);
		$("#myModal1").modal("show");
	}
	actionDeal();
	// /
	/*data = eval("(" + data + ")");
	$("#role_name").val(data.role.roleName);
	$("#hidden_role_id").val(data.role.roleId);
	var status = data.role.statusCd;

	$("#role_status option[value='" + status + "']").attr("selected", true);*/
}

function saveAttrValue() {
	var community_id = $("#hidden_community_id").val();
	var attrId = $("#hidden_attr_id").val();
	$(".value-input").attr("disabled",true).css({"border":"0px solid green"});
	var dc = $(".value-input");
	var objArr = [];
	$.each(dc,function(n,value) {
		
		console.info(value.attributes);
		var cAttr = value.attributes;
		var obj = {};
		
		if (value.value == "" && v4 == "ADD") {
			return;
		}
		obj.attrValue = $(value).val();
		var action = $(value).attr("action-data");
		obj.action = action;
		obj.oldAttrValue = $(value).attr("old-value");
		obj.attrValueId = $(value).attr("data-id");
		obj.attrId = attrId;
		if (action == "ADD" && community_id != null && community_id !=""){
			//新增时如果小区标识不为空时表示该属性为小区独有
			obj.communityId = community_id;
		}
		objArr.push(obj);
	});
	console.info(objArr);
	var inparam = {data:{}};
	inparam.data=objArr;
	$.post("communityAction!saveAttr.action", {
		inParma : JSON.stringify(inparam)
	}, function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			$("#myModal1").modal("hide");
		} else {
			alert("保存失败" + data.msg);
		}
	});
}

function actionDeal() {
	$(".defined-del").click(function() {
		var v = $(this);
		if (v.hasClass("icon-remove")) {
		//v.siblings().attr("disabled",false).css("border-color","red");
		v.siblings("input").css({"color": "red"});
		v.siblings("input").addClass("defined-line");
		v.removeClass("icon-remove").addClass("icon-retweet");
		v.siblings("input").attr("action-data","DEL");
		} else {
			//v.siblings().attr("disabled",false).css("border-color","red");
			v.siblings("input").css({"color": "black"});
			v.siblings("input").removeClass("defined-line");
			v.removeClass("icon-retweet").addClass("icon-remove");
			
			var oldValue = v.siblings("input").attr("old-value");
			var newValue = v.siblings("input").attr("value");
			if (oldValue == oldValue) {
				v.siblings("input").attr("action-data","KEEP");
			} else {
				v.siblings("input").attr("action-data","UPDATE");
			}
		}
	});
	$(".defined-edit").click(function() {
		var v = $(this);
		v.siblings("input").attr("disabled",false);
		v.siblings("input").css({"border":"1px solid green"});
		v.siblings("input").attr("action-data","UPDATE");
	});
	$(".value-input").change(function() {
		$(this).attr("disabled",true);
		$(this).css({"border":"0px solid green"});
		var vv = $(this).val();
		if (vv == "") {
			$(this).val($(this).attr("old-value"));
		}
	});
	$(".defined-add").click(function() {
		var v = $(this);
		v.parent().before("<div style='margin: 3px;'><a class='icon-minus new-del' style='margin-left:2px; margin-right:2px;'></a><a class='icon-edit new-edit' style='margin-left:2px; margin-right:2px;'></a><input type='text' value='' class='value-input' style='border-color : green;' action-data='ADD' old-value='' data-id='0' placeholder='请输入属性值名称'></div>");
		newAdd();
	});
}

function newAdd() {
	$(".value-input").change(function() {
		$(this).attr("disabled",true);
		$(this).css({"border":"0px solid green"});
	});
	$(".new-edit").click(function() {
		var v = $(this);
		v.siblings("input").attr("disabled",false);
		v.siblings("input").css({"border":"1px solid green"});
		v.siblings("input").attr("action-data","ADD");
	});
	$(".new-del").click(function() {
		var v =$(this);
		v.parent().remove();
	});
}
