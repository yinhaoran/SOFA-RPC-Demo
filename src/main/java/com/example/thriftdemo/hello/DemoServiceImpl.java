/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:DemoServiceImpl.java  
 * Package Name:com.example.thriftdemo.hello 
 * Date:2019年3月22日上午10:53:54  
 * Copyright (c) 2019,  
 *  
*/

package com.example.thriftdemo.hello;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thrift.test.DemoService.Iface;

/**
 * ClassName:DemoServiceImpl Date: 2019年3月22日 上午10:53:54
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class DemoServiceImpl implements Iface {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoServiceImpl.class);

	@Override
	public String sayHi(String name) throws TException {
		LOGGER.debug("进入service方法！");
		// TODO Auto-generated method stub
		return "Hello," + name;
	}

}
