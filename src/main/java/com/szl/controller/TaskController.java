package com.szl.controller;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * @author sunzl
 * @date 2018年11月25日
 * 定时任务，@EnableScheduling， @Scheduled 实现
 */
//@EnableScheduling
@Component
public class TaskController {

	
//	@Scheduled(cron = "0/5 * * * * *")//每隔5秒执行一次 ，@Scheduled(fixedRate=5000)
//	@Scheduled(fixedDelay=5000) 每隔5+3=8秒执行一次
	public void sayHello() throws InterruptedException{
		Thread.sleep(3000);
		System.out.println("hello springboot!");
	}
}
