/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:BoltServerMain.java  
 * Package Name:com.example.bolt.start 
 * Date:2019年3月27日上午11:27:31  
 * Copyright (c) 2019,  
 *  
*/

package com.example.bolt.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.example.service.EchoService;
import com.example.service.HelloService;
import com.example.service.impl.EchoServiceImpl;
import com.example.service.impl.HelloServiceImpl;

/**
 * ClassName:BoltServerMain Date: 2019年3月27日 上午11:27:31
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class BoltServerMain {

	private final static String APPNAME = "test-server";

	private final static Logger LOGGER = LoggerFactory.getLogger(Logger.class);

	public static void main(String[] args) {
		ApplicationConfig application = new ApplicationConfig().setAppName(APPNAME);
		ServerConfig serverConfig = new ServerConfig().setPort(22000).setDaemon(false);
		ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName()).setApplication(application).setRef(new HelloServiceImpl())
				.setServer(serverConfig).setRegister(false);
		ProviderConfig<EchoService> providerConfig2 = new ProviderConfig<EchoService>()
				.setInterfaceId(EchoService.class.getName()).setApplication(application).setRef(new EchoServiceImpl())
				.setServer(serverConfig).setRegister(false);
		providerConfig.export();
		providerConfig2.export();
		LOGGER.warn("started at pid {}", RpcRuntimeContext.PID);
	}
}
