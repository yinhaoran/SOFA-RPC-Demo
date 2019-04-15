package com.example.current;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**  
 * ClassName:WaitNotifyTest 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选).  
 * date: 2019年4月11日 下午7:51:49 
 * 
 * @author yin 
 * @version   
 * @since JDK 1.6  
 */
public class WaitNotifyTest {
	@Test
	public void testNotify() throws Exception {
		
	}
	
	public static void main(String[] args) {
		WashRoom washroom = new WashRoom();

		new Thread(new ShitTask(washroom, "狗哥"), "BROTHER-DOG-THREAD").start();
		new Thread(new ShitTask(washroom, "猫爷"), "GRANDPA-CAT-THREAD").start();
		new Thread(new ShitTask(washroom, "王尼妹"), "WANG-NI-MEI-THREAD").start();
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		new Thread(new RepairTask(washroom), "REPAIR-THREAD").start();

	}
}

class WashRoom {
	// private static Class
	private volatile boolean isAvailable = false;

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	private Object lock = new Object();

	public Object getLock() {
		return lock;
	}
}

class RepairTask implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(RepairTask.class);

	private WashRoom washRoom;

	public RepairTask(WashRoom washRoom) {
		super();
		this.washRoom = washRoom;
	}

	@Override
	public void run() {
		synchronized (washRoom.getLock()) {
			LOGGER.debug("维修工 获取了厕所的锁");
			LOGGER.debug("厕所维修中，维修厕所是一件辛苦活，需要很长时间。。。");
			try {
				Thread.sleep(5000L); 
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			washRoom.setAvailable(true); 
			/**
			 * NOTIFY 优化通知与锁对象关联的在阻塞队列中的线程，可以继续执行了
			 */
			washRoom.getLock().notifyAll();
			System.out.println("维修工把厕所修好了，准备释放锁了");
		}
	}

}

class ShitTask implements Runnable {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShitTask.class);
	
	private WashRoom washroom;

	private String name;

	public ShitTask(WashRoom washroom, String name) {
		this.washroom = washroom;
		this.name = name;
	}

	@Override
	public void run() {

		synchronized (washroom.getLock()) {
			LOGGER.debug(name + " 获取了厕所的锁");
			while (!washroom.isAvailable()) {
				try {
					washroom.getLock().wait();
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			System.out.println(name + " 上完了厕所");
		}
	}
}
