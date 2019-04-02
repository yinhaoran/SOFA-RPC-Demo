/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ExampleObj.java  
 * Package Name:com.example.sofa.rpc.rest 
 * Date:2019年4月1日下午6:15:59  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.rest;

import java.io.Serializable;

/**
 * ClassName:ExampleObj Date: 2019年4月1日 下午6:15:59
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ExampleObj implements Serializable {

	/**
	 * serialVersionUID:TODO(用一句话描述这个变量表示什么).
	 * 
	 * @since JDK 1.8
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
