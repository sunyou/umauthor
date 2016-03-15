package com.ai.umauthor.server.service.mrg.interfaces;

import java.util.List;

import com.ai.common.dynasql.SqlCondition;
import com.ai.common.exception.BusinessException;
import com.ai.umauthor.server.model.UmDictitem;
import com.ai.umauthor.server.model.UmMenu;

/**
 * 字典
 * 
 * @author guohui
 * @date 2016年1月28日
 */
public interface DictitemSV {
	/**
	 * 添加字典
	 * 
	 * @param role
	 * @throws BusinessException
	 */
	public void insertDictitem(UmDictitem dictitem) throws BusinessException;

	/**
	 * 修改字典
	 * 
	 * @param role
	 */
	public void modifyDictitem(UmDictitem dictitem) throws BusinessException;

	/**
	 * 根据字典名和字典编号删除字典
	 * 
	 * @param roleId
	 * @throws BusinessException
	 */
	public void deleteDictitem(String dictKey, String itemKey) throws BusinessException;

	/**
	 * 根据条件查询字典列表
	 * 
	 * @author guohui
	 * @date 2016年1月28日
	 * @param dictName
	 * @param itemNo
	 * @param itemState
	 * @return
	 */
	public List<UmDictitem> queryDictitemByCondition(SqlCondition con) throws Exception ;
	
	/**
	 * 根据条件查询字典数量
	 * @author guohui
	 *@date 2016年1月28日
	 * @param dictName
	 * @param itemNo
	 * @param itemState
	 * @return
	 */
	public int countDictitemByConditionTotal(SqlCondition con) throws Exception ;

	/**
	 * 根据主键获取字典（联合主键）
	 * @author guohui
	 *@date 2016年1月28日
	 * @param dictName
	 * @param itemNo
	 * @return
	 * @throws Exception
	 */
	public UmDictitem getDictitemByPrimaryKey(String dictkey,String itemKey) throws Exception;
	
	/**
	 * 逻辑删除
	 * @author guohui
	 *@date 2016年2月1日
	 * @param dictitem
	 * @throws Exception
	 */
	public void logicalDeleteByPrimaryKey(UmDictitem dictitem) throws Exception;

	
	/**
	 * 
	 * 功能描述：根据字典key获取字典参数列表
	 *
	 * @author  zhangly
	 * <p>创建日期 ：2016年2月26日 下午3:22:06</p>
	 *
	 * @param dictKey
	 * @return
	 * @throws BusinessException
	 * @throws Exception
	 *
	 * <p>修改历史 ：(修改人，修改时间，修改原因/内容)</p>
	 */
	public List<UmDictitem> queryUmDictitemByDictKey(String dictKey) throws BusinessException, Exception;
}
