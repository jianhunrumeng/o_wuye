/**
 * 费用服务
 * 
 * @author tanyw
 */

// 创建物业费用模块
var costSettingServiceApp = angular.module("costSettingServiceApp", [ "commonApp" ]);

costSettingServiceApp.service("costSettingService", [ "commonService", function(commonService) {
    // 获取费用大类信息
    this.queryAcctItemType = function(params, sback, eback) {
        commonService.call("acctItemTypeAction!getAcctItemType.action", params, sback, eback);
    };
    // 获取计算方法
    this.queryCaculateMethod = function(params, sback, eback) {
        commonService.call("acctItemTypeAction!getAttrValue.action", params, sback, eback);
    };
    this.saveAcctItemRel=function(params,sback,eback){
    	commonService.call("acctItemRelAction!saveAcctItemRel.action",params,sback,eback);
    };
    this.queryAcctItemRels=function(params,sback,eback){
    	commonService.call("acctItemRelAction!queryAcctItemRels.action",params,sback,eback);
    };
    this.removeAcctItemRel=function(params,sback,eback){
    	commonService.call("acctItemRelAction!removeAcctItemRel.action",params,sback,eback);
    };
     // 获取费表规格信息
    this.queryMeterSpec = function(params, sback, eback) {
        commonService.call("meterSpecAction!queryMeterSpecs.action", params, sback, eback);
    };
    //保存费表规格信息
    this.saveMeterSpec=function(params,sback,eback){
    	commonService.call("meterSpecAction!saveMeterSpec.action",params,sback,eback);
    };
} ]);