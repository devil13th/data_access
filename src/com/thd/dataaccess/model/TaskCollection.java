package com.thd.dataaccess.model;

import java.util.List;
import java.util.Map;

public class TaskCollection {
	private Map<String,Task> taskMap;
	private List<Task> taskList;
	public Map<String, Task> getTaskMap() {
		return taskMap;
	}
	public void setTaskMap(Map<String, Task> taskMap) {
		this.taskMap = taskMap;
	}
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
}
