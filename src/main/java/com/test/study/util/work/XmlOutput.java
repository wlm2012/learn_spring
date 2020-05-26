package com.test.study.util.work;

import com.test.study.util.StringUtil.StringUtil;
import com.test.study.util.io.IoUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lenovo2
 */
public class XmlOutput {

	private static String path = "C:\\Users\\lenovo2\\Desktop\\v6\\a.txt";
	private static int size = 3;

	public static void readFile() throws Exception {
		String file = IoUtil.readFile(path);
		String[] files = file.split("\\r\\n\\r\\n");
		ArrayList<String> list = Stream.of(files).filter(s -> !StringUtil.isEmpty(s)).filter(s -> !s.contains("\\r\\n")).collect(Collectors.toCollection(ArrayList::new));
		if (list.size() != size) {
			throw new Exception("格式不正确");
		}

//		String tile=list.get(0);
//		String input=list.get(1);
//		String output=list.get(2);


		ArrayList<String> title=Stream.of(list.get(0).split(" ")).filter(s -> !StringUtil.isEmpty(s)).collect(Collectors.toCollection(ArrayList::new));
		Stream<String> input = Stream.of(files[0].split("\\r\\n")).filter(s -> !StringUtil.isEmpty(s));
		String[] output = files[1].split("\\r\\n");
	}
}
