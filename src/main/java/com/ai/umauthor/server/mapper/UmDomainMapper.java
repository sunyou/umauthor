package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmDomain;
import com.ai.umauthor.server.model.UmDomainExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmDomainMapper {
    int countByExample(UmDomainExample example);

    int deleteByExample(UmDomainExample example);

    int deleteByPrimaryKey(Long domainId);

    int insert(UmDomain record);

    int insertSelective(UmDomain record);

    List<UmDomain> selectByExample(UmDomainExample example);

    UmDomain selectByPrimaryKey(Long domainId);

    int updateByExampleSelective(@Param("record") UmDomain record, @Param("example") UmDomainExample example);

    int updateByExample(@Param("record") UmDomain record, @Param("example") UmDomainExample example);

    int updateByPrimaryKeySelective(UmDomain record);

    int updateByPrimaryKey(UmDomain record);
}