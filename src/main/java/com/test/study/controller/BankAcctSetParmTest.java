package com.test.study.controller;


import com.test.study.entity.BankAcctSetParm;
import com.test.study.mapper.BankAcctSetParmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BankAcctSetParmTest {

	@Autowired
	private BankAcctSetParmRepository bankAcctSetParmRepository;

	@RequestMapping("/BankAcctSetParmTest")
	public BankAcctSetParm bankAcctSetParmTest() {
		List<BankAcctSetParm> list = bankAcctSetParmRepository.findAll();
		BankAcctSetParm bankAcctSetParm = list.get(0);
		bankAcctSetParm.setRsv("12");
//		bankAcctSetParmRepository.save(bankAcctSetParm);
		System.out.println(list.toString());
		return list.get(0);
	}
}
