package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.po.Enlist;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IEnlistService;
import org.wzxy.breeze.service.Iservice.IPersonInfoService;
import org.wzxy.breeze.service.Iservice.IPlanService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.serviceImpl.EnlistServiceImpl;
import org.wzxy.breeze.service.serviceImpl.PersonInfoServiceImpl;
import org.wzxy.breeze.service.serviceImpl.PlanServiceImpl;
import org.wzxy.breeze.service.serviceImpl.getStatusService;

import java.util.List;

@RestController
@RequestMapping("/laboratory")
public class EnlistController {

	@Autowired
	private Page<EnlistDto> Enlistpage ;
	@Autowired
	private Enlist enlist ;
	@Autowired
	private List<EnlistDto> EnlistDtos ;
	@Autowired
	private List<Enlist> Enlists ;
	@Autowired
	private IPersonInfoService PersonSer;
	@Autowired
	private IEnlistService enlistService;
	@Autowired
	private IPlanService planservice;
	@Autowired
	private Page<PlanDto> Planpage ;
	@Autowired
	private List<PlanDto> PlanDtos ;
	@Autowired
	private PersonInfoDto PersonDto ;
	@Autowired
	private PlanDto planDto;
	@Autowired
	private IUserService UserService;
	private EnlistDto enlistDto = new EnlistDto();
	private String SourceFlag;
	private int studentId;
	@Autowired
	private Logger logger;
	@Autowired
	private getStatusService Status;
	@Autowired
	private  ResponseResult Result ;
	@Autowired
	private HandleResult handle;

	@PostMapping("/enlist")
	@RequiresRoles("assistant")
	public ResponseResult addEnlist(EnlistDto eDto) {
		 try{

			    handle=enlistService.addEnlist(eDto);
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


	@RequiresRoles("assistant")
	public String deleteEnlistById() {

		 try{
			 enlistService.deleteEnlistById(enlistDto.getEnlistId());
				return "success";
				}catch(Exception e) {
					logger.error(e.getMessage());
					return "error";
				}
	}

	@RequiresRoles("assistant")
	public String queryEnlistById() {
		try{
			enlistDto=enlistService.queryEnlistById(enlistDto.getEnlistId());
			return "success";
		}catch(Exception e) {
			logger.error(e.getMessage());
			return "error";
		}
	}



	@GetMapping("/enlist/person")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryEnlistBypersonId() {
		try{
			 int pid= PersonSer.queryPersonInfoByStudentId(Status.getNum()).getPersonId();
			enlistDto=enlistService.queryEnlistBypersonId(pid);
			EnlistDtos.clear();
			if(enlistDto!=null) {
				planDto=planservice.queryPlanById(enlistDto.getPlanId());
				enlistDto.setPlanName(planDto.getPlanName());
				enlistDto.setLabName(planDto.getLabName());

				EnlistDtos.add(enlistDto);
			}
			Enlistpage.setDatas(EnlistDtos);
			Result.setData(Enlistpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取报名单成功！");
			return Result;
		}catch(Exception e) {
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
		}
	}

	@PutMapping("/enlist")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult examineEnlist(EnlistDto eDto) {

		try{
			EnlistDto el=enlistService.queryEnlistById(eDto.getEnlistId());
			 el.setSignSta(eDto.getSignSta());
			handle=enlistService.updatePlan(el);
			 enlistDto.setPlanId(el.getPlanId());
			handle=enlistService.addEnlist(eDto);
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

	@RequiresRoles("assistant")
	public String updateEnlist() {

		try{
			enlistService.updatePlan(enlistDto);
				return "success";
				}catch(Exception e) {
					logger.error(e.getMessage());
					return "error";
				}
	}

	@GetMapping("/enlist/plan/page")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryEnlistforplanIdByPage(EnlistDto eDto) { //////��ҳ////////
		try{
			EnlistDtos=enlistService.queryEnlistByplanId(eDto.getPlanId());
			 if(EnlistDtos!=null) {
				 Enlistpage=enlistService.EnlistPaging(EnlistDtos, eDto.getNowPage(),eDto.getPageSize());
				}else {
					Enlistpage=null;
				}
			Result.setData(Enlistpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取报名信息成功！");
			return Result;

				}catch(Exception e) {
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}
	}

	@GetMapping("/enlist/details")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult EnlistDetails(EnlistDto eDto) {
		try{
			enlistDto=enlistService.queryEnlistById(eDto.getEnlistId());
			if(enlistDto!=null) {
				planDto=planservice.queryPlanById(enlistDto.getPlanId());
			}
			enlistDto.setPlanName(planDto.getPlanName());
			enlistDto.setLabName(planDto.getLabName());
			Result.setData(enlistDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取报名信息成功！");
			return Result;
		}catch(Exception e) {
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
		}
	}


	public void setEnlistService(EnlistServiceImpl enlistService) {
		this.enlistService = enlistService;
	}

	public EnlistDto getEnlistDto() {
		return enlistDto;
	}

	public void setEnlistDto(EnlistDto enlistDto) {
		this.enlistDto = enlistDto;
	}

	public String getSourceFlag() {
		return SourceFlag;
	}


	public void setSourceFlag(String sourceFlag) {
		SourceFlag = sourceFlag;
	}

	public void setPersonSer(PersonInfoServiceImpl personSer) {
		PersonSer = personSer;
	}

	public void setPlanservice(PlanServiceImpl planservice) {
		this.planservice = planservice;
	}

	public int getStudentId() {
		return studentId;
	}



	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

}
