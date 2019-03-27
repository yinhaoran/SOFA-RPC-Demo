/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SampleGenericService.java  
 * Package Name:com.example.service 
 * Date:2019年3月22日下午5:55:29  
 * Copyright (c) 2019,  
 *  
*/
/**  
 * Project Name:spring-boot-sofarpc  
 * File Name:SampleGenericService.java  
 * Package Name:SampleGenericService.java
 * Date:2019年3月22日下午5:55:29  
 * Copyright (c) 2019  
 *  
 */

package com.example.service;

import com.example.entity.People;

/**  
 * ClassName:SampleGenericService   
 * Date:     2019年3月22日 下午5:55:29  
 * @version    
 * @author   yin
 * @since    JDK 1.8  
 * @see       
 */
/**
 * ClassName:SampleGenericService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). date: 2019年3月22日 下午5:55:29
 * 
 * @author yin
 * @version
 * @since JDK 1.6
 */
public interface SampleGenericService {
	String hello(String arg);

	People hello(People people);

	String[] hello(String[] args);
}
