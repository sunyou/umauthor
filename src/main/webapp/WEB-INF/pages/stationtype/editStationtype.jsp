<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<form class="form-horizontal" id="stationtypeForm">
	<input type="hidden" value="${stationtype.stationtypeId}" name="stationtypeId">
	<div class="form-group" style="margin-top: 10px;">
		<label for="stationtypeCode" class="col-xs-12 col-sm-8 col-md-2 control-label">编码</label>
		<c:if test="${empty stationtype.stationtypeCode}">
			<div class="col-xs-12 col-sm-8 col-md-9">			
				<input type="text" class="form-control" id="stationtypeCode" name="stationtypeCode" value="${stationtype.stationtypeCode}" placeholder="">		
			</div>
		</c:if>
		<c:if test="${not empty stationtype.stationtypeCode}">
			<label for="stationtypeCode" class="col-xs-12 col-sm-8 col-md-2 control-label">
			${stationtype.stationtypeCode}
			</label>
		</c:if>
	</div>
	<div class="form-group">
		<label for="stationtypeName" class="col-md-2 control-label">名称</label>
		<div class="col-xs-12 col-sm-8 col-md-9">
			<input type="text" class="form-control" id="stationtypeName" name="stationtypeName" value="${stationtype.stationtypeName}" placeholder="">
		</div>
	</div>
	<div class="form-group">
		<label for="domainId" class="col-md-2 control-label">状态</label>
		<div class="col-xs-12 col-sm-8 col-md-9">
			<select class="form-control" name="stationtypeState" >
				<option value="1"  <c:if test="${stationtype.stationtypeState=='1' }">selected="selected"</c:if>>有效</option>
				<option value="0"  <c:if test="${stationtype.stationtypeState=='0' }">selected="selected"</c:if>>无效</option>
			</select>
		</div>
	</div>
	<div class="form-group">
		<label for="remarks" class="col-md-2 control-label">备注</label>
		<div class="col-xs-12 col-sm-8 col-md-9">
			<div class="clearfix">
				<textarea class="form-control" id="remarks" name="remarks" placeholder="">${stationtype.remarks}</textarea>
			</div>
		</div>
	</div>
	<div class="form-group" style="text-align: center;">
		<button class="btn btn-sm btn btn-success "  id="saveBtn">
			<i class="icon-ok"></i> 确定
		</button>
		<button class="btn btn-sm btn-warning " data-dismiss="modal">
			<i class="icon-remove"></i> 取消
		</button>
	</div>
</form>

<script type="text/javascript">
	jQuery(function($) {

	});
</script>

<%-- 业务js导入,使用businessPath路径指向/static/business --%>
<script type="text/javascript" src="${businessPath }/stationtype/editStationtype.js"></script>
<%-- 业务js导入 --%>