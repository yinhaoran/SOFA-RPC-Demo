/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:CurrentTest.java  
 * Package Name:com.example 
 * Date:2019年4月4日下午4:59:28  
 * Copyright (c) 2019,  
 *  
*/

package com.example;
/**  
 * ClassName:CurrentTest   
 * Date:     2019年4月4日 下午4:59:28  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CurrentTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CurrentTest.class);

	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
		/**
		 * 隔1秒后打印
		 */
		service.schedule(new PrintTask("1"), 1, TimeUnit.SECONDS);
		/**
		 * 首次5秒后打印，每隔1秒打印一次
		 */
		service.scheduleAtFixedRate(new PrintTask("2"), 5, 1, TimeUnit.SECONDS);
	}

	@Test
	public void printCpuCount() {
		int num = Runtime.getRuntime().availableProcessors();
		LOGGER.info("计算机CPU数量为： " + num + " 个");
	}

}

class PrintTask implements Runnable {

	private String s;

	public PrintTask(String s) {
		this.s = s;
	}

	@Override
	public void run() {
		System.out.println(s);
	}
}

class BeeperControl {
	private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);

	public static void beepForAnHour() {
		final Runnable beeper = new Runnable() {
			public void run() {
				System.out.println("beep");
			}
		};
		final ScheduledFuture<?> beeperHandle = scheduler.scheduleAtFixedRate(beeper, 1, 1, TimeUnit.SECONDS);
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
			}
		}, 60 * 60, TimeUnit.SECONDS);
	}

	public static void main(String[] args) {
		beepForAnHour();
	}
}

class AddTask implements Callable<Integer> {

	private int i;

	private int j;

	public AddTask(int i, int j) {
		this.i = i;
		this.j = j;
	}

	@Override
	public Integer call() throws Exception {
		int sum = i + j;
		System.out.println("线程main的运算结果：" + sum);
		return sum;
	}

}

class AddTaskTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddTaskTest.class);

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/**
		 * 创建缓存线程池
		 */
		ExecutorService service = Executors.newCachedThreadPool();
		/**
		 * 提交
		 */
		Future<Integer> future = service.submit(new AddTask(6, 6));
		boolean flag = future.isDone();
		LOGGER.info(String.valueOf(flag));
		/**
		 * 结果
		 */
		LOGGER.info(String.valueOf(future.get()));
		service.shutdown();
	}
}

class MyThreadFactory implements ThreadFactory {

	private static int COUNTER = 0;

	private static String THREAD_PREFIX = "myThread";

	@Override
	public Thread newThread(Runnable runnable) {
		int i = COUNTER++;
		return new Thread(runnable, THREAD_PREFIX + i);
	}

}

class LongTimeTask implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(LongTimeTask.class);

	private int num;

	public LongTimeTask(int num) {
		this.num = num;
	}

	@Override
	public void run() {
		try {
			LOGGER.debug(Thread.currentThread().getName() + "线程正在执行第" + num + "个任务");
			/**
			 * 模拟耗时操作
			 */
			Thread.sleep(1000000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}

class LongTimeTaskTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(LongTimeTaskTest.class);

	public static void main(String[] args) {
		ExecutorService service = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1),
				new MyThreadFactory(),
				// new ThreadPoolExecutor.AbortPolicy()
				/**
				 * CallerRunsPolicy饱和策略的含义是谁提交的任务就由谁执行
				 */
				new ThreadPoolExecutor.CallerRunsPolicy());
		try {
			/**
			 * 该任务会被线程立即执行
			 */
			service.submit(new LongTimeTask(1));
			/**
			 * 该任务会被塞到阻塞队列中
			 */
			service.submit(new LongTimeTask(2));
			/**
			 * 该任务会根据不同的饱和策略而产生不同的反应
			 */
			service.submit(new LongTimeTask(3));
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
	}
}

/**
 * 
 * ClassName:StarvationDeadlockDemo 
 * Function: TODO 线程A 依赖 线程B
 * Reason: TODO ADD REASON(可选).  
 * date: 2019年4月9日 上午10:17:23 
 * 
 * @author yin 
 * @version   
 * @since JDK 1.6
 */
class StarvationDeadlockDemo {

	private static final Logger LOGGER = LoggerFactory.getLogger(StarvationDeadlockDemo.class);

//	private static ExecutorService service = Executors.newSingleThreadExecutor();
	private static ExecutorService service = Executors.newFixedThreadPool(2);

	private static class Taska implements Callable<String> {

		@Override
		public String call() throws Exception {
			LOGGER.info("开始执行task1");
			Future<String> future = service.submit(new Taskb());
			LOGGER.debug("Taska 执行的结果" + future.get());
			return "taska 执行完成";
		}

	}

	private static class Taskb implements Callable<String> {
		@Override
		public String call() throws Exception {
			System.out.println("开始执行task2");
			return "task2";
		}
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Future<String> future = service.submit(new Taska());
		LOGGER.debug("Taska 执行的结果" + future.get());
	}
}