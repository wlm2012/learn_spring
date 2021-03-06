package com.test.study.springInAction.chapter1.javaConfig;


import com.test.study.springInAction.chapter1.autoconfig.CompactDisc;
import com.test.study.springInAction.chapter1.autoconfig.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class CDPlayerConfig {

	@Bean
	public CompactDisc compactDisc() {
		return new SgtPeppers();
	}

	@Bean
	public CDPlayer cdPlayer(CompactDisc compactDisc) {
		return new CDPlayer(compactDisc);
	}
}
