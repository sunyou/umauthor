$(function() {
	// table 处理 start
	var $operatorTabEl = $('#groupTable');

	var oTable1 = null;

	if (oTable1 == null) {
		oTable1 = $operatorTabEl.dataTable(utilsAi.dataTableInit({
			addBtn : function() {
				addBtnFn();
			},
			updateBtn : function() {
				updateBtnFn();
			},
			delBtn : function() {
				deleteFn();
			},
			id : 'groupTable',
			checkId : 'groupId',
			checkNames : [ 'groupId' ],
			"sAjaxSource" : "/stationGroup/queryStationGroupList",
			"fnServerParams" : function(aoData) {
				aoData.push({
					"name" : "groupNameCon",
					"value" : $("#groupNameCon").val()
				});
			},
			"aoColumnDefs" : [ {
				"aTargets" : [ 1 ],
				mData : "groupName"
			}, {
				"aTargets" : [ 2 ],
				mData : "remarks"
			}, {
				"aTargets" : [ 3 ],
				mData : "operatorName"
			}, {
				"aTargets" : [ 4 ],
				mData : "createDate",
				mRender: function (data, type, full, meta){
		    		return new Date(data).format("yyyy-MM-dd hh:mm:ss");		                
	            }
			}
			],
			hasCheck : true
		// hasButton:true,
		// buttonType : ['info','edit','del']
		}));
	} else {
		oTable1.fnDraw();
	}

	$('#searchFormSubmit').click(function() {
		oTable1.fnDraw();
	});

	umauthor.oTable1 = oTable1;

	$("#resetBtn").click(function() {
		$("#groupNameCon").val("");
	});

});
/**
 * 添加按钮事件
 */
function addBtnFn() {
	loadEditStationGroup('', 'add');
	$("#popTitle").html("新增");
	$('#modal-table').modal('toggle');
}
/**
 * 修改按钮事件
 */
function updateBtnFn() {
	var items = [];
	$("#groupTable input[type='checkbox'][name='checkId']:checked")
			.each(function() {
				var $el = $(this);
				var groupId = $el.data('groupid');
				items.push({
					'groupId' : groupId
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
	$("#popTitle").html("修改");
	loadEditStationGroup(items[0].groupId, 'update');
	$('#modal-table').modal('toggle');
}
/**
 * 删除按钮事件
 */
function deleteFn() {
	var items = [];

	$("#groupTable input[type='checkbox'][name='checkId']:checked")
			.each(function() {
				var $el = $(this);
				var groupId = $el.data('groupid');
				items.push({
					'groupId' : groupId
				});
			});

	if (items.length == 0) {
		utilsAi.alert("请勾选需要删除的记录");
		return;
	}
	swal({
		title : "操作提示",
		text : "您确认删除选定的记录吗？",
		type : "warning",
		showCancelButton : true,
		confirmButtonColor : "#DD6B55",
		cancelButtonText : "取消",
		confirmButtonText : "确认",
		closeOnConfirm : true
	}, function() {
		utilsAi.ajax({
			url : "/stationGroup/deleteStationGroup",
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
	});
}

/**
 * 获取新增页面
 * 
 * @param orgId
 * @param type
 */
function loadEditStationGroup(groupId, type) {
	$('#editContent').html("");
	utilsAi.ajax({
		url : "/stationGroup/getEditGroup",
		async : true,
		data : "groupId=" + groupId + "&type=" + type,
		success : function(result) {
			$('#editContent').html(result);
		}
	});
}
