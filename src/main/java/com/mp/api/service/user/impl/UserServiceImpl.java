package com.mp.api.service.user.impl;

import com.mp.api.dao.user.UserDao;
import com.mp.api.entity.user.User;
import com.mp.api.service.user.UserService;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Resource
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDao.getAll();
	}

	@Override
	public List<User> queryList() {
		String sql = "select * from t_user";
		List<User> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(User.class));
		return list;
	}

}
