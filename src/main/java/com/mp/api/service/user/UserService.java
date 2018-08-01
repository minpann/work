package com.mp.api.service.user;

import com.mp.api.entity.user.User;

import java.util.List;

public interface UserService {

	List<User> getAll();

	List<User> queryList();
}
