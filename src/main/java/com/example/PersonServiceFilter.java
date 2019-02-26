/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:PersonServiceFilter.java  
 * Package Name:com.example 
 * Date:2019年2月21日上午11:25:04  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

import com.alipay.sofa.rpc.core.exception.SofaRpcException;
import com.alipay.sofa.rpc.core.request.SofaRequest;
import com.alipay.sofa.rpc.core.response.SofaResponse;
import com.alipay.sofa.rpc.filter.Filter;
import com.alipay.sofa.rpc.filter.FilterInvoker;
import com.alipay.sofa.rpc.log.Logger;
import com.alipay.sofa.rpc.log.LoggerFactory;

/**
 * ClassName:PersonServiceFilter Date: 2019年2月21日 上午11:25:04
 * 
 * TODO 过滤器 注册服务之前
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class PersonServiceFilter extends Filter {

	private final static Logger LOGGER = LoggerFactory.getLogger(PersonServiceFilter.class);

	@Override
	public SofaResponse invoke(FilterInvoker invoker, SofaRequest request) throws SofaRpcException {

		LOGGER.debug("PersonServiceFilter前");
		// TODO Auto-generated method stub
		try {
			return invoker.invoke(request);
		} finally {
			LOGGER.debug("PersonServiceFilter后");
		}
	}

}
