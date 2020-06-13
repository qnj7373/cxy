package org.wzxy.breeze.model.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class StudentDto implements Serializable {
	 private int studentId;
     private String studentName;
     private int classId;
     private String Date;
     private String attStatus;
     private int nowPage;
     private int pageSize;
     private int sId;
     private int number;
     private int randomNum;
     private String stuId;


	public StudentDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	public StudentDto(int studentId, String studentName, int classId, String date, String attStatus,
			int nowPage, int pageSize, int sId, int number, int randomNum, String stuId) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.classId = classId;
		Date = date;
		this.attStatus = attStatus;
		this.nowPage = nowPage;
		this.pageSize = pageSize;
		this.sId = sId;
		this.number = number;
		this.randomNum = randomNum;
		this.stuId = stuId;
	}



	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
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



	public int getNowPage() {
		return nowPage;
	}



	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}



	public int getPageSize() {
		return pageSize;
	}



	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}



	public String getStuId() {
		return stuId;
	}

	public void setStuId(String stuId) {
		this.stuId = stuId;
	}

	public String getAttStatus() {
		return attStatus;
	}

	public void setAttStatus(String attStatus) {
		this.attStatus = attStatus;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}


	public int getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(int randomNum) {
		this.randomNum = randomNum;
	}


	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
