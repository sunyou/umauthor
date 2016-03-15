$(function() {
	// table 处理 start
	var $operatorTabEl = $('#dictListTable');

	var oTable1 = null;

	if (oTable1 == null) {
		oTable1 = $operatorTabEl.dataTable(utilsAi.dataTableInit({
			 addBtn:function(){
					loadEditDictitem('', '', 'add');
					$("#popTitle").html("新增");
			 },
    		 updateBtn:function(){
    			 updateBtnFn();
    		 },
    		 delBtn:function(){
    			 deletBtnFn();
    		 },
			id : 'dictListTable',
			checkId : 'itemKey',
			checkNames : [ 'dictKey', 'itemKey' ],
			"sAjaxSource" : "/dictitem/queryDictitemList",
			"fnServerParams" : function(aoData) {
				aoData.push({
					"name" : "dictKeyCon",
					"value" : $("#dictNameCon").val()
				}, {
					"name" : "itemKeyCon",
					"value" : $("#itemNoCon").val()
				}, {
					"name" : "itemStateCon",
					"value" : $("#itemStateCon").val()
				});
			},
			"aoColumnDefs" : [ {
				"aTargets" : [ 1 ],
				mData : "dictKey"
			}, {
				"aTargets" : [ 2 ],
				mData : "itemKey"
			}, {
				"aTargets" : [ 3 ],
				mData : "itemValue"
			}, {
				"aTargets" : [ 4 ],
				mData : "itemExtValue1"
			}, {
				"aTargets" : [ 5 ],
				mData : "itemExtValue2"
			}, {
				"aTargets" : [ 6 ],
				mData : "itemOrder",
			}, {
				"aTargets" : [ 7 ],
				mData : "parentItemKey"
			}, {
				"aTargets" : [ 8 ],
				mData : "itemState",
				mRender : function(data, type, full, meta) {
					var html;
					if (data == '1') {
						html = '有效';
					} else {
						html = '无效';
					}
					return html;
				}

			}, {
				"aTargets" : [ 9 ],
				mData : "itemDesc"
			} ],
			hasCheck : true,
//			hasButton : true,
//			buttonType : [ 'info', 'edit', 'del' ]
		}));
	} else {
		oTable1.fnDraw();
	}

	oTable1.on('click', '.openEdit', function() {
		var row = $(this).closest('tr')[0];
		var rowData = oTable1.fnGetData(row);
		if (rowData) {
			var items = [];
			items.push({
				'itemKey' : rowData.itemKey,
				'dictKey' : rowData.dictKey
			});
			modifyDictitem(items);
		}
	});
	oTable1.on('click', '.openInfo', function() {
	});
	oTable1.on('click', '.openDel', function() {
	});

	$('#searchFormSubmit').click(function() {
		oTable1.fnDraw();
	});

	umauthor.oTable1 = oTable1;



	$("#resetBtn").click(function() {
		$("#dictNameCon").val("");
		$("#itemNoCon").val("");
		$("#itemStateCon").val('1');
	});

});

/**
 * 获取新增页面
 * 
 * @param orgId
 * @param type
 */
function loadEditDictitem(dictKey, itemKey, type) {
	$('#editContent').html("");
	utilsAi.ajax({
		url : "/dictitem/getEditDictitem",
		async : true,
		data : "dictKey=" + dictKey + "&itemKey=" + itemKey + "&type=" + type,
		success : function(result) {
			$('#editContent').html(result);
			$('#modal-table').modal('toggle');
		}
	});
}

/**
 * 删除事件
 */
function deletBtnFn() {
	var items = [];

	$(
			"#dictListTable input[type='checkbox'][name='checkId']:checked")
			.each(function() {
				var $el = $(this);
				// $el.val();
				var itemKey = $el.data('itemkey');
				var dictKey = $el.data('dictkey');
				items.push({
					'itemKey' : itemKey,
					'dictKey' : dictKey
				});
			});

	if (items.length == 0) {
		utilsAi.alert("请勾选需要删除的记录");
		return;
	}
//	swal({
//		title : "操作提示",
//		text : "您确认删除选定的记录吗？",
//		type : "warning",
//		showCancelButton : true,
//		confirmButtonColor : "#DD6B55",
//		cancelButtonText : "取消",
//		confirmButtonText : "确认",
//		closeOnConfirm : true
//	}, function() {
//		utilsAi.ajax({
//			url : "/dictitem/deleteDictitem",
//			async : true,
//			data : {
//				"primaryKeys" : JSON.stringify(items)
//			},
//			success : function(data) {
//				utilsAi.alert(data.message);
//				if (data.code == '100') {
//					umauthor.oTable1.fnDraw();
//				}
//			}
//		});
//	});
	utilsAi.confirm({
		content:'确定删除已选记录吗？',
		callback:function(){
			utilsAi.ajax({
				url : "/dictitem/deleteDictitem",
				async : true,
				data : {
					"primaryKeys" : JSON.stringify(items)
				},
				success : function(data) {
					utilsAi.alert(data.message);
					if (data.code == '100') {
						umauthor.oTable1.fnDraw();
					}
				}
			});
		}
	})
	
}

/**
 * 更新按钮事件
 */
function updateBtnFn() {
	var items = [];
	$(
			"#dictListTable input[type='checkbox'][name='checkId']:checked")
			.each(
			function() {
				var $el = $(this);
				var itemKey = $el.data('itemkey');
				var dictKey = $el.data('dictkey');
				items.push({
					'itemKey' : itemKey,
					'dictKey' : dictKey
				});
			});

	if (items.length == 0) {
		utilsAi.alert("请勾选需要修改的记录");
		return;
	}
	if (items.length > 1) {
		utilsAi.alert("抱歉只能勾选一条记录");
		return;
	}
	modifyDictitem(items);
}

/**
 * 修改
 * @param items
 */
function modifyDictitem(items) {
	$("#popTitle").html("修改");
	loadEditDictitem(items[0].dictKey, items[0].itemKey, 'update');
	
}
