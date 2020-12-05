package com.test.study.controller;


import com.test.study.entity.TPersInfo;
import com.test.study.primaryMapper.TPersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TPersInfoController {


	private TPersInfoRepository persInfoRepository;

	@Autowired
	public void setRepository(TPersInfoRepository repository) {
		persInfoRepository = repository;
	}


	@RequestMapping("/TPersInfo/{grzh}")
	public TPersInfo tPersInfo(@PathVariable String grzh) {
		Optional<TPersInfo> persInfo = persInfoRepository.findById(grzh);
		if (persInfo.isPresent()) {
			return persInfo.get();
		}
		return null;
	}
}
