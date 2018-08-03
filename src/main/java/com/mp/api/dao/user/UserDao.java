package com.mp.api.dao.user;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.mp.api.entity.user.User;

@Repository
public interface UserDao {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

	List<User> getAll();
}