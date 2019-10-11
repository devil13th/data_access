package com.thd.dataaccess.dao;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;

public class DaoImpl implements Dao{
	@Resource
	private JdbcTemplate jdbcTemplate;

	public void executeSql(String sql){
		jdbcTemplate.execute(sql);
	};
}
