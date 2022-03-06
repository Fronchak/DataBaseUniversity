package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.StudentDao;
import model.entities.Student;
import model.entities.University;

public class StudentDaoJDBC implements StudentDao {

	private Connection conn;
	
	public StudentDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Student student) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("INSERT INTO ALUNO "
					+ "(NOME, CPF, DATA_DE_NASCIMENTO, ID_FACULDADE) "
					+ "VALUES "
					+ "(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, student.getName());
			ps.setString(2, student.getCpf());
			ps.setDate(3, new java.sql.Date(student.getBirthDate().getTime()));
			ps.setInt(4, student.getUniversity().getId());
			Integer rowsAffect = ps.executeUpdate();
			if(rowsAffect.equals(0)) {
				throw new DBException("Fail in insert student");
			}
			rs = ps.getGeneratedKeys();
			if(rs.next()) {
				student.setId(rs.getInt(1));
			}
			else {
				throw new DBException("Fail in inset ID to student!");
			}
		}
		catch(SQLException e) {
			throw new DBException("Error in insert student: " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closePreparedStatement(ps);
		}
	}

	@Override
	public void update(Student student) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Student findById(Student student) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> findByUniversity(University university) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
