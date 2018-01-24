package com.qafs.dao;

import java.util.List;

import com.qafs.domain.EmailRecord;
import com.qafs.dto.SearchDto;

public interface EmailRecordMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(EmailRecord record);

	int insertSelective(EmailRecord record);

	EmailRecord selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(EmailRecord record);

	int updateByPrimaryKey(EmailRecord record);

	List<EmailRecord> selectBySearch(SearchDto search);

	Integer selectAll(EmailRecord record);
}