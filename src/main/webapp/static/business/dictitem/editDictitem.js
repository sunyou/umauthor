$(function() {
	$("#dictForm").validate({
		rules: {
			dictKey: {
				required: true
			},
			itemKey: {
				required: true
			},
			itemValue: {
				required: true
			},
			itemOrder: {
				required: true,
				number:true
			}
		},
		messages: {
			dictKey: {
				required: "请填写字典名",
			},
			itemKey: {
				required: "请填写字典项KEY值",
			},
			itemValue: {
				required: "请填写字典项VALUE值",
			},
			itemOrder: {
				required: "请填写字典项序号",
				number:"只能输入数字"
			}
		}
	});
	
	
	$('#addDictItemBtn').on('click',function(){
//		if($("#dictNameId").val() == ''){
//			utilsAi.showError("提示","请填写字典名");
//			return false;
//		}
//		if($("#itemNoId").val() == ''){
//			utilsAi.showError("提示","请填写字典编号");
//			return false;
//		}
//		if($("#itemNameId").val() == ''){
//			utilsAi.showError("提示","请填写字典内容");
//			return false;
//		}
//		if($("#parentItemNoId").val() == ''){
//			utilsAi.showError("提示","请填写父节点编号");
//			return false;
//		}
//		if($("#itemOrderId").val() == ''){
//			utilsAi.showError("提示","请填写排序");
//			return false;
//		}
		if($("#dictForm").valid() === true ){
			var formData = utilsAi.formArrayUtil($("#dictForm").serializeArray())[0];
			utilsAi.ajax({
				url:'/dictitem/addDictitem',
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
	
//	$("#cancleBtn").click(function() {
//		  $('#modal-table').modal('toggle');
//	});
});
