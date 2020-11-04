package com.test.study.springInAction.chapter4;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class Music {

	@RequestMapping("/music")
	public String play(@RequestParam("num") int num) {
		System.out.println(num);
		System.out.println("play");
		System.out.println(10 / num);
		return num + 10 + "";
	}
}
