package model.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DBException;
import model.dao.StudentDao;
import model.entities.Student;
import model.entities.University;
import model.util.ImplementsEntities;

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
		PreparedStatement ps = null;
		
		try {
			ps = conn.prepareStatement("UPDATE ALUNO SET "
					+ "NOME = ?, "
					+ "CPF = ?, "
					+ "DATA_DE_NASCIMENTO = ?, "
					+ "ID_FACULDADE = ? "
					+ "WHERE IDALUNO = ?");
			ps.setString(1, student.getName());
			ps.setString(2, student.getCpf());
			ps.setDate(3, new java.sql.Date(student.getBirthDate().getTime()));
			ps.setInt(4, student.getUniversity().getId());
			ps.setInt(5, student.getId());
			int rowsAffect = ps.executeUpdate();
			if(rowsAffect == 0) {
				throw new DBException("Unable to find student with this id: " + student.getId());
			}	
		}
		catch(SQLException e) {
			throw new DBException("Error in update student: " + e.getMessage());
		}
		finally {
			DB.closePreparedStatement(ps);
		}
	}

	@Override
	public Student findById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement("SELECT "
					+ "A.IDALUNO, "
					+ "A.NOME, "
					+ "A.CPF, "
					+ "A.DATA_DE_NASCIMENTO, "
					+ "F.IDFACULDADE, "
					+ "F.NOME,"
					+ "F.UF "
					+ "FROM ALUNO A "
					+ "INNER JOIN FACULDADE F "
					+ "ON A.ID_FACULDADE = F.IDFACULDADE "
					+ "WHERE IDALUNO = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if(rs.next()) {
				University obj =ImplementsEntities.implementUniversity(rs);
				return ImplementsEntities.implementStudent(rs, obj);
			}
			else {
				throw new DBException("Can't find any student with this id: " + id);
			}
		}
		catch(SQLException e) {
			throw new DBException("Error in find Student: " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closePreparedStatement(ps);
		}
	}

	@Override
	public List<Student> findByUniversity(University university) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();

			return null;	
	}

	@Override
	public List<Student> getAllStudents() {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> list = new ArrayList<>();
		List<University> list2 = new ArrayList<>();
		Map<Integer, University> map = new HashMap<>();
		
		try {
			ps = conn.prepareStatement("SELECT "
					+ "A.IDALUNO, "
					+ "A.NOME, "
					+ "A.CPF, "
					+ "A.DATA_DE_NASCIMENTO, "
					+ "F.IDFACULDADE, "
					+ "F.NOME, "
					+ "F.UF "
					+ "FROM ALUNO A "
					+ "INNER JOIN FACULDADE F "
					+ "ON A.ID_FACULDADE = F.IDFACULDADE");
			rs = ps.executeQuery();
			while(rs.next()) {
				
				University uni = map.get(rs.getInt("F.IDFACULDADE"));
				
				if(uni == null) {
					uni = ImplementsEntities.implementUniversity(rs);
					map.put(rs.getInt("F.IDFACULDADE"), uni);
					list2.add(uni);
				}
				Student st = ImplementsEntities.implementStudent(rs, uni);
				list.add(st);
				uni.addStudent(st);
			}
			System.out.println("PRINT LIST OF UNIVERSITIES: ");
			for(University univ : list2) {
				
				univ.printList();
			}
			System.out.println("END");
			return list;
		}
		catch(SQLException e) {
			throw new DBException("Error in getAllStudents: " + e.getMessage());
		}
		finally {
			DB.closeResultSet(rs);
			DB.closePreparedStatement(ps);
		}
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
