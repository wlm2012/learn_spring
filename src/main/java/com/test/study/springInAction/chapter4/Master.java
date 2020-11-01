package com.test.study.springInAction.chapter4;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Master implements Performance {

	@Override
	@RequestMapping("/perform")
	public void perform(@RequestParam("num") int num) {
//		System.out.println("do well");
		System.out.println(10 / num);
	}

	@Override
	public void getOut() {
		System.out.println("getOut");
	}
}
