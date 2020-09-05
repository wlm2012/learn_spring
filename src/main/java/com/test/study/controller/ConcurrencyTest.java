package com.test.study.controller;

import com.test.study.config.EntityConfig;
import com.test.study.util.StringUtil.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wlm
 * <p>
 * test Singleton Pattern
 */

@RestController
public class ConcurrencyTest {

	String s = null;

	@Autowired
	private EntityConfig entityConfig;


	@RequestMapping("/concurrencyTest")
	public String concurrencyTest(String time) throws InterruptedException {

		if (StringUtil.isEmpty(time)) {
			time = entityConfig.getTime();
		}
		s = time;
		Thread.sleep(Integer.parseInt(time));
		return s;
	}
}
