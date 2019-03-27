/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:BoltMulipartClientMain.java  
 * Package Name:com.example.bolt.start 
 * Date:2019年3月27日下午1:45:09  
 * Copyright (c) 2019,  
 *  
*/

package com.example.bolt.start;
/**  
 * ClassName:BoltMulipartClientMain   
 * Date:     2019年3月27日 下午1:45:09  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.common.utils.NamedThreadFactory;
import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.alipay.sofa.rpc.context.RpcRuntimeContext;
import com.example.service.EchoService;

public class BoltMulipartClientMain {

	private final static Logger LOGGER = LoggerFactory.getLogger(BoltMulipartClientMain.class);

	public static void main(String[] args) {
		start();
	}

	public static void start() {
		// ConsumerConfig<HelloService> consumerConfig = new
		// ConsumerConfig<HelloService>()
		// .setInterfaceId(HelloService.class.getName()).setDirectUrl("bolt://127.0.0.1:22000").setTimeout(3000)
		// .setRegister(false);
		// final HelloService helloService = consumerConfig.refer();
		ConsumerConfig<EchoService> consumerConfig2 = new ConsumerConfig<EchoService>()
				.setInterfaceId(EchoService.class.getName()).setDirectUrl("bolt://127.0.0.1:22000").setTimeout(3000)
				.setRegister(false);
		final EchoService echoService = consumerConfig2.refer();
		LOGGER.info("START AT PID{}", RpcRuntimeContext.PID);
		final int threads = 1;
		final AtomicLong cnt = new AtomicLong(0);
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS,
				new SynchronousQueue<Runnable>(), new NamedThreadFactory("client"));
		for (int i = 0; i < threads; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						try {
							echoService.echoStr("12234565465456565");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							cnt.incrementAndGet();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			});
		}
		Thread thread = new Thread(new Runnable() {
			private long last = 0;
			@Override
			public void run() {
				long count = cnt.get();
				long tps = count - last;
				LOGGER.info("last 1s invoke: {}, queue: {}", tps, executor.getQueue().size());
				last = count;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
	}
}
