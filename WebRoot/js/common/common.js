/**
 * 
 * 通用JS模块
 * 
 * 描述:
 * </p>
 * 1:ffc.context 上下文变量;
 * </p>
 * 2:ffc.util 全局工具类;
 * </p>
 * 3:ffc.comp;
 * </p>
 * 4:angularJS 封装工具类;
 * </p>
 * 
 */

registNS = function(fullNS, isIgnorSelf) {
    // 命名空间合法性校验依据
    var reg = /^[_$a-z]+[_$a-z0-9]*/i;

    // 将命名空间切成N部分, 比如ffc.util等
    var nsArray = fullNS.split('.');
    var sEval = "";
    var sNS = "";
    var n = isIgnorSelf ? nsArray.length - 1 : nsArray.length;
    for (var i = 0; i < n; i++) {
	// 命名空间合法性校验
	if (!reg.test(nsArray[i])) {
	    throw new Error("Invalid namespace:" + nsArray[i] + "");
	    return;
	}
	if (i != 0)
	    sNS += ".";
	sNS += nsArray[i];
	// 依次创建构造命名空间对象（假如不存在的话）的语句
	sEval += "if(typeof(" + sNS + ")=='undefined') " + sNS + "=new Object();else " + sNS + ";";
    }
    // 生成命名空间
    if (sEval != "") {
	return eval(sEval);
    }
    return {};
}

// 注册命名空间

registNS("ffc.context");
registNS("ffc.util");
registNS("ffc.comp");

// 全局变量
//ffc.context.path = path;
//ffc.context.contextPath = contextPath;
ffc.context.reqMetaUrl = "/metadata";
ffc.context.reqSugUrl = "/suggestion";
ffc.context.errorTip = "不存在的返回结果！";
ffc.context.timeoutTip = "网络连接已断开！";

// antularJS封装
var commonApp = angular.module("commonApp", []);

commonApp.service("commonService", function($http) {
    var _this = this;
    /**
     * 统一HTTP参数
     */

    var http = function(method, url, param) {
        if(method=='POST'){
        	return $http({
        	    method : method,
        	    url : url,
        	    data : param,
        	    headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
        	    transformRequest: function(data){
			       if (data === undefined) {
			           return data;
			       }
			       return $.param(data);
			   }
        //	    ,
        //	    headers : {
        //		'Content-Type' : 'application/x-www-form-urlencoded'
        //	    }
        	})
        }else{
            return $http({
                method : method,
                url : url,
                params : param
            })
        }
    }
    /**
     * 统一同步的syncHttp参数
     */
    var syncHttp = function(method, url, param) {
	return $.ajax({
	    method : method,
	    async : false,
	    url : url,
	    data : param
	});
    }

    /**
     * 获取绝对路径
     */
    this.getContextPath = function() {
	return contextPath;
    }
    /**
     * 获取项目名称
     */
    this.getPath = function() {
	/*
	 * path = path + ""; if (path) { if (path.indexOf("/") >= 0) { return
	 * path.substring(path.indexOf("/")+1); } }
	 */
	return path;
    }

    /**
     * 通用angularJS HTTP请求
     * 
     * @param url
     * @param method
     * @param param
     * @param sucCallback
     * @param errCallBack
     */
    this.call = function(url, param, sucCallback, errCallBack) {
	return http("POST", url, param).success(function(data, status, headers, config) {
	    if (angular.isFunction(sucCallback)) {
	    	if (data.result == false
	    			&& data.exceptions.length > 0) {
	    		MESSAGE_DIALOG.warning("【" + data.retCode + "】 " + "业务异常：" + data.msgTitle   );
	    	} else {
	    		sucCallback(data);
	    	}
		
	    }
	}).error(function(data, status, headers, config) {
	    // 失败处理
	    if (angular.isFunction(errCallBack)) {
		errCallBack(data);
	    }
	});
    };

    /**
     * by get 请求
     * 
     * @param url
     * @param param
     * @param sucCallback
     * @param errCallBack
     */
    this.callByGet = function(url, param, sucCallback, errCallBack) {
	return http("GET", url, param).success(function(data, status, headers, config) {
	    if (angular.isFunction(sucCallback)) {
		sucCallback(data);
	    }
	}).error(function(data, status, headers, config) {
	    // 失败处理
	    if (angular.isFunction(errCallBack)) {
		errCallBack(data);
	    }
	});
    };

    /**
     * 同步的 HTTP请求
     * 
     * @param url
     * @param param
     */
    this.syncCall = function(url, param) {
	var result = {
	    data : "error"
	}
	syncHttp("POST", url, param).success(function(data, textStatus, jqXHR) {
	    result.data = data;
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {

	});
	return result.data;
    };

    /**
     * by get 同步请求
     * 
     * @param url
     * @param param
     */
    this.syncCallByGet = function(url, param) {
	var result = {
	    data : "error"
	}
	syncHttp("GET", url, param).success(function(data, textStatus, jqXHR) {
	    result.data = data;
	}).fail(function(XMLHttpRequest, textStatus, errorThrown) {

	});
	return result.data;
    };

    /**
     * data To Table Rows 格式转换
     * 
     * @param data
     */
    this.cov2TableRows = function(data) {
	var covData = {
	    rows : data.list,
	    total : data.total
	}
	return covData;
    };

    // form初始化
    this.initFrom = function($scopmeta, backfun) {
	var urlParam="";
	$("[data-metadata]").each(function(i, d) {
	    if(i==0){
		urlParam=urlParam+"?metadatas="+($(this).data("metadata"));
	    }else{
		urlParam=urlParam+","+($(this).data("metadata"));
	    }
	});
	// 存在下拉情况
	if (urlParam!="") {
	    _this.call(ffc.context.reqMetaUrl+urlParam, {},
		function(data) {
		// 请求成功
		// 初始数据绑定
		$scopmeta.metadatas = data;
		// 回调函数
		if (angular.isFunction(backfun)) {
		    backfun();
		}
	    }, function(data) {
		// 失败处理
	    });
	} else if (angular.isFunction(backfun)) {
	    // 没有下拉组件回调
	    backfun();
	}
    }

})

// 全局工具

/**
 * 日期格式化
 * 
 * @param date
 * @param fmt
 */
ffc.util.formatter = function(date, fmt) {
    // 日期格式化
    var o = {
	"M+" : date.getMonth() + 1, // month
	"d+" : date.getDate(), // day
	"h+" : date.getHours(), // hour
	"m+" : date.getMinutes(), // minute
	"s+" : date.getSeconds(), // second
	"q+" : Math.floor((date.getMonth() + 3) / 3), // quarter
	"S" : date.getMilliseconds()
    // millisecond
    };
    if (/(y+)/.test(fmt)) {
	fmt = fmt.replace(RegExp.$1, (date.getFullYear() + "").substr(4 - RegExp.$1.length));
    }
    for ( var k in o) {
	if (new RegExp("(" + k + ")").test(fmt)) {
	    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	}
    }
    return fmt;
}
/**
 * bootstrap table formatter 格式化
 * 
 * @param value
 *                new Date()对象;
 * @param row
 * @param index
 */
ffc.util.tableDateFormatter = function(value, row, index) {
    if (value) {
	return ffc.util.formatter(new Date(value), "yyyy-MM-dd hh:mm:ss");
    }
    return "";
}
// 获取table pageSize
ffc.util.getTablePageSize = function($jq) {
    return $jq.bootstrapTable('getOptions').pageSize;
}
// 获取table pageNumber
ffc.util.getTablePageNumber = function($jq) {
    return $jq.bootstrapTable('getOptions').pageNumber;
}

/**
 * 获取Page MATADATAS 绑定
 * 
 * @param $scope
 * 
 */
ffc.util.getPageMetas = function($scope) {
    return $scope.initData = {
	metadatas : {}
    }
}
// bootstrap-table bind reqUrl data2
ffc.util.loadTableData = function(render, result) {
    if (result&&result.result && undefined != result.pageInfo.list ) {
	render.success({
	    total : result.pageInfo.total+"",
	    rows : result.pageInfo.list
	});
    } else {
	render.success({
	    total : "0",
	    rows : []
	});
    }
    render.complete();
}
// bootstrap-table bind pageEvent
ffc.util.bindTablePageEvent = function($jq, param, fun) {
    if ($jq) {
	// 释放监听table分页点击事件
	$jq.unbind("page-change.bs.table");
	// 监听table分页点击事件
	$jq.on('page-change.bs.table', function(e, size, number) {
	    // 分页信息
	    param.pageNumber = number;
	    param.pageSize = size;
	    fun(param)
	});
    }
}
// bootstrap-table bind sortEvent
ffc.util.bindTableSortEvent = function($jq, param, fun) {
    if ($jq) {
	// 释放监听table分页点击事件
	$jq.unbind("sort.bs.table");
	// 监听table分页点击事件
	$jq.on('sort.bs.table', function(e, name, order) {
	    // 刷新到第一页
	    $jq.bootstrapTable('refreshOptions', {
		pageNumber : 1
	    });
	    // 排序信息
	    param.pageNumber = ffc.util.getTablePageNumber($jq);
	    param.pageSize = ffc.util.getTablePageSize($jq);
	    param.sortName = name;
	    param.sortOrder = order;
	    fun(param);
	});
    }
}
// 判断空
ffc.util.isEmpty = function(val) {
    if ((val == null || typeof (val) == "undefined") || (typeof (val) == "string" && val == "" && val != "undefined")) {
	return true;
    } else {
	return false;
    }
}
// 超过长度，截取并填充...
ffc.util.interceptString = function(val, len) {
    if (val.length > len) {
	return val.substring(0, len) + "...";
    } else {
	return val;
    }
}
//tab切换触发angular事件
ffc.util.tabsShownTriggerNg = function(suf) {
    var suf_="_ngtabclick";
    if(!ffc.util.isEmpty(suf)){
	suf_=suf;
    }
    $('a[data-toggle="tab"]').on('shown.bs.tab', function(e) {
	// 获取已激活的标签页的名称
	var activeTab = $(e.target).attr("href");
	$(activeTab + suf_).click();
    });
}
//触发激活状态tab页的angular事件
ffc.util.tabsActiveTriggerNg = function(tabsHref,suf) {
    var suf_ = "_ngtabclick";
    if (!ffc.util.isEmpty(suf)) {
	suf_ = suf;
    }
    $.each(tabsHref, function(i, d) {
	if ($("a[href='" + d + "']").parent().hasClass("active")) {
	    $(d + suf_).click();
	    return false;
	}
    });
}
/**
 * 获取Page MATADATAS 绑定
 * 
 * @param $scope
 * 
 */
ffc.util.setPageResult = function($scope,errorValue,render,result) {
    if(!$scope.errorTips){
	$scope.errorTips={};
    }
    if(render){
        if(result.result){
    		$scope.errorTips[errorValue]=null;
    		ffc.util.loadTableData(render, result);
        }else{
    		$scope.errorTips[errorValue]=ffc.util.isEmpty(result.msgTitle[0])?ffc.context.errorTip:result.msgTitle[0];
        }
    }else{
	$scope.errorTips[errorValue]=ffc.context.timeoutTip;
    }
}
//获取url参数
ffc.util.getQueryString = function(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
function isPC(){
		var d=/iPhone|iPod|Android|Windows\s*Phone|SymbianOS/i;
		var x=d.exec(navigator.userAgent);
		if(!x){
			return true;
		}else if(x[0].match(/Android/i)){
			if(document.body.clientWidth>750){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}