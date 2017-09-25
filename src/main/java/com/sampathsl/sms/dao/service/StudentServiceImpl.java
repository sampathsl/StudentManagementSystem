package com.sampathsl.sms.dao.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sampathsl.sms.dao.repository.StudentRepository;
import com.sampathsl.sms.dto.StudentDTO;
import com.sampathsl.sms.entity.Student;
import com.sampathsl.sms.exception.StudentNotFountException;

import static java.util.stream.Collectors.toList;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	/**
	 * Create a new student
	 * @param student
	 * @return StudentDTO
	 * @throws Exception
	 */
	@Override
	public StudentDTO create(StudentDTO student) throws Exception {
		Student persisted = studentRepository.save(convertToEntity(student));
        return convertToDTO(persisted);
	}

	/**
	 * Delete student using id
	 * @param id
	 * @throws StudentNotFountException
	 */
	@Override
	public StudentDTO delete(String id) throws StudentNotFountException {
		Student deleted = findStudentById(id);
		studentRepository.delete(deleted);
        return convertToDTO(deleted);
	}

	/**
	 * Load all the avalible students
	 * @return List<StudentDTO>
	 */
	@Override
	public List<StudentDTO> findAll() {
		List<Student> students = StreamSupport
		 .stream(studentRepository
				 .findAll()
				 .spliterator(), false)
		 .collect(Collectors.toList());
	     return convertToDTOs(students);
	}

	/**
	 * Select student using id
	 * @param id
	 * @return StudentDTO
	 * @throws StudentNotFountException
	 */
	@Override
	public StudentDTO findById(String id) throws StudentNotFountException {
		Student student = findStudentById(id);
        return convertToDTO(student);
	}

	/**
	 * Update existing student
	 * @param newStudentDTO
	 * @return
	 * @throws StudentNotFountException
	 */
	@Override
	public StudentDTO update(StudentDTO newStudentDTO) throws StudentNotFountException {
		Student updated = studentRepository.save(convertToEntity(newStudentDTO));
        return convertToDTO(updated);
	}

	/**
	 * Convert student entity to Student data transfer object
	 * @param model
	 * @return StudentDTO
	 */
	private StudentDTO convertToDTO(Student model) {
		StudentDTO dto = new StudentDTO();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setGender(model.getGender());
        //dto.setDob(model.getDob());
        dto.setAge(model.getAge());
        return dto;
	}

	/**
	 * Convert liust of student entity object to list of student data transfer objects
	 * @param models
	 * @return List<StudentDTO>
	 */
	private List<StudentDTO> convertToDTOs(List<Student> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }

	/**
	 * Select student using id
	 * @param id
	 * @return Student
	 */
	private Student findStudentById(String id) throws StudentNotFountException {
        Optional<Student> result = studentRepository.findById(id);
        return result.orElseThrow(() -> new StudentNotFountException("Student not found - id : " + id));

    }
	
	/**
	 * Convert StudentDTO object to student entity
	 * @param studentDTO
	 * @return
	 * @throws StudentNotFountException 
	 */
	private Student convertToEntity(StudentDTO studentDTO) throws StudentNotFountException {
		
		Student student = null;
		
	    if (studentDTO.getId() != null) {
	    	Student oldStudent = findStudentById(studentDTO.getId().toString());
	    	if(oldStudent != null){
	    		student = new Student(studentDTO.getId(),studentDTO.getFirstName(),
	    				studentDTO.getLastName(),studentDTO.getAge(),studentDTO.getGender());
	    	} else {
	    		student = new Student(studentDTO.getFirstName(),studentDTO.getLastName(),
	    				studentDTO.getAge(),studentDTO.getGender());
	    	}
	    }
	    else {
	    	student = new Student(studentDTO.getFirstName(),studentDTO.getLastName(),
    				studentDTO.getAge(),studentDTO.getGender());
	    }

		System.out.println("student :: " + student);
		return student;
	    
	}

	/**
	 * Merging same old and new object data
	 * If new object element are null keep the old element data
	 * @param newObject
	 * @param oldObject
	 * @return Student DTO
	 */
	private StudentDTO mergeObjectData(StudentDTO newObject, StudentDTO oldObject) {
		Student merged = new Student(
				newObject.getFirstName() != null ? newObject.getFirstName() : oldObject.getFirstName(),
				newObject.getLastName() != null ? newObject.getLastName() : oldObject.getLastName(),
				newObject.getAge() != null ? newObject.getAge() : oldObject.getAge(),
				newObject.getGender() != null ? newObject.getGender() : oldObject.getGender()
				);
		return convertToDTO(merged);
	}
	
}
