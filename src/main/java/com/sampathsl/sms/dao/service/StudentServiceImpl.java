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

	@Override
	public StudentDTO create(StudentDTO student) throws StudentNotFountException {
		Student persisted = studentRepository.save(convertToEntity(student));
        return convertToDTO(persisted);
	}

	@Override
	public StudentDTO delete(String id) throws StudentNotFountException {
		Student deleted = findStudentById(id);
		studentRepository.delete(deleted);
        return convertToDTO(deleted);
	}

	@Override
	public List<StudentDTO> findAll() {
		List<Student> students = StreamSupport
		 .stream(studentRepository
				 .findAll()
				 .spliterator(), false)
		 .collect(Collectors.toList());
	     return convertToDTOs(students);
	}

	@Override
	public StudentDTO findById(String id) throws StudentNotFountException {
		Student student = findStudentById(id);
        return convertToDTO(student);
	}

	@Override
	public StudentDTO update(StudentDTO newStudentDTO, StudentDTO oldStudentDTO) throws StudentNotFountException {
		StudentDTO merged = mergeObjectData(newStudentDTO, oldStudentDTO);
		Student updatedStudent = new Student(merged.getId(),merged.getFirstName(),
				merged.getLastName(),merged.getDob(),
				merged.getAge(),merged.getGender(),merged.getYearEnrolled());
		Student updated = studentRepository.save(updatedStudent);
        return convertToDTO(updated);
	}
	
	private StudentDTO convertToDTO(Student model) {
		StudentDTO dto = new StudentDTO();
        dto.setId(model.getId());
        dto.setFirstName(model.getFirstName());
        dto.setLastName(model.getLastName());
        dto.setGender(model.getGender());
        dto.setDob(model.getDob());
        dto.setAge(model.getAge());
        return dto;
	}
	
	private List<StudentDTO> convertToDTOs(List<Student> models) {
        return models.stream()
                .map(this::convertToDTO)
                .collect(toList());
    }
	
	private Student findStudentById(String id) throws StudentNotFountException {
        Optional<Student> result = studentRepository.findById(id);
        return result.orElseThrow(() -> new StudentNotFountException(id));
 
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
	    				studentDTO.getLastName(),studentDTO.getDob(),studentDTO.getAge(),studentDTO.getGender(),studentDTO.getYearEnrolled());
	    	} else {
	    		student = new Student(studentDTO.getFirstName(),studentDTO.getLastName(),studentDTO.getDob(),
	    				studentDTO.getAge(),studentDTO.getGender(),studentDTO.getYearEnrolled());
	    	}
	    }
	    else {
	    	student = new Student(studentDTO.getFirstName(),studentDTO.getLastName(),studentDTO.getDob(),
    				studentDTO.getAge(),studentDTO.getGender(),studentDTO.getYearEnrolled());
	    }
	    
	    return student;
	    
	}
	
	private StudentDTO mergeObjectData(StudentDTO newObject, StudentDTO oldObject) {
		Student merged = new Student(
				newObject.getFirstName() != null ? newObject.getFirstName() : oldObject.getFirstName(),
				newObject.getLastName() != null ? newObject.getLastName() : oldObject.getLastName(),
				newObject.getDob() != null ? newObject.getDob() : oldObject.getDob(),
				newObject.getAge() != null ? newObject.getAge() : oldObject.getAge(),
				newObject.getGender() != null ? newObject.getGender() : oldObject.getGender(),
				newObject.getYearEnrolled() != null ? newObject.getYearEnrolled() : oldObject.getYearEnrolled()
				);
		return convertToDTO(merged);
	}
	
}
