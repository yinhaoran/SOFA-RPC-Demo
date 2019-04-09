package com.example;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alipay.sofa.rpc.api.future.SofaResponseFuture;
import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;
import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.alipay.sofa.runtime.api.annotation.SofaReferenceBinding;
import com.example.service.DemoService;
import com.example.service.PersonService;

/**
 * 
 * ClassName:SpringBootSofarpcApplicationTest 
 * Function: TODO ADD FUNCTION. 
 * Reason: TODO ADD REASON(可选).  
 * date: 2019年4月4日 下午2:23:56 
 * 
 * @author yin 
 * @version   
 * @since JDK 1.6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootSofarpcApplicationTest {

	private final static Logger LOGGER = LoggerFactory.getLogger(SpringBootSofarpcApplicationTest.class);

	@SofaReference(interfaceType = DemoService.class, binding = @SofaReferenceBinding(bindingType = "bolt"))
	private DemoService demoService;

	@Autowired
	private PersonService personReferenceBolt;

	@Autowired
	private PersonService personReferenceRest;

	@Test
	public void contextLoads() {
		String message = demoService.doSomething("殷浩然");
		System.out.println(message);
	}

	@SuppressWarnings("unused")
	@Test
	public void clientFactoryBean() {
		try {
			Future<?> future = SofaResponseFuture.getFuture(true);
			String result = (String) SofaResponseFuture.getResponse(0, true);
			LOGGER.debug(result);
		} catch (SofaRpcException | InterruptedException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	@Test
	public void testPersonService() {
		String resulta = personReferenceRest.sayName("殷浩然");
		String resultb = personReferenceBolt.sayName("殷浩然");
		LOGGER.debug(resulta);
		LOGGER.debug(resultb);
	}

	@Test
	public void testLinkedHashSet() {
		Set<String> names = new LinkedHashSet<String>();
		names.add("Jack");
		names.add("Jack");
		names.add("Jack");
		names.add("Jack");
		names.add("Jack");
		names.add("Tom");
		names.add("Jery");
		names.add("Martin");
	}

}
