$(document).ready(function() {
	
});
/**
 * 权限列表查询。
 */
function qryPrivilegeList() {
	var name = $("#n-keyword-input").val();  //权限名称
	var jsondata={};
	jsondata.privilegeName = name;
	$.post(
		"privilegeAction!qryPrivilegeList.action",
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
					url: "privilegeAction!qryPrivilegeList.action",
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
/**
 * 回调函数。
 * @param {Object} data
 */
function callBack(data){
	$("#listTable>tbody").children().remove();
	data = eval("("+data+")");
	$(data.pageInfo.dataList).each(function(ix,ite){
		var trStr = '<tr>';
		trStr += '<td class="with-checkbox"><input type="checkbox" name="check" value="'+ite.privilegeId+'" /></td>';
		trStr += '<td>'+ite.privilegeName+'</td>';
		trStr += '<td>'+ite.path+'</td>';
		trStr += '<td>'+ite.parentPrivilegeName+'</td>';
		if(ite.privilegeType=='101') {
			trStr += '<td>菜单权限</td>';
		} else {
			trStr += '<td>功能权限</td>';
		}
		
		if (ite.statusCd == '1000') {
			trStr += '<td>有效</td>';
		} else {
			trStr += '<td>失效</td>';
		}
		
		if (ite.createDate==null) {
			trStr += '<td></td>';
		} else {
			trStr += '<td>'+ite.createDate+'</td>';
		}
		
		trStr += '<td><a href="addPrivilegeInfo.jsp?privilegeId='+ite.privilegeId+'" data-toggle="modal" class="btn defined-role-btn" id="btn_edit_pri_"'+ite.privilegeId+'>修改</a></td>';
		trStr += '</tr>';
		$("#listTable>tbody").append(trStr);
	});
}

/**
 * 权限删除。
 */
function removeAllPri() {
	var chk_value =[]; 
	$('input[name="check"]:checked').each(function(){ 
		chk_value.push($(this).val()); 
	}); 
	if (chk_value.length == 0) {
		$.sucmodal("提示", "请勾选要删除的权限",2000);
		return;
	}
	$.altmodal("删除", "您确定要删除所选的权限吗?",1000,function(){
		$.post(
			"privilegeAction!removePrivilege.action",
			{"jsondata":JSON.stringify(chk_value)},
			function(data){
				data = eval("("+data+")");
				if(data.result == "true"){
					$.sucmodal("提交成功", data.msg,function(){
						location.reload();
	            	});
				}else{
					$.sucmodal("提交失败", data.msg);
				}
			}
		);
	});
}




