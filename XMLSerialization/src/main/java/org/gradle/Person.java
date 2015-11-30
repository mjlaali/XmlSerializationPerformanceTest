package org.gradle;

import java.io.Serializable;

import com.sun.xml.internal.txw2.annotation.XmlElement;

@SuppressWarnings("serial")
public class Person implements Serializable{
    private String name;
    private double age;

    public Person(String name, double age) {
        this.name = name;
        this.age = age;
    }
    
    public Person() {
	}

    public String getName() {
        return name;
    }
    
    public double getAge() {
		return age;
	}
    
    @XmlElement
    public void setName(String name) {
		this.name = name;
	}
    
    @XmlElement
    public void setAge(double age) {
		this.age = age;
	}
}
