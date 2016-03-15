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
<body>

<!-- header -->
<%@ include file="/WEB-INF/pages/common_page/header.jsp" %>
<!-- header -->

<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
				
				<!-- menu -->
				<%@ include file="/WEB-INF/pages/common_page/menu.jsp" %>
				<!-- menu -->

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
							<!-- tree start -->
							<div class="col-sm-4">
								<div class="widget-box">
									<div class="widget-header header-color-blue2">
										<h4 class="lighter smaller">组织结构</h4>
									</div>
	
									<div class="widget-body">
										<div class="widget-main padding-8">
											<!-- <div id="groupTree" class="tree"></div> -->
											<ul id="groupTree" class="ztree"></ul>
										</div>
									</div>
								</div>
							</div>
							<!-- tree end -->
							<!-- tab start -->
							<div class="col-sm-8">
								<div class="tabbable">
									<ul class="nav nav-tabs padding-12 tab-color-blue background-blue" id="myTab4">
										<li class="active">
											<a data-toggle="tab" href="#groupInfo">组织信息</a>
										</li>

										<li>
											<a data-toggle="tab" href='#profile4'>直属人员</a>
										</li>

										<li>
											<a data-toggle="tab" href="#dropdown14">岗位授权情况</a>
										</li>
									</ul>

									<div class="tab-content">
										<div id="groupInfo" class="tab-pane in active">
											
										</div>

										<div id="profile4" class="tab-pane">
											<div class="table-header">
												直属人员列表
											</div>
											<div class="table-responsive">
											<table id="sample-table-1" class="table table-striped table-bordered table-hover">
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
															组
														</th>
														<th>编辑</th>
													</tr>
												</thead>
											</table>
											</div>
										</div>

										<div id="dropdown14" class="tab-pane">
											<div class="table-header">
												关联用户列表
											</div>
											<div class="table-responsive">
											<div class="panel panel-default">
											  <div class="panel-body">
											    <form class="form-horizontal" role="form" id="searchForm" method="post">
													<div class="form-group">
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="groupNameS">组名:</label>
													
														<div class="col-xs-12 col-sm-9 col-md-4">
															<div class="clearfix">
																<input type="text" id="groupNameS" name="groupNameS" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="" />
															</div>
														</div>
														<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="nickNameS">昵称:</label>
													
														<div class="col-xs-12 col-sm-9 col-md-4">
															<div class="clearfix">
																<input type="text" id="nickNameS" name="nickNameS" placeholder="" value="" class="col-xs-12 col-sm-4 col-md-12" />
															</div>
														</div>
													</div>
													<div class="center">
														<button type="button" class="btn btn-sm btn-success" id="searchFormSubmit">
															查询
															<i class="icon-arrow-right icon-on-right bigger-110"></i>
														</button>
													</div>
												</form>
											  </div>
											</div>
											<table id="sample-table-2" class="table table-striped table-bordered table-hover">
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
															组
														</th>
														<th>编辑</th>
													</tr>
												</thead>
											</table>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- tab end -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->

			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->


<div id="modal-table" class="modal fade" tabindex="-1">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header no-padding">
				<div class="table-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						<span class="white">&times;</span>
					</button>
					Results for "Latest Registered Domains
				</div>
			</div>

			<div class="modal-body no-padding">
				<table class="table table-striped table-bordered table-hover no-margin-bottom no-border-top">
					<thead>
						<tr>
							<th>Domain</th>
							<th>Price</th>
							<th>Clicks</th>

							<th>
								<i class="icon-time bigger-110"></i>
								Update
							</th>
						</tr>
					</thead>

					<tbody>
						
					</tbody>
				</table>
			</div>

			<div class="modal-footer no-margin-top">
				<button class="btn btn-sm btn-danger pull-left" data-dismiss="modal">
					<i class="icon-remove"></i>
					Close
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- PAGE CONTENT ENDS -->

<!-- template -->
<%@ include file="/WEB-INF/pages/template/common/tableEditButtonTemplate.jsp" %>
<%@ include file="/WEB-INF/pages/template/group/groupEditTemplate.jsp" %>
<!-- template -->
<!-- script -->
<%@ include file="/WEB-INF/pages/common_page/js.jsp" %>
<!-- script -->

<script src="${resourcePath}/ace/assets/js/zTree_v3/js/jquery.ztree.core-3.5.js"></script>

<script>

	jQuery(function($) {
		//tab触发事件
		$('#myTab4 a').click(function(){
			var $el = $(this);
			console.log($el);
		});
		
		$('#searchFormSubmit').click(function(){
			oTable1.fnDraw();
		});
		
		//ztree 处理 start
		var setting = {
			view: {
				selectedMulti: false
			},
			async: {
				enable: true,
				url:"${ctx}/group/getNodes",
				autoParam:["id", "name=n", "level=lv"],
				otherParam:{"otherParam":"zTreeAsyncTest"}
			},
			callback: {
				beforeClick: beforeClick
			}
		};

		function beforeClick(treeId, treeNode) {
			//console.log(treeNode);
			loadGroupInfo(treeNode.id);
		}
		
   		var zNodes =[
   			{ id:1, pId:0, name:"组织1",isParent:true}
   		];

   		$.fn.zTree.init($("#groupTree"), setting, zNodes);
		
   		
		var groupEditSource = $("#group-edit-template").html();
		var groupEditTemplate = Handlebars.compile(groupEditSource);
		
		function loadGroupInfo(id){
			$.ajax({
				url:"${ctx}/group/getGroupInfo"
				,async:true
				,data: "id="+id
				,success:function(result){
					var groupData = result.data;
					var groupEditHtml = groupEditTemplate(groupData);
					$('#groupInfo').html(groupEditHtml);
					
					init();
				}
			});
		}
		
		loadGroupInfo(1);
		
		//ztree 处理 end
		
		//table 处理 start
		var $el = $('#sample-table-2');
		var oTable1 = $el.dataTable(utilsAi.dataTableInit({
			id:'sample-table-2',
			"sAjaxSource": "${ctx}/group/list",
			"fnServerParams": function ( aoData ) {
			    aoData.push( { "name": "nickNameS", "value": $('#nickNameS').val() } );
			},
			"aoColumnDefs": [{
					"aTargets": [1],
			    	mData: "username"
			    },{
			    	"aTargets": [2],
			    	mData: "group"
			    }
			 ],
			 //hasCheck:false,
			 hasButton:true,
			 buttonType : ['info','edit','del']
		}));
		
		$el.on('click', '.openInfo', function() {
		    var row = $(this).closest('tr')[0];
		    var rowData = oTable1.fnGetData(row); 
		    
		    if (rowData) {
		        var rowId = rowData.id;
		        console.log(rowId);
		        // Use your rowId value the way you like
		        $('#modal-table').modal('toggle');
		    }
		});
		
		//table 处理 end
		
		function init(){
			$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
				$(this).prev().focus();
			});
			
			$("#groupInfoForm").validate({
				rules: {
					groupName: {
						required: true,
						maxlength: 15
					},
					nickName: "required",
					interest: "required",
					hobby: "required",
					area: "required",
					createTime: "required"
				},
				messages: {
					groupName:{
						required: "请输入组名",
						maxlength: '组名不能大于{0}个字'
					},
					nickName: "请输入昵称",
					interest: "请选择兴趣",
					hobby: "请选择爱好",
					area: "请选择地区",
					createTime: "请选择日期"
				}
			});
		}
		
		//form提交
		$('body').on('click',"#formSubmit",function(){
			console.log(utilsAi.formArrayUtil($("#groupInfoForm").serializeArray())[0])
			if($("#groupInfoForm").valid() === false ){
				
			}
		});
	})

</script>

</body>
</html>

