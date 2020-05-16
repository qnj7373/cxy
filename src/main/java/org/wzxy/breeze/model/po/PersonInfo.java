package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PersonInfo implements Serializable {


  private int personId;
  private int studentId;
  private String password;
  private String personName;
  private String major;
  private int depId;
  private int classId;
  private String grade;
  private String tel;
  private String email;
  private byte[] photo;
  private int labId;
  private String hirSta;



public PersonInfo() {
	super();
	// TODO Auto-generated constructor stub
}


public int getPersonId() {
	return personId;
}
public void setPersonId(int personId) {
	this.personId = personId;
}
public int getStudentId() {
	return studentId;
}
public void setStudentId(int studentId) {
	this.studentId = studentId;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getPersonName() {
	return personName;
}
public void setPersonName(String personName) {
	this.personName = personName;
}
public String getMajor() {
	return major;
}
public void setMajor(String major) {
	this.major = major;
}
public int getDepId() {
	return depId;
}
public void setDepId(int depId) {
	this.depId = depId;
}
public int getClassId() {
	return classId;
}
public void setClassId(int classId) {
	this.classId = classId;
}
public String getGrade() {
	return grade;
}
public void setGrade(String grade) {
	this.grade = grade;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public byte[] getPhoto() {
	return photo;
}
public void setPhoto(byte[] photo) {
	this.photo = photo;
}

public int getLabId() {
	return labId;
}
public void setLabId(int labId) {
	this.labId = labId;
}
public String getHirSta() {
	return hirSta;
}
public void setHirSta(String hirSta) {
	this.hirSta = hirSta;
}

}
