/**
 * 
 */
package com.upjf.fund.server.run;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Administrator
 *
 */
public class Run {

	private static ClassPathXmlApplicationContext context;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			context = new ClassPathXmlApplicationContext(
					"classpath:spring/spring-context.xml");
			context.start();
			System.out.println("-------estatefund service done------");
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
