/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ThreadStateDemo.java  
 * Package Name:com.example.current 
 * Date:2019年4月11日下午7:52:53  
 * Copyright (c) 2019,  
 *  
*/

package com.example.current;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ClassName:ThreadStateDemo Date: 2019年4月11日 下午7:52:53
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ThreadStateDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThreadStateDemo.class);

	/**
	 * 锁对象
	 */
	private static Object lock = new Object();

	@Test
	public void testState() throws Exception {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				double d = 0.1;
				int i = 0;
				while (i++ < 100000) { // 模仿一个耗时操作
					d = d * 0.3d;
				}

				LockUtil.sleep(2000L); // 休眠2秒钟
				synchronized (lock) {
					LockUtil.wait(lock);
				}
				synchronized (lock) { // 尝试获取lock锁

				}
			}
		}, "STATE-TEST-THREAD");
		LOGGER.debug("【初始】状态：" + thread.getState());
		thread.start();
		LOGGER.debug("【耗时操作】状态：" + thread.getState());
		LockUtil.sleep(1000L);
		LOGGER.debug("【休眠】状态：" + thread.getState());
		LockUtil.sleep(2000L);
		LOGGER.debug("【等待】状态：" + thread.getState());
		synchronized (lock) {
			lock.notifyAll();
		}
		LOGGER.debug("【被NOTIFY后】状态：" + thread.getState());
		synchronized (lock) {
			LockUtil.sleep(1000L);
			LOGGER.debug("【因为获取锁而阻塞】状态：" + thread.getState());
		}
	}
}

class LockUtil {
	public static void sleep(long mill) {
		try {
			Thread.sleep(mill);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	public static void wait(Object obj) {
		try {
			obj.wait();
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}