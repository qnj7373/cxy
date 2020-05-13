package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.dto.WorkRecordDto;
import org.wzxy.breeze.model.po.WorkRecord;
import org.wzxy.breeze.model.vo.Page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class WorkRecordBase {

	protected List<WorkRecord> workList = new ArrayList<WorkRecord>();

	protected Page<WorkRecordDto> Wrecordpage=new Page<WorkRecordDto>();

	protected WorkRecord workRecord=new WorkRecord();

	protected List<WorkRecordDto> workDtos=new ArrayList<WorkRecordDto>();

	protected PersonInfoDto PersonDto=new PersonInfoDto();

	public WorkRecord createWorkRecord(WorkRecordDto  WDto) {

		if(WDto!=null) {
			workRecord.setDate(WDto.getDate());
			workRecord.setEquipment(WDto.getEquipment());
			workRecord.setHealth(WDto.getHealth());
			workRecord.setLabId(WDto.getLabId());
			workRecord.setPersonId(WDto.getPersonId());
			workRecord.setPersonName(WDto.getPersonName());
			workRecord.setRecordId(WDto.getRecordId());
			workRecord.setSysTime(WDto.getSysTime());
			workRecord.setWeek(WDto.getWeek());
			workRecord.setWeeklyTimes(WDto.getWeeklyTimes());
		}
		return workRecord;
	}

	////////////�������ݴ������

	public WorkRecordDto createWorkRecordDto(WorkRecord workRe) {

		WorkRecordDto WDto=new WorkRecordDto ();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(workRe!=null) {
			WDto.setRecordDate(sdf.format(workRe.getDate()) );
			WDto.setDate(workRe.getDate());
			WDto.setEquipment(workRe.getEquipment());
			WDto.setHealth(workRe.getHealth());
			WDto.setLabId(workRe.getLabId());
			WDto.setPersonId(workRe.getPersonId());
			WDto.setPersonName(workRe.getPersonName());
			WDto.setRecordId(workRe.getRecordId());
			WDto.setSysTime(workRe.getSysTime());
			WDto.setWeek(workRe.getWeek());
			WDto.setWeeklyTimes(workRe.getWeeklyTimes());
		}
		return WDto;
	}




















	public List<WorkRecord> getWorkList() {
		return workList;
	}

	public void setWorkList(List<WorkRecord> workList) {
		this.workList = workList;
	}

	public Page<WorkRecordDto> getWrecordpage() {
		return Wrecordpage;
	}

	public void setWrecordpage(Page<WorkRecordDto> wrecordpage) {
		Wrecordpage = wrecordpage;
	}

	public WorkRecord getWorkRecord() {
		return workRecord;
	}

	public void setWorkRecord(WorkRecord workRecord) {
		this.workRecord = workRecord;
	}


	public List<WorkRecordDto> getWorkDtos() {
		return workDtos;
	}

	public void setWorkDtos(List<WorkRecordDto> workDtos) {
		this.workDtos = workDtos;
	}

	public PersonInfoDto getPersonDto() {
		return PersonDto;
	}

	public void setPersonDto(PersonInfoDto personDto) {
		PersonDto = personDto;
	}












}
