/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SofaUtilsTest.java  
 * Package Name:com.example 
 * Date:2019年2月24日上午11:43:39  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

import com.alipay.sofa.rpc.common.json.JSON;
import com.alipay.sofa.rpc.common.struct.MapDifference;
import com.alipay.sofa.rpc.common.utils.CommonUtils;
import com.alipay.sofa.rpc.common.utils.StringUtils;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

/**
 * ClassName:SofaUtilsTest Date: 2019年2月24日 上午11:43:39
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class SofaUtilsTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SofaUtilsTest.class);

	@Test
	public void testCommmonUtis() {
		List<String> lista = new LinkedList<String>();
		List<String> listb = new LinkedList<String>();
		lista.add("a");
		lista.add("b");
		lista.add("c");
		listb.add("c");
		listb.add("b");
		listb.add("a");
		CommonUtils.listEquals(lista, listb);
	}

	@Test
	public void testMapUtil() {
		ConcurrentMap<String, String> currentMap = new ConcurrentHashMap<String, String>();
		CommonUtils.putToConcurrentMap(currentMap, "a", "b");
		CommonUtils.putToConcurrentMap(currentMap, "g", "b");
		CommonUtils.putToConcurrentMap(currentMap, "n", "b");
		CommonUtils.putToConcurrentMap(currentMap, "m", "b");
		String m = JSON.toJSONString(currentMap);
		System.out.println(m);
	}

	@Test
	public void testJoinList() {
		String[] array = new String[] { "a", "b", "c", "d" };
		List<String> list = Arrays.asList(array);

		// List mockedList = mock(List.class);
		String str = CommonUtils.join(list, ",");
		System.out.println(str);
		String m = JSON.toJSONString(str);
		System.out.println(m);
	}

	@Test
	public void testBlockingQueue() {
		Queue<String> queue = new ArrayBlockingQueue<String>(16);
		queue.add("a");
		queue.add("b");
		queue.add("c");
		queue.add("d");
		for (Iterator<String> iterator = queue.iterator(); iterator.hasNext();) {
			String item = queue.poll();
			if (StringUtils.isNotEmpty(item)) {
				System.out.println(item);
			}
		}
	}

	@Test
	public void testStringUtils() {
		String s = " 1,2 ,, 3 , ";
		StringUtils.split(s, ",");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testTask() {
		int taskSize = 5;
		ExecutorService pool = Executors.newFixedThreadPool(taskSize);
		List<Future> list = new ArrayList<Future>();
		for (int i = 0; i < taskSize; i++) {
			Callable call = new MyCallable(i);
			Future future = pool.submit(call);
			list.add(future);
		}
		pool.shutdown();
	}

	class MyCallable implements Callable<Object> {

		private int taskNum;

		public MyCallable(int taskNum) {
			super();
			this.taskNum = taskNum;
		}

		@Override
		public Object call() throws Exception {
			LOGGER.debug("序号为【" + this.taskNum + "】的任务启动！");
			Date dateTmp1 = new Date();
			Thread.sleep(100);
			Date dateTmp2 = new Date();
			long time = dateTmp2.getTime() - dateTmp1.getTime();
			System.out.println("序号为【\" + this.taskNum + \"】的任务启动！");
			return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";
		}
	}

	@SuppressWarnings("unused")
	@Test
	public void testMapDifference() {
		Map<String, String> mapone = new HashMap<String, String>();
		mapone.put("a", "b");
		mapone.put("b", "b");
		mapone.put("c", "b");
		mapone.put("d", "b");
		Map<String, String> maptwo = new HashMap<String, String>();
		maptwo.put("a", "y");
		maptwo.put("b", "b");
		maptwo.put("c", "b");
		maptwo.put("d", "b");
		maptwo.put("e", "b");
		MapDifference<String, String> difference = new MapDifference<String, String>(mapone, maptwo);
		Map<String, String> left = difference.entriesOnlyOnLeft();
		Map<String, String> right = difference.entriesOnlyOnRight();
		LOGGER.debug(left.toString());
	}
	
	@Test
	public void testSubString() {
		String str = "hashdhajsdjajsdja";
		str = str.substring(4, str.length());
		LOGGER.debug(str);
	}
	
}
