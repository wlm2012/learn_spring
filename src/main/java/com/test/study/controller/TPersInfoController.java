package com.test.study.controller;


import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TPersInfoController {


	private PersInfoRepository persInfoRepository;

	@Autowired
	public void setRepository(PersInfoRepository repository) {
		persInfoRepository = repository;
	}


	@RequestMapping("/TPersInfo/{zjhm}")
	public PersInfo tPersInfo(@PathVariable String zjhm) {
		Optional<PersInfo> persInfo = persInfoRepository.findById(zjhm);
		if (persInfo.isPresent()) {
			return persInfo.get();
		}
		return null;
	}
}
