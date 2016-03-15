package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmDictitem;
import com.ai.umauthor.server.model.UmDictitemExample;
import com.ai.umauthor.server.model.UmDictitemKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmDictitemMapper {
    int countByExample(UmDictitemExample example);

    int deleteByExample(UmDictitemExample example);

    int deleteByPrimaryKey(UmDictitemKey key);

    int insert(UmDictitem record);

    int insertSelective(UmDictitem record);

    List<UmDictitem> selectByExample(UmDictitemExample example);

    UmDictitem selectByPrimaryKey(UmDictitemKey key);

    int updateByExampleSelective(@Param("record") UmDictitem record, @Param("example") UmDictitemExample example);

    int updateByExample(@Param("record") UmDictitem record, @Param("example") UmDictitemExample example);

    int updateByPrimaryKeySelective(UmDictitem record);

    int updateByPrimaryKey(UmDictitem record);
}