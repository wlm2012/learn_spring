package com.test.study.util.work;


import com.test.study.entity.PersInfo;
import com.test.study.mapper.PersInfoRepository;
import com.test.study.util.StringUtil.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class wenjianController {

	private PersInfoRepository persInfoRepository;

	@Autowired
	public void setPersInfoRepository(PersInfoRepository persInfoRepository) {
		this.persInfoRepository = persInfoRepository;
	}

	@RequestMapping("/wenjian")
	public void wenjian() throws IOException {

		String path = "C:\\Users\\lenovo2\\Desktop\\pers";
		File fold = new File(path);
		File[] files = fold.listFiles();
		String source = "";
		for (File file : files) {
			System.out.println(file.getName());
			StringBuffer buffer = new StringBuffer();
			try (FileReader reader = new FileReader(file)) {
				char[] buf = new char[1024 * 10];
				int temp = 0;
				while ((temp = reader.read(buf)) > 0) {
					buffer.append(new String(buf, 0, temp));
				}
				source += buffer;
			} catch (IOException e) {
				throw e;
			}
		}

//		System.out.println(source);

		System.out.println(source.split("\\n").length);

		Set<String> result = Arrays.stream(source.split("\\n")).filter(s -> s.contains("调用服务出错"))
				.map(s -> s.substring(s.lastIndexOf("zjhm=") + 5, s.length() - 1)).collect(Collectors.toSet());

		for (String zjhm : result) {
			Optional<PersInfo> persInfo = persInfoRepository.findById(zjhm.trim());
			if (persInfo.isPresent()) {
				PersInfo man = persInfo.get();
				man.setBz("-1");
				persInfoRepository.save(man);
				if (!StringUtil.isEmpty(man.getBeiz())) {
					Optional<PersInfo> persInfo1 = persInfoRepository.findById(man.getBeiz().trim());
					if (persInfo1.isPresent()) {
						PersInfo woman = persInfo1.get();
						woman.setBz("-1");
						persInfoRepository.save(woman);
					}
				}
			}
		}

	}
}
