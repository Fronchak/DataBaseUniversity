package model.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.entities.Student;
import model.entities.University;

public class ImplementsEntities {

	public static University implementUniversity(ResultSet rs) throws SQLException {
		University obj = new University();
		obj.setId(rs.getInt("F.IDFACULDADE"));
		obj.setName(rs.getString("F.NOME"));
		obj.setUf(rs.getString("F.UF"));
		return obj;
	}
	
	public static Student implementStudent(ResultSet rs, University university) throws SQLException {
		Student obj = new Student();
		obj.setId(rs.getInt("A.IDALUNO"));
		obj.setName(rs.getString("A.NOME"));
		obj.setCpf(rs.getString("A.CPF"));
		obj.setBirthDate(rs.getDate("A.DATA_DE_NASCIMENTO"));
		obj.setUniversity(university);
		return obj;
	}
	
}
