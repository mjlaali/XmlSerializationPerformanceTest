package org.gradle;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@XmlRootElement
public class People {

	List<Person> persons = new ArrayList<Person>();

	public People(List<Person> persons) {
		this.persons = persons;
	}

	public People() {
	}

	public List<Person> getPersons() {
		return persons;
	}

	@XmlElement
	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	
}
