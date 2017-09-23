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

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		for (StudentDTO studentDTO : studentService.findAll()) {
			studentService.delete(studentDTO.getId());
		}

		// save a couple of customers
		StudentDTO studentDTO = new StudentDTO();
		studentDTO.setFirstName("SAMPATH");
		studentDTO.setLastName("THENNAKOON");
		studentDTO.setAge(30);
		studentDTO.setDob("1995-10-10");
		studentDTO.setGender("MALE");
		studentService.create(studentDTO);

		StudentDTO studentDTO1 = new StudentDTO();
		studentDTO1.setFirstName("SAMPATH");
		studentDTO1.setLastName("THENNAKOON");
		studentDTO1.setAge(31);
		studentDTO1.setDob("1995-10-10");
		studentDTO1.setGender("MALE");
		studentService.create(studentDTO1);

		// fetch all customers
		/*System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Customer customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (Customer customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}*/

	}

}
