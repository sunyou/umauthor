<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<input type="hidden" id="toOperatorId" value="${toOperatorId }"/>
<table id="relaStationTable" class="table table-striped table-bordered table-hover" style="width:100%;">
	<thead>
		<tr>
			<th class="center">
				<label>
					<input type="checkbox" class="ace" />
					<span class="lbl"></span>
				</label>
			</th>
			<th class="center">岗位名称</th>
			<th class="center">授予人</th>
			<th class="center">授予组织</th>
			<th class="center">授权人</th>
			<th class="center">授权时间</th>
			<th class="center">是否可再授权</th>
		</tr>
	</thead>
</table>


<%-- confirm框 --%>
<div id="dialog-confirm" class="hide">
	<div class="alert alert-info bigger-110" id="confirm-content">
		
	</div>

</div>

<script src="${resourcePath }/ace_v135/dist/js/ace-extra.min.js"></script>										

<script src="${resourcePath }/ace_v135/dist/js/fuelux/fuelux.wizard.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/jquery.validate.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/additional-methods.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/bootbox.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/jquery.maskedinput.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/select2.min.js"></script>
<script>
jQuery(function($) {
	
	
	var $operatorTabEl2 = $('#relaStationTable');
	var oTable12 = null;

	function loadRelaStation(){
		if(oTable12 == null){
			oTable12 = $operatorTabEl2.dataTable(utilsAi.dataTableInit({
				id : 'relaStationTable',
				checkId : 'id',
				checkNames:['id','stationName','stationId','operatorId','orgId'],
				"sAjaxSource" : "/operator/relaStationList",
				"fnServerParams" : function(aoData) {
					aoData.push({ 
				    	"name": "operatorId", "value":$('#toOperatorId').val()
				    });
				}, 
				"aoColumnDefs" :  [{
					"aTargets": [1],
			    	mData: "stationName"
			    },{
			    	"aTargets": [2],
			    	mData: "operatorName"
			    },{
			    	"aTargets": [3],
			    	mData: "orgName"
			    },{
			    	"aTargets": [4],
			    	mData: "authorOperatorName"
			    },{
			    	"aTargets": [5],
			    	mData: "authorDate",
			    	"fnRender": function(obj){
			    		return new Date(obj.aData.authorDate).format("yyyy-MM-dd hh:mm:ss");
			    	}
			    },{
			    	"aTargets": [6],
			    	mData: "allowReauthor",
			    	"fnRender": function(obj){
			    		if(obj.aData.allowReauthor == 0){
			    			return "否";
			    		}else{
			    			return "是";
			    		}
			    	}
			    }
			 ],
			 hasCheck : true,
			 	addBtn:add,
			    updateBtn:mod,
			    delBtn:del
			}));
			window.umauthor.relaStationTable = oTable12;
		}else{
			oTable12.fnDraw();
		}
	}

	//点击查询按钮
	$('#searchFormSubmit').click(function(){
		oTable12.fnDraw();
	});
	
	function add(){
		$("#popTitle").html("新增授权岗位");
		utilsAi.ajax({
			url : "/operator/toStationGrant",
			async : true,
			data : "toOperatorId="+$("#toOperatorId").val(),
			success : function(result) {
				$('#editContent').html(result);
				$('#add-modal-table').modal('toggle');
			}
		});
	}

	function mod(){
		$("#popTitle").html("授权岗位修改");
		var items = [];
		$("#relaStationTable input[type='checkbox'][name='checkId']:checked").each(function() {
			var $el = $(this);
			items.push({
				'id' : $el.data('id')
			});
		});
		if (items.length == 0) {
			utilsAi.alert("请勾选需要删除的记录");
			return;
		}
		if (items.length >1) {
			utilsAi.alert("抱歉只能勾选一条记录");
			return;
		}
		utilsAi.ajax({
			url : "/operator/toRelaStationMod",
			async : true,
			data : "id="+items[0].id+"",
			success : function(result) {
				$('#editRelaStationContent').html(result);
				$('#mod-modal-table').modal('toggle');
			}
		});
	}

	function del(){
		var items = [];
		$("#relaStationTable input[type='checkbox'][name='checkId']:checked").each(function() {
			var $el = $(this);
			items.push({
				'id' : $el.data('id'),
				'stationId' : $el.data('stationid'),
				'operatorId' : $el.data('operatorid'),
				'orgId' : $el.data('orgid'),
				'stationName' : $el.data('stationname')
			});
		});
		if (items.length == 0) {
			utilsAi.alert("请勾选需要删除的记录");
			return;
		}
		if (items.length >1) {
			utilsAi.alert("抱歉只能勾选一条记录");
			return;
		}
		bootbox.setDefaults({"locale":"zh_CN"});
		bootbox.confirm('确认回收该岗位权限"'+items[0].stationName+'"?', function(result) {
			if(result) {
				utilsAi.ajax({
					url:'/operator/delRelaStation',
					data:{"id":items[0].id,"operatorId":items[0].operatorId,"stationId":items[0].stationId,"orgId":items[0].orgId},
					success:function(data){
						utilsAi.alert(data.message);
						oTable12.fnDraw();
					}
				});
			}
		});
	}
	
	loadRelaStation();
});
</script>