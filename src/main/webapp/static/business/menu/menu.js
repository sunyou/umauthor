;(function(){
	var treeView;
	var liObj;//点击树位置
	var menuInfoObj;//点击节点信息
	
	$("#parentMenuNameDiv").hide();
	$("#menuUrlDiv").hide();
	//加载树
	//loadMenuTree();
	
	$("#menuForm").validate({
		rules: {
			menuName: {
				required: true,
				maxlength: 32
			},
			menuCode: {
				required: true,
				maxlength: 32
			},
			menuDesc: {
				maxlength: 200
			}
		},
		messages: {
			menuName:{
				required: "请输入菜单名称",
				maxlength: '菜单名称不能大于{0}个字'
			},
			menuCode:{
				required: "请输入菜单编码",
				maxlength: '菜单编码不能大于{0}个字'
			}
		}
	});
	
	$('#addBtn').on('click',function(){
		if($("#menuId").val() == ''){
			utilsAi.showError("提示","请选择一个父节点");
			return false;
		}
		if($("#nodeLevel").val() == '4'){
			utilsAi.showError("提示","目前最多允许建立4级菜单");
			return false;
		}
		$("#menuUrlDiv").hide();
		$("#parentMenuId").val($("#menuId").val());//设置当前选择节点为父节点
		resetMenuInfo();
		$("#addBtn").fadeOut("fast");
		$("#modBtn").fadeOut("fast");
		$("#delBtn").fadeOut("fast");
		$("#ordBtn").fadeOut("fast");
		$("#submitBtn").fadeIn("slow");
		$("#cancelBtn").fadeIn("slow");
		$("#parentMenuNameDiv").show();
		$("input").attr("readonly",false);
		$("textarea").attr("readonly",false);
		$("select").attr("disabled",false);
		$("#op").val("insert");
		return false;
	});
	
	$('#modBtn').on('click',function(){
		if($("#menuId").val() == ''){
			utilsAi.showError("提示","请选择一个节点");
			return false;
		}
		$("#addBtn").fadeOut("fast");
		$("#modBtn").fadeOut("fast");
		$("#delBtn").fadeOut("fast");
		$("#ordBtn").fadeOut("fast");
		$("#submitBtn").fadeIn("slow");
		$("#cancelBtn").fadeIn("slow");
		$("input").attr("readonly",false);
		$("textarea").attr("readonly",false);
		$("select").attr("disabled",false);
		$("#op").val("update");
		return false;
	});
	
	$('#ordBtn').on('click',function(){
		if($("#menuId").val() == ''){
			utilsAi.showError("提示","请选择一个节点");
			return false;
		}
		loadMenuList($("#menuId").val());		
		return false;
	});
	
	$("#delBtn").on('click', function() {
		if($("#menuId").val() == ''){
			utilsAi.showError("提示","请选择一个子节点");
			return false;
		}
		bootbox.setDefaults({"locale":"zh_CN"});
		bootbox.confirm('是否确认删除菜单"'+$("#parentMenuName").val()+'"?', function(result) {
			if(result) {
				utilsAi.ajax({
					url:'/menu/delMenu',
					data:{"menuId":$("#menuId").val()},
					success:function(data){
						if(data.code == "100"){
							//loadMenuTree();//重绘菜单树
							refleshTree($("#parentMenuId").val());
							resetMenuInfo();
							reset();
						}
						utilsAi.alert(data.message);
					}
				});
			}
		});
		return false;
	});
	
	$("#submitBtn").on('click', function() {
		if($("#op").val() == "insert"){
			if($("#menuForm").valid() === true ){
				$("#menuId").val('');
				var formData = utilsAi.formArrayUtil($("#menuForm").serializeArray())[0];
				utilsAi.ajax({
					url:'/menu/saveMenu',
					type:'post',
					data:formData,
					success:function(data){
						utilsAi.alert(data.message);
						resetMenuInfo();
						reset();
						//loadMenuTree();//重绘菜单树
						refleshTree($("#parentMenuId").val());
					}
				});
			}
		}else if($("#op").val() == "update"){
			if($("#menuForm").valid() === true ){
				var formData = utilsAi.formArrayUtil($("#menuForm").serializeArray())[0];
				utilsAi.ajax({
					url:'/menu/editMenu',
					data:formData,
					success:function(data){
						utilsAi.alert(data.message);
						resetMenuInfo();
						reset();
						refleshTree($("#parentMenuId").val());
//						loadMenuTree();//重绘菜单树
					}
				});
			}
		}
		return false;
	});
	
	
	$("#cancelBtn").on('click', function() {
		reset();
		if($("#op").val() == "insert"){
			resetMenuInfo();
		}
		$("#op").val('');
		resetMenuInfo();
		
		$("#parentMenuId").val(menuInfoObj.parentMenuId);
		$("#menuId").val(menuInfoObj.menuId);
		$("#menuName").val(menuInfoObj.menuName);
		$("#menuCode").val(menuInfoObj.menuCode);
		$("#menuType").val(menuInfoObj.menuType);
		if($("#menuType").val() == "2" || $("#menuType").val() == "3"){
			$("#menuUrlDiv").hide();
			$("#menuUrl").val('');
		}else{
			$("#menuUrlDiv").show();
			$("#menuUrl").val(menuInfoObj.menuUrl);
		}
		$("#menuState").val(menuInfoObj.menuState);
		$("#menuImage").val(menuInfoObj.menuImage);
		$("#menuOrder").val(menuInfoObj.menuOrder);
		$("#menuDesc").val(menuInfoObj.menuDesc);
		$("#systemId").val(menuInfoObj.systemId);
		return false;
	});
	
	$("#menuType").on('change',function(){
		$("#menuUrl").val('');
		if($("#menuType").val() == "2" || $("#menuType").val() == "3"){
			$("#menuUrlDiv").hide();
			$("#menuUrl").val('');
		}else{
			$("#menuUrlDiv").show();
		}
	});
	
	function loadMenuTree(){
		utilsAi.ajax({
			url:'/menu/tree',
			data:'pmenuId=0',
			dataType:'json',
			success:function(json){
				treeView = $('#treeview').treeview({
					data: json.data,
					onNodeSelected: function(event, node) {
						$("#parentMenuId").val(node.pid);
						$("#menuId").val(node.id);
						$("#parentMenuName").val(node.text);
						$("#nodeLevel").val(node.level);
						$("#addBtn").fadeIn("slow");
						$("#modBtn").fadeIn("slow");
						$("#delBtn").fadeIn("slow");
						$("#submitBtn").fadeOut("fast");
						$("#cancelBtn").fadeOut("fast");
						$("#parentMenuNameDiv").hide();
						menuInfo(node.id);
					}
				});
			}
		});
	}
	
	//加载数据
	function loadMenuList(parentMenuId){
		utilsAi.ajax({
			url:"/menu/listByParentMenuId"
			,async:true
			,data: "parentMenuId="+parentMenuId
			,success:function(result){
				$('#order-modal').html(result);
				$('#modal-table').modal('toggle');
			}
		});
	}
	
	//点击节点查看菜单详情
	function menuInfo(menuId){
		utilsAi.ajax({
			url:'/menu/id',
			type:'post',
			data:{"id":menuId},
			success:function(json){
				if(json.code == "100"){
					menuInfoObj = json.data;
					$("#menuName").val(json.data.menuName);
					$("#menuCode").val(json.data.menuCode);
					$("#menuType").val(json.data.menuType);
					if($("#menuType").val() == "2" || $("#menuType").val() == "3"){
						$("#menuUrlDiv").hide();
						$("#menuUrl").val('');
					}else{
						$("#menuUrlDiv").show();
						$("#menuUrl").val(json.data.menuUrl);
					}
					$("#menuImage").val(json.data.menuImage);
					$("#menuOrder").val(json.data.menuOrder);
					$("#menuState").val(json.data.menuState);
					$("#menuDesc").val(json.data.menuDesc);
					$("#systemId").val(json.data.systemId);
					$("input").attr("readonly","readonly");
					$("textarea").attr("readonly","readonly");
					$("select").attr("disabled","disabled");
					$("#system").attr("disabled",false);
				}else{
					utilsAi.alert(json.message);
				}
			}
		});
	}

	function resetMenuInfo(){
		$("#menuName").val('');
		$("#menuCode").val('');
		$("#menuUrl").val('');
		$("#menuType").val('3');
		$("#menuState").val('1');
		$("#systemId").val('1');
		$("#menuDesc").val('');
		//$("#menuId").val('');
		//var li = liObj = $('#treeview').find("li.node-selected");
		//li.removeClass("node-selected").attr("style","color:undefined;background-color:undefined;");
	}
	
	function reset(){
		$("#addBtn").fadeIn("slow");
		$("#modBtn").fadeIn("slow");
		$("#delBtn").fadeIn("slow");
		$("#ordBtn").fadeIn("slow");
		$("#submitBtn").fadeOut("fast");
		$("#cancelBtn").fadeOut("fast");
		$("#parentMenuNameDiv").hide();
		$("input").attr("readonly","readonly");
		$("textarea").attr("readonly","readonly");
		$("select").attr("disabled","disabled");
		$("#system").attr("disabled",false);
	}
	
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
					//var isParent = true;
					var node = {
						id:dataArr[i].menuId,
						name:dataArr[i].menuName,
						pId:dataArr[i].parentMenuId,
						isParent:dataArr[i].isParent,
						menuCode:dataArr[i].menuCode,
						menuDesc:dataArr[i].menuDesc,
						menuImage:dataArr[i].menuImage,
						menuOrder:dataArr[i].menuOrder,
						menuState:dataArr[i].menuState,
						menuType:dataArr[i].menuType,
						sys:dataArr[i].systemId,
						menuUrl:dataArr[i].menuUrl
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
			url:"/menu/tree2",
			autoParam:["id=parentMenuId", "name=n", "level=lv","sys=systemId"]
		},
		callback: {
			beforeClick: beforeClick
		}
	};

	function beforeClick(treeId, node) {
		//console.log(node);
		$("#parentMenuId").val(node.pId);
		$("#menuId").val(node.id);
		$("#parentMenuName").val(node.name);
		$("#nodeLevel").val(node.level);
		$("#addBtn").fadeIn("slow");
		$("#modBtn").fadeIn("slow");
		$("#delBtn").fadeIn("slow");
		$("#submitBtn").fadeOut("fast");
		$("#cancelBtn").fadeOut("fast");
		$("#parentMenuNameDiv").hide();
		menuInfo(node.id);
	}
	
	var zNodes =[
		{ id:0, pId:0,'sys':null, name:'系统菜单列表',isParent:true}
	];

	var groupTree = $.fn.zTree.init($("#groupTree"), setting, zNodes);
	var nodes = groupTree.getNodeByParam('id','0');
	groupTree.expandNode(nodes,true,false,false,false);  
	
	function refleshTree(parentMenuId){
		var nodesEdit = groupTree.getNodeByParam('id',parentMenuId);
		groupTree.reAsyncChildNodes(nodesEdit, 'refresh', false);
	}
	
	$("#system").bind("change",function(){
		var nodesEdit = groupTree.getNodeByParam('id','0');
		nodesEdit.sys = $("#system").val();
		groupTree.reAsyncChildNodes(nodesEdit, 'refresh', false); 
	});
})(jQuery);