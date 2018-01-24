package com.qafs.service;

import java.util.List;

import com.qafs.domain.EmailRecord;
import com.qafs.dto.SearchDto;
import com.qafs.vm.PendingListVm;

public interface IEmailRecordService {
	public List<PendingListVm> search(SearchDto search);

	public Integer getAll(String email);

	public List<EmailRecord> getUserRecord();

	public Boolean deal(EmailRecord er,Integer type);
}
