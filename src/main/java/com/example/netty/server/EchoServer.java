/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:EchoServer.java  
 * Package Name:com.example.netty.server 
 * Date:2019年3月2日下午5:22:47  
 * Copyright (c) 2019,  
 *  
*/

package com.example.netty.server;

import com.example.netty.handler.EchoServerHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;

/**
 * ClassName:EchoServer Date: 2019年3月2日 下午5:22:47
 * 
 * @version
 * @author yin																																																																																																·
 * @since JDK 1.8
 * @see
 */
public class EchoServer {
	static final boolean SSL = System.getProperty("ssl") != null;
	static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));

	public static void main(String[] args) throws Exception {
		// Configure SSL.
		final SslContext sslCtx;
		if (SSL) {
			SelfSignedCertificate ssc = new SelfSignedCertificate();
			sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
		} else {
			sslCtx = null;
		}

		// Configure the server.
		/**
		 * 用于服务端接收客户端的连接
		 */
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		/**
		 * 用于客户端的SocketChannel的数据读写
		 */
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		final EchoServerHandler serverHandler = new EchoServerHandler();
		try {
			ServerBootstrap b = new ServerBootstrap();
			/**
			 * 设置接收端和客户端
			 */
			b.group(bossGroup, workerGroup)
			/**
			 * 创建Channel单例
			 */
			.channel(NioServerSocketChannel.class)
			/**
			 * 设置NioServerSocketChannel的可选项
			 */
			.option(ChannelOption.SO_BACKLOG, 100)
			/**
			 * 用于服务请求，这里加入了日志处理器
			 */
			.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						/**
						 * 
						 * TODO Channel被注册时调用此方法， 方法返回时移除实例
						 * 
						 * @see io.netty.channel.ChannelInitializer#initChannel(io.netty.channel.Channel)
						 */
						@Override
						public void initChannel(SocketChannel ch) throws Exception {
							ChannelPipeline p = ch.pipeline();
							if (sslCtx != null) {
								p.addLast(sslCtx.newHandler(ch.alloc()));
							}
							// p.addLast(new LoggingHandler(LogLevel.INFO));
							p.addLast(serverHandler);
						}
					});

			// Start the server.
			/**
			 * 先绑定端口，等待阻塞成功，启动服务端过程
			 * 加上ChannelFutureListener监听器回调通知
			 */
			ChannelFuture f = b.bind(PORT).addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					
				}
			}).sync();
			// Wait until the server socket is closed.
			/**
			 * 监听服务器等待阻塞成功
			 */
			f.channel().closeFuture().sync();
		} finally {
			// Shut down all event loops to terminate all threads.
			/**
			 * 分别关闭两个对象
			 */
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
