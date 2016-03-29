function clickSearch(){
	var propertyCompanyName = $("input[name=keywords]").val();
	var jsondata={};
	jsondata.propertyCompanyName=propertyCompanyName;
	$.post(
		"propertyCompanyAction!getPropertyCompanyList.action",
		{jsondata : JSON.stringify(jsondata)},
		function(data){
			data = eval("("+data+")");
			if(data!=null&&data.result=="true"){
				$("#demo").myPagination({
				currPage: 1,
				pageSize: 5,
				pageCount:data.pageInfo.totalPageCount,
				ajax:{
					on: true,                        //开启状态
		            // url: "mbook!getMBookByCondition.action?type="+type+"&keyword="+keyword,            //访问服务器地址
					url: "propertyCompanyAction!getPropertyCompanyList.action",
		            dataType: 'json',                //返回类型
		            callback:'callBack',
		            param:{"on":true, jsondata : JSON.stringify(jsondata)},
		            ajaxStart: function() {
		                return false;
		            },onClick:function(page){
		            	alert(page);
		            }
				}
			});
			}else{
				alert("查询失败:"+data.msg);
			}
			
		}
	);
}

function callBack(data){
	$("#listTable>tbody").children().remove();
	data = eval("("+data+")");
	$(data.pageInfo.dataList).each(function(ix,ite){
		var trStr = '<tr>';
		trStr += '<td class="with-checkbox"><input type="checkbox" name="check" value="'+ite.propertyCompany.companyId+'" /></td>';
		trStr += '<td>'+ite.propertyCompany.companyName+'</td>';
		if(ite.propertyCompany!=null&&ite.propertyCompany.companyInfo!=""
			&&ite.propertyCompany.companyInfo.officePhone!=null&&
			ite.propertyCompany.companyInfo.officePhone!=""){
			trStr += '<td>'+ite.propertyCompany.companyInfo.officePhone+'</td>';
		}else{
			trStr += '<td>'+""+'</td>';
		}
		if(ite.propertyCompany.partyInfo!=null&&ite.propertyCompany.partyInfo!=""){
			trStr += '<td>'+ite.propertyCompany.partyInfo.partyName+'</td>';
			trStr += '<td>'+ite.propertyCompany.partyInfo.mobile+'</td>';
		}else{
			trStr += '<td>'+""+'</td>';
			trStr += '<td>'+""+'</td>';
		}
		trStr += '<td>'+ite.propertyCompany.statusName+'</td>';
		/*if (ite.propertyCompany.statusCd == '1000') {
			trStr += '<td>有效</td>';
		} else {
			trStr += '<td>失效</td>';
		}*/
		
		trStr +='<td><a href="addPropertyCompanyInfo.jsp?companyId='+ite.propertyCompany.companyId+'" class="btn defined-role-btn" >修改</a>';
//		trStr += '<td><a href="javascript:updatePropertyCompany('+ite.companyId+');" data-toggle="modal" class="btn defined-role-btn" id="btn_edit_role_"'+ite.companyId+'>修改</a>';
//		trStr +='<a href=javascript:removePropertyCompany('+ite.companyId+',"'+ite.companyName+'"); class="btn defined-role-btn"  id="btn_del_role_"'+ite.companyId+'>删除</a>';
		trStr +='<a href="propertyCompanyInfo.jsp?companyId='+ite.propertyCompany.companyId+' " class="btn defined-role-btn" >详情</a></td>';
		trStr += '</tr>';
		$("#listTable>tbody").append(trStr);
	});
}


function removeAllPropertyCompany() {
	var jsondata =[]; 
	$('input[name="check"]:checked').each(function(){ 
		jsondata.push($(this).val()); 
	}); 
	if (jsondata.length == 0) {
		$.sucmodal("提示", "请勾选要物业公司!",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的物业公司吗?",1000,function(){
		$.post(
				"propertyCompanyAction!removePropertyCompany.action",
				{jsondata: JSON.stringify(jsondata)},
				function(data){
					data = eval("("+data+")");
					if(data.key == "success"){
						$.sucmodal("提交成功", "删除成功",2000,function(){
							location.reload();
		            	});
					}else{
						$.sucmodal("提交失败", "请于管理员联系"+data.msg,2000);
					}
				}
			);
	});
}

