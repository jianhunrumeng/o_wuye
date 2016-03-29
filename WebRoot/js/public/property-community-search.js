$(document).ready(function() {
	$( "#property_company_name" ).autocomplete({
        source:function(request,response){
           $.ajax({
               type:"POST",
               url:"propertyCompanyAction!getPropertyCompanyFiveList.action",
               dataType:"json",
               data : {
                   "jsondata": request.term
               },
               success: function(data) {
            	   data = eval("("+data+")");
                   response($.map(data, function(item) {
                       return { label: item.companyName, value: item.companyName, valueId : item.companyId }
                   }
                   
                   ));
               }
           });
       },
       delay:1000,
       select: function(event, ui){
    	   $("#property_company_name").attr("data-id",ui.item.valueId);
    	   $("#property_company_name").val(ui.item.label);
    	   $("#community_name").val("");
    	   $("#community_name").attr("data-id","");
    	   event.preventDefault();  
       }
   });

	$( "#community_name" ).autocomplete({
        source:function(request,response){
           $.ajax({
               type:"POST",
               url:"communityAction!queryFiveCommunityList.action",
               dataType:"json",
               data : {
                   "inParma": JSON.stringify({"communityName" : request.term,"companyId":$("#property_company_name").attr("data-id")})
               },
               success: function(data) {
            	   data = eval("("+data+")");
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
    	   event.preventDefault();  
       }
   });
	
});