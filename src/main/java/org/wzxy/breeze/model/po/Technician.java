package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.TechnicianDto;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Technician implements Serializable {

  private int techId;
  private String techName;
  private String workSta;
  private int depId;


public Technician() {
	super();
	// TODO Auto-generated constructor stub
}


	public Technician (TechnicianDto Tech) {
		this.techId = Tech.getTechId();
		this.techName = Tech.getTechName();
		this.workSta = Tech.getWorkSta();
		this.depId = Tech.getDepId();
	}
public Technician(int techId, String techName, String workSta, int depId) {
	super();
	this.techId = techId;
	this.techName = techName;
	this.workSta = workSta;
	this.depId = depId;
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
public int getDepId() {
	return depId;
}
public void setDepId(int depId) {
	this.depId = depId;
}
public String getWorkSta() {
	return workSta;
}
public void setWorkSta(String workSta) {
	this.workSta = workSta;
}


}
