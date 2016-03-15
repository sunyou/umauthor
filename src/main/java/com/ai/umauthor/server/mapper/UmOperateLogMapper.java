package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmOperateLog;
import com.ai.umauthor.server.model.UmOperateLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmOperateLogMapper {
    int countByExample(UmOperateLogExample example);

    int deleteByExample(UmOperateLogExample example);

    int deleteByPrimaryKey(Long logId);

    int insert(UmOperateLog record);

    int insertSelective(UmOperateLog record);

    List<UmOperateLog> selectByExample(UmOperateLogExample example);

    UmOperateLog selectByPrimaryKey(Long logId);

    int updateByExampleSelective(@Param("record") UmOperateLog record, @Param("example") UmOperateLogExample example);

    int updateByExample(@Param("record") UmOperateLog record, @Param("example") UmOperateLogExample example);

    int updateByPrimaryKeySelective(UmOperateLog record);

    int updateByPrimaryKey(UmOperateLog record);
}