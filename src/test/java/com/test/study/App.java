package com.test.study;

import com.test.study.util.stream.StreamTest;

import com.test.study.util.work.XmlOutput;
import com.test.study.util.xml.XmlUtil;
import javassist.bytecode.CodeIterator;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 */
public class App {
	protected String s = "";

	/**
	 * 与0xff相交，可以去除8位以上的数据
	 */

	@Test
	public void streamTest() {
		// StreamTest.test();
		StreamTest.CollectorsStream();
	}

	@Test
	public void test() {
		// Byte b=1;
		byte value = (byte) 0xff;
		int value1 = 0xff;
		System.out.println(value);
		System.out.println(value1);

	}

	@Test
	public void xmlOutputTest() throws Exception {
		XmlOutput.readFile();
	}

	@Test
	public void xmlUtilTest() throws IOException, SAXException, ParserConfigurationException {
		String path = "C:\\Users\\wlm\\Desktop\\a.txt";
		File file = new File(path);
		XmlUtil.xmlParse(file);
	}

}
