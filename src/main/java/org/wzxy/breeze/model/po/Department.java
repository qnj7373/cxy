package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Department implements Serializable {
  private int depId;
  private String depName;

public Department() {
	super();
	// TODO Auto-generated constructor stub
}


public Department(int depId, String depName) {
	super();
	this.depId = depId;
	this.depName = depName;
}


public int getDepId() {
	return depId;
}
public void setDepId(int depId) {
	this.depId = depId;
}
public String getDepName() {
	return depName;
}
public void setDepName(String depName) {
	this.depName = depName;
}



}
