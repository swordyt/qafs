package com.qafs.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qafs.common.Constant;
import com.qafs.domain.User;
import com.qafs.dto.LoginDto;
import com.qafs.dto.SearchDto;
import com.qafs.service.IUserService;
import com.qafs.util.JwtUtil;
import com.qafs.util.ParaUtil;
import com.qafs.vm.ListVm;
import com.qafs.vm.LoginVm;
import com.qafs.vm.OrderVm;
import com.qafs.vm.Response;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	HttpServletRequest request;
	@Autowired
	Response response;
	@Resource
	IUserService userService;
	@Resource
	ListVm list;
	@Resource
	User user;
	@Resource
	LoginVm loginVm;
	@Resource
	JwtUtil jwt;
	@Resource
	OrderVm orderVm;

	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST)
	public Response login(LoginDto login) {
		if (!ParaUtil.notEmpty(login.getEmail())
				|| !ParaUtil.notEmpty(login.getPassword())) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.ACCOUNT_OR_PASSWORD_NOTEMPTY);
			return response;
		}
		user.setEmail(login.getEmail());
		user.setPassword(login.getPassword());
		User userInfo = userService.login(user);
		if (userInfo == null) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.ACCOUNT_OR_PASSWORD_ERROR);
			return response;
		}

		loginVm.setEmail(userInfo.getEmail());
		loginVm.setName(userInfo.getName());
		loginVm.setUserId(userInfo.getId() + "");
		loginVm.setToken(jwt.createJWT(Constant.JWT.JWT_ID,
				jwt.generalSubject(userInfo), Constant.JWT.JWT_TTL));
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		response.setData(loginVm);
		return response;
	}

	@ResponseBody
	@RequestMapping(value = "users", method = RequestMethod.POST)
	public Response getUsers(SearchDto search) {
		if (!(ParaUtil.notNull(search) && ParaUtil.notNull(search.getLimit()) && search
				.getLimit() >= 0)) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg("参数不合法！");
		}
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		list.setRows(userService.getUsers(search));
		list.setTotal((long) userService.getUserNumber());
		response.setData(list);
		return response;
	}

	@ResponseBody
	@RequestMapping("add")
	public Response addUser(User user) {
		if (!ParaUtil.notEmpty(user.getEmail())
				|| !ParaUtil.notEmpty(user.getName())) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.PARAMETER_NOT_NULL);
			return response;
		}
		orderVm.setOrderId(userService.addUser(user));
		if (orderVm.getOrderId() == 0) {
			response.setCode(Constant.CODE.FALSE);
			response.setMsg(Constant.MSG.NAME_OR_EMAIL_IS_EXIST);
			return response;
		}
		response.setCode(Constant.CODE.SUCCESS);
		response.setMsg(Constant.MSG.SUCCESS);
		response.setData(orderVm);
		return response;
	}
}
