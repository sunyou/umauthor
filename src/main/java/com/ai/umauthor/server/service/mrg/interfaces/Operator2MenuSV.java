package com.ai.umauthor.server.service.mrg.interfaces;

public interface Operator2MenuSV {

	/**
	 * 保存操作员同菜单关联关系
	 * @param operatorId
	 * @param menuId
	 * @param remarks
	 * @throws Exception
	 */
	public void saveOperMenuRela(Long operatorId,Long menuId,String remarks) throws Exception;
	
	/**
	 * 删除操作员同菜单关联关系
	 * @param operatorId
	 * @param menuId
	 * @throws Exception
	 */
	public void delOperMenuRela(Long operatorId,Long menuId) throws Exception;
}
