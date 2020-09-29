package com.test.study.util.work;


import com.test.study.entity.InitBasicAttData;
import com.test.study.mapper.InitBasicAttDataRepository;
import com.test.study.util.StringUtil.StringUtil;
import com.test.study.util.io.IoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.StyledEditorKit;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class InitBasicConreoller {


	@Autowired
	private InitBasicAttDataRepository dataRepository;

	@RequestMapping("/initData")
	public void initData() throws IOException {

		String path = "C:\\Users\\lenovo2\\Desktop\\1.txt";
		String data = IoUtil.readFile(path);
		List<String> list = Arrays.stream(data.split("\n"))
				.filter(s -> !StringUtil.isEmpty(s))
				.filter(s -> !"异地".equals(s))
				.filter(s -> s.contains("-")).collect(Collectors.toList());

		List<String> source = Arrays.stream(data.split("\n"))
				.filter(s -> !StringUtil.isEmpty(s))
				.collect(Collectors.toList());

		List<InitBasicAttData> dataList = dataRepository.findAll();

		StringBuilder result = new StringBuilder();
		for (String s : source) {
			boolean flag = false;
			for (String l : list) {
				if (l.contains(s)) {
					result.append(l).append("\n");
					flag = true;
					break;
				}
			}

			if (!flag) {
				for (InitBasicAttData Idata : dataList) {
					if (Idata.getFjname().contains(s)) {
						result.append(Idata.getFjname()).append("-").append(Idata.getFjcode()).append("\n");
						flag = true;
						break;
					}
				}
			}

			if (!flag) {
				result.append(s).append("\n");
			}
		}

		IoUtil.writeFile("C:\\Users\\lenovo2\\Desktop\\2.txt", result.toString());
	}
}
