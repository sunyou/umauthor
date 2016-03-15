;(function(){
	// table 处理 start
	var $operatorTabEl = $('#stationTable');

	var oTable1 = null;

	if (oTable1 == null) {
		oTable1 = $operatorTabEl.dataTable(utilsAi.dataTableInit({
			id : 'stationTable',
			checkId : 'stationId',
			checkNames:['stationId','stationName'],
			"sAjaxSource" : "/station/list",
			"fnServerParams" : function(aoData) {
				aoData.push({ 
			    	"name": "groupName", "value":$('#groupName').val()
			    },{
			    	"name": "stationName", "value":$('#stationName').val()
			    } );
			},
			"aoColumnDefs" : [{
				"aTargets": [1],
		    	mData: "stationName"
		    },{
		    	"aTargets": [2],
		    	mData: "stationDesc"
		    },{
		    	"aTargets": [3],
		    	mData: "createDate",
		    	"fnRender": function(obj){
		    		return new Date(obj.aData.createDate).format("yyyy-MM-dd hh:mm:ss");
		    	}
		    },{
		    	"aTargets": [4],
		    	mData: "stationState",
		    	"fnRender": function(obj){
		    		if(obj.aData.stationState == "0"){
		    			return "无效";
		    		}else{
		    			return "有效";
		    		}
		    	}
		    },{
		    	"aTargets": [5],
		    	mData: "groupName"
		    }
		    
		 ],
			hasCheck : true,
		    addBtn:add,
		    updateBtn:mod,
		    delBtn:del,
		    btns:[{name:'批量关联菜单',click:batchMenuRelate},{name:'批量关联岗位类型',click:batchStationTypeRelate}]
		}));
		window.umauthor.stationTable = oTable1;
	} else {
		oTable1.fnDraw();
	}

	
	
	$('#searchFormSubmit').click(function() {
		oTable1.fnDraw();
	});
	
	/**
	 * 批量菜单关联
	 * @returns
	 */
	function batchMenuRelate() {
		$("#popTitle").html("批量菜单关联");
		$('#editContent').html("");
		utilsAi.ajax({
			url : "/station/getBatchMenuRelate",
			async : true,
			data : "",
			success : function(result) {
				$('#editContent').html(result);
				$('#modal-table').modal('toggle');
			}
		});
		
	}
	
	function batchStationTypeRelate() {
		$("#popTitle").html("批量岗位类型关联");
		$('#editContent').html("");
		utilsAi.ajax({
			url : "/station/getBatchStationTypeRelate",
			async : true,
			data : "",
			success : function(result) {
				$('#editContent').html(result);
				$('#modal-table').modal('toggle');
			}
		});
	}
	
	
	function add(){
		$("#popTitle").html("新增");
		utilsAi.ajax({
			url : "/station/edit",
			async : true,
			data : "type=add",
			success : function(result) {
				$('#editContent').html(result);
				$('#modal-table').modal('toggle');
			}
		});
	}
	
	function mod(){
		var items = [];
		$("#stationTable input[type='checkbox'][name='checkId']:checked").each(function() {
			var $el = $(this);
			var stationId = $el.val();
			items.push({
				'stationId' : stationId
			});
		});

		if (items.length == 0) {
			utilsAi.alert("请勾选需要修改的记录");
			return;
		}
		if (items.length >1) {
			utilsAi.alert("抱歉只能勾选一条记录");
			return;
		}
		
		$("#popTitle").html("修改");
		utilsAi.ajax({
			url : "/station/edit",
			async : true,
			data : "stationId=" + items[0].stationId + "&type=update",
			success : function(result) {
				$('#editContent').html(result);
				$('#modal-table').modal('toggle');
			}
		});
	}
	
	function del(){
		var items = [];
		$("#stationTable input[type='checkbox'][name='checkId']:checked").each(function() {
			var $el = $(this);
			items.push({
				'stationId' : $el.data('stationid'),
				'stationName' : $el.data('stationname')
			});
		});

		if (items.length == 0) {
			utilsAi.alert("请勾选需要修改的记录");
			return;
		}
		if (items.length >1) {
			utilsAi.alert("抱歉只能勾选一条记录");
			return;
		}
		bootbox.setDefaults({"locale":"zh_CN"});
		bootbox.confirm('是否确认删除岗位"'+items[0].stationName+'"?', function(result) {
			if(result) {
				utilsAi.ajax({
					url:'/station/delStation',
					data:{"stationId":items[0].stationId},
					success:function(data){
						utilsAi.alert(data.message);
						window.umauthor.stationTable.fnDraw();
					}
				});
			}
		});
	}
	
})(jQuery);