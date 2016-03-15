package com.ai.umauthor.web.controller.mgr;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxBean;
import com.ai.common.domain.AjaxPageBean;
import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.TableCols;
import com.ai.umauthor.server.model.UmDictitem;
import com.ai.umauthor.server.service.mrg.interfaces.DictitemSV;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 字典controller
 * 
 * @author guohui
 * @date 2016年1月26日
 */
@Controller
@RequestMapping("/dictitem")
public class DictitemController {
	private static final Logger log = LoggerFactory.getLogger(DictitemController.class);

	@Autowired
	private DictitemSV dictitemSV;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) throws Exception {
		return "dictitem/dictitemList";
	}

	@RequestMapping(value = "/getEditDictitem", method = { RequestMethod.POST, RequestMethod.GET })
	public String getEditDictitem(String dictKey, String itemKey, String type, HttpServletRequest request, Model model)
			throws Exception {
		if ("add".endsWith(type)) {
			model.addAttribute("type", "add");
			return "dictitem/editDictitem";
		} else {
			model.addAttribute("type", "update");
			model.addAttribute("dictitem", dictitemSV.getDictitemByPrimaryKey(dictKey, itemKey));
			return "dictitem/editDictitem";
		}

	}

	@RequestMapping("/addDictitem")
	@ResponseBody
	public AjaxBean addDictitem(String type, UmDictitem dictitem, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AjaxBean ajaxBean = new AjaxBean();
		try {
			if ("add".endsWith(type)) {
				dictitemSV.insertDictitem(dictitem);// 添加
			} else {
				dictitemSV.modifyDictitem(dictitem);// 更新
			}
			ajaxBean.setCode(AjaxBean.SUCCESS_CODE);
			ajaxBean.setMessage("操作成功");
		} catch (BusinessException e) {
			e.printStackTrace();
			ajaxBean.setCode(AjaxBean.ERROR_CODE);
			ajaxBean.setMessage("操作失败" + e.getMessage());
			log.debug(e.getMessage());
		}
		return ajaxBean;

	}

	@RequestMapping("/deleteDictitem")
	@ResponseBody
	public AjaxBean deleteDictitem(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxBean ajaxBean = new AjaxBean();
		String jsonMessage = request.getParameter("primaryKeys");// 获取json串参数
		JSONArray jsonArray = JSONArray.parseArray(jsonMessage);
		UmDictitem dictitem = null;
		JSONObject jsobj;
		try {
			for (int i = 0; i < jsonArray.size(); i++) {
				dictitem = new UmDictitem();
				jsobj = jsonArray.getJSONObject(i);
				dictitem.setDictKey(jsobj.getString("dictKey"));
				dictitem.setItemKey(jsobj.getString("itemKey"));
				// 逻辑删除
				dictitem.setItemState("0");
				dictitemSV.logicalDeleteByPrimaryKey(dictitem);
			}

			ajaxBean.setCode(AjaxBean.SUCCESS_CODE);
			ajaxBean.setMessage("操作成功");
		} catch (BusinessException e) {
			e.printStackTrace();
			ajaxBean.setCode(AjaxBean.ERROR_CODE);
			ajaxBean.setMessage("操作失败" + e.getMessage());
			log.debug(e.getMessage());
		}
		return ajaxBean;

	}

	@RequestMapping("/updateDictitem")
	@ResponseBody
	public AjaxBean updateDictitem(UmDictitem dictitem, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		AjaxBean ajaxBean = new AjaxBean();

		return ajaxBean;

	}

	@RequestMapping("/queryDictitemList")
	@ResponseBody
	public AjaxPageBean queryDictitemList(@RequestParam(value = "iDisplayStart", defaultValue = "0") int pageNo,
			@RequestParam(value = "iDisplayLength", defaultValue = "10") int pageSize, HttpServletRequest request)
			throws Exception {
		AjaxPageBean ajaxBean = new AjaxPageBean();
		String dictKey = (String) request.getParameter("dictKeyCon");
		String itemKey = (String) request.getParameter("itemKeyCon");
		String itemState = (String) request.getParameter("itemStateCon");
		int page = (pageNo / pageSize) + 1;
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		ajaxBean.setOther(sEcho);

		SqlCondition con = new SqlCondition();
		
		TableCols.UmDictitemKeyCols t = TableCols.UmDictitemKeyCols;
		if (!StringUtils.isEmpty(dictKey)) {
			con.like(t.dictKey, dictKey);
		}
		if (!StringUtils.isEmpty(itemKey)) {
			con.like(t.itemKey, itemKey);
		}
		if (!StringUtils.isEmpty(itemState)) {
			con.eq("item_state", itemState);
		}
		con.orderBy("dict_key,item_order");

		try {
			int total = dictitemSV.countDictitemByConditionTotal(con);
			con.page(page, pageSize);
			List<UmDictitem> list = dictitemSV.queryDictitemByCondition(con);
			
			ajaxBean.setTotal(total);
			ajaxBean.setCurrentPage(page);
			ajaxBean.setData(list);
		} catch (Exception e) {
			ajaxBean.setCode(AjaxBean.ERROR_CODE);
			ajaxBean.setMessage(e.getMessage());
			log.debug(e.getMessage());
		}

		return ajaxBean;

	}

	@RequestMapping("/queryDictitemByName")
	@ResponseBody
	public AjaxBean queryDictitemByPrimaryKey(String dictKey, String itemKey, Model model, HttpServletRequest request,
			HttpServletResponse response) {
		AjaxBean ajaxBean = new AjaxBean();

		return ajaxBean;

	}

}
