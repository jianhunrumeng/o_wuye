$(document).ready(function() {
	var community_id = $("#hidden_community_id").val();
	getCommunity(community_id);
});

function getCommunity(community_id) {
	$.post(
	    "communityAction!getCommunity.action",
	    {"inParma" : JSON.stringify({"communityId" :community_id})},
	    function(data) {
	    	data = eval("("+data+")");
	    	if (data.result == "true") {
	    		des(data);
			}else {
				alert("保存失败"+data.msg);
			}
	    	
	    }
	);
}

function des(data){
	var dataList = data.data;
	if (dataList != null){
		var community = dataList[0];
		var company = community.propertyCompany;
		var communityInfo = community.communityInfo;
		$("#pp_company").text(company.companyName);
		$("#pp_name").text(community.communityName);
		$("#pp_abb").text(community.simpleName);
		if (communityInfo != null){
			$("#pp_office_phone").text(communityInfo.officePhone);
			$("#pp_fax").text(communityInfo.fax);
			$("#pp_zip_code").text(communityInfo.zipCode);
			var communityAddress = communityInfo.address;
			if (communityAddress != null){
				var area = communityAddress.area;
				var upArea1 = area.upArea;
				var upArea2 = upArea1.upArea;
				var detailAdd =null;
				if (upArea1.areaType=="10"){
					detailAdd = upArea1.areaName+area.areaName+communityAddress.detailAddress;
				}else{
					detailAdd = upArea2.areaName+upArea1.areaName+area.areaName+communityAddress.detailAddress;
				}
				$("#pp_address").text(detailAdd);
			}
		}
		$("#pp_ca").text(community.communityArea);
		$("#pp_cba").text(community.buildingArea);
		$("#pp_cfa").text(community.afforestArea);
		$("#pp_bank").text(community.bankName2);
		$("#pp_account_name").text(community.bankAcctName);
		$("#pp_account").text(community.bankAcctNbr);
		$("#pp_status").text(community.statusName);
		
		var leaderInfo = community.partyInfo;
		if (leaderInfo != null){
			$("#pp_leader_name").text(leaderInfo.partyName);
			$("#pp_leader_cert_type").text(leaderInfo.certTypeName);
			$("#pp_leader_cert_nbr").text(leaderInfo.certNbr);
			$("#pp_leader_phone").text(leaderInfo.mobile);
			$("#pp_leader_qq").text(leaderInfo.qq);
			$("#pp_leader_wx").text(leaderInfo.weiXin);
			$("#pp_email").text(leaderInfo.email);
		}
		
	}
}