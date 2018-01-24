var Send = {
	Post : function(url, data) {
		{
			return new Promise(function(resolve, inject) {
				$.ajax({
					// 提交数据的类型 POST GET
					type : "POST",
					// 提交的网址
					url : url,
					// 提交的数据
					data : data,
					// 返回数据的格式
					datatype : "json",
					// 在请求之前调用的函数
					beforeSend : function() {
						console.log("[x] Send:" + JSON.stringify(data));
					},
					// 成功返回之后调用的函数
					success : function(data) {
						Tools.toast(data.msg);
						if (data.code == "999999") {
							location.href = "view/login";
						}
						resolve(data);
					},
					// 调用执行后调用的函数
					complete : function(XMLHttpRequest, textStatus) {
						console
								.log("[x] Receive"
										+ XMLHttpRequest.responseText);
					},
					// 调用出错执行的函数
					error : function() {
						Tools.toast("请求错误，请联系管理员处理。");
						// 请求出错处理
					}
				});
			})
		}
	}
};
var Tools = {
	checkNotEmpty : function(e) {
		if (e == null || e == 'undefined' || e == "") {
			return false;
		}
		return true;
	},
	/**
	 *  * 和PHP一样的时间戳格式化函数  * date('Y-m-d','1350052653');//很方便的将时间戳转换成了2012-10-11
	 * date('Y-m-d H:i:s','1350052653');//得到的结果是2012-10-12 22:37:33
	 * 
	 * @param {string}
	 *            format 格式  *
	 * @param {int}
	 *            timestamp 要格式化的时间 默认为当前时间  *
	 * @return {string}   格式化的时间字符串  
	 */
	formatTimestampToDate : function(timestamp) {// 比如需要这样的格式 yyyy-MM-dd
		// hh:mm:ss
		var date = new Date(timestamp);
		Y = date.getFullYear() + '-';
		M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date
				.getMonth() + 1)
				+ '-';
		D = date.getDate() + ' ';
		h = date.getHours() + ':';
		m = date.getMinutes() + ':';
		s = date.getSeconds();
		// console.log(Y+M+D+h+m+s); //呀麻碟
		// 输出结果：2014-04-23 18:55:49
		return Y + M + D + h + m + s;
	},
	getUrlParam : function(name) {
		var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); // 构造一个含有目标参数的正则表达式对象
		var r = window.location.search.substr(1).match(reg); // 匹配目标参数
		if (r != null)
			return unescape(r[2]);
		return null; // 返回参数值
	},
	toast : function(message) {
		bootoast({
			message : message,
			position : 'right-bottom',
			timeout : 2
		});
	}
};
function MultiSelect() {
	var _this = this;
	this.data = new Array();
	this.setData = function(data) {
		this.data = data;
	}
	this.getData = function() {
		return this.data;
	}
	this.initByUrl = function(id, url, data) {
		function fun(data) {
			return new Promise(function(resolve, reject) {
				_this.data = new Array();
				if (data.code == '000000') {
					$.each(data.data.rows, function(k, v) {
						var obj1 = new Object();
						obj1.label = v.name;
						obj1.value = v.id;
						_this.data.push(obj1);
					});
				}
				$(id).multiselect({
					nonSelectedText : '请选择',
					filterPlaceholder : '搜索',
					buttonWidth : '220px', // button宽度
					dropRight : true,// 超出时横向滚动条初始在右边
					includeSelectAllOption : true,
					selectAllText : '选中全部',
					selectAllNumber : false,// 全部选中时不显示后面的数字
					enableFiltering : true,// 启用过滤
					disableIfEmpty : true,// 无选项时禁用
					maxHeight : 200,
					dropUp : true,// 超出时纵向滚动条初始在上方
				});
				$(id).multiselect("dataprovider", _this.data);
				resolve("完成");
			});
		}
		return Send.Post(url, data).then(fun);
	};
	this.initByData = function(id) {
		$(id).multiselect({
			nonSelectedText : '请选择',
			filterPlaceholder : '搜索',
			buttonWidth : '220px', // button宽度
			dropRight : true,// 超出时横向滚动条初始在右边
			includeSelectAllOption : true,
			selectAllText : '选中全部',
			selectAllNumber : false,// 全部选中时不显示后面的数字
			enableFiltering : true,// 启用过滤
			disableIfEmpty : true,// 无选项时禁用
			maxHeight : 200,
			dropUp : true,// 超出时纵向滚动条初始在上方
		});
		console.log("obj", _this);
		console.log("obj.data", _this.data);
		$(id).multiselect("dataprovider", _this.data);
	};
	this.getValues = function(id) {
		var selected = [];
		$(id + ' option:selected').each(function() {
			selected.push($(this).val());
		});
		return selected;
	};
	return this;
}
/**
 * 附件列表对象
 * 
 */
var Attachment = function(id) {
	var _this = this;
	$(id).on("click", "li > a > p > span.glyphicon.glyphicon-remove",
			function(e) {
				var key = $(e.currentTarget).parent().attr("key");
				var type = $(e.currentTarget).parent().attr("type");
				_this.removeElement($(e.currentTarget), key, type);
			});
	$(id).on("click", "li > a > p > span:nth-child(2)", function(e) {
		var key = $(e.currentTarget).parent().attr("key");
		var type = $(e.currentTarget).parent().attr("type");
		var text = $(e.currentTarget).html();
		_this.clickElement($(e.currentTarget), key, type, text);
	});
	this.removeElement = function(e, key, type) {
		$(e).parent().parent().parent().remove();
	};
	this.clickElement = function(e, key, type, text) {
		window.open("download?key=" + key + "." + type + "&text=" + text);
	};
	this.getValues = function() {
		var file = new Array();
		$(id + " li a p").each(function(k, v) {
			var obj = new Object();
			obj.name = $(v).children("span[name=text]").first().html();
			obj.type = $(v).attr("type");
			obj.key = $(v).attr("key");
			file.push(obj);
		});
		return file;
	}
	this.obj = $(id);
	this.append = function(key, type, text) {
		$(id)
				.append(
						"<li role=\"presentation\">"
								+ "<a role=\"menuitem\" tabindex=\"-1\""
								+ "href=\"javascript:void(0);\" style=\"padding-left:0px;\">"
								+ "<p style=\"margin-bottom:0px;font-size: 17px\" key=\""
								+ key
								+ "\" type=\""
								+ type
								+ "\"><span  name=\"remove\" class=\"glyphicon glyphicon-remove\" style=\"color: rgb(212, 106, 64);padding-left:10px;padding-right:10px;\"></span>"
								+ "<span name=\"text\">" + text + "</span></p>"
								+ "</span></a></li>");
	};
	this.removeAll = function() {
		console.log("removeAll");
		this.obj.html("");
	};
}
/** 初始化项目列表 */
function initBootstrapTable(id, url, columns) {
	$(id).bootstrapTable({
		url : url,
		columns : columns,
		sidePagination : "server", // 设置在哪里进行分页，可选值为 'client'
		// 或者 'server'。设置
		// 'server'时，必须设置
		// 服务器数据地址（url）或者重写ajax方法
		search : true, // 是否启用查询
		showColumns : true, // 显示下拉框勾选要显示的列
		showRefresh : true, // 显示刷新按钮
		pageList : [ 5, 10, 15, 20, 25 ], // 记录数可选列表
		striped : true, // 表格显示条纹
		pagination : true, // 启动分页
		pageSize : 10, // 每页显示的记录数
		pageNumber : 1, // 当前第几页
		responseHandler : function(data) {
			if (data.code != "000000") {
				alert(data.msg);
				return {
					"total" : 0,// 总页数
					"rows" : null
				// 数据
				};
			}
			return {
				"total" : data.data.total,// 总页数
				"rows" : data.data.rows
			// 数据
			};
		}
	});
}