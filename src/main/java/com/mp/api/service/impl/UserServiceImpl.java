package com.mp.api.service.impl;

import com.mp.api.persistence.dao.UserDao;
import com.mp.api.persistence.model.User;
import com.mp.api.service.UserService;
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
