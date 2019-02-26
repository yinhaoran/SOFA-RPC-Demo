/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:AnnotationJvmServiceImpl.java  
 * Package Name:com.example.service.impl 
 * Date:2019年2月22日上午11:37:23  
 * Copyright (c) 2019,  
 *  
*/

package com.example.service.impl;

import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.example.service.SimpleJvmService;

/**
 * ClassName:AnnotationJvmServiceImpl Date: 2019年2月22日 上午11:37:23
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@SofaService(uniqueId = "annotationJvmServiceImpl")
public class AnnotationJvmServiceImpl implements SimpleJvmService {

	@Override
	public String message() {
		return "This is 【ANNOTATION SERVICE】";
	}

}
