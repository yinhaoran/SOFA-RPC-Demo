package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.sofa.runtime.api.annotation.SofaReference;
import com.example.service.SimpleJvmService;

/**
 * ClassName:TestController Date: 2019年2月22日 下午2:48:11
 * 
 * TODO 测试控制器
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@RestController
public class TestController {

	/**
	 * 未用注解标注的服务
	 */
	@SofaReference
	private SimpleJvmService simpleJvmService;

	/**
	 * 使用注解的服务，在xml文件中注入实体
	 */
	@SofaReference(uniqueId = "annotationJvmServiceImpl")
	private SimpleJvmService annotationJvmServiceImpl;

	/**
	 * 通过ClientFactory注解，在xml文件中写init方法，通过spring加载机制初始化
	 */
	@SofaReference(uniqueId = "serviceClientImpl")
	private SimpleJvmService simpleServiceClientImpl;

	@GetMapping("/serviceWithoutUniqueId")
	public String serviceWithoutUniqueId() {
		return simpleJvmService.message();
	}
}
