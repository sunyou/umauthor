package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.umauthor.server.model.UmSystem;

public interface SystemSV {

	/**
	 * 查询所有有效的子系统
	 * @return
	 * @throws Exception
	 */
	public List<UmSystem> querySystemList() throws Exception;
}
