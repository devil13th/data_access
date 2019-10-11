package com.thd.dataaccess.model;

import java.util.ArrayList;
import java.util.List;

import com.thd.dataaccess.service.DBService;

public class Task {
	//id
	private String id;
	//源表service
	private DBService sourceService;
	//目标表service
	private DBService targetService;
	//源表目标表的字段明对应
	private List<Column> columns = new ArrayList<Column>();
	//源表表名
	private String sourceTable;
	//目标表表名
	private String targetTable;
	//源表筛选条件
	private String where;
	//是否有条件更新
	private Boolean isUpdate = false ;
	//导入类型
	private String importType = "SAVE"; 
	//标记字段
	private List<Column> markList = new ArrayList<Column>();
	//是否清表
	private Boolean clear = false;
	//自定义sql
	private String customSql = "";
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getImportType() {
		return importType;
	}
	public void setImportType(String importType) {
		this.importType = importType;
	}
	public DBService getSourceService() {
		return sourceService;
	}
	public void setSourceService(DBService sourceService) {
		this.sourceService = sourceService;
	}
	public DBService getTargetService() {
		return targetService;
	}
	public void setTargetService(DBService targetService) {
		this.targetService = targetService;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public String getSourceTable() {
		return sourceTable;
	}
	public void setSourceTable(String sourceTable) {
		this.sourceTable = sourceTable;
	}
	public String getTargetTable() {
		return targetTable;
	}
	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public Boolean getIsUpdate() {
		return isUpdate;
	}
	public void setIsUpdate(Boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
	public Boolean getClear() {
		return clear;
	}
	public void setClear(Boolean clear) {
		this.clear = clear;
	}
	public List<Column> getMarkList() {
		return markList;
	}
	public void setMarkList(List<Column> markList) {
		this.markList = markList;
	}
	public String getCustomSql() {
		return customSql;
	}
	public void setCustomSql(String customSql) {
		this.customSql = customSql;
	}
	
	
}
