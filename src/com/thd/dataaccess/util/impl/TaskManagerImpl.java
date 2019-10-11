package com.thd.dataaccess.util.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.thd.dataaccess.model.Column;
import com.thd.dataaccess.model.Task;
import com.thd.dataaccess.util.TaskManager;
import com.thd.dataaccess.util.TransformModel;

public class TaskManagerImpl implements TaskManager {
	private Logger log = Logger.getLogger(this.getClass());
	public String taskToQuerySql(Task task) throws Exception{
		if(task == null){
			throw new Exception(" task must not be null");
		}
		
		if(task.getCustomSql()!= null && !task.getCustomSql().trim().equals("")){
			return task.getCustomSql().trim();
		}else{
		
			StringBuffer executeSql = new StringBuffer("");
			executeSql.append("select ");
			for(Column c : task.getColumns()){
				if(c.getSourceColumn() != null && !c.getSourceColumn().trim().equals("")){
					executeSql.append(c.getSourceColumn() + " as " + c.getAs() + ",");
				}
			}
			//为了处理select xx,xx,xx,最后一个逗号
			String tempStr = executeSql.toString();
			tempStr = tempStr.substring(0, tempStr.length() - 1);
			executeSql = new StringBuffer(tempStr);
			executeSql.append(" from " + task.getSourceTable());
			
			if(task.getWhere() != null && !task.getWhere().equals("")){
				executeSql.append( " where " + task.getWhere().trim());
			}
			//this.log.info(executeSql.toString());
			return executeSql.toString();
		}
	};
	
	
	public String taskCheckTargetColumnSql(Task task) throws Exception{
		if(task == null){
			throw new Exception(" task must not be null");
		}
		StringBuffer executeSql = new StringBuffer("");
		executeSql.append("select ");
		for(Column c : task.getColumns()){
			if(c.getSourceColumn() != null && !c.getSourceColumn().trim().equals("")){
				executeSql.append(c.getTargetColumn() + ",");
			}
		}
		//为了处理select xx,xx,xx,最后一个逗号
		String tempStr = executeSql.toString();
		tempStr = tempStr.substring(0, tempStr.length() - 1);
		executeSql = new StringBuffer(tempStr);
		executeSql.append(" from " + task.getTargetTable());
		
		//this.log.info(executeSql.toString());
		return executeSql.toString();
	};
	
	public String taskToTargetSqlForInsert(Task task) throws Exception{
		if(task == null){
			throw new Exception(" task must not be null");
		}
		
		StringBuffer executeSql = new StringBuffer("");
		// insert into xx values (...) 中的...
		StringBuffer values = new StringBuffer("");
		executeSql.append("insert into " + task.getTargetTable() + "(");
		for(Column c : task.getColumns()){
			executeSql.append(c.getTargetColumn() + ",");
			values.append("?,");
		}
		//为了处理select xx,xx,xx,最后一个逗号
		String tempStr = executeSql.toString();
		tempStr = tempStr.substring(0, tempStr.length() - 1);
		executeSql = new StringBuffer(tempStr);
		
		executeSql.append(") values (" + values.toString().substring(0,values.length() - 1) + ")");
		
		//this.log.info(executeSql.toString());
		return executeSql.toString();
	};
	
	
	public String taskToTargetSqlForUpdate(Task task)  throws Exception{
		if(task == null){
			throw new Exception(" task must not be null");
		}
		StringBuffer executeSql = new StringBuffer("");
		// insert into xx values (...) 中的...
		StringBuffer values = new StringBuffer("");
		executeSql.append("update " + task.getTargetTable() + " set ");
		for(Column c : task.getColumns()){
			executeSql.append(c.getTargetColumn() + "=?,");
		}
		//为了处理select xx,xx,xx,最后一个逗号
		String tempStr = executeSql.toString();
		tempStr = tempStr.substring(0, tempStr.length() - 1);
		executeSql = new StringBuffer(tempStr);
		
		executeSql.append(" where " );
		
		for(Column c : task.getColumns()){
			if(c.getKey() != null && c.getKey()){
				executeSql.append(" " + c.getTargetColumn() + "=? and" );
			}
		}
		
		//处理最后一个 and
		tempStr = executeSql.toString();
		tempStr = tempStr.substring(0, tempStr.length() - 3);
		executeSql = new StringBuffer(tempStr);
		
		//this.log.info(executeSql.toString());
		return executeSql.toString();
	};
	
	
	
	
	public String executeTask(Task task)  throws Exception{
		
		this.log.info("==================== 导入[" + task.getTargetTable() + "]开始 ==================");
		long beginTime = new Date().getTime();
		//验证目标表和字段是否存在
		String checkTargetColumnSql = this.taskCheckTargetColumnSql(task);
		try{
			task.getTargetService().query(checkTargetColumnSql);
		
		
			String querySql = this.taskToQuerySql(task);
			
			
			
			String insertSql = this.taskToTargetSqlForInsert(task);
			String updateSql = this.taskToTargetSqlForUpdate(task);
			//this.log.info(querySql);
			
			List<Map<String,Object>> results = task.getSourceService().query(querySql);
			
			this.log.info("query count : " + results.size());
			//处理查询记录数量
			int count = 0;
			//查询结果数量
			int allCount = results.size();
			//插入成功记录数量
			int insertSuccessCount = 0;
			//更新成功记录数量
			int updateSuccessCount = 0;
			//插入失败记录数量
			int insertFailureCount = 0;
			//更新失败记录数量
			int updateFailureCount = 0;
			List params = new ArrayList();
	
			//清目标表
			if(task.getClear()){
				String deleteSql = "delete from " + task.getTargetTable();
				task.getTargetService().execute(deleteSql);
			}
			
			if(results != null && results.size() > 0){
				
				for(Map<String,Object> result : results){
					
					count++;
					try{
						params.clear();
						
						//匹配insert语句中的 values(?,?,?)中的"?"对应值数组    或  update语句where关键词之前的"?"对应值的数组
						for(Column c : task.getColumns()){
							Object v = null;
							//主键自动生成的情况
							if( (c.getIsPrimarykey() != null && c.getIsPrimarykey()) && (c.getSourceColumn()== null || c.getSourceColumn().equals(""))){
								v = UUID.randomUUID().toString().trim().replace("-", "");
							}else{
								//v = result.get(c.getSourceColumn());
								v = result.get(c.getAs());
							}
							params.add(v);
						}
						
						//========================== 如果导入类型是 INSERT ========================== 
						if(task.getImportType().equals("INSERT")){
							//this.log.info(insertSql);
							try{
								task.getTargetService().executeByParams(insertSql, params.toArray());
								insertSuccessCount++;
							}catch(Exception e){
								insertFailureCount++;
								this.log.error("[" + result + "] err :" + e.getMessage());
							}
							
						// ========================== 如果导入类型是  UPDATE ==========================
						}else if(task.getImportType().equals("UPDATE")){
							int keyCount = 0;
							//更新后面的where关键词之后的"?"对应值的数组
							for(Column c : task.getColumns()){
								Object v = null;
								//主键自动生成的情况
								if(c.getKey()!= null && c.getKey()){
									v = result.get(c.getSourceColumn());
									params.add(v);
									keyCount++;
								}
							}
							if(keyCount < 1){
								throw new Exception("please set column's key");
							}
							
							//this.log.info(updateSql);
							try{
								task.getTargetService().executeByParams(updateSql, params.toArray());
								updateSuccessCount++;
							}catch(Exception e){
								updateFailureCount++;
								this.log.error("[" + result + "] err :" + e.getMessage());
							}
							
							
						// ========================== 如果导入类型是 INSERTORUPDATE ========================== 
						}else{
							//是否存在某记录的查询sql语句where后面的"?"的值数组
							List existSqlParams = new ArrayList();
							
							String existSql = "select count(*) from " + task.getTargetTable() + " where ";
							for(Column c : task.getColumns()){
								Object v = null;
								//主键自动生成的情况
								if(c.getKey()!= null && c.getKey()){
									existSql += (" " + c.getTargetColumn() + "=? and ");
									v = result.get(c.getSourceColumn());
									existSqlParams.add(v);
								}
							}
							
							
							if(existSqlParams.size() < 1){
								throw new Exception("please set column's key");
							}
							
							//去除最后一个"and"
							existSql = existSql.substring(0,existSql.length() - 4);
							
							
							long rcount = task.getTargetService().getJdbcTemplate().queryForLong(existSql, existSqlParams.toArray());
							//---------------------存在记录---------------------
							if(rcount > 0){
								//更新后面的where关键词之后的"?"对应值的数组
								for(Column c : task.getColumns()){
									Object v = null;
									//主键自动生成的情况
									if(c.getKey()!= null && c.getKey()){
										v = result.get(c.getSourceColumn());
										params.add(v);
									}
								}
								//this.log.info(updateSql);
								
								
								try{
									task.getTargetService().executeByParams(updateSql, params.toArray());
									updateSuccessCount++;
								}catch(Exception e){
									updateFailureCount++;
									this.log.error("[" + result + "] err :" + e.getMessage());
								}
							//---------------------不存在记录---------------------
							}else{
								//this.log.info(insertSql);
								try{
									task.getTargetService().executeByParams(insertSql, params.toArray());
									insertSuccessCount++;
								}catch(Exception e){
									insertFailureCount++;
									this.log.error("[" + result + "] err :" + e.getMessage());
								}
								
							}
						}
						System.out.println( count );
					}catch(Exception e){
						/*String errColumnStr = "";
						if(task.getMarkList().size() > 0){
							for(Column errColumn : task.getMarkList()){
								errColumnStr += result.get(errColumn.getSourceColumn());
							}
						}
						*/
						this.log.error("[" + result + "] err :" + e.getMessage());
					}
					
					
				}
			}
			long endTime = new Date().getTime();
			Double useTime = (Double.parseDouble(String.valueOf(endTime))-Double.parseDouble(String.valueOf(beginTime)))/60000d;
			this.log.info("||||||||||||||||||||||  从[" +task.getSourceTable() + "]导入[" + task.getTargetTable() + "]结束     执行时间:" + new BigDecimal(useTime).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue() + " 分钟    ||||||||||||||||||||||");
			this.log.info("应操作数据[" + allCount + "] 插入成功[" + insertSuccessCount + "] 插入失败 [" + insertFailureCount + "] 更新成功 [" + updateSuccessCount + "] 更新失败[ " + updateFailureCount+ "] ");
		}catch(Exception e){
			this.log.error("从[" +task.getSourceTable() + "]导入[" + task.getTargetTable() + "]失败 原因:" + e.getMessage());
		}
		
		return null;
	};
	
	public void runATask(String xmlPath,String id) throws Exception{
		try{
			File f = new File(xmlPath);
			if(f == null){
				throw new Exception(xmlPath + " not be found ");
			}
			if(f.isDirectory()){
				throw new Exception(xmlPath + " not a file");
			}
			InputStream is = new FileInputStream(f);
			TransformModel tm = new TransformModelImpl();
			
			Map<String,Task> m = tm.transformTaskToMap(is);
			
			if(m.get(id) != null){
				this.executeTask(m.get(id));
			}else{
				throw new Exception("task[" + id + "] can't be found ");
			}
			
		}catch(Exception e){
			throw e;
		}
		
	}
}
