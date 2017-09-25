package com.sampathsl.sms.entity;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Student entiry class
 * @author SAMPATH
 *
 */

public class Student extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 51516546545614L;
	
	private String firstName;
	private String lastName;
	//private String dob;
	private Integer age;
	private String gender;
	
	protected Student() {
        super();
    }
    
	public Student(String firstName,String lastName,
    		Integer age,String gender) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.age = age;
    	this.gender = gender;
    }
    
    public Student(String id,String firstName,String lastName,
    		Integer age,String gender) {
    	super(id);
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.age = age;
    	this.gender = gender;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/*public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}*/

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "Student{" +
				"firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", age=" + age +
				", gender='" + gender + '\'' +
				'}';
	}

}
