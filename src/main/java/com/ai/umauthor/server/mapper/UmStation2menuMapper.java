package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmStation2menu;
import com.ai.umauthor.server.model.UmStation2menuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmStation2menuMapper {
    int countByExample(UmStation2menuExample example);

    int deleteByExample(UmStation2menuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmStation2menu record);

    int insertSelective(UmStation2menu record);

    List<UmStation2menu> selectByExample(UmStation2menuExample example);

    UmStation2menu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmStation2menu record, @Param("example") UmStation2menuExample example);

    int updateByExample(@Param("record") UmStation2menu record, @Param("example") UmStation2menuExample example);

    int updateByPrimaryKeySelective(UmStation2menu record);

    int updateByPrimaryKey(UmStation2menu record);
}