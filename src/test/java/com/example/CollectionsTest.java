/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:CollectionsTest.java  
 * Package Name:com.example 
 * Date:2019年3月28日下午5:34:53  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.rpc.protocol.ProtocolType;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * ClassName:CollectionsTest Date: 2019年3月28日 下午5:34:53
 * 
 * CopyOnWriteArrayList 线程安全测试
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class CollectionsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CollectionsTest.class);

	private static final int THREAD_POOL_MAX_NUM = 10;

	private static final int TIME_OUT = 6;

	/**
	 * List容器
	 */
	private List<String> mList = new CopyOnWriteArrayList<String>();

	private void initData() {
		for (int i = 0; i <= THREAD_POOL_MAX_NUM; i++) {
			this.mList.add("...... Line " + (i + 1) + " ......");
		}
	}

	@Test
	public void start() throws InterruptedException {
		initData();
		/**
		 * 线程命名工厂
		 */
		ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("COLLECTION-TEST-POOL-%d").build();
		/**
		 * 有界队列
		 */
		BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);
		/**
		 * 放弃拒绝的任务并抛出异常
		 */
		// RejectedExecutionHandler abortPolicyHandler = new
		// ThreadPoolExecutor.AbortPolicy();
		RejectedExecutionHandler discardPolicyHandler = new ThreadPoolExecutor.DiscardPolicy();
		ThreadPoolExecutor threadPool = new ThreadPoolExecutor(5, 
				THREAD_POOL_MAX_NUM, 
				30, 
				TimeUnit.SECONDS, 
				workQueue,
				namedThreadFactory, 
				discardPolicyHandler);
		for (int i = 0; i < THREAD_POOL_MAX_NUM; i++) {
			threadPool.execute(new ListReaderTask(mList));
			threadPool.execute(new ListWriterTask(mList, i));
			LOGGER.debug("核心线程数" + threadPool.getCorePoolSize());
			LOGGER.debug("最大线程数" + threadPool.getMaximumPoolSize());
			LOGGER.debug("线程池数" + threadPool.getPoolSize());
			LOGGER.debug("队列任务数" + threadPool.getQueue().size());
			LOGGER.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		}
		threadPool.shutdown();
		if (threadPool.awaitTermination(TIME_OUT, TimeUnit.SECONDS)) {
			threadPool.shutdownNow();
		}
		// ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_MAX_NUM);
		// for (int i = 0; i < THREAD_POOL_MAX_NUM; i++) {
		// service.execute(new ListReader(this.mList));
		// service.execute(new ListWriter(this.mList, i));
		// }
		// service.shutdown();
	}

	private class ListReaderTask implements Runnable {
		private List<String> mList;

		public ListReaderTask(List<String> list) {
			this.mList = list;
		}

		@Override
		public void run() {
			if (this.mList != null) {
				for (String str : this.mList) {
					System.out.println(Thread.currentThread().getName() + " : " + str);
				}
			}
		}
	}

	private class ListWriterTask implements Runnable {
		private List<String> mList;
		private int mIndex;

		public ListWriterTask(List<String> list, int index) {
			this.mList = list;
			this.mIndex = index;
		}

		@Override
		public void run() {
			if (this.mList != null) {
				// this.mList.remove(this.mIndex);
				this.mList.add("...... add " + mIndex + " ......");
			}
			// try {
			// Thread.sleep(100);
			// } catch (InterruptedException e) {
			// e.printStackTrace();
			// }
		}
	}

	@Test
	public void testEnum() {
		String bolt = ProtocolType.bolt.name();
		LOGGER.info(bolt);
	}

	@Test
	public void testArrayToList() {
		List<String> list = null;
		String[] str = new String[] { "you", "wu" };
		list = Arrays.asList(str);
		list.add("sss");
		LOGGER.debug("集合size = " + list.size());
	}

	@Test
	public void testListDelete() {
		List<String> list = new ArrayList<String>();
		list.add("1");
		list.add("2");
		for (String item : list) {
			if ("2".equals(item)) {
				list.remove(item);
			}
		}
	}

}
