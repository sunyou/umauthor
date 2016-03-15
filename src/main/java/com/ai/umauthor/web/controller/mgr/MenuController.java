package com.ai.umauthor.web.controller.mgr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxPageBean;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmSystem;
import com.ai.umauthor.server.service.mrg.interfaces.MenuSV;
import com.ai.umauthor.server.service.mrg.interfaces.SystemSV;
import com.ai.umauthor.server.service.mrg.model.QryUmMenu;
import com.ai.umauthor.web.helper.TreeNode;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private static final Logger log = LoggerFactory.getLogger(MenuController.class);
	@Autowired
	private MenuSV menuSV;
	@Autowired
	private SystemSV systemSV;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) throws Exception{
		List<UmSystem> syslist = systemSV.querySystemList();
		model.addAttribute("syslist", syslist);
    	return "menu/menu";
    }
	
	@RequestMapping(value="/tree", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean menuTree(HttpServletRequest request,Long pmenuId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			//以下方法带角色权限过滤，不方便测试
//			List<UmMenu> list = menuSV.queryMenusByOperator("", "1", pmenuId+"");
			int level = 0;
			List<TreeNode> treeNodes = buildTreeRecursion("0",level);
			if(!treeNodes.isEmpty()){
				treeNodes.get(0).setState(new TreeNode.State(true));//设置第一层为展开状态
			}
			//构造一个根节点
			TreeNode root = new TreeNode();
			root = new TreeNode();
			root.setId("0");
			root.setPid("-1");
			root.setText("系统菜单列表");
			root.setIcon("icon-folder-close-alt");
			root.setTags(new String[]{"0"});
			root.setIsLeaf(false);
			root.setState(new TreeNode.State(true));
			root.setNodes(treeNodes);
			List<TreeNode> rootlist = new ArrayList<TreeNode>();
			rootlist.add(root);
			result.setData(rootlist);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean menuList(HttpServletRequest request,Long pmenuId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			//以下方法带角色权限过滤，不方便测试
//			List<UmMenu> list = menuSV.queryMenusByOperator("", "1", pmenuId+"");
			List<UmMenu> list = menuSV.queryMenuListByParentMenuId(pmenuId+"","");
			result.setData(list);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value = "/listByParentMenuId", method = { RequestMethod.POST, RequestMethod.GET })
	public String listByParentMenuId(HttpServletRequest request, Model model, Long parentMenuId) throws Exception {
		model.addAttribute("menuList", menuSV.queryMenuListByParentMenuId(parentMenuId+"",""));
		return "menu/orderMenu";
	}
	
	@RequestMapping(value="/saveMenuOrder", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean saveMenuOrder(HttpServletRequest request,String orderedJson) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			menuSV.saveOrder(orderedJson);
			result.setCode(AjaxPageBean.SUCCESS_CODE);
			result.setMessage("保存成功！");
		} catch (Exception e) {
			log.error("queryMenuById:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	public List<TreeNode> buildTreeRecursion(String parentMenuId,int level) throws BusinessException{
		List<UmMenu> list = menuSV.queryMenuListByParentMenuId(parentMenuId,"");
		List<TreeNode> nodeList = new ArrayList<TreeNode>();
		TreeNode node = null;
		level++;
		for (UmMenu menu : list) {
			node = new TreeNode();
			node.setId(menu.getMenuId()+"");
			node.setPid(menu.getParentMenuId()+"");
			node.setText(menu.getMenuName());
			
			node.setLevel(level);
			node.setUrl(menu.getMenuUrl());
			node.setTags(new String[]{node.getId()});
			List<TreeNode> templist = buildTreeRecursion(menu.getMenuId()+"",level);
			if(templist != null && templist.size() > 0){
				node.setIcon("icon-folder-close-alt");
//				node.setSelectedIcon("icon-folder-open-alt");
				node.setIsLeaf(false);
				node.setNodes(templist);
			}else{
				node.setIcon("icon-bookmark-empty");
				node.setSelectedIcon("icon-bookmark");
				node.setIsLeaf(true);
			}
			nodeList.add(node);
		}
		return nodeList;
	}
	
	@RequestMapping(value="/tree2", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean getNodes(HttpServletRequest request,String parentMenuId,String systemId) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			List<UmMenu> list = menuSV.queryMenuListByParentMenuId(parentMenuId,systemId);
			List<QryUmMenu> treelist = new ArrayList<QryUmMenu>();
			QryUmMenu node = null;
			for (UmMenu menu : list) {
				node = new QryUmMenu();
				BeanUtils.copyProperties(node, menu);
				if(menuSV.hasChildrens(menu.getMenuId()+"")){
					node.setIsParent(true);
				}else{
					node.setIsParent(false);
				}
				treelist.add(node);
			}
			result.setData(treelist);
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping(value="/id", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean queryMenuById(HttpServletRequest request,Long id) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		try {
			UmMenu menu = menuSV.queryMenuById(id);
			result.setData(menu);
			result.setCode(AjaxPageBean.SUCCESS_CODE);
			result.setMessage("查询成功！");
		} catch (Exception e) {
			log.error("queryMenuById:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	} 
	
	@RequestMapping(value="/saveMenu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean saveMenu(HttpServletRequest request,UmMenu menu){
		AjaxPageBean result = new AjaxPageBean();
		try {
			menuSV.insertMenu(menu);
			result.setCode(AjaxPageBean.SUCCESS_CODE);
			result.setMessage("操作成功！");
		} catch (Exception e) {
			log.error("editMenu:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	} 
	
	@RequestMapping(value="/editMenu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean editMenu(HttpServletRequest request,UmMenu menu){
		AjaxPageBean result = new AjaxPageBean();
		try {
			menuSV.modifyMenu(menu);
			result.setCode(AjaxPageBean.SUCCESS_CODE);
			result.setMessage("操作成功！");
		} catch (Exception e) {
			log.error("editMenu:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	} 
	
	@RequestMapping(value="/delMenu", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean delMenu(HttpServletRequest request,Long menuId){
		AjaxPageBean result = new AjaxPageBean();
		try {
			menuSV.deleteMenu(menuId);
			result.setCode(AjaxPageBean.SUCCESS_CODE);
			result.setMessage("操作成功！");
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
}
