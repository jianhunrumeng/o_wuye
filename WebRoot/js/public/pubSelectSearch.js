/**
 * 
 */
function communitySearch(tag){
	tag = arguments[1]?arguments[1]:"#pp_company";
	$( tag ).autocomplete({
        source:function(request,response){
           $.ajax({
               type:"POST",
               url:"communityAction!getCompany.action",
               dataType:"json",
               data : {
                   "inParma": JSON.stringify({"companyName":request.term,"qryType":"getCompanySimple"})
               },
               success: function(data) {
            	   data = eval("("+data+")").data;

                   response($.map(data, function(item) {
                	   
                       return { label: item.companyName+"（"+item.regionWithSHQ+"）", value: item.companyName ,companyId:item.companyId}
                   }
                   
                   ));
               }
           });
       },
       delay:500,
       select: function(event, ui){
    	   $(tag).attr("data-id",ui.item.companyId);
    	   //$("#community").val(ui.item.label);
    	   //$("#community").val(ui.item.value);
    	   //event.preventDefault();  
       }
   });
}

/**
 * 
 */
function communitySearch(tag){
	tag = arguments[1]?arguments[1]:"#pp_company";
	$( tag ).autocomplete({
        source:function(request,response){
           $.ajax({
               type:"POST",
               url:"communityAction!getCompany.action",
               dataType:"json",
               data : {
                   "inParma": JSON.stringify({"companyName":request.term,"qryType":"getCompanySimple"})
               },
               success: function(data) {
            	   data = eval("("+data+")").data;

                   response($.map(data, function(item) {
                	   
                       return { label: item.companyName+"（"+item.regionWithSHQ+"）", value: item.companyName ,companyId:item.companyId}
                   }
                   
                   ));
               }
           });
       },
       delay:500,
       select: function(event, ui){
    	   $(tag).attr("data-id",ui.item.companyId);
    	   //$("#community").val(ui.item.label);
    	   //$("#community").val(ui.item.value);
    	   //event.preventDefault();  
       }
   });
}