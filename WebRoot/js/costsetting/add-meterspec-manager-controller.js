'use strict';
/**
 * 
 * 控制器
 * 
 * @author tanyw
 */
var meterSpecApp = angular.module("meterSpecApp",["commonApp","costSettingServiceApp","directiveUtilApp", "propertyServiceApp",'ui.event', 'ui.autocomplete']);

meterSpecApp.controller("meterSpecController", [ "$scope", "commonService","costSettingService",
		"propertyService", "$timeout", "$log","DirectiveUtil","$compile",
		function($scope, commonService,costSettingService, propertyService, $timeout, $log,DirectiveUtil,$compile) {
	
		//加载出错，弹出提示框
	    $scope.isSuccess = true;
	    $scope.$watch('isSuccess',function(nv,ov){
			if(!nv){
				$scope.isSuccess = true;
			    MESSAGE_DIALOG.error("出错了！");
			    setTimeout(function(){MESSAGE_DIALOG.close()},2000);
			}
	    });
	    $scope.data=[{id:'12',name:"==小区=="},{id:'14',name:"==楼栋=="},{id:'17',name:"==楼层=="}];
	    $scope.selectValue='12';
	    $scope.show=function(value){
	    	if(value=="14"){
	    		$scope.showBuilding=true;
	    		$scope.showFloor=false;
	    	}else if(value=="17"){
	    		$scope.showBuilding=true;
	    		$scope.showFloor=true;
	    	}else if(value=="12"){
	    		$scope.showBuilding=false;
	    		$scope.showFloor=false;
	    	}
	    }
	    $scope.showQueryModel=function(){
	    	$("#queryModal").modal("show");
	    }
        $scope.changeClass= function(obj){
        	var widget=obj.methods.widget();
        	widget.removeClass('ui-menu ui-corner-all ui-widget-content').addClass('dropdown-menu')
        	.addClass("defined-modal-input");
        }
        $scope.propertyCompanys = {
            options: {
                html: true,
                minLength: 1,
                onlySelectValid: true,
                outHeight: 50,
                source: function (request, response) {
	        		propertyService.queryCompany({"inParma":JSON.stringify({"companyName":request.term,"qryType":"getCompanySimple"})},
		        		function(data){
		        			data=eval("("+data+")").data;
		        			if(data.result=="fasle"){
		        				$scope.isSuccess=false;
		        				return false;
		        			}
		        			response($.map(data,function(item){
	        					return { label: item.companyName+"（"+item.regionWithSHQ+"）", value: item.companyName ,companyId:item.companyId}
	        				}));
		        		},
		        		function(){
		        			$scope.isSuccess=false;
		        		}
		        	);
                }
            },
            events: {
                change: function (event, ui) {
                    if(ffc.util.isEmpty(ui.item)){
        				$scope.propertyCompanys=null;
        			}
                },
                select: function (event, ui) {
                    console.log('select', event, ui);
                    $scope.propertyCompany=ui.item;
                }
            }
        };
        $scope.communitys={
        	options:{
        		html:true,
        		minLength:1,
        		onlySelectValid:true,
        		outHeight:50,
        		source:function(request,response){
        			if(ffc.util.isEmpty($scope.propertyCompany)
        					||ffc.util.isEmpty($scope.propertyCompany.value)){
        				MESSAGE_DIALOG.error("请先选择物业公司！");
        				return false;
        			}
        			propertyService.queryCommunity(
        				{"inParma":JSON.stringify({"communityName":request.term,"companyId":$scope.propertyCompany.companyId,"qryType":"getCommunitySimple"})
        				},
        				function(data){
        					data=eval("("+data+")").data;
        					if(data.result=="false"){
        						$scope.isSuccess=false;
        					}
        					response($.map(data,function(item){
        						return { label: item.communityName+"（"+item.regionWithSHQ+"）", value: item.communityName,communityId:item.communityId}
        					}));
        				},
        				function(){
        					$scope.isSuccess=false;
        				}
        				
        			);
        		}
        	},
        	events:{
        		change:function(event,ui){
        			if(ffc.util.isEmpty(ui.item)){
        				$scope.community=null;
        			}
        		},
        		select:function(event,ui){
        			$scope.community=ui.item;
        		}
        	}
        };
        $scope.buildings={
        	options:{
        		html:true,
        		minLength:1,
        		onlySelectValid:true,
        		outHeight:50,
        		source:function(request,response){
        			if(ffc.util.isEmpty($scope.community)
        					||ffc.util.isEmpty($scope.community.companyName)){
        				MESSAGE_DIALOG.error("请先选择小区！");
        				return false;
        			}
        			propertyService.queryBuilding(
        				{"inParma": JSON.stringify({"buildingName" : request.term,"communityId":$scope.community.communityId,"fuzzy":"true"})
        				},
        				function(data){
        					data=eval("("+data+")").data;
        					if(data.result=="false"){
        						$scope.isSuccess=false;
        					}
        					response($.map(data,function(item){
        						return { label: item.buildingName, value: item.buildingName, building : item }
        					}));
        				},
        				function(){
        					$scope.isSuccess=false;
        				}
        				
        			);
        		}
        	},
        	events:{
        		change:function(event,ui){
        			if(ffc.util.isEmpty(ui.item)){
        				$scope.building=null;
        				$scope.floors=null;
        			}
        		},
        		select:function(event,ui){
        			$scope.building=null;
        			$scope.building=ui.item;
        			var floorCount=$scope.building.building.floorCount;
        			var floors=[];
        			if(!ffc.util.isEmpty(floorCount)){
        				for(var tempCount=1;tempCount<=floorCount;tempCount++){
        				floors.push({"floorId":tempCount,"floorName":tempCount+"层"});
	        			}
        			}
        			$scope.floors=floors;
        			
        		}
        	}
        };
        $scope.resetCommpanyChild=function(){
        	$scope.building=null;
        	$scope.communitys=null;
        };
        $scope.resetBuildingChild=function(){
        	$scope.building=null;
        };
        $scope.meterSpecs=[];
        $scope.meterSpecs.push({parentMeterSpecId:"",meterSpecId:1,meterName:"",price:""});
        $scope.addMeterSpec = function (obj) {
        	var tempStr=(new Date()).valueOf();
            $scope.meterSpecs.push({parentMeterSpecId:tempStr,meterSpecId:tempStr,meterName:"",price:""});
            $scope.queryMeterSpecUps(tempStr);
//            $scope.queryCaculateMethod(tempStr);
        };
	    $scope.removeMeterSpec=function(meterSpec){
	    	if(!ffc.util.isEmpty(meterSpec)){
	    		var length=$scope.meterSpecs.length;
		    	if(length!=1){
		    		for(var i=0;i<length;i++){
			    		if($scope.meterSpecs[i].meterSpecId==meterSpec.meterSpecId){
			    			$scope.meterSpecs.splice(i,1);
			    		}
		    		}
		    	}
	    	}
	    };
	    //查询费用大类
	    $scope.meterSpecUps=null;
		$scope.queryMeterSpecUps=function(number){
			if(!ffc.util.isEmpty($scope.meterSpecUps)){
				var meterSpecUpsAll="meterSpecUps"+number;
				$scope[meterSpecUpsAll]=$scope.meterSpecUps;
			}else{
				costSettingService.queryMeterSpec({"inParma":JSON.stringify({"qryType":"top"})},
				function(data){
					data=eval("("+data+")");
					if(data.result=="false"){
						$scope.isSuccess=false;
						return false;
					}
					$scope.meterSpecUps=data.data;
//					$scope[meterSpecUpsAll]=data.data;
					var meterSpecUpsAll="meterSpecUps"+number;
					$scope[meterSpecUpsAll]=$scope.meterSpecUps;
				},
				function(){
					$scope.isSuccess=false;
				}
				
				);
			}
		}
		$scope.queryMeterSpecUps(1);
		//查询物业细类
//		$scope.queryAcctItemType=function(upAcctItemTypeId,number){
//			var acctItemTypeAll="acctItemTypes"+number;
//			var acctItemTypeUp="acctItemTypesUp"+upAcctItemTypeId;
//			var unitSingle="unit"+number;
//			//如果已经通过上级取过下级了就不需要去库里面再取数据，加速界面的加载
//			if(!ffc.util.isEmpty($scope[acctItemTypeUp])){
//				$scope[acctItemTypeAll]=$scope[acctItemTypeUp];
//				angular.forEach($scope[acctItemTypeUp],function(value){
//					if(value.parentAcctTypeId==upAcctItemTypeId){
//						$("#"+unitSingle).html(value.unit);
//					}
//				});
//			}else{
//				costSettingService.queryAcctItemType({"inParma":JSON.stringify(
//					{"qryType":"","parentAcctItemTypeId":upAcctItemTypeId})
//					},
//					function(data){
//						data=eval("("+data+")");
//						if(data.result=="false"){
//							$scope.isSuccess=false;
//							return false;
//						}
//						$scope[acctItemTypeAll]=data.data;
//						$scope[acctItemTypeUp]=data.data;
//						angular.forEach(data.data,function(value){
//							if(value.parentAcctTypeId==upAcctItemTypeId){
//								$("#"+unitSingle).html(value.unit);
//								}
//							});
//					},
//					function(){
//						$scope.isSuccess=false;
//					}
//				
//				);
//			}
//		}
//		$scope.queryCaculateMethod=function(number){
//			costSettingService.queryCaculateMethod({"inParma" : JSON.stringify(
//				{"attrCd" : "caculate_method",
//				"qryType":"attrCd",
//				"communityId" : ""
//				})
//				},
//				function(data){
//					data=eval("("+data+")");
//					if(data.result=="false"){
//						$scope.isSuccess=false;
//						return false;
//					}
//					//计算方法都是一样的时候直接从缓存存取的值返回
////					var caculateMethodAll="caculateMethods"+number;
//					$scope.caculateMethods=data.data;
//				},
//				function(){
//					$scope.isSuccess=false;
//				}
//			);
//		}
//		$scope.queryCaculateMethod(1);
		
		$scope.clickOk=function(){
			$scope.acctItemType1;
			$scope.propertyCompany;
			$scope.community;
		}
//		$scope.changeBuildingQryRoom = function (buildingId) {
//			$scope.roomPage = 0;
//			$scope.rooms = [];
//			$scope.queryRoom(buildingId);
//		}
		$(function(){  
		 $scope.onSave = function () {
			 if($scope.selectValue=="12"){
				 if(ffc.util.isEmpty([$scope.community][0])){
				 MESSAGE_DIALOG.alert("小区必选!");
				 return false;
			 	}
			 }else if($scope.selectValue=="14"){
				 if(ffc.util.isEmpty([$scope.building][0])){
				 MESSAGE_DIALOG.alert("楼栋必选!");
				 return false;
			 	}
			 }else if($scope.selectValue=="17"){
				 if(ffc.util.isEmpty([$scope.floor][0])){
				 MESSAGE_DIALOG.alert("楼层必选!");
				 return false;
			 	}
			 }
			 $("#submit_btn").attr("disabled",true);
			 // 保存逻辑
			 // 服务请求
			costSettingService.saveMeterSpec({"inParma" : JSON.stringify({
					"propertyCompany" : $scope.propertyCompany,
					"community" : $scope.community,
					"building":$scope.building,
					"floor":$scope.floor,
					"saveType":$scope.selectValue,
					"meterSpecs":$scope.meterSpecs
				})},
				function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						MESSAGE_DIALOG.alert(data.msg);
						$("#submit_btn").attr("disabled",false);
						return false;
					}
					// 调用成功
//					$('#myModal').modal('hide');
					MESSAGE_DIALOG.alert("保存成功");
					setTimeout(function(){MESSAGE_DIALOG.close()
						location.href="meterSpecSettingManager.jsp";
					},1500);
				},
				function() {
						$scope.isSuccess = false;
					}
				);
		 }
});  
} ]);
