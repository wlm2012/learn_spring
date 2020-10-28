package com.test.study.springInAction.chapter4;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class Audience {

	@Before("execution(* com.test.study.springInAction.chapter4.Performance.perform(..)) ")
	public void silenceCellPhone(){
		System.out.println("silence CellPhone");
	}
}
