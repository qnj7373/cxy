package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.PlanDto;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Plan implements Serializable {
  private  int planId;
  private  String planName;
  private  String requirement;
  private  String techName;
  private  String planSta;
  private  int number;
  private  int depId;
  private  String depName;
  private int labId;
  private String labName;
  private  int techId;


public Plan() {
	super();
	// TODO Auto-generated constructor stub
}
	public Plan(PlanDto plan) {
		this.planId = plan.getPlanId();
		this.planName = plan.getPlanName();
		this.requirement = plan.getRequirement();
		this.number = plan.getNumber();
		this.labId = plan.getLabId();
		this.labName = plan.getLabName();
		this.depId = plan.getDepId();
		this.depName = plan.getDepName();
		this.techId = plan.getTechId();
		this.techName = plan.getTechName();
		this.planSta = plan.getPlanSta();
	}

public Plan(int planId, String planName, String requirement, String techName, String planSta, int number, int depId,
		String depName, int labId, String labName, int techId) {
	super();
	this.planId = planId;
	this.planName = planName;
	this.requirement = requirement;
	this.techName = techName;
	this.planSta = planSta;
	this.number = number;
	this.depId = depId;
	this.depName = depName;
	this.labId = labId;
	this.labName = labName;
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
public int getPlanId() {
	return planId;
}
public void setPlanId(int planId) {
	this.planId = planId;
}
public String getPlanName() {
	return planName;
}
public void setPlanName(String planName) {
	this.planName = planName;
}
public String getRequirement() {
	return requirement;
}
public void setRequirement(String requirement) {
	this.requirement = requirement;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}
public int getDepId() {
	return depId;
}
public void setDepId(int depId) {
	this.depId = depId;
}

public String getPlanSta() {
	return planSta;
}
public void setPlanSta(String planSta) {
	this.planSta = planSta;
}

public String getDepName() {
	return depName;
}
public void setDepName(String depName) {
	this.depName = depName;
}
public int getTechId() {
	return techId;
}
public void setTechId(int techId) {
	this.techId = techId;
}
public String getTechName() {
	return techName;
}
public void setTechName(String techName) {
	this.techName = techName;
}



}
