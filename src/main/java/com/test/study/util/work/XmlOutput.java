package com.test.study.util.work;

import com.test.study.util.StringUtil.StringUtil;
import com.test.study.util.io.IoUtil;
import org.apache.logging.log4j.spi.ReadOnlyThreadContextMap;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

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
	private static String pathOut = "";
	private static int size = 3;

	public static void readFile() throws Exception {
		String file = IoUtil.readFile(path);
		String[] files = file.split("\\r\\n\\r\\n");
		ArrayList<String> list = Stream.of(files).filter(s -> !StringUtil.isEmpty(s)).filter(s -> !StringUtil.isEmpty(s)).collect(Collectors.toCollection(ArrayList::new));
		if (list.size() > size) {
			throw new Exception("格式不正确");
		}

		ArrayList<String> title = Stream.of(list.get(0).replace("\\r\\n"," ").split(" ")).filter(s -> !StringUtil.isEmpty(s)).collect(Collectors.toCollection(ArrayList::new));
		Stream<String> input = Stream.of(files[0].split("\\r\\n")).filter(s -> !StringUtil.isEmpty(s));
		String[] output = files[1].split("\\r\\n");
	}

	public static void writeXml() throws IOException {
		Document doc = DocumentHelper.createDocument();
		Element function = doc.addElement("function");
		function.addAttribute("method_name", "FundStoragePlanDetQuy");
		function.addAttribute("desc", "资金存储计划明细查询");


		//service
		Element service = function.addElement("service");

		//in
		Element in = service.addElement("in");
		Element item1 = in.addElement("item");
		item1.addAttribute("name", "plan_no");
		Element item2 = in.addElement("item");
		item2.addAttribute("name", "stat");


		//ecip
		Element ecip = function.addElement("ecip");
		Element server_name = ecip.addElement("server_name");
		server_name.addText("HTT_JSON_SVR");
		Element page = ecip.addElement("page");
		page.addAttribute("module", "page1");
		page.addAttribute("node", "in");

		OutputFormat format = OutputFormat.createPrettyPrint();
		format.setEncoding("UTF-8");
		XMLWriter xmlWriter = new XMLWriter(format);
		xmlWriter.write(function);
	}
}
