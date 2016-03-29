$(document).ready(function() {
	
	$("#area_province").change(function() {
		$("#area_city").html("");
		$("#area_area").html("");
		var up_area_id = $(this).val();
		editGetCity(up_area_id,null);
	});
	$("#area_city").change(function() {
		var up_area_id = $(this).val();
		editGetArea(up_area_id,null); 
	});
});
function editGetProvince(area_id) {	
	$.post("areas!queryAreas.action",{upAreaId : 0},function(data){
		var result = eval("(" + data + ")");
		$("#area_province").html("");
		var province = "";
		for(var i = 0; i < result.length; i ++) {
			var obj = result[i];
			var selected = "";
			if (area_id != null && area_id == obj.areaId) {				
				selected = "selected = 'selected'";
			} 
			province += "<option value='"+obj.areaId+"'  "+selected+" >"+obj.areaName+"</option>";
		}
		$("#area_province").append(province);
	});
}


function editGetCity(up_area_id, area_id) {
	$.post("areas!queryAreas.action",{upAreaId : up_area_id},function(data){
		var result = eval("(" + data + ")");
		$("#area_city").html("");
		var province = "";
		for(var i = 0; i < result.length; i ++) {
			var obj = result[i];
			var selected = "";
			if (area_id != null && area_id == obj.areaId) {				
				selected = "selected = 'selected'";
			} 
			province += "<option value='"+obj.areaId+"'  "+selected+" >"+obj.areaName+"</option>";
		}
		$("#area_city").append(province);
		if (area_id == null) {			
			editGetArea(result[0].areaId,null);
		}
	});
}

function editGetArea(up_area_id,area_id) {
	$.post("areas!queryAreas.action",{upAreaId : up_area_id},function(data){
		var result = eval("(" + data + ")");
		$("#area_area").html("");
		var province = "";
		for(var i = 0; i < result.length; i ++) {
			var obj = result[i];
			var selected = "";
			if (area_id != null && area_id == obj.areaId) {				
				selected = "selected = 'selected'";
			} 
			province += "<option value='"+obj.areaId+"'  "+selected+" >"+obj.areaName+"</option>";
		}
		$("#area_area").append(province);
	});
}