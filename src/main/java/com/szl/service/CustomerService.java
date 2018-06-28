package com.szl.service;

import com.baomidou.mybatisplus.service.IService;
import com.szl.entity.Customer;

public interface CustomerService extends IService<Customer>{

	public Customer getCustomerById(String id);
}
