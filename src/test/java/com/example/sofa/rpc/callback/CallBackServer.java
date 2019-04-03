/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:CallBackServer.java  
 * Package Name:com.example.sofa.rpc.callback 
 * Date:2019年4月2日下午6:24:04  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.example.service.EchoService;
import com.example.service.HelloExceptionService;
import com.example.service.HelloService;
import com.example.service.impl.EchoServiceImpl;
import com.example.service.impl.HelloExceptionServiceImpl;
import com.example.service.impl.HelloServiceImpl;

/**
 * ClassName:CallBackServer Date: 2019年4月2日 下午6:24:04
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class CallBackServer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CallBackServer.class);

	
	public static void main(String[] args) {
		ApplicationConfig applicationConfig = new ApplicationConfig().setAppName("future-server");
		ServerConfig serverConfig = new ServerConfig()
				.setPort(2222)
				.setDaemon(false);
		ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
				.setApplication(applicationConfig)
				.setInterfaceId(HelloService.class.getName())
				.setRef(new HelloServiceImpl())
				.setServer(serverConfig);
		ProviderConfig<EchoService> providerConfig2 = new ProviderConfig<EchoService>()
				.setApplication(applicationConfig)
				.setInterfaceId(EchoService.class.getName())
				.setRef(new EchoServiceImpl())
				.setServer(serverConfig);
		ProviderConfig<HelloExceptionService> providerConfig3 = new ProviderConfig<HelloExceptionService>()
				.setApplication(applicationConfig)
				.setInterfaceId(HelloExceptionService.class.getName())
				.setRef(new HelloExceptionServiceImpl())
				.setServer(serverConfig);
		providerConfig.export();
		LOGGER.debug("服务A成功发布!");
		providerConfig2.export();
		LOGGER.debug("服务B成功发布!");
		providerConfig3.export();
		LOGGER.debug("服务C成功发布!");
	}
}
