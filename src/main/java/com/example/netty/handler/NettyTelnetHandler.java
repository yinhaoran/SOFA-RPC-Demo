/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:NettyTelnetHandler.java  
 * Package Name:com.example.netty.handler 
 * Date:2019年2月27日下午4:25:01  
 * Copyright (c) 2019,  
 *  
*/

package com.example.netty.handler;

import com.alibaba.dubbo.common.utils.StringUtils;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * ClassName:NettyTelnetHandler Date: 2019年2月27日 下午4:25:01
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class NettyTelnetHandler extends SimpleChannelInboundHandler<String> {

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		String echo;
		boolean close = false;
		if (StringUtils.isEmpty(msg)) {
			echo = "MESSAGE IS EMPTY!";
			close = true;
		} else if ("bye".equals(msg.toLowerCase())) {
			echo = "HAVE A GOOD DAY";
		} else {
			echo = "DID YOU SAY " + msg + "?";
		}
		ChannelFuture future = ctx.write(echo);
		ctx.flush();
		if (close) {
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}

}
