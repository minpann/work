package com.mp.api.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mp.api.dao.user.UserDao;
import com.mp.api.entity.user.User;
import com.mp.api.service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;

	@Resource
	JdbcTemplate jdbcTemplate;

	@Override
	public int deleteByPrimaryKey(String id) {
		return userDao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(User user) {
		return userDao.insert(user);
	}

	@Override
	public User selectByPrimaryKey(String id) {
		return userDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(User user) {
		return userDao.updateByPrimaryKey(user);
	}

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
