$(document).ready(function() {
	$("#submit_btn").bind("click", saveCommunity);
	var community_id = $("#hidden_community_id").val();
	getSelectValue('bank_name','','#pp_bank');
	if (community_id != null && community_id != "" && community_id != "null") {
		$("#submit_btn").css({"display":"none"});
		$("#reset_btn").css({"display":"none"});
		getCommunity(community_id);
	}
	//获取选择属性
	
	$("#submit_btn").css({"display":"block"});
	
	$( "#pp_company" ).autocomplete({
        source:function(request,response){
           $.ajax({
               type:"POST",
               url:"communityAction!getCompany.action",
               dataType:"json",
               data : {
                   "inParma": JSON.stringify({"companyName":request.term,"qryType":"getCompanySimple"})
               },
               success: function(data) {
            	   data = eval("("+data+")").data;

                   response($.map(data, function(item) {
                	   
                       return { label: item.companyName+"（"+item.regionWithSHQ+"）", value: item.companyName ,companyId:item.companyId}
                   }
                   
                   ));
               }
           });
       },
       delay:500,
       select: function(event, ui){
    	   $("#pp_company").attr("data-id",ui.item.companyId);
    	   //$("#community").val(ui.item.label);
    	   //$("#community").val(ui.item.value);
    	   //event.preventDefault();  
       },
       change: function( event, ui ) {
           // event 是当前事件对象
           
           // ui对象仅有一个item属性，它表示当前选择的菜单项对应的数据源对象
           // 该对象具有label和value属性，以及其它自定义(如果有的话)的属性
           // 如果当前没有选择的菜单项，这item属性为null
    	   if (ui.item == null || ui.item == "null" || typeof(ui.item) == "undefined"){
    		   $("#pp_company").attr("data-id",null);
    		   $("#pp_company").val("");
    	   }
       }
   });
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
function getCommunity(community_id) {
	$.post("communityAction!getCommunity.action", {
		"inParma" : JSON.stringify({
			"communityId" : community_id
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

function des(data) {
	var dataList = data.data;
	if (dataList != null) {
		var community = dataList[0];
		var company = community.propertyCompany;
		var communityInfo = community.communityInfo;
		$("#pp_company").val(company.companyName);
		$("#pp_company").attr("data-id",company.companyId);
		$("#pp_name").val(community.communityName);
		$("#pp_abb").val(community.simpleName);
		if (communityInfo != null) {
			$("#pp_office_phone").val(communityInfo.officePhone);
			$("#pp_fax").val(communityInfo.fax);
			$("#pp_zip_code").val(communityInfo.zipCode);
			var communityAddress = communityInfo.address;
			if (communityAddress != null) {
				var area = communityAddress.area;
				var upArea1 = area.upArea;
				var upArea2 = upArea1.upArea;
				/*$("#area_province option[value='"
								+ upArea2.areaId + "']").attr(
						"selected", true);
//				getCity(upArea2.areaId);
				getCity(upArea2.areaId);
				$("#area_city option[value='" + upArea1.areaId + "']")
				.attr("selected", true);
				getArea(upArea1.areaId);
				$("#area_area option[value='" + area.areaId + "']")
						.attr("selected", true);*/
				editGetProvince(upArea2.areaId);
				editGetCity(upArea2.areaId,upArea1.areaId);
				editGetArea(upArea1.areaId,area.areaId);
				$("#pp_address").val(communityAddress.detailAddress);
			}
		}
		$("#pp_ca").val(community.communityArea);
		$("#pp_cba").val(community.buildingArea);
		$("#pp_cfa").val(community.afforestArea);
		$("#pp_bank  option[value='"+community.bankName+"']").attr("selected",true);
		$("#pp_account_name").val(community.bankAcctName);
		$("#pp_account").val(community.bankAcctNbr);
		$("#pp_status option[value='"+community.statusCd+"']").attr("selected",true);

		var leaderInfo = community.partyInfo;
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
function saveCommunity() {
	var pp_name = $("input[name='pp_name']").val(); // 公司名称
	var companyId = $("#pp_company").attr("data-id");
	if (companyId == "" || companyId=="null" || companyId==null){
		$.sucmodal("提示", "物业公司必填");
		return;
	}
	
	if (pp_name == "") {
		$.sucmodal("提示", "小区名称必填");
		return;
	}
	var pp_abb = $("input[name='pp_abb']").val(); // 简称
	/*if (pp_abb == "") {
		$.sucmodal("提示", "小区简称必填");
		return;
	}*/
	var pp_leader_name = $("input[name='pp_leader_name']").val(); // 负责人
	if (pp_leader_name == "") {
		$.sucmodal("提示", "小区负责人必填");
		return;
	}
	var pp_phone = $("input[name='pp_phone']").val(); // 联系电话
	if (pp_phone == "") {
		$.sucmodal("提示", "负责人联系电话必填");
		return;
	}
	var pp_office_phone = $("input[name='pp_office_phone']").val(); // 公司电话
	if (pp_office_phone == "") {
		$.sucmodal("提示", "小区电话必填");
		return;
	}
	var pp_bank = $("#pp_bank option:selected").val(); // 开户银行
	var pp_account_name = $("input[name='pp_account_name']").val(); // 开户名称
	/*if (pp_account_name == "") {
		$.sucmodal("提示", "开户名称必填");
		return;
	}*/
	var pp_account = $("input[name='pp_account']").val(); // 开户账号
	/*if (pp_account == "") {
		$.sucmodal("提示", "开户账号必填");
		return;
	}*/
	var pp_fax = $("input[name='pp_fax']").val(); // 公司传真
	var pp_status = $("#pp_status option:selected").val(); // 有效标志
	var pp_zip_code = $("input[name='pp_zip_code']").val(); // 邮编
	var area_area = $("#area_area option:selected").val(); // 地区
	if (area_area == "") {
		$.sucmodal("提示", "请选择完地区内容");
		return;
	}
	var pp_address = $("input[name='pp_address']").val(); // 地址
	if (pp_address == "") {
		$.sucmodal("提示", "小区地址必填");
		return;
	}
	
	var pp_ca = $("#pp_ca").val(); // 
	var pp_cba = $("#pp_cba").val(); // 
	var pp_cfa = $("#pp_cfa").val(); // 
	
	
	// var pp_leader_name = $("input[name='pp_leader_name']").val(); //负责人姓名
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
	var community = {
			propertyCompany : {},
			partyInfopp : {
				address : {
					area : {}
				}
			},
			partyInfo : {}
	};
	var inparam = {data:{}};
	community.propertyCompany.companyId = companyId;//先固定，后传选择的物业公司
	community.communityName = pp_name;// 物业公司名称
	community.simpleName = pp_abb;// 物业公司简称
	community.bankName = pp_bank;// 物业公司开户银行
	community.bankAcctName = pp_account_name;// 物业公司开户名称
	community.bankAcctNbr = pp_account;// 开户账号
	community.statusCd = pp_status;// 物业公司状态

	community.communityArea = pp_ca;
	community.buildingArea = pp_cba;
	community.afforestArea = pp_cfa;
	
	community.partyInfopp.zipCode = pp_zip_code;// 物业公司邮编
	community.partyInfopp.officePhone = pp_office_phone;// 物业公司电话
	community.partyInfopp.fax = pp_fax;// 物业公司传真
	community.partyInfopp.partyName = pp_name;
	community.partyInfopp.address.areaId = area_area;
	community.partyInfopp.address.detailAddress = pp_address;

	community.partyInfo.partyName = pp_leader_name;// 负责人信息
	community.partyInfo.qq = pp_leader_qq;
	community.partyInfo.certType = pp_leader_cert_type;
	community.partyInfo.certNbr = pp_leader_cert_nbr;
	community.partyInfo.weiXin = pp_leader_wx;
	community.partyInfo.email = pp_email;
	community.partyInfo.mobile = pp_leader_phone;
	// jsondata.organization.orgType = "10";
	// jsondata.organization.upOrgId="0";//物业公司没有上级组织

	// jsondata.address.addressId="312";
	// var pzJson =
	// "{'pp_leader_name':'"+pp_leader_name+"','area_area':'"+area_area+"'}";
	var communityId = $("#hidden_community_id").val();
	if (communityId != null && communityId != "" && communityId != "null") {
		community.communityId = communityId;
	} 
	jsarray.push(community);
	inparam.data=jsarray;
	$.post("communityAction!saveCommunity.action", {
		inParma : JSON.stringify(inparam)
	}, function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			$.sucmodal("提示", data.msg, function() {
				location.href = "communityManager.jsp";
			});
		} else {
			alert("保存失败" + data.msg);
		}
	});
}