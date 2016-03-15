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
							<span class="title">岗位类型</span>
						</li>

						<li data-step="2">
							<span class="step">2</span>
							<span class="title">关联岗位</span>
						</li>
					</ul>
				</div>
				
				<hr>
				<div class="step-content row-fluid position-relative" style="width: 100%;" id="step-container">
						<div class="step-pane active" style="margin-top:-20px;width: 100%;"  data-step="1">
						<div>
							     <table id="pathcstationtypeTable1"  style="width: 100%;" class="table table-striped table-bordered table-hover"  >
								<thead>
									<tr>
										<th class="center">
											<label>
												<input type="checkbox" class="ace" />
												<span class="lbl"></span>
											</label>
										</th>
										<th >岗位类型编码</th>
										<th >岗位类型名称</th>
										<th>状态</th>
									</tr>
								</thead>
							</table>
							</div>
						</div>
		
						<div class="step-pane" style="margin-top:-20px;" data-step="2">
						        <table id="batchStationTable1" class="table table-striped table-bordered table-hover">
										<thead>
											<tr>
												<th class="center">
													<label>
														<input type="checkbox" class="ace" />
														<span class="lbl"></span>
													</label>
												</th>
												<th class="hidden-480">岗位名称</th>
												<th class="hidden-480">岗位描述</th>
												<th class="hidden-480" >创建时间</th>
												<th class="hidden-480" >岗位状态</th>
												<th class="hidden-480" >岗位分类</th>
											</tr>
										</thead>
									</table>
									<div id="tipMsg" style="text-align: center;">
									  <span style="color: red;">请返回上一页选择菜单</span>
									</div>
						</div>
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
<script type="text/javascript" src="${businessPath }/station/batchStationTypeRelate.js"></script>

