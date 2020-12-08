package com.test.study.controller;


import com.google.common.collect.Lists;
import com.test.study.entity.TPersInfo;
import com.test.study.primaryMapper.PersMapper;
import com.test.study.primaryMapper.TPersInfoRepository;
import com.test.study.secondMapper.SecondPersInfoRepository;
import com.test.study.secondMapper.SecondPersMapper;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TPersInfoController {


	private TPersInfoRepository persInfoRepository;

	private SecondPersInfoRepository secondPersInfoRepository;

	private PersMapper persMapper;

	private SecondPersMapper secondPersMapper;


	@Autowired
	public void setSecondPersMapper(SecondPersMapper secondPersMapper) {
		this.secondPersMapper = secondPersMapper;
	}

	@Autowired
	public void setPersMapper(PersMapper persMapper) {
		this.persMapper = persMapper;
	}

	@Autowired
	public void setSecondPersInfoRepository(SecondPersInfoRepository repository) {
		secondPersInfoRepository = repository;
	}

	@Autowired
	public void setRepository(TPersInfoRepository repository) {
		persInfoRepository = repository;
	}


	@RequestMapping("/TPersInfo/{grzh}/{datasouce}")
	@Transactional(isolation = Isolation.READ_UNCOMMITTED)
	public TPersInfo tPersInfo(@PathVariable String grzh, @PathVariable int datasouce) {
		Optional<TPersInfo> persInfo = null;
		if (1 == datasouce) {
			persInfo = persInfoRepository.findById(grzh);
		} else {
			persInfo = secondPersInfoRepository.findById(grzh);
		}

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

	@RequestMapping("/SavePers1/{grzh}/{xingbie}")
	@Transactional("secondTransactionManager")
	public void second(@PathVariable String grzh, @PathVariable String xingbie, @RequestBody TPersInfo tPersInfo) {
		Optional<TPersInfo> persInfo = secondPersInfoRepository.findById(grzh);
		if (persInfo.isPresent()) {
			TPersInfo persInfo1 = persInfo.get();
			persInfo1.setXingbie(xingbie);
			secondPersInfoRepository.save(persInfo1);
		}

		secondPersInfoRepository.save(tPersInfo);
	}

	@RequestMapping("/persMapper")
	public List<TPersInfo> persMapper(@RequestParam String grzh) {
		TPersInfo tPersInfo = persMapper.selectById(grzh);

		TPersInfo tPersInfo1 = secondPersMapper.selectById(grzh);

		List<TPersInfo> list = Lists.newArrayList(tPersInfo, tPersInfo1);

		return list;
	}

}
