package com.mp.api.controller.user;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mp.api.common.response.JsonBackData;
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

	@RequestMapping(value = "delete")
	@ResponseBody
	public JsonBackData delete(String id) {
		JsonBackData backData = new JsonBackData();
		try {
			int count = userService.deleteByPrimaryKey(id);
			if (count > 0) {
				backData.setBackMsg("删除成功！");
			} else {
				backData.setBackMsg("没有找到id为：" + id + "的记录。");
			}
		} catch (Exception e){
			backData.setSuccess(false);
			backData.setBackMsg("删除异常" + e);
		}
		return backData;
	}

	@RequestMapping(value = "update")
	@ResponseBody
	public JsonBackData queryList(@RequestBody User user) {
		JsonBackData backData = new JsonBackData();
		try {
			int count = userService.updateByPrimaryKey(user);
			if (count > 0) {
				backData.setBackMsg("更新成功！");
			} else {
				backData.setBackMsg("没有找到user id为：" + user.getId() + "的记录。");
			}
		} catch (Exception e){
			backData.setSuccess(false);
			backData.setBackMsg("更新异常" + e);
		}
		return backData;
	}

	@RequestMapping(value = "insert")
	@ResponseBody
	public JsonBackData insert(@RequestBody User user) {
		JsonBackData backData = new JsonBackData();
		try {
			userService.insert(user);
			backData.setBackMsg("新增数据成功！");
		} catch (Exception e){
			backData.setSuccess(false);
			backData.setBackMsg("新增数据异常" + e);
		}
		return backData;
	}
	
}

