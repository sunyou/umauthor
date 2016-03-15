<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Page title -->
    <title>后台管理首页</title>

    <%-- css --%>
	<%@ include file="/WEB-INF/pages/common_page/css.jsp" %>
	<%-- css --%>
	<%-- 需要树加载ztree --%>
	<%-- <link rel="stylesheet" href="${resourcePath}/ace/assets/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" /> --%>
	<%-- 需要树加载ztree --%>
</head>
<body>

<%-- header --%>
<%@ include file="/WEB-INF/pages/common_page/header.jsp" %>
<%-- header --%>

<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				
				<%-- menu --%>
				<%@ include file="/WEB-INF/pages/common_page/menu.jsp" %>
				<%-- menu --%>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>
						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
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
							
							
							<%-- 页面内容 --%>
							
							
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
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
				内容
			</div>

			<div class="modal-footer no-margin-top">
				<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
					<i class="icon-remove"></i>
					关闭
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- PAGE CONTENT ENDS -->

<%-- confirm框 --%>
<div id="dialog-confirm" class="hide">
	<div class="alert alert-info bigger-110" id="confirm-content">
		
	</div>

</div>

<%-- template --%>
<%@ include file="/WEB-INF/pages/template/common/tableEditButtonTemplate.jsp" %>
<%-- template --%>
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
<%-- <script src="${businessPath}/org/index.js"></script> --%>
<%-- 业务js导入 --%>



</body>
</html>