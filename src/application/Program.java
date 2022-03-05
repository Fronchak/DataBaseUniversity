package application;

import java.util.List;

import db.DB;
import db.DBException;
import model.dao.DaoFactory;
import model.dao.UniversityDao;
import model.entities.University;

public class Program {

	public static void main(String[] args) {
		
		try {
			UniversityDao universityDao = DaoFactory.getUniversityDao();
			//University university = new University("UFRJ","RJ");
			//universityDao.insert(university);
			
			University univer3 = universityDao.findById(3);
			System.out.println("FindById: " + univer3);
			//univer3.setName("USP");
			//univer3.setId(10);
			universityDao.deleteById(3);
			
			List<University> list = universityDao.getAllUniversity();
			list.stream().forEach(System.out::println);
			
		}
		catch(DBException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeConnection();
		}
		
	}
	
}
