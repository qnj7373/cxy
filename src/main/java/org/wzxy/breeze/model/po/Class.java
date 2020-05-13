package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Class implements Serializable {
     private int classId;
     private String className;
     private int depId;


	public Class() {
		super();
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

}
