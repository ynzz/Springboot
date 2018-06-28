package com.szl.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.szl.entity.Customer;
import com.szl.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Resource
	private CustomerService customerService;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello(){
		return "hello springBoot!";
	}
	
	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
	public Customer getCustomer(@RequestParam(value = "uuid") String uuid){
		return customerService.getCustomerById(uuid);
	}
}
