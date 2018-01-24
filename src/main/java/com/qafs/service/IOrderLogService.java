package com.qafs.service;

import java.util.List;

import com.qafs.domain.OrderLog;

public interface IOrderLogService {
	public Boolean save(OrderLog order);

	public List<OrderLog> getOrderLog(Integer orderId);
}
