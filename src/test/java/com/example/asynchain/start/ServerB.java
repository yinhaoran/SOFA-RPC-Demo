/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ServerB.java  
 * Package Name:com.example.asynchain.start 
 * Date:2019年3月26日下午5:03:28  
 * Copyright (c) 2019,  
 *  
*/

package com.example.asynchain.start;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.example.asynchain.ServiceB;
import com.example.asynchain.ServiceBImpl;
import com.example.asynchain.ServiceC;

/**
 * ClassName:ServerB Date: 2019年3月26日 下午5:03:28
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ServerB {
	public static void main(String[] args) {
		ConsumerConfig<ServiceC> consumerConfig = new ConsumerConfig<ServiceC>()
				.setApplication(new ApplicationConfig().setAppName("BBB"))
				.setInterfaceId(ServiceC.class.getName())
				.setDirectUrl("bolt://127.0.0.1:12299?appName=CCC")
				.setRegister(false)
				.setInvokeType("callback")
				.setTimeout(2000);
		ServiceC serviceC = consumerConfig.refer();
		ServerConfig serverConfig = new ServerConfig()
				.setPort(12298)
				.setDaemon(false);
		ProviderConfig<ServiceB> providerConfig = new ProviderConfig<ServiceB>()
				.setInterfaceId(ServiceB.class.getName())
				.setApplication(new ApplicationConfig().setAppName("BBB"))
				.setRef(new ServiceBImpl(serviceC))
				.setServer(serverConfig)
				.setRegister(false);
		providerConfig.export();
	}
}
