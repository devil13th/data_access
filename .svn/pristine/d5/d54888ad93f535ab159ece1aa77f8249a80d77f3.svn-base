package com.thd.dataaccess.custom;

import org.springframework.jdbc.core.JdbcTemplate;

import com.thd.dataaccess.service.DBService;

abstract public class CustomImportProgramAbsImpl implements CustomImportProgram {
	
	private DBService sourceService;
	private DBService targetService;
	private JdbcTemplate sourceTemplate;
	private JdbcTemplate targetTemplate;
	public CustomImportProgramAbsImpl(DBService sourceService,DBService targetService,JdbcTemplate sourceTemplate,JdbcTemplate targetTemplate){
		this.sourceService = sourceService;
		this.targetService = targetService;
		this.sourceTemplate = sourceTemplate;
		this.targetTemplate = targetTemplate;
	}
	
	abstract public void runImport();

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

	public JdbcTemplate getSourceTemplate() {
		return sourceTemplate;
	}

	public void setSourceTemplate(JdbcTemplate sourceTemplate) {
		this.sourceTemplate = sourceTemplate;
	}

	public JdbcTemplate getTargetTemplate() {
		return targetTemplate;
	}

	public void setTargetTemplate(JdbcTemplate targetTemplate) {
		this.targetTemplate = targetTemplate;
	}

}
