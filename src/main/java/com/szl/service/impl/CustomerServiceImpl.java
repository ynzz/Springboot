package com.szl.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.szl.entity.Customer;
import com.szl.mapper.CustomerMapper;
import com.szl.service.CustomerService;

@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService{

	@Override
	public Customer getCustomerById(String id) {
		Customer customer = new Customer();
		customer.setUuid(id);
		return baseMapper.getCustomerById(id);
	}

}
