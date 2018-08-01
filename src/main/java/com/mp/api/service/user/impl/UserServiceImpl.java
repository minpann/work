package com.mp.api.service.user.impl;

import com.mp.api.dao.user.UserDao;
import com.mp.api.entity.user.User;
import com.mp.api.service.user.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDao.getAll();
	}

}
