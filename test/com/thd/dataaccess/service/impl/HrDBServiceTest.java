package com.thd.dataaccess.service.impl;

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

import com.thd.dataaccess.custom.CustomImportProgram;
import com.thd.dataaccess.custom.impl.HrInitProgram;
import com.thd.dataaccess.service.DBService;
import com.thd.dataaccess.util.Base64Tool;
import com.thd.dataaccess.util.ListUtil;
import com.thd.dataaccess.util.StringUtil;

public class HrDBServiceTest extends TestCase{
	private DBService newHrService;
	private JdbcTemplate newHrTemplate;
	private DBService oldHrService;
	private JdbcTemplate oldHrTemplate;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Before
	public void setUp(){
		String[] appContext = new String[]{"classpath:conf/appContext.xml"};
		ApplicationContext factory=new ClassPathXmlApplicationContext(appContext); 
		newHrService = (DBService)factory.getBean("oracleService");
		newHrTemplate = (JdbcTemplate)factory.getBean("oracleJdbcTemplate");
		oldHrService = (DBService)factory.getBean("oracle1Service");
		oldHrTemplate = (JdbcTemplate)factory.getBean("oracle1JdbcTemplate");
	}
	/*
	@Test
	public void test001(){
		System.out.println(newHrService);
		System.out.println(newHrTemplate);
	}
	
	
	@Test
	public void test002(){
		String sql = " select * from hr_exam_paper_ins ";
		List l = newHrTemplate.queryForList(sql);
		System.out.println(l);
	}
	
	
	
	@Test
	public void test003(){
		String sql = " select * from KNOWLEDGECATEGORY ";
		List l = oldHrTemplate.queryForList(sql);
		System.out.println(l);
	}
	*/
	//@Test
	/**
	 * 导入试题大纲
	 */
	/*
	public void testimportProgram(){
		this.log.info(" ======================================= 导入考题大纲[HR_EXAM_PROGRAM]表   开始  ============================");
		String delete_sql = "delete from HR_EXAM_PROGRAM  ";
		newHrTemplate.execute(delete_sql);
		this.log.info("----------清空该表-----------");
		
		String query_sql = " select KCID,KCNAME,KCNUM,TINDEX from KNOWLEDGECATEGORY ";
		List l = oldHrTemplate.queryForList(query_sql);
		
		long ct_all = 0l;
		long ct_success = 0l;
		long ct_failure = 0l;
		long ct = 0;
		if(l!=null && l.size() > 0){
			this.log.info(" 应导入：" + l.size() +"条记录");
			
			ct_all = l.size();
			
			for(Object obj : l){
				ct++;
				if(obj != null){
					try{
						Map map = (Map)obj;
						String kcid = map.get("KCID") == null ? "" : map.get("KCID").toString();
						String kcname = map.get("KCNAME") == null ? "" : map.get("KCNAME").toString();
						String kcnum = map.get("KCNUM") == null ? "" : map.get("KCNUM").toString();
						String tindex = map.get("TINDEX") == null ? "" : map.get("TINDEX").toString();
						
						String insert_sql = " insert into HR_EXAM_PROGRAM(PROGRAM_ID,PROGRAM_NAME,PROGRAM_NO,PROGRAM_SORT) values(?,?,?,?)";
						List params = new ArrayList();
						params.add(kcid);
						params.add(kcname);
						params.add(kcnum);
						params.add(tindex);
						newHrTemplate.update(insert_sql, params.toArray());
						ct_success++;
						System.out.println("--" + ct + "--");
					}catch(Exception e){
						ct_failure++;
						this.log.error(e.getMessage());
					}
				}
			}
		}
		this.log.info("考题大纲[HR_EXAM_PROGRAM]表      应导入" + ct_all +"条记录       成功" + ct_success + "条         失败" + ct_failure + "条");
		this.log.info(" ======================================= 导入考题大纲[HR_EXAM_PROGRAM]表  完成 ============================");
	}
	*/
	
	@Test
	/**
	 * 初始化试题大纲树形代码
	 */
	public void testinitProgram(){
		
		long beginTime = new Date().getTime();
		this.log.info(" ======================================= 考题大纲初始化树形目录代码[HR_EXAM_PROGRAM]表   开始  ============================");
		
		String clear_code_sql = "update HR_EXAM_PROGRAM set PROGRAM_STATUS='1',IS_VALID='1',CRE_USERID='init',CRE_NAME='init'";
		newHrTemplate.update(clear_code_sql);
		this.log.info(" 清空CODE字段成功 ");
		
		String init_valid = "update HR_EXAM_PROGRAM set PROGRAM_CODE = null";
		newHrTemplate.update(clear_code_sql);
		this.log.info(" 清空CODE字段成功 ");
		
		String update_root_sql = "update HR_EXAM_PROGRAM set PROGRAM_CODE = 'root' where PROGRAM_ID='9000'";
		newHrTemplate.update(update_root_sql);
		this.log.info(" 初始化根节点成功 ");
		
		
		
		String import_order_sql = "select "
				+ " t.kcid as ID,"
				+ " t1.kcid as PID "
				+ " from "
				+ " KNOWLEDGECATEGORY t left join  KNOWLEDGECATEGORY t1 on t.parentid = t1.kcid "
				+ " order by t1.kcid,t.kcid";
		List l = oldHrTemplate.queryForList(import_order_sql);
		int ct = 0;
		if(ListUtil.isNotEmpty(l)){
			for(Object obj : l){
				ct++;
				Map map = (Map)obj;
				String id = map.get("ID").toString();
				CustomImportProgram cip = new HrInitProgram(oldHrService,newHrService,oldHrTemplate,newHrTemplate,id);
				cip.runImport();
				System.out.println(" -- " + ct + "--");
			}
		}
		this.log.info(" 初始化树形目录代码成功 ");
		
		
		this.log.info(" ======================================= 考题大纲初始化树形目录代码[HR_EXAM_PROGRAM]表  完成 ============================");
		
		long endTime = new Date().getTime();
		Double useTime = (Double.parseDouble(String.valueOf(endTime))-Double.parseDouble(String.valueOf(beginTime)))/60000d;
		this.log.info("||||||||||||||||||||||      执行时间:" + new BigDecimal(useTime).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + " 分钟    ||||||||||||||||||||||");
	}
	
	
	@Test
	/**
	 * 初始化试题大纲树 - 节点类型
	 */
	public void testinitProgram2(){
		
		long beginTime = new Date().getTime();
		this.log.info(" ======================================= 考题大纲初始化-节点类型[HR_EXAM_PROGRAM]表   开始  ============================");
		
		String sql = "select PROGRAM_ID as ID,PROGRAM_CODE as CODE from HR_EXAM_PROGRAM ";
		List l = newHrTemplate.queryForList(sql);
		int ct = 0;
		if(ListUtil.isNotEmpty(l)){
			for(Object obj : l){
				ct++;
				System.out.println(" -- " + ct + "--");
				Map map = (Map)obj;
				String id = map.get("ID").toString();
				try{
					String code = map.get("CODE").toString();
					String sql_1  =  "select PROGRAM_ID as ID,PROGRAM_CODE from HR_EXAM_PROGRAM where PROGRAM_CODE like '" + code + ".%'";
					List l2  = newHrTemplate.queryForList(sql_1);
					if(!ListUtil.isNotEmpty(l2)){
						String update_sql = "update HR_EXAM_PROGRAM set IS_LEAF = '1' where PROGRAM_ID = '" + id + "'";
						newHrTemplate.update(update_sql);
					}else{
						String update_sql = "update HR_EXAM_PROGRAM set IS_LEAF = '0' where PROGRAM_ID = '" + id + "'";
						newHrTemplate.update(update_sql);
					}
				}catch(Exception e){
					this.log.info("[" + id + "]没有导入成功,原因：" + e.getMessage());
				}
				
				
				
				
			}
		}
		
		this.log.info(" ======================================= 考题大纲初始化-节点类型[HR_EXAM_PROGRAM]表  完成 ============================");
		
		long endTime = new Date().getTime();
		Double useTime = (Double.parseDouble(String.valueOf(endTime))-Double.parseDouble(String.valueOf(beginTime)))/60000d;
		this.log.info("||||||||||||||||||||||      执行时间:" + new BigDecimal(useTime).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + " 分钟    ||||||||||||||||||||||");
	}
	
	
	@Test
	/**
	 * 初始化试题
	 */
	public void testinitSubject(){
		
		long beginTime = new Date().getTime();
		this.log.info(" ======================================= 考题初始化[HR_EXAM_SUBJECT]表   开始  ============================");
		String set_valid_sql = "update HR_EXAM_SUBJECT set IS_VALID='1',CRE_USERID='init',CRE_NAME='init',PUB_STATUS='审核通过'";
		newHrTemplate.update(set_valid_sql);
		this.log.info(" 初始化有效字段完成 ");
		
		String update_subject_type1 = "update HR_EXAM_SUBJECT set SUB_TYPE='单选' where SUB_TYPE='2'";
		newHrTemplate.update(update_subject_type1);
		this.log.info(" 初始化单选题题型完成 ");
		String update_subject_type2 = "update HR_EXAM_SUBJECT set SUB_TYPE='多选' where SUB_TYPE='3'";
		newHrTemplate.update(update_subject_type2);
		this.log.info(" 初始化多选题题型完成 ");
		String update_subject_type3 = "update HR_EXAM_SUBJECT set SUB_TYPE='问答' where SUB_TYPE='4'";
		newHrTemplate.update(update_subject_type3);
		this.log.info(" 初始化问答题题型完成 ");
		
		
		this.log.info(" ======================================= 考题初始化[HR_EXAM_SUBJECT]表  完成 ============================");
		
		long endTime = new Date().getTime();
		Double useTime = (Double.parseDouble(String.valueOf(endTime))-Double.parseDouble(String.valueOf(beginTime)))/60000d;
		this.log.info("||||||||||||||||||||||      执行时间:" + new BigDecimal(useTime).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + " 分钟    ||||||||||||||||||||||");
	}
	
	@Test
	/**
	 * 初始化试题 - 补充大纲名称
	 */
	public void testinitSubject2(){
		
		long beginTime = new Date().getTime();
		this.log.info(" ======================================= 考题初始化 - 补充大纲名称[HR_EXAM_SUBJECT]表   开始  ============================");
		/*
		String sql = "select SUB_ID from HR_EXAM_SUBJECT ";
		List l = newHrService.query(sql);
		if(ListUtil.isNotEmpty(l)){
			for(Object obj: l){
				Map<String,Object> m = (Map<String,Object>)obj;
				String subId = m.get("SUB_ID").toString();
				System.out.println(subId);
				
				String proRela = " select to_char(wm_concat(KCNAME)) as MK from KNOWLEDGERESOURCE  r left join KNOWLEDGECATEGORY p on r.KCID = p.Kcid where RBID = " + subId;
				
			}
		}*/
		
		String updateSql = "update hr_exam_subject sub set sub.program_name = (select wm_concat(pram.program_name) from HR_EXAM_R_PROGRAM_SUBJECT r left join HR_EXAM_PROGRAM pram on r.program_id = pram.program_id where r.sub_id = sub.sub_id)";
		newHrService.execute(updateSql);
		this.log.info(" ======================================= 考题初始化 - 补充大纲名称[HR_EXAM_SUBJECT]表  完成 ============================");
		
		long endTime = new Date().getTime();
		Double useTime = (Double.parseDouble(String.valueOf(endTime))-Double.parseDouble(String.valueOf(beginTime)))/60000d;
		this.log.info("||||||||||||||||||||||      执行时间:" + new BigDecimal(useTime).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + " 分钟    ||||||||||||||||||||||");
	}
	
	@Test
	/**
	 * 初始化试题 - 题干编码
	 */
	public void testinitSubject3(){
		long beginTime = new Date().getTime();
		
		this.log.info(" ======================================= 考题初始化 - 题干编码[HR_EXAM_SUBJECT]表   开始  ============================");
	
		String sql = "select SUB_ID,SUB_TITLE from HR_EXAM_SUBJECT ";
		List l = newHrService.query(sql);
		long allCt = 0;
		long successCt = 0;
		long failureCt = 0;
		long i = 0;
		if(ListUtil.isNotEmpty(l)){
			this.log.info("应执行数量：" + l.size());
			for(Object obj: l){
				try{
					Map<String,Object> m = (Map<String,Object>)obj;
					String subId = m.get("SUB_ID").toString();
					String content = m.get("SUB_title") == null ? "" : m.get("SUB_title").toString();
					if(StringUtil.isNotEmpty(content)){
						String title = Base64Tool.decodeStr(content);
						String updateSql = "update HR_EXAM_SUBJECT set SUB_TITLE=? where SUB_ID = ?";
						List params = new ArrayList();
						params.add(title);
						params.add(subId);
						this.newHrService.executeByParams(updateSql, params.toArray());
					}
					successCt++;
				}catch(Exception e){
					this.log.error(e.getMessage());
					failureCt++;
				}
				System.out.println(" -- " + i++ + " --  ");
			}
		}
		this.log.info(" 应执行数量 ： " + l.size() + " 成功:" + successCt + "   失败:" + failureCt);
		
		String updateTitleHtml = "update hr_exam_subject set sub_title_text = sub_title";
		newHrService.execute(updateTitleHtml);
		this.log.info(" 题干富文本内容初始化成功  ");
		
		this.log.info(" ======================================= 考题初始化 - 题干编码[HR_EXAM_SUBJECT]表  完成 ============================");
		
		long endTime = new Date().getTime();
		Double useTime = (Double.parseDouble(String.valueOf(endTime))-Double.parseDouble(String.valueOf(beginTime)))/60000d;
		this.log.info("||||||||||||||||||||||      执行时间:" + new BigDecimal(useTime).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + " 分钟    ||||||||||||||||||||||");
	}
	@Test
	/**
	 * 选项初始化 - 有效值
	 */
	public void testinitOption(){
		long beginTime = new Date().getTime();
		
		this.log.info(" ======================================= 选项初始化 - 有效值[HR_EXAM_OPTIONS]表   开始  ============================");
		String updateValid = "update hr_exam_options set  IS_VALID='1',CRE_USERID='init',CRE_NAME='init'";
		newHrService.execute(updateValid);
		this.log.info(" 选项初始化 - 有效值初始化成功  ");
		this.log.info(" ======================================= 选项初始化 - 有效值[HR_EXAM_OPTIONS]表  完成 ============================");
		
		long endTime = new Date().getTime();
		Double useTime = (Double.parseDouble(String.valueOf(endTime))-Double.parseDouble(String.valueOf(beginTime)))/60000d;
		this.log.info("||||||||||||||||||||||      执行时间:" + new BigDecimal(useTime).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + " 分钟    ||||||||||||||||||||||");
	}
	@Test
	/**
	 * 选项初始化 - 选项内容 
	 */
	public void testinitOption1(){
		long beginTime = new Date().getTime();
		
		this.log.info(" ======================================= 选项初始化 - 选项内容[HR_EXAM_OPTIONS]表   开始  ============================");
	
		String sql = "select OPT_ID,to_char(OPTION_CONTENT) as OPT_CONTENT from HR_EXAM_OPTIONS ";
		List l = newHrService.query(sql);
		long allCt = 0;
		long successCt = 0;
		long failureCt = 0;
		long i = 0;
		if(ListUtil.isNotEmpty(l)){
			this.log.info("应执行数量：" + l.size());
			for(Object obj: l){
				try{
					Map<String,Object> m = (Map<String,Object>)obj;
					String optId = m.get("OPT_ID").toString();
					String content = m.get("OPT_CONTENT") == null ? "" : m.get("OPT_CONTENT").toString();
					if(StringUtil.isNotEmpty(content)){
						String title = Base64Tool.decodeStr(content);
						String updateSql = "update HR_EXAM_OPTIONS set OPTION_CONTENT=? where OPT_ID = ?";
						List params = new ArrayList();
						params.add(title);
						params.add(optId);
						this.newHrService.executeByParams(updateSql, params.toArray());
					}
					successCt++;
				}catch(Exception e){
					this.log.error(e.getMessage());
					failureCt++;
				}
				System.out.println(" -- " + i++ + " --  ");
			}
		}
		this.log.info(" 应执行数量 ： " + l.size() + " 成功:" + successCt + "   失败:" + failureCt);
		
		
		this.log.info(" ======================================= 选项初始化 - 选项内容[HR_EXAM_OPTIONS]表  完成 ============================");
		
		long endTime = new Date().getTime();
		Double useTime = (Double.parseDouble(String.valueOf(endTime))-Double.parseDouble(String.valueOf(beginTime)))/60000d;
		this.log.info("||||||||||||||||||||||      执行时间:" + new BigDecimal(useTime).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + " 分钟    ||||||||||||||||||||||");
	}
	
	
	
}
