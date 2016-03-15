package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmOperator2menu;
import com.ai.umauthor.server.model.UmOperator2menuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmOperator2menuMapper {
    int countByExample(UmOperator2menuExample example);

    int deleteByExample(UmOperator2menuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmOperator2menu record);

    int insertSelective(UmOperator2menu record);

    List<UmOperator2menu> selectByExample(UmOperator2menuExample example);

    UmOperator2menu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmOperator2menu record, @Param("example") UmOperator2menuExample example);

    int updateByExample(@Param("record") UmOperator2menu record, @Param("example") UmOperator2menuExample example);

    int updateByPrimaryKeySelective(UmOperator2menu record);

    int updateByPrimaryKey(UmOperator2menu record);
}