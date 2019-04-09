/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ResolverConfig.java  
 * Package Name:com.example.config 
 * Date:2019年3月10日上午10:01:31  
 * Copyright (c) 2019,  
 *  
*/

package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * ClassName:ResolverConfig Date: 2019年3月10日 上午10:01:31
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@Configuration
public class ResolverConfig {
	/**
	 * multipartResolver:(显示声明CommonsMultipartResolver为mutipartResolver). <br/>
	 *
	 * @return
	 * @since JDK 1.8
	 */
	@Bean(name = "multipartResolver")
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		resolver.setDefaultEncoding("UTF-8");
		/**
		 * resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
		 */
		resolver.setResolveLazily(true);
		resolver.setMaxInMemorySize(40960);
		/**
		 * 上传文件大小 50M 50*1024*1024
		 */
		resolver.setMaxUploadSize(50 * 1024 * 1024);
		return resolver;
	}
}
