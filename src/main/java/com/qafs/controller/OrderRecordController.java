package com.qafs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beust.jcommander.Parameter;
import com.qafs.common.Constant;
import com.qafs.common.EmailConfirmStatus;
import com.qafs.common.OrderLog_OrderType;
import com.qafs.common.RCOOrderStatus;
import com.qafs.common.Status;
import com.qafs.domain.EmailRecord;
import com.qafs.domain.OrderLog;
import com.qafs.domain.RequirementChangeOrderWithBLOBs;
import com.qafs.domain.User;
import com.qafs.dto.SearchDto;
import com.qafs.service.IEmailRecordService;
import com.qafs.service.IEmailSendService;
import com.qafs.service.IOrderLogService;
import com.qafs.service.IRequirementChangeOrderService;
import com.qafs.service.impl.EmailRecordServiceImpl;
import com.qafs.service.impl.EmailServiceImpl;
import com.qafs.service.impl.OrderLogServiceImpl;
import com.qafs.util.ParaUtil;
import com.qafs.vm.ListVm;
import com.qafs.vm.Response;

@Controller
@RequestMapping("or")
public class OrderRecordController {
	@Resource
	HttpServletRequest request;
	@Resource
	IEmailRecordService emailRecordService;
	@Resource
	ListVm listVm;
	@Resource
	Response response;
	@Resource
	IRequirementChangeOrderService rcoService;
	@Resource
	IEmailSendService emailSend;
	@Resource
	OrderLog orderLog;
	@Resource
	IOrderLogService orderLogService;

	@ResponseBody
	@RequestMapping("search")
	public Response search(SearchDto search) {
		User user = (User) request.getAttribute("user");
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		search.setEmail(user.getEmail());
		listVm.setRows(emailRecordService.search(search));
		listVm.setTotal((long) emailRecordService.getAll(user.getEmail()));
		response.setData(listVm);
		return response;
	}

	@ResponseBody
	@RequestMapping("refuse")
	public Response refuse(RequirementChangeOrderWithBLOBs rcowb, Integer erId,@RequestParam(value="comment") String remark) {
		if (!ParaUtil.notEmpty(rcowb.getId()) || !ParaUtil.notEmpty(erId)) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.PARAMETER_NOT_NULL);
			return response;

		}
		RequirementChangeOrderWithBLOBs rcowbc = new RequirementChangeOrderWithBLOBs();
		rcowbc.setId(rcowb.getId());
		rcowbc.setChangesystemorder(rcowb.getChangesystemorder());
		rcowbc.setConfigurationfilechange(rcowb.getConfigurationfilechange());
		rcowbc.setDatabasechange(rcowb.getDatabasechange());
		rcowbc.setNetworkconfigurationchange(rcowb
				.getNetworkconfigurationchange());
		rcowbc.setRelatedsystem(rcowb.getRelatedsystem());
		rcowbc.setUpyesandno(rcowb.getUpyesandno());
		rcowbc.setOrderstatus(RCOOrderStatus.OJ);
		rcowbc.setRollbackplan(rcowb.getRollbackplan());
		User user = (User) request.getAttribute("user");
		EmailRecord er = new EmailRecord();
		er.setId(erId);
		er.setReceiver(user.getEmail());
		er.setOrderid(rcowb.getId());
		if (!emailRecordService.deal(er, EmailConfirmStatus.NAK)) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.ORDER_STATUS_EXCEPTION);
			return response;
		}
		rcoService.update(rcowbc);
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		orderLog.setOrderid(rcowbc.getId());
		orderLog.setOrdertype(OrderLog_OrderType.T_REQUIREMENT_CHANGE_ORDER);
		orderLog.setUserid(user.getId());
		orderLog.setStatus(Status.SUCCESS);
		orderLog.setOrdercontent(user.getName()+"拒绝了上线工单，备注："+remark);
		orderLog.setRemark(remark);
		orderLogService.save(orderLog);
		return response;
	}

	@ResponseBody
	@RequestMapping("confirm")
	public Response confirm(RequirementChangeOrderWithBLOBs rcowb, Integer erId,@RequestParam(value="comment") String remark) {
		if (!ParaUtil.notEmpty(rcowb.getId()) || !ParaUtil.notEmpty(erId)) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.PARAMETER_NOT_NULL);
			return response;
		}
		RequirementChangeOrderWithBLOBs rcowbc = new RequirementChangeOrderWithBLOBs();
		rcowbc.setId(rcowb.getId());
		rcowbc.setChangesystemorder(rcowb.getChangesystemorder());
		rcowbc.setConfigurationfilechange(rcowb.getConfigurationfilechange());
		rcowbc.setDatabasechange(rcowb.getDatabasechange());
		rcowbc.setNetworkconfigurationchange(rcowb
				.getNetworkconfigurationchange());
		rcowbc.setRelatedsystem(rcowb.getRelatedsystem());
		rcowbc.setUpyesandno(rcowb.getUpyesandno());
		rcowbc.setRollbackplan(rcowb.getRollbackplan());
		User user = (User) request.getAttribute("user");
		EmailRecord er = new EmailRecord();
		er.setId(erId);
		er.setReceiver(user.getEmail());
		er.setOrderid(rcowb.getId());
		if (!emailRecordService.deal(er, EmailConfirmStatus.CC)) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.ORDER_STATUS_EXCEPTION);
			return response;
		}
		rcoService.update(rcowbc);
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		orderLog.setOrderid(rcowbc.getId());
		orderLog.setOrdertype(OrderLog_OrderType.T_REQUIREMENT_CHANGE_ORDER);
		orderLog.setUserid(user.getId());
		orderLog.setStatus(Status.SUCCESS);
		orderLog.setRemark(remark);
		orderLog.setOrdercontent(user.getName()+"确认了上线工单，备注："+orderLog.getRemark());
		orderLogService.save(orderLog);
		return response;
	}

	@ResponseBody
	@RequestMapping("send")
	public Response emailSend(Integer id) {
		if (!ParaUtil.notEmpty(id) || !emailSend.send(id)) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.FALSE);
			return response;
		}
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		return response;
	}
}
