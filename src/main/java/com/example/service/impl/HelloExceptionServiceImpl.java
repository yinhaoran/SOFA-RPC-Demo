/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:HelloExceptionServiceImpl.java  
 * Package Name:com.example.service.impl 
 * Date:2019年4月3日下午4:30:36  
 * Copyright (c) 2019,  
 *  
*/  
  
package com.example.service.impl;

import com.example.service.HelloExceptionService;

/**  
 * ClassName:HelloExceptionServiceImpl   
 * Date:     2019年4月3日 下午4:30:36  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */
public class HelloExceptionServiceImpl implements HelloExceptionService {

	@Override
	public String getException() throws Exception {
		throw new Exception("模拟异常");
	}

}
  
