package com.test.study.springInAction.chapter4;


import org.springframework.stereotype.Component;

@Component
public class Master implements Performance {

	@Override
	public void perform() {
		System.out.println("do well");
	}
}
