/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ExampleObj.java  
 * Package Name:com.example.http.rest 
 * Date:2019年3月27日下午10:34:38  
 * Copyright (c) 2019,  
 *  
*/

package com.example.http.rest;

import java.io.Serializable;

/**
 * ClassName:ExampleObj Date: 2019年3月27日 下午10:34:38
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
	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
