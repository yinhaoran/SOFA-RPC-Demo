/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:NettyTelnetServer.java  
 * Package Name:com.example.netty 
 * Date:2019年2月27日下午4:01:20  
 * Copyright (c) 2019,  
 *  
*/

package com.example.netty.server;
/**  
 * ClassName:NettyTelnetServer   
 * Date:     2019年2月27日 下午4:01:20  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import com.example.netty.config.NettyTelnetInitializer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyTelnetServer {
	private static final int PORT = 8888;

	private ServerBootstrap serverBootstrap;

	private EventLoopGroup producerLoopGroup = new NioEventLoopGroup(1);

	private EventLoopGroup consumerLoopGroup = new NioEventLoopGroup();

	public void open() throws InterruptedException {
		serverBootstrap = new ServerBootstrap();
		/**
		 * 指定SOCKET 的属性
		 */
		serverBootstrap
		.option(ChannelOption.SO_BACKLOG, 1024)
		.group(producerLoopGroup, consumerLoopGroup)
		/**
		 * 指定一个NIO通道
		 */
		.channel( NioServerSocketChannel.class)
		.handler(new LoggingHandler(LogLevel.INFO))
		.childHandler(new NettyTelnetInitializer());
		/**
		 * 绑定对应的端口号，并启动开始监听端口的连接
		 */
		Channel ch = serverBootstrap.bind(PORT).sync().channel();
		ch.closeFuture().sync();
	}
	
	public void close() {
		producerLoopGroup.shutdownGracefully();
		consumerLoopGroup.shutdownGracefully();
	}
}
