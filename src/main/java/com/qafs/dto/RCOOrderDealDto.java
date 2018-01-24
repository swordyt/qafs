package com.qafs.dto;

public class RCOOrderDealDto {
	private Integer orderId;
	private Integer type;// 0:删除，1:发布，2：停止

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
