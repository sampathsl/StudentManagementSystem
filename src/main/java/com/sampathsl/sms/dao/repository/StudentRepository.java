package com.sampathsl.sms.dao.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.sampathsl.sms.entity.Student;

public interface StudentRepository extends MongoRepository<Student, Long> {

	void delete(Student deleted);
	 
    List<Student> findAll();
 
    Optional<Student> findById(String id);
 
    Student save(Student saved);
	
}
