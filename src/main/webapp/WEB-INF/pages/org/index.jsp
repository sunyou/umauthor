<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>后台管理首页</title>

    <!-- header -->
	<%@ include file="/WEB-INF/pages/common_page/css.jsp" %>
	<!-- header -->
	<link rel="stylesheet" href="${resourcePath}/ace/assets/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
	
</head>
<body class="no-skin">

<!-- header -->
<%@ include file="/WEB-INF/pages/common_page/header.jsp" %>
<!-- header -->

<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<div class="main-container-inner">
				
				<!-- menu -->
				<%@ include file="/WEB-INF/pages/common_page/menu.jsp" %>
				<!-- menu -->

				<div class="main-content">
					<div class="main-content-inner">
					<div class="breadcrumbs ace-save-state" id="breadcrumbs">
						
						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">主页</a>
							</li>

							<li>
								<a href="#">系统管理</a>
							</li>
							<li class="active">组织管理</li>
						</ul><!-- .breadcrumb -->

					</div>

					<div class="page-content">
						
						<div class="row">
							<!-- tree start -->
							<div class="col-sm-3">
								<div class="widget-box widget-color-blue">
									<div class="widget-header">
										<h4 class="widget-title lighter smaller">组织结构</h4>
									</div>
									<div class="widget-body">
										<div class="widget-main padding-8">
											<!-- <div class="center">
												<a class="btn btn-success btn-sm" id="searchOrg"><i class="glyphicon glyphicon-search"></i>查询</a>
												<a class="btn btn-primary btn-sm" id="addOrg"><i class="glyphicon glyphicon-plus"></i>新增</a>
												<a class="btn btn-danger btn-sm" id="deleteOrg"><i class="glyphicon glyphicon-trash"></i>删除</a>
											</div> -->
											<div>
												<div class="form-group">
											    <label for="orgTypeTree">组织类型</label>
											    <select class="form-control input-sm" id="orgTypeTree" name="orgTypeTree">
													<option value="">选择组织类型</option>
													<c:forEach items="${orgTypes}" var="orgType">
													 <option value="${orgType.itemKey}">${orgType.itemValue}</option>
													</c:forEach>
												</select>
											  </div>
											</div>
											<ul id="groupTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
							<!-- tree end -->
							<!-- tab start -->
							<div class="col-sm-9">
								<div class="tabbable">
									<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
										<li class="active">
											<a data-toggle="tab" href="#groupInfo" id="orgInfoTab">组织信息</a>
										</li>

										<li>
											<a data-toggle="tab" href='#profile4' id="orgPermissionTab">岗位授权情况</a>
										</li>

										<li>
											<a data-toggle="tab" href="#dropdown14" id="operatorListTab">下属人员</a>
										</li>
										<li>
											<a data-toggle="tab" href="#orgListInfo" id="orgListTab">下属组织信息</a>
										</li>
										
									</ul>

									<div class="tab-content">
										<div id="groupInfo" class="tab-pane in active">
											
										</div>
										<div id="orgListInfo" class="tab-pane">
											<div class="table-responsive">
											<div class="widget-box widget-color-blue">
												<div class="widget-header">
													<h5 class="widget-title">下属组织列表</h5>
											
													<div class="widget-toolbar">
														<a href="#" data-action="collapse">
															<i class="ace-icon fa fa-chevron-up"></i>
														</a>
													</div>
												</div>
											
												<div class="widget-body">
													<div class="widget-main">
														<form class="form-horizontal" role="form" id="searchOrgForm" method="post">
															<div class="form-group">
																<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="provinceCodeOrgS">所在行政区:</label>
																<div class="col-xs-12 col-sm-9 col-md-9">
																	<div class="clearfix">
																		<div class="clearfix">
																			<select id="provinceCodeOrgS" name="provinceCodeOrgS" data-target-id='cityCodeOrgS' data-result-id="areaId" data-result-value="areaName" data-ajax-url='/org/getAreaNodes' >
																				<option value="">请选择省份</option>
																				<c:forEach var="area" items="${provices}">
																					<option value="${area.areaId}">${area.areaName}</option>
																				</c:forEach>
																			</select>
																			<select id="cityCodeOrgS" name="cityCodeOrgS" data-target-id='districtCodeOrgS' data-result-id="areaId" data-result-value="areaName" data-ajax-url='/org/getAreaNodes' >
																				<option value="">请选择地市</option>
																			</select>
																			<select id="districtCodeOrgS" name="districtCodeOrgS" >
																				<option value="">请选择区县</option>
																			</select>
																		</div>
																	</div>
																</div>
															</div>
															<div class="form-group">
																<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgNameOrgS">组织名称:</label>
															
																<div class="col-xs-12 col-sm-9 col-md-4">
																	<div class="clearfix">
																		<input type="text" id="orgNameOrgS" name="orgNameOrgS" placeholder="" class="form-control" value="" />
																	</div>
																</div>
																<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgTypeOrgS">组织类型:</label>
															
																<div class="col-xs-12 col-sm-9 col-md-4">
																	<div class="clearfix">
																		<select id="orgTypeOrgS" name="orgTypeOrgS" >
																			<option value="">请选择组织类型</option>
																			<c:forEach items="${orgTypes}" var="orgType">
																			 <option value="${orgType.itemKey}">${orgType.itemValue}</option>
																			</c:forEach>
																		</select>
																	</div>
																</div>
															</div>
															<div class="form-group">
																<div class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right"></div>
															
																<div class="col-xs-12 col-sm-9 col-md-4">
																	<div class="clearfix">
																		<label class="control-label" style="text-align: left;" for="isAllOrg"><input type="checkbox" id="isAllOrg" name="isAllOrg" value="1"/><span class="checkbox-title">是否包含下属组织</span></label>
																	</div>
																</div>
																<div class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right"></div>
																<div class="col-xs-12 col-sm-9 col-md-6">
																	<div class="clearfix text-center">
																		<button type="button" class="btn btn-xs btn-success" id="searchOrgSubmit">
																			查询
																			<i class="icon-arrow-right icon-on-right bigger-110"></i>
																		</button>
																	</div>
																</div>
															</div>
														</form>
													</div>
												</div>
											</div>
											<table id="orgTable" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th class="hidden-480">组织名称</th>
														<th>
															<i class="icon-time bigger-110 hidden-480"></i>
															机构代码
														</th>
														<!-- <th>编辑</th> -->
													</tr>
												</thead>
											</table>
											</div>
										</div>
										<div id="profile4" class="tab-pane">
											<div class="table-header">
												组织岗位授权列表
											</div>
											<div class="table-responsive">
											<table id="permissionTable" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th class="hidden-480">用户</th>
														<th>
															<i class="icon-time bigger-110 hidden-480"></i>
															组织
														</th>
														<th>
															岗位
														</th>
														<th>编辑</th>
													</tr>
												</thead>
											</table>
											</div>
										</div>

										<div id="dropdown14" class="tab-pane">
											<%-- 用户列表 --%>
											<%@ include file="/WEB-INF/pages/operator/list.jsp" %>
										</div>
									</div>
								</div>
							</div>
							<!-- tab end -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
					</div><!-- /.main-content-inner -->
				</div><!-- /.main-content -->

			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->

<%-- modal框 --%>
<div id="modal-table" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
					</button>
					标题
				</div>
			</div>

			<div class="modal-body no-padding">
				<div id="modalContent"></div>
			</div>

			<div class="modal-footer no-margin-top">
				<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
					<i class="ace-icon fa fa-remove"></i>
					关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- PAGE CONTENT ENDS -->

<div id="dialog-confirm" class="hide">
	<div class="alert alert-info bigger-110" id="confirm-content">
		
	</div>
</div>

<!-- script -->
<%@ include file="/WEB-INF/pages/common_page/js.jsp" %>
<!-- script -->

<script src="${resourcePath}/ace/assets/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>

<script src="${resourcePath}/scripts/common/select-tree.js"></script>

<%-- 业务js导入,使用businessPath路径指向/static/business --%>
<script src="${businessPath}/org/org.js"></script>
<%-- 业务js导入 --%>

<script type="text/javascript">
	
	jQuery(function($) {
		var orgInfo = {
			orgId:'${orgId}',
			orgName:'${orgName}',
			isParent:true
		};
		umauthor.orgObj = new umauthor.OrgObj({
			userOrg:orgInfo
		});
	});
</script>

</body>
</html>