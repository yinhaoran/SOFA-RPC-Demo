/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SocketTest.java  
 * Package Name:com.example 
 * Date:2019年3月19日上午9:53:21  
 * Copyright (c) 2019,  
 *  
*/

package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Test;

/**
 * ClassName:SocketTest Date: 2019年3月19日 上午9:53:21
 * 
 * @version
 * @author yin
 * @since JDK 1.8
 * @see
 */
public class SocketTest {
	/**
	 * 
	 * bioTest:(这里用一句话描述这个方法的作用). <br/>
	 * 
	 * @since JDK 1.8
	 */
	@Test
	public void bioTest() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(5678);
			Socket clientSocket = serverSocket.accept();
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			String request, response;
			while ((request = in.readLine()) != null) {
				if ("Done".equals(request)) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
