/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:DemoServer.java  
 * Package Name:com.example.thriftdemo.hello 
 * Date:2019年3月22日上午10:56:53  
 * Copyright (c) 2019,  
 *  
*/

package com.example.thriftdemo.hello;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thrift.test.DemoService;

/**
 * ClassName:DemoServer Date: 2019年3月22日 上午10:56:53
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class DemoServer {

	private static final Logger LOGGER = LoggerFactory.getLogger(DemoServer.class);

	private static final int PORT = 9000;

	public static void main(String[] args) throws TTransportException {
		/**
		 * 传输层
		 */
		TServerSocket serverTransport = new TServerSocket(PORT);
		/**
		 * 协议层
		 */
		TBinaryProtocol.Factory binaryProtocolFactory = new TBinaryProtocol.Factory(true, true);

		DemoService.Processor<DemoServiceImpl> processor = new DemoService.Processor<DemoServiceImpl>(
				new DemoServiceImpl());
		/**
		 * 服务层
		 */
		TServer server = new TThreadPoolServer(new TThreadPoolServer.Args(serverTransport)
				.protocolFactory(binaryProtocolFactory).processor(processor));
		LOGGER.debug("服务启动");
		server.serve();
	}

}
