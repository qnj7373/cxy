package org.wzxy.breeze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.DepartmentBase;
import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.service.Iservice.IDepartmentService;
import org.wzxy.breeze.service.serviceImpl.DepartmentServiceImpl;

@RestController
public class DepartmentController extends DepartmentBase {
private String SourceFlag;
private DepartmentDto DepDto;
@Autowired
private IDepartmentService DepSer;

//////////����dep��action����


		public String queryAllDeps() {

		 try{
			 DepList = DepSer.getAllDep();
			 if(SourceFlag.equals("InClassList")) {
				 return "ClassList";
			 }else if(SourceFlag.equals("InClaAdd")) {
				 return "ClaAdd";
			 }else if(SourceFlag.equals("InTeaList")) {
				 return "TeaList";
			 }else if(SourceFlag.equals("InTeaAdd")) {
				 return "TeaAdd";
			}
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
		}


public String addDep() {

	 try{
		 DepSer.addDepartment(DepDto);
		 Deppage=DepSer.DepPaging(DepDto.getNowPage(), DepDto.getPageSize());
			return "success";
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
}

public String deleteDepById() {

	 try{
		 DepSer.deleteDepartmentById(DepDto.getDepId());
		 Deppage=DepSer.DepPaging(DepDto.getNowPage(), DepDto.getPageSize());
			return "success";
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
}


public String queryDepById() {
	try{
		DepDto=DepSer.queryDepartmentById(DepDto.getDepId());
			 return "success";

	}catch(Exception e) {
		e.printStackTrace();
		return "error";
	}
}

public String queryDepsByPage() { //////��ҳ////////
	try{
		Deppage=DepSer.DepPaging(DepDto.getNowPage(),DepDto.getPageSize());
		return "success";
	}catch(Exception e) {
		e.printStackTrace();
		return "error";
	}
}


public String updateDep() {

	 try{
		 DepSer.updateDepartment(DepDto);
		 Deppage=DepSer.DepPaging(DepDto.getNowPage(), DepDto.getPageSize());
			return "success";
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
}




//////////����dep��action����

public void setDepSer(DepartmentServiceImpl depSer) {
	DepSer = depSer;
}


public DepartmentDto getDepDto() {
	return DepDto;
}


public void setDepDto(DepartmentDto depDto) {
	DepDto = depDto;
}


public String getSourceFlag() {
	return SourceFlag;
}


public void setSourceFlag(String sourceFlag) {
	SourceFlag = sourceFlag;
}




}
