package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmAreas;
import com.ai.umauthor.server.model.UmAreasExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmAreasMapper {
    int countByExample(UmAreasExample example);

    int deleteByExample(UmAreasExample example);

    int insert(UmAreas record);

    int insertSelective(UmAreas record);

    List<UmAreas> selectByExample(UmAreasExample example);

    int updateByExampleSelective(@Param("record") UmAreas record, @Param("example") UmAreasExample example);

    int updateByExample(@Param("record") UmAreas record, @Param("example") UmAreasExample example);
}