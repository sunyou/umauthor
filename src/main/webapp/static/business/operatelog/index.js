(function($) {	
	//table 处理 start
	var $operatorTabEl = $('#resultTable');
	var oTable1 = null;
	
	if(oTable1 == null){
		oTable1 = $operatorTabEl.dataTable(utilsAi.dataTableInit({
			id:'resultTable',
			checkId: 'logId',
			"sAjaxSource": "/operatelog/operateLogList",
			"fnServerParams": function ( aoData ) {
			    aoData.push( { 
			    	"name": "operatorCode", "value": $("#operatorCode").val()
			    },{
			    	"name": "operatorType", "value": $("#operatorType").val()
			    },{
			    	"name": "operatorDateBegin", "value": $("#operatorDateBegin").val()
			    },{
			    	"name": "operatorDateEnd", "value": $("#operatorDateEnd").val()
			    } );
			},
			"aoColumnDefs": [{
					"aTargets": [0],
			    	mData: "operatorCode"
			    },{
			    	"aTargets": [1],
			    	mData: "operatorType"
			    },{
			    	"aTargets": [2],
			    	mData: "operatorDesc"
			    },{
			    	"aTargets": [3],
			    	mData: "operatorDate",
			    	mRender: function (data, type, full, meta){
			    		return new Date(data).format("yyyy-MM-dd hh:mm:ss");		                
		            }
			    }
			 ],
			 hasCheck:false,
			 hasButton:false,
			 buttonType : ['info','edit','del']
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
	        $('#modal-table').modal('toggle');
	    }
	});
	
	//点击查询按钮
	$('#searchFormSubmit').click(function(){
		oTable1.fnDraw();
	});
	
	$('.date-picker').datepicker({autoclose:true}).next().on('click', function(){
		$(this).prev().focus();
	});
	
	//点击重置按钮
	$("#resetBtn").click(function() {
		$("#operatorCode").val('');
		$("#operatorType").val('');
		$("#operatorDateBegin").val('');
		$("#operatorDateEnd").val('');
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
		}
	});
}