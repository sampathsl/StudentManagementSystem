package com.sampathsl.sms.controller;

import java.util.List;
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
import com.sampathsl.sms.exception.CustomErrorTypeException;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	private static final Logger logger = LogManager.getLogger(StudentController.class);
	
	@Autowired
	private StudentServiceImpl studentServiceImpl;

	/**
	 * Load all the students
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public ResponseEntity<List<StudentDTO>> listAllStudents() {
		
		logger.info("IN listAllTasks METHOD");
		List<StudentDTO> studentList = studentServiceImpl.findAll();
        if (studentList.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<StudentDTO>>(studentList, HttpStatus.OK);

	}

	/**
	 * Load student by id
	 * @param id
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudent(@Valid @PathVariable("id") String id) throws Exception {
		
		logger.info("IN getStudent METHOD");
		StudentDTO studentDTO = studentServiceImpl.findById(id);
		return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
		
	}

	/**
	 * Get all the errors
	 * @param errors
	 * @return ResponseEntity
	 */
	private ResponseEntity<List<CustomErrorTypeException>> getErrors(Errors errors) {
		
        return ResponseEntity.badRequest().body(errors.getAllErrors()
				.stream()
				.map(msg -> new CustomErrorTypeException(msg.getDefaultMessage()))
				.collect(Collectors.toList()));
        
	}

	/**
	 * Create a new student
	 * @param studentDTO
	 * @param ucBuilder
	 * @param errors
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@RequestMapping(value = "/students", method = RequestMethod.POST)
	public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDTO studentDTO, 
			UriComponentsBuilder ucBuilder, Errors errors) throws Exception {
		
		logger.info("IN createStudent METHOD");
		if (errors.hasErrors()) {
			return getErrors(errors);
        }

		StudentDTO studentDTONew = studentServiceImpl.create(studentDTO);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/tasks/{id}").buildAndExpand(studentDTONew.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
		
	}

	/**
	 * Update particular student by id
	 * @param id
	 * @param studentDTO
	 * @param errors
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateStudent(@Valid @PathVariable("id") String id,
			@Valid @RequestBody StudentDTO studentDTO, Errors errors) throws Exception {
		
		logger.info("IN updateStudent METHOD");

		if (errors.hasErrors()) {
			return getErrors(errors);
        }
		
		if(studentDTO.getId() == null){
			throw new CustomErrorTypeException("Unable to update. Student with id " + id + " not found.");
		}
		
		try {
			studentServiceImpl.update(studentDTO);
			StudentDTO updatedStudentDTO = studentServiceImpl.findById(id);
			return new ResponseEntity<StudentDTO>(updatedStudentDTO, HttpStatus.OK);
		} catch (Exception e) {
			throw new CustomErrorTypeException("Student update is not allowed.Please reload your edit student view.");
		}
		
	}

	/**
	 * Delete student by id
	 * @param id
	 * @return ResponseEntity
	 * @throws Exception
	 */
	@RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStudent(@Valid @PathVariable("id") String id) throws Exception {

		logger.info("IN delete METHOD");

		try {
			studentServiceImpl.delete(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			throw new CustomErrorTypeException("Student delete is not allowed.");
		}

	}
	
}
