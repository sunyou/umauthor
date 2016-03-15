package com.ai.umauthor.web.controller.mgr;

import java.util.Date;
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
import com.ai.umauthor.server.model.UmStationGroup;
import com.ai.umauthor.server.service.mrg.interfaces.DictitemSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationGroupSV;
import com.ai.umauthor.server.service.mrg.model.UmStationGroupListBean;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * 岗位分组控制器
 * 
 * @author guohui
 * @date 2016年2月2日
 */
@Controller
@RequestMapping("/stationGroup")
public class StationGroupController {
	private static final Logger log = LoggerFactory.getLogger(StationGroupController.class);

	@Autowired
	private StationGroupSV stationGroupSV;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) throws Exception {
		return "stationgroup/index";
	}

	@RequestMapping(value = "/getEditGroup", method = { RequestMethod.POST, RequestMethod.GET })
	public String getEditStationGroup(String groupId, String type, HttpServletRequest request, Model model)
			throws Exception {
		if ("add".equals(type)) {
			model.addAttribute("type", "add");
			return "stationgroup/editStationGroup";
		} else {
			model.addAttribute("type", "update");
			model.addAttribute("stationGroup", stationGroupSV.getStationGroupByPrimaryKey(Long.parseLong(groupId)));
			return "stationgroup/editStationGroup";
		}

	}

	@RequestMapping("/addStationGroup")
	@ResponseBody
	public AjaxBean addStationGroup(String type, UmStationGroup stationGroup, Model model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		AjaxBean ajaxBean = new AjaxBean();
		try {
			if ("add".equals(type)) {
				stationGroup.setCreateDate(new Date());//@todo 暂时
				stationGroup.setCreateOperatorId(new Long("11"));//@todo  暂时
				stationGroup.setDomainId(new Long(1));
				stationGroupSV.insertStationGroup(stationGroup);// 添加
			} else {
				stationGroupSV.modifyStation(stationGroup);
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

	@RequestMapping("/deleteStationGroup")
	@ResponseBody
	public AjaxBean deleteStationGroup(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxBean ajaxBean = new AjaxBean();
		String jsonMessage = request.getParameter("primaryKeys");// 获取json串参数
		JSONArray jsonArray = JSONArray.parseArray(jsonMessage);
		JSONObject jsobj;
		try {
			for (int i = 0; i < jsonArray.size(); i++) {
				jsobj = jsonArray.getJSONObject(i);
				stationGroupSV.deleteStationGroup(Long.parseLong(jsobj.getString("groupId")));
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

	@RequestMapping("/updateStationGroup")
	@ResponseBody
	public AjaxBean updateStationGroup(UmDictitem dictitem, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		AjaxBean ajaxBean = new AjaxBean();

		return ajaxBean;

	}

	@RequestMapping("/queryStationGroupList")
	@ResponseBody
	public AjaxPageBean queryStationGroupList(@RequestParam(value = "iDisplayStart", defaultValue = "0") int pageNo,
			@RequestParam(value = "iDisplayLength", defaultValue = "10") int pageSize, HttpServletRequest request)
			throws Exception {
		AjaxPageBean ajaxBean = new AjaxPageBean();
		String groupName = (String) request.getParameter("groupNameCon");
		int page = (pageNo / pageSize) + 1;
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		ajaxBean.setOther(sEcho);

		SqlCondition con = new SqlCondition();
		
		TableCols.UmStationGroupCols t = TableCols.UmStationGroupCols;
		if (!StringUtils.isEmpty(groupName)) {
			con.like(t.groupName, groupName);
		}
		con.eq("t1.domain_id",1);//@Todo 暂时写死  domainId过滤
		con.orderByDesc("t1.create_date");

		try {
			int total = stationGroupSV.countStationGroupByConditionTotal(con);
			con.page(page, pageSize);
			List<UmStationGroupListBean> list = stationGroupSV.queryStationGroupByCondition(con);
			
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

}
