$(function() {
	$("#groupForm").validate({
		rules: {
			groupName: {
				required: true,
				maxlength: 32
			}
		},
		messages: {
			groupName:{
				required: "请输入分组名称",
				maxlength: '分组名称不能大于{0}个字'
			}
		}
	});
	
	$('#addStationGroupBtn').on('click',function(){
//		if($("#groupNameId").val() == ''){
//			utilsAi.showError("提示","请填分组名称");
//			return false;
//		}
		if($("#groupForm").valid() === true ){
			var formData = utilsAi.formArrayUtil($("#groupForm").serializeArray())[0];
			utilsAi.ajax({
				url:'/stationGroup/addStationGroup',
				type:'post',
				data:formData,
				success:function(data){
					utilsAi.alert(data.message);
					if (data.code=='100') {
						//操作成功
						 $('#modal-table').modal('hide');
						 umauthor.oTable1.fnDraw();
					}
				}
			});
		}
		return false;
	});
});
