/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SimpleJvmServiceImpl.java  
 * Package Name:com.example.service.impl 
 * Date:2019年2月21日下午4:25:58  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service.impl;

import com.example.service.SimpleJvmService;

/**
 * ClassName:SimpleJvmServiceImpl Date: 2019年2月21日 下午4:25:58
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class SimpleJvmServiceImpl implements SimpleJvmService {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SimpleJvmServiceImpl(String message) {
		this.message = message;
	}

	public SimpleJvmServiceImpl() {
		super();
	}

	@Override
	public String message() {
		System.out.println(message);
		return message;
	}

}
