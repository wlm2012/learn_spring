package com.test.study.util.work;

import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.concurrent.Callable;

public class PersTestCalable implements Callable {


	private PersInfoRepository persInfoRepository;

	private String zjhm;

//	@Autowired
//	public void SetPersInfoRepository(PersInfoRepository repository) {
//		persInfoRepository = repository;
//	}


	public PersTestCalable(String sfz, PersInfoRepository persInfoRepository) {
		this.zjhm = sfz;
		this.persInfoRepository = persInfoRepository;
	}

	@Override
	public Object call() {
		System.out.println(zjhm);
		Optional<PersInfo> persInfo = persInfoRepository.findById(zjhm);
		if (persInfo.isPresent()) {
			System.out.println("call" + persInfo);
		}
		return null;
	}
}
