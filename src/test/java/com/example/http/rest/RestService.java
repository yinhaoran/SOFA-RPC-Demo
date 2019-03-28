/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:RestService.java  
 * Package Name:com.example.http.rest 
 * Date:2019年3月27日下午10:28:33  
 * Copyright (c) 2019,  
 *  
*/  
  
package com.example.http.rest;

import java.util.List;

/**  
 * ClassName:RestService   
 * Date:     2019年3月27日 下午10:28:33  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */
public interface RestService {
	public String add(int code, String name);

    public void noResp();

    public String query(int code);

    public ExampleObj object(ExampleObj code);

    public List<ExampleObj> objects(List<ExampleObj> code);

    public String error(String code);

    public EchoResponse echoPb(EchoRequest request);
}
  
