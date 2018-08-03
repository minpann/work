package com.mp.api.service.user;

import java.util.List;

import com.mp.api.entity.user.User;

public interface UserService {

	int deleteByPrimaryKey(String id);

	int insert(User user);

	User selectByPrimaryKey(String id);

	int updateByPrimaryKey(User user);

	List<User> getAll();

	List<User> queryList();
}
