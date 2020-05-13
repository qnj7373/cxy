package org.wzxy.breeze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.ClassBase;
import org.wzxy.breeze.model.dto.ClassDto;
import org.wzxy.breeze.service.Iservice.IClassService;
import org.wzxy.breeze.service.Iservice.IDepartmentService;

@RestController
public class ClassController extends ClassBase {

	@Autowired
	private IClassService ClaSer;
	@Autowired
	private IDepartmentService DepSer;
	private ClassDto ClaDto;
	private String SourceFlag;


//////////����Class��action����
public String addClass() {
	 try{
		 ClaSer.addClass(ClaDto);
		 classDtos=ClaSer.queryClassBydepId(ClaDto.getDepId());
		 /////��ʼֵ����++++++++++++++++++++
		 Deppage.setCommonObject(DepSer.queryDepartmentById(ClaDto.getDepId()));
		 DepList = DepSer.getAllDep();
		 if(classDtos!=null) {
				Cpage=ClaSer.ClaPaging(classDtos,ClaDto.getNowPage(),ClaDto.getPageSize());
			}else {
				Cpage=null;
			}
			return "success";
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
}

public String deleteClassById() {

	 try{
			ClaSer.deleteClassById(ClaDto.getClassId());
			classDtos=ClaSer.queryClassBydepId(ClaDto.getDepId());
			DepList = DepSer.getAllDep();
			 if(classDtos!=null) {
					Cpage=ClaSer.ClaPaging(classDtos,ClaDto.getNowPage(),ClaDto.getPageSize());
				}else {
					Cpage=null;
				}
			return "success";
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
}


public String queryClassBydepId() {         //���Ӧ����ѧԺ�İ༶
	try{
		classDtos=ClaSer.queryClassBydepId(ClaDto.getDepId());
		Deppage.setCommonObject(DepSer.queryDepartmentById(ClaDto.getDepId()));
		DepList = DepSer.getAllDep();
		 if(classDtos!=null) {
				Cpage=ClaSer.ClaPaging(classDtos,ClaDto.getNowPage(),ClaDto.getPageSize());
			}else {
				Cpage=null;
			}
		return "success";
	}catch(Exception e) {
		e.printStackTrace();
		return "error";
	}
}

public String queryClassById() {
	try{
		ClaDto=ClaSer.queryClassById(ClaDto.getClassId());
	return "success";
	}catch(Exception e) {
		e.printStackTrace();
		return "error";
	}
}

public String queryAllClass() {
	try{
		classDtos=ClaSer.queryAllClass();
		   if(SourceFlag.equals("ClassList")) {
			   return "ClassList";
		    }else if(SourceFlag.equals("StudentList")) {
		    	ClaList=createClassList(classDtos);
		    	return "StudentList";
		   }else if(SourceFlag.equals("StudentAdd")) {
			   ClaList=createClassList(classDtos);
			   return "StudentAdd";
	   }
	}catch(Exception e) {
		e.printStackTrace();
		return "error";
	}
	return "error";
}


public String queryClassByPage() { //////��ҳ////////
	try{
		classDtos=ClaSer.queryClassBydepId(ClaDto.getDepId());
		 if(classDtos!=null) {
				Cpage=ClaSer.ClaPaging(classDtos,ClaDto.getNowPage(),ClaDto.getPageSize());
			}else {
				Cpage=null;
			}
		return "success";
	}catch(Exception e) {
		e.printStackTrace();
		return "error";
	}
}


public String updateClass() {

	 try{
		 ClaSer.updateClass(ClaDto);
			classDtos=ClaSer.queryClassBydepId(ClaDto.getDepId());
			Deppage.setCommonObject(DepSer.queryDepartmentById(ClaDto.getDepId()));
			 if(classDtos!=null) {
					Cpage=ClaSer.ClaPaging(classDtos,ClaDto.getNowPage(),ClaDto.getPageSize());
				}else {
					Cpage=null;
				}
			return "success";
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
}
//////////����Class��action����


}
