package com.thd.dataaccess.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Before;

import com.thd.dataaccess.model.Task;
import com.thd.dataaccess.util.impl.TaskManagerImpl;
import com.thd.dataaccess.util.impl.TransformModelImpl;

public class RunAllXmlTest extends TestCase{
	private TaskManager tm;
	private TransformModel tfm;
	private List<Task> r;
	
	private Logger log = Logger.getLogger(this.getClass());
	@Before
	public void setUp() throws Exception{
		tm = new TaskManagerImpl();
		tfm = new TransformModelImpl();
		File f = new File("E://thdsvn//java_project//data_access//xml//dsmis.xml");
		InputStream is = new FileInputStream(f);
		r = tfm.transformTaskToList(is);
	}
	
	
	public void test_runAll() throws Exception{
		long beginTime = new Date().getTime();
		List<String> errList = new ArrayList<String>();
		for(Task task:r){
			try{
				tm.executeTask(task);
			}catch(Exception e){
				errList.add(task.getId());
				this.log.error("执行任务出现错误:" + task.getId());
			}
		}
		
		this.log.info("======================================= finish  =============================");
		System.out.println("任务全部完成:失败" + errList.size() + "条");
		for(String r : errList){
			this.log.info(r);
		}
		long endTime = new Date().getTime();
		Double useTime = (Double.parseDouble(String.valueOf(endTime))-Double.parseDouble(String.valueOf(beginTime)))/60000d;
		this.log.info("||||||||||||||||||||||      执行时间:" + new BigDecimal(useTime).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + " 分钟    ||||||||||||||||||||||");
	}
	
	
	
}
