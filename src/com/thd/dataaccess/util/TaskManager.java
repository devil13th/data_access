package com.thd.dataaccess.util;

import com.thd.dataaccess.model.Task;

/**
 * 任务管理器
 * @author ThirdteenDevil
 *
 */
public interface TaskManager {
	/**
	 * Task对象转换成查询源表sql
	 * @param task
	 * @return sql
	 */
	public String taskToQuerySql(Task task)  throws Exception;
	/**
	 * Task对象转换成目标表 insert sql
	 * @param task
	 * @return sql
	 */
	public String taskToTargetSqlForInsert(Task task)  throws Exception;
	/**
	 * Task对象转换成目标表 update sql
	 * 根据column 中key属性为true的字段为基础如果存在和源表字段值匹配的则更新
	 * @param task
	 * @return sql
	 */
	public String taskToTargetSqlForUpdate(Task task)  throws Exception;
	/**
	 * 执行一个任务
	 * @param task Task对象
	 */
	public String executeTask(Task task)  throws Exception;
	/**
	 * 加载xml并运行一个任务
	 * @param xmlPath xml路径
	 * @param id 任务id
	 * @throws Exception
	 */
	public void runATask(String xmlPath,String id) throws Exception;
}
