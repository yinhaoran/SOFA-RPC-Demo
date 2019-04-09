/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ProtocolType.java  
 * Package Name:com.example 
 * Date:2019年4月1日下午5:12:44  
 * Copyright (c) 2019,  
 *  
*/

package com.example.rpc.protocol;

/**
 * ClassName:ProtocolType Date: 2019年4月1日 下午5:12:44
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public enum ProtocolType {
	/**
	 * Rest类型
	 */
	rest("rest"),
	/**
	 * bolt类型
	 */
	bolt("bolt"),
	/**
	 * dubbo类型
	 */
	dubbo("dubbo"),
	/**
	 * h2c类型
	 */
	h2c("h2c");

	private String type;

	private ProtocolType(String type) {
		this.type = type;
	}
}
