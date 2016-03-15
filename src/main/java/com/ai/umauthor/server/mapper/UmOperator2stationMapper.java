package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmOperator2station;
import com.ai.umauthor.server.model.UmOperator2stationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmOperator2stationMapper {
    int countByExample(UmOperator2stationExample example);

    int deleteByExample(UmOperator2stationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmOperator2station record);

    int insertSelective(UmOperator2station record);

    List<UmOperator2station> selectByExample(UmOperator2stationExample example);

    UmOperator2station selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmOperator2station record, @Param("example") UmOperator2stationExample example);

    int updateByExample(@Param("record") UmOperator2station record, @Param("example") UmOperator2stationExample example);

    int updateByPrimaryKeySelective(UmOperator2station record);

    int updateByPrimaryKey(UmOperator2station record);
}