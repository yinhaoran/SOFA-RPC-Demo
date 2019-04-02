/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:RestServiceImpl.java  
 * Package Name:com.example.sofa.rpc.rest 
 * Date:2019年4月1日下午6:20:21  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.rest;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:RestServiceImpl Date: 2019年4月1日 下午6:20:21
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class RestServiceImpl implements RestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceImpl.class);

	private final Map<String, String> map = new ConcurrentHashMap<String, String>();

	@Override
	public String add(String code, String name) {
		LOGGER.info("POST CODE IS" + code + " NAME IS" + name);
		map.put(code, name);
		// TODO Auto-generated method stub
		return "create ok! " + code;
	}

	@Override
	public String post(String code, String body) {
		return "server " + code + body;
	}

	@Override
	public String get(String code) {

		// TODO Auto-generated method stub
		return "server " + code;
	}

}
