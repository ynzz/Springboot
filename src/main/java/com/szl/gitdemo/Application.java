package com.szl.gitdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@SpringBootApplication
@MapperScan("com.szl.sbdm.mapper")
@EnableSwagger2
public class Application {
	
	@RequestMapping("/")
	public String sayHello() {
		return "HelloWorld!";
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
