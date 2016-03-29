$(document).ready(function() {
	$( "#community_name" ).autocomplete({
        source:function(request,response){
           $.ajax({
               type:"POST",
               url:"communityAction!getCommunity.action",
               dataType:"json",
               data : {
                   "inParma": JSON.stringify({"communityName" : request.term,"companyId":$("#property_company_name").attr("data-id")})
               },
               success: function(data) {
            	   data = eval("("+data+")").data;
                   response($.map(data, function(item) {
                       return { label: item.communityName, value: item.communityName, valueId : item.communityId }
                   }
                   
                   ));
               }
           });
       },
       delay:1000,
       select: function(event, ui){
    	   $("#community_name").attr("data-id",ui.item.valueId);
    	   $("#community_name").val(ui.item.label);
    	   $("#building_name").val("");
    	   $("#building_name").attr("data-id","");
    	   event.preventDefault();  
       }
   });

	$( "#building_name" ).autocomplete({
        source:function(request,response){
           $.ajax({
               type:"POST",
               url:"buildingAction!getBuilding.action",
               dataType:"json",
               data : {
                   "inParma": JSON.stringify({"communityName" : request.term,"communityId":$("#community_name").attr("data-id"),"fuzzy":true})
               },
               success: function(data) {
            	   data = eval("("+data+")").data;
                   response($.map(data, function(item) {
                       return { label: item.buildingName, value: item.buildingName, valueId : item.buildingId }
                   }
                   
                   ));
               }
           });
       },
       delay:1000,
       select: function(event, ui){
    	   $("#building_name").attr("data-id",ui.item.valueId);
    	   $("#building_name").val(ui.item.label);
    	   event.preventDefault();  
       }
   });
	
});