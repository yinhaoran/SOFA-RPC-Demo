/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SimpleRestService.java  
 * Package Name:com.example.service 
 * Date:2019年2月21日下午2:29:24  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * ClassName:SimpleRestService Date: 2019年2月21日 下午2:29:24
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@Path("/sample")
public interface SimpleRestService {
	@GET
	@Path("/hello")
	String sayHello();
}
