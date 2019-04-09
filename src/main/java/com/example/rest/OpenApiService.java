/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:OpenApiService.java  
 * Package Name:com.example.rest 
 * Date:2019年2月22日下午4:28:18  
 * Copyright (c) 2019,  
 *  
*/

package com.example.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * ClassName:OpenApiService Date: 2019年2月22日 下午4:28:18
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@Path("swagger")
public interface OpenApiService {
	/**
	 * 
	 * openApi:(测试swagger). <br/>  
	 *
	 * @return  
	 * @since JDK 1.8
	 */
	@GET
	@Path("openapi")
	@Produces(MediaType.APPLICATION_JSON)
	String openApi();
}
