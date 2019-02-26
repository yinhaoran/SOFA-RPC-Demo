package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;

import com.example.service.PersonService;
import com.example.service.impl.HelloSynService;

@ImportResource({ "classpath*:rest-server-example.xml" })
@SpringBootApplication
public class SpringBootSofarpcApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringBootSofarpcApplication.class);

	public static void main(String[] args) {
		SpringApplication springApplication = new SpringApplication(SpringBootSofarpcApplication.class);
		ApplicationContext applicationContext = springApplication.run(args);
		/**
		 * 获取服务实例
		 */
		HelloSynService helloSynServiceReference = (HelloSynService) applicationContext
				.getBean("helloSyncServiceReference");
		String message = helloSynServiceReference.saySync("殷浩然");
		LOGGER.debug(message);
		PersonService personService = (PersonService) applicationContext.getBean("personReferenceBolt");
		LOGGER.debug(personService.sayName("Jackson"));
	}

}
