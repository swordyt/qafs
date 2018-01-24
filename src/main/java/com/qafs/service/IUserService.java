package com.qafs.service;

import java.util.List;

import com.qafs.domain.User;
import com.qafs.dto.SearchDto;
import com.qafs.vm.LoginVm;

public interface IUserService {
	public User login(User user);

	public List<User> getUsers(SearchDto search);
	
	public Integer getUserNumber();
	
	public Integer addUser(User user);
}
