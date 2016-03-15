<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>岗位类型</title>

    <%-- css --%>
	<%@ include file="/WEB-INF/pages/common_page/css.jsp" %>
	<%-- css --%>
	<%-- 需要树加载ztree --%>
	<%-- <link rel="stylesheet" href="${resourcePath}/ace/assets/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" /> --%>
	<%-- 需要树加载ztree --%>
</head>
<body class="no-skin">

<%-- header --%>
<%@ include file="/WEB-INF/pages/common_page/header.jsp" %>
<%-- header --%>

<div class="main-container" id="main-container">
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
							<li class="active">岗位类型</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<div class="row">
						<div class="col-md-12">			
							<%-- 页面内容 --%>
							<div class="table-header">
								岗位类型查询
							</div>
							<div class="table-responsive">
							<div class="panel panel-default">
						  	<div class="panel-body">
							<form class="form-horizontal" role="form" id="stationtypeSearchForm" method="post">
							<div class="form-group">
								<label class="control-label col-md-1 col-xs-12 col-sm-3 no-padding-right" for="stationtypeName">名称:</label>
							
								<div class="col-xs-12 col-sm-9 col-md-3">
									<div class="clearfix">
										<input type="text" id="stationtypeName" name="stationtypeName" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
									</div>
								</div>
								
								<label class="control-label col-md-1 col-xs-12 col-sm-3 no-padding-right" for="stationtypeCode">编码:</label>
							
								<div class="col-xs-12 col-sm-9 col-md-2">
									<div class="clearfix">
										<input type="text" id="stationtypeCode" name="stationtypeCode" placeholder="" value="" class="col-xs-12 col-sm-4 col-md-12" />
									</div>
								</div>
								
								<label class="control-label col-md-1 col-xs-12 col-sm-3 no-padding-right" for="stationtypeState">状态:</label>
							
								<div class="col-xs-12 col-sm-9 col-md-2">
									<div class="clearfix">
										<select class="form-control" id="stationtypeState" name="stationtypeState" class="col-xs-12 col-sm-4 col-md-12">
									         <option value="1">有效</option>
									         <option value="0">无效</option>
								      	</select>
									</div>
								</div>
								
								<div class="col-xs-12 col-sm-9 col-md-2" style="padding-right: 5px;">
									<button type="button" class="btn btn-sm btn-success" id="searchFormSubmit">
										查询
										<i class="icon-arrow-right icon-on-right bigger-110"></i>
									</button>
									<button class="btn btn-sm btn-danger" id="resetBtn" type="button">
										重置
									</button>			
								</div>
								</div>
							</form>
							</div>
							</div>
							</div>
							</div>
							<div class="col-md-12">
							<div class="table-responsive">
								<table id="stationtypeTable" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<th class="center">
												<label>
													<input type="checkbox" class="ace" />
													<span class="lbl"></span>
												</label>
											</th>
											<th>岗位类型编码</th>
											<th>岗位类型名称</th>
											<th>状态</th>
											<th style="width: 360px;">备注</th>
											<th>编辑</th>
										</tr>
									</thead>
								</table>
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
					岗位类型
				</div>
			</div>

			<div class="modal-body no-padding" id="editContent">
			<!-- 弹出框内容 -->
			</div>

			<!-- <div class="modal-footer no-margin-top">
				<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
					<i class="ace-icon fa fa-remove"></i>
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
<%-- <script src="${resourcePath}/ace/assets/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script> --%>
<%-- 需要树加载ztree --%>

<script type="text/javascript">
	jQuery(function($) {
		
	});
</script>

<%-- 业务js导入,使用businessPath路径指向/static/business --%>
<script src="${businessPath}/stationtype/index.js"></script>
<%-- 业务js导入 --%>



</body>
</html>