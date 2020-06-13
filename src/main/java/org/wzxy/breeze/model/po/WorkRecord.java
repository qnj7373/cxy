package org.wzxy.breeze.model.po;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.dto.WorkRecordDto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class WorkRecord implements Serializable {


  private int recordId;
  private String weeklyTimes;
  private int labId;
  private Date date;
  private String week;
  private int personId;
  private String personName;
  private String Health;
  private String equipment;
  private Timestamp sysTime;




public WorkRecord() {
	super();
	// TODO Auto-generated constructor stub
}
	public WorkRecord (WorkRecordDto workRe) {

		this.recordId = workRe.getRecordId();
		this.weeklyTimes = workRe.getWeeklyTimes();
		this.labId = workRe.getLabId();
		this.date = workRe.getDate();
		this.week = workRe.getWeek();
		this.personId = workRe.getPersonId();
		this.personName = workRe.getPersonName();
		this.Health = workRe.getHealth();
		this.equipment = workRe.getEquipment();
		this.sysTime = workRe.getSysTime();
	}

public WorkRecord(int recordId, String weeklyTimes, int labId, Date date, String week, int personId, String health,
		String equipment, Timestamp sysTime) {
	super();
	this.recordId = recordId;
	this.weeklyTimes = weeklyTimes;
	this.labId = labId;
	this.date = date;
	this.week = week;
	this.personId = personId;
	Health = health;
	this.equipment = equipment;
	this.sysTime = sysTime;
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
public void setSysTime(Timestamp sysTime) {
	this.sysTime = sysTime;
}
public String getPersonName() {
	return personName;
}
public void setPersonName(String personName) {
	this.personName = personName;
}





}
