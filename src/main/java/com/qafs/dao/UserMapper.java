package com.qafs.dao;

import java.util.List;

import com.qafs.domain.User;
import com.qafs.dto.SearchDto;

public interface UserMapper {
	public User selectByPrimaryKey(Integer id);

	public List<User> selectByIdAndEmail(User record);

	public List<User> selectBySearch(SearchDto search);

	public User selectByEmailAndPassword(User record);

	/**
	 * 返回数据总数
	 * */
	public Integer selectAll();

	/**
	 * 根据传入信息完全匹配是否存在该user
	 * */
	public List<User> selectByAnd(User user);
	/**
	 * 根据传入信息其中一个是否存在该user
	 * */
	public List<User> selectByOr(User user);

	public void insertSelective(User urecord);
}