package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Maintenance implements Serializable {

  private int mainId;
  private int labId;
  private int personId;
  private int equId;
  private String equName;
  private String equtype;
  private String description;
  private Date reportTime;
  private String personName;



public Maintenance() {
	super();
	// TODO Auto-generated constructor stub
}





public Maintenance(int mainId, int labId, int personId, int equId, String equName, String equtype, String description,
		Date reportTime, String personName) {
	super();
	this.mainId = mainId;
	this.labId = labId;
	this.personId = personId;
	this.equId = equId;
	this.equName = equName;
	this.equtype = equtype;
	this.description = description;
	this.reportTime = reportTime;
	this.personName = personName;
}





public int getLabId() {
	return labId;
}


public void setLabId(int labId) {
	this.labId = labId;
}


public int getPersonId() {
	return personId;
}


public void setPersonId(int personId) {
	this.personId = personId;
}


public int getMainId() {
	return mainId;
}
public void setMainId(int mainId) {
	this.mainId = mainId;
}
public int getEquId() {
	return equId;
}
public void setEquId(int equId) {
	this.equId = equId;
}
public String getEquName() {
	return equName;
}
public void setEquName(String equName) {
	this.equName = equName;
}
public String getEqutype() {
	return equtype;
}
public void setEqutype(String equtype) {
	this.equtype = equtype;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public Date getReportTime() {
	return reportTime;
}
public void setReportTime(Date reportTime) {
	this.reportTime = reportTime;
}
public String getPersonName() {
	return personName;
}
public void setPersonName(String personName) {
	this.personName = personName;
}

}
