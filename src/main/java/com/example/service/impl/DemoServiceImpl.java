/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:DemoServiceImpl.java  
 * Package Name:com.example.service.impl 
 * Date:2019年2月20日下午5:12:34  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service.impl;

import org.springframework.stereotype.Component;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;
import com.example.service.DemoService;

/**
 * ClassName:DemoServiceImpl Date: 2019年2月20日 下午5:12:34
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */

@SofaService(interfaceType = DemoService.class, bindings = { @SofaServiceBinding(bindingType = "bolt")})
@Component
public class DemoServiceImpl implements DemoService {

	@Override
	public String doSomething(String name) {
		return name;
	}

}
