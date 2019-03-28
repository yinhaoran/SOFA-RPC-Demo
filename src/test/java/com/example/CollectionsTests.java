/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:CollectionsTests.java  
 * Package Name:com.example 
 * Date:2019年3月28日下午5:34:53  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.Test;

/**
 * ClassName:CollectionsTests Date: 2019年3月28日 下午5:34:53
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class CollectionsTests {
	private static final int THREAD_POOL_MAX_NUM = 1000;
//	private List<String> mList = new ArrayList<String>();
	private List<String> mList = new CopyOnWriteArrayList<String>();

//	public static void main(String args[]) {
//		new ListConcurrentTest().start();
//	}

	private void initData() {
		for (int i = 0; i <= THREAD_POOL_MAX_NUM; i++) {
			this.mList.add("...... Line " + (i + 1) + " ......");
		}
	}

	@Test
	public void start() {
		initData();
		ExecutorService service = Executors.newFixedThreadPool(THREAD_POOL_MAX_NUM);
		for (int i = 0; i < THREAD_POOL_MAX_NUM; i++) {
			service.execute(new ListReader(this.mList));
			service.execute(new ListWriter(this.mList, i));
		}
		service.shutdown();
	}

	private class ListReader implements Runnable {
		private List<String> mList;

		public ListReader(List<String> list) {
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

	private class ListWriter implements Runnable {
		private List<String> mList;
		private int mIndex;

		public ListWriter(List<String> list, int index) {
			this.mList = list;
			this.mIndex = index;
		}

		@Override
		public void run() {
			if (this.mList != null) {
				// this.mList.remove(this.mIndex);
				this.mList.add("...... add " + mIndex + " ......");
			}
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();  
//			}
		}
	}
	// ---------------------
	// 作者：hua631150873
	// 来源：CSDN
	// 原文：https://blog.csdn.net/hua631150873/article/details/51306021
	// 版权声明：本文为博主原创文章，转载请附上博文链接！
}
