package com.thd.dataaccess.model;

public class Column {
	//目标表字段名
	private String targetColumn;
	//源表字段名
	private String sourceColumn;
	//作为导入条件新增还是编辑的依据
	private Boolean key = false;
	//目标表字段类型
	private String targetColumnDataType;
	//源表字段类型
	private String sourceColumnDataType;
	//是否是主键
	private Boolean isPrimarykey;
	//主键生成策略
	private String generator;
	//异常报错标记
	private Boolean mark = false;
	//as字段
	private String as;
	
	
	public String getGenerator() {
		return generator;
	}
	
	public void setGenerator(String generator) {
		this.generator = generator;
	}
	public Boolean getIsPrimarykey() {
		return isPrimarykey;
	}
	public void setIsPrimarykey(Boolean isPrimarykey) {
		this.isPrimarykey = isPrimarykey;
	}
	public String getTargetColumn() {
		return targetColumn;
	}
	public void setTargetColumn(String targetColumn) {
		this.targetColumn = targetColumn;
	}
	public String getSourceColumn() {
		return sourceColumn;
	}
	public void setSourceColumn(String sourceColumn) {
		this.sourceColumn = sourceColumn;
	}
	public Boolean getKey() {
		return key;
	}
	public void setKey(Boolean key) {
		this.key = key;
	}
	public String getTargetColumnDataType() {
		return targetColumnDataType;
	}
	public void setTargetColumnDataType(String targetColumnDataType) {
		this.targetColumnDataType = targetColumnDataType;
	}
	public String getSourceColumnDataType() {
		return sourceColumnDataType;
	}
	public void setSourceColumnDataType(String sourceColumnDataType) {
		this.sourceColumnDataType = sourceColumnDataType;
	}

	public Boolean getMark() {
		return mark;
	}

	public void setMark(Boolean mark) {
		this.mark = mark;
	}

	public String getAs() {
		return as;
	}

	public void setAs(String as) {
		this.as = as;
	}

	
	
}
