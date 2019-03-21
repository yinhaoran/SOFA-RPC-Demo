/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:EchoClient.java  
 * Package Name:com.example.netty.server 
 * Date:2019年3月5日下午2:52:27  
 * Copyright (c) 2019,  
 *  
*/

package com.example.netty.server;

import javax.net.ssl.SSLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.netty.handler.EchoClientHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

/**
 * ClassName:EchoClient Date: 2019年3月5日 下午2:52:27
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class EchoClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EchoClient.class); 
	/**
	 * 安全套接层
	 */
	static final boolean SSL = System.getProperty("ssl") != null;
	/**
	 * 主机
	 */
	static final String HOST = System.getProperty("host", "127.0.0.1");
	/**
	 * 端口
	 */
	static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
	/**
	 * 大小
	 */
	static final int SIZE = Integer.parseInt(System.getProperty("size", "256"));

	public static void main(String[] args) throws SSLException, InterruptedException {
		// Configure SSL.git
		final SslContext sslCtx;
		if (SSL) {
			sslCtx = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
		} else {
			sslCtx = null;
		}

		EventLoopGroup group = new NioEventLoopGroup();
		try {
			/**
			 * 客户端容器
			 */
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel ch) throws Exception {
					ChannelPipeline channelPipeline = ch.pipeline();
					if (sslCtx != null) {
						channelPipeline.addLast(sslCtx.newHandler(ch.alloc(), HOST, PORT));
					}
					channelPipeline.addLast(new EchoClientHandler());
					ChannelFuture channelFuture = bootstrap.connect(HOST, PORT)
							.addListener(new ChannelFutureListener() {
								@Override
								public void operationComplete(ChannelFuture future) throws Exception {
									LOGGER.info("连接完成");
								}
							}).sync();
					channelFuture.channel().closeFuture().sync();
				}
			});
		} finally {
			group.shutdownGracefully();
		}
	}
}
