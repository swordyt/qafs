package com.qafs.dao;

import java.util.List;

import com.qafs.domain.OrderLog;

public interface OrderLogMapper {

	int insertSelective(OrderLog record);

	OrderLog selectByPrimaryKey(Integer id);

	List<OrderLog> selectByOrderid(Integer orderId);
}