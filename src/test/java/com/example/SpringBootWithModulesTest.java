/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SpringBootWithModulesTest.java  
 * Package Name:com.example 
 * Date:2019年2月22日上午11:42:23  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.example.service.SimpleJvmService;

/**
 * ClassName:SpringBootWithModulesTest Date: 2019年2月22日 上午11:42:23
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootWithModulesTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootWithModulesTest.class);

	@SofaReference
	private SimpleJvmService simpleJvmService;

	@SofaReference(uniqueId = "annotationJvmServiceImpl")
	private SimpleJvmService annotationJvmServiceImpl;

	@SofaReference(uniqueId = "serviceClientImpl")
	private SimpleJvmService simpleServiceClientImpl;

	@Test
	public void serviceTest() {
		String messagea = simpleJvmService.message();
		LOGGER.debug(messagea);
		String messageb = annotationJvmServiceImpl.message();
		LOGGER.debug(messageb);
		String messagec = simpleServiceClientImpl.message();
		LOGGER.debug(messagec);
	}

}
