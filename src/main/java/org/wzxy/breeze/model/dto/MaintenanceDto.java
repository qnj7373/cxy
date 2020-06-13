package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.Maintenance;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class MaintenanceDto implements Serializable {


	  private int mainId;
	  private int labId;
	  private String labName;
	  private int personId;
	  private String depName;
	  private int equId;
	  private String equName;
	  private String equtype;
	  private String description;
	  private Date reportTime;
	  private String personName;
	  private String reportDate;

	  private int nowPage;
	     private int pageSize;

	public MaintenanceDto() {
	}

	public MaintenanceDto (Maintenance MT) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(MT!=null) {
			this.mainId = MT.getMainId();
			this.labId = MT.getLabId();
			this.personId =MT.getPersonId();
			this.equId = MT.getEquId();
			this.equName = MT.getEquName();
			this.equtype = MT.getEqutype();
			this.description = MT.getDescription();
			this.reportTime = MT.getReportTime();
			this.personName = MT.getPersonName();
			this.reportDate=sdf.format(MT.getReportTime());
		}

	}

	public int getMainId() {
			return mainId;
		}
		public void setMainId(int mainId) {
			this.mainId = mainId;
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
		public String getReportDate() {
			return reportDate;
		}
		public void setReportDate(String reportDate) {
			this.reportDate = reportDate;
		}

	public String getLabName() {
		return labName;
	}

	public void setLabName(String labName) {
		this.labName = labName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}
}
