<%@ page contentType="text/html;charset=UTF-8" %>
<shiro:authenticated>
<div id="navbar" class="navbar navbar-default ace-save-state">


<div class="navbar-container ace-save-state" id="navbar-container">
	<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
		<span class="sr-only">Toggle sidebar</span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>
	</button>
				
	<div class="navbar-header pull-left">
		<a href="#" class="navbar-brand">
			<small>
				<i class="fa fa-leaf"></i>
				系统管理
			</small>
		</a><!-- /.brand -->
	</div><!-- /.navbar-header -->

	<div class="navbar-buttons navbar-header pull-right" role="navigation">
		<ul class="nav ace-nav">
			<%-- <li class="purple">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<i class="icon-bell-alt icon-animated-bell"></i>
					<span class="badge badge-important">8</span>
				</a>

				<ul class="pull-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
					<li class="dropdown-header">
						<i class="icon-warning-sign"></i>
						8 Notifications
					</li>

					<li>
						<a href="#">
							<div class="clearfix">
								<span class="pull-left">
									<i class="btn btn-xs no-hover btn-pink icon-comment"></i>
									New Comments
								</span>
								<span class="pull-right badge badge-info">+12</span>
							</div>
						</a>
					</li>

					<li>
						<a href="#">
							<i class="btn btn-xs btn-primary icon-user"></i>
							Bob just signed up as an editor ...
						</a>
					</li>

					<li>
						<a href="#">
							<div class="clearfix">
								<span class="pull-left">
									<i class="btn btn-xs no-hover btn-success icon-shopping-cart"></i>
									New Orders
								</span>
								<span class="pull-right badge badge-success">+8</span>
							</div>
						</a>
					</li>

					<li>
						<a href="#">
							<div class="clearfix">
								<span class="pull-left">
									<i class="btn btn-xs no-hover btn-info icon-twitter"></i>
									Followers
								</span>
								<span class="pull-right badge badge-info">+11</span>
							</div>
						</a>
					</li>

					<li>
						<a href="#">
							See all notifications
							<i class="icon-arrow-right"></i>
						</a>
					</li>
				</ul>
			</li> --%>

			<%-- <li class="green">
				<a data-toggle="dropdown" class="dropdown-toggle" href="#">
					<i class="icon-envelope icon-animated-vertical"></i>
					<span class="badge badge-success">5</span>
				</a>

				<ul class="pull-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
					<li class="dropdown-header">
						<i class="icon-envelope-alt"></i>
						5 Messages
					</li>

					<li>
						<a href="#">
							<img src="${resourcePath}/ace/assets/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
							<span class="msg-body">
								<span class="msg-title">
									<span class="blue">Alex:</span>
									Ciao sociis natoque penatibus et auctor ...
								</span>

								<span class="msg-time">
									<i class="icon-time"></i>
									<span>a moment ago</span>
								</span>
							</span>
						</a>
					</li>

					<li>
						<a href="#">
							<img src="${resourcePath}/ace/assets/avatars/avatar3.png" class="msg-photo" alt="Susan's Avatar" />
							<span class="msg-body">
								<span class="msg-title">
									<span class="blue">Susan:</span>
									Vestibulum id ligula porta felis euismod ...
								</span>

								<span class="msg-time">
									<i class="icon-time"></i>
									<span>20 minutes ago</span>
								</span>
							</span>
						</a>
					</li>

					<li>
						<a href="#">
									<img src="${resourcePath}/ace/assets/avatars/avatar4.png" class="msg-photo" alt="Bob's Avatar" />
									<span class="msg-body">
										<span class="msg-title">
											<span class="blue">Bob:</span>
											Nullam quis risus eget urna mollis ornare ...
										</span>

										<span class="msg-time">
											<i class="icon-time"></i>
											<span>3:15 pm</span>
										</span>
									</span>
								</a>
							</li>

							<li>
								<a href="inbox.html">
									See all messages
									<i class="icon-arrow-right"></i>
								</a>
							</li>
						</ul>
					</li> --%>

					<li class="light-blue">
						<a data-toggle="dropdown" href="#" class="dropdown-toggle">
							<img class="nav-user-photo" src="${resourcePath}/ace/assets/avatars/user.jpg" alt="Jason's Photo" />
							<span class="user-info">
								<small>欢迎,</small>
								<shiro:principal/>
							</span>

							<i class="ace-icon fa fa-caret-down"></i>
						</a>

						<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
							<!-- <li>
								<a href="#">
									<i class="ace-icon fa fa-cog"></i>
									设置
								</a>
							</li> -->
							<li>
								<a href="${ctx}/operator/toChangePsw">
									<i class="ace-icon fa fa-key"></i>
									修改密码
								</a>
							</li>
							<li>
								<a href="#">
									<i class="ace-icon fa fa-user"></i>
									个人信息
								</a>
							</li>

							<li class="divider"></li>

							<li>
								<a href="#">
									<i class="ace-icon fa fa-power-off"></i>
									退出
								</a>
							</li>
						</ul>
					</li>
				</ul><!-- /.ace-nav -->
			</div><!-- /.navbar-header -->
		</div><!-- /.container -->
	</div>
	
	<script type="text/javascript" >
	$(function () {
		$('.icon-off').parent('a').on('click',function(){
			window.location=GLOBAL.WEBROOT+'/doLogout';
		});
		/* $(".icon-off").each(function(){
			
			$(this).live("click", function (e) {//绑定事件
				alert(1);
				//hi($(this).attr("code"));	
		
			});
		}); */
	});
	</script>
</shiro:authenticated>