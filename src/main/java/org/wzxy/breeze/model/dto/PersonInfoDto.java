package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.wzxy.breeze.model.po.PersonInfo;

import java.io.Serializable;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class PersonInfoDto implements Serializable {

	  private int personId;
	  private int planId;
	  private int studentId;
	  private String password;
	  private String personName;
	  private String major;
	  private int depId;
	  private int classId;
	  private String grade;
	  private String tel;
	  private String email;
	  private byte[] photo;
	  private int  labId;
	  private String hirSta;
	  private String utype;



	 private CommonsMultipartFile pfile;
	  private int nowPage;
	  private int pageSize;


	public PersonInfoDto() {
		super();
	}


	public PersonInfoDto(PersonInfo personDto) {

        if (personDto != null) {
            this.personId = personDto.getPersonId();
            this.studentId = personDto.getStudentId();
            this.password = personDto.getPassword();
            this.personName = personDto.getPersonName();
            this.major = personDto.getMajor();
            this.depId = personDto.getDepId();
            this.classId = personDto.getClassId();
            this.grade = personDto.getGrade();
            this.tel = personDto.getTel();
            this.email = personDto.getEmail();
            this.photo = personDto.getPhoto();
            this.labId = personDto.getLabId();
            this.hirSta = personDto.getHirSta();

        }
    }

	public PersonInfoDto(int personId, int planId, int studentId, String password, String personName, String major, int depId, int classId, String grade, String tel, String email, byte[] photo, int labId, String hirSta, CommonsMultipartFile pfile, int nowPage, int pageSize) {
		this.personId = personId;
		this.planId = planId;
		this.studentId = studentId;
		this.password = password;
		this.personName = personName;
		this.major = major;
		this.depId = depId;
		this.classId = classId;
		this.grade = grade;
		this.tel = tel;
		this.email = email;
		this.photo = photo;
		this.labId = labId;
		this.hirSta = hirSta;
		this.pfile = pfile;
		this.nowPage = nowPage;
		this.pageSize = pageSize;
	}

	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public int getLabId() {
		return labId;
	}
	public void setLabId(int labId) {
		this.labId = labId;
	}
	public String getHirSta() {
		return hirSta;
	}
	public void setHirSta(String hirSta) {
		this.hirSta = hirSta;
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

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public CommonsMultipartFile getPfile() {
		return pfile;
	}

	public void setPfile(CommonsMultipartFile pfile) {
		this.pfile = pfile;
	}

	public String getUtype() {
		return utype;
	}

	public void setUtype(String utype) {
		this.utype = utype;
	}
}
