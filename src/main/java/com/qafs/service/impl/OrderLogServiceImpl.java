package com.qafs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qafs.dao.OrderLogMapper;
import com.qafs.domain.OrderLog;
import com.qafs.service.IOrderLogService;

@Service
public class OrderLogServiceImpl implements IOrderLogService {
	@Resource
	OrderLog orderLog;
	@Resource
	OrderLogMapper orderLogDao;

	@Override
	public Boolean save(OrderLog order) {
		orderLogDao.insertSelective(order);
		return true;
	}

	@Override
	public List<OrderLog> getOrderLog(Integer orderId) {
		return orderLogDao.selectByOrderid(orderId);
	}

}
