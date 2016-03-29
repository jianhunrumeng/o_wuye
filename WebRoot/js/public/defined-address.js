$(document).ready(function() {
	$.post("areas!queryAreas.action",{upAreaId : 0},function(data){
		var result = eval("(" + data + ")");
		$("#area_province").html("");
		var province = "";
		for(var i = 0; i < result.length; i ++) {
			var obj = result[i];
			province += "<option value='"+obj.areaId+"'>"+obj.areaName+"</option>";
		}
		$("#area_province").append(province);
		$("#area_city").html("");
		$("#area_area").html("");
		getCity(result[0].areaId);
	});
	$("#area_province").change(function() {
		var up_area_id = $(this).val();
		getCity(up_area_id);
	});
	$("#area_city").change(function() {
		var up_area_id = $(this).val();
		getArea(up_area_id); 
	});
	/*$("#area_city").click(function() {
		var up_area_id = $(this).val();
		getArea(up_area_id); 
	});*/
});

function getCity(up_area_id) {
	$.post("areas!queryAreas.action",{upAreaId : up_area_id},function(data){
		var result = eval("(" + data + ")");
		$("#area_city").html("");
		var province = "";
		for(var i = 0; i < result.length; i ++) {
			var obj = result[i];
			province += "<option value='"+obj.areaId+"'>"+obj.areaName+"</option>";
		}
		$("#area_city").append(province);
		if (result.length == 1) {
			getArea(result[0].areaId);
		}
		getArea(result[0].areaId);
		$("#area_area").html("");
	});
}

function getArea(up_area_id) {
	$.post("areas!queryAreas.action",{upAreaId : up_area_id},function(data){
		var result = eval("(" + data + ")");
		$("#area_area").html("");
		var province = "";
		for(var i = 0; i < result.length; i ++) {
			var obj = result[i];
			province += "<option value='"+obj.areaId+"'>"+obj.areaName+"</option>";
		}
		$("#area_area").append(province);
	});
}

/*//编辑

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
}*/