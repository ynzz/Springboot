package com.szl.gitdemo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.szl.gitdemo.mapper.UserMapper;
import com.szl.gitdemo.model.User;
import com.szl.gitdemo.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User getUserById(Integer id) {
		return userMapper.getUserById(id);
	}

	@Override
	public List<User> getUserList() {
		return userMapper.getUserList();
	}

	@Override
	public int add(User user) {
		return userMapper.add(user);
	}

	@Override
	public int update(Map<String,Object> map) {
		return userMapper.update(map);
	}

	@Override
	public int delete(Integer id) {
		return userMapper.delete(id);
	}

}
