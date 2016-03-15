package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmStationtype;
import com.ai.umauthor.server.model.UmStationtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmStationtypeMapper {
    int countByExample(UmStationtypeExample example);

    int deleteByExample(UmStationtypeExample example);

    int deleteByPrimaryKey(Long stationtypeId);

    int insert(UmStationtype record);

    int insertSelective(UmStationtype record);

    List<UmStationtype> selectByExample(UmStationtypeExample example);

    UmStationtype selectByPrimaryKey(Long stationtypeId);

    int updateByExampleSelective(@Param("record") UmStationtype record, @Param("example") UmStationtypeExample example);

    int updateByExample(@Param("record") UmStationtype record, @Param("example") UmStationtypeExample example);

    int updateByPrimaryKeySelective(UmStationtype record);

    int updateByPrimaryKey(UmStationtype record);
}