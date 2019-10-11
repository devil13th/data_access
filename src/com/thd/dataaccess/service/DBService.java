package com.thd.dataaccess.service;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

public interface DBService {
	/**
	 * 执行sql
	 * @param sql 执行的sql语句
	 */
	public void execute(String sql);
	
	/**
	 * 执行sql
	 * @param sql 执行的sql语句
	 * @param params 取代sql中的?
	 */
	public void executeByParams(String sql,Object[] params);
	
	/**
	 * 查询l
	 * @param sql 查询的sql语句
	 */
	public List<Map<String,Object>> query(String sql);
	
	
	/**
	 * 查询l
	 * @param sql 查询的sql语句
	 * @param params 取代sql中的?
	 */
	public List<Map<String,Object>> queryByParams(String sql,Object[] params);
	
	
	public JdbcTemplate getJdbcTemplate();
}
