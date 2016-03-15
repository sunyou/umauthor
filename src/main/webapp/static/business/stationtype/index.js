var $operatorTabEl = $('#stationtypeTable');
var oTable1 = null;
(function($) {	
	//table 处理 start
	
	if(oTable1 == null){
		oTable1 = $operatorTabEl.dataTable(utilsAi.dataTableInit({
			id:'stationtypeTable',
			checkId: 'stationtypeId',
			"sAjaxSource": "/stationtype/stationtypeList",
			"fnServerParams": function ( aoData ) {
			    aoData.push( { 
			    	"name": "stationtypeCode", "value": $("#stationtypeCode").val()
			    },{
			    	"name": "stationtypeName", "value": $("#stationtypeName").val()
			    },{
			    	"name": "stationtypeState", "value": $("#stationtypeState").val()
			    } );
			},
			"aoColumnDefs": [{
					"aTargets": [1],
			    	mData: "stationtypeCode"
			    },{
			    	"aTargets": [2],
			    	mData: "stationtypeName"
			    },{
			    	"aTargets": [3],
			    	mData: "stationtypeState",
					mRender: function (data, type, full, meta){
						var html;
						if (data=='1') {
							html='有效';
						}else {
							html='无效';
						}
		                return html;
		            }
			    },{
			    	"aTargets": [4],
			    	mData: "remarks"
			    }
			 ],
			 //hasCheck:false,
			 hasButton:true,
			 buttonType : ['edit','del'],
			 addBtn:function(){
				 toAdd();
    		 },
    		 updateBtn:function(){
    			 toEdit();
    		 },
    		 delBtn:function(){
    			 del();
    		 }/*,
    		 //自定义按钮
    		 btns:[{
    			 name:'锁定',
    			 color:'btn-warning',
    			 click:function(){
    				 console.log('aaa');
    			 }
    		 }],*/
		}));
	}else{
		oTable1.fnDraw();
	}
	
	//查看详情
	$operatorTabEl.on('click', '.openInfo', function() {
	    var row = $(this).closest('tr')[0];
	    var rowData = $operatorTabEl.fnGetData(row); 
	    
	    if (rowData) {
	        var stationtypeId = rowData.stationtypeId;
	        loadStationtype(stationtypeId);
	        
	    }
	});
	
	//逐条修改
	$operatorTabEl.on('click', '.openEdit', function() {
	    var row = $(this).closest('tr')[0];
	    var rowData = $operatorTabEl.fnGetData(row); 
	    
	    if (rowData) {
	        var stationtypeId = rowData.stationtypeId;
	        loadStationtype(stationtypeId);
	    }
	});
	
	//逐条删除
	$operatorTabEl.on('click', '.openDel', function() {
	    var row = $(this).closest('tr')[0];
	    var rowData = $operatorTabEl.fnGetData(row); 
	    
	    if (rowData) {
	        var stationtypeId = rowData.stationtypeId;
	        utilsAi.confirm({
	    		content:'确定删除这条记录吗？',
	    		callback:function(){
	    	    	utilsAi.ajax({
	    				url : "/stationtype/deleteStationtype",
	    				async : true,
	    				data : {
	    					"delIds" : stationtypeId
	    				},
	    				success : function(data) {
	    					if (data.code == '100') {
	    						utilsAi.alert(data.message);
	    						oTable1.fnDraw();
	    					}
	    					
	    				}
	    			});
	    		}
	    	})
	    }
	});
	
	//点击查询按钮
	$('#searchFormSubmit').click(function(){
		oTable1.fnDraw();
	});
	
	//点击重置按钮
	$("#resetBtn").click(function() {
		$("#stationtypeName").val('');
		$("#stationtypeCode").val('');
		$("#stationtypeState").val('1');
	});

})(jQuery);

//加载数据
function loadStationtype(stationtypeId){
	utilsAi.ajax({
		url:"/stationtype/getStationtype"
		,async:true
		,data: "stationtypeId="+stationtypeId
		,success:function(result){
			$('#editContent').html(result);
			$('#modal-table').modal('toggle');
		}
	});
}

//新增
function toAdd(){
	loadStationtype('');
}

//修改
function toEdit(){
	var items = [];
	$("#stationtypeTable input[type='checkbox'][name='checkId']:checked").each(function() {
		var $el = $(this);
		var stationtypeId = $el.val();
		items.push({
			'stationtypeId' : stationtypeId
		});
	});

	if (items.length == 0) {
		utilsAi.alert("请选择需要修改的记录");
		return false;
	}
	
	if (items.length > 1) {
		utilsAi.alert("仅选择一条记录");
		return false;
	}

	loadStationtype(items[0].stationtypeId);
}

//删除
function del(){
	var items = '';
	$("#stationtypeTable input[type='checkbox'][name='checkId']:checked").each(function() {
		var $el = $(this);
		var stationtypeId = $el.val();
		items += stationtypeId + ',';
	});

	if (items == '') {
		utilsAi.alert("请选择需要删除的记录");
		return false;
	}
	
	utilsAi.confirm({
		content:'确定删除已选记录吗？',
		callback:function(){
	    	utilsAi.ajax({
				url : "/stationtype/deleteStationtype",
				async : true,
				data : {
					"delIds" : items.substring(0, items.length-1)
				},
				success : function(data) {
					if (data.code == '100') {
						utilsAi.alert(data.message);
						oTable1.fnDraw();
					}
					
				}
			});
		}
	})
}