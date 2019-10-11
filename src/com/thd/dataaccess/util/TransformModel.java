package com.thd.dataaccess.util;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import com.thd.dataaccess.model.Task;
import com.thd.dataaccess.model.TaskCollection;
/**
 * 数据源转换成Task工具
 * @author ThirdteenDevil
 *
 */
public interface TransformModel {
	
	
	/**
	 * 将输入流转换成TaskCollection对象
	 * @param is 输入流
	 * @return TaskCollection对象集合
	 */
	public TaskCollection transformTaskToTaskCollection(InputStream is) throws Exception;
	
	/**
	 * 将输入流转换成Task的Map对象
	 * @param is 输入流
	 * @return Task对象集合
	 */
	public Map<String,Task> transformTaskToMap(InputStream is) throws Exception;
	
	/**
	 * 将输入流转换成Task的List对象
	 * @param is 输入流
	 * @return List对象集合
	 */
	public List<Task> transformTaskToList(InputStream is) throws Exception;
	
}
