package com.ai.umauthor.server.service.mrg.model;

import com.ai.umauthor.server.model.UmMenu;

public class QryUmMenu extends UmMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean isParent;

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}
}
