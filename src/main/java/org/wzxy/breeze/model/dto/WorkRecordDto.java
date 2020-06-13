package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.WorkRecord;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class WorkRecordDto implements Serializable {

	  private int recordId;
	  private String weeklyTimes;
	  private int labId;
		@JsonFormat(
			 pattern = "yyyy-MM-dd",
			 timezone = "GMT+8"
			 )
	  private Date date;
	  private String recordDate;
	  private String week;
	  private int personId;
	  private String personName;
	  private String Health;
	  private String equipment;
	  private Timestamp sysTime;
	  private List<WorkRecordDto> workDates;

	   private int nowPage;
	     private int pageSize;

	public WorkRecordDto() {
	}

	public WorkRecordDto (WorkRecord workRe) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			this.recordId = workRe.getRecordId();
			this.weeklyTimes = workRe.getWeeklyTimes();
			this.labId = workRe.getLabId();
			this.date = workRe.getDate();
			this.recordDate =sdf.format(workRe.getDate());
			this.week = workRe.getWeek();
			this.personId = workRe.getPersonId();
			this.personName = workRe.getPersonName();
			this.Health = workRe.getHealth();
			this.equipment = workRe.getEquipment();
			this.sysTime = workRe.getSysTime();
	}


	public WorkRecordDto(int recordId, String weeklyTimes, int labId, Date date, String recordDate, String week, int personId, String personName, String health, String equipment, Timestamp sysTime, List<WorkRecordDto> workDates, int nowPage, int pageSize) {
		this.recordId = recordId;
		this.weeklyTimes = weeklyTimes;
		this.labId = labId;
		this.date = date;
		this.recordDate = recordDate;
		this.week = week;
		this.personId = personId;
		this.personName = personName;
		Health = health;
		this.equipment = equipment;
		this.sysTime = sysTime;
		this.workDates = workDates;
		this.nowPage = nowPage;
		this.pageSize = pageSize;
	}

	public int getRecordId() {
			return recordId;
		}
		public void setRecordId(int recordId) {
			this.recordId = recordId;
		}
		public String getWeeklyTimes() {
			return weeklyTimes;
		}
		public void setWeeklyTimes(String weeklyTimes) {
			this.weeklyTimes = weeklyTimes;
		}
		public int getLabId() {
			return labId;
		}
		public void setLabId(int labId) {
			this.labId = labId;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		public String getWeek() {
			return week;
		}
		public void setWeek(String week) {
			this.week = week;
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
		public String getHealth() {
			return Health;
		}
		public void setHealth(String health) {
			Health = health;
		}
		public String getEquipment() {
			return equipment;
		}
		public void setEquipment(String equipment) {
			this.equipment = equipment;
		}
		public Timestamp getSysTime() {
			return sysTime;
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
		public void setSysTime(Timestamp sysTime) {
			this.sysTime = sysTime;
		}
		public String getRecordDate() {
			return recordDate;
		}
		public void setRecordDate(String recordDate) {
			this.recordDate = recordDate;
		}

	public List<WorkRecordDto> getWorkDates() {
		return workDates;
	}

	public void setWorkDates(List<WorkRecordDto> workDates) {
		this.workDates = workDates;
	}
}
