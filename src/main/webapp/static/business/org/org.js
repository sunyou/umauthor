(function($) {
	"use strict";
	
	umauthor.OrgObj = function (options) {
		options = options || {};
		this.userOrg = options.userOrg || {};
		this.initPage();
		this.initTree();
		this.initTab();
		
		//默认info tab是激活的
		this.tabActiveInfoId = this.userOrg.orgId;
		this.orgIdSelect = this.userOrg.orgId;
		this.orgIdSelectName = this.userOrg.orgName;
		this.loadGroupInfo(this.userOrg.orgId,'info');
		
		//this.createTime = utilsAi.dataRangeInit('createTime');
		
		umauthor.operatorObj = new umauthor.OperatorObj(this.userOrg);
		this.operatorObj = umauthor.operatorObj;
	};
	
	umauthor.OrgObj.prototype = {

			constructor: umauthor.OrgObj,
			//变量
			//用户组织信息
			userOrg:null,
			//选中的组织信息
			orgIdSelect  : null,
			orgIdSelectName : null,
			orgIdParent : null,
			//树
			groupTree : null,
			//时间区域组件
			createTime : null,
			//用来判断tab选中时树选中id是否和之前相同
			tabActiveInfoId:null,
			tabActiveOperatorId:null,
			tabActiveOrgId:null,
			tabActivePermissionId:null,
			//form编辑类型
			formEditType:'',
			//table对象
			operatorTableObj : null,
			orgTableObj : null,
			permissionTableObj : null,
			//引入的operatorList对象
			operatorObj : null,
			//当前orgForm状态 info查看，update 修改， add新增
			orgFormType:'info',
			initPage:function(){
				var that = this;
				//添加组织
				$('body').on('click',"#addOrg",function(){
					if(that.orgIdSelect == null){
						utilsAi.alert('请选择要添加的父节点。');
						return ;
					}
					
					that.loadGroupInfo(that.orgIdSelect,'add');
					$('#orgInfoTab').tab('show');
				});
				//删除组织
				$('body').on('click',"#deleteOrg",function(e){
					e.preventDefault();
					
					if(that.orgIdSelect == null){
						utilsAi.alert('请选择要删除的组织。');
						return ;
					}
					
					utilsAi.confirm({
						content:'确定要删除组织:<span class="text-danger">'+that.orgIdSelectName+'</span>？',
						callback:function(){
							//提交删除
							utilsAi.ajax({
								url:'/org/deleteOrg',
								data:'orgId='+that.orgIdSelect,
								dataType:'json',
								success:function(data){
									//console.log(data);
									utilsAi.alert('删除成功');
									//刷树
									var nodesEdit = that.groupTree.getNodeByParam('id',that.orgIdParent);
									that.groupTree.reAsyncChildNodes(nodesEdit, 'refresh', false);
									
									that.orgIdSelect = null;
									that.orgIdSelectName = null;
									that.orgIdParent = null;
								}
							});
						}
					});
					
				});
				
				//到编辑页面
				$('body').on('click',"#toUpdateSubmit",function(e){
					e.preventDefault();
					that.orgFormType = 'update';
					that.orgFormChangeType();
				});
				
				//取消保存
				$('body').on('click',"#cancelSubmit",function(e){
					e.preventDefault();
					that.loadGroupInfo(that.orgIdSelect,'info');
				});
				
				
				$('#orgTypeTree').change(function(e){
					var $el = $(this);
					console.log($el.val());
					
					e.preventDefault();
					
					var nodes = that.groupTree.getNodeByParam('id',that.userOrg.orgId);
		        	that.groupTree.reAsyncChildNodes(nodes, 'refresh', false);
				});
				
				//查询树
				$('body').on('click',"#searchOrg",function(e){
					e.preventDefault();
					
					var nodes = that.groupTree.getNodeByParam('id',that.userOrg.orgId);
		        	that.groupTree.reAsyncChildNodes(nodes, 'refresh', false);
				});
				
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
			orgFormChangeType: function(){
				var that = this;
				if(that.orgFormType == 'info'){
					$("#orgInfoForm input").attr("readonly","readonly");
					$("#orgInfoForm textarea").attr("readonly","readonly");
					$("#orgInfoForm select").attr("disabled","disabled");
					
					//按钮
					$("#orgInfoForm #addOrg").show();
					$("#orgInfoForm #toUpdateSubmit").show();
					$("#orgInfoForm #deleteOrg").show();
					
					$("#orgInfoForm #orgFormSubmit").hide();
					$("#orgInfoForm #cancelSubmit").hide();
				}else{
					$("#orgInfoForm input").attr("readonly",false);
					$("#orgInfoForm textarea").attr("readonly",false);
					$("#orgInfoForm select").attr("disabled",false);
					
					//按钮
					$("#orgInfoForm #orgFormSubmit").show();
					$("#orgInfoForm #cancelSubmit").show();
					
					$("#orgInfoForm #addOrg").hide();
					$("#orgInfoForm #toUpdateSubmit").hide();
					$("#orgInfoForm #deleteOrg").hide();
				}
			},
	        initTree: function(){
	        	var that = this;
	        	//ztree 处理 start
	        	var setting = {
	        		view: {
	        			selectedMulti: false
	        		},
	        		data:{
	        			dataTransform:function(dataArr){
	        				//console.log(dataArr);
	        				var newData = [];
	        				for(var i = 0;i< dataArr.length;i++){
	        					var isParent = true;
	        					var node = {
	        							id:dataArr[i].orgId,
	        							name:dataArr[i].orgName,
	        							pId:dataArr[i].parentOrgId,
	        							isParent:isParent
	        							
	        					};
	        					newData.push(node);
	        				}
	        				//console.log(newData);
	        				return newData;
	        			}
	        		},
	        		async: {
	        			enable: true,
	        			dataType: 'json',
	        			url:"/org/getOrgNodes",
	        			autoParam:["id=orgId", "name=n", "level=lv"],
	        			otherParam:{
	        				"orgTypeTree":$('#orgTypeTree').val()
	        			}
	        		},
	        		callback: {
	        			beforeClick: beforeClick
	        		}
	        	};

	        	function beforeClick(treeId, treeNode) {
	        		//console.log(treeNode);
	        		//判断是哪个tab激活
	        		that.orgIdSelect = treeNode.id;
	        		that.orgIdSelectName = treeNode.name;
	        		that.orgIdParent = treeNode.pId;
	        		
	        		var $tab = $('#myTab4 .active a');
	        		if('operatorListTab'==$tab.attr('id')){
	        			//that.operatorTable();
	        			that.operatorObj.initTable({
		    				orgId : that.orgIdSelect,
		    				orgName : that.orgIdSelectName
		    			});
	    			}else if('orgListTab'==$tab.attr('id')){
	    				that.orgTable();
	    			}else if('orgPermissionTab'==$tab.attr('id')){
	    				that.orgPermission();
	    			}else if('orgInfoTab'==$tab.attr('id')){
	    				that.loadGroupInfo(treeNode.id,'info');
	    			}
	        		
	        	}
	        	
	        	var zNodes =[
	        		{ id:that.userOrg.orgId, pId:0, name:that.userOrg.orgName,isParent:that.userOrg.isParent}
	        	];

	        	that.groupTree = $.fn.zTree.init($("#groupTree"), setting, zNodes);
	        	//var zTree = $.fn.zTree.getZTreeObj("groupTree");
	        	var nodes = that.groupTree.getNodeByParam('id',that.userOrg.orgId);
	        	that.groupTree.expandNode(nodes,true,false,false,false);  
	        },
	        loadGroupInfo : function(orgId,type){
	        	var that = this;
	        	that.orgFormType = type;
	    		utilsAi.ajax({
	    			url:"/org/editOrgForm"
	    			,data: "orgId="+orgId+"&type="+type
	    			,success:function(result){
	    				$('#groupInfo').html(result);
	    				that.orgFormChangeType();
	    			}
	    		});
	    	},
	    	initTab:function(){
	    		var that = this;
	    		$('#myTab4 a').click(function(){
	    			//选中树的值和之前不同，刷新tab里面数据
	    			var orgId = that.orgIdSelect || that.userOrg.orgId;
	    			var $tab = $(this);
	    			
	    			if('operatorListTab'==$tab.attr('id')){
	    				if(orgId != that.tabActiveOperatorId){
			    			that.tabActiveOperatorId = orgId;
			    			//that.operatorTable();
			    			that.operatorObj.initTable({
			    				orgId : that.orgIdSelect || that.userOrg.orgId,
			    				orgName: that.orgIdSelectName || that.userOrg.orgName
			    			});
	    				}
	    			}else if('orgListTab'==$tab.attr('id')){
	    				if(orgId != that.tabActiveOrgId){
			    			that.tabActiveOrgId = orgId;
			    			that.orgTable();
	    				}
	    			}else if('orgPermissionTab'==$tab.attr('id')){
	    				if(orgId != that.tabActivePermissionId){
			    			that.tabActivePermissionId = orgId;
			    			that.orgPermission();
	    				}
	    			}else if('orgInfoTab'==$tab.attr('id')){
	    				if(orgId != that.tabActiveInfoId){
			    			that.tabActiveInfoId = orgId;
			    			that.loadGroupInfo(that.orgIdSelect,'update');
	    				}
	    			}
	    		});
	    	},
	    	operatorTable:function(){
	    		var that = this;
	    		var $tabEl = $('#operatorTable');
	    		
	    		if(that.operatorTableObj == null){
	    			that.operatorTableObj = $tabEl.dataTable(utilsAi.dataTableInit({
						id:'operatorTable',
						checkId:'operatorId',
						"sAjaxSource": "/org/operateList",
						"fnServerParams": function ( aoData ) {
						    aoData.push( { 
						    	"name": "orgId", "value": that.orgIdSelect || that.userOrg.orgId
//						    },{
//						    	"name": "createTimeStart", "value":that.createTime.getStart()
//						    },{
//						    	"name": "createTimeEnd", "value":that.createTime.getEnd()
						    },{
						    	"name": "operatorNameS", "value":$('#operatorNameS').val()
						    } );
						},
						"aoColumnDefs": [{
								"aTargets": [1],
						    	mData: "operatorName"
						    },{
						    	"aTargets": [2],
						    	mData: "orgName"
						    }
						 ],
						 //hasCheck:false,
						 hasButton:true,
						 buttonType : ['info','edit','del']
					}));
	    			
	    			
	    			$tabEl.on('click', '.openInfo', function() {
	    			    var row = $(this).closest('tr')[0];
	    			    var rowData = that.operatorTableObj.fnGetData(row); 
	    			    
	    			    if (rowData) {
	    			        var rowId = rowData.id;
	    			        console.log(rowId);
	    			        // Use your rowId value the way you like
	    			        $('#modal-table').modal('toggle');
	    			    }
	    			});
	    			
	    			$('#searchFormSubmit').click(function(){
	    				that.operatorTableObj.fnDraw();
	    			});
	    			
				}else{
					that.operatorTableObj.fnDraw();
				}
	    	},
	    	orgTable:function(){
	    		var that = this;
	    		var $tabEl = $('#orgTable');
	    		
	    		if(that.orgTableObj == null){
	    			that.orgTableObj = $tabEl.dataTable(utilsAi.dataTableInit({
						id:'orgTable',
						checkId:'orgId',
						"sAjaxSource": "/org/orgList",
						"fnServerParams": function ( aoData ) {
							utilsAi.dataTableParam(aoData,'searchOrgForm',function(aoData,formData){
								aoData.push( { 
							    	"name": "orgId", "value": that.orgIdSelect || that.userOrg.orgId
							    } );
							});
//						    aoData.push( { 
//						    	"name": "orgId", "value": that.orgIdSelect || that.userOrg.orgId
//						    },{
//						    	"name": "orgNameOrgS", "value":$('#orgNameOrgS').val()
//						    },{
//						    	"name": "orgTypeOrgS", "value":$('#orgTypeOrgS').val()
//						    } ,{
//						    	"name": "isAllOrg", "value":$('#isAllOrg').is(':checked') ? 1 : 0
//						    } ,{
//						    	"name": "provinceCodeOrgS", "value":$('#provinceCodeOrgS').val()
//						    } ,{
//						    	"name": "cityCodeOrgS", "value":$('#cityCodeOrgS').val()
//						    } ,{
//						    	"name": "districtCodeOrgS", "value":$('#districtCodeOrgS').val()
//						    }  );
						},
						"aoColumnDefs": [{
								"aTargets": [1],
						    	mData: "orgName"
						    },{
						    	"aTargets": [2],
						    	mData: "orgCode"
						    }
						 ]
						 //,hasCheck:false
//						 ,hasButton:true
//						 ,buttonType : ['info','edit','del']
					}));
	    			
	    			
//	    			$tabEl.on('click', '.openInfo', function() {
//	    			    var row = $(this).closest('tr')[0];
//	    			    var rowData = that.orgTableObj.fnGetData(row); 
//	    			    
//	    			    if (rowData) {
//	    			        var rowId = rowData.id;
//	    			        console.log(rowId);
//	    			        // Use your rowId value the way you like
//	    			        $('#modal-table').modal('toggle');
//	    			    }
//	    			});
	    			
	    			$('#searchOrgSubmit').click(function(){
	    				that.orgTableObj.fnDraw();
	    			});
	    			
				}else{
					that.orgTableObj.fnDraw();
				}
	    	},
	    	orgPermission:function(){
	    		var that = this;
	    		var $tabEl = $('#permissionTable');
	    		
	    		if(that.permissionTableObj == null){
	    			that.permissionTableObj = $tabEl.dataTable(utilsAi.dataTableInit({
						id:'permissionTable',
						checkId:'operatorId',
						"sAjaxSource": "/org/operateList",
						"fnServerParams": function ( aoData ) {
						    aoData.push( { 
						    	"name": "orgId", "value": that.orgIdSelect || that.userOrg.orgId
//						    },{
//						    	"name": "createTimeStart", "value":that.createTime.getStart()
//						    },{
//						    	"name": "createTimeEnd", "value":that.createTime.getEnd()
						    },{
						    	"name": "operatorNameS", "value":$('#operatorNameS').val()
						    } );
						},
						"aoColumnDefs": [{
								"aTargets": [1],
						    	mData: "operatorName"
						    },{
						    	"aTargets": [2],
						    	mData: "orgName"
						    },{
						    	"aTargets": [3],
						    	mData: "operatorId",
						    	mRender: function (data, type, full, meta){
					                return '<label> <select > <option>1</option> <option>2</option> <option>3</option> </select> </label>';
					            }
						    }
						 ],
						 //hasCheck:false,
						 hasButton:true,
						 buttonType : ['info','edit','del']
					}));
	    			
	    			
	    			$tabEl.on('click', '.openInfo', function() {
	    			    var row = $(this).closest('tr')[0];
	    			    var rowData = that.permissionTableObj.fnGetData(row); 
	    			    
	    			    if (rowData) {
	    			        var rowId = rowData.id;
	    			        console.log(rowId);
	    			        // Use your rowId value the way you like
	    			        $('#modal-table').modal('toggle');

	    			    }
	    			});
	    			
//	    			$('#searchOrgForm').click(function(){
//	    				that.orgTableObj.fnDraw();
//	    			});
	    			
				}else{
					that.permissionTableObj.fnDraw();
				}
	    	},
	    	formInit:function(){
	    		var that = this;
	    		$("#orgInfoForm").validate({
	    			rules: {
	    				orgCode: {
	    					required: true,
	    					maxlength: 12
	    				},
	    				orgName: {
	    					required: true,
	    					maxlength: 10
	    				},
	    				remarks: {
	    					maxlength: 200
	    				},
	    				orgType: "required",
	    				adminLevel:"required",
//	    				businessType:"required",
	    				webUrl: { url: true },
	    				email: { email: true },
	    				postcode: { isZipCode: true },
	    				faxNo: { isTel: true },
	    				telNo:{ isTel: true },
	    				orgAddr: { maxlength: 100 },
	    				contactTel: { isPhone: true },
	    				orderId:{
	    					required:true,
	    					digits:true,
	    					maxlength: 5
	    				}
	    			},
	    			messages: {
	    				orgCode:{
	    					required: "请输入组织代码",
	    					maxlength: '组织代码不能大于{0}个字'
	    				},
	    				orgName:{
	    					required: "请输入组织名称",
	    					maxlength: '组织名称不能大于{0}个字'
	    				}/*,
	    				remarks: {
	    					maxlength: '备注不能大于{0}个字'
	    				},
	    				orgType: "请选择组类型",
	    				orgIntroduct: {
	    					maxlength: '组织简介不能大于{0}个字'
	    				}*/
	    			}
	    		});
	    		
	    		//form提交
	    		$('#orgFormSubmit').on('click',function(){
	    			//console.log(utilsAi.formArrayUtil($("#orgInfoForm").serializeArray())[0]);
	    			if($("#orgInfoForm").valid() === true ){
	    				var formData = utilsAi.formArrayUtil($("#orgInfoForm").serializeArray())[0];
	    				//console.log(formData);
	    				var urlStr = '/org/saveOrg';
	    				if(that.formEditType=='update'){
	    					urlStr = '/org/updateOrg';
	    				}
	    				
	    				utilsAi.ajax({
	    					url:urlStr,
	    					data:formData,
	    					success:function(data){
	    						utilsAi.alert('保存成功');
	    						//刷新树
	    						if(that.formEditType=='update'){
	    							var nodesEdit = that.groupTree.getNodeByParam('id',$('#orgId').val());
	    							nodesEdit.name = $('#orgName').val();
	    							that.groupTree.updateNode(nodesEdit);
	    							that.loadGroupInfo($('#orgId').val(),'info');
	    						}else{
	    							var nodesEdit = that.groupTree.getNodeByParam('id',$('#parentOrgId').val());
	    							that.groupTree.reAsyncChildNodes(nodesEdit, 'refresh', false);
	    							
	    							that.loadGroupInfo(that.orgIdSelect,'add');
	    						}
	    					}
	    				});
	    			}
	    		});
	    	}
	};
	
})(jQuery);