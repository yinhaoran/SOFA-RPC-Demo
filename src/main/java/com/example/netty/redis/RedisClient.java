/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:RedisClient.java  
 * Package Name:com.example.netty.redis 
 * Date:2019年3月21日下午4:33:00  
 * Copyright (c) 2019,  
 *  
*/

package com.example.netty.redis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.redis.RedisArrayAggregator;
import io.netty.handler.codec.redis.RedisBulkStringAggregator;
import io.netty.handler.codec.redis.RedisDecoder;
import io.netty.handler.codec.redis.RedisEncoder;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * ClassName:RedisClient Date: 2019年3月21日 下午4:33:00
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class RedisClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RedisClient.class);

	/**
	 * 主机地址
	 */
	private static final String HOST = System.getProperty("host", "127.0.0.1");

	/**
	 * REDIS 端口号
	 */
	private static final int PORT = Integer.parseInt(System.getProperty("port", "6379"));

	public void start() throws InterruptedException, IOException {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).handler(new ChannelInitializer<Channel>() {
				@Override
				protected void initChannel(Channel ch) throws Exception {
					ChannelPipeline p = ch.pipeline();
					p.addLast(new RedisDecoder()).addLast(new RedisBulkStringAggregator())
							.addLast(new RedisArrayAggregator()).addLast(new RedisEncoder())
							.addLast(new RedisClientHandler());
				}
			});
			Channel ch = b.connect(HOST, PORT).sync().channel();
			ChannelFuture channelFuture = null;
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			for (;;) {
				final String input = in.readLine();
				final String line = StringUtils.stripToEmpty(input);
				if (line == null || "quit".equalsIgnoreCase(line)) {
					ch.close().sync();
					break;
				} else if (line.isEmpty()) {
					continue;
				}
				channelFuture = ch.writeAndFlush(line);
				channelFuture.addListener(new GenericFutureListener<ChannelFuture>() {
					@Override
					public void operationComplete(ChannelFuture future) throws Exception {
						if (!future.isSuccess()) {
							LOGGER.error("write failed");
							future.cause().printStackTrace(System.err);
						}
					}
				});

				if (channelFuture != null) {
					channelFuture.sync();
				}
			} 
		} finally {
			group.shutdownGracefully();
		}
	}

}
