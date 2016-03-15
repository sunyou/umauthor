package com.ai.umauthor.web.shiro.load;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ai.common.security.ILoginUser;
import com.ai.common.util.WebUtils;
import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.model.UmStationtype;
import com.ai.umauthor.server.service.mrg.interfaces.LoginUserSV;
import com.ai.umauthor.server.service.mrg.interfaces.MenuSV;
import com.ai.umauthor.server.service.mrg.interfaces.StationSV;
import com.ai.umauthor.server.service.mrg.model.SessionUser;
import com.ai.umauthor.web.controller.LoginController;

/**
 * @author Typhon Chens 2016年2月4日
 */
public class CustomSessionDataDao implements SessionDataDao {

	private static final Logger log = LoggerFactory
			.getLogger(CustomSessionDataDao.class);
	

	public LoginUserSV getLoginUserSV() {
		return loginUserSV;
	}

	public void setLoginUserSV(LoginUserSV loginUserSV) {
		this.loginUserSV = loginUserSV;
	}

	public StationSV getStationSV() {
		return stationSV;
	}

	public void setStationSV(StationSV stationSV) {
		this.stationSV = stationSV;
	}

	public MenuSV getMenuSV() {
		return menuSV;
	}

	public void setMenuSV(MenuSV menuSV) {
		this.menuSV = menuSV;
	}

	@Autowired
	private LoginUserSV loginUserSV;
	@Autowired
	private StationSV stationSV;

	@Autowired
	private MenuSV menuSV;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ai.umauthor.web.shiro.load.SessionDataDao#LoadAuthorityInfo(java.
	 * lang.String)
	 */
	@Override
	public void LoadAuthorityInfo(String username) {
		// username="test2";
		SessionUser<UmOperator, UmStation, UmMenu, Object> sessionUserInfo = new SessionUser<UmOperator, UmStation, UmMenu, Object>();
		
		ILoginUser loginUser = loginUserSV.queryUserInfoByLoginCode(username);
		sessionUserInfo.setLoginUser((UmOperator) loginUser);
		UmOperator umOperator = sessionUserInfo.getLoginUser();
		log.debug("LoadAuthorityInfo "+ umOperator.getOperatorCode()+"        "+umOperator.getOperatorType());
		List<UmStation> stations = stationSV.queryStationsByOperator(umOperator.getOperatorId(), umOperator.getDomainId());
		sessionUserInfo.setStations(stations);
		List<UmMenu> menus = menuSV.queryMenusAllByOperator(
				umOperator.getOperatorId(), umOperator.getDomainId());
		sessionUserInfo.setMenus(menus);
		WebUtils.setSessionUserInfo(sessionUserInfo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ai.umauthor.web.shiro.load.SessionDataDao#LoadRoleInfo(java.lang.
	 * String)
	 */
	@Override
	public List<String> LoadRoleInfo(String userId) {
		SessionUser<UmOperator, UmStation, UmMenu, Object> sessionUserInfo = WebUtils
				.getSessionUserInfo();
		List<String> stations = new ArrayList<String>();
		List<UmStation> stationList = sessionUserInfo.getStations();
		for (Iterator iterator = stationList.iterator(); iterator.hasNext();) {
			UmStation umStation = (UmStation) iterator.next();
			stations.add(umStation.getStationId() + "");
		}
		return stations;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ai.umauthor.web.shiro.load.SessionDataDao#LoadPermissionsInfo(java
	 * .lang.String)
	 */
	@Override
	public List<String> LoadPermissionsInfo(String userId) {
		SessionUser<UmOperator, UmStationtype, UmMenu, Object> sessionUserInfo = WebUtils
				.getSessionUserInfo();
		List<UmMenu> menuList = sessionUserInfo.getMenus();
		List<String> permissions = new ArrayList<String>();
		for (Iterator iterator = menuList.iterator(); iterator.hasNext();) {
			UmMenu umMenu = (UmMenu) iterator.next();
			permissions.add(umMenu.getMenuCode());
		}
		return permissions;
	}

}
