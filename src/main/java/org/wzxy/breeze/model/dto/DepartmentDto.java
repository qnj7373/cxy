package org.wzxy.breeze.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.wzxy.breeze.model.po.Department;
import org.wzxy.breeze.model.po.Technician;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class DepartmentDto implements Serializable {
	  private int depId;
	  private int techId;
	  private String depName;
	  private String dID;
	  private TechnicianDto techDto;
	protected List<Technician> Techlist=new ArrayList<Technician>();
	protected List<LaboratoryDto> labList=new ArrayList<LaboratoryDto>();
	  private int nowPage;
	  private int pageSize;


	public DepartmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentDto (Department DM) {
		this.depId=DM.getDepId();
		this.depName=DM.getDepName();
	}

	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getdID() {
		return dID;
	}
	public void setdID(String dID) {
		this.dID = dID;
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

	public List<Technician> getTechlist() {
		return Techlist;
	}

	public void setTechlist(List<Technician> techlist) {
		Techlist = techlist;
	}

	public int getTechId() {
		return techId;
	}

	public void setTechId(int techId) {
		this.techId = techId;
	}

	public List<LaboratoryDto> getLabList() {
		return labList;
	}

	public void setLabList(List<LaboratoryDto> labList) {
		this.labList = labList;
	}

	public TechnicianDto getTechDto() {
		return techDto;
	}

	public void setTechDto(TechnicianDto techDto) {
		this.techDto = techDto;
	}
}
