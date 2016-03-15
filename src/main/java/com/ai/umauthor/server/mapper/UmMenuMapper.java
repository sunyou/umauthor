package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmMenu;
import com.ai.umauthor.server.model.UmMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmMenuMapper {
    int countByExample(UmMenuExample example);

    int deleteByExample(UmMenuExample example);

    int deleteByPrimaryKey(Long menuId);

    int insert(UmMenu record);

    int insertSelective(UmMenu record);

    List<UmMenu> selectByExample(UmMenuExample example);

    UmMenu selectByPrimaryKey(Long menuId);

    int updateByExampleSelective(@Param("record") UmMenu record, @Param("example") UmMenuExample example);

    int updateByExample(@Param("record") UmMenu record, @Param("example") UmMenuExample example);

    int updateByPrimaryKeySelective(UmMenu record);

    int updateByPrimaryKey(UmMenu record);
}