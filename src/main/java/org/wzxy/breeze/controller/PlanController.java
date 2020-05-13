package org.wzxy.breeze.controller;


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
	public ResponseResult queryPlanById(PlanDto pDto) {
		try{
			planDto=planservice.queryPlanById(pDto.getPlanId());
			//labDtos=LabSer.queryLaboratorysBydepId(planDto.getDepId());
			/* if(SourceFlag.equals("Institute")) {
		    	 return "Institute";
		     }else if(SourceFlag.equals("Technician")) {
		    	 return "Technician";
		     }*/
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
	public ResponseResult queryPlanPageByTechId(PlanDto pDto) {
		try{
			 PlanDtos=planservice.queryPlanByTechId(getNum());
			 if(PlanDtos!=null) {
				 Planpage=planservice.PlanPaging(PlanDtos, pDto.getNowPage(),pDto.getPageSize());
				}else {
					Planpage=null;
				}
			/* if(SourceFlag.equals("profiles")) {
		    	 return "profiles";
		     }else if(SourceFlag.equals("sign")) {
		    	 return "sign";
		     }*/
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

	public String queryAllPlanByPage() { //////ȫ����ҳ////////
		try{
             PlanDtos=planservice.queryAllPlan();
			 if(PlanDtos!=null) {
				 Planpage=planservice.PlanPaging(PlanDtos, planDto.getNowPage(),planDto.getPageSize());
				}else {
					Planpage=null;
				}
			 return "success";

				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

	public String queryAdoptPlanByPage() { //////��ҳ////////
		try{
             PlanDtos=planservice.queryPlanByPlanSta("ͨ��");
			 /////��ʼֵ����++++++++++++++++++++
			 if(PlanDtos!=null) {
				 Planpage=planservice.PlanPaging(PlanDtos, planDto.getNowPage(),planDto.getPageSize());
				}else {
					Planpage=null;
				}
			     return "success";

				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

@PostMapping("/updatePlan")
	public ResponseResult updatePlan(PlanDto pDto) {

		try{
			 planservice.updatePlan(pDto);
			/* if(SourceFlag.equals("Institute")) {
				 queryAllPlanByPage();
		    	 return "Institute";
		     }else if(SourceFlag.equals("Technician")) {
		    	// queryPlanByPage(pDto);
		    	 return "Technician";
		     }*/
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


	public String examinePlan() {

		try{
			 PlanDto pd=planservice.queryPlanById(planDto.getPlanId());
			 pd.setPlanSta(planDto.getPlanSta());
			 planservice.updatePlan(pd);
			 queryAllPlanByPage();
			 /////��ʼֵ����++++++++++++++++++++
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}

	}


@GetMapping("/planDetails")
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
