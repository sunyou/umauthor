$(function() {
	$("#saveBtn").click(function() {
		saveOrder();
	});
});

function getHexBackgroundColor(obj) {
	var rgb = $(obj).css('background-color');
	if(rgb.indexOf('rgb')>=0){
		rgb = rgb.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
		function hex(x) {
			return ("0" + parseInt(x).toString(16)).slice(-2);
		}
		rgb = "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
	}
	return rgb;
}

function onMouseOver(obj) {
	var oldCss = getHexBackgroundColor(obj);
	
	if ('#87b87f' == oldCss) {
		return;
	}
	$(obj).css('background', '#ccffcc');
}

function onMouseOut(obj) {
	var oldCss = getHexBackgroundColor(obj);

	if ('#87b87f' == oldCss) {
		return;
	}
	$(obj).css('background', '#ffffff');
}

function onClick(obj) {
	var oldCss = getHexBackgroundColor(obj);
	
	var selectArray = $("#configTab tr");
	for(var i=0;i<selectArray.length;i++){
		$(selectArray[i]).css('background', '#ffffff'); 
	}
	// 未选中
	if ('#87b87f' == oldCss) {
		$(obj).css('background', '#ffffff');
		return;
	}
	// 选中
	$(obj).css('background', '#87b87f');
}

function toUp() {
	// 获取所有选中的
	var selectArray = $("#configTab tr:not(:first)");
	var html = "";
	var obj = null;
	var select = 0;
	var topObj = null;
	for (var i = 0; i < selectArray.length; i++) {
		if ('#87b87f' == getHexBackgroundColor(selectArray[i])) {
			select++;
			obj = selectArray[i];
			// 移动的是第二个一下的
			if (i != 0) {
				topObj = selectArray[i - 1];
			}
		}
	}
	if (select < 1) {
		utilsAi.alert('请选择行!');
		return;
	}
	if (select > 1) {
		utilsAi.alert('只能选择一行!');
		return;
	}
	// 将两个对象调换
	if (topObj != null) {
		$(topObj).before($(obj));
	}
}

function toDown() {

	// 获取所有选中的
	var selectArray = $("#configTab tr:not(:first)");
	var html = "";
	var obj = null;
	var select = 0;
	var downObj = null;
	for (var i = 0; i < selectArray.length; i++) {
		if ('#87b87f' == getHexBackgroundColor(selectArray[i])) {
			select++;
			obj = selectArray[i];
			// 移动的是第二个一下的
			if (i != (selectArray.length - 1)) {
				downObj = selectArray[i + 1];
			}
		}
	}
	if (select < 1) {
		utilsAi.alert('请选择行!');
		return;
	}
	if (select > 1) {
		utilsAi.alert('只能选择一行!');
		return;
	}
	// 将两个对象调换
	if (downObj != null) {
		$(downObj).after($(obj));
	}
}

function saveOrder() {
	var selectArray = $("#configTab tr:not(:first)");
	var orderedData = [];
	var count = 0;
	for (var i = 0; i < selectArray.length; i++) {
		var obj = {};
		obj.menuId = $(selectArray[i]).attr("id");
		obj.menuOrder = ++count;
		orderedData.push(obj);
	}

	var orderedJson = JSON.stringify(orderedData);
	
	utilsAi.ajax({
		url:'/menu/saveMenuOrder',
		type:'post',
		data:{"orderedJson":orderedJson},
		success:function(json){
			utilsAi.alert(json.message);
			$('#modal-table').modal('toggle');
			}
	});
}
