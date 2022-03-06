package model.dao;

import java.util.List;

import model.entities.Student;
import model.entities.University;

public interface StudentDao {

	void insert(Student student);
	
	void update(Student student);
	
	Student findById(Integer id);
	
	List<Student> findByUniversity(University university);
	
	List<Student> getAllStudents();
	
	void deleteById(Integer id);
}
