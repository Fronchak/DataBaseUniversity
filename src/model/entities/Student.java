package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {

	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	
	private Integer id;
	private String name;
	private String cpf;
	private Date birthDate;
	private University university;
	
	public Student(){
	}

	public Student(String name, String cpf, Date birthDate, University university) {
		super();
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.university = university;
	}

	public Student(Integer id, String name, String cpf, Date birthDate, University university) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.birthDate = birthDate;
		this.university = university;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}
	
	@Override
	public String toString() {
		return  "ID: " + id + ", Name: " + name + ", CPF: " + cpf + ", Birth Date: " + sdf.format(birthDate) + 
				", University: " + university;
	}
}
