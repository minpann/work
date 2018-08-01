package com.mp.api.controller.user;

import java.util.List;

import javax.annotation.Resource;

import com.mp.api.common.exception.ServiceException;
import com.mp.api.common.response.JsonBackData;
import com.mp.api.common.response.SimpleResponse;
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
@RequestMapping(value = "user")
public class UserController {

	private static Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;
	
	@RequestMapping(value = "list")
	@ResponseBody
	public ListResponse<User> getAll(){
		List<User> userList = this.userService.getAll();
		LOG.info(userList.toString());
        return ResponseBuilder.listResponse(userList);
	}

	@RequestMapping(value = "queryList")
	@ResponseBody
	public JsonBackData queryList() {
		JsonBackData backData = new JsonBackData();
		try {
			List<User> list = userService.queryList();
			backData.setBackData(list);
		} catch (Exception e){
			backData.setSuccess(false);
			backData.setBackMsg("查询异常" + e);
		}
		return backData;
	}
	
}

