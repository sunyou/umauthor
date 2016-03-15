package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmSystem;
import com.ai.umauthor.server.model.UmSystemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmSystemMapper {
    int countByExample(UmSystemExample example);

    int deleteByExample(UmSystemExample example);

    int deleteByPrimaryKey(Long systemId);

    int insert(UmSystem record);

    int insertSelective(UmSystem record);

    List<UmSystem> selectByExample(UmSystemExample example);

    UmSystem selectByPrimaryKey(Long systemId);

    int updateByExampleSelective(@Param("record") UmSystem record, @Param("example") UmSystemExample example);

    int updateByExample(@Param("record") UmSystem record, @Param("example") UmSystemExample example);

    int updateByPrimaryKeySelective(UmSystem record);

    int updateByPrimaryKey(UmSystem record);
}