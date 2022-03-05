package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.UniversityDao;
import model.entities.University;

public class UniversityDaojdbc implements UniversityDao{

	private Connection conn;
	
	public UniversityDaojdbc(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(University university) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO FACULDADE "
					+ "(NOME, "
					+ "UF) "
					+ "VALUES (?,?)");
			ps.setString(1, university.getName());
			ps.setString(2, university.getUf());
			int rowsAffect = ps.executeUpdate();
			System.out.println("RowsAffect: " + rowsAffect);
		}
		catch(SQLException e) {
			throw new DBException("Error in insert University: " + e.getMessage());
		}
		finally {
			DB.closePreparedStatement(ps);
		}	
	}

	@Override
	public void update(University university) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public University findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<University> getAllUniversity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
