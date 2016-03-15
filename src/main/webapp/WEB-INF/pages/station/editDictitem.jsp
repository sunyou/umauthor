<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

 <form class="form-horizontal" id="dictForm" role="form"  action="" >
 <input type="hidden"  value="${type}" name="type">
		<div class="form-group" style="margin-top: 10px;">
			<label for="dictName" class="col-sm-3 control-label">字典名称</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id=dictNameId  name="dictKey" <c:if test="${not empty  dictitem}">readonly="readonly"</c:if>   value="${dictitem.dictKey}"
					placeholder="">
			</div>
		</div>
		<div class="form-group">
			<label for="itemNoId" class="col-sm-3 control-label">字典项KEY值</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="itemNoId" placeholder=""   <c:if test="${not empty  dictitem}">readonly="readonly"</c:if>  name="itemKey" value="${dictitem.itemKey }"> 
			</div>
		</div>
		<div class="form-group">
			<label for="itemNameId" class="col-sm-3 control-label">字典项VALUE值</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="itemNameId" placeholder=""  name="itemValue" value="${dictitem.itemValue}">
			</div>
		</div>
		<div class="form-group">
			<label for="itemNameId" class="col-sm-3 control-label">字典项扩展值1</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="itemExtValue1Id" placeholder=""  name="itemExtValue1" value="${dictitem.itemExtValue1}">
			</div>
		</div>
		<div class="form-group">
			<label for="itemNameId" class="col-sm-3 control-label">字典项扩展值2</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="itemExtValue2Id" placeholder=""  name="itemExtValue2" value="${dictitem.itemExtValue2}">
			</div>
		</div>
		<div class="form-group">
			<label for="parentItemNoId" class="col-sm-3 control-label">上级字典项KEY值</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="parentItemNoId" placeholder="" name="parentItemKey" value="${dictitem.parentItemKey }">
			</div>
		</div>
		<div class="form-group">
			<label for="itemOrderId" class="col-sm-3 control-label">字典项排序</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="itemOrderId" placeholder="" name="itemOrder" value="${dictitem.itemOrder }">
			</div>
		</div>
		<div class="form-group">
			<label for="itemState" class="col-sm-3 control-label">状态</label>
			<div class="col-sm-8">
				<select class="form-control" name="itemState" >
					<option value="1"  <c:if test="${dictitem.itemState=='1' }">selected="selected"</c:if>>有效</option>
					<option value="0"  <c:if test="${dictitem.itemState=='0' }">selected="selected"</c:if>>无效</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="itemDescId" class="col-sm-3 control-label">备注</label>
			<div class="col-sm-8">
				<input type="text" class="form-control" id="itemDescId" placeholder="" name="itemDesc" value="${dictitem.itemDesc }">
			</div>
		</div>
		<div class="form-group" style="text-align: center;">
			<button class="btn btn-sm btn btn-success "  id="addDictItemBtn">
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
<script type="text/javascript" src="${businessPath }/dictitem/editDictitem.js"></script>


