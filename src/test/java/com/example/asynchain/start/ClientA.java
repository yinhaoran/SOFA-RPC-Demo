/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ClientA.java  
 * Package Name:com.example.asynchain.start 
 * Date:2019年3月27日上午8:48:20  
 * Copyright (c) 2019,  
 *  
*/

package com.example.asynchain.start;
/**  
 * ClassName:ClientA   
 * Date:     2019年3月27日 上午8:48:20  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.example.asynchain.ServiceB;

public class ClientA {

	private final static Logger LOGGER = LoggerFactory.getLogger(ClientA.class);

	public static void main(String[] args) {
		ConsumerConfig<ServiceB> consumerConfig = new ConsumerConfig<ServiceB>()
				.setApplication(new ApplicationConfig().setAppName("AAA"))
				.setInterfaceId(ServiceB.class.getName())
				.setDirectUrl("bolt://127.0.0.1:12298?appName=BBB")
				.setRegister(false)
				.setTimeout(3000);
		ServiceB serviceB = consumerConfig.refer();
		for (;;) {
			int ret = serviceB.getInt(1);
			LOGGER.info("ret0 " + ret);
			try {
				Thread.sleep(3000);
			} catch (Exception e) {
			}
		}
	}
}
