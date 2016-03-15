/*$(function () {
	loadMenu();
   
});*/
function loadMenu(){
	readMenu(0);
}
function readMenu(parentMenuId){
	
	utilsAi.ajax({
		url:"/main/menu"
		,data: "menuId="+parentMenuId
		,success:function(data){
			if(data.code==100){
				
				
				sohwMenu(data.data);
			}
			
		}
	});
}

function sohwMenu(data){
	var menuActiveId=getMenuActive();
	//[];
	
	var menuDatas=getMenuNode(data,menuActiveId);
/*	
		var menuData=getMenuNode(data[i]);
		menuData['id']=data[i]['menu']['menuId'];
		menuData['text']=data[i]['menu']['menuName'];
		menuData['icon']=data[i]['menu']['menuImage']||'icon-cog';
		menuData['url']=data[i]['menu']['menuUrl']||'#';
		
		if(data[i]['childList']){
			var childMenuList=data[i]['childList'];
			
			var childMenus=[];
			for (var j = 0; j < childMenuList.length; j++) {
				var childMenu=getMenuNode(childMenuList[j]);
				
				childMenus.push(childMenu);
			}
			menuData['menus']=childMenus;
		}
		
	}*/
	
	$('#menu').sidebarMenu({
		data :menuDatas
	});
	
//	 class="active"
//	console.log(menuDatas);
}


function getMenuNode(menuJson,menuActiveId){
	//console.log(menuJson);
	
	var menuDatas=[];
	//var menuActiveId="0";//getMenuActive();
	
	for (var i = 0; i < menuJson.length; i++) {
		var menuData={};
		menuData['id']=menuJson[i]['menu']['menuId'];
		menuData['text']=menuJson[i]['menu']['menuName'];
		menuData['icon']=menuJson[i]['menu']['menuImage']||'fa fa-cog';
		menuData['url']=menuJson[i]['menu']['menuUrl']||'#';
		menuData['order']=menuJson[i]['menu']['menuOrder']||'0';
		
		if(menuActiveId==menuJson[i]['menu']['menuId']){
			menuData['active']=true;
		}else{
			menuData['active']=false;
		}
		if(menuJson[i]['childList']){
			var childMenus=getMenuNode(menuJson[i]['childList'],menuActiveId);
			childMenus.sort(function(a,b){return sortMenu(a,b);});
			menuData['menus']=childMenus;
		}
		menuDatas.push(menuData);
	}
	
	
	//console.log(menuDatas);
	return menuDatas;
}

function sortMenu(a,b){

	return a['order']>b['order']?1:-1;
	//alert(a);
}
