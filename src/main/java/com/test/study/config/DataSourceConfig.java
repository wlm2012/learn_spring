package com.test.study.config;


import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.annotation.Resource;
import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {


	@ConfigurationProperties("spring.datasource.primary")
	@Bean
	@Primary
	public DataSource primaryDataSource() {
		DataSource dataSource= DataSourceBuilder.create().type(HikariDataSource.class).build();
		return dataSource;
	}


/*	@Bean
	@Resource
	@Primary
	public PlatformTransactionManager primaryPlatformTransactionManager() {
		return new DataSourceTransactionManager(primaryDataSource());
	}*/

	@Bean
	@Resource
	@Primary
	@Qualifier("transactionManager")
	public TransactionManager primaryTransactionManager() {
		return new DataSourceTransactionManager(primaryDataSource());
	}

	@Bean
	@Primary
	@Resource
	public JdbcTemplate primaryJdbcTemplate() {
		return new JdbcTemplate(primaryDataSource());
	}


	@ConfigurationProperties("spring.datasource.second")
	@Bean
	public DataSource secondDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@Bean
	@Resource
	public PlatformTransactionManager secondTransactionManager() {
		return new DataSourceTransactionManager(secondDataSource());
	}

	@Bean
	@Resource
	public JdbcTemplate secondJdbcTemplate() {
		return new JdbcTemplate(secondDataSource());
	}

}
