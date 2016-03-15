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
							<span class="title">岗位信息</span>
						</li>

						<li data-step="2">
							<span class="step">2</span>
							<span class="title">关联菜单</span>
						</li>

						<li data-step="3">
							<span class="step">3</span>
							<span class="title">关联岗位类型</span>
						</li>
					</ul>

					<!-- /section:plugins/fuelux.wizard.steps -->
				</div>
				
				<hr>
				<div class="step-content pos-rel" id="step-container">
					<form class="form-horizontal" role="form" id="stationForm" method="post" style="padding-top:10px;">
						<input type="hidden" id="type" value="${type }"/>
	 					<input type="hidden" name="stationId" value="${station.stationId }"/>
	 					<input type="hidden" id="oldRelaMenuIds" name="oldRelaMenuIds" value="${relaMenuIds }"/>
	 					<input type="hidden" id="oldRelaTypeIds" name="oldRelaTypeIds" value="${relaTypeIds }"/>
	 					<input type="hidden" id="relaMenuIds" name="relaMenuIds" value=""/>
	 					<input type="hidden" id="relaTypeIds" name="relaTypeIds" value=""/>
						<div class="step-pane active" id="step1" data-step="1">
							<div class="form-group">
								<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="stationName">岗位名称:</label>
								<div class="col-xs-12 col-sm-9 col-md-4">
									<div class="clearfix">
										<input type="text" id="stationName" name="stationName" placeholder="请输入岗位名称" value="${station.stationName }" class="col-xs-12 col-sm-6 col-md-12" value="" />
									</div>
								</div>
								<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="stationState">岗位状态:</label>
								<div class="col-xs-12 col-sm-9 col-md-1">
									<div class="clearfix">
										<select id="stationState" name="stationState" value="${station.stationState }">
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
									</div>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="groupId">岗位分类:</label>
								<div class="col-xs-12 col-sm-9 col-md-4">
									<div class="clearfix">
										<select id="groupId" name="groupId">
											<option value="">--------请选择岗位分类--------</option>
											<c:forEach var="item" items="${groupList }" varStatus="status">
												<option value="${item.groupId }" <c:if test="${item.groupId == station.groupId}">selected="selected"</c:if> >${item.groupName }</option>
											</c:forEach>
										</select>
									</div>
								</div>
								<%-- <label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="canGrant">可再授权:</label>
								<div class="col-xs-12 col-sm-9 col-md-2">
									<div class="clearfix">
										<select id="canGrant" name="canGrant" value="${station.canGrant }">
											<option value="1">是</option>
											<option value="0">否</option>
										</select>
									</div>
								</div> --%>
							</div>
							<div class="form-group">
								<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="stationDesc">岗位描述:</label>
								<div class="col-xs-12 col-sm-9 col-md-4">
									<div class="clearfix">
										<textarea id="stationDesc" name="stationDesc" placeholder="请输入岗位描述" rows="5" cols="40">${station.stationDesc }</textarea>
									</div>
								</div>
							</div>
						</div>
		
						<div class="step-pane" id="step2" style="margin-top:-20px;" data-step="2">
							<ul id="groupTree" class="ztree"></ul>
						</div>
						<div class="step-pane" id="step3">
							<!-- <div class="widget-box" style="margin-top:-20px;">
								<div class="widget-header header-color-blue">
									<h5>岗位类型列表</h5>
									<div class="widget-toolbar">
										<a href="#" data-action="collapse">
											<i class="icon-chevron-up"></i>
										</a>
									</div>
								</div>
								<div class="widget-body">
									<div class="widget-main">
										<form class="form-horizontal" role="form" id="searchOrgForm" method="post">
											<div class="form-group" style="height:20px;">
												<label class="control-label col-md-1 col-xs-12 col-sm-3 no-padding-right" for="stationtypeName">名称:</label>
												<div class="col-xs-12 col-sm-9 col-md-2">
													<input type="text" id="stationtypeName" name="stationtypeName" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
												</div>
												<label class="control-label col-md-1 col-xs-12 col-sm-3 no-padding-right" for="stationtypeCode">编码:</label>
												<div class="col-xs-12 col-sm-9 col-md-2">
													<input type="text" id="stationtypeCode" name="stationtypeCode" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
												</div>
												<label class="control-label col-md-1 col-xs-12 col-sm-3 no-padding-right" for="stationtypeState">状态:</label>
												<div class="col-xs-12 col-sm-9 col-md-2">
													<select class="form-control" id="stationtypeState" name="stationtypeState" class="col-xs-12 col-sm-4 col-md-12">
												         <option value="1">有效</option>
												         <option value="0">无效</option>
											      	</select>
												</div>
												<div class="col-xs-12 col-sm-9 col-md-1">
													<button type="button" class="btn btn-sm btn-success" id="searchFormSubmit">
														查询<i class="icon-arrow-right icon-on-right bigger-110"></i>
													</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div> -->
							<table id="stationtypeTable" class="table table-striped table-bordered table-hover">
								<thead>
									<tr>
										<th class="center">
											<label>
												<input type="checkbox" class="ace" />
												<span class="lbl"></span>
											</label>
										</th>
										<th class="hidden-480">岗位类型编码</th>
										<th class="hidden-480">岗位类型名称</th>
										<th class="hidden-480">状态</th>
									</tr>
								</thead>
							</table>
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
				if(!$('#stationForm').valid()) return false;
			}else if(info.step == 1){
				loadTree();
			}else if(info.step == 2){
				loadStationType();
			}
		}).on('finished.fu.wizard', function(e) {
			
			//第一步验证
			if(!$('#stationForm').valid()) return false;
			//第二步验证
			var treeObj = $.fn.zTree.getZTreeObj("groupTree");
	        var nodes = treeObj.getCheckedNodes(true);
	        if(nodes.length > 0){
		        var msg = "";
		        for (var i = 0; i < nodes.length; i++) {
		        	//if(nodes[i].isParent)continue;
		            msg += nodes[i].id+",";
		        }
		        $("#relaMenuIds").val(msg.substr(0,msg.length-1));
	        }
			//第三部验证
			var items = [];
			$("#stationtypeTable input[type='checkbox'][name='checkId']:checked").each(function() {
				var $el = $(this);
				items.push($el.data('stationtypeid'));
			});
			if(items.length > 0){
				var msg = "";
		        for (var i = 0; i < items.length; i++) {
		            msg += items[i]+",";
		        }
		        $("#relaTypeIds").val(msg.substr(0,msg.length-1));
			}
			submit();//提交表单
		}).on('stepclick.fu.wizard', function(e){
			//return false;//prevent clicking on steps
		});
	
		//ztree 处理 start
		var zNodes =[
			{ id:0, pId:0, name:'系统菜单列表',isParent:true}
		];
		var setting = {
			check:{
		        enable: true
		    },
			view: {
				selectedMulti: true
			},
			data:{
				dataTransform:function(dataArr){
					var newData = [];
					var oldRelaMenuIds = $("#oldRelaMenuIds").val();
					for(var i = 0;i< dataArr.length;i++){
						var checked = false;
						if(oldRelaMenuIds && oldRelaMenuIds.indexOf(dataArr[i].menuId) > -1){
							checked = true;
						}
						var node = {
							id:dataArr[i].menuId,
							name:dataArr[i].menuName,
							pId:dataArr[i].parentMenuId,
							isParent:dataArr[i].isParent,
							checked:checked
						};
						newData.push(node);
					}
					return newData;
				}
			},
			async: {
				enable: true,
				dataType: 'json',
				url:"/menu/tree2",
				autoParam:["id=parentMenuId", "name=n", "level=lv"]
			},
			callback: {
				beforeClick: beforeClick
			}
		};

		function beforeClick(treeId, node) {
			//console.log(node);
		}
		
		function refleshTree(parentMenuId){
			var nodesEdit = groupTree.getNodeByParam('id',parentMenuId);
			groupTree.reAsyncChildNodes(nodesEdit, 'refresh', false);
		}
		
		var groupTree = null;
		function loadTree(){
			groupTree = $.fn.zTree.init($("#groupTree"), setting, zNodes);
			var nodes = groupTree.getNodeByParam('id','0');
			groupTree.expandNode(nodes,true,false,false,false); 
		}
	
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
		var $operatorTabEl = $('#stationtypeTable');
		var oTable1 = null;
		
		function loadStationType(){
			if(oTable1 == null){
				oTable1 = $operatorTabEl.dataTable(utilsAi.dataTableInit({
					id:'stationtypeTable',
					checkId: 'stationtypeId',
					checkNames:['stationtypeId'],
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
					    }
					 ],
					 hideTitle:true,
					 fnAfterInitFinish:function(){
						//修改用
						var oldRelaTypeIds = $("#oldRelaTypeIds").val();
						if(oldRelaTypeIds){
							var relaIds = oldRelaTypeIds.split(",");
							$("#stationtypeTable input[type='checkbox'][name='checkId']").each(function() {
								var $el = $(this);
								for (var i = 0; i < relaIds.length; i++) {
									if($el.data('stationtypeid') == relaIds[i] || $el.data('stationtypeid') == Number(relaIds[i])){
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
			//oTable1.fnDraw();
		});
		
		function submit(){
			if($("#type").val() == "add"){
				if($("#stationForm").valid() === true ){
					var formData = utilsAi.formArrayUtil($("#stationForm").serializeArray())[0];
					utilsAi.ajax({
						url:'/station/presistent',
						type:'post',
						data:formData,
						success:function(data){
							utilsAi.alert(data.message);
							$('#modal-table').modal('toggle');
							window.umauthor.stationTable.fnDraw();
						}
					});
				}
			}else if($("#type").val() == "update"){
				if($("#stationForm").valid() === true ){
					var formData = utilsAi.formArrayUtil($("#stationForm").serializeArray())[0];
					utilsAi.ajax({
						url:'/station/presistent',
						data:formData,
						success:function(data){
							utilsAi.alert(data.message);
							$('#modal-table').modal('toggle');
							window.umauthor.stationTable.fnDraw();
						}
					});
				}
			}
		}
		
	});
</script>										
