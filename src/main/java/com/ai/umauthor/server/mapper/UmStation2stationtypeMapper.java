package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmStation2stationtype;
import com.ai.umauthor.server.model.UmStation2stationtypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmStation2stationtypeMapper {
    int countByExample(UmStation2stationtypeExample example);

    int deleteByExample(UmStation2stationtypeExample example);

    int deleteByPrimaryKey(Long relationId);

    int insert(UmStation2stationtype record);

    int insertSelective(UmStation2stationtype record);

    List<UmStation2stationtype> selectByExample(UmStation2stationtypeExample example);

    UmStation2stationtype selectByPrimaryKey(Long relationId);

    int updateByExampleSelective(@Param("record") UmStation2stationtype record, @Param("example") UmStation2stationtypeExample example);

    int updateByExample(@Param("record") UmStation2stationtype record, @Param("example") UmStation2stationtypeExample example);

    int updateByPrimaryKeySelective(UmStation2stationtype record);

    int updateByPrimaryKey(UmStation2stationtype record);
}