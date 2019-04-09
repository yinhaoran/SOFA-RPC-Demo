/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:CallBackClientTest.java  
 * Package Name:com.example.sofa.rpc.callback 
 * Date:2019年4月3日下午4:31:53  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.callback;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.common.RpcConstants;
import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.invoke.SofaResponseCallback;
import com.alipay.sofa.rpc.core.request.RequestBase;
import com.example.service.HelloExceptionService;

/**
 * ClassName:CallBackClientTest Date: 2019年4月3日 下午4:31:53
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class CallBackClientTest {
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CallBackClientTest.class);

	private static final int TIMES = 100;
	
	@Test
	public void test() {
		ApplicationConfig applicationConfig = new ApplicationConfig().setAppName("future-server");
		/**
		 * 引用服务
		 */
		ConsumerConfig<HelloExceptionService> consumerConfig = new ConsumerConfig<HelloExceptionService>()
				.setApplication(applicationConfig)
				.setInterfaceId(HelloExceptionService.class.getName())
				.setInvokeType(RpcConstants.INVOKER_TYPE_CALLBACK)
				.setTimeout(5000)
				.setOnReturn(new SofaResponseCallback<String>() {
					@Override
					public void onAppResponse(Object appResponse, String methodName, RequestBase request) {
						// TODO Auto-generated method stub  
					}

					@Override
					public void onAppException(Throwable throwable, String methodName, RequestBase request) {
						LOGGER.error("Throwable is {},MethodName is {},request is {}" ,throwable,methodName,request);
					}

					@Override
					public void onSofaException(SofaRpcException sofaException, String methodName,
							RequestBase request) {
						// TODO Auto-generated method stub  
					}
				}).setDirectUrl(CallBackClientMain.DIRECT_URL);
		HelloExceptionService service = consumerConfig.refer();
//		for (int i = 0; i < 100; i++) {
//			String result = helloService.sayHello("xxx");
//			LOGGER.warn("接口返回结果：" + result);
//			Thread.sleep(2000);
//		}
//
//		synchronized (CallBackClientMain.class) {
//			while (true) {
//				CallBackClientMain.class.wait();
//			}
//		}
		for (int i = 0; i < TIMES; i++) {
			try {
				service.getException();
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block  
				e.printStackTrace();  
			}
		}
//		synchronized (CallBackClientMain.class) {
//			while (true) {
//				CallBackClientMain.class.wait();
//			}
//		}
	}
}
