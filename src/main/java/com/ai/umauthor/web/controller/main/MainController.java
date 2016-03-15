package com.ai.umauthor.web.controller.main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ai.common.domain.AjaxBean;
import com.ai.common.domain.AjaxPageBean;
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.model.UmStationtype;
import com.ai.umauthor.server.service.mrg.interfaces.MenuSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationSV;
import com.ai.umauthor.server.service.mrg.model.MenuInfo;
import com.ai.umauthor.server.service.mrg.model.SessionUser;

@Controller
// @RequestMapping("/login")
public class MainController {
	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	@Autowired
	StationSV StationSV;

	@Autowired
	MenuSV menuSV;

	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(HttpServletRequest request, Model model) throws Exception {
		return "main";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) throws Exception {
		return "main";
	}
	
	
	// , method = RequestMethod.POST

	/**
	 * json格式的菜单输出
	 * 
	 * @param menuId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/main/menu")
	@ResponseBody
	public AjaxBean menuJson(String menuId, HttpServletRequest request) throws Exception {
		AjaxBean resultBean = new AjaxBean();
		List<UmMenu> umMenusTmp = new ArrayList<UmMenu>();
		SessionUser<UmOperator, UmStationtype, UmMenu, Object> sessionUserInfo = WebUtils.getSessionUserInfo();
		List<UmMenu> umMenus = sessionUserInfo.getMenus();
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
		UmMenu umMenuTmp = null;
		for (int i = 0; i < umMenus.size(); i++) {
			umMenuTmp = umMenus.get(i);
			if (umMenuTmp.getParentMenuId().toString().equals("0")) {
				menuInfos.add(getMenuInfoByTree(umMenuTmp, umMenus));
			}
		}
		resultBean.setCode(AjaxPageBean.SUCCESS_CODE);
		resultBean.setData(menuInfos);
		return resultBean;
	}

	/**
	 * 页面格式输出
	 * 
	 * @param menuId
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/main/menuTest")
	@ResponseBody
	@RequiresPermissions("provinceGovernment")
	public ModelAndView menuPage(String menuId, HttpServletRequest request) throws Exception {
		AjaxBean resultBean = new AjaxBean();
		List<UmMenu> umMenusTmp = new ArrayList<UmMenu>();
		SessionUser<UmOperator, UmStation, UmMenu, Object> sessionUserInfo = WebUtils.getSessionUserInfo();
		List<UmMenu> umMenus = sessionUserInfo.getMenus();
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();
		UmMenu umMenuTmp = null;
		for (int i = 0; i < umMenus.size(); i++) {
			umMenuTmp = umMenus.get(i);
			if (umMenuTmp.getParentMenuId().toString().equals("0")) {
				menuInfos.add(getMenuInfoByTree(umMenuTmp, umMenus));
			}
		}
		ModelAndView model = new ModelAndView("common_page/menu");
		model.addObject("menuInfos", menuInfos);
		return model;
		/*
		 * resultBean.setCode( AjaxPageBean.SUCCESS_CODE);
		 * resultBean.setData(menuInfos);
		 */
		// return resultBean;
	}

	private MenuInfo getMenuInfoByTree(UmMenu umMenu, List<UmMenu> umMenus) {
		MenuInfo menuInfo = new MenuInfo();
		menuInfo.setMenu(umMenu);
		for (int i = 0; i < umMenus.size(); i++) {
			if (umMenus.get(i).getParentMenuId().equals(umMenu.getMenuId())) {
				menuInfo.addChildMenu(getMenuInfoByTree(umMenus.get(i), umMenus));
			}
		}
		return menuInfo;
	}
}
