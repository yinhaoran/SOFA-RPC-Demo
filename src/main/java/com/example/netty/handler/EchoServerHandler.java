/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:EchoServerHandler.java  
 * Package Name:com.example.netty.handler 
 * Date:2019年3月2日下午5:26:11  
 * Copyright (c) 2019,  
 *  
*/

package com.example.netty.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ClassName:EchoServerHandler Date: 2019年3月2日 下午5:26:11
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ctx.write(msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		// Close the connection when an exception is raised.
		cause.printStackTrace();
		ctx.close();
	}
}
