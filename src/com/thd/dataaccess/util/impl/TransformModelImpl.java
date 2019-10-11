package com.thd.dataaccess.util.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.thd.dataaccess.model.Column;
import com.thd.dataaccess.model.Task;
import com.thd.dataaccess.model.TaskCollection;
import com.thd.dataaccess.service.DBService;
import com.thd.dataaccess.util.TransformModel;

public class TransformModelImpl implements TransformModel {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	
	public TaskCollection transformTaskToTaskCollection(InputStream is) throws Exception{
		TaskCollection taskCollection = new TaskCollection();
		
		
		if(is == null){
			throw new Exception("input stream must be not null");
		}
		
		Map<String,Task> taskMap = new HashMap<String,Task>();
		List<Task> taskList = new ArrayList<Task>();
		SAXBuilder builder = new SAXBuilder();
		Document doc = builder.build(is);
		//根目录
		Element root = doc.getRootElement();
		
		String[] appContext = new String[]{"classpath:conf/appContext.xml"};
		ApplicationContext factory=new ClassPathXmlApplicationContext(appContext); 
		
		//获取源表service
		Element sourceServiceEle = root.getChild("source_service");
		if(sourceServiceEle == null){
			throw new Exception("source_service must be not null");
		}
		DBService sourceService = (DBService)factory.getBean(sourceServiceEle.getValue());
		
		
		//获取目标表service
		Element targetServiceEle = root.getChild("target_service");
		if(targetServiceEle == null){
			throw new Exception("target_service must be not null");
		}
		DBService targetService = (DBService)factory.getBean(targetServiceEle.getValue());
		
		
		//获取tasklist
		Element taskListEle = root.getChild("tasklist");
		if(taskListEle == null){
			throw new Exception("tasklist must be not null");
		}
		
		//获取task列表
		List<Element> taskEles = taskListEle.getChildren("task");
		if(taskEles == null || taskEles.size() < 1){
			throw new Exception("must has a task at least");
		}
		
		for(Element aTask : taskEles){
			Task task = new Task();
			
			String id = aTask.getAttributeValue("id");
			if(id == null || id.trim().equals("")){
				throw new Exception("must has id ");
			}
			
			if(taskMap.get(id) != null){
				throw new Exception("task[" + id + "] be duplicate defined ");
			}
			
			task.setId(id);
			//源表service
			task.setSourceService(sourceService);
			
			//目标表service
			task.setTargetService(targetService);
			
			
			
			//源表表名
			Element sourceTableEle = aTask.getChild("source_table");
			if(sourceTableEle == null || sourceTableEle.getValue() == null || sourceTableEle.getValue().trim().equals("")){
				throw new Exception("must has source_table");
			}
			task.setSourceTable(sourceTableEle.getValue().trim());
			
			//目标表表名
			Element targetTableEle = aTask.getChild("target_table");
			if(targetTableEle == null || targetTableEle.getValue() == null || targetTableEle.getValue().trim().equals("")){
				throw new Exception("must has target_table");
			}
			task.setTargetTable(targetTableEle.getValue().trim());
			
			
			//导入类型
			Element importTypeEle = aTask.getChild("import_type");
			if(importTypeEle != null && importTypeEle.getValue()!= null && !importTypeEle.getValue().trim().equals("")){
				task.setImportType(importTypeEle.getValue().trim().toUpperCase());
			}else{
				task.setImportType("SAVE");
			}
			
			
			//导入前是否清表
			Element clearEle = aTask.getChild("clear");
			if(clearEle != null && clearEle.getValue()!= null && clearEle.getValue().trim().toUpperCase().equals("TRUE")){
				task.setClear(true);
			}
			
			//自定义sql
			Element customSqlEle = aTask.getChild("custom_sql");
			if(customSqlEle != null && customSqlEle.getValue()!= null){
				task.setCustomSql(customSqlEle.getValue().trim());
			}
			
			//字段集合
			Element columnList = aTask.getChild("columns");
			if(columnList == null){
				throw new Exception("must has columns");
			}
			
			List<Element> columns = columnList.getChildren("column");
			if(columns == null || columns.size() < 1){
				throw new Exception("must has a column at least");
			}
			
			for(Element aColumn : columns){
				Column c = new Column();
				
				
				
				//是否是标记字段
				String key = aColumn.getAttributeValue("key");
				if(key !=null && key.trim().toUpperCase().equals("TRUE")){
					c.setKey(true);
					task.setIsUpdate(true);
				}
				
				//是否是报错标记字段
				String mark = aColumn.getAttributeValue("mark");
				if(mark != null && mark.trim().toUpperCase().equals("TRUE")){
					c.setMark(true);
					task.getMarkList().add(c);
				}
				
				//是否主键
				String isPrimarykey = aColumn.getAttributeValue("primarykey");
				if(isPrimarykey !=null && isPrimarykey.trim().toUpperCase().equals("TRUE")){
					c.setIsPrimarykey(true);
					
					//主键生成策略
					String generator = aColumn.getAttributeValue("generator");
					if(generator !=null && !generator.trim().equals("")){
						if(!generator.trim().toUpperCase().equals("UUID") && !generator.trim().toUpperCase().equals("COPY")){
							throw new Exception("can't be support generator");
						}
						c.setGenerator(generator.trim().toUpperCase());
					}
				}
				
				
				
				
				//源表字段名
				String sourceColumn = aColumn.getAttributeValue("source");
				c.setSourceColumn(sourceColumn);
				
				if(c.getIsPrimarykey() != null && c.getIsPrimarykey()){
					//如果是主键 且源表字段名不为空则必须设置主键生成策略是COPY
					if(c.getSourceColumn()!= null && !c.getSourceColumn().trim().equals("")){
						if(!c.getGenerator().equals("COPY")){
							throw new Exception(" generator must be 'COPY' ");
						}
					}else{
						if(c.getGenerator().equals("COPY")){
							throw new Exception(" generator must can't be 'COPY' ");
						}
					}
					
					
				}else{
					//不是主键  必须要有源表字段名
					if(c.getSourceColumn() == null || c.getSourceColumn().trim().equals("")){
						throw new Exception("must be set source column");
					}
				}
				
				
				//目标表字段名
				String targetColumn = aColumn.getAttributeValue("target");
				if(targetColumn == null || targetColumn.trim().equals("")){
					throw new Exception("must be set target column");
				}
				c.setTargetColumn(targetColumn);
				
				//as字段
				String sourceColumnAs = aColumn.getAttributeValue("as");
				if(sourceColumnAs == null || sourceColumnAs.trim().equals("")){
					c.setAs(c.getSourceColumn());
				}else{
					c.setAs(sourceColumnAs.trim());
				}
				
				
				
				c.setSourceColumnDataType(aColumn.getAttributeValue("target_type"));
				c.setSourceColumnDataType(aColumn.getAttributeValue("source_type"));
				task.getColumns().add(c);
				
				
				
			}
			
			//where 语句
			Element whereEle = aTask.getChild("where");
			
			if(whereEle != null && !whereEle.getValue().trim().equals("")){
				task.setWhere(whereEle.getValue().trim());
			}
			
			taskMap.put(task.getId(), task);
			taskList.add(task);
		}
		
		taskCollection.setTaskList(taskList);
		taskCollection.setTaskMap(taskMap);
		return taskCollection;
	};
	
	public List<Task> transformTaskToList(InputStream is) throws Exception{
		return transformTaskToTaskCollection(is).getTaskList();
	};
	
	public Map<String,Task> transformTaskToMap(InputStream is) throws Exception{
		return transformTaskToTaskCollection(is).getTaskMap();
	}
	
	
	

}
