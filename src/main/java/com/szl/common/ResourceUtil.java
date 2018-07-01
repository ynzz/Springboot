package com.szl.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author szl
 * @data 2018年7月1日 下午9:59:00
 *
 */
@Configuration
@ConfigurationProperties(prefix="szl")
public class ResourceUtil {

	private String name;
	
	private String age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
}
