package com.qafs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.qafs.common.Constant;
import com.qafs.common.OrderLog_OrderType;
import com.qafs.common.Status;
import com.qafs.domain.OrderLog;
import com.qafs.domain.RequirementChangeOrderWithBLOBs;
import com.qafs.domain.User;
import com.qafs.dto.RCOOrderDealDto;
import com.qafs.dto.SearchDto;
import com.qafs.service.IOrderLogService;
import com.qafs.service.IRequirementChangeOrderService;
import com.qafs.util.ParaUtil;
import com.qafs.vm.ListVm;
import com.qafs.vm.OrderVm;
import com.qafs.vm.Response;

@Controller
@RequestMapping("rco")
public class RequirementChangeOrderController {
	@Resource
	HttpServletRequest request;
	@Resource
	OrderVm orderVm;
	@Resource
	IRequirementChangeOrderService rcoService;
	@Resource
	Response response;
	@Resource
	ListVm listVm;
	@Resource
	OrderLog orderLog;
	@Resource
	IOrderLogService orderLogService;

	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public Response add(RequirementChangeOrderWithBLOBs rcoOrder) {
		User user = (User) request.getAttribute("user");
		rcoOrder.setChangeapplicant(user.getId());
		rcoOrder.setStatus(Status.SUCCESS);
		orderVm.setOrderId(rcoService.createRco(rcoOrder));
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		response.setData(orderVm);
		orderLog.setOrderid(orderVm.getOrderId());
		orderLog.setOrdertype(OrderLog_OrderType.T_REQUIREMENT_CHANGE_ORDER);
		orderLog.setStatus(Status.SUCCESS);
		orderLog.setUserid(user.getId());
		orderLog.setOrdercontent(user.getName() + "创建了'"
				+ rcoOrder.getOnlinetitle() + "'工单");
		orderLogService.save(orderLog);
		return response;
	}

	@RequestMapping(value = "search")
	@ResponseBody
	public Response search(SearchDto search) {
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		search.setUserId(((User) request.getAttribute("user")).getId());
		listVm.setRows(rcoService.searchRCO(search));
		listVm.setTotal((long) rcoService.queryNumber(((User) request
				.getAttribute("user")).getId()));
		response.setData(listVm);
		return response;
	}

	@RequestMapping(value = "deal")
	@ResponseBody
	public Response deal(RCOOrderDealDto rcoOrderDealDto) {
		User user = (User) request.getAttribute("user");
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		response.setData(rcoService.orderDeal(rcoOrderDealDto));
		orderLog.setOrderid(rcoOrderDealDto.getOrderId());
		orderLog.setOrdertype(OrderLog_OrderType.T_REQUIREMENT_CHANGE_ORDER);
		orderLog.setStatus(Status.SUCCESS);
		orderLog.setUserid(user.getId());

		switch (rcoOrderDealDto.getType()) {
		case 0:
			orderLog.setOrdercontent(user.getName() + "删除了工单。");
			break;
		case 1:
			orderLog.setOrdercontent(user.getName() + "发布了工单。");
			break;
		case 2:
			orderLog.setOrdercontent(user.getName() + "停止了工单。");
			break;
		}
		orderLogService.save(orderLog);
		return response;
	}

	@ResponseBody
	@RequestMapping("detail")
	public Response detail(Integer id) {
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		response.setData(rcoService.getDeatil(id));
		return response;
	}

	@ResponseBody
	@RequestMapping("log")
	public Response log(Integer id) {
		if(!ParaUtil.notEmpty(id)){
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.FALSE);
			return response;
		}
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		response.setData(orderLogService.getOrderLog(id));
		return response;
	}
}
