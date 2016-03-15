var groupTree = null;
var menuId = '';
// ztree 处理 start
var zNodes = [ {
	id : 0,
	pId : 0,
	name : '系统菜单列表',
	isParent : true
} ];
var setting = {
	check : {
		enable : true,
		chkStyle : "radio",
		radioType : "all"
	},
	view : {
	// selectedMulti: false
	},
	data : {
		dataTransform : function(dataArr) {
			var newData = [];
			for (var i = 0; i < dataArr.length; i++) {
				var node = {
					id : dataArr[i].menuId,
					name : dataArr[i].menuName,
					pId : dataArr[i].parentMenuId,
					isParent : dataArr[i].isParent
				};
				newData.push(node);
			}
			return newData;
		}
	},
	async : {
		enable : true,
		dataType : 'json',
		url : "/menu/tree2",
		autoParam : [ "id=parentMenuId", "name=n", "level=lv" ]
	},
	callback : {
		beforeClick : beforeClick
	}
};

function beforeClick(treeId, node) {

}

function loadTree() {
	groupTree = $.fn.zTree.init($("#groupTree"), setting, zNodes);
	var nodes = groupTree.getNodeByParam('id', '0');
	groupTree.expandNode(nodes, true, false, false, false);
}

$(function() {
	loadTree();
	$('#fuelux-wizard').ace_wizard({}).on('actionclicked.fu.wizard',
			function(e, info) {
				if (info.step == 0) {
					loadTree();
				} else if (info.step == 1) {
					var treeObj = $.fn.zTree.getZTreeObj("groupTree");
					var nodes = treeObj.getCheckedNodes(true);
					if (nodes.length > 0) {
						menuId = nodes[0].id;
						$("#batchStationTable1").show();
						$("#tipMsg").hide();
						loadStation();
					} else {
						utilsAi.alert('请选择菜单');
						return false;
					}

				}
			}).on('finished.fu.wizard', function(e) {
		submit();
	});

	// step 2:
	var $operatorTabEl = $('#batchStationTable1');
	var batchStationTable = null;

	function loadStation() {

		if (batchStationTable == null) {
			batchStationTable = $operatorTabEl.dataTable(utilsAi
					.dataTableInit({
						id : 'batchStationTable1',
						checkId : 'stationId',
						checkNames : [ 'stationId', 'relateId' ],
						"sAjaxSource" : "/station/menuStationList",
						"fnServerParams" : function(aoData) {
							aoData.push({
								"name" : "menuId",
								"value" : menuId
							});
						},
						"aoColumnDefs" : [
								{
									"aTargets" : [ 1 ],
									mData : "stationName"
								},
								{
									"aTargets" : [ 2 ],
									mData : "stationDesc"
								},
								{
									"aTargets" : [ 3 ],
									mData : "createDate",
									"fnRender" : function(obj) {
										return new Date(obj.aData.createDate)
												.format("yyyy-MM-dd hh:mm:ss");
									}
								}, {
									"aTargets" : [ 4 ],
									mData : "stationState",
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
									"aTargets" : [ 5 ],
									mData : "groupName"
								} ],
						defaultCheck : 'relateId',// 不为空勾选
						hideTitle : true,
					// hasCheck : true
					}));
		} else {
			batchStationTable.fnDraw();
		}
	}

});

/**
 * 完成提交
 */
function submit() {
	var selectItems = [];
	$("#batchStationTable1 input[type='checkbox'][name='checkId']:checked").each(function() {
				var $el = $(this);
				var stationId = $el.data('stationid');
				selectItems.push({
					'stationId' : stationId
				});
			});
	utilsAi.ajax({
		url : "/station/saveMenuStation",
		async : true,
		data : {
			"selectItems" : JSON.stringify(selectItems),
			"menuId":menuId
		},
		success : function(result) {
			utilsAi.alert(result.message);
			if (result.code=='100') {
				$('#modal-table').modal('toggle');
			}
		}
	});
}
