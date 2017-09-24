package com.sampathsl.sms.dao.service;

import java.util.List;

import com.sampathsl.sms.dto.StudentDTO;

public interface StudentService {
	
	StudentDTO create(StudentDTO studentDTO) throws Exception;
	 
	StudentDTO delete(String id) throws Exception;
 
    List<StudentDTO> findAll();
 
    StudentDTO findById(String id) throws Exception;
 
    StudentDTO update(StudentDTO newStudentDTO) throws Exception;
	
}
