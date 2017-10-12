package com.szl.gitdemo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szl.gitdemo.model.JsonResult;
import com.szl.gitdemo.model.User;
import com.szl.gitdemo.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
public class UserController {

	@Resource
	private UserService userService;

	/**
	 * 获取所有用户的信息
	 * 
	 * @return
	 */
	@ApiOperation(value = "获取所有用户信息", notes = "获取所有用户信息")
	@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> getUserList() {

		JsonResult r = new JsonResult();
		try {
			List<User> user = userService.getUserList();
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}
	
	/**
	 * 根据id获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "获取用户信息", notes = "根据用户id获取")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "Integer", paramType = "path")
	@RequestMapping(value = "/getUserById/{id}", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> getUserById(@PathVariable("id") Integer id) {

		JsonResult r = new JsonResult();
		try {
			User user = userService.getUserById(id);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 更新用户信息
	 * 
	 * @param id
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "更新用户信息", notes = "根据用户id更新")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "path"),
			@ApiImplicitParam(name = "user", value = "用户实体类", required = true, dataType = "User") })
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> update(@PathVariable("id") int id, @RequestBody User user) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", user.getName());
		map.put("age", user.getAge());
		map.put("password", user.getPassword());
		JsonResult r = new JsonResult();
		try {
			int delete = userService.update(map);
			r.setResult(delete);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 添加用户
	 * 
	 * @param user
	 * @return
	 */
	@ApiOperation(value = "添加用户", notes = "添加用户")
	@ApiImplicitParam(name = "user", value = "用户实体类", required = true, dataType = "User")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<JsonResult> add(@RequestBody User user) {
		JsonResult r = new JsonResult();
		try {
			int add = userService.add(user);
			r.setResult(add);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
			e.printStackTrace();
		}
		return ResponseEntity.ok(r);
	}

	/**
	 * 删除用户信息
	 * 
	 * @param id
	 * @return
	 */
	@ApiOperation(value = "删除用户", notes = "删除用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "int", paramType = "path")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<JsonResult> delete(@PathVariable("id") int id) {
		JsonResult r = new JsonResult();
		try {
			int delete = userService.delete(id);
			r.setResult(delete);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName() + ":" + e.getMessage());
			r.setStatus("error");
		}
		return ResponseEntity.ok(r);
	}
	
	@RequestMapping("/selectById")
	public User getById(@RequestBody User user) {
		System.out.println(user.toString());
		return userService.selectById(user);
	}
}
