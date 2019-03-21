/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ThriftServer.java  
 * Package Name:com.example.thriftdemo 
 * Date:2019年3月19日下午1:46:31  
 * Copyright (c) 2019,  
 *  
*/

package com.example.thriftdemo;

import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import thrift.generated.PersonService;

/**
 * ClassName:ThriftServer Date: 2019年3月19日 下午1:46:31
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ThriftServer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ThriftServer.class);
	
	public static void main(String[] args) {
		try {
			TNonblockingServerSocket socket = new TNonblockingServerSocket(8899);
			THsHaServer.Args arg = new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
			/**
			 * 泛型是实现的接收类
			 */
			PersonService.Processor<PersonServiceImpl> processor = new PersonService.Processor<PersonServiceImpl>(
					new PersonServiceImpl());
			/**
			 * 表示协议层次
			 */
			arg.protocolFactory(new TCompactProtocol.Factory());
			/**
			 * 表示传输层次
			 */
			arg.transportFactory(new TFramedTransport.Factory());
			arg.processorFactory(new TProcessorFactory(processor));
			TServer server = new THsHaServer(arg);
			server.serve();
		}catch(Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
