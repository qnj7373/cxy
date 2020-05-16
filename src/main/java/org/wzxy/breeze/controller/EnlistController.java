package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.EnlistBase;
import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IEnlistService;
import org.wzxy.breeze.service.Iservice.IPersonInfoService;
import org.wzxy.breeze.service.Iservice.IPlanService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.serviceImpl.EnlistServiceImpl;
import org.wzxy.breeze.service.serviceImpl.PersonInfoServiceImpl;
import org.wzxy.breeze.service.serviceImpl.PlanServiceImpl;
import org.wzxy.breeze.utils.getUId;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/enlist")
public class EnlistController extends EnlistBase {
	@Autowired
	private IPersonInfoService PersonSer;
	@Autowired
	private IEnlistService enlistService;
	@Autowired
	private IPlanService planservice;
	@Autowired
	private IUserService UserService;
	private EnlistDto enlistDto = new EnlistDto();
	private String SourceFlag;
	private int studentId;
	private  ResponseResult Result = new ResponseResult();


	@PostMapping("/addEnlist")
	@RequiresRoles("assistant")
	public ResponseResult addEnlist(EnlistDto eDto) {
		 try{

				signResult=enlistService.addEnlist(eDto);
				if("exist".equals(signResult)){
					Result.setStatus(ResponseCode.getFailcode());
					Result.setMessage("报名失败，您已经应聘了其它岗位！！！！");
				}else  if ("enough".equals(signResult)){
					Result.setStatus(ResponseCode.getFailcode());
					Result.setMessage("报名失败，您已经应聘了其它岗位！！！！");
				}else  if ("fail".equals(signResult)){
					Result.setStatus(ResponseCode.getFailcode());
					Result.setMessage("未知原因导致了失败，请重试！！！");
				}else {
					Result.setStatus(ResponseCode.getOkcode());
					Result.setMessage("恭喜你，报名成功！");
				}
			 return Result;
				}catch(Exception e) {
					e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}


	@RequiresRoles("assistant")
	public String deleteEnlistById() {

		 try{
			 enlistService.deleteEnlistById(enlistDto.getEnlistId());
			// queryPlanByPage();
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

	@RequiresRoles("assistant")
	public String queryEnlistById() {
		try{
			enlistDto=enlistService.queryEnlistById(enlistDto.getEnlistId());
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}



	@GetMapping("/queryEnlistBypersonId")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryEnlistBypersonId() {
		try{
			 int pid= PersonSer.queryPersonInfoByStudentId(getNum()).getPersonId();
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
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}

	@PostMapping("/examineEnlist")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult examineEnlist(EnlistDto eDto) {

		try{
			EnlistDto el=enlistService.queryEnlistById(eDto.getEnlistId());
			 el.setSignSta(eDto.getSignSta());
			 enlistService.updatePlan(el);
			 enlistDto.setPlanId(el.getPlanId());
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("更新报名状态成功！");
			return Result;
				}catch(Exception e) {
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}

	}

	@RequiresRoles("assistant")
	public String updateEnlist() {

		try{
			enlistService.updatePlan(enlistDto);
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

	@GetMapping("/queryEnlistforplanIdByPage")
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
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}
	}

	@GetMapping("/EnlistDetails")
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
