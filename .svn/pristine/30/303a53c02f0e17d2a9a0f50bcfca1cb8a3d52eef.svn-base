package com.thd.dataaccess.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.thd.dataaccess.service.DBService;

public class DBServiceTest extends TestCase{
	private DBService mysqlService;
	private DBService mysqlService1;
	private DBService oracleService;
	private DBService sqlserverService;
	private DBService oracleWorkCSService;
	private DBService oracleWorkZSService;
	
	private JdbcTemplate mysqlTemplate;
	private JdbcTemplate oracleTemplate;
	private JdbcTemplate sqlserverTemplate;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Before
	public void setUp(){
		String[] appContext = new String[]{"classpath:conf/appContext.xml"};
		ApplicationContext factory=new ClassPathXmlApplicationContext(appContext); 
		//mysqlService = (DBService)factory.getBean("mysqlService");
		//mysqlService1 = (DBService)factory.getBean("mysql1Service");
		oracleWorkZSService = (DBService)factory.getBean("oracle4Service");
		//oracleService = (DBService)factory.getBean("oracleService");
		//sqlserverService = (DBService)factory.getBean("sqlserverService");
		
		//mysqlTemplate = (JdbcTemplate)factory.getBean("mysqlJdbcTemplate");
		//oracleTemplate = (JdbcTemplate)factory.getBean("oracleJdbcTemplate");
		//sqlserverTemplate = (JdbcTemplate)factory.getBean("sqlserverJdbcTemplate");
		
		oracleWorkCSService = (DBService)factory.getBean("oracle1Service");
	}
	
	@Test
	public void test001(){
		//System.out.println(oracleService);
		
		String sql = "select distinct table_name from user_tab_columns where table_name like 'SHIP%' ";
		List l = oracleWorkZSService.query(sql);
		for(int i = 0 , j = l.size() ; i < j ; i++){
			Map m = (Map)l.get(i);
			String tabName = m.get("TABLE_NAME").toString();
			//System.out.println(tabName);
			String select_sql = "select * from " + tabName + " where job = 'ff8080814e30f0cc014e3e8d4f2e5765' ";
			List rs = oracleWorkZSService.query(sql);
			if(rs != null && !rs.isEmpty()){
				//System.out.println("select * from " + tabName + " where job = 'ff8080814e30f0cc014e3e8d4f2e5765' ");
				
				String cs_col_sql = "select column_name from  user_tab_columns where table_name like '" + tabName + "'";
				List cs_cols = oracleWorkCSService.query(cs_col_sql);
				String columnsSql = "";
				String valueSql = "";
				for(Object obj : cs_cols){
					String colName = ((Map)obj).get("COLUMN_NAME").toString();
					columnsSql += colName + ",";
					valueSql += "?,";
				}
				columnsSql = columnsSql.substring(0,columnsSql.length() -1);
				valueSql = valueSql.substring(0,valueSql.length() -1);
				//System.out.println(columnsSql);
				//System.out.println(valueSql);
				
				String insertSql = "insert into " + tabName + " (" +  columnsSql + ") values (" + valueSql + ")" ;				
				String selectSql = " select " + columnsSql + " from " + tabName + " where job = 'ff8080814e30f0cc014e3e8d4f2e5765' ";
				String deleteSql = "delete from " + tabName + " where job = 'ff8080814e30f0cc014e3e8d4f2e5765'";
				System.out.println(insertSql);
				System.out.println(selectSql);
				System.out.println(deleteSql);
				oracleWorkCSService.execute(deleteSql);
				List rs_tmp = oracleWorkZSService.query(selectSql);
				if(rs_tmp!=null && !rs_tmp.isEmpty()){
					List params = new ArrayList();
					//处理每一条记录
					for(Object obj_tmp : rs_tmp){
						Map vMap = (Map)obj_tmp;
						System.out.println(vMap);
						params.clear();
						for(Object obj_1 : cs_cols){
							String cname = ((Map)obj_1).get("COLUMN_NAME").toString();
							params.add(vMap.get(cname));
						}
						
						//oracleWorkCSService.execute("delete from " + tabName ＋ " where ");
						
						oracleWorkCSService.executeByParams(insertSql, params.toArray());
					}
					
				}
				System.out.println("= ============================================================");
				
			}
			
			
			
			//System.out.println(i);
		}
		
	}
	
	/*@Test
	public void test02(){
		System.out.println(oracleService);
		String sql = "select count(cjdjh) from p_ship";
		List l = oracleService.query(sql);
		System.out.print(l.get(0));
	}
	
	@Test
	public void test(){
		//System.out.println(mysqlService);
		//System.out.println(mysqlService1);
		
		//System.out.println(oracleService);
		//System.out.println(sqlserverService);
		//System.out.println(mysqlTemplate);
		//System.out.println(oracleTemplate);
		//System.out.println(sqlserverTemplate);
		String str = "select * from ship";
		List l = mysqlService.query(str);
		System.out.println(l.size());
		
		String str2 = "select * from userinfo";
		List l2 = mysqlService1.query(str2);
		System.out.println(l2.size());
		
		this.log.info("success");
	}
	*/
	/*
	@Test
	public void testsqlserverService(){
		String sql = "select * from cbjbqk";
		List<Map<String,Object>> l = sqlserverService.query(sql);
		log.info(l.size());
		
		String sql1 = "select * from p_ship";
		List<Map<String,Object>> l2 = oracleService.query(sql1);
		log.info(l2.size());
	}
	
	@Test
	public void testOracleService(){
		System.out.println(mysqlService.getJdbcTemplate().equals(oracleService.getJdbcTemplate()));
		
		String sql = "select source_table_name as st,source_table_column_name as sc ,source_table_key as sk,id,target_table_name as tt ,target_table_column_name as tc ,target_table_key as tk,target_table_column_comment as cmt  from source_target order by target_table_name,source_table_name,target_table_column_name,source_table_column_name";
		List<Map<String,Object>> l = oracleService.query(sql);
		for(Map<String,Object> m : l){
			String insert_sql = "insert into tbinfo(s_t,s_c,s_k,t_t,t_c,t_k,cmt) values ('" + m.get("ST") + "','" + m.get("SC") + "','" + m.get("SK") + "','" + m.get("TT") + "','" + m.get("TC") + "','" + m.get("TK") + "','" + m.get("CMT") + "')";
			this.log.info(insert_sql);
			this.mysqlService.getJdbcTemplate().execute(insert_sql);
			log.info("success");
			
		}
		
		log.info("finish");
		
	}
	
	*/
	
	
	
}
