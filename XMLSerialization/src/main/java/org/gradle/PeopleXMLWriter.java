package org.gradle;

import java.io.IOException;
import java.io.OutputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

public class PeopleXMLWriter {
	private XMLOutputter xmlOutput = new XMLOutputter(); 
	
	public PeopleXMLWriter() {
		
	}

	public void serialize(People people, OutputStream output) throws IOException{
		Element rootElement = new Element("root");
		for (Person p: people.getPersons())
			rootElement.addContent(getElement(p));
		
		Document document = new Document(rootElement);
		
		xmlOutput.output(document, output);
	}

	private Element getElement(Person p) {
		Element element = new Element("Person");
		element.setAttribute("age", "" + p.getAge());
		element.setText(p.getName());
		return element;
	}
}
