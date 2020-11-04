package com.test.study.springInAction.chapter4;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class Audience {

	@Pointcut("execution(* com.test.study.springInAction.chapter4.Performance.perform(int)) && args(num) ")
	public void pointCut(int num) {
	}

	@Before("pointCut(num)")
	public void silenceCellPhone(int num) {
		System.out.println("silence CellPhone");
	}

	@Around("pointCut(num)")
	public void watch(ProceedingJoinPoint pj, int num) {
		try {
			System.out.println("before");
			System.out.println(num);
			pj.proceed();
			System.out.println("after");
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			System.out.println(throwable.getMessage());
		}
	}


	@Pointcut("execution(* com.test.study.springInAction.chapter4.Music.play(..))")
	public void pointCut2() {

	}

	@Around("pointCut2()")
	public Object listen(ProceedingJoinPoint joinPoint) {
		try {
			System.out.println("before");
			Object object = joinPoint.proceed();
			System.out.println("after");
			return object;
		} catch (Throwable throwable) {
			throwable.printStackTrace();
			System.out.println(throwable.getMessage());
		}
		return null;
	}
}
