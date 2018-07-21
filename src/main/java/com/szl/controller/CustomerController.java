package com.szl.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.szl.common.ResourceUtil;
import com.szl.common.ResultEnum;
import com.szl.entity.Customer;
import com.szl.service.CustomerService;
import com.szl.vo.ResultVo;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Resource
	private CustomerService customerService;
	
	@Autowired
	private ResourceUtil resourceUtil;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello(){
		return "hello springBoot!" + resourceUtil.getName();
	}
	/**
	 * 根据手机号查询用户信息
	 * @param mobile
	 * @return
	 */
	@RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
	public ResultVo getCustomer(@RequestParam(value = "mobile") String mobile){
		Customer customer = customerService.getCustomerByMobile(mobile);
		return ResultVo.getData(ResultEnum.SUCCESS, customer);
	}
}
