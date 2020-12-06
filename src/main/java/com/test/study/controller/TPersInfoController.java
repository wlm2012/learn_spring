package com.test.study.controller;


import com.test.study.entity.TPersInfo;
import com.test.study.primaryMapper.TPersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public TPersInfo tPersInfo(@PathVariable String grzh) {
		Optional<TPersInfo> persInfo = persInfoRepository.findById(grzh);
		if (persInfo.isPresent()) {
			return persInfo.get();
		}
		return null;
	}


	@RequestMapping("/TPersInfo1/{grzh}")
	@Transactional(isolation = Isolation.READ_COMMITTED)
	public TPersInfo tPersInfo1(@PathVariable String grzh) {
		Optional<TPersInfo> persInfo = persInfoRepository.findById(grzh);
		if (persInfo.isPresent()) {
			return persInfo.get();
		}
		return null;
	}

	@RequestMapping("/SavePers/{grzh}/{xingbie}")
	@Transactional
	public void tPersInfo(@PathVariable String grzh, @PathVariable String xingbie, @RequestBody TPersInfo tPersInfo) {
		Optional<TPersInfo> persInfo = persInfoRepository.findById(grzh);
		if (persInfo.isPresent()) {
			TPersInfo persInfo1 = persInfo.get();
			persInfo1.setXingbie(xingbie);
			persInfoRepository.save(persInfo1);
		}

		persInfoRepository.save(tPersInfo);
	}
}
