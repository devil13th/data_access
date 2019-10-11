package com.thd.dataaccess.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import com.thd.dataaccess.model.Task;
import com.thd.dataaccess.service.DBService;
import com.thd.dataaccess.util.TaskManager;
import com.thd.dataaccess.util.TransformModel;
import com.thd.dataaccess.util.impl.TaskManagerImpl;
import com.thd.dataaccess.util.impl.TransformModelImpl;

public class ImportHrDataByXmlTest extends TestCase{
	private TaskManager tm;
	private TransformModel tfm;
	private Map<String,Task> r;
	private Logger log = Logger.getLogger(this.getClass());
	@Before
	public void setUp() throws Exception{
		tm = new TaskManagerImpl();
		tfm = new TransformModelImpl();
		File f = new File("E://thdsvn//java_project//data_access//xml//hrmis.xml");
		InputStream is = new FileInputStream(f);
		r = tfm.transformTaskToMap(is);
	}
	@Test
	public void test_hr_exam_program() throws Exception{
		tm.executeTask(r.get("hr_exam_program"));
	}
	@Test
	public void test_hr_exam_subject() throws Exception{
		tm.executeTask(r.get("hr_exam_subject"));
	}
	@Test
	public void test_hr_exam_subject_pro() throws Exception{
		tm.executeTask(r.get("hr_exam_subject_pro"));
	}
	@Test
	public void test_hr_exam_r_program_subject() throws Exception{
		tm.executeTask(r.get("hr_exam_r_program_subject"));
	}
	
	@Test
	public void test_hr_exam_options() throws Exception{
		tm.executeTask(r.get("hr_exam_options"));
	}
	
	
	
}
