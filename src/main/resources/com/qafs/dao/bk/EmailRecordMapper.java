package com.qafs.dao.bk;

import com.qafs.domain.bk.EmailRecord;

public interface EmailRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EmailRecord record);

    int insertSelective(EmailRecord record);

    EmailRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EmailRecord record);

    int updateByPrimaryKey(EmailRecord record);
}