package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DBException;
import model.dao.DaoFactory;
import model.dao.StudentDao;
import model.dao.UniversityDao;
import model.entities.Student;
import model.entities.University;

public class Program {

	public static void main(String[] args) {
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			UniversityDao universityDao = DaoFactory.getUniversityDao();
			StudentDao studentDao = DaoFactory.getStudentDao();
			/*
			University uni = universityDao.findById(2);
			Student student = new Student("Alice Fronchak","63802082915",sdf.parse("05/10/2000"),uni);
			System.out.println("Before insert: " + student);
			studentDao.insert(student);
			System.out.println("After insert:" + student);
			*/
			Student student = studentDao.findById(5);
			System.out.println(student);
			
			
		}
		catch(DBException e) {
			System.out.println(e.getMessage());
		}
		finally {
			DB.closeConnection();
		}
		
	}
	
}
