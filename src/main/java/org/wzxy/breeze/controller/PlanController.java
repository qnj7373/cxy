package org.wzxy.breeze.controller;


import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.mapper.laboratoryMapper;
import org.wzxy.breeze.mapper.planMapper;
import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.Plan;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.*;
import org.wzxy.breeze.service.serviceImpl.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/laboratory")
public class PlanController  {
	private String SourceFlag;
	@Resource
	private planMapper planDao;
	@Resource
	private laboratoryMapper laboratoryDao;
	@Autowired
	private Page<PlanDto> Planpage ;
	private int uNum;
	@Autowired
	private Plan plan ;
	@Autowired
	private List<PlanDto> PlanDtos ;
	@Autowired
	private List<Plan> Planlist ;
	@Autowired
	private DepartmentDto depmentDto ;
	@Autowired
	private TechnicianDto technicianDto ;
	@Autowired
	private List<LaboratoryDto> labDtos ;
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
	@Autowired
	private  ResponseResult Result ;
	@Autowired
	private HandleResult handle;
	@Autowired
	private Logger logger;
	@Autowired
	private getStatusService Status;

	@GetMapping("/plan/toadd")
	@RequiresRoles("technician")
	public ResponseResult toadd() {
		 try{
			 labDtos.clear();
			 technicianDto=TechSer.queryTechById(Status.getNum());
			 depmentDto=DepSer.queryDepartmentById(technicianDto.getDepId());
			 labDtos=LabSer.queryLaboratorysBydepId(depmentDto.getDepId());
			 depmentDto.setLabList(labDtos);
			 depmentDto.setTechDto(technicianDto);
			 Result.setData(depmentDto);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("获取新增计划所需信息成功！");
			 return Result;
				}catch(Exception e) {
					logger.error(e.getMessage());
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员处理~");
			 return Result;
				}
	}

	@PostMapping("/plan")
	@RequiresRoles("technician")
	public ResponseResult addPlan(PlanDto pDto) {
		 try{
			 handle= planservice.addPlan(pDto);
			 Result.setData(null);
			 Result.setStatus(handle.getStatus());
			 Result.setMessage(handle.getMessage());
			 return Result;
				}catch(Exception e) {
					logger.error(e.getMessage());
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员处理~");
			 return Result;
				}
	}


@DeleteMapping("/plan")
@RequiresRoles("technician")
	public ResponseResult deletePlanById(PlanDto pDto) {

		 try{
			 handle=planservice.deletePlanById(pDto.getPlanId());
			 Result.setData(null);
			 Result.setStatus(handle.getStatus());
			 Result.setMessage(handle.getMessage());
			 return Result;
				}catch(Exception e) {
					logger.error(e.getMessage());
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员处理~");
			 return Result;
				}
	}

@GetMapping("/plan")
@RequiresRoles(value={"technician","institute","assistant"},logical = Logical.OR)
	public ResponseResult queryPlanById(PlanDto pDto) {
		try{
			planDto=planservice.queryPlanById(pDto.getPlanId());
			Result.setData(planDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取计划成功！");
			return Result;
		}catch(Exception e) {
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
		}
	}

	@GetMapping("/plan/technician")
	@RequiresRoles("technician")
	public ResponseResult queryPlanPageByTechId(PlanDto pDto) {
		try{
			 PlanDtos=planservice.queryPlanByTechId(Status.getNum());
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
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}

	}

	@GetMapping("/plan/page")
	@RequiresRoles(value={"technician","institute","assistant"},logical = Logical.OR)
	public ResponseResult queryPlanByPage(PlanDto pDto) {
		try {
			PlanDtos = planservice.queryPlanBydepId(TechSer.queryTechById(Status.getNum()).getDepId());
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
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
		}

	}

	@GetMapping("/plan/all/page")
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
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}
	}

	@GetMapping("/plan/adopt/page")
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
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}
	}

@PutMapping("/plan")
@RequiresRoles(value={"technician","institute"},logical = Logical.OR)
	public ResponseResult updatePlan(PlanDto pDto) {

		try{
			handle= planservice.updatePlan(pDto);
			Result.setData(null);
			Result.setStatus(handle.getStatus());
			Result.setMessage(handle.getMessage());
			return Result;
				}catch(Exception e) {
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}
	}

@PutMapping("/plan/examine")
@RequiresRoles("institute")
	public ResponseResult examinePlan(PlanDto pDto) {

		try{
			 PlanDto pd=planservice.queryPlanById(pDto.getPlanId());
			 pd.setPlanSta(pDto.getPlanSta());
			handle= planservice.updatePlan(pd);
			Result.setData(null);
			Result.setStatus(handle.getStatus());
			Result.setMessage(handle.getMessage());
			return Result;
				}catch(Exception e) {
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}

	}


@GetMapping("/plan/details")
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
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
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
