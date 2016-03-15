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
							<li class="active">岗位分组管理</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<div class="row">
							<%-- 页面内容 --%>
							<div class="col-md-12">
								<div class="table-header">
						岗位分组查询
							</div>
							<div class="table-responsive">
								<div class="panel panel-default">
							  	<div class="panel-body">
							      <form action=""class="form-horizontal" role="form">
							          <div class="col-sm-12 form-group">
							           <label class="col-sm-1 control-label" for="name">分组名称</label>
							           <div class="col-sm-2 ">
							            <input type="text" class="form-control" id="groupNameCon"   placeholder="请输入分组名称">
							            </div>
							            <div class="col-sm-9 ">
							            <button type="button" class="btn btn-sm btn-success" id="searchFormSubmit">
														查询
														<i class="icon-arrow-right icon-on-right bigger-110"></i>
													</button>
							               <button class="btn btn-sm btn-danger" id="resetBtn" type="button">重置</button>
							            </div>
							          </div>
							     </form>
							     </div>
							     </div>
							     </div>
							     
						</div>
<div class="col-md-12">
					   <div class="table-responsive">
						<div class="panel panel-default">
					  	<div class="panel-body">
						<div class="table-responsive">
											<table id="groupTable" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center" style="width:80px;">
															<label>
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th class="hidden-480">分组名称</th>
														<th >备注</th>
														<th >创建人</th>
														<th >创建时间</th>
													</tr>
												</thead>
											</table>
								</div>
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

<script type="text/javascript">
	jQuery(function($) {
		
	});
</script>

<%-- 业务js导入,使用businessPath路径指向/static/business --%>
<%-- <script src="${businessPath}/org/index.js"></script> --%>
<%-- 业务js导入 --%>
<script type="text/javascript" src="${businessPath }/stationgroup/index.js"></script>
<script type="text/javascript" src="${resourcePath }/scripts/common/sweetalert.min.js"></script>
 <link rel="stylesheet" href="${resourcePath }/styles/sweetalert.css"/>
</body>
</html>