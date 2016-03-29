/**
 * 
 */
;(function($, window, document,undefined) {
})(jQuery, window, document);

function SearchSelectObj(tag,deal){
	this.tag = tag;
	this.deal = deal;
	//this.child = child;
}

SearchSelectObj.prototype.clear = function(){
	$(this.tag).attr("data-id",null);
	$(this.tag).val("");
	if (this.child != null && typeof(this.child) != "undefined"){
		   this.child.clear();
		   this.child.notEdit();
	   }
};
SearchSelectObj.prototype.add = function(child){
	this.child = child;
	return this;
};
SearchSelectObj.prototype.notEdit = function(){
	$(this.tag).attr("disabled",true);
};

SearchSelectObj.prototype.canEdit = function(){
	$(this.tag).attr("disabled",false);
};

function CompanyObj(tag,deal){
	SearchSelectObj.call(this,tag);
	var _this = this;
	this.complete = function(){
		if (this.child != null && typeof(this.child) != "undefined" && this.child != "null"){
			this.child.notEdit();
			this.child.complete();
		}
		if (deal != null && deal.init != null){
			   deal.init();
		   }
		$(tag).autocomplete({
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
	    	   if (_this.child != null && _this.child != undefined && _this.child != "null"){
	    		   _this.child.canEdit();
	    		   $(_this.child.tag).attr("data-p_id",ui.item.companyId);//设置父ID
	    	   }
	    	   if (deal != null && deal.select != null){
	    		   deal.select();
	    	   }
	    	   //$("#community").val(ui.item.label);
	    	   //$("#community").val(ui.item.value);
	    	   //event.preventDefault();  
	       },
	       change: function( event, ui ) {
	           // event 是当前事件对象
	           
	           // ui对象仅有一个item属性，它表示当前选择的菜单项对应的数据源对象
	           // 该对象具有label和value属性，以及其它自定义(如果有的话)的属性
	           // 如果当前没有选择的菜单项，这item属性为null
	    	   if (ui.item == null || ui.item == "null" || typeof(ui.item) == "undefined"){
//	    		   $(tag).attr("data-id",null);
	    		   _this.clear();
	    		   if (_this.child != null){
	    			   _this.child.clear();
	    			   _this.child.notEdit();
	    		   }
	    		   if (deal != null && deal.clear != null){
	    			   deal.clear();
	    		   }
	    	   }
	       }
	   });
	};
}

function ComunityObj(tag,deal){
	SearchSelectObj.call(this,tag);
	var _this = this;
	this.complete = function(){
		if (this.child != null && typeof(this.child) != "undefined" && this.child != "null"){
			this.child.notEdit();
			this.child.complete();
		}
		if (deal != null && deal.init != null){
			   deal.init();
		   }
		$( tag ).autocomplete({
	        source:function(request,response){
	           $.ajax({
	               type:"POST",
	               url:"communityAction!getCommunity.action",
	               dataType:"json",
	               data : {
	                   "inParma": JSON.stringify({"communityName":request.term,"companyId":$(_this.tag).attr("data-p_id"),"qryType":"getCommunitySimple"})
	               },
	               success: function(data) {
	            	   data = eval("("+data+")").data;

	                   response($.map(data, function(item) {
	                	   
	                       return { label: item.communityName+"（"+item.regionWithSHQ+"）", value: item.communityName ,communityId:item.communityId}
	                   }
	                   
	                   ));
	               }
	           });
	       },
	       delay:500,
	       select: function(event, ui){
	    	   $(tag).attr("data-id",ui.item.communityId);
	    	   if (_this.child != null && _this.child != undefined && _this.child != "null"){
	    		   _this.child.canEdit();
	    		   $(_this.child.tag).attr("data-p_id",ui.item.communityId);//设置父ID
	    	   }
	    	   if (deal != null && deal.select != null){
	    		   deal.select();
	    	   }
	    	   //$("#community").val(ui.item.label);
	    	   //$("#community").val(ui.item.value);
	    	   //event.preventDefault();  
	       },
	       change: function( event, ui ) {
	           // event 是当前事件对象
	           
	           // ui对象仅有一个item属性，它表示当前选择的菜单项对应的数据源对象
	           // 该对象具有label和value属性，以及其它自定义(如果有的话)的属性
	           // 如果当前没有选择的菜单项，这item属性为null
	    	   if (ui.item == null || ui.item == "null" || typeof(ui.item) == "undefined"){
//	    		   $(tag).attr("data-id",null);
	    		   _this.clear();
	    		   if (_this.child != null){
	    			   _this.child.clear();
	    			   _this.child.notEdit();
	    		   }
	    		   if (deal != null && deal.clear != null){
	    			   deal.clear();
	    		   }
	    	   }
	       }
	   });
	};
}
function BuddingObj(tag,deal){
	SearchSelectObj.call(this,tag);
	var _this = this;
	this.complete = function(){
		if (this.child != null && typeof(this.child) != "undefined" && this.child != "null"){
			this.child.notEdit();
			this.child.complete();
		}
		if (deal != null && deal.init != null){
			   deal.init();
		   }
		$( tag ).autocomplete({
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
	       delay:500,
	       select: function(event, ui){
	    	   $(tag).attr("data-id",ui.item.buildingId);
	    	   if (_this.child != null && _this.child != undefined && _this.child != "null"){
	    		   _this.child.canEdit();
	    		   $(_this.child.tag).attr("data-p_id",ui.item.buildingId);//设置父ID
	    	   }
	    	    if (deal != null && deal.select != null){
	    		   deal.select();
	    	   }
	    	   //$("#community").val(ui.item.label);
	    	   //$("#community").val(ui.item.value);
	    	   //event.preventDefault();  
	       },
	       change: function( event, ui ) {
	           // event 是当前事件对象
	           
	           // ui对象仅有一个item属性，它表示当前选择的菜单项对应的数据源对象
	           // 该对象具有label和value属性，以及其它自定义(如果有的话)的属性
	           // 如果当前没有选择的菜单项，这item属性为null
	    	   if (ui.item == null || ui.item == "null" || typeof(ui.item) == "undefined"){
//	    		   $(tag).attr("data-id",null);
	    		   _this.clear();
	    		   if (_this.child != null){
	    			   _this.child.clear();
	    			   _this.child.notEdit();
	    		   }
	    		    if (deal != null && deal.clear != null){
	    			   deal.clear();
	    		   }
	    	   }
	       }
	   });
	};
}

CompanyObj.prototype=new SearchSelectObj();
CompanyObj.prototype.constructor=CompanyObj;

ComunityObj.prototype=new SearchSelectObj();
ComunityObj.prototype.constructor=ComunityObj;

BuddingObj.prototype=new SearchSelectObj();
BuddingObj.prototype.constructor=BuddingObj;