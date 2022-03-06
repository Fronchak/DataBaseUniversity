package model.entities;

import java.util.ArrayList;
import java.util.List;

public class University {

	private Integer id;
	private String name;
	private String uf;

	List<Student> list = new ArrayList<>();
	
	public University(){
	}
	
	public University(String name, String uf) {
		this.name = name;
		this.uf = uf;
	}
	
	public University(Integer id, String name, String uf) {
		this.id = id;
		this.name = name;
		this.uf = uf;
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

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
	
	public void addStudent(Student student) {
		list.add(student);
	}
	
	public void printList() {
		System.out.println("UNIVERSITY: " + name);
		list.stream().forEach(System.out::println);
	}
	
	@Override
	public String toString() {
		return "ID: " + id + ", Name: " + name + ", UF: " + uf;
	}
	
}
