/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:HttpApacheClientTest.java  
 * Package Name:com.example.sofa.rpc.rest 
 * Date:2019年4月2日上午9:01:52  
 * Copyright (c) 2019,  
 *  
*/

package com.example.sofa.rpc.rest;
/**  
 * ClassName:HttpApacheClientTest   
 * Date:     2019年4月2日 上午9:01:52  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */

import java.nio.charset.Charset;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alipay.sofa.rpc.common.json.JSON;

public class HttpApacheClientTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpApacheClientTest.class);

	@Test
	public void test() {
		String url = "http://127.0.0.1:8888/rest/post/121221";
		Object[] params = new Object[] { "xxhttpxx" };
		String result = null;
		try {
			result = sendPost(url, params);
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		LOGGER.info("result : {}", result);
	}

	private String sendPost(String url, Object[] params) throws Exception {
		/**
		 * 创建实例
		 */
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("token", "1qaz2wsx"); // 服务端需要token
		String json = JSON.toJSONString(params[0]);
		StringEntity entity = new StringEntity(json, Charset.forName("UTF-8"));
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
		HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
		HttpEntity responseEntity = httpResponse.getEntity();
		LOGGER.info("response status: " + httpResponse.getStatusLine());
		String response = EntityUtils.toString(responseEntity);
		LOGGER.info("响应 " + response);
		return response;
	}

}
