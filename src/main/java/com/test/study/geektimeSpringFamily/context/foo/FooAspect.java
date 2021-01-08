package com.test.study.geektimeSpringFamily.context.foo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class FooAspect {

	@AfterReturning("bean(testBean*)")
	public void printAfter() {
		log.info("after hello()");
	}
}
