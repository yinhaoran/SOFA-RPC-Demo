/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ProtoTest.java  
 * Package Name:com.boomsecret.protobuf 
 * Date:2019年4月1日下午2:19:06  
 * Copyright (c) 2019,  
 *  
*/

package com.boomsecret.protobuf;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boomsecret.protobuf.MessageUserLogin.MessageUserLoginResponse;
import com.google.protobuf.InvalidProtocolBufferException;

/**
 * ClassName:ProtoTest Date: 2019年4月1日 下午2:19:06
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ProtoTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(ProtoTest.class);

	@Test
	public void test() {
		MessageUserLoginResponse.Builder builder = MessageUserLoginResponse.newBuilder();
		builder.setAccessToken(UUID.randomUUID().toString());
		builder.setUsername("yhr");
		MessageUserLoginResponse a = builder.build();
		String msg = a.toString();
		LOGGER.info(msg);
	}

	@Test
	public void testSeriable() throws InvalidProtocolBufferException {
		MessageUserLogin.MessageUserLoginRequest.Builder builder = MessageUserLogin.MessageUserLoginRequest
				.newBuilder();
		builder.setUsername("yhr");
		builder.setPassword("hello");
		MessageUserLogin.MessageUserLoginRequest serializaton = builder.build();
		MessageUserLogin.MessageUserLoginRequest deserializaton = MessageUserLogin.MessageUserLoginRequest
				.parseFrom(serializaton.toByteArray());
		LOGGER.info(deserializaton.getPassword());
		LOGGER.info(deserializaton.getUsername());
	}
	
}
