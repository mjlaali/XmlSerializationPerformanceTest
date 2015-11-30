package org.gradle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@SuppressWarnings("serial")
@XmlRootElement
public class People implements Serializable{

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
