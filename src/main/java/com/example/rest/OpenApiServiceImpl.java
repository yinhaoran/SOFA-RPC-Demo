/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:OpenApiServiceImpl.java  
 * Package Name:com.example.rest 
 * Date:2019年2月22日下午4:38:53  
 * Copyright (c) 2019,  
 *  
*/

package com.example.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.InitializingBean;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alipay.sofa.runtime.api.annotation.SofaService;
import com.alipay.sofa.runtime.api.annotation.SofaServiceBinding;

import io.swagger.v3.core.util.Json;
import io.swagger.v3.jaxrs2.integration.JaxrsOpenApiContextBuilder;
import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.integration.api.OpenApiContext;
import io.swagger.v3.oas.models.OpenAPI;

/**
 * ClassName:OpenApiServiceImpl Date: 2019年2月22日 下午4:38:53
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 * 
 * 
 */
@SofaService(bindings = { @SofaServiceBinding(bindingType = "rest") }, interfaceType = OpenApiService.class)
public class OpenApiServiceImpl implements OpenApiService, InitializingBean {

	private OpenAPI openApi;

	@Override
	public String openApi() {
		return Json.pretty(openApi);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		List<Package> resourcePackages = new ArrayList<Package>();
		resourcePackages.add(this.getClass().getPackage());
		if (CollectionUtils.isNotEmpty(resourcePackages)) {
			SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                    .resourcePackages(resourcePackages.stream().map(Package::getName).collect(Collectors.toSet()));

            OpenApiContext oac = new JaxrsOpenApiContextBuilder<>()
                    .openApiConfiguration(oasConfig)
                    .buildContext(true);
            openApi = oac.read();
		}
		// TODO Auto-generated method stub
	}

}
