package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Student implements Serializable {
	private int studentId;
	private String studentName;
	private int classId;


	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(int studentId, String studentName, int classId) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.classId = classId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}


}
