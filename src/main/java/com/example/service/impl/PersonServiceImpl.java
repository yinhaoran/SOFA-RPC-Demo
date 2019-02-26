/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:PersonServiceImpl.java  
 * Package Name:com.example.service.impl 
 * Date:2019年2月21日上午11:23:46  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service.impl;

import com.example.service.PersonService;

/**
 * ClassName:PersonServiceImpl Date: 2019年2月21日 上午11:23:46
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class PersonServiceImpl implements PersonService {

	@Override
	public String sayName(String name) {

		// TODO Auto-generated method stub
		return "Hi " + name + " !";
	}

}
