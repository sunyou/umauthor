<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>后台管理首页</title>
	<style>
	ul, ol {
	    padding: 0;
	}
	</style>
    <%-- css --%>
	<%@ include file="/WEB-INF/pages/common_page/css.jsp" %>
	<link rel="stylesheet" href="${resourcePath}/scripts/lib/css/bootstrap-treeview.css" />
	<link rel="stylesheet" href="${resourcePath}/scripts/lib/zTree_v3/css/zTreeStyle/zTreeStyle.css" />

</head>
<body class="no-skin">

<%-- header --%>
<%@ include file="/WEB-INF/pages/common_page/header.jsp" %>
<%-- header --%>

<div class="main-container ace-save-state" id="main-container">
			<script type="text/javascript">
				try{ace.settings.loadState('main-container')}catch(e){}
			</script>

			<div class="main-container-inner">
				
				<%-- menu --%>
				<%@ include file="/WEB-INF/pages/common_page/menu.jsp" %>
				<%-- menu --%>

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
							<li class="active">菜单管理</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<div class="row">
							<!-- 左边树 -->
							<div class="col-md-3">
								<!-- <div class="table-header">
									菜单列表
								</div>
								<div id="treeview" class=""></div> -->
								
								<div class="widget-box widget-color-blue">
									<div class="widget-header">
										<h5 class="widget-title">菜单列表</h5>
										
										<div class="widget-toolbar">
											<a href="#" data-action="collapse">
												<i class="ace-icon fa fa-chevron-up"></i>
											</a>
										</div>
									</div>
									<div class="widget-body" style="min-height: 400px;">
										<div class="widget-main">
											<div>
												<div class="form-group">
											    <label for="orgTypeTree">系统切换</label>
											    <select class="form-control input-sm" id="system" name="system">
													<c:forEach var="item" items="${syslist }" varStatus="status">
														<option value="${item.systemId }">${item.systemName }</option>
													</c:forEach>
												</select>
											  </div>
											</div>
											<ul id="groupTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
							<!-- 右边框 -->
							
							<div class="col-md-9">
								<!-- <div class="page-header">
									<button class="btn btn-primary">新增</button>
									<button class="btn btn-warning">修改</button>
									<button class="btn btn-danger">删除</button>
								</div> -->
								<div class="row-fluid">
									<div class="span12">
										<div class="widget-box">
											<div id="profile4" class="tab-pane">
												<div class="table-header">
													菜单详情
												</div>
												<form class="form-horizontal" role="form" id="menuForm" method="post" style="padding-top:10px;">
													<input type="hidden" id="parentMenuId" name="parentMenuId" value="" />
													<input type="hidden" id="menuId" name="menuId" value="" />
													<input type="hidden" id="nodeLevel" value="" />
													<input type="hidden" id="op" value="" />
													<div class="form-group" id="parentMenuNameDiv">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="parentMenuName">父菜单名称:</label>
														<div class="col-xs-12 col-sm-9 col-md-4">
															<div class="clearfix">
																<input type="text" id="parentMenuName" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" disabled="disabled"/>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="systemId">所属系统:</label>
														<div class="col-xs-12 col-sm-9 col-md-4">
															<select class="form-control input-sm" id="systemId" name="systemId">
																<c:forEach var="item" items="${syslist }" varStatus="status">
																	<option value="${item.systemId }">${item.systemName }</option>
																</c:forEach>
															</select>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="menuType">菜单类型:</label>
														<div class="col-xs-12 col-sm-9 col-md-1">
															<div class="clearfix">
																<select id="menuType" name="menuType">
																	<option value="3">目录</option>
																	<option value="1">菜单</option>
																	<option value="2">功能点</option>
																</select>
															</div>
														</div>
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="menuState">菜单状态:</label>
														<div class="col-xs-12 col-sm-9 col-md-2">
															<div class="clearfix">
																<select id="menuState" name="menuState">
																	<option value="1">有效</option>
																	<option value="0">无效</option>
																</select>
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="menuName">菜单名称:</label>
														<div class="col-xs-12 col-sm-9 col-md-4">
															<div class="clearfix">
																<input type="text" id="menuName" name="menuName" placeholder="请输入菜单名称" class="col-xs-12 col-sm-6 col-md-12" value="" />
															</div>
														</div>
													</div>
													<div class="form-group" id="menuUrlDiv">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="menuUrl">菜单链接:</label>
														<div class="col-xs-12 col-sm-9 col-md-4">
															<div class="clearfix">
																<input type="text" id="menuUrl" name="menuUrl" placeholder="请输入菜单链接" class="col-xs-12 col-sm-6 col-md-12" value="" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="menuCode">菜单编码:</label>
														<div class="col-xs-12 col-sm-9 col-md-4">
															<div class="clearfix">
																<input type="text" id="menuCode" name="menuCode" placeholder="请输入菜单编码" class="col-xs-12 col-sm-6 col-md-12" value="" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="menuImage">菜单图片:</label>
														<div class="col-xs-12 col-sm-9 col-md-4">
															<div class="clearfix">
																<input type="text" id="menuImage" name="menuImage" placeholder="请输入菜单图片" class="col-xs-12 col-sm-6 col-md-12" value="" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="menuOrder">菜单排序:</label>
														<div class="col-xs-12 col-sm-9 col-md-4">
															<div class="clearfix">
																<input type="text" id="menuOrder" name="menuOrder" placeholder="请输入数字顺序" class="col-xs-12 col-sm-6 col-md-12" value="" />
															</div>
														</div>
													</div>
													<div class="form-group">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="menuDesc">菜单描述:</label>
														<div class="col-xs-12 col-sm-9 col-md-4">
															<div class="clearfix">
																<textarea id="menuDesc" name="menuDesc" rows="5" cols="31"></textarea>
															</div>
														</div>
													</div>
													<div class="form-group">
														<div class="col-xs-12 col-sm-9 col-md-6 col-md-offset-3">
															<div class="clearfix">
																<button id="addBtn" class="btn btn-primary">新增</button>
																<button id="modBtn"class="btn btn-warning">修改</button>
																<button id="delBtn"class="btn btn-danger">删除</button>
																<button id="ordBtn"class="btn btn-success">排序</button>
																<button id="submitBtn"class="btn btn-success" style="display:none;">确定</button>
																<button id="cancelBtn"class="btn btn-warning" style="display:none;">取消</button>
															</div>
														</div>
													</div>
												</form>
											</div>
										</div>
									</div>
								</div>
							</div>
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
					菜单排序
				</div>
			</div>

			<div class="modal-body no-padding" id="order-modal">
			</div>

			<!-- <div class="modal-footer no-margin-top">
				<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
					<i class="icon-remove"></i>
					关闭
				</button>
			</div> -->
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- PAGE CONTENT ENDS -->

<%-- confirm框 --%>
<div id="dialog-confirm" class="hide">
	<div class="alert alert-info bigger-110" id="confirm-content">
		
	</div>

</div>

<%-- script --%>
<%@ include file="/WEB-INF/pages/common_page/js.jsp" %>
<%-- script --%>

<%-- 需要树加载ztree --%>
<%-- <script src="${resourcePath}/scripts/lib/zTree_v3/js/jquery.ztree.core-3.5.js"></script> --%>
<%-- 需要树加载ztree --%>
<script type="text/javascript" src="${resourcePath }/scripts/lib/bootstrap-treeview.js"></script>
<script src="${resourcePath}/scripts/lib/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script src="${resourcePath}/scripts/common/select-tree.js"></script>
<script type="text/javascript" src="${businessPath }/menu/menu.js"></script>
<script type="text/javascript">
	jQuery(function($) {
		
	});
</script>

<%-- 业务js导入,使用businessPath路径指向/static/business --%>
<%-- <script src="${businessPath}/org/index.js"></script> --%>
<%-- 业务js导入 --%>



</body>
</html>