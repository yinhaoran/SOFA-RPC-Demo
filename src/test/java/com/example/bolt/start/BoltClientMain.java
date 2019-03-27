/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:BoltClientMain.java  
 * Package Name:com.example.bolt.start 
 * Date:2019年3月27日上午10:16:09  
 * Copyright (c) 2019,  
 *  
*/

package com.example.bolt.start;
/**  
 * ClassName:BoltClientMain   
 * Date:     2019年3月27日 上午10:16:09  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.example.service.EchoService;
import com.example.service.HelloService;

public class BoltClientMain {
	private static final Logger LOGGER = LoggerFactory.getLogger(BoltClientMain.class);

	public static void main(String[] args) {
		ApplicationConfig applicationConfig = new ApplicationConfig().setAppName("test-client");
		/**
		 * 服务消费者配置：【HELLOSERVICE】
		 */
		ConsumerConfig<HelloService> consumerConfigA = new ConsumerConfig<HelloService>()
				.setApplication(applicationConfig)
				.setInterfaceId(HelloService.class.getName())
				.setDirectUrl("bolt://127.0.0.1:22000")
				.setRegister(false)
				.setTimeout(3000);
		HelloService helloService = consumerConfigA.refer();
		/**
		 * 服务消费者配置：【ECHOSERVICE】
		 */
		ConsumerConfig<EchoService> consumerConfigB = new ConsumerConfig<EchoService>()
				.setApplication(applicationConfig)
				.setInterfaceId(EchoService.class.getName())
				.setDirectUrl("bolt://127.0.0.1:22000")
				.setRegister(false)
				.setTimeout(3000);
		EchoService echoService = consumerConfigB.refer();
		consumerConfigB.unRefer();
		LOGGER.warn("started at pid {}", RpcRuntimeContext.PID);
		while(true) {
			String s = helloService.sayHello("YHR");
			LOGGER.warn("{}",s);
			try {
                Thread.sleep(2000);
            } catch (Exception e) {
            }
		}
		
	}
}




















