<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<link rel="stylesheet" href="${resourcePath}/ace/assets/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<div class="widget-box">
	<div class="widget-body">
		<div class="widget-main">
			<div id="fuelux-wizard">
				<div>
					<!-- #section:plugins/fuelux.wizard.steps -->
					<ul class="steps">
						<li data-step="1" class="active">
							<span class="step">1</span>
							<span class="title">选择岗位</span>
						</li>

						<li data-step="2">
							<span class="step">2</span>
							<span class="title">其它信息</span>
						</li>
					</ul>

					<!-- /section:plugins/fuelux.wizard.steps -->
				</div>
				
				<hr>
				<div class="step-content row-fluid position-relative" id="step-container">
					<form class="form-horizontal" role="form" id="stationForm" method="post" style="padding-top:10px;">
	 					<input type="hidden" id="oldStationIds" name="oldStationIds" value="${oldStationIds }"/>
	 					<input type="hidden" id="stationIds" name="stationIds" value=""/>
	 					<input type="hidden" id="toOperatorId" name="toOperatorId" value="${toOperatorId }"/>
						<div class="step-pane active" id="step1" data-step="1">
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
		
						<div class="step-pane" id="step2" style="margin-top:-20px;" data-step="2">
							<div class="form-group">
								<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="stationName">授权操作员:</label>
								<div class="col-xs-12 col-sm-9 col-md-4">
									<div class="clearfix">
										<input type="text" id="grantOperator" name="grantOperator" placeholder="" value="${umOperator.operatorName }" readonly="readonly" class="col-xs-12 col-sm-4 col-md-12" />
									</div>
								</div>
							</div>
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
					</form>
				</div>
			</div>
			<hr>
			<div class="wizard-actions">
				<!-- #section:plugins/fuelux.wizard.buttons -->
				<button class="btn btn-prev">
					<i class="ace-icon fa fa-arrow-left"></i>
					上一页
				</button>

				<button class="btn btn-success btn-next" data-last="完成">
					下一页
					<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
				</button>

				<!-- /section:plugins/fuelux.wizard.buttons -->
			</div>
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
		
		$('#fuelux-wizard').ace_wizard({}).on('actionclicked.fu.wizard' , function(e, info){
			if(info.step == 0) {
				if($("#stationTable input[type='checkbox'][name='checkId']:checked").length() == 0)return false;
			}else if(info.step == 1){
				if(!$('#stationForm').valid()) return false;
			}
		}).on('finished.fu.wizard', function(e) {
			
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
		        $("#stationIds").val(msg.substr(0,msg.length-1));
			}
			submit();//提交表单
		}).on('stepclick.fu.wizard', function(e){
			//return false;//prevent clicking on steps
		});
	
		
	
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
				 //"iDisplayLength": 5,
					 hideTitle:true,
					 fnAfterInitFinish:function(){
						//修改用
						var oldStationIds = $("#oldStationIds").val();
						if(oldStationIds){
							var relaIds = oldStationIds.split(",");
							$("#stationTable input[type='checkbox'][name='checkId']").each(function() {
								var $el = $(this);
								for (var i = 0; i < relaIds.length; i++) {
									if($el.data('stationid') == relaIds[i] || $el.data('stationid') == Number(relaIds[i])){
										$el.attr("checked","checked");
									}
								}
							});
						}
					 }
				}));
			}else{
				oTable1.fnDraw();
			}
		}
		
		//点击查询按钮
		$('#searchFormSubmit').click(function(){
			oTable1.fnDraw();
		});
		
		function submit(){
			if($("#stationForm").valid() === true ){
				var formData = utilsAi.formArrayUtil($("#stationForm").serializeArray())[0];
				utilsAi.ajax({
					url:'/operator/relaStations',
					type:'post',
					data:formData,
					success:function(data){
						utilsAi.alert('授权成功');
						$('#modal-table').modal('toggle');
					}
				});
			}
		}
		loadStation();
		var options = {
			orgId:'${umOperator.orgId }'
		};
		umauthor.operatorObj.stationGrantFormInit(options);
	});
</script>										
