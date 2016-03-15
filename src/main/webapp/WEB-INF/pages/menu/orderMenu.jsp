<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<div class="form-group row valign">
	<div class="col-xs-12 col-sm-9 col-md-10">
		<table id="configTab" class="table">
			<thead>
				<tr>
					<th align="center" valign="middle" nowrap="nowrap">目录/菜单/功能点名称</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="menu" items="${menuList }" varStatus="status">
					<tr id="${menu.menuId }" onmouseover="onMouseOver(this);" onmouseout="onMouseOut(this);" bgcolor="#ffffff"
						onclick="onClick(this);" ondblclick="InfoBean.dbClickToLeft(this);" style="cursor: pointer" >
						<td>${menu.menuName }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<div class="col-xs-12 col-sm-9 col-md-2">
		<div class="">
		<input class="btn btn-sm btn btn-success " type="button" value="↑" onclick="toUp();" title="向上移" /><br /><br />
		<input class="btn btn-sm btn btn-success " type="button" value="↓" onclick="toDown();" title="向下移" />
		</div>
	</div>
</div>
<div class="form-group row" style="text-align: center;">
	<button class="btn btn-sm btn btn-success " id="saveBtn">
		<i class="icon-ok"></i> 保存
	</button>
	<button class="btn btn-sm btn-warning " data-dismiss="modal">
		<i class="icon-remove"></i> 取消
	</button>
</div>

<script type="text/javascript">
	jQuery(function($) {

	});
</script>

<%-- 业务js导入,使用businessPath路径指向/static/business --%>
<script type="text/javascript" src="${businessPath }/menu/orderMenu.js"></script>
<%-- 业务js导入 --%>