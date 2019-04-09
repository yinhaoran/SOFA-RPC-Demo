/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:FutrueClientMain.java  
 * Package Name:com.example.sofa.rpc.future 
 * Date:2019年4月3日下午3:11:45  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.future;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.ExecutionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.api.future.SofaResponseFuture;
import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.message.bolt.BoltResponseFuture;
import com.example.service.EchoService;
import com.example.service.HelloService;

/**
 * ClassName:FutrueClientMain Date: 2019年4月3日 下午3:11:45
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class FutrueClientMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(FutrueClientMain.class);
	
	private static final int TIMES = 100;

	public static void main(String[] args) {
		ApplicationConfig applicationConfig = new ApplicationConfig().setAppName("future-client");

		ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
				.setApplication(applicationConfig).setInterfaceId(HelloService.class.getName())
				.setInvokeType(RpcConstants.INVOKER_TYPE_FUTURE).setTimeout(50000)
				.setDirectUrl("bolt://127.0.0.1:2222?appName=future-server");
		HelloService helloService = consumerConfig.refer();

		ConsumerConfig<EchoService> consumerConfig2 = new ConsumerConfig<EchoService>()
				.setApplication(applicationConfig).setInterfaceId(EchoService.class.getName())
				.setInvokeType(RpcConstants.INVOKER_TYPE_FUTURE).setTimeout(50000)
				.setDirectUrl("bolt://127.0.0.1:2222?appName=future-server");
		EchoService echoService = consumerConfig2.refer();
		for (int i = 0; i < TIMES; i++) {
			try {
				String str = helloService.sayHello("yhr");
				LOGGER.warn("must null :{}", str);
				assertEquals(str, null);
				@SuppressWarnings("unchecked")
				BoltResponseFuture<String> futurea = (BoltResponseFuture<String>) SofaResponseFuture.getFuture();
				str = (String) futurea.get();
				LOGGER.warn("get future1: {}, elapse: {}", str, futurea.getElapsedTime());
				LOGGER.debug("FUTURE a IS {}", str);
				/**
				 * -------------------------------------------------------------------------------------------------
				 */
				String echo = echoService.echoStr("yhr");
				LOGGER.warn("must null :{}", echo);
				assertEquals(echo, null);
				@SuppressWarnings("unchecked")
				BoltResponseFuture<String> futureb = (BoltResponseFuture<String>) SofaResponseFuture.getFuture();
				echo = (String) futureb.get();
				LOGGER.warn("get future1: {}, elapse: {}", str, futurea.getElapsedTime());
				LOGGER.debug("FUTURE b IS {}", echo);
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}

	}
}
