package com.szl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szl.entity.Customer;
import com.szl.mapper.CustomerMapper;
import com.szl.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerMapper customerMapper;
	
	@Override
	public Customer getCustomerByMobile(String mobile) {
		return customerMapper.getCustomerByMobile(mobile);
	}

}
