$(function() {
	$("#stationtypeForm").validate({
		rules: {
			stationtypeCode: {
				required: true,
				maxlength: 100
			},
			stationtypeName: {
				required: true,
				maxlength: 100
			},
			remarks:{
				maxlength: 500
			}
		},
		messages: {
			stationtypeCode:{
				required: "请输入编码",
				maxlength: '编码不能大于{0}个字'
			},
			stationtypeName:{
				required: "请输入名称",
				maxlength: '名称不能大于{0}个字'
			},
			remarks: {
				maxlength: '备注不能大于{0}个字'
			}
		}
	});	
	
	
	$('#saveBtn').on('click',function(){

		if($("#stationtypeForm").valid() === true ){
			var formData = utilsAi.formArrayUtil($("#stationtypeForm").serializeArray())[0];
			var urlStr = '/stationtype/saveStationtype';
			
			utilsAi.ajax({
				url:urlStr,
				data:formData,
				success:function(data){
					//隐藏悬浮框
					$('#modal-table').modal('hide');
					//刷新列表
					oTable1.fnDraw();
				}
			});
		}
		return false;
	});

});
