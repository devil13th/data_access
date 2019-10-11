package com.thd.dataaccess.dao;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DaoImplTest extends TestCase{
	private Dao mysqlDao;
	@Before
	public void setUp(){
		String[] appContext = new String[]{"classpath:conf/appContext.xml"};
		ApplicationContext factory=new ClassPathXmlApplicationContext(appContext); 
		mysqlDao = (Dao)factory.getBean("mysqlDao");
		System.out.println("init");
	}
	
	@Test
	public void test(){
		System.out.println(mysqlDao);
	}
}
