/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ProtobufServiceServerMain.java  
 * Package Name:com.example.sofa.rpc.protobuf 
 * Date:2019年4月1日下午4:48:25  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.protobuf;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.alipay.sofa.rpc.server.bolt.BoltServer;

/**
 * ClassName:ProtobufServiceServerMain Date: 2019年4月1日 下午4:48:25
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ProtobufServiceServerMain {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProtobufServiceServerMain.class);

	public static void main(String[] args) {
		/**
		 * -------------------------------初始化服务提供者-----------------------
		 */
		ServerConfig serverConfig = new ServerConfig().setProtocol("bolt").setPort(12200).setDaemon(false);
		ProviderConfig<ProtoService> providerConfig = new ProviderConfig<ProtoService>()
				.setInterfaceId(ProtoService.class.getName()).setRef(new ProtoServiceImpl()).setServer(serverConfig);
		providerConfig.export();
		/**
		 * -------------------------------初始化服务提供者-----------------------
		 */
		LOGGER.error("START AT PID {}", RpcRuntimeContext.PID);

		/**
		 * 计数器和线程池：
		 */
		final AtomicInteger cnt = ((ProtoServiceImpl) providerConfig.getRef()).getCounter();
		final ThreadPoolExecutor executor = ((BoltServer) serverConfig.getServer()).getBizThreadPool();
		Thread thread = new Thread(new Runnable() {

			private long last = 0;

			@Override
			public void run() {
				while (true) {
					long count = cnt.get();
					long tps = count - last;
					LOGGER.error("last 1s invoke: {}, queue: {}", tps, executor.getQueue().size());
					last = count;
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
		}, "Print-tps-THREAD");
		thread.start();
	}
}
