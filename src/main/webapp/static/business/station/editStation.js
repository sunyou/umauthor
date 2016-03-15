jQuery(function($) {
			
		$('#fuelux-wizard').ace_wizard({}).on('actionclicked.fu.wizard' , function(e, info){
			if(info.step == 0) {
				//if(!$('#stationForm').valid()) return false;
			}else if(info.step == 1){
				loadTree();
			}else if(info.step == 2){
				loadStationType();
			}
		}).on('finished.fu.wizard', function(e) {
			//第一步验证
			if(!$('#stationForm').valid()) return false;
			//第二步验证
			var treeObj = $.fn.zTree.getZTreeObj("groupTree");
	        var nodes = treeObj.getCheckedNodes(true);
	        if(nodes.length > 0){
		        var msg = "";
		        for (var i = 0; i < nodes.length; i++) {
		            msg += nodes[i].id+",";
		        }
		        $("#relaMenuIds").val(msg.substr(0,msg.length-1));
	        }else{
	        	return false;
	        }
			//第三部验证
			var items = [];
			$("#stationtypeTable input[type='checkbox'][name='checkId']:checked").each(function() {
				var $el = $(this);
				items.push($el.data('stationtypeid'));
			});
			if(items.length > 0){
				var msg = "";
		        for (var i = 0; i < items.length; i++) {
		            msg += items[i]+",";
		        }
		        $("#relaTypeIds").val(msg.substr(0,msg.length-1));
			}else{
				return false;
			}
			submit();//提交表单
		}).on('stepclick.fu.wizard', function(e){
			//return false;//prevent clicking on steps
		});
	
		//ztree 处理 start
		var zNodes =[
			{ id:0, pId:0, name:'系统菜单列表',isParent:true}
		];
		var setting = {
			check:{
		        enable: true
		    },
			view: {
				selectedMulti: true
			},
			data:{
				dataTransform:function(dataArr){
					var newData = [];
					for(var i = 0;i< dataArr.length;i++){
						var node = {
							id:dataArr[i].menuId,
							name:dataArr[i].menuName,
							pId:dataArr[i].parentMenuId,
							isParent:dataArr[i].isParent
						};
						newData.push(node);
					}
					return newData;
				}
			},
			async: {
				enable: true,
				dataType: 'json',
				url:"/menu/tree2",
				autoParam:["id=parentMenuId", "name=n", "level=lv"]
			},
			callback: {
				beforeClick: beforeClick
			}
		};

		function beforeClick(treeId, node) {
			
		}
		
		function refleshTree(parentMenuId){
			var nodesEdit = groupTree.getNodeByParam('id',parentMenuId);
			groupTree.reAsyncChildNodes(nodesEdit, 'refresh', false);
		}
		
		var groupTree = null;
		function loadTree(){
			groupTree = $.fn.zTree.init($("#groupTree"), setting, zNodes);
			var nodes = groupTree.getNodeByParam('id','0');
			groupTree.expandNode(nodes,true,false,false,false); 
		}
	
		$("#stationForm").validate({
			rules: {
				stationName: {
					required: true,
					maxlength: 32
				},
				groupId: {
					required: true
				}
			},
			messages: {
				stationName:{
					required: "请输入岗位名称",
					maxlength: '岗位名称不能大于{0}个字'
				},
				groupId:{
					required: "请选择岗位分类"
				}
			}
		});
		//step 3:
		var $operatorTabEl = $('#stationtypeTable');
		var oTable1 = null;
		
		function loadStationType(){
			if(oTable1 == null){
				oTable1 = $operatorTabEl.dataTable(utilsAi.dataTableInit({
					id:'stationtypeTable',
					checkId: 'stationtypeId',
					checkNames:['stationtypeId'],
					"sAjaxSource": "/stationtype/stationtypeList",
					"fnServerParams": function ( aoData ) {
					    aoData.push( { 
					    	"name": "stationtypeCode", "value": $("#stationtypeCode").val()
					    },{
					    	"name": "stationtypeName", "value": $("#stationtypeName").val()
					    },{
					    	"name": "stationtypeState", "value": $("#stationtypeState").val()
					    } );
					},
					"aoColumnDefs": [{
							"aTargets": [1],
					    	mData: "stationtypeCode"
					    },{
					    	"aTargets": [2],
					    	mData: "stationtypeName"
					    },{
					    	"aTargets": [3],
					    	mData: "stationtypeState",
							mRender: function (data, type, full, meta){
								var html;
								if (data=='1') {
									html='有效';
								}else {
									html='无效';
								}
				                return html;
				            }
					    }
					 ],
					 //hasCheck:false,
				}));
			}else{
				oTable1.fnDraw();
			}
		}
		
		//点击查询按钮
		$('#searchFormSubmit').click(function(){
			oTable1.fnDraw();
		});
		
		function submit(){
			return false;
			if($("#type").val() == "add"){
				if($("#stationForm").valid() === true ){
					var formData = utilsAi.formArrayUtil($("#stationForm").serializeArray())[0];
					utilsAi.ajax({
						url:'/station/presistent',
						type:'post',
						data:formData,
						success:function(data){
							utilsAi.alert(data.message);
							$('#modal-table').modal('toggle');
							window.umauthor.stationTable.fnDraw();
						}
					});
				}
			}else if($("#type").val() == "update"){
				if($("#stationForm").valid() === true ){
					var formData = utilsAi.formArrayUtil($("#stationForm").serializeArray())[0];
					utilsAi.ajax({
						url:'/station/presistent',
						data:formData,
						success:function(data){
							utilsAi.alert(data.message);
							$('#modal-table').modal('toggle');
							window.umauthor.stationTable.fnDraw();
						}
					});
				}
			}
		}
		
	});