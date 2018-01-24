package com.qafs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qafs.common.Constant;
import com.qafs.common.EmailConfirmStatus;
import com.qafs.common.RCOOrderStatus;
import com.qafs.dao.EmailRecordMapper;
import com.qafs.dao.RequirementChangeOrderMapper;
import com.qafs.domain.EmailRecord;
import com.qafs.domain.RequirementChangeOrder;
import com.qafs.domain.RequirementChangeOrderWithBLOBs;
import com.qafs.dto.SearchDto;
import com.qafs.service.IEmailRecordService;
import com.qafs.service.IRequirementChangeOrderService;
import com.qafs.vm.PendingListVm;

@Service
public class EmailRecordServiceImpl implements IEmailRecordService {
	@Resource
	PendingListVm pendingListVm;
	@Resource
	EmailRecordMapper erDao;
	@Resource
	RequirementChangeOrderMapper rcoDao;
	@Resource
	IRequirementChangeOrderService rcoService;

	@Override
	public List<PendingListVm> search(SearchDto search) {
		List<EmailRecord> list = erDao.selectBySearch(search);
		List<PendingListVm> listPL = new ArrayList<PendingListVm>();
		for (EmailRecord er : list) {
			PendingListVm pl = new PendingListVm();
			pl.setConfirmstatus(er.getConfirmstatus());
			pl.setId(er.getId());
			pl.setCreatetime(er.getCreatetime());
			RequirementChangeOrderWithBLOBs rco = rcoDao.selectByPrimaryKey(er
					.getOrderid());
			pl.setOnlinetime(rco.getOnlinetime());
			pl.setOnlinetitle(rco.getOnlinetitle());
			pl.setOrderId(rco.getId());
			pl.setConfirmstatusName(EmailConfirmStatus.value.get(er
					.getConfirmstatus()));
			listPL.add(pl);
		}
		return listPL;
	}

	public Integer getAll(String email) {
		EmailRecord er = new EmailRecord();
		er.setReceiver(email);
		return erDao.selectAll(er);
	}

	@Override
	public List<EmailRecord> getUserRecord() {
		return null;
	}

	/**
	 * 
	 * type:2:拒绝，1：确认
	 * */
	@Override
	public Boolean deal(EmailRecord er, Integer type) {
		EmailRecord emailRecord = erDao.selectByPrimaryKey(er.getId());
		if (emailRecord.getConfirmstatus() != EmailConfirmStatus.TBC) {
			return false;
		}
		if (!emailRecord.getReceiver().equals(er.getReceiver())) {
			return false;
		}
		if (!(emailRecord.getOrderid() == er.getOrderid())) {
			return false;
		}
		er.setConfirmstatus(type);
		erDao.updateByPrimaryKeySelective(er);
		if (type == EmailConfirmStatus.CC) {
			EmailRecord e = new EmailRecord();
			e.setConfirmstatus(EmailConfirmStatus.TBC);
			e.setOrderid(er.getOrderid());
			if (erDao.selectAll(e) < 1) {
				RequirementChangeOrderWithBLOBs rcowb = rcoDao
						.selectByPrimaryKey(er.getOrderid());
				if (rcowb.getOrderstatus().equals(RCOOrderStatus.DC)) {
					rcowb.setOrderstatus(RCOOrderStatus.TC);
					rcoDao.updateByPrimaryKeySelective(rcowb);
					rcoService
							.sendEmail(rcowb.getTestengineer(), rcowb.getId());
				}else if (rcowb.getOrderstatus().equals(RCOOrderStatus.TC)) {
					rcowb.setOrderstatus(RCOOrderStatus.S);
					rcoDao.updateByPrimaryKeySelective(rcowb);
				}
			}
		}
		return true;
	}
}
