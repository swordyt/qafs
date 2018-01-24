package com.qafs.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qafs.common.Constant;
import com.qafs.dao.UserMapper;
import com.qafs.domain.User;
import com.qafs.dto.SearchDto;
import com.qafs.service.IUserService;
import com.qafs.util.JwtUtil;
import com.qafs.vm.LoginVm;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	UserMapper userDao;
	@Autowired
	LoginVm loginVm;
	@Autowired
	JwtUtil jwt;

	@Override
	public User login(User user) {
		User userInfo = userDao.selectByEmailAndPassword(user);
		if (userInfo == null) {
			return null;
		}
		return userInfo;
	}

	@Override
	public List<User> getUsers(SearchDto search) {
		return userDao.selectBySearch(search);
	}

	@Override
	public Integer getUserNumber() {
		return userDao.selectAll();
	}

	@Override
	public Integer addUser(User user) {
		if (userDao.selectByOr(user).size() > 0) {
			return 0;
		}
		user.setStatus(1);
		user.setRoleid(Constant.DEFAULT_ROLEID);
		userDao.insertSelective(user);
		return user.getId();
	}

}
