package com.piano.session;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


	
	public class GetConfig {
		
		static ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		public static  ApplicationContext getConfig(){
			return ac;
		}
	}



