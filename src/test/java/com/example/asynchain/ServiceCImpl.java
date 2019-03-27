/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ServiceCImpl.java  
 * Package Name:com.example.asynchain 
 * Date:2019年3月26日下午4:30:40  
 * Copyright (c) 2019,  
 *  
*/

package com.example.asynchain;

import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

/**
 * ClassName:ServiceCImpl Date: 2019年3月26日 下午4:30:40
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ServiceCImpl implements ServiceC {

	private final static Logger LOGGER = LoggerFactory.getLogger(ServiceCImpl.class);

	int time = 0;

	public ServiceCImpl(int arg) {
		time = arg;
	}

	@Override
	public String getStr(String str) {
		LOGGER.info("C 从 B处获取响应：" + str);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			LOGGER.error(e.getMessage());
		}
		return "c2b" + str;
	}

}
