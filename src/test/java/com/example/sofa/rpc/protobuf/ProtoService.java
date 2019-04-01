/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ProtoService.java  
 * Package Name:com.example.sofa.rpc.protobuf 
 * Date:2019年4月1日下午4:37:14  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.protobuf;

/**
 * ClassName:ProtoService Date: 2019年4月1日 下午4:37:14
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public interface ProtoService {
	EchoResponse echoObj(EchoRequest echoRequest);
}
