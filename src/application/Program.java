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
			//University university = new University("UTFPR","PR");
			//universityDao.insert(university);
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
