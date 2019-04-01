/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ProtoServiceImpl.java  
 * Package Name:com.example.sofa.rpc.protobuf 
 * Date:2019年4月1日下午4:37:47  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.protobuf;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ClassName:ProtoServiceImpl Date: 2019年4月1日 下午4:37:47
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ProtoServiceImpl implements ProtoService {

	private Integer sleep = 10;

	private String result;

	private AtomicInteger counter = new AtomicInteger();

	public ProtoServiceImpl(String result) {
		super();
		this.result = result;
	}

	public ProtoServiceImpl(Integer sleep) {
		super();
		this.sleep = sleep;
	}

	public ProtoServiceImpl() {

		super();
		// TODO Auto-generated constructor stub

	}

	@Override
	public EchoResponse echoObj(EchoRequest echoRequest) {
		if (sleep > 0) {
			try {
				Thread.sleep(sleep);
			} catch (Exception ignore) { // NOPMD
			}
		}
		counter.incrementAndGet();
		EchoResponse response = EchoResponse.newBuilder()
				.setCode(200)
				.setMessage(result != null ? result : "protobuf works" + echoRequest.getName())
				.build();
		return response;
	}
	
	public AtomicInteger getCounter() {
		return this.counter;
	}

}
