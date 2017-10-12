package com.szl.gitdemo.controller;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.szl.gitdemo.model.JsonResult;
import com.szl.gitdemo.model.User;
import com.szl.gitdemo.service.UserService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@RestController
public class HelloController {

	@Resource
	private UserService userService;
	
	@ApiOperation(value="查询用户信息",notes="根据id查询用户信息")
	@ApiImplicitParam(name="id",value="用户id",dataType="int",required=true,paramType="path")
	@RequestMapping(value="/getUserByIds/{id}",method=RequestMethod.POST)
	public ResponseEntity<JsonResult> getUserById(@PathVariable("id") int id){
		JsonResult r = new JsonResult();
		try {
			User user =userService.getUserById(id);
			r.setResult(user);
			r.setStatus("ok");
		} catch (Exception e) {
			r.setResult(e.getClass().getName()+":"+e.getMessage());
			r.setStatus("error");
		}
		return ResponseEntity.ok(r);
	}
}
