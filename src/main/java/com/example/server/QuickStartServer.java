/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:QuickStartServer.java  
 * Package Name:com.example.server 
 * Date:2019年2月20日下午2:34:10  
 * Copyright (c) 2019,  
 *  
*/

package com.example.server;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.example.service.HelloService;
import com.example.service.impl.HelloServiceImpl;

/**
 * ClassName:QuickStartServer Date: 2019年2月20日 下午2:34:10
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class QuickStartServer {
	public static void main(String[] args) {
		ServerConfig serverConfig = new ServerConfig()
				.setProtocol("bolt")
				.setPort(12200)
				.setDaemon(false);
		ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())
				.setRef(new HelloServiceImpl())
				.setServer(serverConfig);
		providerConfig.export();
	}
}
