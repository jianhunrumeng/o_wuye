$(document).ready(function() {
	$("#submit_btn").bind("click", saveBuilding);
	var building_id = $("#hidden_building_id").val();
	//var community = new ComunityObj("#community");
	//community.complete();
	var company = new CompanyObj("#pp_company").add(new ComunityObj("#community"));
	company.complete();
	/*$( "#community" ).autocomplete({
        source:function(request,response){
           $.ajax({
               type:"POST",
               url:"communityAction!getCommunity.action",
               dataType:"json",
               data : {
                   "inParma": JSON.stringify({"communityName":request.term,"qryType":"getCommunitySimple"})
               },
               success: function(data) {
            	   data = eval("("+data+")").data;

                   response($.map(data, function(item) {
                	   
                       return { label: item.communityName+"（"+item.regionWithSHQ+"）", value: item.communityName ,communityId:item.communityId}
                   }
                   
                   ));
               }
           });
       },
       delay:500,
       select: function(event, ui){
    	   $("#community").attr("data-id",ui.item.communityId);
    	   //$("#community").val(ui.item.label);
    	   //$("#community").val(ui.item.value);
    	   //event.preventDefault();  
       }
   });*/

    
	if (building_id != null && building_id != "" && building_id != "null") {
		getBuilding(building_id);
	}else{
		//获取选择属性
		getSelectValue('right_type',$("#community").attr("data-id"),'#ownershipType');
		getSelectValue('building_structure',$("#community").attr("data-id"),'#buildingStructure');
		
	}
	/*
	 * $("#communityform").validate({ debug:true, rules : { pp_name :{required:
	 * "请输入小区名称"} } });
	 */
});

function getSelectValue(attrCd,communityId,selectTag){
	$.post("communityAction!getAttrValue.action", {
		"inParma" : JSON.stringify({
			"attrCd" : attrCd,
			"qryType":"attrCd",
			"communityId" : communityId
		})
	}, function(data) {
		var ret = eval("(" + data + ")");
		if (ret.result == "true"){
			var opt = "<option value=''>请选择</option>";
			$(ret.data).each(function(ix, ite){
				opt += "<option value='"+ite.attrValueId+"'>"+ite.attrValue+"</option>";
			}); 
			$(selectTag).html("");
			$(selectTag).append(opt);
		}else{
			alert(data.msg);
		}
	});
}

function getBuilding(building_id) {
	$.post("buildingAction!getBuilding.action", {
		"inParma" : JSON.stringify({
			"buildingId" : building_id
		})
	}, function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			des(data);
		} else {
			alert(data.msg);
		}

	});
}

function des(data) {
	var dataList = data.data;
	if (dataList != null){
		var building = dataList[0];
		var community = building.community;
		
		if (community != null){
			$("#community").val(community.communityName);
			$("#community").attr("data-id",community.communityId);
			$("#pp_company").val(community.company.companyName);
			$("#pp_company").attr("data-id",community.company.companyId);
		}
		
		var ownerBuildingEntt = building.ownerBuildingEntt;
		
		$.post("buildingAction!getBuilding.action",{
			inParma : JSON.stringify({"communityId":community.communityId,"qryType":"getSimpleBuildingBycommunity"})
		}, function(data) {
			data = eval("(" + data + ")");
			if (data.result == "true") {
				var opt = "<option value=''>选择楼栋</option>";
				$(data.data).each(function(ix, ite){
					opt += "<option value='"+ite.buildingId+"'>"+ite.buildingName+"</option>";
				}); 
				
				$("#ownerBuilding").html("");
				$("#ownerBuilding").append(opt);
					if (ownerBuildingEntt != null && typeof ownerBuildingEntt.buildingId != "undefined"){
						$("#ownerBuilding option[value='" + ownerBuildingEntt.buildingId + "']").attr("selected", true);
					}
				} else {
				alert("查询失败" + data.msg);
			}
		});
		
		
		$("#buildingName").val(building.buildingName);
		/*if (ownerBuildingEntt != null){
			//是单元
			$("#ownerBuilding ").val(ownerBuildingEntt.buildingName);
		}else{
			$("#ownerBuilding").val("");
		}*/
		
		getSelectValue('right_type',$("#community").attr("data-id"),'#ownershipType');
		getSelectValue('building_structure',$("#community").attr("data-id"),'#buildingStructure');
		getSelectValue('upgrade_condition',$("#community").attr("data-id"),'#upgradeCondition');
		
		$("#buildingPhone").val(building.phone);
		$("#floorCount").val(building.floorCount);
		$("#stairArea").val(building.stairArea);
		$("#finishTime").val(building.finishTime);
		$("#userableArea").val(building.userableArea);
		$("#afforestArea").val(building.afforestArea);
		
		$("#ownershipType option[value='" + building.ownershipType + "']")
		.attr("selected", true);
		//$("#ownershipType").val(building.ownershipType);
		$("#buildingStructure option[value='" + building.buildingStructure + "']")
		.attr("selected", true);
		//$("#buildingStructure").val(building.buildingStructure);
		$("#upgradeCondition option[value='" + building.upgradeCondition + "']")
		.attr("selected", true);
		//$("#upgradeCondition").val(building.upgradeCondition);
		
		
		if (building.statusCd == "1000"){
			$("#pp_status").val("有效");
		}else if (building.statusCd == "1100"){
			$("#pp_status").val("无效");
		}else{
			$("#pp_status").val("未知状态");
		}
		
		var leaderInfo = building.partyInfo;
		if (leaderInfo != null) {
			$("#pp_leader_name").val(leaderInfo.partyName);
			$("#pp_leader_cert_type").val(leaderInfo.certType);
			$("#pp_leader_cert_nbr").val(leaderInfo.certNbr);
			$("#pp_leader_phone").val(leaderInfo.mobile);
			$("#pp_leader_qq").val(leaderInfo.qq);
			$("#pp_leader_wx").val(leaderInfo.weiXin);
			$("#pp_email").val(leaderInfo.email);
		}
		
	}
}

/*
 * $(function () {
 * $("input,select,textarea").not("[type=submit]").jqBootstrapValidation(); } );
 */
// 保存物业公司方法
function saveBuilding() {
	var communityId = $("#community").attr("data-id");
	
	var buildingName = $("input[name='buildingName']").val(); // 公司名称
	if (buildingName == ""|| buildingName =="null") {
		$.sucmodal("提示", "名称必填");
		return;
	}
	//所属楼栋 待加
	var ownerBuilding = $("#ownerBuilding option:selected").val();
	var buildingPhone = $("input[name='buildingPhone']").val(); // 楼栋电话
	/*if (buildingPhone == ""||buildingPhone=="null" ) {
		$.sucmodal("提示", "楼栋联系电话必填");
		return;
	}*/
	var floorCount = $("input[name='floorCount']").val(); // 负责人
	
	var stairArea = $("input[name='stairArea']").val(); // 联系电话
	
	var finishTime = $("input[name='finishTime']").val(); // 公司电话
	
	
	var userableArea = $("input[name='userableArea']").val(); // 使用面积
	
	var afforestArea = $("input[name='afforestArea']").val(); // 绿化面积 
	
	//ownershipType 选择型
	//buildingStructure 选择型
	//upgradeCondition 装修状态
	
	var pp_status = $("#pp_status option:selected").val(); // 有效标志
	

	var pp_leader_name = $("input[name='pp_leader_name']").val(); //负责人姓名
	var pp_leader_cert_type = $("#pp_leader_cert_type option:selected").val(); // 负责人证件类型
	var pp_leader_cert_nbr = $("input[name='pp_leader_cert_nbr']").val();// 负责人证件号码
	var pp_leader_phone = $("input[name='pp_leader_phone']").val();// 负责人联系电话
	/*if (pp_leader_phone == "") {
		$.sucmodal("提示", "负责人联系电话必填");
		return;
	}*/
	var pp_leader_qq = $("input[name='pp_leader_qq']").val();// 负责人QQ
	var pp_leader_wx = $("input[name='pp_leader_wx']").val();// 负责人QQ
	var pp_email = $("input[name='pp_email']").val(); // Email

	// $.sucmodal("提示","物业公司信息保存成功" , function(){
	// location.href="propertyCompanyManager.jsp"
	// });
	// var jsondata = { propertyCompany: {}, partyInfo:
	// {},organization:{},address:{}};
	var jsarray = new Array();
	var building = {
			community : {},
			partyInfo : {}
	};
	var inparam = {data:{}};
	building.community.communityId = communityId;
	building.buildingName = buildingName;
	building.ownerBuilding = ownerBuilding;
	building.phone = buildingPhone;
	building.floorCount = floorCount;
	building.stairArea = stairArea;
	building.finishTime = finishTime;
	building.userableArea = userableArea;
	building.afforestArea = afforestArea;
	building.pp_status = pp_status;
	
	if(pp_leader_name != null && pp_leader_name !="" && pp_leader_name!="null"){
		building.partyInfo.partyName = pp_leader_name;// 负责人信息
		building.partyInfo.qq = pp_leader_qq;
		building.partyInfo.certType = pp_leader_cert_type;
		building.partyInfo.certNbr = pp_leader_cert_nbr;
		building.partyInfo.weiXin = pp_leader_wx;
		building.partyInfo.email = pp_email;
		building.partyInfo.mobile = pp_leader_phone;
	}
	
	// jsondata.organization.orgType = "10";
	// jsondata.organization.upOrgId="0";//物业公司没有上级组织

	// jsondata.address.addressId="312";
	// var pzJson =
	// "{'pp_leader_name':'"+pp_leader_name+"','area_area':'"+area_area+"'}";
	var buildingId = $("#hidden_building_id").val();
	if (buildingId != null && buildingId != "" && buildingId != "null") {
		building.buildingId = buildingId;
	} 
	jsarray.push(building);
	inparam.data=jsarray;
	$.post("buildingAction!saveBuilding.action", {
		inParma : JSON.stringify(inparam)
	}, function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			$.sucmodal("提示", data.msg, function() {
				location.href = "buildingManager.jsp";
			});
		} else {
			alert("保存失败" + data.msg);
		}
	});
}

