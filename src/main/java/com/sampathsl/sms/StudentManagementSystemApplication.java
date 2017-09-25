package com.sampathsl.sms;

import com.sampathsl.sms.dao.service.StudentService;
import com.sampathsl.sms.dto.StudentDTO;
import com.sampathsl.sms.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner {

	@Autowired
	private StudentService studentService;

	/**
	 * Spring boot application startup location
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	/**
	 * Pre load the spring boot application data
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run(String... args) throws Exception {

		for (StudentDTO studentDTO : studentService.findAll()) {
			studentService.delete(studentDTO.getId());
		}

		// save a couple of students
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setFirstName("Ruwan");
		studentDTO.setLastName("Kulathunga");
		studentDTO.setAge(10);
		//studentDTO.setDob("2005-10-10");
		studentDTO.setGender("MALE");
		studentService.create(studentDTO);

		StudentDTO studentDTO1 = new StudentDTO();
		studentDTO1.setFirstName("Ravidu");
		studentDTO1.setLastName("Senanayake");
		studentDTO1.setAge(15);
		//studentDTO1.setDob("2000-10-10");
		studentDTO1.setGender("MALE");
		studentService.create(studentDTO1);

	}

}
