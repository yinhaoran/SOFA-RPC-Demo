/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:QuickStartClient.java  
 * Package Name:com.example.server 
 * Date:2019年2月20日下午2:38:13  
 * Copyright (c) 2019,  
 *  
*/

package com.example.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.example.service.HelloService;

/**
 * ClassName:QuickStartClient Date: 2019年2月20日 下午2:38:13
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class QuickStartClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(QuickStartClient.class);
	
	public static void main(String[] args) {
		ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName()).setProtocol("bolt")
				.setDirectUrl("bolt://127.0.0.1:12200");
		HelloService helloService = consumerConfig.refer();
		while (true) {
			String message = helloService.sayHello("殷浩然");
			LOGGER.debug(message);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
			}
		}

	}
}
