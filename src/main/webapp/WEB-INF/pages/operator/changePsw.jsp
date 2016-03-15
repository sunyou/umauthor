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
								<a href="#">个人信息</a>
							</li>
							<li class="active">修改密码</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<div class="row">
							<%-- 页面内容 --%>
							<div class="widget-box widget-color-blue">
								<div class="widget-header">
									<h5 class="widget-title">修改密码</h5>
							
									<div class="widget-toolbar">
										<a href="#" data-action="collapse">
											<i class="ace-icon fa fa-chevron-up"></i>
										</a>
									</div>
								</div>
							
								<div class="widget-body">
									<div class="widget-main">
										<form class="form-horizontal" role="form" id="changePswForm" method="post">
											<div class="form-group">
												<label class="control-label col-md-3 col-xs-12 col-sm-3 no-padding-right" for="pswOld">旧密码:</label>
											
												<div class="col-xs-12 col-sm-9 col-md-9">
													<div class="clearfix">
														<input type="password" id="pswOld" name="pswOld" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-md-3 col-xs-12 col-sm-3 no-padding-right" for="pswNew">新密码:</label>
											
												<div class="col-xs-12 col-sm-9 col-md-9">
													<div class="clearfix">
														<input type="password" id="pswNew" name="pswNew" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
													</div>
												</div>
											</div>
											<div class="form-group">
												<label class="control-label col-md-3 col-xs-12 col-sm-3 no-padding-right" for="pswNew1">确认新密码:</label>
											
												<div class="col-xs-12 col-sm-9 col-md-9">
													<div class="clearfix">
														<input type="password" id="pswNew1" name="pswNew1" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
													</div>
												</div>
											</div>
											<div class="center">
												<button type="button" class="btn btn-sm btn-primary" id="changeFormSubmit">
													修改
												</button>
												
											</div>
										</form>
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
					标题
				</div>
			</div>

			<div class="modal-body no-padding">
				内容
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
		$("#changePswForm").validate({
			rules: {
				pswOld: {
					required: true,
					minlength: 6,
					maxlength: 12
				},
				pswNew: {
					required: true,
					minlength: 6,
					maxlength: 12
				},
				pswNew1: {
					required: true,
					minlength: 6,
					maxlength: 12,
					equalTo: "#pswNew"
				}
			},
			messages: {
				pswNew1: {
					equalTo:'输入和新密码不同'
				}
			}
		});
		
		//form提交
		$('#changeFormSubmit').on('click',function(){
			if($("#changePswForm").valid() === true ){
				var formData = utilsAi.formArrayUtil($("#changePswForm").serializeArray())[0];
				var urlStr = '/operator/savePassword';
				
				utilsAi.ajax({
					url:urlStr,
					data:formData,
					success:function(data){
						utilsAi.alert('修改成功');
						
						$('#pswOld').val('');
						$('#pswNew').val('');
						$('#pswNew1').val('');
					}
				});
			};
		});
	});
</script>

<%-- 业务js导入,使用businessPath路径指向/static/business --%>
<%-- <script src="${businessPath}/org/index.js"></script> --%>
<%-- 业务js导入 --%>



</body>
</html>