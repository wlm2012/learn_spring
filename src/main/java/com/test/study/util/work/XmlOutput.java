package com.test.study.util.work;

import com.test.study.util.StringUtil.StringUtil;
import com.test.study.util.io.IoUtil;

import java.io.IOException;
import java.util.stream.Stream;

public class XmlOutput {

	private static String path = "C:\\Users\\lenovo2\\Desktop\\v6\\a.txt";

	public static void readFile() throws IOException {
		String file = IoUtil.readFile(path);
		String[] files=file.split("\\r\\n\\r\\n");
		Stream<String> input= Stream.of(files[0].split("\\r\\n")).filter(s-> !StringUtil.isEmpty(s));
		String[] output=files[1].split("\\r\\n");
	}
}
