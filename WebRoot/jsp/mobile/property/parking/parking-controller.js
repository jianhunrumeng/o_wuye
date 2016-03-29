/**
 * 控制器
 * 
 * @author zhenggw
 */
var parkingApp = angular.module("parkingApp", ["commonApp", "propertyServiceApp"]);

parkingApp.controller("parkingController", [ "$scope", "commonService",
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
		    
		// 查询车位信息
		$scope.queryParking = function () {
			if (!$scope.nbr) {
				MESSAGE_DIALOG.warning("请输入车位号或车牌号！");
				setTimeout(function(){MESSAGE_DIALOG.close()},1000);
				return;
			}
			// 服务请求
			propertyService.queryParking({"inParma" : JSON.stringify( {
					"parkingNbr" : $scope.nbr
				})},
				function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					$scope.parkings = data.data;
					},
					function() {
						$scope.isSuccess = false;
					}
				);
			}
} ]);
