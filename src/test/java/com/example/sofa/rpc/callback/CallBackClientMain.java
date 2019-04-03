/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:CallBackClientMain.java  
 * Package Name:com.example.sofa.rpc.callback 
 * Date:2019年4月3日上午8:50:25  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.callback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.invoke.SofaResponseCallback;
import com.alipay.sofa.rpc.core.request.RequestBase;
import com.example.service.HelloService;

/**
 * <pre>
 * onAppResponse：当客户端接收到服务端的正常返回的时候，SOFARPC 会回调这个方法。
 * onAppException：当客户端接收到服务端的异常响应的时候，SOFARPC 会回调这个方法。
 * onSofaException：当 SOFARPC本身出现一些错误，比如路由错误的时候，SOFARPC 会回调这个方法
 * </pre>
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class CallBackClientMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(CallBackClientMain.class);

	public static final String DIRECT_URL = "bolt://127.0.0.1:2222?appName=future-server";

	public static void main(String[] args) throws InterruptedException {
		ApplicationConfig applicationConfig = new ApplicationConfig().setAppName("future-server");
		/**
		 * 引用服务
		 */
		@SuppressWarnings("rawtypes")
		ConsumerConfig<HelloService> consumerConfig = new ConsumerConfig<HelloService>()
				.setApplication(applicationConfig).setInterfaceId(HelloService.class.getName())
				.setInvokeType(RpcConstants.INVOKER_TYPE_CALLBACK).setTimeout(5000)
				.setOnReturn(new SofaResponseCallback() {
					@Override
					public void onAppResponse(Object appResponse, String methodName, RequestBase request) {
						LOGGER.info("Interface get result : {} , method name is {}", appResponse, methodName);
						LOGGER.info("获取响应后可以在这里做一些操作");
					}

					@Override
					public void onAppException(Throwable throwable, String methodName, RequestBase request) {
						LOGGER.info("Interface get app exception : {} , method name is {}", throwable, methodName);
					}

					@Override
					public void onSofaException(SofaRpcException sofaException, String methodName,
							RequestBase request) {
						LOGGER.info("Interface get sofa exception : {} , method name is {}", sofaException, methodName);
					}
				}).setDirectUrl(DIRECT_URL);
		HelloService helloService = consumerConfig.refer();
		LOGGER.warn("started at pid {}", RpcRuntimeContext.PID);
		// String result = helloService.sayHello("xxx");
		// LOGGER.info("sayHello方法获取响应的结果：" + result);
		for (int i = 0; i < 100; i++) {
			String result = helloService.sayHello("xxx");
			LOGGER.warn("接口返回结果：" + result);
			Thread.sleep(2000);
		}

		synchronized (CallBackClientMain.class) {
			while (true) {
				CallBackClientMain.class.wait();
			}
		}
	}
}
