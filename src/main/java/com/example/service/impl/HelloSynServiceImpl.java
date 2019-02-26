/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:HelloSynServiceImpl.java  
 * Package Name:com.example.service.impl 
 * Date:2019年2月20日下午3:32:30  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service.impl;

/**
 * ClassName:HelloSynServiceImpl Date: 2019年2月20日 下午3:32:30
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class HelloSynServiceImpl implements HelloSynService {

	@Override
	public String saySync(String name) {
		return name;
	}

}
