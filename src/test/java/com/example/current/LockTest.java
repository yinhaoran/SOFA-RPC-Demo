/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:LockTest.java  
 * Package Name:com.example.current 
 * Date:2019年4月12日下午4:38:33  
 * Copyright (c) 2019,  
 *  
*/

package com.example.current;
/**  
 * ClassName:LockTest   
 * Date:     2019年4月12日 下午4:38:33  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LockTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LockTest.class);

	// private static final Random RANDOMGENTER = new Random();

	// ReentranLock

	@Test
	public void testName() throws Exception {
		Random randomGenter = new Random();
		Lock lock = new ReentrantLock();
		while (true) {
			boolean result = lock.tryLock();
			LOGGER.info(Thread.currentThread().getName() + "加锁");
			if (result) {
				try {
					LOGGER.info("开始执行！");
				} finally {
					lock.unlock();
					LOGGER.info(Thread.currentThread().getName() + "释放锁");
				}
			}
			Thread.sleep(randomGenter.nextInt(1000));
		}

	}
}
