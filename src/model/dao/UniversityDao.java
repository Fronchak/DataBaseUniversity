package model.dao;

import java.util.List;

import model.entities.University;

public interface UniversityDao {

	void insert(University university);
	
	void update(University university);
	
	University findById(Integer id);
	
	List<University> getAllUniversity();
	
	void deleteById(Integer id);
	
}
