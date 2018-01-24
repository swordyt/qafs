package com.qafs.dao.bk;

import com.qafs.domain.bk.RequirementChangeOrder;
import com.qafs.domain.bk.RequirementChangeOrderWithBLOBs;

public interface RequirementChangeOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RequirementChangeOrderWithBLOBs record);

    int insertSelective(RequirementChangeOrderWithBLOBs record);

    RequirementChangeOrderWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RequirementChangeOrderWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(RequirementChangeOrderWithBLOBs record);

    int updateByPrimaryKey(RequirementChangeOrder record);
}