package com.sampathsl.sms.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.sampathsl.sms.dao.service.StudentServiceImpl;
import com.sampathsl.sms.dto.StudentDTO;
import com.sampathsl.sms.exception.StudentNotFountException;
import com.sampathsl.sms.util.CustomErrorType;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	private static final Logger logger = LogManager.getLogger(StudentController.class);
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> listAllStudents() {
		
		logger.info("IN listAllTasks METHOD");
		
		List<StudentDTO> studentList = studentServiceImpl.findAll();
		
        if (studentList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
        
		return new ResponseEntity<List<StudentDTO>>(studentList, HttpStatus.OK);

	}

	@RequestMapping(value = "/students/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudent(@Valid @PathVariable("uuid") UUID uuid) throws StudentNotFountException {
		
		logger.info("IN getStudent METHOD");
		
		StudentDTO studentDTO = studentServiceImpl.findById(uuid.toString());
		
		if (studentDTO == null) {
			return new ResponseEntity(new CustomErrorType("Student with uuid " + uuid
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
		
	}
	
	private ResponseEntity<List<CustomErrorType>> getErrors(Errors errors) {
		
        return ResponseEntity.badRequest().body(errors.getAllErrors()
				.stream()
				.map(msg -> new CustomErrorType(msg.getDefaultMessage()))
				.collect(Collectors.toList()));
        
	}
	
	@RequestMapping(value = "/students", method = RequestMethod.POST)
	public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDTO studentDTO, 
			UriComponentsBuilder ucBuilder, Errors errors) throws StudentNotFountException {
		
		logger.info("IN createStudent METHOD");
		
		if (errors.hasErrors()) {
			return getErrors(errors);
        }

		StudentDTO studentDTONew = studentServiceImpl.create(studentDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/tasks/{uuid}").buildAndExpand(studentDTONew.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/students/{uuid}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateStudent(@Valid @PathVariable("uuid") UUID uuid,
			@Valid @RequestBody StudentDTO studentDTO, Errors errors) throws StudentNotFountException {
		
		logger.info("IN updateStudent METHOD");
		
		if (errors.hasErrors()) {
			return getErrors(errors);
        }
		
		if(studentDTO.getId() != null){
			return new ResponseEntity(new CustomErrorType("Unable to upate. Student with uuid " + uuid + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		StudentDTO existingStudentDTO = studentServiceImpl.findById(studentDTO.getId().toString());

		if (existingStudentDTO == null) {
			return new ResponseEntity(new CustomErrorType("Unable to upate. Student with uuid " + uuid + " not found."),
					HttpStatus.NOT_FOUND);
		}
		
		try {
			studentServiceImpl.update(studentDTO,existingStudentDTO);
			StudentDTO updatedStudentDTO = studentServiceImpl.findById(uuid.toString());
			return new ResponseEntity<StudentDTO>(updatedStudentDTO, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity(new CustomErrorType("Student update is not allowed.Please reload your edit student view."),
					HttpStatus.NOT_FOUND);
		
	}
	
}
