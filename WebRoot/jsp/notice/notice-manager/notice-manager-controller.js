/**
 * 控制器
 * 
 * @author zhenggw
 */
var noticeManagerApp = angular.module("noticeManagerApp", ["commonApp", "propertyServiceApp", "noticeServiceApp"]);

noticeManagerApp.controller("noticeManagerController", [ "$scope", "commonService",
		"propertyService", "noticeService", "$timeout", "$log",
		function($scope, commonService, propertyService, noticeService, $timeout, $log) {
	
		$scope.communitys = [];
		$scope.noticeTypes = [];
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
			propertyService.queryCommunity( {
					"inParma" : "{}"
				},
				function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					if (data.data) {
						for (var i=0; i<data.data.length; i++) {
							$scope.communitys.push(data.data[i]);
						}
					}
				},
				function() {
					$scope.isSuccess = false;
				}
			);
		}
		$scope.queryCommunity();
		// 查询通知类型
		$scope.queryNoticeType = function () {
			// 服务请求
			noticeService.queryNoticeType( {
					"jsondata" : "{}"
				},
				function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					$scope.noticeTypes = data.data;
				},
				function() {
					$scope.isSuccess = false;
				}
			);
		}
		$scope.queryNoticeType();
		
		// 查询通知
		$scope.queryNotice = function () {
			var jsondata = {communityId : $scope.communityId,noticeType : $scope.noticeType};
			// 服务请求
			noticeService.queryNotice( {
					"jsondata" : JSON.stringify(jsondata)
				},
				function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					$scope.notices = data.data;
				},
				function() {
					$scope.isSuccess = false;
				}
			);
		}
} ]);
