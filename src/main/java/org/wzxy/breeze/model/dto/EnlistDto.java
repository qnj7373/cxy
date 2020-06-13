package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.Enlist;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class EnlistDto implements Serializable {

	  private int enlistId;
	  private int planId;
	  private String planName;
	  private String labName;
	  private String postName;
	  private String skill;
	  private int personId;
	  private String personName;
	  private Date appDate;
	  private String signSta;

	  private int nowPage;
	     private int pageSize;


		public EnlistDto() {
			super();
			// TODO Auto-generated constructor stub
		}

	public EnlistDto(Enlist  enlist) {
		this.enlistId = enlist.getEnlistId();
		this.planId = enlist.getPlanId();
		this.postName =enlist.getPostName();
		this.skill = enlist.getSkill();
		this.personId =enlist.getPersonId();
		this.personName = enlist.getPersonName();
		this.appDate = enlist.getAppDate();
		this.signSta = enlist.getSignSta();
	}


	public EnlistDto(int enlistId, int planId, String planName, String labName, String postName, String skill, int personId, String personName, Date appDate, String signSta, int nowPage, int pageSize) {
		this.enlistId = enlistId;
		this.planId = planId;
		this.planName = planName;
		this.labName = labName;
		this.postName = postName;
		this.skill = skill;
		this.personId = personId;
		this.personName = personName;
		this.appDate = appDate;
		this.signSta = signSta;
		this.nowPage = nowPage;
		this.pageSize = pageSize;
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
		public int getPlanId() {
			return planId;
		}
		public void setPlanId(int planId) {
			this.planId = planId;
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
		public String getPersonName() {
			return personName;
		}
		public void setPersonName(String personName) {
			this.personName = personName;
		}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}
}
