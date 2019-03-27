/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:EchoServiceImpl.java  
 * Package Name:com.example.service.impl 
 * Date:2019年3月27日上午11:20:07  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.service.EchoService;

/**
 * ClassName:EchoServiceImpl Date: 2019年3月27日 上午11:20:07
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class EchoServiceImpl implements EchoService {

	private static final Logger LOGGER = LoggerFactory.getLogger(EchoServiceImpl.class);

	@Override
	public String echoStr(String str) {
		LOGGER.info(str);
		return "【ECHO SERVICEIMPL】";
	}

}
