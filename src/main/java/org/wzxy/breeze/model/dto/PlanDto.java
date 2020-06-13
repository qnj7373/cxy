package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.Plan;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PlanDto implements Serializable {

	  private  int planId;
	  private  String planName;
	  private  String requirement;
	  private  int number;
	  private int labId;
	  private String labName;
	  private  int depId;
	  private  String depName;
	  private  int techId;

	  private int personId;
	  private String personName;

	  private  String techName;
	  private  String planSta;
	  private int nowPage;
	   private int pageSize;

	public PlanDto (Plan plan) {
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

	public PlanDto(int planId, String planName, String requirement, int number, int labId, String labName, int depId, String depName, int techId, int personId, String personName, String techName, String planSta, int nowPage, int pageSize) {
		this.planId = planId;
		this.planName = planName;
		this.requirement = requirement;
		this.number = number;
		this.labId = labId;
		this.labName = labName;
		this.depId = depId;
		this.depName = depName;
		this.techId = techId;
		this.personId = personId;
		this.personName = personName;
		this.techName = techName;
		this.planSta = planSta;
		this.nowPage = nowPage;
		this.pageSize = pageSize;
	}

	public PlanDto() {
		super();
		// TODO Auto-generated constructor stub
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
	public String getTechName() {
		return techName;
	}
	public void setTechName(String techName) {
		this.techName = techName;
	}


	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}
}
