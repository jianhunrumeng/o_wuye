/**
 * 
 */

$(document).ready(function(){
	$.ajaxSetup({async: false});
	var menus=loadingMenu();
	showMenu(menus);
	$("#left").attr("style","overflow:scroll");
})
/**
 * 菜单数据获取。
 * @return {TypeName} 
 */
function loadingMenu(){
	var menulst;
	var privilegeType = '101';
	$.post("menuAction!loadMenu.action",{jsondata:privilegeType},function(data){
		var ret = eval("(" + data + ")");
		var menus=ret.menu;
		menulst= menus;
	})
	return menulst;
}
/**
 * 菜单展现.
 * @param {Object} menus
 */
function showMenu(menus){
	$("#menu").html("");
	for(var i=0;i<menus.length;i++){
		var str = '';
		var primary = menus[i];
		var childs = primary.childMenus;
		str += '<a href="#primary'+primary.menuId+'" class="nav-header menu-first collapsed" data-toggle="collapse"><i class="icon-list-alt"></i> '+primary.menuName+'</a>';
		if(i==0){
			str += '<ul id="primary'+primary.menuId+'" class="nav nav-list collapse menu-second in">';
		}else{
			str += '<ul id="primary'+primary.menuId+'" class="nav nav-list collapse menu-second">';
		}
		
		for(var j=0;j<childs.length;j++){
			var child=childs[j];
			str += '<li><a href="'+child.menuPath+'"><i class="icon-user"></i> '+child.menuName+'</a></li>';
		}
		str += '</ul>';
		$("#menu").append(str);
	}
}
