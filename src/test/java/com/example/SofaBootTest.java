/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SofaBootTest.java  
 * Package Name:com.example 
 * Date:2019年2月22日下午4:02:29  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alipay.sofa.common.utils.StringUtil;
import com.alipay.sofa.rpc.config.ProviderConfig;
import com.alipay.sofa.rpc.config.RegistryConfig;
import com.alipay.sofa.rpc.config.ServerConfig;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.test.runner.SofaBootRunner;
import com.example.service.HelloService;
import com.example.service.SimpleJvmService;
import com.example.service.impl.HelloServiceImpl;

/**
 * ClassName:SofaBootTest Date: 2019年2月22日 下午4:02:29
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@RunWith(SofaBootRunner.class)
@SpringBootTest
public class SofaBootTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SofaBootTest.class);

	@SofaReference
	private SimpleJvmService service;

	@Test
	public void test() {
		String serviceName = service.message();
		Assert.assertTrue(StringUtil.isNotEmpty(serviceName));
	}

	@Test
	public void testEnum() {
		String type = MediaType.APPLICATION_JSON;
		LOGGER.debug(type);
	}

	@Test
	public void testCollection() {
		String str1 = "Mark";
		String str2 = "Juwen";
		@SuppressWarnings("unused")
		Map<String, String> map = CollectionUtils.toMap(str1, str2);
		LOGGER.debug("ASD");
	}

	@Test
	public void sofazoo() {
		RegistryConfig registryConfig = new RegistryConfig()
				.setProtocol("zookeeper")
				.setAddress("127.0.0.1:2181");
		ServerConfig serverConfig = new ServerConfig()
				.setProtocol("bolt")
				.setPort(12345)
				.setDaemon(false);
		ProviderConfig<HelloService> providerConfig = new ProviderConfig<HelloService>()
				.setInterfaceId(HelloService.class.getName())
				.setRef(new HelloServiceImpl())
				.setRegistry(registryConfig)
				.setServer(serverConfig);
		providerConfig.export();
	}

}
