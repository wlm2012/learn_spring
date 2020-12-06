package com.test.study.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		entityManagerFactoryRef = "secondEntityManagerFactory",
		transactionManagerRef = "secondTransactionManager",
		basePackages = {"com.test.study.secondMapper"}
)
public class secondConfig {


	private Environment env;

	private DataSource dataSource;

	@Autowired
	public void setEnvironment(Environment env) {
		this.env = env;
	}

	@Autowired
	@Qualifier("secondDataSource")
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Primary
	@Bean(name = "secondEntityManager")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return Objects.requireNonNull(secondEntityManagerFactory(builder).getObject()).createEntityManager();
	}

	@Primary
	@Bean(name = "secondEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean secondEntityManagerFactory(EntityManagerFactoryBuilder builder) {
		return builder
				.dataSource(dataSource)
				.properties(getVendorProperties())
				.packages("com.test.study.entity")
				.persistenceUnit("secondPersistenceUnit")
				.build();
	}


	private Map<String, String> getVendorProperties() {
		Map<String, String> jpaProperties = new HashMap<>(16);
		jpaProperties.put("hibernate.hbm2ddl.auto", "update");
		jpaProperties.put("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		jpaProperties.put("hibernate.format_sql", env.getProperty("spring.jpa.hibernate.format_sql"));
		jpaProperties.put("hibernate.hbm2ddl.auto",env.getProperty("spring.jpa.hibernate.ddl-auto"));
//		jpaProperties.put("hibernate.dialect", env.getProperty("spring.jpa.hibernate.primary-dialect"));
		jpaProperties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
		return jpaProperties;
	}

	@Primary
	@Bean(name = "secondTransactionManager")
	public PlatformTransactionManager secondTransactionManager(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(Objects.requireNonNull(secondEntityManagerFactory(builder).getObject()));
	}
}


