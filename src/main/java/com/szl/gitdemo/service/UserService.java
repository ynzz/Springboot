package com.szl.gitdemo.service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;
import com.szl.gitdemo.model.User;

public interface UserService extends IService<User>{

	public User getUserById(Integer id);
	
	public List<User> getUserList();
	
	public int add(User user);
	
	public int update(Map<String,Object> map);
	
	public int delete(Integer id);
}
