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

import com.thd.dataaccess.model.Column;
import com.thd.dataaccess.model.Task;
import com.thd.dataaccess.service.DBService;
import com.thd.dataaccess.util.TaskManager;
import com.thd.dataaccess.util.impl.TaskManagerImpl;
/**
 * 用于导入两个相同数据库且相同的数据库表的数据
 * @author lwang
 *
 */
public class SameTabAccessTest extends TestCase{
	private DBService testService;
	private DBService officialService;
	
	private DBService testFormalService;
	private DBService officialFormalService;
	
	private String tableName;
	private TaskManager tm = new TaskManagerImpl();
	private Logger log = Logger.getLogger(this.getClass());
	
	@Before
	public void setUp(){
		String[] appContext = new String[]{"classpath:conf/appContext.xml"};
		ApplicationContext factory=new ClassPathXmlApplicationContext(appContext); 
		testService = (DBService)factory.getBean("oracle1Service");
		officialService = (DBService)factory.getBean("oracle4Service");
		testFormalService = (DBService)factory.getBean("oracleService");
		officialFormalService = (DBService)factory.getBean("oracle3Service");
	}
	
	@Test
	public void test01(){
		System.out.println(testService);
		String sql1 = "select count(jobno) from work_list";
		List l = testService.query(sql1);
		System.out.println(l.get(0));
		System.out.println(officialService);
		l = officialService.query(sql1);
		System.out.println(l.get(0));
	}
	
	
	public Task createTask(String tableName){
		String sql = "select COLUMN_NAME,DATA_TYPE from user_tab_columns where table_name like '" + tableName + "'";
		
		Task task = new Task();
		//List<Map<String,Object>> l = testService.query(sql);
		//task.setSourceService(testService);
		//task.setTargetService(officialService);
		List<Map<String,Object>> l = testFormalService.query(sql);
		task.setSourceService(testFormalService);
		task.setTargetService(officialFormalService);
		
		task.setClear(true);
		List<Column> columns = new ArrayList<Column>();
		for(Map obj : l){
			String columnName = obj.get("COLUMN_NAME").toString();
			String dataType = obj.get("DATA_TYPE").toString();
			Column c = new Column();
			c.setAs(columnName);
			c.setGenerator("copy");
			c.setIsPrimarykey(false);
			c.setKey(true);
			c.setMark(false);
			
			c.setSourceColumn(columnName);
			c.setTargetColumn(columnName);
			columns.add(c);
		}
		
		task.setColumns(columns);
		task.setId(tableName);
		task.setImportType("SAVE");
		task.setIsUpdate(false);
		task.setSourceTable(tableName);
		task.setTargetTable(tableName);
		return task;
	};
	
	@Test
	public void test_sys_dic() throws Exception{
		tableName = "SYS_DIC";
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	
	@Test
	public void test_work_dic_form() throws Exception{
		tableName = "work_dic_form".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	
	@Test
	public void test_work_dic_form_version() throws Exception{
		tableName = "work_dic_form_version".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_work_dic_form_totalpage() throws Exception{
		tableName = "work_dic_form_totalpage".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_work_dic_survey_code() throws Exception{
		tableName = "work_dic_survey_code".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_dic_ship_type() throws Exception{
		tableName = "sys_dic_ship_type".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_work_dic_survey_addition() throws Exception{
		tableName = "work_dic_survey_addition".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_dic_ship_record() throws Exception{
		tableName = "sys_dic_ship_record".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	
	@Test
	public void test_sys_dic_notation() throws Exception{
		tableName = "sys_dic_notation".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_work_dic_datatransfer() throws Exception{
		tableName = "work_dic_datatransfer".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_dic_law() throws Exception{
		tableName = "sys_dic_law".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_dic_form_law() throws Exception{
		tableName = "sys_dic_form_law".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_dic_product_cert_type() throws Exception{
		tableName = "sys_dic_product_cert_type".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_dic_addition_exp_cdt() throws Exception{
		tableName = "sys_dic_addition_exp_cdt".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_checklist() throws Exception{
		tableName = "sys_checklist".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_checklist_param() throws Exception{
		tableName = "sys_checklist_param".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_gc_client() throws Exception{
		tableName = "gc_client".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_gc_client_cate() throws Exception{
		tableName = "gc_client_cate".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_gc_client_cate_rela() throws Exception{
		tableName = "gc_client_cate_rela".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_dic_work() throws Exception{
		tableName = "sys_dic_work".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_work_dic_process() throws Exception{
		tableName = "work_dic_process".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_work_dic_step() throws Exception{
		tableName = "work_dic_step".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_work_type_step_assign() throws Exception{
		tableName = "work_type_step_assign".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_menu() throws Exception{
		tableName = "sys_menu".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_org() throws Exception{
		tableName = "sys_org".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	
	@Test
	public void test_sys_user_info() throws Exception{
		tableName = "sys_user_info".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_role() throws Exception{
		tableName = "sys_role".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_rela_user_role() throws Exception{
		tableName = "sys_rela_user_role".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_rela_user_org() throws Exception{
		tableName = "sys_rela_user_org".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_rela_role_menu() throws Exception{
		tableName = "sys_rela_role_menu".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_rela_checklist_survey() throws Exception{
		tableName = "sys_rela_checklist_survey".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	@Test
	public void test_sys_rela_checklist_record() throws Exception{
		tableName = "sys_rela_checklist_record".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	
	
	@Test
	public void test_p_dic_tables() throws Exception{
		tableName = "p_dic_tables".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	
	@Test
	public void test_p_dic_columns() throws Exception{
		tableName = "p_dic_columns".toUpperCase();
		Task task = createTask(tableName);
		tm.executeTask(task);
	}
	
	
	
	
	
	
	
	
}
