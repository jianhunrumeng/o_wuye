(function($) {
	$.fn.myPlugin = function() {
		this.fadeOut('normal');
	};
})(jQuery);

(function($) {
	var privateFunction = function() {
		// 代码在这里运行
	}

	var methods = {
		init : function(options) {
			return this.each(function() {
				var $this = $(this);
				var settings = $this.data('pluginName');

				if (typeof (settings) == 'undefined') {

					var defaults = {
						propertyName : 'value',
						onSomeEvent : function() {
						}
					}

					settings = $.extend({}, defaults, options);

					$this.data('pluginName', settings);
				} else {
					settings = $.extend({}, settings, options);
				}
				app();
				binds();
				p_init();
				// 代码在这里运行
			});
		},
		destroy : function(options) {
			return $(this).each(function() {
				var $this = $(this);

				$this.removeData('pluginName');
			});
		},
		val : function(options) {
			app(this);
			//getCommunityData();
			binds();
			p_init(options);
			var someValue = this.eq(0).html();

			return someValue;
		}
	};

	$.fn.selectDiv = function() {
		var method = arguments[0];

		if (methods[method]) {
			method = methods[method];
			arguments = Array.prototype.slice.call(arguments, 1);
		} else if (typeof (method) == 'object' || !method) {
			method = methods.init;
		} else {
			$
					.error('Method ' + method
							+ ' does not exist on jQuery.pluginName');
			return this;
		}

		return method.apply(this, arguments);

	}
	function app(tag) {
		$(tag).addClass("city_input  inputFocus proCityQueryAll proCitySelAll");
		var ap = '<div class="provinceCityAll">'
				+ '  <div class="tabs clearfix">'
				+ '    <ul class="">'
				+ '      <li><a href="javascript:" tb="provinceAll">楼栋</a></li>'
				+ '      <li><a href="javascript:" tb="cityAll" id="cityAll">单元</a></li>'
				+ '      <li><a href="javascript:" tb="countyAll" id="countyAll">房间</a></li>'
				/*+ '      <li><a href="javascript:" tb="streetAll" id="streetAll">街道</a></li>'*/
				+ '    </ul>'
				+ '  </div>'
				+ '  <div class="con">'
				+ '    <!-- <div class="hotCityAll invis">'
				+ '      <div class="pre"><a></a></div>'
				+ '      <div class="list">'
				+ '        <ul>'
				+ '          					<li><a href="javascript:"  class="current">南京</a></li>'
				+ '        </ul>'
				+ '      </div>'
				+ '      <div class="next"><a class="can"></a></div>'
				+ '    </div> -->'
				+ '    <div class="provinceAll invis">'
				+ '      <div class="pre"><a></a></div>'
				+ '      <div class="list">'
				+ '        <ul>'
				+ '          <!-- 					<li><a href="javascript:"  class="current">江西省</a></li> -->'
				+ '        </ul>'
				+ '      </div>'
				+ '      <div class="next"><a class="can"></a></div>'
				+ '    </div>'
				+ '    <div class="cityAll invis">'
				+ '      <div class="pre"><a></a></div>'
				+ '      <div class="list">'
				+ '        <ul>'
				+ '          <!-- 					<li><a href="javascript:"  class="current">南京</a></li> -->'
				+ '        </ul>' + '      </div>'
				+ '      <div class="next"><a class="can"></a></div>'
				+ '    </div>' + '    <div class="countyAll invis">'
				+ '      <div class="pre"><a></a></div>'
				+ '      <div class="list">' + '        <ul>' + '        </ul>'
				+ '      </div>'
				+ '      <div class="next"><a class="can"></a></div>'
				+ '    </div>' 
				+ '  </div>' + '</div>';
		$(tag).after(ap);

		var vcs = '.provinceCityAll ul,li{' + '  margin: 0;' + '  padding: 0;'
				+ '  list-style: none;' + '}';
		$("style").append(vcs);
	}
	function p_init(options) {
		// 添加输入框点击事件
		$(".proCitySelAll")
				.click(
						function(event) {
							if ($("body").data("allProvinces") == null){
								getCommunityData(options.communityId);
								
							}
							
							/*if ($("body").data("CitysAll") == null) {
								sendAllCitiesAjax();
							}*/
							$(this).select();
							$(".provinceCity").hide();
							$(".provinceCityAll").hide();
							/* $("#dimCityQuery").hide(); */
							var o2 = $(this).offset();
							var l2 = o2.left;
							var t2 = o2.top;
							var h2 = $(this).height();
							var w = $(this).width();
							/*
							 * toggle() 方法切换元素的可见状态。
							 * 如果被选元素可见，则隐藏这些元素，如果被选元素隐藏，则显示这些元素。
							 */
							$(".provinceCityAll").css("top", t2 + h2 + 5).css(
									"left", l2).toggle();
							$(".provinceCityAll .tabs li").css("width",w/3+1);
							$(".provinceCityAll .tabs li a").css("width",w/3);
							$(".provinceCityAll").click(function(event) {
								event.stopPropagation();
							});
							event.stopPropagation();
							$("html").click(function() {
								$(".provinceCityAll").hide();
							});
							// 做什么用
							$("input.proCitySelAll").removeClass("current2");
							$(this).addClass("current2");

							$(".provinceCityAll").find(".tabs").find("a")
									.removeClass("current");// 移除所有tabs的样式

							// 给省tabs添加样式
							$(".provinceCityAll").find(".tabs").find(
									"a[tb=provinceAll]").addClass("current");

							$(".provinceCityAll").find(".con").children()
									.hide();
							$(".provinceCityAll").find(".con").find(
									".provinceAll").show();

							/*if ($("body").data("allProvinces") == null) {
								sendAllProvinceAjax();
							}
							if ($("body").data("allCountys") == null) {
								sendAllCountiesAjax();
							}*/

							// 绑定tab点击事件
							$(".provinceCityAll")
									.find(".tabs")
									.find("a")
									.click(
											function() {
												// 如果上一级没有选择，不做处理
												if ($(this).attr("tb") == "cityAll"
														&& $(
																".provinceAll .list .current")
																.val() == null) {
													return;
												}
												;
												if ($(this).attr("tb") == "countyAll"
														&& $(
																".cityAll .list .current")
																.val() == null) {
													return;
												}
												;
												if ($(this).attr("tb") == "streetAll"
														&& $(
																".countyAll .list .current")
																.val() == null) {
													return;
												}

												$(".provinceCityAll").find(
														".tabs").find("a")
														.removeClass("current");
												//设置点击的tab样式		
												$(this).addClass("current");
												var tb = $(this).attr("tb");
												$(".provinceCityAll").find(
														".con").children()
														.hide();
												$(".provinceCityAll").find(
														".con").find("." + tb)
														.show();
											});
						});
	}



	var allProvinces = null;
	var allCities = null;
	var allAreas = null;
	var allProId = null;
	var cityIdAll = null;
	var provinceAllTotalPage = null;
	var pa_pageSize = 12;
	var pa_currentPage = 1;

	function binds() {
		
		$(".provinceAll .pre a").bind('click', function() {
			var provincePage1 = parseInt($('#provincePage1').html());
			if (provincePage1 == 1) {
				return;
			}
			viewAllProvince(provincePage1 - 1);
		});
		$(".cityAll .pre a").bind('click', function() {
			var cityPages1 = parseInt($('#cityPage1').html());
			if (cityPages1 == 1) {
				return;
			}
			allCityPage(cityPages1 - 1);
		});
		$(".countyAll .pre a").bind('click', function() {
			var countyPages = parseInt($('#countyPage1').html());
			if (countyPages == 1) {
				return;
			}
			allCountyPage(countyPages - 1);
		});
		$(".provinceAll .next a").bind('click', function() {
			var provincePage1 = parseInt($('#provincePage1').html());
			provinceAllTotalPage = countAllProvincePages();
			if (provincePage1 >= provinceAllTotalPage) {
				return;
			}
			viewAllProvince(provincePage1 + 1);
		});
		$(".cityAll .next a").bind('click', function() {
			if ($(this).hasClass("can")) {
				var cityPages1 = parseInt($('#cityPage1').html());
				allCityPage(cityPages1 + 1);
			}
		});
		$(".countyAll .next a").bind('click', function() {
			if ($(this).hasClass("can")) {
				var countyPages = parseInt($('#countyPage1').html());
				allCountyPage(countyPages + 1);
			}
		});
	}

	function countProvincePages() {
		provinceTotalPage = Math.ceil(provinces.length / p_pageSize);
		return provinceTotalPage;
	}
	function countAllProvincePages() {
		provinceAllTotalPage = Math.ceil(allProvinces.length / pa_pageSize);
		return provinceAllTotalPage;
	}
	function viewAllProvince(page) {
		//显示省
		$(".provinceAll .list ul li").remove();
		if (page == 1) {
			$(".provinceAll .pre a").removeClass("can");
			$(".provinceAll .next a").addClass("can");
		} else {
			$(".provinceAll .pre a").addClass("can");
			$(".provinceAll .next a").addClass("can");
		}
		var end;
		var start;
		if (page == provinceAllTotalPage) {
			start = (page - 1) * pa_pageSize;
			end = allProvinces.length;
			$(".provinceAll .next a").removeClass("can");
		} else {
			start = (page - 1) * pa_pageSize;
			end = start + pa_pageSize;
		}
		for ( var i = start; i < end; i++) {
			var p_id = allProvinces[i].buildingId;
			var p_name = allProvinces[i].buildingName;
			
			// style="background: none repeat scroll 0% 0% transparent; border:
			// 0px none;"
			var li = $('<li><a  href="javascript:" data_i="' + i + '" id="'
					+ p_id + '">' + p_name + '</a></li>');
			// onclick=viewAllCities(' + i + ');
			// $(".provinceAll .list ul li
			// a").bind("click",{"id":$(this).attr('data_i')},viewAllCities);

			$(".provinceAll .list ul").append(li);
		}
		$(".provinceAll .list ul li a").bind("click", viewAllCities);//绑定选择省事件
		$(".provinceAll .list #provincePage1").remove();
		$(".provinceAll .list").append(
				"<label id='provincePage1' style='display:none;'>" + page
						+ "</label>");
	}
	function viewAllCities(i) {
		//选择省时显示城市，记录选择的省
		allProId = allProvinces[$(this).attr("data_i")].buildingId;
		
		if (allProId != $(".proCitySelAll").data("buildingId")){
			//选了其它省，需要清空下级值
			$(".proCitySelAll").data("unitId","");
			$(".proCitySelAll").data("unitName","");
			$(".proCitySelAll").data("roomId","");
			$(".proCitySelAll").data("roomNbr","");
		}
		$(".proCitySelAll").data("buildingId", allProId);
		$(".proCitySelAll").data("buildingName",
				allProvinces[$(this).attr("data_i")].buildingName);
		$("input.current2").removeClass("iGrays");
		//选择的省的名称显示在输入框中
		$("input.current2").val(allProvinces[$(this).attr("data_i")].buildingName);
		/*allCitys = [];
		var j = 0;
		$.each(allCities, function(i, city) {
			if (city.provinceId == allProId) {
				allCitys[j++] = city;
			}
		});*/
		allCitys = allProvinces[$(this).attr("data_i")].unit;
		allCityTotalPage = Math.ceil(allCitys.length / pa_pageSize);
		
		$(".provinceCityAll").find(".tabs").find("a").removeClass("current");
		$(".provinceCityAll .tabs").find("#cityAll").addClass("current");
		$(".con .provinceAll .list a").removeClass("current");
		$(".con .provinceAll .list a[id='" + allProId + "']").addClass(
				"current");
		$(".provinceCityAll").find(".con").children().hide();
		$(".provinceCityAll").find(".con").find(".cityAll").show();
		//var pAllName = $(".proCitySelAll").data("buildingName");
		
		allCityPage(1);//显示城市第一页
	}
	function allCityPage(page) {
		$(".cityAll .list ul li").empty();
		$(".cityAll .list ul li").remove();
		if (page == 1) {
			$(".cityAll .pre a").removeClass("can");
		} else {
			$(".cityAll .pre a").addClass("can");
		}
		var start;
		var end;
		if (page <= 1) {
			page = 1;
			$(".cityAll .pre a").removeClass("can");
			$(".cityAll .next a").addClass("can");
		}
		if (allCityTotalPage == 1) {
			$(".cityAll .next a").removeClass("can");
			$(".cityAll .pre a").removeClass("can");
		}
		if (page >= allCityTotalPage) {
			page = allCityTotalPage;
			$(".cityAll .next a").removeClass("can");
			start = (page - 1) * pa_pageSize;
			end = allCitys.length;
		} else if (page == 1) {
			start = (page - 1) * pa_pageSize;
			end = start + pa_pageSize;
			$(".cityAll .pre a").removeClass("can");
			$(".cityAll .next a").addClass("can");
		} else {
			start = (page - 1) * pa_pageSize;
			end = start + pa_pageSize;
			$(".cityAll .next a").addClass("can");
			$(".cityAll .pre a").addClass("can");
		}
		for ( var i = start; i < end; i++) {
			var c_id = allCitys[i].buildingId;
			var cityName = allCitys[i].buildingName;
			var li = $('<li><a href="javascript:" data_i="' + i + '" id="'
					+ c_id + '">' + cityName + '</a></li>');
			// $(".cityAll .list ul li
			// a").bind("click",{"id":i},viewAllCounties);
			$(".cityAll .list ul").append(li);
		}
		$(".cityAll .list ul li a").bind("click", viewAllCounties);
		$(".cityAll .list #cityPage1").remove();
		$(".cityAll .list").append(
				"<label id='cityPage1' style='display:none;'>" + page
						+ "</label>");
	}
	function viewAllCounties(i) {
		//选择城市时显示区，记录选择的城市
		cityIdAll = allCitys[$(this).attr("data_i")].buildingId;
		$(".proCitySelAll").data("unitId", cityIdAll);
		var cityname = $.trim(allCitys[$(this).attr("data_i")].buildingName);
		$(".proCitySelAll").data("unitName", cityname);
		
		if (cityIdAll != $(".proCitySelAll").data("unitId")){
			//选了其它市，需要清空下级值
			$(".proCitySelAll").data("roomId","");
			$(".proCitySelAll").data("roomNbr","");
		}
		
		var nameOfProvince = $(".proCitySelAll").data("buildingName");
		//var cityCurrentName = $(".provinceCityAll").data("unitName");
		var province_id = $(".proCitySelAll").data("buildingId");
		//var city_id = $(".provinceCityAll").data("unitId");
		var rtn = "";
		if (cityIdAll == province_id){
			rtn = nameOfProvince;
		}else{
			rtn = nameOfProvince + "-" + cityname;
		}
		
		$("input.current2").removeClass("iGrays");
		$("input.current2").val(rtn);
		/*countiesAll = [];
		var j = 0;
		$.each(allAreas, function(i, countys) {
			if (countys.cityId == cityIdAll) {
				countiesAll[j++] = countys;
			}
		});*/
		countiesAll = allCitys[$(this).attr("data_i")].room;
		countyTotalPageAll = Math.ceil(countiesAll.length / pa_pageSize);
		$(".provinceCityAll").find(".tabs").find("a").removeClass("current");
		$(".provinceCityAll .tabs").find("#countyAll").addClass("current");
		$(".con .cityAll .list a").removeClass("current");
		$(".con .cityAll .list a[id='" + cityIdAll + "']").addClass("current");
		$(".provinceCityAll").find(".con").children().hide();
		$(".provinceCityAll").find(".con").find(".countyAll").show();
		allCountyPage(1);//显示区的第一页
	}
	function allCountyPage(page) {
		
		$(".countyAll .list ul li").remove();
		if (page == 1) {
			$(".countyAll .pre a").removeClass("can");
		} else {
			$(".countyAll .pre a").addClass("can");
		}
		var start;
		var end;
		if (page <= 1) {
			page = 1;
			$(".countyAll .pre a").removeClass("can");
			$(".countyAll .next a").addClass("can");
		}
		if (countyTotalPageAll == 1) {
			$(".countyAll .next a").removeClass("can");
			$(".countyAll .pre a").removeClass("can");
		}
		if (page >= countyTotalPageAll) {
			page = countyTotalPageAll;
			$(".countyAll .next a").removeClass("can");
			start = (page - 1) * pa_pageSize;
			end = countiesAll.length;
		} else if (page == 1) {
			start = (page - 1) * pa_pageSize;
			end = start + pa_pageSize;
			$(".countyAll .pre a").removeClass("can");
			$(".countyAll .next a").addClass("can");
		} else {
			start = (page - 1) * pa_pageSize;
			end = start + pa_pageSize;
			$(".countyAll .next a").addClass("can");
			$(".countyAll .pre a").addClass("can");
		}
		for ( var i = start; i < end; i++) {
			var c_id = countiesAll[i].roomId;
			var countyName = countiesAll[i].roomNbr;
			;
			var li = $('<li><a href="javascript:" data_i="' + i + '" id="'
					+ c_id + '">' + countyName + '</a></li>');
			/*
			 * $(".countyAll .list ul li
			 * a").bind("click",{"id":i},addrInputAll);
			 */
			$(".countyAll .list ul").append(li);
		}
		$(".countyAll .list ul li a").bind("click", addrInputAll);
		$(".countyAll .list #countyPage1").remove();
		$(".countyAll .list").append(
				"<label id='countyPage1' style='display:none;'>" + page
						+ "</label>");
	}
	function addrInputAll(i) {
		var countyId = $.trim(countiesAll[$(this).attr("data_i")].roomId);
		var countyName = $.trim(countiesAll[$(this).attr("data_i")].roomNbr);
		 $(".proCitySelAll").data("roomId",countyId);
		 $(".proCitySelAll").data("roomNbr",countyName);
		/*$(".con .hotCityAll .list a input").removeClass("current");
		$(".con .hotCityAll .list a input[id='" + cityIdAll + "']").addClass(
				"current");*/
		$(".con .countyAll .list a").removeClass("current");
		$(".con .countyAll .list a[id='" + countyId + "']").addClass("current");
		
		var nameOfProvince = $(".proCitySelAll").data("buildingName");
		var cityCurrentName = $(".proCitySelAll").data("unitName");
		var province_id = $(".proCitySelAll").data("buildingId");
		var city_id = $(".proCitySelAll").data("unitId");
		var rtn = "";
		if (city_id == province_id){
			rtn = nameOfProvince +  "-" + countyName
		}else{
			rtn = nameOfProvince + "-" + cityCurrentName + "-" + countyName
		}
		var nameValue = $("input.current2");
		nameValue.removeClass("iGrays");
		$(".provinceCityAll").hide();
		
		$("input.current2").val(rtn);
		$(".backifname").hide();
		var nameValue = $("input.current2").attr("name");
		if (nameValue == "consignor.addrProCity") {
			$("#provinceId").val(allProId);
			$("#cityId").val(cityIdAll);
		}
		if (nameValue == "order.caddrProCity") {
			$("input[name='order.caddrProCity']").trigger("change");
		}
		if (nameValue == "consigneeInfo.addrProCity") {
			$("input[name='consigneeInfo.addrProCity']").trigger("change");
		}
		if (nameValue == 'template.caddrProCity') {
			$("input[name='template.caddrProCity']").trigger("change");
		}
	}

	function wrongMsg(object, msg) {
		$(".wrongMsg").text(msg);
		object.addClass("wrong");
	}

	function getCommunityData(communtiyId) {
		$.ajax({
			  type: 'POST',
			  url: "communityAction!getCommunity.action",
			  data: {
					"inParma" : JSON.stringify({
						"communityId" : communtiyId,
						"qryType" : "building_room_simple"})
					},
					//async:false,
			  success: function(data) {
					var ret = eval("(" + data + ")");
					if (ret.result == "true") {
						//return data.data;
						$("body").data("allProvinces", ret.data);
						allProvinces = ret.data;
						provinceAllTotalPage = countAllProvincePages();
						viewAllProvince(1);//显示第一页
					} else {
						alert(data.msg);
					}
				}});
		/*$.post("communityAction!getCommunity.action", {
			"inParma" : JSON.stringify({
				"communityId" : communtiyId,
				"qryType" : "building_room_simple"
			})
		}, function(data) {
			var ret = eval("(" + data + ")");
			if (ret.result == "true") {
				//return data.data;
				$("body").data("allProvinces", ret.data);
				allProvinces = ret.data;
				provinceAllTotalPage = countAllProvincePages();
			} else {
				alert(data.msg);
			}
		});*/
	}

})(jQuery);