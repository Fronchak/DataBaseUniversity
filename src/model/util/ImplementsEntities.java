package model.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.entities.University;

public class ImplementsEntities {

	public static University implementUniversity(ResultSet rs) throws SQLException {
		University obj = new University();
		obj.setId(rs.getInt("IDFACULDADE"));
		obj.setName(rs.getString("NOME"));
		obj.setUf(rs.getString("UF"));
		return obj;
	}
	
}
