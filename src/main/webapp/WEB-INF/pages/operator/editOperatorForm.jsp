<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<form class="form-horizontal" role="form" id="operatorForm" method="post" style="margin:10px 20px 10px 0px;">
<input type="hidden" id="domainId" name="domainId" value="${umOperator.domainId}">
<input type="hidden" id="operatorId" name="operatorId" value="${umOperator.operatorId}">
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="operatorName">员工姓名:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="operatorName" name="operatorName" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="${umOperator.operatorName}" />
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="operatorCode">员工工号:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="operatorCode" name="operatorCode" placeholder="" value="${umOperator.operatorCode}" class="col-xs-12 col-sm-4 col-md-12" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="operatorName">账号类型:</label>
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<select class="form-control" id="operatorType" name="operatorType">
					<option value="">---请选择</option>
					<c:forEach var="item" items="${operatorTypes}">
						<option value="${item.itemKey}" <c:if test='${umOperator.operatorType==item.itemKey }'>selected</c:if>>${item.itemValue}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="operatorCode">账号级别:</label>
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<select class="form-control" id="operatorLevel" name="operatorLevel">
					<option value="">---请选择</option>
					<c:forEach var="item" items="${operatorLevels}">
						<option value="${item.itemKey}" <c:if test='${umOperator.operatorLevel==item.itemKey }'>selected</c:if>>${item.itemValue}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgId">所属组织:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="orgIdTree" name="orgIdTree" placeholder="" value="" class="col-xs-12 col-sm-4 col-md-12" />
				<input type="hidden" id="orgId" name="orgId" value="${umOperator.orgId}"/>
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="sexCode">性别:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<select class="form-control" id="sexCode" name="sexCode">
					<option value="">请选择性别</option>
					<option value="1" <c:if test="${umOperator.sexCode==1}"> selected="selected" </c:if>>男</option>
					<option value="0" <c:if test="${umOperator.sexCode==0}"> selected="selected" </c:if>>女</option>
				</select>
			</div>
			
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="remarks">备注:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-10">
			<div class="clearfix">
				<textarea class="form-control" id="remarks" name="remarks" placeholder="备注">${umOperator.remarks}</textarea>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="birthday">生日:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<div class="input-group">
					<input class="form-control date-picker" id="birthday" name="birthday" type="text" data-date-format="yyyy-mm-dd" value="${umOperator.birthday}"/>
					<span class="input-group-addon">
						<i class="fa fa-calendar  bigger-110"></i>
					</span>
				</div>
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="certNo">身份证:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="certNo" name="certNo" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="${umOperator.certNo}" />
			</div>
		</div>
		
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="email">邮箱:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="email" name="email" placeholder="" value="${umOperator.email}" class="col-xs-12 col-sm-4 col-md-12" />
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="telNo">联系电话:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="telNo" name="telNo" placeholder="" value="${umOperator.telNo}" class="col-xs-12 col-sm-4 col-md-12" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="addressDetail">地址:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-10">
			<div class="clearfix">
				<input type="text" id="addressDetail" name="addressDetail" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="${umOperator.addressDetail}" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="positionCode">职位:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="positionCode" name="positionCode" placeholder="" value="${umOperator.positionCode}" class="col-xs-12 col-sm-4 col-md-12" />
			</div>
		</div>
	</div>
	<div class="center">
		<button type="button" class="btn btn-sm btn-success" id="operatorFormSubmit">
			提交
			<i class="icon-arrow-right icon-on-right bigger-110"></i>
		</button>
	</div>
</form>
<script  type="text/javascript">
umauthor.operatorObj.operatorEditType = '${type}';
var options = {
	orgId:'${treeOrgId}'
};
umauthor.operatorObj.operatorFormInit(options);
function setValues(){
	$('#sexCode').val('${umOperator.sexCode}');
}

setValues();
</script>