/**
 * 控制器
 * 
 * @author zhenggw
 */
var noticeReleaseApp = angular.module("noticeReleaseApp", ["commonApp", "propertyServiceApp", "noticeServiceApp"]);

noticeReleaseApp.controller("noticeReleaseController", [ "$scope", "commonService", "propertyService",
		"noticeService", "$timeout", "$log",
		function($scope, commonService, propertyService, noticeService, $timeout, $log) {
		$scope.isPc = isPC();
		$scope.notice = {};
		$scope.$btn = null;
		//加载出错，弹出提示框
	    $scope.isSuccess = true;
	    $scope.$watch('isSuccess',function(nv,ov){
			if(!nv){
				$scope.isSuccess = true;
			    MESSAGE_DIALOG.error("出错了！");
			    $scope.$btn.button('reset');
			    setTimeout(function(){MESSAGE_DIALOG.close()},2000);
			}
	    });
	    // 查询小区信息
		$scope.queryCommunity = function () {
			// 服务请求
			propertyService.queryCommunity( {
					"inParma" : "{}", "page" : ++$scope.pageNum
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
		
		// 查询小区楼栋信息
		$scope.queryBuilding = function (communityId) {
			$scope.communityId = communityId;
			// 服务请求
			propertyService.queryBuilding( {
				"inParma" : JSON.stringify( {
					"communityId" : communityId
				}), "page" : ++$scope.pageNum
			},function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					if (data.data) {
						for (var i=0; i<data.data.length; i++) {
							$scope.buildings.push(data.data[i]);
						}
					}
					},
					function() {
						$scope.isSuccess = false;
					}
				);
			}
		
		// 查询楼栋单元信息
		$scope.queryBuildingUnit = function (buildingId) {
			$scope.buildingId = buildingId;
			// 服务请求
			propertyService.queryBuilding( {
				"inParma" : JSON.stringify( {
					"ownerBuilding" : buildingId
				}), "page" : ++$scope.pageNum
			},function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					// 调用成功
					if (data.data) {
						for (var i=0; i<data.data.length; i++) {
							$scope.buildingUnits.push(data.data[i]);
						}
					}
					},
					function() {
						$scope.isSuccess = false;
					}
				);
			}
		// 查询房间信息
		$scope.queryRoom = function (buildingId) {
			$scope.buildingUnitId = buildingId;
			// 服务请求
			propertyService.queryRoom( {
				"inParma" : JSON.stringify( {
					"buildingId" : buildingId
				}), "page" : ++$scope.pageNum
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
		// 改变小区下拉框
		$scope.changeCommunity = function (communityId) {
			$scope.pageNum = 0;
			$scope.buildings = [];
			$scope.queryBuilding(communityId);
		}
		// 改变楼栋下拉框
		$scope.changeBuilding = function (buildingId) {
			$scope.pageNum = 0;
			$scope.buildingUnits = [];
			$scope.queryBuildingUnit(buildingId);
		}
		// 改变单元
		$scope.changeBuildingUnit = function (buildingId) {
			$scope.pageNum = 0;
			$scope.rooms = [];
			$scope.queryRoom(buildingId);
		}
		// 选择发送对象
	    $scope.choose = function (flag) {
	    	// 初始化
	    	$scope.flag = flag;
	    	$scope.pageNum = 0;
	    	$("#checkAll").attr("checked", false);
	    	$scope.communitys = [];
	    	$scope.buildings = [];
	    	$scope.buildingUnits = [];
	    	$scope.rooms = [];
	    	$scope.queryCommunity();
	    	$("#myModal").modal('show');
	    }
	    // 保存发送对象
	    $scope.chk_value = [];
	    $scope.chk_id = [];
	    $scope.onSure = function () {
	    	$scope.chk_value = [];
	    	$scope.chk_id = [];
	    	$scope.choseTitle = "";
	    	$scope.choseValue = "";
	    	$scope.classCode = "";
			$('input[name="check"]:checked').each(function(){
				$scope.chk_value.push($(this).val());
				var obj = JSON.parse($(this).val());
				if ($scope.flag == 1) {
					$scope.classCode = "Community";
					$scope.chk_id.push(obj.communityId);
		    		$scope.choseTitle = "小区：";
		    		$scope.choseValue += obj.communityName;
		    		$scope.choseValue += " | "
		    	} else if ($scope.flag == 2) {
		    		$scope.classCode = "Building";
		    		$scope.chk_id.push(obj.buildingId);
		    		$scope.choseTitle = "楼栋：";
		    		$scope.choseValue += obj.buildingName;
		    		$scope.choseValue += " | "
		    	} else if ($scope.flag == 3) {
		    		$scope.classCode = "Building";
		    		$scope.chk_id.push(obj.buildingId);
		    		$scope.choseTitle = "单元：";
		    		$scope.choseValue += obj.buildingName;
		    		$scope.choseValue += " | "
		    	} else if ($scope.flag == 4) {
		    		$scope.classCode = "Room";
		    		$scope.chk_id.push(obj.roomId);
		    		$scope.choseTitle = "房间：";
		    		$scope.choseValue += obj.roomNbr;
		    		$scope.choseValue += " | "
		    	} 
			});
			$("#myModal").modal('hide');
	    }
	    // 发送
	    $scope.send = function () {
	    	$scope.$btn = $("#send").button('loading');
	    	$scope.saveNotice("1000");
	    }
	    // 保存草稿
	    $scope.saveDraft = function () {
	    	$scope.$btn = $("#draft").button('loading');
	    	$scope.saveNotice("1200");
	    }
	    // 保存
	    $scope.saveNotice = function (statusCd) {
	    	$scope.notice.content = $('#textarea').val();
	    	var jsondata = {notice:$scope.notice, entts:$scope.chk_id, classCode:$scope.classCode, communityId:$scope.communityId, statusCd:statusCd};
	    	noticeService.saveNotice( {
					"jsondata" : JSON.stringify(jsondata)
				},
				function(data) {
					data = eval("(" + data + ")");
					if (data.result=="false") {
						$scope.isSuccess = false;
						return false;
					}
					MESSAGE_DIALOG.alert("保存成功！");
			    	setTimeout(function(){
			    		window.location.reload();
			    		$scope.$btn.button('reset');
			    	},1000);
				},
				function() {
					$scope.isSuccess = false;
				}
			);
		}
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
	    $(function(){
	    	if ($scope.isPc) {
	    		$("#ui-header").addClass("hide");
	    	}
		  	$('#myModal').scroll(function() {  
		      //当内容滚动到底部时加载新的内容  
		      if ($(this).scrollTop() + $('#myModal').height() + 2 >= $('#myModal').height() && $(this).scrollTop() > 2) {  
		          //当前要加载的页码 
		    	  if ($scope.flag == 1) {
		    		  $scope.queryCommunity();
		    	  } else if ($scope.flag == 2) {
		    		  $scope.queryBuilding($scope.communityId);
		    	  } else if ($scope.flag == 3) {
		    		  $scope.queryBuilding($scope.buildingId);
		    	  } else if ($scope.flag == 4) {
		    		  $scope.queryRoom($scope.buildingUnitId);
		    	  } 
		    	}
		  })
		});  
} ]);

$('#textarea').wysihtml5();
$('#myModal').on('show.bs.modal', function (event) {
  $('#myModal').css({'overflow-y':'scroll'});
});