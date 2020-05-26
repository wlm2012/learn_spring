package com.test.study.util.xml;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * @author lenovo2
 */
public class XmlUtil {

	public static void xmlParse(File file) throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		Element root = document.getDocumentElement();
		NodeList children = root.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Node child = children.item(i);
			if (child instanceof Element) {
				Element childElement = (Element) child;

				NamedNodeMap attributes = childElement.getAttributes();
				for (int j = 0; j < attributes.getLength(); j++)
				{
					Node attribute = attributes.item(j);
					String name = attribute.getNodeName();
					String value = attribute.getNodeValue();
					System.out.println(name+"   "+value);
				}


				Text textNode = (Text) childElement.getFirstChild();
				String text = textNode.getData().trim();
				System.out.println(text);
				if ("name".equals(childElement.getTagName())) {
					System.out.println(text);
				}

			}
		}


	}


}
