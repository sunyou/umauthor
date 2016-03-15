<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<link rel="stylesheet" href="${resourcePath}/ace/assets/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<div class="widget-box">
	<div class="widget-body">
		<div class="widget-main">
			<form class="form-horizontal" role="form" id="stationForm" method="post" style="padding-top:10px;">
				<input type="hidden" id="stationId" name="stationId" value=""/>
				<input type="hidden" id="toOperatorId" name="toOperatorId" value="${toOperatorId }"/>
				<div class="row">
					<div class="col-md-12"><strong>选择授权岗位</strong></div>
					<div class="col-md-12">
						<table id="stationTable" class="table table-striped table-bordered table-hover" style="width: 100%;">
							<thead>
								<tr>
									<th class="center">
										<label>
											<input type="checkbox" class="ace" />
											<span class="lbl"></span>
										</label>
									</th>
									<th class="center" >岗位名称</th>
									<th class="center" >岗位描述</th>
									<th class="center" style="width: 150px;">创建时间</th>
									<th class="center" style="width: 100px;">岗位状态</th>
									<th class="center" style="width: 100px;">岗位分类</th>
								</tr>
							</thead>
						</table>
					</div>
					<div class="col-md-12" style="margin-top:20px;"><strong>其它信息填写</strong></div>
					<div class="col-md-12">
						<div class="form-group">
							<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgId">授权组织:</label>
							<div class="col-xs-12 col-sm-9 col-md-4">
								<div class="clearfix">
									<input type="text" id="grantOrgTree" name="grantOrgTree" placeholder="" value="" class="col-xs-12 col-sm-4 col-md-12" />
									<input type="hidden" id="grantOrgId" name="grantOrgId" value="${umOperator.orgId }"/>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="stationName">授权操作员:</label>
							<div class="col-xs-12 col-sm-9 col-md-4">
								<div class="clearfix">
									<input type="text" id="grantOperator" name="grantOperator" placeholder="" value="${umOperator.operatorName }" readonly="readonly" class="col-xs-12 col-sm-4 col-md-12" />
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="allowReauthor">可再授权:</label>
							<div class="col-xs-12 col-sm-9 col-md-2">
								<div class="clearfix">
									<select id="allowReauthor" name="allowReauthor" value="">
										<option value="1">是</option>
										<option value="0">否</option>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="center">
						<button type="button" class="btn btn-sm btn-success" id="stationFormSubmit">
							提交
							<i class="icon-arrow-right icon-on-right bigger-110"></i>
						</button>
					</div>
				</div>
			</form>
		</div><!-- /widget-main -->
	</div><!-- /widget-body -->
</div>
<script src="${resourcePath }/ace_v135/dist/js/ace-extra.min.js"></script>										

<script src="${resourcePath }/ace_v135/dist/js/fuelux/fuelux.wizard.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/jquery.validate.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/additional-methods.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/bootbox.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/jquery.maskedinput.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/select2.min.js"></script>
	
<script src="${resourcePath}/scripts/lib/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script src="${resourcePath}/scripts/lib/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script src="${resourcePath}/scripts/common/select-tree.js"></script>								
<script type="text/javascript">

	jQuery(function($) {
		
	
		$("#stationForm").validate({
			rules: {
				stationName: {
					required: true,
					maxlength: 32
				},
				groupId: {
					required: true
				}
			},
			messages: {
				stationName:{
					required: "请输入岗位名称",
					maxlength: '岗位名称不能大于{0}个字'
				},
				groupId:{
					required: "请选择岗位分类"
				}
			}
		});
		//step 3:
		var $operatorTabEl = $('#stationTable');
		var oTable1 = null;
		
		function loadStation(){
			if(oTable1 == null){
				oTable1 = $operatorTabEl.dataTable(utilsAi.dataTableInit({
					id : 'stationTable',
					checkId : 'stationId',
					checkNames:['stationId','stationName'],
					"sAjaxSource" : "/station/list",
					/* "fnServerParams" : function(aoData) {
						aoData.push({ 
					    	"name": "groupName", "value":$('#groupName').val()
					    },{
					    	"name": "stationName", "value":$('#stationName').val()
					    } );
					}, */
					"aoColumnDefs" :  [{
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
				 hideTitle:true
				}));
			}else{
				oTable1.fnDraw();
			}
		}
		
		//点击查询按钮
		$('#stationFormSubmit').click(function(){
			submit();
		});
		
		function submit(){
			var items = [];
			$("#stationTable input[type='checkbox'][name='checkId']:checked").each(function() {
				var $el = $(this);
				items.push($el.data('stationid'));
			});
			if(items.length > 0){
				var msg = "";
		        for (var i = 0; i < items.length; i++) {
		            msg += items[i]+",";
		        }
		        $("#stationId").val(msg.substr(0,msg.length-1));
			}
			if($("#stationForm").valid() === true ){
				var formData = utilsAi.formArrayUtil($("#stationForm").serializeArray())[0];
				utilsAi.ajax({
					url:'/operator/relaStations2',
					type:'post',
					data:formData,
					success:function(data){
						utilsAi.alert('授权成功');
						$('#add-modal-table').modal('toggle');
						window.umauthor.relaStationTable.fnDraw();
					}
				});
			}
		}
		loadStation();
		var options = {
			orgId:'${umOperator.orgId }',
			type:'add'
		};
		umauthor.operatorObj.stationGrantFormInit(options);
	});
</script>										
