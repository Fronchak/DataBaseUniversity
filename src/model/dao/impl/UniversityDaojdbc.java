package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.UniversityDao;
import model.entities.University;
import model.util.ImplementsEntities;

public class UniversityDaoJDBC implements UniversityDao{

	private Connection conn;
	
	public UniversityDaoJDBC(Connection conn) {
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
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("UPDATE FACULDADE SET "
					+ "NOME = ?, "
					+ "UF = ? "
					+ "WHERE IDFACULDADE = ?");
			ps.setString(1,university.getName());
			ps.setString(2, university.getUf());
			ps.setInt(3, university.getId());
			int rowsAffect = ps.executeUpdate();
			if(rowsAffect == 0) {
				throw new DBException("Unable to find id: " + university.getId());
			}
		}
		catch(SQLException e) {
			throw new DBException("Error in updateUniversity: " + e.getMessage());
		}
		finally {
			DB.closePreparedStatement(ps);
		}
	}

	@Override
	public University findById(Integer id) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT "
					+ "F.IDFACULDADE, "
					+ "F.NOME, "
					+ "F.UF "
					+ "FROM FACULDADE F "
					+ "WHERE IDFACULDADE = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				return ImplementsEntities.implementUniversity(rs);
			}
			else {
				throw new DBException("Can't find this id: " + id);
			}	
		}
		catch(SQLException e) {
			throw new DBException("Error in findByID University: " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closePreparedStatement(ps);
		}
	}

	@Override
	public List<University> getAllUniversity() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<University> list = new ArrayList<>();	
		try {
			ps = conn.prepareStatement("SELECT "
					+ "F.IDFACULDADE, "
					+ "F.NOME, "
					+ "F.UF "
					+ "FROM FACULDADE F");
			rs = ps.executeQuery();
			while(rs.next()) {
				list.add(ImplementsEntities.implementUniversity(rs));
			}
			return list;
		}
		catch(SQLException e) {
			throw new DBException("Error in getAllUniversity: " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closePreparedStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("DELETE FROM FACULDADE "
					+ "WHERE IDFACULDADE = ?");
			ps.setInt(1, id);
			int rowsAffect = ps.executeUpdate();
			if(rowsAffect == 0) {
				throw new DBException("Unable to find this id: " + id);
			}
		}
		catch(SQLException e ) {
			throw new DBException("Error in DeleteUniversity: " + e.getMessage());
		}
		finally {
			DB.closePreparedStatement(ps);
		}
	}	
}
