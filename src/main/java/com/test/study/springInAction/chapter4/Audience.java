package com.test.study.springInAction.chapter4;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {

	@Pointcut("execution(* com.test.study.springInAction.chapter4.Performance.*(..)) ")
	public void pointCut() {
	}

	@Before("pointCut()")
	public void silenceCellPhone() {
		System.out.println("silence CellPhone");
	}

	@Around("pointCut()")
	public void watch(ProceedingJoinPoint pj) {
		try {
			System.out.println("before");
			pj.proceed();
			System.out.println("after");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			System.out.println(throwable.getMessage());
		}
	}
}
