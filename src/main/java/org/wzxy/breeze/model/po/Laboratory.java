package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Laboratory implements Serializable {
 private int labId;
 private String labName;
 private int depId;
 private int techId;




public Laboratory() {
	super();
	// TODO Auto-generated constructor stub
}




public Laboratory(int labId, String labName, int depId, int techId) {
	super();
	this.labId = labId;
	this.labName = labName;
	this.depId = depId;
	this.techId = techId;
}




public int getLabId() {
	return labId;
}
public void setLabId(int labId) {
	this.labId = labId;
}
public String getLabName() {
	return labName;
}
public void setLabName(String labName) {
	this.labName = labName;
}
public int getDepId() {
	return depId;
}
public void setDepId(int depId) {
	this.depId = depId;
}
public int getTechId() {
	return techId;
}
public void setTechId(int techId) {
	this.techId = techId;
}



}
