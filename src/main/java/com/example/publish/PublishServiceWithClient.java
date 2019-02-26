/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:PublishServiceWithClient.java  
 * Package Name:com.example.publish 
 * Date:2019年2月21日下午4:17:45  
 * Copyright (c) 2019,  
 *  
*/

package com.example.publish;
/**  
 * ClassName:PublishServiceWithClient   
 * Date:     2019年2月21日 下午4:17:45  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import com.alipay.sofa.runtime.api.aware.ClientFactoryAware;
import com.alipay.sofa.runtime.api.client.ClientFactory;
import com.alipay.sofa.runtime.api.client.ServiceClient;
import com.alipay.sofa.runtime.api.client.param.ServiceParam;
import com.example.service.SimpleJvmService;
import com.example.service.impl.SimpleJvmServiceImpl;

/**
 * 
 * ClassName:PublishServiceWithClient 
 * Function: TODO 部署服务的客户端 
 * 
 * ADD REASON(可选). date: 2019年2月26日 下午5:15:54
 * 
 * @author yin
 * @version
 * @since JDK 1.6
 */
public class PublishServiceWithClient implements ClientFactoryAware {
	private ClientFactory clientFactory;

	@Override
	public void setClientFactory(ClientFactory clientFactory) {
		this.clientFactory = clientFactory;
	}

	public void init() {
		ServiceClient serverClient = clientFactory.getClient(ServiceClient.class);
		ServiceParam serviceParam = new ServiceParam();
		serviceParam.setInstance(new SimpleJvmServiceImpl("Hello,This is jvm service client"));
		serviceParam.setInterfaceType(SimpleJvmService.class);
		serviceParam.setUniqueId("serviceClientImpl");
		serverClient.service(serviceParam);
	}
}
