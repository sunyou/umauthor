package com.ai.umauthor.server.mapper;

import com.ai.umauthor.server.model.UmOrg;
import com.ai.umauthor.server.model.UmOrgExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmOrgMapper {
    int countByExample(UmOrgExample example);

    int deleteByExample(UmOrgExample example);

    int deleteByPrimaryKey(String orgId);

    int insert(UmOrg record);

    int insertSelective(UmOrg record);

    List<UmOrg> selectByExample(UmOrgExample example);

    UmOrg selectByPrimaryKey(String orgId);

    int updateByExampleSelective(@Param("record") UmOrg record, @Param("example") UmOrgExample example);

    int updateByExample(@Param("record") UmOrg record, @Param("example") UmOrgExample example);

    int updateByPrimaryKeySelective(UmOrg record);

    int updateByPrimaryKey(UmOrg record);
}