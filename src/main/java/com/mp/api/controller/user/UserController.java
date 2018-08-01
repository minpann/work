package com.mp.api.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mp.api.common.response.ListResponse;
import com.mp.api.common.response.ResponseBuilder;
import com.mp.api.entity.user.User;
import com.mp.api.service.user.UserService;

@Controller
public class UserController {

	private static Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;
	
	@RequestMapping(value = "/user/list")
	@ResponseBody
	public ListResponse<User> getAll(){
		List<User> userList = this.userService.getAll();
		LOG.info(userList.toString());
        return ResponseBuilder.listResponse(userList);
	}
	
}

