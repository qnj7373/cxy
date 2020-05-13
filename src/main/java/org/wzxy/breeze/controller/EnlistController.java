package org.wzxy.breeze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.EnlistBase;
import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IEnlistService;
import org.wzxy.breeze.service.Iservice.IPersonInfoService;
import org.wzxy.breeze.service.Iservice.IPlanService;
import org.wzxy.breeze.service.serviceImpl.EnlistServiceImpl;
import org.wzxy.breeze.service.serviceImpl.PersonInfoServiceImpl;
import org.wzxy.breeze.service.serviceImpl.PlanServiceImpl;

@RestController
@RequestMapping("/enlist")
public class EnlistController extends EnlistBase {
	@Autowired
	private IPersonInfoService PersonSer;
	@Autowired
	private IEnlistService enlistService;
	@Autowired
	private IPlanService planservice;
	private EnlistDto enlistDto = new EnlistDto();
	private String SourceFlag;
	private int studentId;
	private  ResponseResult Result = new ResponseResult();


	public String addEnlist() {
		 try{

			 PlanDtos=planservice.queryPlanByPlanSta("ͨ��");
			 /////��ʼֵ����++++++++++++++++++++
			 if(PlanDtos!=null) {
				 Planpage=planservice.PlanPaging(PlanDtos, enlistDto.getNowPage(),enlistDto.getPageSize());
				}else {
					Planpage=null;
				}
				signResult=enlistService.addEnlist(enlistDto);
				PersonDto=PersonSer.queryPersonInfoById(enlistDto.getPersonId());
				planDto=planservice.queryPlanById(enlistDto.getPlanId());
				return signResult;
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}



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

	public String queryEnlistById() {
		try{
			enlistDto=enlistService.queryEnlistById(enlistDto.getEnlistId());
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}



	public String queryEnlistBypersonId() {
		try{
			 int pid= PersonSer.queryPersonInfoByStudentId(studentId).getPersonId();
			enlistDto=enlistService.queryEnlistBypersonId(pid);
			if(enlistDto!=null) {
				planDto=planservice.queryPlanById(enlistDto.getPlanId());
			}
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@PostMapping("/examineEnlist")
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

	public String updateEnlist() {

		try{
			enlistService.updatePlan(enlistDto);
			/// queryPlanByPage();
			 /////��ʼֵ����++++++++++++++++++++
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

	@GetMapping("/queryEnlistforplanIdByPage")
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
