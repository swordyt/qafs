package com.qafs.dao;

import java.util.List;

import com.qafs.domain.RequirementChangeOrder;
import com.qafs.domain.RequirementChangeOrderWithBLOBs;
import com.qafs.dto.SearchDto;

public interface RequirementChangeOrderMapper {
	Integer selectAll(Integer userId);

	List<RequirementChangeOrderWithBLOBs> selectBySearch(SearchDto search);

	int insertSelective(RequirementChangeOrderWithBLOBs record);

	RequirementChangeOrderWithBLOBs selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(RequirementChangeOrderWithBLOBs record);

	int updateByPrimaryKeyWithBLOBs(RequirementChangeOrderWithBLOBs record);

	int updateByPrimaryKey(RequirementChangeOrder record);
}