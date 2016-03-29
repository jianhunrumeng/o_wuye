$(document).ready(function() {
	$("#submit_btn").bind("click", saveBuildings);
	
	var company = new CompanyObj("#pp_company",null).add(new ComunityObj("#community",null).add(new BuddingObj("#buillding",null)));
	company.complete();
});
function addRow(obj){
		var new_div=$(obj).parents(".accItemRelRow").clone();
		var randStr=Date.parse(new Date());
		$(new_div.children('td').eq(1).find("select")).attr("ng-model","acctItemTypeUp"+randStr);
		$("#acctItemTypes").append(new_div);
//	}
//	var new_div=$("#acctItemType"+1).clone();
//	$("#acctItemType"+num).find("a")[0].setAttribute("disabled",true);
//	$("#acctItemType"+num).find("a")[0].href="javascript:void(0);";
//	num=num+1;
//	new_div.attr("id","acctItemType"+num);
//	new_div.find("a")[0].href="javascript:addRow("+num+")";
//	new_div.find("a")[1].href="javascript:removeRow("+num+")";
	
}
function removeRow(obj){
	var len=$("#acctItemTypes .row-fluid").length;
	if(len!=1){
		$(obj).parents(".accItemRelRow").remove();
	}
//	var len = $("#acctItemTypes .row-fluid").length ;
//	if (len == num){
//		var num_up=num-1;
//		$("#acctItemTypes a").eq(-4).attr("disabled",false).attr("href","javascript:addRow("+num_up+")");
//		if(len!=1){
//			$("#acctItemType"+num).remove();
//		}
//	}else if(len!=num){
//		$("#acctItemType"+num).remove();
//		$("#acctItemTypes a").eq(-4).attr("disabled",false).attr("href","javascript:addRow("+num_up+")");
////		for(var i=(num+1);i<=len;i++){
////			if(i==len){
////				$("#acctItemType"+i+" a").eq(-2).href="javascript:addRow("+(i-1)+")";
////			}
////			$("#acctItemType"+i+" a").eq(-1).href="javascript:removeRow("+(i-1)+")";
////			$("#acctItemType"+i).attr("id","acctItemType"+(i-1));
////		}
//	}
}
function saveBuildings(){
	if ($("#community").attr("data-id") == null
			|| $("#community").attr("data-id") == "null"
				||$("#community").attr("data-id") == ""
				|| typeof($("#community").attr("data-id")) == "undefined"){
		$.sucmodal("提示", "请选择小区");
		return;
	}
	var jsarray = new Array();
	
	var inparam = {data:{}};
	var isSave = true;
	$("#buildings .row-fluid").each(function(index,element){
		var buildingName = $(this).find("input[name='buildingName']").val();
		if (buildingName == null || buildingName == "null"
			|| buildingName ==""){
			$.sucmodal("提示", "楼栋或单元名称不能为空");
			isSave = false;
			return false;
		}
		var buildingName = $(this).find("input[name='buildingName']").val();
		var ownerBuilding = $(this).find("select[name='type'] option:selected").val();
		for (var i = 0; i < jsarray.length; i++){
			if (buildingName == jsarray[i].buildingName
					&& ownerBuilding == jsarray[i].ownerBuilding){
				$.sucmodal("提示", "楼栋或单元重复");
				isSave = false;
				return false;
			}
		}
		var building = {
				community : {}
		};
		building.community.communityId=$("#community").attr("data-id");
		building.buildingName = buildingName;
		building.ownerBuilding = ownerBuilding;
		jsarray.push(building);
		//building.buildingName = ;
	});
	
	if (!isSave){
		return;
	}
	
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

function clickOk(){
	if ($("#community").attr("data-id") == null
			|| $("#community").attr("data-id") == "null"
				|| typeof($("#community").attr("data-id")) == "undefined"){
		$.sucmodal("提示", "请选择小区");
		return;
	}
	$("#desCompany").text($("#pp_company").val());
	$("#desCommunity").text($("#community").val());
	$("#desBudding").text($("#buillding").val());
	/*$.post("buildingAction!getBuilding.action",{
		inParma : JSON.stringify({"communityId":$("#community").attr("data-id"),"qryType":"getSimpleBuildingBycommunity"})
	}, function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			var opt = "<option value=''>选择楼栋</option>";
			$(data.data).each(function(ix, ite){
				opt += "<option value='"+ite.buildingId+"'>"+ite.buildingName+"</option>";
			}); 
			
			$("#buildings select").html("");
			$("#buildings select").append(opt);
		} else {
			alert("查询失败" + data.msg);
		}
	});*/
	
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
	
	var buildingPhone = $("input[name='buildingPhone']").val(); // 楼栋电话
	if (buildingPhone == ""||buildingPhone=="null" ) {
		$.sucmodal("提示", "楼栋联系电话必填");
		return;
	}
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
	if (pp_leader_phone == "") {
		$.sucmodal("提示", "负责人联系电话必填");
		return;
	}
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
	building.phone = buildingPhone;
	building.floorCount = floorCount;
	building.stairArea = stairArea;
	building.finishTime = finishTime;
	building.userableArea = userableArea;
	building.afforestArea = afforestArea;
	building.pp_status = pp_status;
	

	building.partyInfo.partyName = pp_leader_name;// 负责人信息
	building.partyInfo.qq = pp_leader_qq;
	building.partyInfo.certType = pp_leader_cert_type;
	building.partyInfo.certNbr = pp_leader_cert_nbr;
	building.partyInfo.weiXin = pp_leader_wx;
	building.partyInfo.email = pp_email;
	building.partyInfo.mobile = pp_leader_phone;
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

