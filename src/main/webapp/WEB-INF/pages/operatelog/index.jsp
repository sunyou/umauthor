<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>操作日志</title>

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
							<li class="active">操作日志</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<div class="row">
							<div class="col-md-12">							
							<%-- 页面内容 --%>
							<div class="table-header">
								操作日志查询
							</div>
							<div class="table-responsive">
							<div class="panel panel-default">
						  	<div class="panel-body">
							<form class="form-horizontal" role="form" id="searchForm" method="post">
							<div class="form-group">
								<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="operatorCode">工号:</label>
							
								<div class="col-xs-12 col-sm-9 col-md-3">
									<div class="clearfix">
										<input type="text" id="operatorCode" name="operatorCode" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
									</div>
								</div>
								
								<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="operatorType">操作类型:</label>
							
								<div class="col-xs-12 col-sm-9 col-md-3">
									<div class="clearfix">
										<input type="text" id="operatorType" name="operatorType" placeholder="" value="" class="col-xs-12 col-sm-4 col-md-12" />
									</div>
								</div>
							</div>
							<div class="form-group">	
								<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="">操作时间:</label>
							
								<div class="col-xs-12 col-sm-9 col-md-3">
									<div class="clearfix">
										<div class="input-group">
										<input class="form-control date-picker" id="operatorDateBegin" name="operatorDateBegin" type="text" data-date-format="yyyy-mm-dd" value=""/>
										<span class="input-group-addon">
											<i class="fa fa-calendar bigger-110"></i>
										</span>
										</div>
									</div>
								</div>
								<div class="col-xs-12 col-sm-9 col-md-3">
									<div class="clearfix">
										<div class="input-group">
										<input class="form-control date-picker" id="operatorDateEnd" name="operatorDateEnd" type="text" data-date-format="yyyy-mm-dd" value=""/>
										<span class="input-group-addon">
											<i class="fa fa-calendar bigger-110"></i>
										</span>
										</div>
									</div>									
								</div>
								<div class="col-xs-12 col-sm-9 col-md-2 col-md-offset-1" style="padding-right: 5px;">
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
								<table id="resultTable" class="table table-striped table-bordered table-hover">
									<thead>
										<tr>
											<!-- 
											<th class="center">
												<label>
													<input type="checkbox" class="ace" />
													<span class="lbl"></span>
												</label>
											</th>
											 -->
											<th>工号</th>
											<th>操作类型</th>
											<th style="width: 260px;">操作描述</th>
											<th>操作时间</th>
											
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
<script src="${businessPath}/operatelog/index.js"></script>
<%-- 业务js导入 --%>



</body>
</html>