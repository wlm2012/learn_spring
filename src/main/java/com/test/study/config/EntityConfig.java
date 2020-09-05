package com.test.study.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "entity")
@Component
@Data
public class EntityConfig {

	private String id;
	private String time;
}
