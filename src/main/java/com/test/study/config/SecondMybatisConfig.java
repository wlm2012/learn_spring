package com.test.study.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author wlm
 */
@Configuration
@MapperScan(basePackages = "com.test.study.secondMapper", sqlSessionFactoryRef = "secondSqlSessionFactory")
public class SecondMybatisConfig {

	@Bean("secondSqlSessionTemplate")
	@Primary
	public SqlSessionTemplate mainSqlSessionTemplate(
			@Qualifier("secondSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean("secondSqlSessionFactory")
	@Primary
	public SqlSessionFactory sqlSessionFactory(@Qualifier("secondDataSource") DataSource dataSource, @Qualifier("secondPaginationInterceptor") MybatisPlusInterceptor paginationInnerInterceptor) throws Exception {
		MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/test/study/secondMapper/*.xml"));
		Interceptor[] plugins = new Interceptor[]{paginationInnerInterceptor};
		sqlSessionFactoryBean.setPlugins(plugins);
		return sqlSessionFactoryBean.getObject();
	}

	@Bean("secondPaginationInterceptor")
	public MybatisPlusInterceptor mybatisPlusInterceptor() {
		return new MybatisPlusInterceptor();
	}
}
