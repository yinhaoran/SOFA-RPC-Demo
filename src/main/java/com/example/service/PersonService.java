/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:PersonService.java  
 * Package Name:com.example.service 
 * Date:2019年2月21日上午11:15:32  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * ClassName:PersonService Date: 2019年2月21日 上午11:15:32
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@Path("/webapi/rest/person")
@Consumes("application/json;charset=UTF-8")
@Produces("application/json;charset=UTF-8")
public interface PersonService {
	@GET
	@Path("/sayName/{string}")
	String sayName(@PathParam("string") String name);
}
