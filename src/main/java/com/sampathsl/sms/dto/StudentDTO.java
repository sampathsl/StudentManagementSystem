package com.sampathsl.sms.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Student Data Transfer class
 * @author SAMPATH
 */

public final class StudentDTO implements Serializable {

	private static final long serialVersionUID = 5545512118451221L;

	private String id;
	
	@NotNull(message = "First name can''t be empty!")
    @Size(min = 2, max = 100 , message = "First name length should be between 2 and 100!")
	private String firstName;
	
	@NotNull(message = "Lastname can''t be empty!")
    @Size(min = 2, max = 100 , message = "Last name length should be between 2 and 100!")
	private String lastName;
	
	/*@JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private String dob;*/
	
	@NotNull(message = "Age can''t be empty!")
	private Integer age;
	
	@NotNull(message = "Gender can''t be empty!")
	private String gender;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

}
