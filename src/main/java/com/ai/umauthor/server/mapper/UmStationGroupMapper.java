package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmStationGroup;
import com.ai.umauthor.server.model.UmStationGroupExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmStationGroupMapper {
    int countByExample(UmStationGroupExample example);

    int deleteByExample(UmStationGroupExample example);

    int deleteByPrimaryKey(Long groupId);

    int insert(UmStationGroup record);

    int insertSelective(UmStationGroup record);

    List<UmStationGroup> selectByExample(UmStationGroupExample example);

    UmStationGroup selectByPrimaryKey(Long groupId);

    int updateByExampleSelective(@Param("record") UmStationGroup record, @Param("example") UmStationGroupExample example);

    int updateByExample(@Param("record") UmStationGroup record, @Param("example") UmStationGroupExample example);

    int updateByPrimaryKeySelective(UmStationGroup record);

    int updateByPrimaryKey(UmStationGroup record);
}