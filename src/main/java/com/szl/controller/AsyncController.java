package com.szl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szl.service.CustomerService;
import com.szl.vo.ResultVo;

/**
 * @author sunzl
 * @date 2018年11月25日
 * 异步调用： @EnableAsync 和 @Async 实现
 */
@RestController
public class AsyncController {
	
	@Autowired
	private CustomerService customerService;

	/**
	 * 测试异步调用
	 * 在异步方法上添加@Async注解，在启动类Application上添加@EnableAsync注解
	 * 异步方法不能和调用方法同一个类中，负责会用同一个线程执行，异步不起作用
	 * @return
	 * @throws InterruptedException
	 */
	@RequestMapping("/testAsync")
	public ResultVo testAsync() throws InterruptedException{
		ResultVo resultVo = ResultVo.get("0", "成功");
		customerService.testAsync();
		async();
		return resultVo;
	}
	
	/**
	 * 同一个类，异步调用不起作用
	 * @throws InterruptedException 
	 */
	@Async
	private void async() throws InterruptedException{
		System.out.println("同类异步开始");
		Thread.sleep(5000);
		System.out.println("同类异步结束");
	}
}
