package com.qafs.service;

import java.util.List;

import com.qafs.domain.RequirementChangeOrderWithBLOBs;
import com.qafs.dto.RCOOrderDealDto;
import com.qafs.dto.SearchDto;
import com.qafs.vm.RcoDetailVm;

public interface IRequirementChangeOrderService {
	public Integer createRco(RequirementChangeOrderWithBLOBs order);

	public List<RcoDetailVm> searchRCO(SearchDto search);

	public Integer queryNumber(Integer userId);

	public Boolean orderDeal(RCOOrderDealDto order);

	public RcoDetailVm getDeatil(Integer id);

	public void update(RequirementChangeOrderWithBLOBs order);

	public void sendEmail(String receiver,Integer orderId);
}
