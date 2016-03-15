package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmOperator;
import com.ai.umauthor.server.model.UmOperatorExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmOperatorMapper {
    int countByExample(UmOperatorExample example);

    int deleteByExample(UmOperatorExample example);

    int deleteByPrimaryKey(Long operatorId);

    int insert(UmOperator record);

    int insertSelective(UmOperator record);

    List<UmOperator> selectByExample(UmOperatorExample example);

    UmOperator selectByPrimaryKey(Long operatorId);

    int updateByExampleSelective(@Param("record") UmOperator record, @Param("example") UmOperatorExample example);

    int updateByExample(@Param("record") UmOperator record, @Param("example") UmOperatorExample example);

    int updateByPrimaryKeySelective(UmOperator record);

    int updateByPrimaryKey(UmOperator record);
}