(function($) {
	$.fn.sidebarMenu = function(options) {
		//console.log(options);
		options = $.extend({}, $.fn.sidebarMenu.defaults, options || {});
		var target = $(this);
		target.addClass('nav');
		target.addClass('nav-list');
		if (options.data) {
			init(target, options.data);
		} else {
			if (!options.url)
				return;
			$.getJSON(options.url, options.param, function(data) {
				init(target, data);
			});
		}
		//var url = window.location.pathname;
		// menu = target.find("[href='" + url + "']");
		// menu.parent().addClass('active');
		// menu.parent().parentsUntil('.nav-list',
		// 'li').addClass('active').addClass('open');
		function init(target, data) {
			$.each(data, function(i, item) {
				var li = $('<li></li>');
				//alert(menuActiveId==item.id);
				
				if(item.open==true){
					li = $('<li class=\"active\"></li>');
				}
				if(item.active==true){
					li = $('<li class=\"active\"></li>');
				}
				
				var a = $('<a></a>');
				var icon = $('<i></i>');
				// icon.addClass('glyphicon');
				icon.addClass('menu-icon');
				icon.addClass(item.icon);
				var text = $('<span></span>');
				text.addClass('menu-text').text(item.text);
				a.append(icon);
				a.append(text);
				if (item.menus && item.menus.length > 0) {
					a.attr('href', '#');
					a.addClass('dropdown-toggle');
					var arrow = $('<b></b>');
					arrow.addClass('arrow').addClass('fa fa-angle-down');
					a.append(arrow);
					li.append(a);
					var menus = $('<ul></ul>');
					menus.addClass('submenu');
					init(menus, item.menus);
					li.append(menus);
					li.append('<b class="arrow"></b>');
				} else {
					var href = 'javascript:onMenu({id:\'' + item.id
							+ '\',title: \'' + item.text
							+ '\',close: true,url: \'' + item.url + '\'});';
					a.attr('href', href);
					li.append(a);
				}
				target.append(li);
			});
		}
	//	alert(1);
		$(".active").parents('li').each(function(){
			$(this).addClass("active open");
		});
		$(".active").parents('.submenu').each(function(){
			$(this).attr("style","display:block");
		});
		
	};

	$.fn.sidebarMenu.defaults = {
		url : null,
		param : null,
		data : null
	};
})(jQuery);

var getMenuActive=function(){
	
	//menuActiveId=getMenuActive();
	/*alert(menuActiveId);
	console.log(options);*/
	return Cookies.get('menuId' );
};
var onMenu=function(options){
	Cookies.set('menuId', options.id);
	window.location=GLOBAL.WEBROOT+options.url;
	
};



/*var addTabs = function (options) {
	  //var rand = Math.random().toString();
	  //var id = rand.substring(rand.indexOf('.') + 1);
	  var url = window.location.protocol + '//' + window.location.host;
	  options.url = url + options.url;
	  id = "tab_" + options.id;
	  $(".active").removeClass("active");
	  //如果TAB不存在，创建一个新的TAB
	  if (!$("#" + id)[0]) {
	    //固定TAB中IFRAME高度
	    mainHeight = $(document.body).height() - 90;
	    //创建新TAB的title
	    title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + options.title;
	    //是否允许关闭
	    if (options.close) {
	      title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id + '"></i>';
	    }
	    title += '</a></li>';
	    //是否指定TAB内容
	    if (options.content) {
	      content = '<div role="tabpanel" class="tab-pane" id="' + id + '">' + options.content + '</div>';
	    } else {//没有内容，使用IFRAME打开链接
	      content = '<div role="tabpanel" class="tab-pane" id="' + id + '"><iframe src="' + options.url + '" width="100%" height="' + mainHeight +
	          '" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
	    }
	    //加入TABS
	    $(".nav-tabs").append(title);
	    $(".tab-content").append(content);
	  }
	  //激活TAB
	  $("#tab_" + id).addClass('active');
	  $("#" + id).addClass("active");
	};
	var closeTab = function (id) {
	  //如果关闭的是当前激活的TAB，激活他的前一个TAB
	  if ($("li.active").attr('id') == "tab_" + id) {
	    $("#tab_" + id).prev().addClass('active');
	    $("#" + id).prev().addClass('active');
	  }
	  //关闭TAB
	  $("#tab_" + id).remove();
	  $("#" + id).remove();
	};
	$(function () {
	  mainHeight = $(document.body).height() - 45;
	  $('.main-left,.main-right').height(mainHeight);
	  $("[addtabs]").click(function () {
	    addTabs({ id: $(this).attr("id"), title: $(this).attr('title'), close: true });
	  });

	  $(".nav-tabs").on("click", "[tabclose]", function (e) {
	    id = $(this).attr("tabclose");
	    closeTab(id);
	  });
	});
*/