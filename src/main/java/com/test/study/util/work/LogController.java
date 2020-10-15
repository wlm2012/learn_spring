package com.test.study.util.work;


import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import com.test.study.util.io.IoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LogController {

	private PersInfoRepository persInfoRepository;

	@Autowired
	public void SetPersInfoRepository(PersInfoRepository repository) {
		persInfoRepository = repository;
	}

	@RequestMapping("log2")
	public void Log2Test() throws IOException {

		String log = IoUtil.readFile("C:\\Users\\lenovo2\\Desktop\\1log.txt");
		String[] logs = log.split("\\r\\n");
		List<String> list = Arrays.stream(logs).filter(s -> s.contains("time")).map(s -> s.substring(8, 13)).collect(Collectors.toList());
		long count = 0L;
		for (String time : list) {
			count += Long.parseLong(time.trim());
		}
		System.out.println(count / 1000 / 60 / 6);
		List<PersInfo> persInfoList = persInfoRepository.findByLastUpdateTimeAfter("2020-10-15");
		System.out.println(persInfoList.size());
	}
}
