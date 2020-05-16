package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Enlist implements Serializable {
  private int enlistId;
  private String postName;
  private String personName;
  private String skill;
  private int personId;
  private int planId;
  private Date appDate;
  private String signSta;

public Enlist() {
	super();
	// TODO Auto-generated constructor stub
}


public Enlist(int enlistId, String postName, String personName, String skill, int personId, int planId, Date appDate,
		String signSta) {
	super();
	this.enlistId = enlistId;
	this.postName = postName;
	this.personName = personName;
	this.skill = skill;
	this.personId = personId;
	this.planId = planId;
	this.appDate = appDate;
	this.signSta = signSta;
}


public int getEnlistId() {
	return enlistId;
}
public void setEnlistId(int enlistId) {
	this.enlistId = enlistId;
}
public String getPostName() {
	return postName;
}
public void setPostName(String postName) {
	this.postName = postName;
}
public String getSkill() {
	return skill;
}
public void setSkill(String skill) {
	this.skill = skill;
}
public int getPersonId() {
	return personId;
}
public void setPersonId(int personId) {
	this.personId = personId;
}
public Date getAppDate() {
	return appDate;
}
public void setAppDate(Date appDate) {
	this.appDate = appDate;
}
public String getSignSta() {
	return signSta;
}
public void setSignSta(String signSta) {
	this.signSta = signSta;
}
public int getPlanId() {
	return planId;
}
public void setPlanId(int planId) {
	this.planId = planId;
}
public String getPersonName() {
	return personName;
}
public void setPersonName(String personName) {
	this.personName = personName;
}


}
