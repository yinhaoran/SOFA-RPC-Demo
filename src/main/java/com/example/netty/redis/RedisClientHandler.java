/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:RedisClientHandler.java  
 * Package Name:com.example.netty.redis 
 * Date:2019年3月21日下午4:02:36  
 * Copyright (c) 2019,  
 *  
*/

package com.example.netty.redis;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.redis.ArrayRedisMessage;
import io.netty.handler.codec.redis.ErrorRedisMessage;
import io.netty.handler.codec.redis.FullBulkStringRedisMessage;
import io.netty.handler.codec.redis.IntegerRedisMessage;
import io.netty.handler.codec.redis.RedisMessage;
import io.netty.handler.codec.redis.SimpleStringRedisMessage;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * ClassName:RedisClientHandler Date: 2019年3月21日 下午4:02:36
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class RedisClientHandler extends ChannelDuplexHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisClientHandler.class);

	@Override
	public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
		String[] commands = ((String) msg).split("\\s+");
		List<RedisMessage> children = new ArrayList<RedisMessage>(commands.length);
		for (String cmdStr : commands) {
			children.add(new FullBulkStringRedisMessage(ByteBufUtil.writeUtf8(ctx.alloc(), cmdStr)));
		}
		RedisMessage request = new ArrayRedisMessage(children);
		ctx.write(request, promise);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		RedisMessage redisMessage = (RedisMessage) msg;
		printAggregatedRedisResponse(redisMessage);
		ReferenceCountUtil.release(msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		LOGGER.error(cause.getMessage());
		ctx.close();
	}

	private void printAggregatedRedisResponse(RedisMessage msg) {
		if (msg instanceof SimpleStringRedisMessage) {
			LOGGER.debug(((SimpleStringRedisMessage) msg).content());
		} else if (msg instanceof ErrorRedisMessage) {
			LOGGER.debug(((ErrorRedisMessage) msg).content());
		} else if (msg instanceof FullBulkStringRedisMessage) {
			LOGGER.debug(getString((FullBulkStringRedisMessage) msg));
		} else if (msg instanceof IntegerRedisMessage) {
			LOGGER.debug(String.valueOf(((IntegerRedisMessage) msg).value()));
		} else if (msg instanceof ArrayRedisMessage) {

		}
	}

	private String getString(FullBulkStringRedisMessage msg) {
		if (msg.isNull()) {
			return "(null)";
		}
		return msg.content().toString(CharsetUtil.UTF_8);
	}

}
