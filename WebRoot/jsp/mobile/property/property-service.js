/**
 * 物业服务
 * 
 * @author zhenggw
 */

// 创建物业服务模块
var propertyServiceApp = angular.module("propertyServiceApp", [ "commonApp" ]);

propertyServiceApp.service("propertyService", [ "commonService", function(commonService) {
	// 获取物业公司信息
    this.queryCompany = function(params, sback, eback) {
        commonService.call("communityAction!getCompany.action", params, sback, eback);
    };
    // 获取小区信息
    this.queryCommunity = function(params, sback, eback) {
        commonService.call("communityAction!getCommunity.action", params, sback, eback);
    };
    // 获取小区楼栋信息
    this.queryBuilding = function(params, sback, eback) {
        commonService.call("buildingAction!getBuilding.action", params, sback, eback);
    };
    // 获取房间信息
    this.queryRoom = function(params, sback, eback) {
    	commonService.call("roomAction!getRoom.action", params, sback, eback);
    };
    // 获取车位信息
    this.queryParking = function(params, sback, eback) {
    	commonService.call("parkingAction!getParking.action", params, sback, eback);
    };
    // 添加住户
    this.addPartyInfos = function(params, sback, eback) {
    	commonService.call("roomAction!addPartyInfos.action", params, sback, eback);
    };
} ]);