package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.po.Department;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

public interface IDepartmentService {

	 ///////////����ѧԺ
	   public HandleResult addDepartment(DepartmentDto Depdto) ;

	   public  HandleResult deleteDepartmentById(int depId);

	   public DepartmentDto queryDepartmentById(int depId) ;

	   public HandleResult updateDepartment(DepartmentDto Depdto) ;

	   public Page<Department> DepPaging(int nowPage, int pageSize) ;

	   public List<Department> getAllDep();

}
