/**
 * 岗位类型获取
 */
var stationtypeId;
var $operatorTabElSType = $('#pathcstationtypeTable1');
var MystationtypeTable1 = null;

function loadStationType1() {
	if (MystationtypeTable1 == null) {
		MystationtypeTable1 = $operatorTabElSType.dataTable(utilsAi
				.dataTableInit({
					id : 'pathcstationtypeTable1',
					checkId : 'stationtypeId',
					checkNames : [ 'stationtypeId' ],
					"sAjaxSource" : "/stationtype/stationtypeList",
					"fnServerParams" : function(aoData) {
					},
					"aoColumnDefs" : [ {
						"aTargets" : [ 1 ],
						mData : "stationtypeCode"
					}, {
						"aTargets" : [ 2 ],
						mData : "stationtypeName"
					}, {
						"aTargets" : [ 3 ],
						mData : "stationtypeState",
						mRender : function(data, type, full, meta) {
							var html;
							if (data == '1') {
								html = '有效';
							} else {
								html = '无效';
							}
							return html;
						}
					} ],
//					checkType:'radio',
					hideTitle : true,
				// hasCheck:false,
				}));
	} else {
		MystationtypeTable1.fnDraw();
	}
}

$(function() {
	loadStationType1();
	$('#fuelux-wizard')
			.ace_wizard({})
			.on(
					'actionclicked.fu.wizard',
					function(e, info) {
						if (info.step == 0) {
						
						} else if (info.step == 1) {
							stationtypeId = "";
							var stationtypeIds = [];
							$("#pathcstationtypeTable1 input[type='checkbox'][name='checkId']:checked")
									.each(function() {
										var $el = $(this);
										var temp = $el.data('stationtypeid');
										stationtypeIds.push({
											'temp' : temp
										});
									});

							if (stationtypeIds.length > 1) {
								utilsAi.alert('只能选择一个岗位类型');
								return false;
							}
							if (stationtypeIds.length == 0) {
								utilsAi.alert('请选择岗位类型');
								return false;
							}
							stationtypeId = stationtypeIds[0].temp;
							$("#batchStationTable1").show();
							$("#tipMsg").hide();
							loadStation();

						}

					}).on('finished.fu.wizard', function(e) {
				submit();
			});

	// step 2:
	var $operatorTabElStation = $('#batchStationTable1');
	var batchStationTable = null;

	function loadStation() {

		if (batchStationTable == null) {
			batchStationTable = $operatorTabElStation.dataTable(utilsAi
					.dataTableInit({
						id : 'batchStationTable1',
						checkId : 'stationId',
						checkNames : [ 'stationId', 'relateId' ],
						"sAjaxSource" : "/station/stationTypeStationList",
						"fnServerParams" : function(aoData) {
							aoData.push({
								"name" : "stationTypeId",
								"value" : stationtypeId
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
									"fnRender" : function(obj) {
										if (obj.aData.stationState == "0") {
											return "无效";
										} else {
											return "有效";
										}
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
	$("#batchStationTable1 input[type='checkbox'][name='checkId']:checked")
			.each(function() {
				var $el = $(this);
				var stationId = $el.data('stationid');
				selectItems.push({
					'stationId' : stationId
				});
			});
	utilsAi.ajax({
		url : "/station/saveStationTypeStation",
		async : true,
		data : {
			"selectItems" : JSON.stringify(selectItems),
			"stationTypeId" : stationtypeId
		},
		success : function(result) {
			utilsAi.alert(result.message);
			if (result.code == '100') {
				$('#modal-table').modal('toggle');
			}
		}
	});
}
