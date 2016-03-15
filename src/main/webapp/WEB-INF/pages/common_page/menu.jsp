<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
				<div id="sidebar" class="sidebar responsive ace-save-state">
					<script type="text/javascript">
						try{ace.settings.loadState('sidebar')}catch(e){}
					</script>

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="ace-icon fa fa-signal"></i>
							</button>
	
							<button class="btn btn-info">
								<i class="ace-icon fa fa-pencil"></i>
							</button>
	
							<!-- #section:basics/sidebar.layout.shortcuts -->
							<button class="btn btn-warning">
								<i class="ace-icon fa fa-users"></i>
							</button>
	
							<button class="btn btn-danger">
								<i class="ace-icon fa fa-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->
					
			        <ul class="nav nav-list" id="menu">
			        </ul>
			        
					
					<!-- /.nav-list -->

					<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
						<i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
					</div>
				</div>
		
<!-- <script type="text/javascript">
$(function() {

	
	$('#menu').sidebarMenu({
		data : [<c:forEach var="menu" items="${menuInfos}" varStatus="menuStat">
		{
			id : '${menu.menu.menuId}',
			text : '${menu.menu.menuName}',
			icon : '${menu.menu.menuImage}',
			url : '${menu.menu.menuUrl}',
			menus : [
			<c:forEach var="menuChild" items="${menu.childList}" varStatus="menuChildStat">
			{
			id : '${menuChild.menu.menuId}',
			text : '${menuChild.menu.menuName}',
			icon : '${menuChild.menu.menuImage}',
			url : '${menuChild.menu.menuUrl}'
			}<c:if test="${!menuChildStat.last}">,</c:if> 
			</c:forEach>
			]
			}<c:if test="${!menuStat.last}">,</c:if>  
			
			</c:forEach>]
	});
});
</script> -->
