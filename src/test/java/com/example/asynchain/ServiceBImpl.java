/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ServiceBImpl.java  
 * Package Name:com.example.asynchain 
 * Date:2019年3月26日下午4:30:29  
 * Copyright (c) 2019,  
 *  
*/

package com.example.asynchain;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.context.RpcInvokeContext;
import com.alipay.sofa.rpc.core.request.RequestBase;
import com.alipay.sofa.rpc.message.bolt.BoltSendableResponseCallback;

/**
 * ClassName:ServiceBImpl Date: 2019年3月26日 下午4:30:29
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ServiceBImpl implements ServiceB {

	private final static Logger LOGGER = LoggerFactory.getLogger(ServiceBImpl.class);

	private Random random = new Random();

	private ServiceC serviceC;

	public ServiceBImpl(ServiceC serviceC) {
		this.serviceC = serviceC;
	}

	@Override
	public int getInt(int num) {
		RpcInvokeContext.getContext().setResponseCallback(new BoltSendableResponseCallback<Object>() {
			@Override
			public void onAppResponse(Object appResponse, String methodName, RequestBase request) {
				/**
				 * C 异步返回B
				 */
				LOGGER.info("B 从 C处获取响应：" + appResponse);
				int respToA = random.nextInt(1000);
				/**
				 * A 异步调用，则拿到这个AppResponse响应值。 B->异步返回给A
				 */
				sendAppResponse(respToA);
			}
		});
		String s = serviceC.getStr("xx");
		LOGGER.debug(s);
		return -1;
	}

}
