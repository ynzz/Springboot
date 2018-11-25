package com.szl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author sunzl
 * @date 2018年11月24日
 * 模板
 */
@Controller
public class HelloWorldController {

	@RequestMapping("/hello")
	public String hello(){
		
		return "/index";
	}
}
