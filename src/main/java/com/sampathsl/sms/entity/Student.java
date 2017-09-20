package com.sampathsl.sms.entity;

import java.io.Serializable;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

public class Student extends BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 51516546545614L;
	
	private String firstName;
	private String lastName;
	private String dob;
	private Integer age;
	private String gender;
	private Long yearEnrolled;
	
	protected Student() {
        super();
    }
    
	public Student(String firstName,String lastName,String dob,
    		Integer age,String gender,Long yearEnrolled) {
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.dob = dob;
    	this.age = age;
    	this.gender = gender;
    	this.yearEnrolled = yearEnrolled;
    }
    
    public Student(UUID id,String firstName,String lastName,String dob,
    		Integer age,String gender,Long yearEnrolled) {
    	super(id);
    	this.firstName = firstName;
    	this.lastName = lastName;
    	this.dob = dob;
    	this.age = age;
    	this.gender = gender;
    	this.yearEnrolled = yearEnrolled;
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

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

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

	public Long getYearEnrolled() {
		return yearEnrolled;
	}

	public void setYearEnrolled(Long yearEnrolled) {
		this.yearEnrolled = yearEnrolled;
	}

}
