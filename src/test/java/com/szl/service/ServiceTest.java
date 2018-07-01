package com.szl.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.szl.entity.Customer;
import com.szl.service.CustomerService;

/**
 * 测试Service
 * @author szl
 * @data 2018年6月30日 下午10:32:10
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {

	@Autowired
	private CustomerService customerService;
	
	@Test
	public void getCustomerByMobileTest(){
		Customer customer = customerService.getCustomerByMobile("1882928146");
		Assert.assertEquals("信仰", customer.getCustomerName());
	}
}
