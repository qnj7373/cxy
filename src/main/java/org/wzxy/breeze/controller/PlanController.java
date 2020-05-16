package org.wzxy.breeze.controller;


import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.PlanBase;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.*;
import org.wzxy.breeze.service.serviceImpl.DepartmentServiceImpl;
import org.wzxy.breeze.service.serviceImpl.LaboratoryServiceImpl;
import org.wzxy.breeze.service.serviceImpl.PlanServiceImpl;
import org.wzxy.breeze.service.serviceImpl.TechnicianServiceImpl;
import org.wzxy.breeze.utils.getUId;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController extends PlanBase {
	private String SourceFlag;
	@Autowired
	private IDepartmentService DepSer;
	@Autowired
	private IPlanService planservice;
	@Autowired
	private ITechnicianService TechSer;
	@Autowired
	private ILaboratoryService LabSer;
	@Autowired
	private IUserService UserService;
	private  PlanDto planDto;
	private  ResponseResult Result = new ResponseResult();


	@GetMapping("/toadd")
	@RequiresRoles("technician")
	public ResponseResult toadd() {
		 try{
			 labDtos.clear();
			 technicianDto=TechSer.queryTechById(getNum());
			 depmentDto=DepSer.queryDepartmentById(technicianDto.getDepId());
			 labDtos=LabSer.queryLaboratorysBydepId(depmentDto.getDepId());
			 depmentDto.setLabList(labDtos);
			 depmentDto.setTechDto(technicianDto);
			 Result.setData(depmentDto);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("获取新增计划所需信息成功！");
			 return Result;
				}catch(Exception e) {
					e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}

	@PostMapping("addPlan")
	@RequiresRoles("technician")
	public ResponseResult addPlan(PlanDto pDto) {
		 try{
			 System.out.println(pDto.getTechId()+" hhh "+pDto.getTechName());
			 planservice.addPlan(pDto);
			 Result.setData(null);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("新增计划成功！");
			 return Result;
				}catch(Exception e) {
					e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}


@GetMapping("deletePlanById")
@RequiresRoles("technician")
	public ResponseResult deletePlanById(PlanDto pDto) {

		 try{
			 planservice.deletePlanById(pDto.getPlanId());
			 Result.setData(null);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("删除计划成功！");
			 return Result;
				}catch(Exception e) {
					e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}

@GetMapping("/queryPlanById")
@RequiresRoles(value={"technician","institute","assistant"},logical = Logical.OR)
	public ResponseResult queryPlanById(PlanDto pDto) {
		try{
			planDto=planservice.queryPlanById(pDto.getPlanId());
			Result.setData(planDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取计划成功！");
			return Result;
		}catch(Exception e) {
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}

	@GetMapping("/queryPlanPageByTechId")
	@RequiresRoles("technician")
	public ResponseResult queryPlanPageByTechId(PlanDto pDto) {
		try{
			 PlanDtos=planservice.queryPlanByTechId(getNum());
			 if(PlanDtos!=null) {
				 Planpage=planservice.PlanPaging(PlanDtos, pDto.getNowPage(),pDto.getPageSize());
				}else {
					Planpage=null;
				}
			Result.setData(Planpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取计划成功！");
			return Result;
				}catch(Exception e) {
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}

	}

	@GetMapping("/queryPlanByPage")
	@RequiresRoles(value={"technician","institute","assistant"},logical = Logical.OR)
	public ResponseResult queryPlanByPage(PlanDto pDto) {
		try {
			PlanDtos = planservice.queryPlanBydepId(TechSer.queryTechById(getNum()).getDepId());
			/////初始值设置++++++++++++++++++++
			if (PlanDtos != null) {
				Planpage = planservice.PlanPaging(PlanDtos, pDto.getNowPage(), pDto.getPageSize());
			} else {
				Planpage = null;
			}
			Result.setData(Planpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("分页获取计划列表成功！");
			return Result;
		} catch (Exception e) {
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}

	}

	@GetMapping("/queryAllPlanByPage")
	@RequiresRoles("institute")
	public ResponseResult queryAllPlanByPage(PlanDto pDto) {
		try{
			PlanDtos.clear();
             PlanDtos=planservice.queryAllPlan();
			 if(PlanDtos!=null) {
				 Planpage=planservice.PlanPaging(PlanDtos, pDto.getNowPage(),pDto.getPageSize());
				}else {
					Planpage=null;
				}
			Result.setData(Planpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("分页获取计划列表成功！");
			return Result;

				}catch(Exception e) {
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}
	}

	@GetMapping("/queryAdoptPlanByPage")
	@RequiresRoles(value={"technician","institute","assistant"},logical = Logical.OR)
	public ResponseResult queryAdoptPlanByPage(PlanDto pDto) {
		try{
             PlanDtos=planservice.queryPlanByPlanSta("通过");
			 if(PlanDtos!=null) {
				 Planpage=planservice.PlanPaging(PlanDtos, pDto.getNowPage(),pDto.getPageSize());
				}else {
					Planpage=null;
				}
			Result.setData(Planpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取计划列表成功！");
			return Result;

				}catch(Exception e) {
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}
	}

@PostMapping("/updatePlan")
@RequiresRoles(value={"technician","institute"},logical = Logical.OR)
	public ResponseResult updatePlan(PlanDto pDto) {

		try{
			 planservice.updatePlan(pDto);
			Result.setData(null);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("修改计划成功！");
			return Result;
				}catch(Exception e) {
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}
	}

@PostMapping("/examinePlan")
@RequiresRoles("institute")
	public ResponseResult examinePlan(PlanDto pDto) {

		try{
			 PlanDto pd=planservice.queryPlanById(pDto.getPlanId());
			 pd.setPlanSta(pDto.getPlanSta());
			 planservice.updatePlan(pd);
			Result.setData(null);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("更新审核状态成功！");
			return Result;
				}catch(Exception e) {
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}

	}


@GetMapping("/planDetails")
@RequiresRoles(value={"technician","institute","assistant"},logical = Logical.OR)
	public ResponseResult PlanDetails(PlanDto pDto) {
		try{
			planDto=planservice.queryPlanById(pDto.getPlanId());
			labDtos=LabSer.queryLaboratorysBydepId(planDto.getDepId());
			Result.setData(planDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取计划详细信息成功！");
			return Result;
		}catch(Exception e) {
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}


	@RequiresRoles(value={"technician","institute","assistant"},logical = Logical.OR)
	private int getNum(){
		User u = new User();
		u.setUid(getUId.getid());
		List<User> users = new ArrayList<>();
		users= UserService.findUserByFactor(u);
		if (users!=null){
			return  users.get(0).getUnum();
		}else{
			return 0;
		}
	}

	public PlanDto getPlanDto() {
		return planDto;
	}

	public void setPlanDto(PlanDto planDto) {
		this.planDto = planDto;
	}

	public void setPlanservice(PlanServiceImpl planservice) {
		this.planservice = planservice;
	}


	public void setDepSer(DepartmentServiceImpl depSer) {
		DepSer = depSer;
	}

	public void setTechSer(TechnicianServiceImpl techSer) {
		TechSer = techSer;
	}

	public String getSourceFlag() {
		return SourceFlag;
	}

	public void setSourceFlag(String sourceFlag) {
		SourceFlag = sourceFlag;
	}
	public void setLabSer(LaboratoryServiceImpl labSer) {
		LabSer = labSer;
	}
}
