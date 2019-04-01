/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ProtobufServiceClientMain.java  
 * Package Name:com.example.sofa.rpc.protobuf 
 * Date:2019年4月1日下午5:05:08  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.protobuf;
/**  
 * ClassName:ProtobufServiceClientMain   
 * Date:     2019年4月1日 下午5:05:08  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.config.ConsumerConfig;
import com.example.rpc.protocol.ProtocolType;

public class ProtobufServiceClientMain {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProtobufServiceClientMain.class);

	public static void main(String[] args) {
		/**
		 * -----------------------------初始化服务消费者设置-------------------------------
		 */
		ConsumerConfig<ProtoService> consumerConfig = new ConsumerConfig<ProtoService>()
		.setInterfaceId(ProtoService.class.getName())
		.setProtocol(ProtocolType.bolt.name())
		.setDirectUrl("bolt://127.0.0.1:12200")
		.setSerialization("protobuf")
		.setConnectTimeout(10 * 1000);
		ProtoService protoService = consumerConfig.refer();
		while(true) {
			try {
				EchoRequest request = EchoRequest.newBuilder()
						.setName("Yin")
						.setGroup(Group.A)
						.build();
				EchoResponse response = protoService.echoObj(request);
				LOGGER.info(response.getCode() + ": " + response.getMessage());
			} catch (Exception e) {
				LOGGER.error(e.getMessage());
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}
}
