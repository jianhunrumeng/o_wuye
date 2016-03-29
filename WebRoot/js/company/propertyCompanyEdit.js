$(document).ready(function() {
	$("#submit_btn").bind("click", savePropertyCompany);
	var hCompanyId = $("#hidden_company_id").val();
	if(hCompanyId!=null&&hCompanyId!=""){
			$("#submit_btn").css({"display":"none"});
			$("#reset_btn").css({"display":"none"});
//			$("#main").attr("disabled","true");
		getPropertyCompany(hCompanyId);
	}
	
	//获取选择属性
	getSelectValue('bank_name','','#pp_bank');
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

//保存物业公司方法
function savePropertyCompany() {
	var pp_name = $("input[name='pp_name']").val();			//公司名称
	if (pp_name == "") {
		$.sucmodal("提示","物业公司名称必填");
		return;
	}
	var pp_abb = $("input[name='pp_abb']").val();				//简称
	/*if (pp_abb == "") {
		$.sucmodal("提示","物业公司简称必填");
		return;
	}*/
	var pp_leader_name = $("input[name='pp_leader_name']").val();		//负责人
	if (pp_leader_name == "") {
		$.sucmodal("提示","物业公司负责人必填");
		return;
	}
	/*var pp_phone = $("input[name='pp_phone']").val();			//联系电话
	if (pp_phone == "") {
		$.sucmodal("提示","物业公司联系电话必填");
		return;
	}*/
	var pp_office_phone = $("input[name='pp_office_phone']").val();	//公司电话
	if (pp_office_phone == "") {
		$.sucmodal("提示","物业公司电话必填");
		return;
	}
	var pp_bank = $("#pp_bank option:selected").val();				//开户银行
	var pp_account_name = $("input[name='pp_account_name']").val();				//开户名称
	/*if (pp_account_name == "") {
		$.sucmodal("提示","开户名称必填");
		return;
	}*/
	var pp_account = $("input[name='pp_account']").val();				//开户账号
	/*if (pp_account == "") {
		$.sucmodal("提示","开户账号必填");
		return;
	}*/
	var pp_fax = $("input[name='pp_fax']").val();				//公司传真
	var pp_status = $("#pp_status option:selected").val();	//有效标志	
	var pp_zip_code = $("input[name='pp_zip_code']").val();	//邮编
	var area_area = $("#area_area option:selected").val();		//地区
	if (area_area == "") {
		$.sucmodal("提示","请选择完地区内容");
		return;
	}
	var pp_address = $("input[name='pp_address']").val();		//地址
	if (pp_address == "") {
		$.sucmodal("提示","物业公司地址必填");
		return;
	}
	
//	var pp_leader_name = $("input[name='pp_leader_name']").val();		//负责人姓名
	var pp_leader_cert_type = $("#pp_leader_cert_type option:selected").val();		//负责人证件类型
	var pp_leader_cert_nbr = $("input[name='pp_leader_cert_nbr']").val();//负责人证件号码
	var pp_leader_phone = $("input[name='pp_leader_phone']").val();//负责人联系电话
	if (pp_leader_phone == "") {
		$.sucmodal("提示","负责人联系电话必填");
		return;
	}
	var pp_leader_qq = $("input[name='pp_leader_qq']").val();//负责人QQ
	var pp_leader_wx = $("input[name='pp_leader_wx']").val();//负责人QQ
	var pp_email = $("input[name='pp_email']").val();			//Email

//	$.sucmodal("提示","物业公司信息保存成功" , function(){
//					location.href="propertyCompanyManager.jsp"
//				});
//	var jsondata = { propertyCompany: {}, partyInfo: {},organization:{},address:{}}; 
	//var jsondata = { propertyCompany: {},organization:{},partyInfopp: {},partyInfo:{},address:{},area:{}}; 

	var jsarray = new Array();
	var company = {
			partyInfopp : {
				address : {
				}
			},
			partyInfo : {}
	};
	var jsondata = {data:{}};
	
	company.companyName=pp_name;//物业公司名称
	company.simpleName=pp_abb;//物业公司简称
	company.bankName=pp_bank;//物业公司开户银行
	company.bankAcctName=pp_account_name;//物业公司开户名称
	company.bankAcctNbr=pp_account;//开户账号
	company.statusCd=pp_status;//物业公司状态
	company.partyInfopp.zipCode=pp_zip_code;//物业公司邮编
	company.partyInfopp.officePhone=pp_office_phone;//物业公司电话
	company.partyInfopp.fax=pp_fax;//物业公司传真
	company.partyInfopp.partyName=pp_name;
	company.partyInfopp.address.areaId=area_area;
	company.partyInfopp.address.detailAddress=pp_address;


	company.partyInfo.partyName=pp_leader_name;//负责人信息
	company.partyInfo.qq=pp_leader_qq;
	company.partyInfo.certType=pp_leader_cert_type;
	company.partyInfo.certNbr=pp_leader_cert_nbr;
	company.partyInfo.weiXin=pp_leader_wx;
	company.partyInfo.email=pp_email;
	company.partyInfo.mobile=pp_leader_phone;
	
	var hCompanyId = $("#hidden_company_id").val()
	if(hCompanyId!=null&&hCompanyId!="" && hCompanyId != "null"){
		company.companyId=hCompanyId;
	}
//	jsondata.organization.upOrgId="0";//物业公司没有上级组织
	jsarray.push(company);
	jsondata.data=jsarray;

//	jsondata.address.addressId="312";
//	var pzJson = "{'pp_leader_name':'"+pp_leader_name+"','area_area':'"+area_area+"'}";
	$.post("propertyCompanyAction!addPropertyCompany.action",{
		jsondata : JSON.stringify(jsondata)
	},function(data) {
		data = eval("(" + data + ")");
		if (data.result == "true") {
			$.sucmodal("提示",data.msg , function(){
				location.href="propertyCompanyManager.jsp";
			});
		}else {
			alert("保存失败"+data.msg);
		}
	} );
	
}

function getPropertyCompany(CompanyId) {
	var jsondata={};
	jsondata.companyId=CompanyId;
	jsondata.isQueryOne="yes";
	$.post(
	    "propertyCompanyAction!getPropertyCompanyList.action",
	    {jsondata : JSON.stringify(jsondata)},
	    function(data) {
	    	data = eval("("+data+")");
	    	if (data!=null&&data.result=="true") {
	    		var propertyCompany=data.pageInfo.dataList[0];
	    		if(propertyCompany!=null&&propertyCompany!=""
	    			&&propertyCompany.propertyCompany!=null
	    			&&propertyCompany.propertyCompany!=""){
	    			var propertyCompany=propertyCompany.propertyCompany;
	    			$("#pp_name").val(propertyCompany.companyName);			//公司名称
					$("input[name='pp_abb']").val(propertyCompany.simpleName);				//简称
					
	//				$("input[name='pp_phone']").val()=propertyCompany;			//联系电话
					if(propertyCompany.companyInfo!=null&&propertyCompany.companyInfo!=""){
						$("input[name='pp_office_phone']").val(propertyCompany.companyInfo.officePhone);	//公司电话
						$("input[name='pp_zip_code']").val(propertyCompany.companyInfo.zipCode);	//邮编
						$("input[name='pp_fax']").val(propertyCompany.companyInfo.fax);
						//公司传真
						if(propertyCompany.companyInfo.address!=null
								&&propertyCompany.companyInfo.address!=""){
							if(propertyCompany.companyInfo.address.area!=null&&
									propertyCompany.companyInfo.address.area!=""){
								var area = propertyCompany.companyInfo.address.area;
								var upArea1 = area.upArea;
								var upArea2 = upArea1.upArea;
								/*$("#area_province option[value='"
												+ upArea2.areaId + "']").attr(
										"selected", true);*/
								editGetProvince(upArea2.areaId);
								editGetCity(upArea2.areaId,upArea1.areaId);
								editGetArea(upArea1.areaId,area.areaId);
							
								/*var hidden_area_id = $("#hidden_area_id").val();
								if(hidden_area_id!=null&&hidden_area_id!=""){
									$.post("areas!queryUpAreas.action",{upAreaId : hidden_area_id},function(data){
										var result = eval("(" + data + ")");
										if(result.key="yes"){
											$("#area_area option[value='"+result.area_area+"']").attr("selected",true);
											$("#area_city option[value='"+result.area_city+"']").attr("selected",true);
											$("#area_province option[value='"+result.area_province+"']").attr("selected",true);
										}else{
											$.sucmodal("查询失败", "请于管理员联系"+result.msg,2000);
										}
									});
								}*/
							}
							$("input[name='pp_address']").val(propertyCompany.companyInfo.address.detailAddress);		//地址
						}
					}
					getSelectValue('bank_name','','#bank_name');
					$("#pp_bank option[value='"+propertyCompany.bankName+"']").attr("selected",true);		//开户银行
					$("input[name='pp_account_name']").val(propertyCompany.bankAcctName);				//开户名称
					$("input[name='pp_account']").val(propertyCompany.bankAcctNbr);				//开户账号
					$("#pp_status option[value='"+propertyCompany.statusCd+"']").attr("selected",true);
//					$("#pp_status option:selected").val(propertyCompany.statusCd);	//有效标志	
					if(propertyCompany.partyInfo!=null&&propertyCompany.partyInfo!=""){
						$("input[name='pp_leader_name']").val(propertyCompany.partyInfo.partyName);		//负责人
						$("#pp_leader_cert_type option:selected").val(propertyCompany.partyInfo.certType);		//负责人证件类型
						$("input[name='pp_leader_cert_nbr']").val(propertyCompany.partyInfo.certNbr);//负责人证件号码
						$("input[name='pp_leader_phone']").val(propertyCompany.partyInfo.mobile);//负责人联系电话
						$("input[name='pp_leader_qq']").val(propertyCompany.partyInfo.qq);//负责人QQ
						$("input[name='pp_leader_wx']").val(propertyCompany.partyInfo.weiXin);//负责人QQ
						$("input[name='pp_email']").val(propertyCompany.partyInfo.email);			//Email
					}
	    		}
//	    		$("#xs_role_name").text(data.role.roleName);
//		    	var statucStr = '失效';
//		    	if (data.role.statusCd == '1000') {
//		    		var statucStr = '生效';
//		    	}
//		    	$("#xs_role_status").text(statucStr);
//		    	$("#role_privilege_detail").html("");
//		    	for (var i = 0; i < data.privilegeList.length; i ++) {
//		    		var spanStr = "";
//		    		spanStr += "<span class='rpd-detail'>"+data.privilegeList[i].privilegeName+"</span>";
//		    		$("#role_privilege_detail").append(spanStr);
//		    	}
	    		$("#submit_btn").css({"display":"block"});
	    	} else if (data.result == "false") {
	    		$.sucmodal("查询失败", "请于管理员联系"+data.msg,2000);
	    	}
	    	
	    }
	);
}
