/**
 * 控制器
 * 
 * @author zhenggw
 */
var meterReadingApp = angular.module("meterReadingApp", ["commonApp", "propertyServiceApp"]);

meterReadingApp.controller("meterReadingController", [ "$scope", "commonService",
		"propertyService", "$timeout", "$log",
		function($scope, commonService, propertyService, $timeout, $log) {
	
		//加载出错，弹出提示框
	    $scope.isSuccess = true;
	    $scope.$watch('isSuccess',function(nv,ov){
			if(!nv){
				$scope.isSuccess = true;
			    MESSAGE_DIALOG.error("出错了！");
			    setTimeout(function(){MESSAGE_DIALOG.close()},2000);
			}
	    });
		    
		// 查询小区信息
		$scope.queryCommunity = function () {
			// 服务请求
			propertyService.queryCommunity({"inParma" : "{}"},
				function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					$scope.communitys = data.data;
					},
					function() {
						$scope.isSuccess = false;
					}
				);
			}
		// 查询小区
		$scope.queryCommunity();
		
		// 查询小区楼栋信息
		$scope.queryBuilding = function (communityId) {
			// 服务请求
			propertyService.queryBuilding( {
				"inParma" : JSON.stringify( {
					"communityId" : communityId
				})
			},function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					$scope.buildings = data.data;
					},
					function() {
						$scope.isSuccess = false;
					}
				);
			}
		
		// 查询楼栋单元信息
		$scope.queryBuildingUnit = function (buildingId) {
			// 服务请求
			propertyService.queryBuilding( {
				"inParma" : JSON.stringify( {
					"ownerBuilding" : buildingId
				})
			},function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					$scope.buildingUnits = data.data;
					},
					function() {
						$scope.isSuccess = false;
					}
				);
			}
		// 查询房间信息
		$scope.roomPage = 0;
		$scope.queryRoom = function (buildingId) {
			// 服务请求
			propertyService.queryRoom( {
				"inParma" : JSON.stringify( {
					"buildingId" : buildingId
				}), "page" : ++$scope.roomPage
			},function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					if (data.data) {
						for (var i=0; i<data.data.length; i++) {
							$scope.rooms.push(data.data[i]);
						}
					}
					},
					function() {
						$scope.isSuccess = false;
					}
				);
			}
		$scope.changeBuildingQryRoom = function (buildingId) {
			$scope.roomPage = 0;
			$scope.rooms = [];
			$scope.queryRoom(buildingId);
		}
		$(function(){  
		  	$(window).scroll(function() {  
		      //当内容滚动到底部时加载新的内容  
		      if ($(this).scrollTop() + $(window).height() + 20 >= $(document).height() && $(this).scrollTop() > 20) {  
		          //当前要加载的页码 
		    	  if ($scope.buildingUnit && $scope.buildingUnit.buildingId) {
		    		  $scope.queryRoom($scope.buildingUnit.buildingId);
		    	  }
		      }  
		  });  
		 }); 
		 // 点击房间
		 $scope.clickRoom = function (room) {
			 $scope.room = room;
			$('#myModal').modal('show')
		}
		 $scope.onSave = function () {
			 // 保存逻辑
			 
			 $('#myModal').modal('hide');
		 }
} ]);
