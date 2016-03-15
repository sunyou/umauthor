<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/common/taglibs.jsp" %>
<link rel="stylesheet" href="${resourcePath}/ace/assets/js/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
<form class="form-horizontal" role="form" id="relaStationForm" method="post" style="padding-top:10px;">
	<input type="hidden" id="id" name="id" value="${rela.id }"/>

	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="stationName">授权岗位:</label>
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" placeholder="" value="${rela.stationName }" readonly="readonly" class="col-xs-12 col-sm-4 col-md-12" />
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="stationName">授权操作员:</label>
		<div class="col-xs-12 col-sm-9 col-md-4">
			<div class="clearfix">
				<input type="text" placeholder="" value="${rela.operatorName }" readonly="readonly" class="col-xs-12 col-sm-4 col-md-12" />
			</div>
		</div>
	</div>
	<div class="form-group">
	<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="orgId">授权组织:</label>
	<div class="col-xs-12 col-sm-9 col-md-4">
		<div class="clearfix">
			<input type="text" id="grantOrgModTree" name="grantOrgModTree" placeholder="" value="${rela.orgName }" class="col-xs-12 col-sm-4 col-md-12" />
			<input type="hidden" id="grantOrgModId" name="grantOrgModId" value="${rela.orgId }"/>
			</div>
		</div>
	</div>
	<div class="form-group">
		<label class="control-label col-md-2 col-xs-12 col-sm-3 no-padding-right" for="allowReauthor">可再授权:</label>
		<div class="col-xs-12 col-sm-9 col-md-2">
			<div class="clearfix">
				<select id="allowReauthor" name="allowReauthor" value="${rela.allowReauthor }">
					<option value="1">是</option>
					<option value="0">否</option>
				</select>
			</div>
		</div>
	</div>
	<div class="center">
		<button type="button" class="btn btn-sm btn-success" id="relaStationFormSubmit">
			提交
			<i class="icon-arrow-right icon-on-right bigger-110"></i>
		</button>
	</div>
</form>
<script src="${resourcePath }/ace_v135/dist/js/ace-extra.min.js"></script>										

<script src="${resourcePath }/ace_v135/dist/js/jquery.validate.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/additional-methods.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/bootbox.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/jquery.maskedinput.min.js"></script>
<script src="${resourcePath }/ace_v135/dist/js/select2.min.js"></script>
	
<script src="${resourcePath}/scripts/lib/zTree_v3/js/jquery.ztree.core-3.5.js"></script>
<script src="${resourcePath}/scripts/lib/zTree_v3/js/jquery.ztree.excheck-3.5.js"></script>
<script src="${resourcePath}/scripts/common/select-tree.js"></script>								
<script type="text/javascript">
var options = {
	orgId:'${rela.orgId }',
	type:'update'
};
$("#allowReauthor").val('${rela.allowReauthor}');
umauthor.operatorObj.stationGrantFormInit(options);

$("#relaStationFormSubmit").click(function(){
	var formData = utilsAi.formArrayUtil($("#relaStationForm").serializeArray())[0];
	utilsAi.ajax({
		url:'/operator/updateRelaStations',
		type:'post',
		data:formData,
		success:function(data){
			utilsAi.alert('修改成功');
			$('#mod-modal-table').modal('toggle');
			window.umauthor.relaStationTable.fnDraw();
		}
	});
});
	
</script>										
