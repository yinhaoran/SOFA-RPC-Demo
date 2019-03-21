/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:PersonServiceImpl.java  
 * Package Name:com.example.thriftdemo 
 * Date:2019年3月19日上午11:56:26  
 * Copyright (c) 2019,  
 *  
*/

package com.example.thriftdemo;

import org.apache.thrift.TException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

/**
 * ClassName:PersonServiceImpl Date: 2019年3月19日 上午11:56:26
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class PersonServiceImpl implements PersonService.Iface {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonServiceImpl.class);

	@Override
	public Person getPersonByUsername(String username) throws DataException, TException {
		LOGGER.debug("GOT CLIENT PARAM : " + username);
		Person person = new Person();
		person.setAge(25);
		person.setMarried(false);
		person.setUsername(username);
		return person;
	}

	@Override
	public void savePerson(Person person) throws DataException, TException {
		LOGGER.debug("GOT CLIENT PARAM : ");
		LOGGER.debug(person.getUsername());
	}

}
