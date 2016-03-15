package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmStation;
import com.ai.umauthor.server.model.UmStationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmStationMapper {
    int countByExample(UmStationExample example);

    int deleteByExample(UmStationExample example);

    int deleteByPrimaryKey(Long stationId);

    int insert(UmStation record);

    int insertSelective(UmStation record);

    List<UmStation> selectByExample(UmStationExample example);

    UmStation selectByPrimaryKey(Long stationId);

    int updateByExampleSelective(@Param("record") UmStation record, @Param("example") UmStationExample example);

    int updateByExample(@Param("record") UmStation record, @Param("example") UmStationExample example);

    int updateByPrimaryKeySelective(UmStation record);

    int updateByPrimaryKey(UmStation record);
}