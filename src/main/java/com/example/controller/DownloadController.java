/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:DownloadController.java  
 * Package Name:com.example.controller 
 * Date:2019年3月10日上午9:56:04  
 * Copyright (c) 2019,  
 *  
*/

package com.example.controller;

import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * ClassName:DownloadController Date: 2019年3月10日 上午9:56:04
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
@RestController
@RequestMapping(value = "/common")
public class DownloadController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DownloadController.class);

	@SuppressWarnings({ "unused", "rawtypes" })
	@RequestMapping(value = "/upload")
	public void upload(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "file", required = false) MultipartFile uploadFile) {
		LOGGER.debug("AAA");
		// 转型为MultipartHttpRequest：
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获得文件：
		MultipartFile file = multipartRequest.getFile("file");
		Iterator it = multipartRequest.getFileNames();
		String name = uploadFile.getName();
		LOGGER.debug("AAA");
	}
}
