/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SofaBootRpcDemoApplicationTest.java  
 * Package Name:com.example 
 * Date:2019年3月25日下午5:35:17  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.example.service.PersonService;

/**
 * ClassName:SofaBootRpcDemoApplicationTest Date: 2019年3月25日 下午5:35:17
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootSofarpcApplication.class)
public class SofaBootRpcDemoApplicationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(SofaBootRpcDemoApplicationTest.class);

	@Autowired
	private PersonService personReferenceBolt;

	@Autowired
	private PersonService personReferenceRest;

	@Test
	public void test() {
		String bolt = personReferenceBolt.sayName("Bolt");
		LOGGER.debug(bolt);
		String rest = personReferenceRest.sayName("Rest");
		LOGGER.debug(rest);
	}
}
