package com.szl.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String sayHello(){
		return "hello springBoot!";
	}
}
