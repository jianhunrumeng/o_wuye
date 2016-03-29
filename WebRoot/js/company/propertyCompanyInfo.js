$(document).ready(function() {
	$("#submit_btn").bind("click", savePropertyCompany);
	var hCompanyId = $("#hidden_company_id").val();
	if(hCompanyId!=null&&hCompanyId!=""){
		getPropertyCompany(hCompanyId);
	}
});

//保存物业公司方法
function savePropertyCompany() {
	var pp_name = $("input[name='pp_name']").val();			//公司名称
	if (pp_name == "") {
		$.sucmodal("提示","物业公司名称必填");
		return;
	}
	var pp_abb = $("input[name='pp_abb']").val();				//简称
	if (pp_abb == "") {
		$.sucmodal("提示","物业公司简称必填");
		return;
	}
	var pp_leader_name = $("input[name='pp_leader_name']").val();		//负责人
	if (pp_leader_name == "") {
		$.sucmodal("提示","物业公司负责人必填");
		return;
	}
	var pp_phone = $("input[name='pp_phone']").val();			//联系电话
	if (pp_phone == "") {
		$.sucmodal("提示","物业公司联系电话必填");
		return;
	}
	var pp_office_phone = $("input[name='pp_office_phone']").val();	//公司电话
	if (pp_office_phone == "") {
		$.sucmodal("提示","物业公司电话必填");
		return;
	}
	var pp_bank = $("#pp_bank option:selected").val();				//开户银行
	var pp_account_name = $("input[name='pp_account_name']").val();				//开户名称
	if (pp_account_name == "") {
		$.sucmodal("提示","开户名称必填");
		return;
	}
	var pp_account = $("input[name='pp_account']").val();				//开户账号
	if (pp_account == "") {
		$.sucmodal("提示","开户账号必填");
		return;
	}
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
					area : {}
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
	company.partyInfopp.area.areaId=area_area;
	company.partyInfopp.address.detailAddress=pp_address;


	company.partyInfo.partyName=pp_leader_name;//负责人信息
	company.partyInfo.qq=pp_leader_qq;
	company.partyInfo.certType=pp_leader_cert_type;
	company.partyInfo.certNbr=pp_leader_cert_nbr;
	company.partyInfo.weiXin=pp_leader_wx;
	company.partyInfo.email=pp_email;
	company.partyInfo.mobile=pp_leader_phone;
	
	var hCompanyId = $("#hidden_company_id").val()
	if(hCompanyId!=null&&hCompanyId!=""){
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
	    			$("#pp_name").text(propertyCompany.companyName);			//公司名称
					$("#pp_abb").text(propertyCompany.simpleName);				//简称
					
	//				$("input[name='pp_phone']").val()=propertyCompany;			//联系电话
					if(propertyCompany.companyInfo!=null&&propertyCompany.companyInfo!=""){
						$("#pp_office_phone").text(propertyCompany.companyInfo.officePhone);	//公司电话
						$("#pp_zip_code").text(propertyCompany.companyInfo.zipCode);	//邮编
						$("#pp_fax").text(propertyCompany.companyInfo.fax);
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
								/*editGetProvince(upArea2.areaId);
								editGetCity(upArea2.areaId,upArea1.areaId);
								editGetArea(upArea1.areaId,area.areaId);*/
							
								if (upArea1.areaType=="10"){
									detailAdd = upArea1.areaName+area.areaName+propertyCompany.companyInfo.address.detailAddress;
								}else{
									detailAdd = upArea2.areaName+upArea1.areaName+area.areaName+propertyCompany.companyInfo.address.detailAddress;
								}
								$("#pp_address").text(detailAdd);
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
							/*$("input[name='pp_address']").val(propertyCompany.companyInfo.address.detailAddress);*/		//地址
						}
					}
					$("#pp_bank").text(propertyCompany.bankName2);		//开户银行
					$("#pp_account_name").text(propertyCompany.bankAcctName);				//开户名称
					$("#pp_account").text(propertyCompany.bankAcctNbr);				//开户账号
					$("#pp_status").text(propertyCompany.statusName);
//					$("#pp_status option:selected").val(propertyCompany.statusCd);	//有效标志	
					var leaderInfo = propertyCompany.partyInfo;
					if (leaderInfo != null && leaderInfo !=""){
						$("#pp_leader_name").text(leaderInfo.partyName);
						$("#pp_leader_cert_type").text(leaderInfo.certTypeName);
						$("#pp_leader_cert_nbr").text(leaderInfo.certNbr);
						$("#pp_leader_phone").text(leaderInfo.mobile);
						$("#pp_leader_qq").text(leaderInfo.qq);
						$("#pp_leader_wx").text(leaderInfo.weiXin);
						$("#pp_email").text(leaderInfo.email);
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
	    	} else if (data.result == "false") {
	    		$.sucmodal("查询失败", "请于管理员联系"+data.msg,2000);
	    	}
	    	
	    }
	);
}
