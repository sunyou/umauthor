<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

 <form class="form-horizontal" id="groupForm" role="form"  action="" >
 <input type="hidden"  value="${type}" name="type">
 <input type="hidden"  value="${stationGroup.groupId}" name="groupId">
		<div class="form-group" style="margin-top: 10px;">
			<label for="groupName" class="col-sm-2 control-label">分组名称</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="groupNameId"  name="groupName"   value="${stationGroup.groupName}"
					placeholder="">
			</div>
		</div>
		<div class="form-group" style="margin-top: 10px;">
			<label for="groupName" class="col-sm-2 control-label">备注</label>
			<div class="col-sm-9">
				<input type="text" class="form-control" id="groupNameId"  name="remarks"   value="${stationGroup.remarks}"
					placeholder="">
			</div>
		</div>
		<div class="form-group" style="text-align: center;">
			<button class="btn btn-sm btn-success "  id="addStationGroupBtn">
				<i class="icon-ok"></i> 确定
			</button>
			<button class="btn btn-sm btn-warning " data-dismiss="modal">
				<i class="icon-remove"></i> 取消
			</button>
		</div>
	</form>




<%-- template --%>
<%-- template --%>
<%-- script --%>
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
<script type="text/javascript" src="${businessPath }/stationgroup/editStationGroup.js"></script>


