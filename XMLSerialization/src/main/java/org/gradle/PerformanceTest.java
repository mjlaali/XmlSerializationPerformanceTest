package org.gradle;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Calendar;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.jdom2.JDOMException;

public class PerformanceTest {

	public static void main(String[] args) throws JAXBException, IOException, JDOMException, ClassNotFoundException {
		System.out.println("PerformanceTest.main()");
		File outputFile = new File("out.xml");
		JAXBContext context = JAXBContext.newInstance(People.class);
		
		People people = new People();
		
		final int PEOPLE_CNT = 1000000;
//		final int PEOPLE_CNT = 1;
		for (int i = 0; i < PEOPLE_CNT; i++)
			people.getPersons().add(new Person("Name:" + Math.random(), Math.random() * 100));
		
		long startTime, endTime;
		
		startTime = Calendar.getInstance().getTimeInMillis();
		OutputStream out = new BufferedOutputStream(new FileOutputStream(outputFile));
		Marshaller marshaller = context.createMarshaller();
		marshaller.marshal(people, out);
		out.close();
		endTime = Calendar.getInstance().getTimeInMillis();
		
		System.out.printf("JAXB -> File size: %d, marshaling time: %d\n", outputFile.length(), endTime - startTime);
		
		startTime = Calendar.getInstance().getTimeInMillis();
		InputStream in = new BufferedInputStream(new FileInputStream(outputFile));
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Object unmarshaled = unmarshaller.unmarshal(in);
		in.close();
		endTime = Calendar.getInstance().getTimeInMillis();

		System.out.printf("JAXB -> Unmarshaled's person size: %d, unmarshaling time: %d\n", ((People)unmarshaled).getPersons().size(), endTime - startTime);
		
		PeopleXMLWriter peopleXMLWriter = new PeopleXMLWriter();
		startTime = Calendar.getInstance().getTimeInMillis();
		out = new BufferedOutputStream(new FileOutputStream(outputFile));
		peopleXMLWriter.serialize(people, out);
		out.close();
		endTime = Calendar.getInstance().getTimeInMillis();
		
		System.out.printf("Manual -> File size: %d, marshaling time: %d\n", outputFile.length(), endTime - startTime);
		
		PeopleXMLReader peopleXMLReader = new PeopleXMLReader();
		startTime = Calendar.getInstance().getTimeInMillis();
		in = new BufferedInputStream(new FileInputStream(outputFile));
		People people2 = peopleXMLReader.deserialize(in);
		in.close();
		endTime = Calendar.getInstance().getTimeInMillis();
		
		System.out.printf("Manual -> Unmarshaled's person size: %d, unmarshaling time: %d\n", people2.getPersons().size(), endTime - startTime);
		
		
		ObjectOutputStream output = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
		startTime = Calendar.getInstance().getTimeInMillis();
		output.writeObject(people);
		endTime = Calendar.getInstance().getTimeInMillis();
		output.close();
		System.out.printf("Java -> File size: %d, marshaling time: %d\n", outputFile.length(), endTime - startTime);
		
		ObjectInputStream input = new ObjectInputStream(new BufferedInputStream(new FileInputStream(outputFile)));
		startTime = Calendar.getInstance().getTimeInMillis();
		Object obj = input.readObject();
		endTime = Calendar.getInstance().getTimeInMillis();
		input.close();
		System.out.printf("Java -> Unmarshaled's person size: %d, unmarshaling time: %d\n", ((People)obj).getPersons().size(), endTime - startTime);
		
	}
}
