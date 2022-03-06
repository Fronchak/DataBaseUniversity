package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
			
			University uni = universityDao.findById(1);
			Student student = new Student("Felipe","565154915",sdf.parse("01/07/1984"),uni);
			//System.out.println("Before insert: " + student);
			//studentDao.insert(student);
			//System.out.println("After insert:" + student);
			
			//Student student = studentDao.findById(5);
			//System.out.println(student);
			
			Student studentTest = studentDao.findById(3);
			studentTest.setName("Ana Beatriz");
			studentDao.update(studentTest);
			
			List<Student> list = studentDao.getAllStudents();
			list.stream().forEach(System.out::println);
			
			
			
		}
		catch(DBException e) {
			System.out.println(e.getMessage());
		}
		catch(ParseException e) {
			System.out.println("Invalid input format!");
		}
		finally {
			DB.closeConnection();
		}
		
	}
	
}
