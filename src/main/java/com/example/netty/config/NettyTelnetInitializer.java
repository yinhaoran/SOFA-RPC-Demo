/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:NettyTelnetInitializer.java  
 * Package Name:com.example.netty.config 
 * Date:2019年2月27日下午4:07:47  
 * Copyright (c) 2019,  
 *  
*/

package com.example.netty.config;

import com.example.netty.handler.NettyTelnetHandler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * ClassName:NettyTelnetInitializer Date: 2019年2月27日 下午4:07:47
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class NettyTelnetInitializer extends ChannelInitializer<SocketChannel> {

	private static final StringDecoder STRINGDECODER = new StringDecoder();
	private static final StringEncoder STRINGENCODER = new StringEncoder();

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
		/**
		 * 字符串解密
		 */
		.addLast(STRINGDECODER)
		/**
		 * 字符串加密
		 */
		.addLast(STRINGENCODER)
		.addLast(new NettyTelnetHandler());
	}

}
