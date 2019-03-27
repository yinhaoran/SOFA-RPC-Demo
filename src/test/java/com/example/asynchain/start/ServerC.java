/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ServerC.java  
 * Package Name:com.example.asynchain.start 
 * Date:2019年3月27日上午8:48:11  
 * Copyright (c) 2019,  
 *  
*/

package com.example.asynchain.start;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.example.asynchain.ServiceC;
import com.example.asynchain.ServiceCImpl;

/**
 * ClassName:ServerC Date: 2019年3月27日 上午8:48:11
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ServerC {
	public static void main(String[] args) {
		ServerConfig serverConfig = new ServerConfig()
				.setPort(12299)
				.setDaemon(false);
		ProviderConfig<ServiceC> providerConfig = new ProviderConfig<ServiceC>()
				.setInterfaceId(ServiceC.class.getName())
				.setApplication(new ApplicationConfig().setAppName("CCC"))
				.setRef(new ServiceCImpl(1000))
				.setServer(serverConfig)
				.setRegister(false);
		providerConfig.export();
	}
}
