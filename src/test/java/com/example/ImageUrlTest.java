/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:ImageUrlTest.java  
 * Package Name:com.example 
 * Date:2019年3月4日下午3:54:10  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

/**
 * ClassName:ImageUrlTest Date: 2019年3月4日 下午3:54:10
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class ImageUrlTest {

	/**
	 * 
	 * getInputStreamByUrl:(从连接中获取二进制流文件). <br/>
	 *
	 * @param strUrl
	 * @return
	 * @throws Exception
	 * @since JDK 1.8
	 */
	public byte[] getInputStreamByUrl(String strUrl) throws Exception {
		HttpsURLConnection conn = null;
		try {
			URL url = new URL(strUrl);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(20 * 1000);
			if (conn instanceof HttpsURLConnection) {
				SSLContext sc = SSLContext.getInstance("SSL");
				sc.init(null, new TrustManager[] { new TrustAnyTrustManager() }, new java.security.SecureRandom());
				conn.setSSLSocketFactory(sc.getSocketFactory());
				conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
			}
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			IOUtils.copy(conn.getInputStream(), output);
			return output.toByteArray();
		} catch (Exception e) {
			throw new Exception("附件查看错误:" + e.getStackTrace() + e.getMessage());
		} finally {
			try {
				if (conn != null) {
					conn.disconnect();
				}
			} catch (Exception e) {
				throw new Exception("附件查看错误:" + e.getStackTrace() + e.getMessage());
			}
		}
	}

	private static class TrustAnyTrustManager implements X509TrustManager {
		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return new X509Certificate[] {};
		}
	}

	private static class TrustAnyHostnameVerifier implements HostnameVerifier {
		@Override
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

	@Test
	public void test() {
		try {
			byte[] output = getInputStreamByUrl(URL);
			getFile(output, "D:\\Work", "图片.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	final String URL = "https://srm.thinkinpower.net:8843/sap/bc/zfdownload?sap-client=600&fileid=00000000000000002418";

	/**
	 * 根据byte数组，生成文件
	 */
	public void getFile(byte[] bfile, String filePath, String fileName) {
		BufferedOutputStream bos = null;
		FileOutputStream fos = null;
		File file = null;
		try {
			File dir = new File(filePath);
			/**
			 * 判断文件目录是否存在
			 */
			if (!dir.exists() && dir.isDirectory()) {
				dir.mkdirs();
			}
			file = new File(filePath + "\\" + fileName);
			fos = new FileOutputStream(file);
			bos = new BufferedOutputStream(fos);
			bos.write(bfile);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	@Test
	public void testHttpClient() throws Exception {
		byte[] bFile = getBFile(URL);
		getFile(bFile, "D:\\Work", "图片.jpg");
	}
	
	
	private byte[] getBFile(String url) throws Exception {
		byte[] bfile = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		/**
		 * 创建实例
		 */
		CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
		/**
		 * 创建请求
		 */
		HttpGet httpGet = new HttpGet(url); 
		/**
		 * 执行
		 */
		CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
		/**
		 * 获取实体
		 */
		HttpEntity httpEntity = closeableHttpResponse.getEntity(); 
		if (httpEntity != null) {
//			System.out.println("ContentType:" + httpEntity.getContentType().getValue());
			InputStream inputStream = httpEntity.getContent();
			IOUtils.copy(inputStream, output);
			bfile = output.toByteArray();
		}
		closeableHttpResponse.close();
		closeableHttpClient.close();
		return bfile;
	}
	
	@Test
	public void filePathTest() {
		String path = "D:\\PNT测试文件\\2019-04-03\\patch_恒力应付接口对接补丁包括国信影像二维码改造\\replacement\\modules";
		getFiles(path);
	}
	
	private void getFiles(String path) {
		File file = new File(path);
		// 如果这个路径是文件夹
		if (file.isDirectory()) {
			// 获取路径下的所有文件
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				File tmp = files[i];
				// 如果还是文件夹 递归获取里面的文件 文件夹
				if (tmp.isDirectory()) {
					// System.out.println("目录：" + tmp.getPath());
					getFiles(files[i].getPath());
				} else {
					// System.out.println("文件：" + tmp.getPath());
					String fileName = tmp.getName();
					if (fileName.endsWith("java")) {
						System.out.println(fileName);
					}
				}
			}
		} else {
			System.out.println("文件：" + file.getPath());
		}
	}
}
