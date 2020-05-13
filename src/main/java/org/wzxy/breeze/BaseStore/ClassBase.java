package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.ClassDto;
import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.po.Class;
import org.wzxy.breeze.model.po.Department;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

public class ClassBase {
	protected List<Department> DepList =new ArrayList<>();
	protected Page<DepartmentDto> Deppage=new Page<DepartmentDto>();
	protected List<ClassDto> classDtos=new ArrayList<ClassDto>();
	protected List<Class> ClaList =new ArrayList<>();
	protected Page<Class> Classpage=new Page<Class>();
	protected Page<ClassDto> Cpage=new Page<ClassDto>();
	protected Class cla=new Class();

/////////////////////////////////

	public Class createClass(ClassDto  classdto) {
		if(classdto!=null) {
			cla.setClassId(classdto.getClassId());
			cla.setClassName(classdto.getClassName());
			cla.setDepId(classdto.getDepId());
		}

		return cla;
	}

	public ClassDto  createClassDto(Class cla) {
		ClassDto  cladto=new ClassDto ();
		if(cla!=null) {
			cladto.setClassId(cla.getClassId());
			cladto.setClassName(cla.getClassName());
			cladto.setDepId(cla.getDepId());
				}
		return cladto;
	}


public List<Class> createClassList(List<ClassDto > AdminDtos) {
	for(ClassDto  ad:AdminDtos) {
		Class c=new Class();
		c.setClassId(ad.getClassId());
		c.setClassName(ad.getClassName());
		c.setDepId(ad.getDepId());
		ClaList.add(c);
	}
	return ClaList;
}
	//////////////
	public List<Class> getClaList() {
		return ClaList;
	}
	public void setClaList(List<Class> claList) {
		ClaList = claList;
	}
	public Page<Class> getClasspage() {
		return Classpage;
	}
	public void setClasspage(Page<Class> classpage) {
		Classpage = classpage;
	}
	public Class getCla() {
		return cla;
	}
	public void setCla(Class cla) {
		this.cla = cla;
	}

	public List<ClassDto> getClassDtos() {
		return classDtos;
	}

	public void setClassDtos(List<ClassDto> classDtos) {
		this.classDtos = classDtos;
	}

	public Page<ClassDto> getCpage() {
		return Cpage;
	}

	public void setCpage(Page<ClassDto> cpage) {
		Cpage = cpage;
	}

	public Page<DepartmentDto> getDeppage() {
		return Deppage;
	}

	public void setDeppage(Page<DepartmentDto> deppage) {
		Deppage = deppage;
	}

	public List<Department> getDepList() {
		return DepList;
	}

	public void setDepList(List<Department> depList) {
		DepList = depList;
	}

}
