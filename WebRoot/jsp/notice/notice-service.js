/**
 * 通知公告服务
 * 
 * @author zhenggw
 */

// 创建模块
var noticeServiceApp = angular.module("noticeServiceApp", [ "commonApp" ]);

noticeServiceApp.service("noticeService", [ "commonService", function(commonService) {
  	// 查询通知类型
    this.queryNoticeType = function(params, sback, eback) {
        commonService.call("noticeAction!queryNoticeType.action", params, sback, eback);
    };
    // 保存通知
    this.saveNotice = function(params, sback, eback) {
        commonService.call("noticeAction!saveNotice.action", params, sback, eback);
    };
    // 查询通知
    this.queryNotice = function(params, sback, eback) {
        commonService.call("noticeAction!queryNotice.action", params, sback, eback);
    };
} ]);