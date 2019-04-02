/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:RestService.java  
 * Package Name:com.example.sofa.rpc.rest 
 * Date:2019年4月1日下午6:17:11  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.rest;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * ClassName:RestService Date: 2019年4月1日 下午6:17:11
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@Resource
@Path(value = "rest")
@Consumes
public interface RestService {
	@POST
	@Path(value = "/hello/{code}/{name}")
	String add(@PathParam("code") String code, @PathParam("name") String name);

	@POST
	@Path(value = "/post/{code}")
	@Produces(MediaType.TEXT_PLAIN)
	public String post(@PathParam("code") String code, String body);

	@GET
	@Path(value = "/get/{code}")
	@Produces(MediaType.TEXT_PLAIN)
	public String get(@PathParam("code") String code);

}
