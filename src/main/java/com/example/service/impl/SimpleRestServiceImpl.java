/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SimpleRestServiceImpl.java  
 * Package Name:com.example.service.impl 
 * Date:2019年2月21日下午2:32:12  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.example.service.SimpleRestService;

/**
 * ClassName:SimpleRestServiceImpl Date: 2019年2月21日 下午2:32:12
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@SofaService(bindings = { @SofaServiceBinding(bindingType = "rest") })
public class SimpleRestServiceImpl implements SimpleRestService {

	@Override
	public String sayHello() {

		// TODO Auto-generated method stub
		return "Hello";
	}

}
