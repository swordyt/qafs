package com.qafs.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.qafs.common.Constant;
import com.qafs.common.EmailConfirmStatus;
import com.qafs.common.EmailRecord_OrderType;
import com.qafs.common.OrderLog_OrderType;
import com.qafs.common.RCOOrderStatus;
import com.qafs.common.Status;
import com.qafs.dao.EmailRecordMapper;
import com.qafs.dao.OrderLogMapper;
import com.qafs.dao.RequirementChangeOrderMapper;
import com.qafs.dao.UserMapper;
import com.qafs.domain.EmailRecord;
import com.qafs.domain.OrderLog;
import com.qafs.domain.RequirementChangeOrder;
import com.qafs.domain.RequirementChangeOrderWithBLOBs;
import com.qafs.domain.User;
import com.qafs.dto.RCOOrderDealDto;
import com.qafs.dto.SearchDto;
import com.qafs.service.IEmailSendService;
import com.qafs.service.IRequirementChangeOrderService;
import com.qafs.util.PropertiesUtil;
import com.qafs.vm.RcoDetailVm;

@Service
public class RequirementChangeOrderServiceImpl implements
		IRequirementChangeOrderService {
	@Resource
	RequirementChangeOrderMapper rcoDao;
	@Resource
	RequirementChangeOrderWithBLOBs rco;
	@Resource
	UserMapper userDao;
	@Resource
	EmailRecordMapper erDao;
	@Resource
	IEmailSendService emailService;
	
	@Override
	public Integer createRco(RequirementChangeOrderWithBLOBs order) {
		order.setOrderstatus(RCOOrderStatus.I);
		rcoDao.insertSelective(order);
		return order.getId();
	}

	@Override
	public List<RcoDetailVm> searchRCO(SearchDto search) {
		List<RequirementChangeOrderWithBLOBs> rcowbl = rcoDao
				.selectBySearch(search);
		List<RcoDetailVm> rcoDetailVmL = new ArrayList<RcoDetailVm>();
		for (RequirementChangeOrderWithBLOBs rcowb : rcowbl) {
			RcoDetailVm RcoDetailVm = (RcoDetailVm) rcowb;
			RcoDetailVm.setOrderstatusName(RCOOrderStatus.value.get(RcoDetailVm
					.getOrderstatus()));
			rcoDetailVmL.add(RcoDetailVm);
		}
		return rcoDetailVmL;
	}

	@Override
	public Integer queryNumber(Integer userId) {
		return rcoDao.selectAll(userId);
	}

	@Override
	public Boolean orderDeal(RCOOrderDealDto order) {
		rco.setId(order.getOrderId());
		switch (order.getType()) {
		case 0:
			rco.setStatus(0);
			rcoDao.updateByPrimaryKeySelective(rco);
			break;
		case 1:
			RequirementChangeOrderWithBLOBs rcoOrder = rcoDao
					.selectByPrimaryKey(order.getOrderId());
			if (rcoOrder == null) {
				return false;
			}
			String devUser = rcoOrder.getDevelopmentengineer();
			sendEmail(devUser, rcoOrder.getId());
			rcoOrder.setOrderstatus(RCOOrderStatus.DC);
			rcoDao.updateByPrimaryKeySelective(rcoOrder);
			break;
		case 2:
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public RcoDetailVm getDeatil(Integer id) {
		RequirementChangeOrderWithBLOBs rco = rcoDao.selectByPrimaryKey(id);
		RcoDetailVm rcoDetailVm = (RcoDetailVm) rco;
		rcoDetailVm.setChangeapplicantName(userDao.selectByPrimaryKey(
				rco.getChangeapplicant()).getName());
		User dealUser = userDao.selectByPrimaryKey(rco.getChangedealman());
		rcoDetailVm.setChangedealmanName(dealUser == null ? null : dealUser
				.getName());
		rcoDetailVm.setOrderstatusName(RCOOrderStatus.value.get(rco
				.getOrderstatus()));
		Object[] developmentengineer = JSONArray.parseArray(
				rco.getDevelopmentengineer()).toArray();
		Object[] operationengineer = JSONArray.parseArray(
				rco.getOperationengineer()).toArray();
		Object[] testengineer = JSONArray.parseArray(rco.getTestengineer())
				.toArray();
		List<String> devName = new ArrayList<String>();
		for (Object userId : developmentengineer) {
			User user = userDao.selectByPrimaryKey(Integer
					.parseInt((String) userId));
			devName.add(user.getName());
		}
		rcoDetailVm.setDevelopmentengineerName(devName);
		List<String> operName = new ArrayList<String>();
		for (Object userId : operationengineer) {
			User user = userDao.selectByPrimaryKey(Integer
					.parseInt((String) userId));
			operName.add(user.getName());
		}
		rcoDetailVm.setOperationengineerName(operName);
		List<String> testName = new ArrayList<String>();
		for (Object userId : testengineer) {
			User user = userDao.selectByPrimaryKey(Integer
					.parseInt((String) userId));
			testName.add(user.getName());
		}
		rcoDetailVm.setTestengineerName(testName);
		return rcoDetailVm;
	}

	@Override
	public void update(RequirementChangeOrderWithBLOBs order) {
		rcoDao.updateByPrimaryKeySelective(order);
	}

	@Override
	public void sendEmail(String receiver, Integer orderId) {
		JSONArray jsonArray = JSONArray.parseArray(receiver);
		for (Object obj : jsonArray.toArray()) {
			EmailRecord email = new EmailRecord();
			User user = userDao.selectByPrimaryKey(Integer
					.parseInt((String) obj));
			email.setConfirmstatus(EmailConfirmStatus.TBC);
			email.setOrderid(orderId);
			email.setOrdertype(EmailRecord_OrderType.RCO);
			email.setReceiver(user.getEmail());
			email.setSubject(Constant.EMAIL.TITLE);
			email.setText("");
			email.setStatus(1);
			int flag = erDao.insertSelective(email);
			if (flag > 0) {
				email.setText(Constant.EMAIL.TEXT
						+ PropertiesUtil
								.getFormatProperty("spring.server.${env}.ip")
						+ "/qafs/view/rcodetail?id=" + email.getOrderid()
						+ "&erId=" + email.getId());
				email.setSendstatus(emailService.send(email) == true ? 1 : 0);
				erDao.updateByPrimaryKeySelective(email);
			}
		}

	}
}
