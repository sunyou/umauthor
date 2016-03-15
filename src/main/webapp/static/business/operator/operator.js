(function($) {
	"use strict";
	
	umauthor.OperatorObj = function (options) {
		this.initPage();	
	};
	
	umauthor.OperatorObj.prototype = {

	        constructor: umauthor.OperatorObj,
	        
	        operatorTableObj: null,
	        operatorTableOrgId:null,
	        initPage:function(){
	        	//点checkbox的文字，对应到前面的checkbox
				$('body').on('click','.checkbox-title',function(){
					
					var $check = $(this).previousSbiling();
					if ($check.is(':checked')) {
						$check.prop('checked', false);
					}else{
						$check.prop('checked', true);
					};
				});
	        },
	        initTable:function(op){
	        	op = op || {};
	        	var that = this;
	        	
	    		var $tabEl = $('#operatorTable');
	    		
	    		$('#orgIdS').val('');
	    		that.operatorTableOrgId = $('#orgIdS').val() || op.orgId;
	    		
	    		//刷新树
	    		this.initOrgSelecttree('orgIdSTree',op.orgId);
	    		
	    		if(that.operatorTableObj == null){
	    			that.operatorTableObj = $tabEl.dataTable(utilsAi.dataTableInit({
	    				id:'operatorTable',
		        		checkId:'operatorCode',
		        		"sAjaxSource": "/operator/operateList",
		        		"fnServerParams": function ( aoData ) {
		        		    aoData.push( { 
		        		    	"name": "orgId", "value": that.operatorTableOrgId
//		        		    },{
//		        		    	"name": "createTimeStart", "value":createTime.getStart()
//		        		    },{
//		        		    	"name": "createTimeEnd", "value":createTime.getEnd()
		        		    },{
		        		    	"name": "operatorNameS", "value":$('#operatorNameS').val()
		        		    },{
		        		    	"name": "operatorCodeS", "value":$('#operatorCodeS').val()
		        		    },{
		        		    	"name": "telNoS", "value":$('#telNoS').val()
		        		    } );
		        		},
		        		"aoColumnDefs": [{
		        				"aTargets": [1],
		        		    	mData: "operatorName"
		        		    },{
		        		    	"aTargets": [2],
		        		    	mData: "operatorCode"
		        		    },{
		        		    	"aTargets": [3],
		        		    	mData: "telNo"
		        		    },{
		        		    	"aTargets": [4],
		        		    	mData: "orgName"
		        		    }
		        		 ],
		        		 checkNames:['orgId','operatorId'],
		        		 //hasCheck:false,
		        		 addBtn:function(){
		        			 that.loadForm('','','add');
		        		 },
		        		 updateBtn:function(){
		        			 var $checkEl = $('#operatorTable input[type="checkbox"][name="checkId"]:checked');
		        			 if($checkEl.length!=1){
		        				 utilsAi.showError('提示','请选择一条记录');
		        			 }else{
		        				 that.loadForm($checkEl.val(),$checkEl.data('orgid'),'update');
		        			 }
		        		 },
		        		 delBtn:function(){
		        			 var $checkEl = $('#operatorTable input[type="checkbox"][name="checkId"]:checked');
		        			 var operatorIds = '';
		        			 if($checkEl.length==0){
		        				 utilsAi.showError('提示','请选择要删除的记录');
		        			 }else{
		        				 $checkEl.each(function(){
		        					 var me = $(this);
		        					 operatorIds += me.data('operatorid')+',';
		        				 });
		        				 operatorIds = operatorIds.substring(0,operatorIds.length-1);
		        				 //console.log(operatorIds);
		        				 utilsAi.confirm({
				        	    		content:'<span class="text-danger">确定要删除选中的人员</span>？',
				        	    		callback:function(){
					        	    		//提交删除
				    						utilsAi.ajax({
				    							url:'/operator/deleteOperator',
				    							data:'operatorIds='+operatorIds,
				    							dataType:'json',
				    							success:function(data){
				    								utilsAi.alert('删除成功');
				    								//刷新
				    								that.operatorTableObj.fnDraw();
				    							}
				    						});
					        	    	}
				        	    	});
		        			 }
		        		 },
		        		 btns:[{
		        			 name:'锁定',
		        			 color:'btn-warning',
		        			 click:function(){
		        				 var $checkEl = $('#operatorTable input[type="checkbox"][name="checkId"]:checked');
			        			 var operatorIds = '';
			        			 if($checkEl.length==0){
			        				 utilsAi.showError('提示','请选择要锁定的记录');
			        			 }else{
			        				 $checkEl.each(function(){
			        					 var me = $(this);
			        					 operatorIds += me.data('operatorid')+',';
			        				 });
			        				 operatorIds = operatorIds.substring(0,operatorIds.length-1);
			        				 //console.log(operatorIds);
			        				 utilsAi.confirm({
					        	    		content:'<span class="text-danger">确定要锁定选中的人员</span>？',
					        	    		callback:function(){
						        	    		//提交删除
					    						utilsAi.ajax({
					    							url:'/operator/lockOperator',
					    							data:'operatorIds='+operatorIds,
					    							dataType:'json',
					    							success:function(data){
					    								utilsAi.alert('删除成功');
					    								//刷新
					    								that.operatorTableObj.fnDraw();
					    							}
					    						});
						        	    	}
					        	    	});
			        			 }
		        			 }
		        		 },{
		        			 name:'重置密码',
		        			 color:'btn-danger',
		        			 click:function(){
		        				 var $checkEl = $('#operatorTable input[type="checkbox"][name="checkId"]:checked');
			        			 var operatorIds = '';
			        			 if($checkEl.length==0){
			        				 utilsAi.showError('提示','请选择要重置密码的人员');
			        			 }else{
			        				 $checkEl.each(function(){
			        					 var me = $(this);
			        					 operatorIds += me.data('operatorid')+',';
			        				 });
			        				 operatorIds = operatorIds.substring(0,operatorIds.length-1);
			        				 //console.log(operatorIds);
			        				 utilsAi.confirm({
					        	    		content:'<span class="text-danger">确定要重置选中人员的密码</span>？',
					        	    		callback:function(){
						        	    		//提交删除
					    						utilsAi.ajax({
					    							url:'/operator/resetPassword',
					    							data:'operatorIds='+operatorIds,
					    							dataType:'json',
					    							success:function(data){
					    								utilsAi.alert('重置成功');
					    								//刷新
					    								that.operatorTableObj.fnDraw();
					    							}
					    						});
						        	    	}
					        	    	});
			        			 }
		        			 }
		        		 },{
		        			 name:'岗位授权',
		        			 click:function(){
		        				 var $checkEl = $('#operatorTable input[type="checkbox"][name="checkId"]:checked');
		        				 var operatorId = '';
		        				 if($checkEl.length == 0){
			        				 utilsAi.showError('提示','请选择要授权岗位的人员');
			        				 return false;
			        			 }else if($checkEl.length > 1){
			        				 utilsAi.showError('提示','每次最多允许操作一条记录');
			        				 return false;
			        			 }else{
			        				 $checkEl.each(function(){
			        					 var me = $(this);
			        					 operatorId = me.data('operatorid');
			        				 });
			        				utilsAi.ajax({
//	        						url : "/operator/toGrantStation",
			        					 url : "/operator/toRelaStationList",
			        					 async : true,
	        						data : {"toOperatorId":operatorId},
			        					 success : function(result) {
			        						 $('#modalContent').html(result);
			        						 $('#modal-table').modal('toggle');
			        					 }
			        				 });
			        			 }
		        			 }
		        		 }],
		        		 hasButton:true,
		        		 buttonType : ['info','edit','del']
					}));
	    			
//	    			var bus = '<button id="refresh" class="btn btn-success btn-xs">新增</button>&nbsp;&nbsp;'+
//	    			'<button id="addBut" class="btn btn-xs btn-primary">修改</button>&nbsp;&nbsp;' +
//	    			'<button id="addBut" class="btn btn-xs btn-danger">删除</button>&nbsp;&nbsp;';
//	    			$("#operatorListInfo div.table_buttons").append(bus);
	    			
	    			
	    			$tabEl.on('click', '.openInfo', function() {
		        	    var row = $(this).closest('tr')[0];
		        	    var rowData = that.operatorTableObj.fnGetData(row); 
		        	    
		        	    if (rowData) {
		        	        that.loadForm(rowData.operatorCode,rowData.orgId,'info');
		        	    }
		        	});
		        	
	    			$tabEl.on('click', '.openEdit', function() {
		        	    var row = $(this).closest('tr')[0];
		        	    var rowData = that.operatorTableObj.fnGetData(row); 
		        	    
		        	    if (rowData) {
		        	        that.loadForm(rowData.operatorCode,rowData.orgId,'update');
		        	    }
		        	});
		        	
	    			$tabEl.on('click', '.openDel', function(e) {
		        		e.preventDefault();
		        		
		        	    var row = $(this).closest('tr')[0];
		        	    var rowData = that.operatorTableObj.fnGetData(row); 
		        	    
		        	    if (rowData) {
		        	    	utilsAi.confirm({
		        	    		content:'确定要删除人员:<span class="text-danger">'+rowData.operatorName+'</span>？',
		        	    		callback:function(){
			        	    		//提交删除
		    						utilsAi.ajax({
		    							url:'/operator/deleteOperator',
		    							data:'operatorId='+rowData.operatorId,
		    							dataType:'json',
		    							success:function(data){
		    								console.log(data);
		    								utilsAi.alert('删除成功');
		    								//刷新
		    								that.operatorTableObj.fnDraw();
		    							}
		    						});
			        	    	}
		        	    	});
		        	    }
		        	});

		        	$('#searchFormSubmit').click(function(){
		        		//console.log($('input[name="createTime1"]').data('daterangepicker').container.find('input[name="daterangepicker_start"]').val());
		        		that.operatorTableOrgId = $('#orgIdS').val() || op.orgId;
		        		that.operatorTableObj.fnDraw();
		        	});
		        	
		        	$('#addFormSubmit').click(function(){
		        		that.loadForm('','','add');
		        	});
	    			
				}else{
					that.operatorTableObj.fnDraw();
				}
	        	
	        
	        },
	        loadForm : function(operatorCode,orgId,type){
	        	utilsAi.ajax({
	    			url:"/operator/editOperatorForm"
	    			,async:true
	    			,data: "operatorCode="+operatorCode+"&type="+type+"&orgId="+orgId
	    			,success:function(result){
	    				$('#modalContent').html(result);
	    				$('#modal-table').modal('toggle');
	    			}
	    		});
	        },
	        loadInfo : function(operatorCode,orgId,type){
	        	utilsAi.ajax({
	    			url:"/operator/operatorInfo"
	    			,async:true
	    			,data: "operatorCode="+operatorCode+"&type="+type+"&orgId="+orgId
	    			,success:function(result){
	    				$('#operatorInfo').html(result);
	    			}
	    		});
	        },
	        initOrgSelecttree:function(eleId,orgId,noTree){
	        	utilsAi.ajax({
	    			url:"/org/getOrgNode"
	    			,async:true
	    			,data: "orgId="+orgId
	    			,success:function(result){
	    				if(noTree){
	    					$('#'+eleId).val(result.data.orgName);
	    				}else{
	    					$('#'+eleId).selecttree({
		    	    			url:'/org/getOrgNodes',
		    	    			treeId:result.data.orgId,
		    	    			treeName:result.data.orgName,
		    	    			autoParam:["id=orgId"],
		    	    			refresh:true,
		    	    			setDefault:true
		    	    		});
	    				}
	    				
	    			}
	    		});
	        },
	        operatorEditType : '',
	        operatorFormInit : function(options){
	        	var that = this;
	        	
	        	if(that.operatorEditType=='info'){
	        		that.initOrgSelecttree('orgIdTree',options.orgId,true);
	        		
	        		$("#operatorForm input").attr("readonly","readonly");
					$("#operatorForm textarea").attr("readonly","readonly");
					$("#operatorForm select").attr("disabled","disabled");
					$('#operatorFormSubmit').hide();
	        	}else{
	        		//初始化组件
	        		that.initOrgSelecttree('orgIdTree',options.orgId);
	        		$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
						$(this).prev().focus();
					});
	        	}
	        	
	        	
	        	
	        	//更新时去取组织名称
	        	if(that.operatorEditType!='add'){
	        		utilsAi.ajax({
		    			url:"/org/getOrgNode"
		    			,async:true
		    			,data: "orgId="+$('#operatorForm').find('#orgId').val()
		    			,success:function(result){
		    				$('#orgIdTree').val(result.data.orgName);
		    			}
		    		});
	        	}
	        	
	        	
	    		$("#operatorForm").validate({
	    			rules: {
	    				operatorCode: {
	    					required: true,
	    					maxlength: 12
	    				},
	    				operatorName: {
	    					required: true,
	    					maxlength: 10
	    				},
	    				remarks: {
	    					maxlength: 200
	    				},
	    				operatorType: 'required',
	    				operatorLevel: 'required',
	    				orgId: "required",
	    				certNo: { isIdCardNo: true },
	    				email: { email: true },
	    				birthday: { dateISO: true },
	    				telNo: { isTel: true },
	    				addressDetail: { maxlength: 100 }
	    			},
	    			messages: {
	    				operatorCode:{
	    					required: "请输入员工工号",
	    					maxlength: '员工工号不能大于{0}个字'
	    				},
	    				operatorName:{
	    					required: "请输入员工姓名",
	    					maxlength: '员工姓名不能大于{0}个字'
	    				},
	    				operatorType:{
	    					required: "账号类型必填"
	    				},
	    				operatorLevel:{
	    					required: "账号级别必须"
	    				}
	    			}
	    		});
	    		
	    		//form提交
	    		$('#operatorFormSubmit').on('click',function(){
	    			//console.log(utilsAi.formArrayUtil($("#orgInfoForm").serializeArray())[0]);
	    			if($("#operatorForm").valid() === true ){
	    				var formData = utilsAi.formArrayUtil($("#operatorForm").serializeArray())[0];
	    				console.log(formData);
	    				var urlStr = '/operator/saveOperator';
	    				if(that.operatorEditType=='update'){
	    					urlStr = '/operator/updateOperator';
	    				}

	    				utilsAi.ajax({
	    					url:urlStr,
	    					data:formData,
	    					success:function(data){
	    						utilsAi.alert('保存成功');
	    						//刷新table
	    						that.operatorTableObj.fnDraw();
	    						$('#modal-table').modal('toggle');
	    					}
	    				});
	    			};
	    		});
	    	},
	    	stationGrantFormInit:function(options){
	    		var that = this;
	    		if(options.type == 'add'){
	    			that.initOrgSelecttree('grantOrgTree',options.orgId);
	    		}else if(options.type == 'update'){
	    			that.initOrgSelecttree('grantOrgModTree',options.orgId);
	    		}
	    	}
	};
	
	
})(jQuery);