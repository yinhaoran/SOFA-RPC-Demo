/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ProtoCommonConfig.java  
 * Package Name:com.example.config 
 * Date:2019年4月1日下午2:14:12  
 * Copyright (c) 2019,  
 *  
*/

package com.example.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * ClassName:ProtoCommonConfig Date: 2019年4月1日 下午2:14:12
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@Configuration
public class ProtoCommonConfig {
	@Bean
	ProtobufHttpMessageConverter httpMessageConverter() {
		return new ProtobufHttpMessageConverter();
	}

	RestTemplate restTemplate(ProtobufHttpMessageConverter httpMessageConverter) {
		return new RestTemplate(Collections.singletonList(httpMessageConverter));
	}
}
