/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ThriftClient.java  
 * Package Name:com.example.thriftdemo 
 * Date:2019年3月19日下午2:03:12  
 * Copyright (c) 2019,  
 *  
*/

package com.example.thriftdemo;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * ClassName:ThriftClient Date: 2019年3月19日 下午2:03:12
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 * 
 * 		客户端协议与服务器端协议一致
 */
public class ThriftClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(ThriftClient.class);

	private static final int PORT = 8899;

	public static void main(String[] args) {
		TTransport transport = new TFastFramedTransport(new TSocket("localhost", PORT), 600);
		try {
			TProtocol protocol = new TCompactProtocol(transport);
			PersonService.Client client = new PersonService.Client(protocol);
			transport.open();
			Person person = client.getPersonByUsername("张三");
			LOGGER.debug(person.getUsername());
			LOGGER.debug(String.valueOf(person.getAge()));
			LOGGER.debug(String.valueOf(person.isMarried()));
			client.savePerson(person);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			transport.close();
		}
	}
}
