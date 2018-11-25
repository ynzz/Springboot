package com.szl.service;

import com.baomidou.mybatisplus.service.IService;
import com.szl.entity.Customer;

public interface CustomerService extends IService<Customer>{

	/**
	 * 根据手机号查询用户信息
	 * @param mobile
	 * @return
	 */
	public Customer getCustomerByMobile(String mobile);
	
	/**
	 * 测试异步调用
	 * @throws InterruptedException
	 */
	public void testAsync() throws InterruptedException;
}
