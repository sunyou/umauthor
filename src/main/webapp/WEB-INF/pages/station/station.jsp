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
	<%-- css --%>
	<%-- 需要树加载ztree --%>
	<%-- <link rel="stylesheet" href="${resourcePath}/ace/assets/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" /> --%>
	<link rel="stylesheet" href="${resourcePath }/styles/sweetalert.css"/>
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
							<li class="active">岗位管理</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<div class="col-md-12">
							<div class="table-responsive">
								<div class="widget-box widget-color-blue">
									<div class="widget-header">
										<h5 class="widget-title">岗位列表</h5>
								
										<div class="widget-toolbar">
											<a href="#" data-action="collapse">
												<i class="ace-icon fa fa-chevron-up"></i>
											</a>
										</div>
									</div>
								
									<div class="widget-body">
										<div class="widget-main">
											<form class="form-horizontal" role="form" id="searchOrgForm" method="post">
												<div class="form-group" style="height:20px;">
													<label class="control-label col-md-1 col-xs-12 col-sm-3 no-padding-right" for="stationName">岗位名称:</label>
													<div class="col-xs-12 col-sm-9 col-md-2">
														<input type="text" id="stationName" name="stationName" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
													</div>
													<label class="control-label col-md-1 col-xs-12 col-sm-3 no-padding-right" for="groupName">岗位分类:</label>
													<div class="col-xs-12 col-sm-9 col-md-2">
														<input type="text" id="groupName" name="groupName" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
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
								</div>
								<table id="stationTable" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">
												<label>
													<input type="checkbox" class="ace" />
													<span class="lbl"></span>
												</label>
											</th>
											<th class="center">岗位名称</th>
											<th class="center">岗位描述</th>
											<th class="center" style="width: 150px;">创建时间</th>
											<th class="center" style="width: 100px;">岗位状态</th>
											<th class="center" style="width: 100px;">岗位分类</th>
										</tr>
									</thead>
								</table>
							</div>
						</div>
					</div><!-- /.page-content -->
					</div><!-- /.main-content-inner -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
<%-- modal框 --%>
<div id="modal-table" class="modal fade" tabindex="-1">
	<div class="modal-dialog" style="width:800px;">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
					</button>
					<span id="popTitle">新增</span>
				</div>
			</div>

			<div class="modal-body no-padding" id="editContent">
			<!-- 弹出框内容 -->
			</div>

			<div class="modal-footer no-margin-top"  style="text-align: center;">
			    
			</div>
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
<%-- <script src="${resourcePath}/ace/assets/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script> --%>
<%-- 需要树加载ztree --%>
<script type="text/javascript" src="${businessPath }/station/station.js"></script>
<script type="text/javascript">
	jQuery(function($) {
		
	});
</script>
<script type="text/javascript" src="${resourcePath }/scripts/common/sweetalert.min.js"></script>

</body>
</html>
