<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<div class="table-responsive" id="operatorListInfo">
<div class="widget-box widget-color-blue">
	<div class="widget-header">
		<h5 class="widget-title">用户列表</h5>

		<div class="widget-toolbar">
			<a href="#" data-action="collapse">
				<i class="ace-icon fa fa-chevron-up"></i>
			</a>
		</div>
	</div>

	<div class="widget-body">
		<div class="widget-main">
			<form class="form-horizontal" role="form" id="searchForm" method="post">
				<div class="form-group">
					<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="operatorCodeS">工号:</label>
				
					<div class="col-xs-12 col-sm-9 col-md-4">
						<div class="clearfix">
							<input type="text" id="operatorCodeS" name="operatorCodeS" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
						</div>
					</div>
					<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="operatorNameS">姓名:</label>
				
					<div class="col-xs-12 col-sm-9 col-md-4">
						<div class="clearfix">
							<input type="text" id="operatorNameS" name="operatorNameS" placeholder="" value="" class="col-xs-12 col-sm-4 col-md-12" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="telNoS">手机号码:</label>
				
					<div class="col-xs-12 col-sm-9 col-md-4">
						<div class="clearfix">
							<input type="text" id="telNoS" name="telNoS" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
						</div>
					</div>
					<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for=""orgIdSTree"">组织:</label>
				
					<div class="col-xs-12 col-sm-9 col-md-4">
						<div class="clearfix">
							<input type="text" id="orgIdSTree" name="orgIdSTree" placeholder="" value="" class="col-xs-12 col-sm-4 col-md-12" />
							<input type="hidden" id="orgIdS" name="orgIdS"/>
						</div>
					</div>
				</div>
				<div class="form-group">
					<div class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right"></div>
					<div class="col-xs-12 col-sm-9 col-md-4">
						<div class="clearfix">
							<label class="control-label" style="text-align: left;" for="isAllOperator"><input type="checkbox" id="isAllOperator" name="isAllOperator" value="1"/><span class="checkbox-title">是否包含下属人员</span></label>
						</div>
					</div>
					<div class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right"></div>
					<div class="col-xs-12 col-sm-9 col-md-6">
						<div class="clearfix text-center">
							<button type="button" class="btn btn-xs btn-success" id="searchFormSubmit">
								查询
								<i class="icon-arrow-right icon-on-right bigger-110"></i>
							</button>
						</div>
					</div>
				</div>
				<!-- <div class="form-group">
					<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="createTime">创建时间:</label>
				
					<div class="col-xs-12 col-sm-9 col-md-5">
						<div class="input-group">
							<span class="input-group-addon">
								<i class="icon-calendar bigger-110"></i>
							</span>

							<input class="form-control" type="text" name="createTime" id="createTime" readonly="readonly"/>
						</div>
					</div>
					
				</div> -->
			</form>
		</div>
	</div>
</div>


<table id="operatorTable" class="table table-striped table-bordered table-hover">
	<thead>
		<tr>
			<th class="center">
				<label>
					<input type="checkbox" class="ace" />
					<span class="lbl"></span>
				</label>
			</th>
			<th>员工姓名</th>
			<th>员工工号</th>
			<th class="hidden-480">联系电话</th>
			<th>
				<i class="ace-icon fa fa-time bigger-110 hidden-480"></i>
				所属组织
			</th>
			<th>编辑</th>
		</tr>
	</thead>
</table>
</div>
<%-- 业务js导入,员工列表要用的业务 --%>
<script src="${businessPath}/operator/operator.js"></script>
<%-- 业务js导入 --%>