package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.Laboratory;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class LaboratoryDto implements Serializable {
	 private int labId;
	 private String labName;
	 private int depId;
	 private String depName;
	 private int techId;
	 private String techName;

	 private int nowPage;
     private int pageSize;


	public LaboratoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LaboratoryDto (Laboratory lab) {
		this.labId = lab.getLabId();
		this.labName = lab.getLabName();
		this.depId = lab.getDepId();
		this.techId = lab.getTechId();
	}


	public LaboratoryDto(int labId, String labName, int depId, String depName, int techId, String techName, int nowPage, int pageSize) {
		this.labId = labId;
		this.labName = labName;
		this.depId = depId;
		this.depName = depName;
		this.techId = techId;
		this.techName = techName;
		this.nowPage = nowPage;
		this.pageSize = pageSize;
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
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}

}
