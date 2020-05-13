package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.po.PersonInfo;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.loginUser;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonInfoBase {
	protected loginUser luser=new loginUser();
	protected List<PersonInfo> PersonList =new ArrayList<>();
	protected Page<PersonInfo> Personpage=new Page<PersonInfo>();
	protected int planId;
	protected int techId;
	protected int tempLID;
	protected PlanDto planDto;
	protected PersonInfo Person=new PersonInfo();
	protected PersonInfoDto PersonDto=new PersonInfoDto();
	protected List<PersonInfoDto> PerDtos =new ArrayList<>();
	protected Page<PersonInfoDto> Perpage=new Page<PersonInfoDto>();
	protected List<EnlistDto> EnlistDtos=new ArrayList<EnlistDto>();
	protected List<PlanDto> PlanDtos=new ArrayList<PlanDto>();
	protected LaboratoryDto LabDto=new LaboratoryDto();

	//�����������������ԵĿ�ݺ���

			@SuppressWarnings("resource")
			public PersonInfo createPersonInfo(PersonInfoDto personDto,File Pfile) throws IOException {

				if(personDto!=null) {
					Person.setClassId(personDto.getClassId());
					Person.setDepId(personDto.getDepId());
					Person.setEmail(personDto.getEmail());
					Person.setGrade(personDto.getGrade());
					Person.setHirSta(personDto.getHirSta());
					Person.setLabId(personDto.getLabId());
					Person.setMajor(personDto.getMajor());
					Person.setPassword(personDto.getPassword());
					Person.setPersonId(personDto.getPersonId());
					Person.setPersonName(personDto.getPersonName());
					Person.setStudentId(personDto.getStudentId());
					Person.setTel(personDto.getTel());
					if(Pfile!=null){
						FileInputStream file=new FileInputStream(Pfile);
						byte[] buffer=new byte[file.available()];
						file.read(buffer);
						Person.setPhoto(buffer);
					}
				}

				return Person;
			}

			////////////�������ݴ������

			public PersonInfoDto createPersonInfoDto(PersonInfo person) {

				PersonInfoDto personDto=new PersonInfoDto();
				if(person!=null) {
					personDto.setClassId(person.getClassId());
					personDto.setDepId(person.getDepId());
					personDto.setEmail(person.getEmail());
					personDto.setGrade(person.getGrade());
					personDto.setHirSta(person.getHirSta());
					personDto.setLabId(person.getLabId());
					personDto.setMajor(person.getMajor());
					personDto.setPassword(person.getPassword());
					personDto.setPersonId(person.getPersonId());
					personDto.setPersonName(person.getPersonName());
					personDto.setPhoto(person.getPhoto());
					personDto.setStudentId(person.getStudentId());
					personDto.setTel(person.getTel());
				}

				return personDto;
			}




	public List<PersonInfo> getPersonList() {
		return PersonList;
	}
	public void setPersonList(List<PersonInfo> personList) {
		PersonList = personList;
	}
	public Page<PersonInfo> getPersonpage() {
		return Personpage;
	}
	public void setPersonpage(Page<PersonInfo> personpage) {
		Personpage = personpage;
	}

	public PersonInfoDto getPersonDto() {
		return PersonDto;
	}

	public void setPersonDto(PersonInfoDto personDto) {
		PersonDto = personDto;
	}

	public PersonInfo getPerson() {
		return Person;
	}
	public void setPerson(PersonInfo person) {
		Person = person;
	}
	public Page<PersonInfoDto> getPerpage() {
		return Perpage;
	}
	public void setPerpage(Page<PersonInfoDto> perpage) {
		Perpage = perpage;
	}

	public List<PersonInfoDto> getPerDtos() {
		return PerDtos;
	}

	public void setPerDtos(List<PersonInfoDto> perDtos) {
		PerDtos = perDtos;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public PlanDto getPlanDto() {
		return planDto;
	}

	public void setPlanDto(PlanDto planDto) {
		this.planDto = planDto;
	}

	public loginUser getLuser() {
		return luser;
	}

	public void setLuser(loginUser luser) {
		this.luser = luser;
	}

	public List<EnlistDto> getEnlistDtos() {
		return EnlistDtos;
	}

	public void setEnlistDtos(List<EnlistDto> enlistDtos) {
		EnlistDtos = enlistDtos;
	}

	public List<PlanDto> getPlanDtos() {
		return PlanDtos;
	}

	public void setPlanDtos(List<PlanDto> planDtos) {
		PlanDtos = planDtos;
	}

	public LaboratoryDto getLabDto() {
		return LabDto;
	}

	public void setLabDto(LaboratoryDto labDto) {
		LabDto = labDto;
	}

	public int getTechId() {
		return techId;
	}

	public void setTechId(int techId) {
		this.techId = techId;
	}

	public int getTempLID() {
		return tempLID;
	}

	public void setTempLID(int tempLID) {
		this.tempLID = tempLID;
	}

}
