package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.departmentMapper;
import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.Department;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.Technician;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IDepartmentService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DepartmentServiceImpl   implements IDepartmentService {

	@Autowired
	private List<Department> DepList ;
	@Autowired
	private Page<Department> Deppage ;
	@Autowired
	private Department depment ;
	@Autowired
	private TechnicianDto Techdto ;
	@Autowired
	private List<Technician> Techlist ;
	@Resource
	private departmentMapper Departmentdao;
	@Autowired
	private HandleResult handle ;
	private int exist=-1;
	/////二级学院的业务逻辑
	@Override
	@CacheEvict(cacheNames = "departmentZone",allEntries = true)
	public HandleResult addDepartment(DepartmentDto Depdto) {

		exist=Departmentdao.isExist(Depdto.getDepId());
		if(exist==0){
			if(Departmentdao.addDep(new Department(Depdto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("新增部门成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("新增部门失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("新增失败，部门已存在!");
			return handle;
		}
	}

	@Override
	@CacheEvict(cacheNames = "departmentZone",allEntries = true)
	public HandleResult deleteDepartmentById(int depId) {

		exist=Departmentdao.isExist(depId);
		if(exist==1){
			depment=Departmentdao.queryDepById(depId);

			if(Departmentdao.deleteDepById(depment)){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("删除部门成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("删除部门失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("删除失败，部门不存在!");
			return handle;
		}
	}

	@Override
	@Cacheable(value = "departmentZone" , key = "'queryDepartmentById'+#depId")
	public DepartmentDto queryDepartmentById(int depId) {
		depment=Departmentdao.queryDepById(depId);
		if(depment!=null) {
			return new DepartmentDto(depment);
		}else {
			return null;
		}
	}

	@Override
	@CacheEvict(cacheNames = "departmentZone",allEntries = true)
	public HandleResult updateDepartment(DepartmentDto Depdto) {

		exist=Departmentdao.isExist(Depdto.getDepId());
		if(exist==1){
			if(Departmentdao.updateDep(new Department(Depdto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("更新部门成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("更新部门失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("更新失败，部门不存在!");
			return handle;
		}

	}

	@Override
	@Cacheable(value = "departmentZone" , key = "'getAllDep'")
	public List<Department> getAllDep() {

		return DepList=Departmentdao.getAllDeps();

	}

	@Override
	@Cacheable(value = "departmentZone" , key = "'DepPaging'+#nowPage+','+#pageSize")
	public Page<Department> DepPaging(int nowPage, int pageSize) {

		Deppage.setNowPage(nowPage+1);
		Deppage.setDataTotalCount(Departmentdao.getTotalCount());
		Deppage.setPageSize(pageSize);
		Deppage.setPageTotalCount(Deppage.getPageTotalCount());
		Deppage.setDatas((Departmentdao.getDepsByPage(nowPage, pageSize)));
		return Deppage;

	}
	/////二级学院的业务逻辑



}
