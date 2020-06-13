package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.ClassDto;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Class implements Serializable {
     private int classId;
     private String className;
     private int depId;
     private Department department;


	public Class() {
		super();
	}

	public Class(ClassDto cla) {
		if(cla!=null) {
			this.classId=cla.getClassId();
			this.className=cla.getClassName();
			this.depId=cla.getDepId();
		}
	}

	public Class(int classId, String className, int depId) {
		super();
		this.classId = classId;
		this.className = className;
		this.depId = depId;
	}

	public int getClassId() {
		return classId;
	}

	public void setClassId(int classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public int getDepId() {
		return depId;
	}

	public void setDepId(int depId) {
		this.depId = depId;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
