package org.gradle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class PeopleXMLReader {
	private SAXBuilder saxBuilder;
	public PeopleXMLReader() {
		 saxBuilder = new SAXBuilder();  
	}

	public People deserialize(InputStream in) throws JDOMException, IOException{
		 Document document = saxBuilder.build(in);
		 List<Person> person = new ArrayList<Person>();
		 
		 Element rootElement = document.getRootElement();
		 for (Element e: rootElement.getChildren()){
			 person.add(getPerson(e));
		 };
		 return new People(person);
	}

	private Person getPerson(Element e) {
		double age = Double.parseDouble(e.getAttributeValue("age"));
		String name = e.getText();
		return new Person(name, age);
	}
}
