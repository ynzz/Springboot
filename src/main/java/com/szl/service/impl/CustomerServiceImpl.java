package com.szl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.szl.entity.Customer;
import com.szl.mapper.CustomerMapper;
import com.szl.service.CustomerService;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Customer getCustomerByMobile(String mobile) {
		return customerMapper.getCustomerByMobile(mobile);
	}
	
	@Async
	@Override
	public void testAsync() throws InterruptedException{
		System.out.println("异步开始");
		Thread.sleep(10000);
		System.out.println("异步调用结束");
	}

}
