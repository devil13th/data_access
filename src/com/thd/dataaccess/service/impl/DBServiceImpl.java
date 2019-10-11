package com.thd.dataaccess.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.thd.dataaccess.service.DBService;

public class DBServiceImpl implements DBService{
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void execute(String sql) {
		jdbcTemplate.execute(sql);
	}
	
	@Override
	public void executeByParams(String sql,Object[] obj) {
		jdbcTemplate.update(sql,obj);
	}

	@Override
	public List<Map<String,Object>> query(String sql) {
		return jdbcTemplate.queryForList(sql);
	}
	@Override
	public List<Map<String,Object>> queryByParams(String sql,Object[] obj) {
		return jdbcTemplate.queryForList(sql,obj);
	}

}
