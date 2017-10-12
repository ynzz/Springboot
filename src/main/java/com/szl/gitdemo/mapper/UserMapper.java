package com.szl.gitdemo.mapper;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.szl.gitdemo.model.User;

public interface UserMapper extends BaseMapper<User>{

	public User getUserById(Integer id);
	
	public List<User> getUserList();
	
	public int add(User user);
	
	public int update(Map<String,Object> map);
	
	public int delete(Integer id);
}
