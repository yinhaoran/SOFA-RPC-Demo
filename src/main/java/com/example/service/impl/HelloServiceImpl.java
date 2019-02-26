/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:HelloServiceImpl.java  
 * Package Name:com.example.service.impl 
 * Date:2019年2月20日下午2:27:31  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service.impl;

import com.example.service.HelloService;

/**
 * ClassName:HelloServiceImpl Date: 2019年2月20日 下午2:27:31
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class HelloServiceImpl implements HelloService {

	@Override
	public String sayHello(String name) {
		return "hello " + name;
	}

}
