/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:RestServerMain.java  
 * Package Name:com.example.sofa.rpc.rest 
 * Date:2019年4月2日上午8:42:42  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.rest;
/**  
 * ClassName:RestServerMain   
 * Date:     2019年4月2日 上午8:42:42  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.example.rpc.protocol.ProtocolType;

public class RestServerMain {
	private final static Logger LOGGER = LoggerFactory.getLogger(RestServerMain.class);

	private final static int PORT = 8888;

	public static void main(String[] args) {
		ApplicationConfig application = new ApplicationConfig().setAppName("test-server");
		ServerConfig serverConfig = new ServerConfig()
				.setProtocol(ProtocolType.rest.name())
				.setPort(PORT)
				.setDaemon(false);
		ProviderConfig<RestService> providerConfig = new ProviderConfig<RestService>()
				.setInterfaceId(RestService.class.getName())
				.setApplication(application)
				.setRef(new RestServiceImpl())
				.setBootstrap(ProtocolType.rest.name())
				.setServer(serverConfig)
				.setRegister(false);
		providerConfig.export();
		LOGGER.info("START AT PID {}", RpcRuntimeContext.PID);
		@SuppressWarnings({ "unused", "rawtypes" })
		Map map = RpcRuntimeContext.getContext();
		LOGGER.info("START AT PID {}", RpcRuntimeContext.PID);
	}
}
