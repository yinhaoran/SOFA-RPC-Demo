/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:NettyTest.java  
 * Package Name:com.example 
 * Date:2019年2月27日下午5:02:16  
 * Copyright (c) 2019,  
 *  
*/  
  
package com.example;

import org.junit.Test;

import com.example.netty.server.NettyTelnetServer;

/**  
 * ClassName:NettyTest   
 * Date:     2019年2月27日 下午5:02:16  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */
public class NettyTest {
	@Test
    public void test() {
        NettyTelnetServer nettyTelnetServer = new NettyTelnetServer();
        try {
            nettyTelnetServer.open();
        } catch (InterruptedException e) {
            nettyTelnetServer.close();
        }	
    }
}
  
