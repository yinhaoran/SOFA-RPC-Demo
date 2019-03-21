/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:BigIntegerDecoder.java  
 * Package Name:com.example.netty.factorial 
 * Date:2019年3月19日上午9:16:29  
 * Copyright (c) 2019,  
 *  
*/

package com.example.netty.factorial;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * ClassName:BigIntegerDecoder Date: 2019年3月19日 上午9:16:29
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class BigIntegerDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < 5) {
			return;
		}
		in.markReaderIndex();
		int magicNumber = in.readUnsignedByte();
		if (magicNumber != 'F') {
			in.resetReaderIndex();
		}
	}

}
