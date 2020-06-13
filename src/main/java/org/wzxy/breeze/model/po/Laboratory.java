package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.LaboratoryDto;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class Laboratory implements Serializable {


 private int labId;
 private String labName;
 private int depId;
 private int techId;

 private List<Maintenance> maintenances;
 private List<WorkRecord> workRecords;
 private List<Plan> plans;

public Laboratory() {
	super();
	// TODO Auto-generated constructor stub
}


	public Laboratory(LaboratoryDto lab) {
		this.labId = lab.getLabId();
		this.labName = lab.getLabName();
		this.depId = lab.getDepId();
		this.techId = lab.getTechId();
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


	public List<Maintenance> getMaintenances() {
		return maintenances;
	}

	public void setMaintenances(List<Maintenance> maintenances) {
		this.maintenances = maintenances;
	}

	public List<WorkRecord> getWorkRecords() {
		return workRecords;
	}

	public void setWorkRecords(List<WorkRecord> workRecords) {
		this.workRecords = workRecords;
	}

	public List<Plan> getPlans() {
		return plans;
	}

	public void setPlans(List<Plan> plans) {
		this.plans = plans;
	}
}
