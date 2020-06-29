package com.test.study.util.work;

import com.test.study.util.StringUtil.StringUtil;
import com.test.study.util.io.IoUtil;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author lenovo2
 */
public class XmlOutput {

    private static String path = "C:\\Users\\lenovo2\\Desktop\\v6\\a.txt";


    public static void readFile() throws IOException {
        String file = IoUtil.readFile(path);
        Arrays.stream(file.split("-")).filter(s -> !StringUtil.isEmpty(s)).forEach(s -> {
            try {
                readXml(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    public static void readXml(String file) throws Exception {
        String[] files = file.split("\\r\\n\\r\\n");
        ArrayList<String> list = Stream.of(files).filter(s -> !StringUtil.isEmpty(s)).collect(Collectors.toCollection(ArrayList::new));
        if (list.size() != 3 && list.size() != 2) {
            throw new Exception("格式不正确");
        }

        ArrayList<String> title = Stream.of(list.get(0).replace("\r\n", " ").split(" ")).filter(s -> !StringUtil.isEmpty(s)).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<String> input = Stream.of(list.get(1).split("\\r\\n")).filter(s -> !StringUtil.isEmpty(s)).collect(Collectors.toCollection(ArrayList::new));
        Stream<String> output = null;
        if (list.size() == 3) {
            output = Stream.of(list.get(2).split("\\r\\n")).filter(s -> !StringUtil.isEmpty(s));

        }

        if (title.size() == 0 || input.size() == 0) {
            throw new Exception("方法名和输入不能为空");
        }

        Document doc = DocumentHelper.createDocument();
        Element function = doc.addElement("function");
        function.addAttribute("method_name", title.get(0));
        function.addAttribute("desc", title.get(1));


        //service
        Element service = function.addElement("service");

        //in
        Element in = service.addElement("in");
        for (String name : input) {
            Element item = in.addElement("item");
            item.addAttribute("name", name);
        }

        //out
        Element out = service.addElement("out");
        if (output != null) {
            output.forEach(s -> {
                Element item = out.addElement("item");
                item.addAttribute("name", s);
            });
        }


        //ecip
        Element ecip = function.addElement("ecip");
        Element server_name = ecip.addElement("server_name");
        server_name.addText("HTT_JSON_SVR");
        Element page = ecip.addElement("page");
        page.addAttribute("module", "page1");
        page.addAttribute("node", "in");

        //输出
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        format.setNewlines(true);
        XMLWriter xmlWriter = new XMLWriter(format);
        xmlWriter.write(function);

    }
}
