/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:RestClientMultipleMain.java  
 * Package Name:com.example.sofa.rpc.rest 
 * Date:2019年4月2日上午10:48:30  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.rest;
/**  
 * ClassName:RestClientMultipleMain   
 * Date:     2019年4月2日 上午10:48:30  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.common.struct.NamedThreadFactory;
import com.alipay.sofa.rpc.config.ApplicationConfig;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.example.rpc.protocol.ProtocolType;

public class RestClientMultipleMain {
	private static final Logger LOGGER = LoggerFactory.getLogger(RestClientMultipleMain.class);

	public static void main(String[] args) {
		ApplicationConfig applicationConfig = new ApplicationConfig().setAppName("test-client");

		ConsumerConfig<RestService> consumerConfig = new ConsumerConfig<RestService>().setApplication(applicationConfig)
				.setInterfaceId(RestService.class.getName()).setProtocol(ProtocolType.rest.name())
				.setBootstrap(ProtocolType.rest.name()).setDirectUrl("rest://127.0.0.1:8888").setTimeout(3000);
		final RestService restService = consumerConfig.refer();
		LOGGER.info("START AT PID {}", RpcRuntimeContext.PID);
		String s = restService.get("1234567890");
		LOGGER.info("RESPONSE IS {}", s);

//		final int threads = 5;
//		final ThreadPoolExecutor service1 = new ThreadPoolExecutor(threads, threads, 0L, TimeUnit.MILLISECONDS,
//				new SynchronousQueue<Runnable>(), new NamedThreadFactory("client-"));// 无队列
//		for (int i = 0; i < threads; i++) {
//			service1.execute(new Runnable() {
//				@Override
//				public void run() {
//					while (true) {
//						try {
//							String s = restService.get("1234567890");
//							LOGGER.info("RESPONSE IS {}", s);
//							try {
//								Thread.sleep(1000);
//							} catch (InterruptedException e) {
//							}
//						} catch (Exception e) {
//							LOGGER.error("", e);
//						}
//					}
//				}
//			});
//			Thread thread = new Thread(new Runnable() {
//				public void run() {
//					while (true) {
//						try {
//							Thread.sleep(1000);
//						} catch (InterruptedException e) {
//						}
//					}
//				}
//			}, "Print-tps-THREAD");
//			thread.start();
//		}
	}
}
