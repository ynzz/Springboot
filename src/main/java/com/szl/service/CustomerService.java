package com.szl.service;

import com.szl.entity.Customer;

public interface CustomerService{

	/**
	 * 根据手机号查询用户信息
	 * @param mobile
	 * @return
	 */
	public Customer getCustomerByMobile(String mobile);
}
