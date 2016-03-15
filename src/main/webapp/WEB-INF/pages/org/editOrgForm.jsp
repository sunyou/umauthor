<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>

<form class="form-horizontal" role="form" id="orgInfoForm" method="post">
<input type="hidden" id="domainId" name="domainId" value="${umOrg.domainId}">
<input type="hidden" id="orgId" name="orgId" value="${umOrg.orgId}">
<input type="hidden" id="parentOrgId" name="parentOrgId" value="${umOrg.parentOrgId}">
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right">上级组织:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<span class="form-control">${orgName}</span>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="provinceCode">所在行政区:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-10">
			<div class="clearfix">
				<select id="provinceCode" name="provinceCode" data-target-id='cityCode' data-result-id="areaId" data-result-value="areaName" data-ajax-url='/org/getAreaNodes'>
					<option value="">请选择省份</option>
					<c:forEach var="area" items="${provices}">
						<option value="${area.areaId}">${area.areaName}</option>
					</c:forEach>
				</select>
				<select id="cityCode" name="cityCode" data-target-id='districtCode' data-result-id="areaId" data-result-value="areaName" data-ajax-url='/org/getAreaNodes'>
					<option value="">请选择地市</option>
					<c:forEach var="area" items="${cities}">
						<option value="${area.areaId}">${area.areaName}</option>
					</c:forEach>
				</select>
				<select id="districtCode" name="districtCode" data-target-id='streetCode' data-result-id="areaId" data-result-value="areaName" data-ajax-url='/org/getAreaNodes'>
					<option value="">请选择区县</option>
					<c:forEach var="area" items="${districts}">
						<option value="${area.areaId}">${area.areaName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgName">组织名称:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="orgName" name="orgName" placeholder="" class="form-control" value="${umOrg.orgName}" />
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgCode">组织机构代码:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="orgCode" name="orgCode" placeholder="" value="${umOrg.orgCode}" class="form-control" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgType">组织类型:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<select class="form-control" id="orgType" name="orgType">
					<option value="">请选择组织类型</option>
					<c:forEach items="${orgTypes}" var="org">
						<c:if test="${org.itemKey==umOrg.orgType}">
							 <option value="${org.itemKey}" selected="selected">${org.itemValue}</option>
						</c:if>
						<c:if test="${org.itemKey!=umOrg.orgType}">
							 <option value="${org.itemKey}">${org.itemValue}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="">组织小类:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<select class="form-control" id="businessType" name="businessType">
					<option value="">请选择组织小类</option>
					<option value="1">小类1</option>
					<option value="2">小类2</option>
				</select>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="">行政级别:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<select class="form-control" id="adminLevel" name="adminLevel">
					<option value="">请选择行政级别</option>
					<c:forEach items="${adminLevels}" var="level">
						<c:if test="${level.itemKey==umOrg.adminLevel}">
							 <option value="${level.itemKey}" selected="selected">${level.itemValue}</option>
						</c:if>
						<c:if test="${level.itemKey!=umOrg.adminLevel}">
							 <option value="${level.itemKey}">${level.itemValue}</option>
						</c:if>
					</c:forEach>
				</select>
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgLevel">组织级别:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<select class="form-control" id="orgLevel" name="orgLevel">
					<option value="">请选择组织级别</option>
					<option value="1" selected>级别1</option>
					<option value="2">级别2</option>
				</select>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="contactName">联系人:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="contactName" name="contactName" placeholder="" value="${umOrg.contactName}" class="form-control" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="contactTel">手机:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="contactTel" name="contactTel" placeholder="" class="form-control" value="${umOrg.contactTel}" />
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="">电话:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="telNo" name="telNo" placeholder="区号-电话号码" class="form-control" value="${umOrg.contactTel}" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="email">邮箱:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="email" name="email" placeholder="" value="${umOrg.email}" class="form-control" />
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="faxNo">传真:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="faxNo" name="faxNo" placeholder="" value="${umOrg.faxNo}" class="form-control" />
			</div>
		</div>
	</div>
	<%-- <div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgIntroduct">组织简介:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-10">
			<div class="clearfix">
				<textarea class="form-control" id="orgIntroduct" name="orgIntroduct" placeholder="组织简介">${umOrg.orgIntroduct}</textarea>
			</div>
		</div>
	</div> --%>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgAddr">地址:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-10">
			<div class="clearfix">
				<input type="text" id="orgAddr" name="orgAddr" placeholder="" class="form-control" value="${umOrg.orgAddr}" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="postcode">邮编:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="postcode" name="postcode" placeholder="" class="form-control" value="${umOrg.postcode}" />
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="webUrl">网址:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="webUrl" name="webUrl" placeholder="" class="form-control" value="${umOrg.webUrl}" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="">排序:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="orderId" name="orderId" placeholder="" class="form-control" value="${umOrg.orderId}" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="remarks">备注:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-10">
			<div class="clearfix">
				<textarea class="form-control" id="remarks" name="remarks" placeholder="备注">${umOrg.remarks}</textarea>
			</div>
		</div>
	</div>
	<%-- <div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="lawPersonName">法人姓名:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="lawPersonName" name="lawPersonName" placeholder="" class="col-xs-12 col-sm-6 col-md-12" value="${umOrg.lawPersonName}" />
			</div>
		</div>
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="lawPersonTel">法人电话:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="lawPersonTel" name="lawPersonTel" placeholder="" value="${umOrg.lawPersonTel}" class="col-xs-12 col-sm-4 col-md-12" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgShort">组织简称:</label>
	
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" id="orgShort" name="orgShort" placeholder="" value="" class="col-xs-12 col-sm-4 col-md-12" />
			</div>
		</div>
		
	</div> --%>
	<div class="center">
		<button type="button" class="btn btn-sm btn-primary" style="display: hidden;" id="addOrg">
			新增
		</button>
		<button type="button" class="btn btn-sm btn-warning" style="display: hidden;" id="toUpdateSubmit">
			修改
		</button>
		<button type="button" class="btn btn-sm btn-danger" style="display: hidden;" id="deleteOrg">
			删除
		</button>
		<button type="button" class="btn btn-sm btn-success" style="display: hidden;" id="orgFormSubmit">
			保存
		</button>
		<button type="button" class="btn btn-sm btn-warning" style="display: hidden;" id="cancelSubmit">
			取消
		</button>
	</div>
</form>
<script  type="text/javascript">
umauthor.orgObj.formEditType = '${type}';
umauthor.orgObj.formInit();

function setValues(){
	$('#provinceCode').val('${umOrg.provinceCode}');
	$('#cityCode').val('${umOrg.cityCode}');
	$('#districtCode').val('${umOrg.districtCode}');
	$('#orgType').val('${umOrg.orgType}');
	$('#businessType').val('${umOrg.businessType}');
	$('#adminLevel').val('${umOrg.adminLevel}');
	$('#orgLevel').val('${umOrg.orgLevel}');
}

setValues();
</script>