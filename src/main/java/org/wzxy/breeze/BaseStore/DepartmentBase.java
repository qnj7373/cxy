package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.Department;
import org.wzxy.breeze.model.po.Technician;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

public class DepartmentBase {
	protected List<Department> DepList =new ArrayList<>();
	protected Page<Department> Deppage=new Page<Department>();
	protected Department depment=new Department();
	protected TechnicianDto Techdto=new TechnicianDto();
	protected List<Technician> Techlist=new ArrayList<Technician>();


	public List<Department> getDepList() {
		return DepList;
	}
	public void setDepList(List<Department> depList) {
		DepList = depList;
	}
	public Page<Department> getDeppage() {
		return Deppage;
	}
	public void setDeppage(Page<Department> deppage) {
		Deppage = deppage;
	}
	public Department getDepment() {
		return depment;
	}
	public void setDepment(Department depment) {
		this.depment = depment;
	}

	public TechnicianDto getTechdto() {
		return Techdto;
	}
	public void setTechdto(TechnicianDto techdto) {
		Techdto = techdto;
	}




	public List<Technician> getTechlist() {
		return Techlist;
	}
	public void setTechlist(List<Technician> techlist) {
		Techlist = techlist;
	}
	////////////////
	public Department createDepartment(DepartmentDto Depdto) {
		depment.setDepId(Depdto.getDepId());
		depment.setDepName(Depdto.getDepName());
		return depment;
		}

	public DepartmentDto createDepartmentDto(Department DM) {
		DepartmentDto Deptdto=new DepartmentDto();
		Deptdto.setDepId(DM.getDepId());
		Deptdto.setDepName(DM.getDepName());
		return Deptdto;
		}






}
