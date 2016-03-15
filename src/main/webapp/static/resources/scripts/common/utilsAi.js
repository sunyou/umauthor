(function ($) {
	"use strict";
	// 对Date的扩展，将 Date 转化为指定格式的String
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	// 例子： 
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 

	if (typeof window.console === "undefined" || typeof window.console.log === "undefined") {
		window.console = {};
		if (window.alertFallback) {
			window.console.log = function (msg) {
				alert(msg);
			};
		} else {
			window.console.log = function () {
			};
		}
	}

	Date.prototype.format = function (fmt) { //author: meizz 
		var o = {
			"M+": this.getMonth() + 1, //月份
			"d+": this.getDate(), //日
			"h+": this.getHours(), //小时
			"m+": this.getMinutes(), //分
			"s+": this.getSeconds(), //秒
			"q+": Math.floor((this.getMonth() + 3) / 3), //季度
			"S": this.getMilliseconds() //毫秒
		};
		if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		for (var k in o)
			if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		return fmt;
	};

	String.prototype.capitalize = function () {
		return this.charAt(0).toUpperCase() + this.slice(1);
	};

	String.prototype.trim = function () {
		return this.replace(/^\s+|\s+$/g, '');
	};

	String.prototype.ltrim = function () {
		return this.replace(/^\s+/, '');
	};

	String.prototype.rtrim = function () {
		return this.replace(/\s+$/, '');
	};

	String.prototype.fulltrim = function () {
		return this.replace(/(?:(?:^|\n)\s+|\s+(?:$|\n))/g, '').replace(/\s+/g, ' ');
	};

	//modal 弹出居中 start
	var modalVerticalCenterClass = ".modal";

	function centerModals($element) {
		var $modals;
		if ($element.length) {
			$modals = $element;
		} else {
			$modals = $(modalVerticalCenterClass + ':visible');
		}
		$modals.each(function (i) {
			var $clone = $(this).clone().css('display', 'block').appendTo('body');
			var top = Math.round(($clone.height() - $clone.find('.modal-content').height()) / 2);
			top = top > 0 ? top : 0;
			$clone.remove();
			$(this).find('.modal-content').css("margin-top", top);
		});
	}

	$(modalVerticalCenterClass).on('show.bs.modal', function (e) {
		centerModals($(this));
	});
	$(window).on('resize', centerModals);
	//modal 弹出居中 end

	//Handlebars定义方法 start
	/*window.Handlebars.registerHelper('select', function (value, options) {
		var $el = $('<select />').html(options.fn(this));
		$el.find('[value="' + value + '"]').attr({'selected': 'selected'});
		return $el.html();
	});

	window.Handlebars.registerHelper("setChecked", function (value, currentValue, options) {
		if (currentValue instanceof Array) {
			//console.log(options);
			var dataId = options.hash.id || 'id';
			//console.log(dataId)
			for (var i = 0; i < currentValue.length; i++) {
				//console.log('v:' + value + 'c:' + currentValue[i][dataId])
				if (value == currentValue[i][dataId]) {
					return "checked";
				}
			}
			return "";
		} else {
			if (value == currentValue) {
				return "checked";
			} else {
				return "";
			}
		}

	});

	window.Handlebars.registerHelper('if_eq', function (a, b, opts) {
		if (a == b) {
			return opts.fn(this);
		} else {
			return opts.inverse(this);
		}
	});

	window.Handlebars.registerHelper('compare', function (lvalue, rvalue, options) {

		if (arguments.length < 3)
			throw new Error("Handlerbars Helper 'compare' needs 2 parameters");

		var operator = options.hash.operator || "==";

		var operators = {
			'==': function (l, r) {
				return l == r;
			},
			'===': function (l, r) {
				return l === r;
			},
			'!=': function (l, r) {
				return l != r;
			},
			'<': function (l, r) {
				return l < r;
			},
			'>': function (l, r) {
				return l > r;
			},
			'<=': function (l, r) {
				return l <= r;
			},
			'>=': function (l, r) {
				return l >= r;
			},
			'typeof': function (l, r) {
				return typeof l == r;
			}
		};

		if (!operators[operator])
			throw new Error("Handlerbars Helper 'compare' doesn't know the operator " + operator);

		var result = operators[operator](lvalue, rvalue);

		if (result) {
			return options.fn(this);
		} else {
			return options.inverse(this);
		}

	});*/

	//Handlebars定义方法 end


	//jquery 验证 start

	function isDate6(sDate) {
		if (!/^[0-9]{6}$/.test(sDate)) {
			return false;
		}
		var year, month, day;
		year = sDate.substring(0, 4);
		month = sDate.substring(4, 6);
		if (year < 1700 || year > 2500) return false
		if (month < 1 || month > 12) return false
		return true
	}

	function isDate8(sDate) {
		if (!/^[0-9]{8}$/.test(sDate)) {
			return false;
		}
		var year, month, day;
		year = sDate.substring(0, 4);
		month = sDate.substring(4, 6);
		day = sDate.substring(6, 8);
		var iaMonthDays = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
		if (year < 1700 || year > 2500) return false
		if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1] = 29;
		if (month < 1 || month > 12) return false
		if (day < 1 || day > iaMonthDays[month - 1]) return false
		return true
	}

	function isIdCardNo(num) {
		var factorArr = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1);
		var parityBit = new Array("1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2");
		var varArray = new Array();
		var intValue;
		var lngProduct = 0;
		var intCheckDigit;
		var intStrLen = num.length;
		var idNumber = num;
		// initialize
		if ((intStrLen != 15) && (intStrLen != 18)) {
			return false;
		}
		// check and set value
		for (var i = 0; i < intStrLen; i++) {
			varArray[i] = idNumber.charAt(i);
			if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {
				return false;
			} else if (i < 17) {
				varArray[i] = varArray[i] * factorArr[i];
			}
		}
		if (intStrLen == 18) {
			//check date
			var date8 = idNumber.substring(6, 14);
			if (isDate8(date8) == false) {
				return false;
			}
			// calculate the sum of the products
			for (var i = 0; i < 17; i++) {
				lngProduct = lngProduct + varArray[i];
			}
			// calculate the check digit
			intCheckDigit = parityBit[lngProduct % 11];
			// check last digit
			if (varArray[17] != intCheckDigit) {
				return false;
			}
		}
		else {        //length is 15
			//check date
			var date6 = idNumber.substring(6, 12);
			if (isDate6(date6) == false) {
				return false;
			}
		}
		return true;
	}

	// 字符验证       
	jQuery.validator.addMethod("stringCheck", function (value, element) {
		return this.optional(element) || /^[\u0391-\uFFE5\w]+$/.test(value);
	}, "只能包括中文字、英文字母、数字和下划线");

	// 中文字两个字节       
	jQuery.validator.addMethod("byteRangeLength", function (value, element, param) {
		var length = value.length;
		for (var i = 0; i < value.length; i++) {
			if (value.charCodeAt(i) > 127) {
				length++;
			}
		}
		return this.optional(element) || ( length >= param[0] && length <= param[1] );
	}, "请确保输入的值在3-15个字节之间(一个中文字算2个字节)");

	// 身份证号码验证       
	jQuery.validator.addMethod("isIdCardNo", function (value, element) {
		return this.optional(element) || isIdCardNo(value);
	}, "请正确输入您的身份证号码");

	// 手机号码验证       
	jQuery.validator.addMethod("isMobile", function (value, element) {
		var length = value.length;
		var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		return this.optional(element) || (length == 11 && mobile.test(value));
	}, "请正确填写您的手机号码");

	// 电话号码验证       
	jQuery.validator.addMethod("isTel", function (value, element) {
		var tel = /^\d{3,4}-?\d{7,9}$/;    //电话号码格式010-12345678
		return this.optional(element) || (tel.test(value));
	}, "请正确填写您的电话号码");

	// 联系电话(手机/电话皆可)验证   
	jQuery.validator.addMethod("isPhone", function (value, element) {
		var length = value.length;
		var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
		var tel = /^\d{3,4}-?\d{7,9}$/;
		return this.optional(element) || (tel.test(value) || mobile.test(value));
	}, "请正确填写您的联系电话");

	// 邮政编码验证
	jQuery.validator.addMethod("isZipCode", function (value, element) {
		var tel = /^[0-9]{6}$/;
		return this.optional(element) || (tel.test(value));
	}, "请正确填写您的邮政编码");
	//jquery 验证 end

	//按钮模板
	var sourceBut = $("#table-edit-button-template").html();
	var templateBut = '';
	if (sourceBut) {
		templateBut = Handlebars.compile(sourceBut);
	}

	function getRootPath() {
		//GLOBAL.WEBROOT -> css.jsp
		return GLOBAL.WEBROOT;
	}

	//select ajax start
	function clearData(id, emptyValue) {
		if (id) {
			var $el = $('#' + id);
			//var first = $el.find('option:first');
			var first = $el.find('option[value="' + emptyValue + '"]');
//			console.log(first)
			$el.empty();
			$el.append(first);

			var targetId = $el.data('target-id');
			clearData(targetId, emptyValue);
		}
	}

	function selectAjax() {
		$('body').on('change', '[data-ajax-url]', function (e) {
			var urlStr = $(this).data('ajax-url');
			var parentIdName = $(this).data('parent-id') || 'parentId';

			var type = $(this).data('type');
			var parentIdVal = $(this).val();

			var targetId = $(this).data('target-id');
			var resultId = $(this).data('result-id') || 'id';
			var resultValue = $(this).data('result-value') || 'value';
			var emptyValue = $(this).data('empty-value') || '';

			var urlData = parentIdName + "=" + parentIdVal;
			if (type) {
				urlData += "&type=" + type;
			}

			var urlDataName = $(this).data('url-data-name');
			if (urlDataName) {
				var names = urlDataName.split(',');
				for (var i = 0; i < names.length; i++) {
					urlData += '&' + names[i] + "=" + ($(this).data(names[i]) || '');
				}
			}


			//清空
			clearData(targetId, emptyValue);
			
			utilsAi.ajax({
				url: urlStr
				, type: 'GET'
				, data: urlData
				, success: function (result) {
					//console.log(result);
					var data = result.data;
					var $el = $('#' + targetId);
//						var first = $el.find('option:first');
					var first = $el.find('option[value="' + emptyValue + '"]');

					$el.empty();
					$el.append(first);
					for (var i = 0; i < data.length; i++) {
						$el.append('<option value="' + data[i][resultId] + '">' + data[i][resultValue] + '</option>');
					}
				}
			});
			

		});
	}

	//select ajax end
	
	//密码检测密码强度
	//	* 强度规则
	//	* 1) 任何在1-6之间的字符的组合，弱；例如： win
	//	* 2) 任何字符数的两类字符组合，中； 例如： win123
	//	* 3) 12位字符数以下的三类或四类字符组合，强；  例如： win123abcABC
	//	* 4) 12位字符数以上的三类或四类字符组合，非常好。 例如：win123abcABC!
	function checkStrong(sValue) {
		var modes = 0;
		//正则表达式验证符合要求的
		if (sValue.length < 1) return modes;
		if (/\d/.test(sValue)) modes++; //数字
		if (/[a-z]/.test(sValue)) modes++; //小写
		if (/[A-Z]/.test(sValue)) modes++; //大写  
		if (/\W/.test(sValue)) modes++; //特殊字符
		
		//逻辑处理
		switch (modes) {
			case 1:
				return 1;
				break;
			case 2:
				return 2;
			case 3:
			case 4:
				return sValue.length < 12 ? 3 : 4
				break;
		}
	}

	//自定义工具类
	window.utilsAi = {
		ctx: getRootPath(),
		checkPswStrong:function(pswId,showInfoId){
			var $bar = $('#'+showInfoId+' .progress-bar');
			var $info = $bar.parent().next();
			$info.hide();
			$("#"+pswId).keyup(function () {
				var index = checkStrong($(this).val());
//				console.log(index);
				//	1 弱；例如： win
				//	2 中； 例如： win123
				//	3 强；  例如： win123abcABC
				//	4 非常好。 例如：win123abcABC!
				$bar.removeClass("progress-bar-danger progress-bar-warning progress-bar-success");
				$bar.css({width:'0%'});
				$bar.html('');
				$info.hide();
				if(index==1){
					$bar.addClass("progress-bar-danger");
					$bar.css({width:'30%'});
					$bar.html('弱');
					$info.show();
				}else if(index==2){
					$bar.addClass("progress-bar-warning");
					$bar.css({width:'60%'});
					$bar.html('中');
				}else if(index==3){
					$bar.css({width:'80%'});
					$bar.html('强');
				}else if(index==4){
					$bar.addClass("progress-bar-success");
					$bar.css({width:'95%'});
					$bar.html('非常好');
				}
			});
		},
		sendSmsBut:function(btnId,time){
			var $btn = $('#'+btnId);
			var text = $btn.html();
			$btn.prop('disabled', true);
			time = time || 59;
			$btn.html('等待'+time+'秒');
			var callback = function(){
				if(time==1){
					$btn.prop('disabled', false);
					$btn.html(text);
				}else{
					time -=1; 
					$btn.html('等待'+time+'秒');
					setTimeout(callback,1000);
				}
				
			}
			setTimeout(callback,1000);
			
		},
		formArrayUtil: function (array, num) {
			//使用jquery的serializeArray 将表单序列化为数组  再次转化为对象属性一共num个的数组对象 
			num = num || array.length;
			var nArr = [];
			var otemp = {};
			for (var i = 0; i < array.length; i++) {

				if (i !== 0 && i % num === 0) {
					if ($.isEmptyObject(otemp)) {
						continue;
					}
					nArr.push(otemp);
					otemp = {};
				}
				if ($.trim(array[i].value) !== "") {
					var value = otemp[array[i].name];
					if (value) {
						if (value instanceof Array) {
							value.push(array[i].value);
						} else {
							var arrTemp = [];
							arrTemp.push(value);
							arrTemp.push(array[i].value);
							otemp[array[i].name] = arrTemp;
						}
					} else {
						otemp[array[i].name] = array[i].value;
					}

				}
				if (i + 1 === array.length && $.isEmptyObject(otemp) === false) {
					nArr.push(otemp);
				}

			}
			if (nArr.length === 0) {
				return [];
			}
			return nArr;
		},
		code: {
			SUCCESS: '100',
			ERROR: '200',
			LOGIN: '300'
		},
		alert: function (message) {
			$.gritter.add({
				title: '提示',
				text: message,
				class_name: 'gritter-info gritter-center' + ' gritter-light'
			});
			this.hideErrorFn();
		},
		hideError: null,
		hideErrorFn: function () {
			var obj = this;

			if (obj.hideError != null) {
				clearTimeout(obj.hideError);
			}
			obj.hideError = setTimeout(function () {
				$.gritter.removeAll();
				obj.hideError = null;
			}, 2500);
		},
		showError: function (title, message) {
			//gritter-center
			$.gritter.add({
				title: title,
				text: message,
				class_name: 'gritter-error gritter-center' + ' gritter-light'
			});
			this.hideErrorFn();
		},
		confirm: function (op) {
			op = op || {};
			var defaults = {
				contentId: 'confirm-content',
				confirmId: 'dialog-confirm',
				title: '警告',
				content: '',
				callback: function () {
				}
			};

			op = $.extend(true, {}, defaults, op);

			$('#' + op.contentId).html(op.content);
			$("#" + op.confirmId).removeClass('hide').dialog({
				resizable: false,
				modal: true,
				title: op.title,
				titleHtml: true,
				buttons: [{
					html: "<i class='icon-trash bigger-110'></i>&nbsp; 确定删除",
					"class": "btn btn-danger btn-xs",
					click: function () {
						op.callback();
						$(this).dialog("close");
					}
				},
					{
						html: "<i class='icon-remove bigger-110'></i>&nbsp; 取消",
						"class": "btn btn-xs",
						click: function () {
							$(this).dialog("close");
						}
					}]
			});
		},
		emptyFn: function () {
		},
		ajax: function (op) {
			op.url = this.ctx + op.url;

			var defaults = {
				"type": "POST"
//				,"dataType":"json"
			};
			var obj = this;
			var result = $.extend(true, {}, defaults, op);
			var success = result.success;
			result.success = function (data, textStatus, jqXHR) {
				//console.log(data);
//				console.log(data instanceof Object);
				if (data instanceof Object) {
					if (data.code == obj.code.SUCCESS) {
						//成功
						success(data, textStatus, jqXHR);
					} else if (data.code == obj.code.ERROR) {
						//出错
						obj.showError('请求出错', data.message);
					} else if (data.code == obj.code.LOGIN) {
						//登陆
						alert('登陆');
					} else {
						//未知
						obj.showError('请求出错', '未知错误，请联系管理员');
					}
				} else {
					obj.chkLogin(data);
					success(data, textStatus, jqXHR);
				}
			};

			var error = result.error || this.emptyFn;

			result.error = function (jqXHR, textStatus, errorThrown) {
				obj.chkLogin(jqXHR.responseText);
				obj.showError('请求出错', '系统出错，请稍后再试');
				error(jqXHR, textStatus, errorThrown);
			};

			$.ajax(result);
		},
		chkLogin: function (rspText) {
			if (rspText && rspText.indexOf("登录</title>") > 0)
				location.href = "/";
		},
		dataTableParam: function (aoData, formId, callback) {
			callback = callback || this.emptyFn;

			var formData = this.formArrayUtil($("#" + formId).serializeArray())[0];
			//console.log(formData);
			for (var key in formData) {
				aoData.push({name: key, value: formData[key]});
			}

			callback(aoData, formData);

		},
		dataTableButTemplate: '<div><div class="hidden-sm hidden-xs action-buttons"></div>'
		+ '<div class="hidden-md hidden-lg">'
		+ '<div class="inline pos-rel">'
		+ '<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">'
		+ '	<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>'
		+ '</button>'
		+ '<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">'
		+ '</ul>'
		+ '</div>'
		+ '</div></div>',
		dataTableButList: {},
		initDataTableButFlag: false,
		initDataTableBut: function () {
			if (!this.initDataTableButFlag) {
				this.addDataTableBut('info', 'glyphicon glyphicon-list', 'blue', '详细信息');
				this.addDataTableBut('edit', 'fa fa-pencil-square-o', 'green', '修改');
				this.addDataTableBut('del', 'fa fa-trash-o', 'red', '删除');
				this.initDataTableButFlag = true;
			}

		},
		addDataTableBut: function (name, icon, color, title) {
			title = title || '';
			color = color || 'blue';
			var nameC = name.capitalize();
			var b = '<a class="' + color + ' open' + nameC + '" href="javascript:void(0);" title="' + title + '">'
					+ '<i class="ace-icon ' + icon + ' bigger-130"></i>'
					+ '</a>';

			var s = '<li>'
					+ '<a href="javascript:void(0);" class="tooltip-info open' + nameC + '" title="' + title + '" data-rel="tooltip">'
					+ '<span class="' + color + '">'
					+ '<i class="ace-icon ' + icon + ' bigger-120"></i>'
					+ '</span>'
					+ '</a>'
					+ '</li>';
					
			this.dataTableButList[name] = {
				big: b,
				small: s
			};
		},
		dataTableInit: function (op) {
			op = op || {};
			if (typeof op.id === 'undefined') {
				throw new Error('请输入table的Id');
			}

			op.addBtn = op.addBtn || false;
			op.updateBtn = op.updateBtn || false;
			op.delBtn = op.delBtn || false;
			op.btns = op.btns || [];
			op.checkType =  op.checkType || 'checkbox';

			var columnCheck = {
				"aTargets": [0],
				'bSearchable': false,
				'bSortable': false,
				mData: 'id',
				//'width': '5%',
				'sClass': 'center',
				'mRender': function (data, type, full, meta) {
					var checked = '';
					if(op.defaultCheck&&full[op.defaultCheck]){
						checked = 'checked';
					}
					return '<label> <input type="' + op.checkType + '" class="ace" name="checkId" value="' + data + '" ' + checked + '/> <span class="lbl"></span> </label>';
				}
			};
			var columnBut = {
				//"aTargets": [3],
				mData: null
				, 'bSearchable': false
				, 'bSortable': false
				, sClass: "center"
				//,"width": "10%"
				//,sDefaultContent: editHtml
			};


			//判断是否要checkbox，默认加
			var aoColumnDefs = op.aoColumnDefs || [];
			var columnLength = op.aoColumnDefs.length;
			
			if (false === op.hasCheck) {
				//不加
			} else {
				//加
				var columnCheckClone = jQuery.extend(true, {}, columnCheck);
				if (op.checkId) {
					columnCheckClone.mData = op.checkId;
				}

				if (op.checkNames && op.checkNames.length > 0) {

					columnCheckClone.mRender = function (data, type, full, meta) {
						var checkData = '';
						var checked = '';
						if(op.defaultCheck&&full[op.defaultCheck]){
							checked = 'checked';
						}

						for (var i = 0; i < op.checkNames.length; i++) {
							checkData += ' data-' + op.checkNames[i] + '="' + (full[op.checkNames[i]] || '') + '"';
						}

						var checkHtml = '<label> <input type="' + op.checkType + '" class="ace" name="checkId" value="' + data + '" ' + checkData + ' ' + checked + '/> <span class="lbl"></span> </label>';
						return checkHtml;
					};
				}

				aoColumnDefs.unshift(columnCheckClone);

				columnLength++;
			}

			//判断是否要编辑按钮，默认不加
			if (true === op.hasButton) {
				var context = {id: op.id};
				if (op.buttonType) {
					context.buttonType = op.buttonType;
				} else {
					context.buttonType = ['info', 'edit', 'del'];
				}

				//var editHtml = templateBut(context);

				var columnButClone = jQuery.extend(true, {}, columnBut);
				columnButClone.aTargets = [columnLength];
				//columnButClone.sDefaultContent = editHtml;

				//按钮列表
				//this.dataTableButList = {};
				this.initDataTableBut();

				var templateBut = $(this.dataTableButTemplate);
				for (var j = 0; j < context.buttonType.length; j++) {
					var butObj = this.dataTableButList[context.buttonType[j]];
					if (butObj) {
						templateBut.find('.action-buttons').append(butObj['big']);
						templateBut.find('ul').append(butObj['small']);
					}
				}

				columnButClone.sDefaultContent = templateBut.html();
				aoColumnDefs.push(columnButClone);
			}

			if (op.aoColumnDefs) {
				op.aoColumnDefs = aoColumnDefs;
			}

			var sDomV = '';
			if(op.hideTitle){
				sDomV = '<"text-center"r>t';
			}else{
				sDomV = '<"row"<"col-md-6 table_buttons text-left"><"col-md-2 text-center"r><"col-md-4 text-right"i>>t<"row table-bottom"<"col-md-3"l><"col-md-9"p>><"clear">';
			}
				

			var defaults = {
				sDom: sDomV,
				"bProcessing": true,
				"bServerSide": true,
				'bFilter': false,
				aaSorting: [],
				fnInitComplete: function () {
					var buttonsEle = $("#" + op.id).parent().find("div.table_buttons");

					if (op.addBtn) {
						buttonsEle.append('<button class="tabAdd btn btn-primary btn-xs">新增</button>');
						buttonsEle.find('.tabAdd').click(op.addBtn);
					}
					if (op.updateBtn) {
						buttonsEle.append('<button class="tabUp btn btn-xs btn-primary">修改</button>');
						buttonsEle.find('.tabUp').click(op.updateBtn);
					}
					if (op.delBtn) {
						buttonsEle.append('<button class="tabDel btn btn-xs btn-danger">删除</button>');
						buttonsEle.find('.tabDel').click(op.delBtn);
					}

					if (op.btns.length > 0) {
						for (var j = 0; j < op.btns.length; j++) {
							var b = op.btns[j];
							var color = b.color || 'btn-primary';
							var $e = $('<button class="btn btn-xs ' + color + '">' + b.name + '</button>');
							$e.appendTo(buttonsEle);
							$e.click(b.click);
						}

					}
					if(op.fnAfterInitFinish){
						op.fnAfterInitFinish();
					}
				},
				"fnServerData": function (sSource, aoData, fnCallback) {
					utilsAi.ajax({
						"dataType": 'json',
						"type": "POST",
						"url": sSource,
						"data": aoData,
//					"success": fnCallback
						"success": function (data, textStatus, jqXHR) {
							var tableData = {
								sEcho: data.other,
								iTotalRecords: data.total,
								iTotalDisplayRecords: data.total,
								aaData: data.data
							};
							//console.log(data);
							fnCallback(tableData, textStatus, jqXHR);
						}
					});
				},
				oLanguage: {
					"sProcessing": "查询中...",
					"sLengthMenu": "显示 _MENU_ 项结果",
					"sZeroRecords": "没有匹配结果",
					"sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
					"sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
					"sInfoFiltered": "(由 _MAX_ 项结果过滤)",
					"sInfoPostFix": "",
					"sSearch": "搜索:",
					"sUrl": "",
					"sEmptyTable": "查无数据",
					"sLoadingRecords": "载入中...",
					"sInfoThousands": ",",
					"oPaginate": {
						"sFirst": "首页",
						"sPrevious": "上页",
						"sNext": "下页",
						"sLast": "末页"
					},
					"oAria": {
						"sSortAscending": ": 以升序排列此列",
						"sSortDescending": ": 以降序排列此列"
					}
				}
			};

			var result = $.extend(true, {}, defaults, op);
			//console.log(JSON.stringify(result));

			$('table th input:checkbox').on('click', function () {
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
						.each(function () {
							this.checked = that.checked;
							$(this).closest('tr').toggleClass('selected');
						});
			});
			return result;
		}, dataRangeInit: function (id, op, callback) {
			op = op || {};
			callback = callback || function (start, end) {

					};
			var defaults = {
				autoUpdateInput: false,
				format: 'YYYY-MM-DD',
				locale: {
					applyLabel: '确定',
					cancelLabel: '取消',
					fromLabel: '从',
					toLabel: '到',
					customRangeLabel: '指定范围',
					daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
					monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
					firstDay: 7
				}
			};
			var result = jQuery.extend(true, {}, defaults);
			var $el = $('#' + id);
			$el.daterangepicker(result, callback);
			if ($el.prev() && $el.prev().length > 0) {
				$el.prev().on(ace.click_event, function () {
					$(this).next().focus();
				});
			}

			var timeObject = $el.data('daterangepicker');

			var obj = {
				timeObject: timeObject,
				createTimeStart: '',
				createTimeEnd: '',
				getStart: function () {
					return this.createTimeStart;
				},
				getEnd: function () {
					return this.createTimeEnd;
				}
			};

			timeObject.container.find('.applyBtn').on('click', function (e) {
				obj.createTimeStart = timeObject.container.find('input[name="daterangepicker_start"]').val();
				obj.createTimeEnd = timeObject.container.find('input[name="daterangepicker_end"]').val();
			});

			timeObject.container.find('.cancelBtn').on('click', function (e) {
				obj.createTimeStart = '';
				obj.createTimeEnd = '';
				$el.val('');
			});

			return obj;
		}
	};


	//初始化函数
	function init() {
		selectAjax();
		if (!(typeof window.loadMenu === "undefined")){
			loadMenu();
		}
		

		//页面include进来的，js初始化
		umauthor.pageInit();
	}

	init();

})(jQuery);
