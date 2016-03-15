package com.ai.umauthor.web.controller.mgr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ai.common.domain.AjaxBean;
import com.ai.common.domain.AjaxPageBean;
import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.model.UmStation2menu;
import com.ai.umauthor.server.model.UmStation2stationtype;
import com.ai.umauthor.server.service.mrg.interfaces.Station2MenuSV;
import com.ai.umauthor.server.service.mrg.interfaces.Station2StationtypeSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationGroupSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationSV;
import com.ai.umauthor.server.service.mrg.model.MenuStationRelate;
import com.ai.umauthor.server.service.mrg.model.QryUmStation;
import com.ai.umauthor.server.service.mrg.model.SessionUser;

@Controller
@RequestMapping("/station")
public class StationController {

	private static final Logger log = LoggerFactory.getLogger(StationController.class);
	
	@Autowired
	private StationSV stationSV;
	
	@Autowired
	private StationGroupSV groupSV;
	
    @Autowired
    private Station2MenuSV station2MenuSV;
    
    @Autowired
    private Station2StationtypeSV station2StationtypeSV;
    
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model model) throws Exception{
    	return "station/station";
    }
	
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean queryStationList(HttpServletRequest request,String orgId,
			String stationName,String groupName,
			@RequestParam(value="iDisplayStart", defaultValue="0") int pageNo, 
			@RequestParam(value="iDisplayLength", defaultValue="10")int pageSize) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		result.setOther(sEcho);
		try {
			SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
			UmOperator umOperator=sessionUserInfo.getLoginUser();
			if(umOperator == null){
				throw new BusinessException("-4", "会话超时，请重新登录");
			}
			int count = stationSV.queryStationCountByOrgId(umOperator.getOrgId());
			if(count > 0){
				List<QryUmStation> list = stationSV.queryStationsByOrgId(umOperator.getOrgId(),stationName,groupName,(pageNo / pageSize)+1,pageSize);
				result.setData(list);
			}else{
				result.setData(new ArrayList<QryUmStation>());
			}
			result.setCurrentPage(pageNo+1);
			result.setTotal(count);
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 打开新增或修改页面
	 * @param stationId
	 * @param type
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit", method = { RequestMethod.POST, RequestMethod.GET })
	public String getEditDictitem(Long stationId, String type, Model model)
			throws Exception {
		model.addAttribute("groupList", groupSV.queryAllStationGroup());
		if ("add".endsWith(type)) {
			model.addAttribute("type", "add");
		} else {
			model.addAttribute("type", "update");
			//岗位信息
			model.addAttribute("station", stationSV.queryStationById(stationId));
			//岗位关联菜单信息
			List<UmStation2menu> smrelaList = station2MenuSV.queryStation2MenuListByStationId(stationId);
			StringBuffer menuIds = new StringBuffer();
			for (UmStation2menu rela : smrelaList) {
				 menuIds.append(rela.getMenuId()).append(",");
			}
			if(menuIds.length() > 0)menuIds.deleteCharAt(menuIds.length()-1);
			model.addAttribute("relaMenuIds", menuIds);
			//岗位关联岗位类型信息
			List<UmStation2stationtype> strelaList = station2StationtypeSV.queryRelaByStationId(stationId);
			StringBuffer typeIds = new StringBuffer();
			for (UmStation2stationtype rela : strelaList) {
				typeIds.append(rela.getStationtypeId()).append(",");
			}
			if(typeIds.length() > 0)typeIds.deleteCharAt(typeIds.length()-1);
			model.addAttribute("relaTypeIds", typeIds);
		}
		return "station/editStation";
	} 
	
	/**
	 * 新增或修改
	 * @param request
	 * @param menu
	 * @return
	 */
	@RequestMapping(value="/presistent", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean editStation(HttpServletRequest request,UmStation station,String relaMenuIds,String relaTypeIds,String oldRelaMenuIds,String oldRelaTypeIds){
		AjaxPageBean result = new AjaxPageBean();
		try {
			if(station.getStationId() != null && station.getStationId() > 0){
				stationSV.modifyStation(station,relaMenuIds,relaTypeIds,oldRelaMenuIds,oldRelaTypeIds);
			}else{
				stationSV.insertStation(station,relaMenuIds,relaTypeIds);
			}
			result.setCode(AjaxPageBean.SUCCESS_CODE);
			result.setMessage("操作成功！");
		} catch (Exception e) {
			log.error("editStation:"+e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	} 
	
	/**
	 * 删除岗位信息
	 * @param request
	 * @param menuId
	 * @return
	 */
	@RequestMapping(value="/delStation", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean delStation(HttpServletRequest request,Long stationId){
		AjaxPageBean result = new AjaxPageBean();
		try {
			stationSV.deleteStation(stationId);
			result.setCode(AjaxPageBean.SUCCESS_CODE);
			result.setMessage("操作成功！");
		} catch (Exception e) {
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	/**
	 * 打开批量菜单关联岗位
	 * @author guohui
	 *@date 2016年2月17日
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBatchMenuRelate", method = { RequestMethod.POST, RequestMethod.GET })
	public String getBatchMenuRelate(Model model)
			throws Exception {
		
		return "station/batchMenuRelate";
	} 
	
	/**
	 * 菜单岗位关系列表
	 * @author guohui
	 *@date 2016年2月17日
	 * @param request
	 * @param orgId
	 * @param stationName
	 * @param groupName
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/menuStationList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean queryMenuStationList(HttpServletRequest request,String orgId,String menuId,
			@RequestParam(value="iDisplayStart", defaultValue="0") int pageNo, 
			@RequestParam(value="iDisplayLength", defaultValue="10")int pageSize) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		SqlCondition cond=new SqlCondition();
		int page = (pageNo / pageSize)+1;
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		result.setOther(sEcho);
		try {
			SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
			UmOperator umOperator=sessionUserInfo.getLoginUser();
			if(umOperator == null){
				throw new BusinessException("-4", "会话超时，请重新登录");
			}
			int count = stationSV.queryMenuStationCountByOrgId(umOperator.getOrgId(),menuId,cond);
			if(count > 0){
//				cond.page(page, pageSize);
				List<MenuStationRelate> list = stationSV.queryMenuStationsByOrgId(umOperator.getOrgId(),menuId,cond);
				result.setData(list);
			}
			result.setCurrentPage(page);
			result.setTotal(count);
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	
	@RequestMapping("/saveMenuStation")
	@ResponseBody
	public AjaxBean saveMenuStation(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxBean ajaxBean = new AjaxBean();
		String jsonMessage = request.getParameter("selectItems");// 获取json串参数
		String menuId = request.getParameter("menuId");
	try{
		   stationSV.saveMenuStation(jsonMessage, menuId);
			ajaxBean.setCode(AjaxBean.SUCCESS_CODE);
			ajaxBean.setMessage("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxBean.setCode(AjaxBean.ERROR_CODE);
			ajaxBean.setMessage("操作失败" + e.getMessage());
			log.debug(e.getMessage());
		}
		return ajaxBean;

	}
	
	/**
	 * 打开批量岗位类型关联
	 * @author guohui
	 *@date 2016年2月18日
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getBatchStationTypeRelate", method = { RequestMethod.POST, RequestMethod.GET })
	public String getBatchStationTypeRelate(Model model)
			throws Exception {
		return "station/batchStationTypeRelate";
	} 
	
	@RequestMapping(value="/stationTypeStationList", method = RequestMethod.POST)
	@ResponseBody
	public AjaxPageBean queryStationTypeStationList(HttpServletRequest request,String orgId,String stationTypeId,
			@RequestParam(value="iDisplayStart", defaultValue="0") int pageNo, 
			@RequestParam(value="iDisplayLength", defaultValue="10")int pageSize) throws Exception{
		AjaxPageBean result = new AjaxPageBean();
		SqlCondition cond=new SqlCondition();
		int page = (pageNo / pageSize)+1;
		int sEcho = Integer.valueOf(request.getParameter("sEcho"));
		result.setOther(sEcho);
		try {
			SessionUser<UmOperator,UmStation,UmMenu,Object>  sessionUserInfo= WebUtils.getSessionUserInfo();
			UmOperator umOperator=sessionUserInfo.getLoginUser();
			if(umOperator == null){
				throw new BusinessException("-4", "会话超时，请重新登录");
			}
			int count = stationSV.queryStationTypeStationCountByOrgId(umOperator.getOrgId(),stationTypeId,cond);
			if(count > 0){
//				cond.page(page, pageSize);
				List<MenuStationRelate> list = stationSV.queryStationTypeStationsByOrgId(umOperator.getOrgId(),stationTypeId,cond);
				result.setData(list);
			}
			result.setCurrentPage(page);
			result.setTotal(count);
		} catch (Exception e) {
			log.error(e.getMessage());
			result.setCode(AjaxPageBean.ERROR_CODE);
			result.setMessage(e.getMessage());
		}
		return result;
	}
	
	@RequestMapping("saveStationTypeStation")
	@ResponseBody
	public AjaxBean saveStationTypeStation(Model model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		AjaxBean ajaxBean = new AjaxBean();
		String jsonMessage = request.getParameter("selectItems");// 获取json串参数
		String stationTypeId = request.getParameter("stationTypeId");
	try{
		   stationSV.saveStationTypeStation(jsonMessage, stationTypeId);
			ajaxBean.setCode(AjaxBean.SUCCESS_CODE);
			ajaxBean.setMessage("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxBean.setCode(AjaxBean.ERROR_CODE);
			ajaxBean.setMessage("操作失败" + e.getMessage());
			log.debug(e.getMessage());
		}
		return ajaxBean;

	}
}
