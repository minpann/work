package com.mp.api.controller;
import com.mp.api.persistence.model.User;
import com.mp.api.response.ListResponse;
import com.mp.api.response.ResponseBuilder;
import com.mp.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

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

