package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thd.dataaccess.service.DBService;

public class importByFileTest extends TestCase{
	private DBService oracleService;
	private String encoding = "UTF-8";
	private Logger log = Logger.getLogger(this.getClass());
	
	@Before
	public void setUp(){
		String[] appContext = new String[]{"classpath:conf/appContext.xml"};
		ApplicationContext factory=new ClassPathXmlApplicationContext(appContext); 
		oracleService = (DBService)factory.getBean("oracle2Service");
	}
	
	@Test
	public void testImportFile(){
		try{
			String file = "E://thdsvn//java_project//data_access//dbfile//test_deleteme.tab";
			InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");//考虑到编码格式
	        BufferedReader bufferedReader = new BufferedReader(read);
	        StringBuffer sql = new StringBuffer("");
	        String lineTxt = null;
	        while((lineTxt = bufferedReader.readLine()) != null){
	            //System.out.println(lineTxt);
	            sql.append(lineTxt+"\n");
	        }
	        read.close();
	        System.out.println(sql.toString());
	       	System.out.println("finish");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testImportFolder(){
		try{
			String folderPath = "E://thdsvn//java_project//data_access//dbfile";
			File folder = new File(folderPath);
			if(folder.isDirectory()){
				File[] files = folder.listFiles();
				StringBuffer sql = new StringBuffer("");
				for(File file : files){
					InputStreamReader read = new InputStreamReader(new FileInputStream(file),"GBK");//考虑到编码格式
			        BufferedReader bufferedReader = new BufferedReader(read);
			        
			        String lineTxt = null;
			        while((lineTxt = bufferedReader.readLine()) != null){
			            //System.out.println(lineTxt);
			            sql.append(lineTxt+"\n");
			        }
			        read.close();
			        System.out.println(sql.toString());
				}
				
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(new File("D:\\sql.txt")));     
				writer.write(sql.toString());          
				writer.close();
				
			}else{
				throw new Exception("不是文件夹");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
}
