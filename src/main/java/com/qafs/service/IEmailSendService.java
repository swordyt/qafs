package com.qafs.service;

import com.qafs.domain.EmailRecord;

public interface IEmailSendService {
	public Boolean send(EmailRecord email);

	public Boolean send(Integer id);
}
