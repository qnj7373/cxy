package org.wzxy.breeze.controller;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.wzxy.breeze.model.dto.*;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.PersonInfo;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.model.vo.loginUser;
import org.wzxy.breeze.service.Iservice.*;
import org.wzxy.breeze.service.serviceImpl.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/laboratory")
public class PersonInfoController   {

	@Autowired
	private ILaboratoryService LabSer;
	@Autowired
	private IPersonInfoService PersonSer;
	@Autowired
	 private IUserService userService;
	@Autowired
	 private IPlanService planservice;
	@Autowired
	private IUserService UserService;
	@Autowired
	private IEnlistService enlistService;
	@Autowired
	private HandleResult handle;
	@Autowired
	private  ResponseResult Result ;
	@Autowired
	private PersonInfoDto pInfodto  ;
	@Autowired
	private PlanDto planDto;
	@Autowired
	private PersonInfo Person ;
	@Autowired
	private PersonInfoDto PersonDto ;
	@Autowired
	private List<PersonInfoDto> PerDtos ;
	@Autowired
	private Page<PersonInfoDto> Perpage ;
	@Autowired
	private List<EnlistDto> EnlistDtos ;
	@Autowired
	private List<PlanDto> PlanDtos ;
	@Autowired
	private loginUser luser ;
	@Autowired
	private List<PersonInfo> PersonList  ;
	private int planId;
	private int techId;
	private int tempLID;
	@Autowired
	private Page<PersonInfo> Personpage ;
	@Autowired
	private LaboratoryDto LabDto ;
	@Autowired
	private Logger logger;
	@Autowired
	private getStatusService Status;
	private File Photofile;
	private String utype;
	private String SourceFlag;


	@GetMapping("/person/toSign")
	@RequiresRoles("assistant")
	public  ResponseResult toSign(PersonInfoDto pdto) {

		 try{
			 planDto=planservice.queryPlanById(pdto.getPlanId());
			 PersonDto=PersonSer.queryPersonInfoByStudentId(Status.getNum());
			 planDto.setPersonId(PersonDto.getPersonId());
			 planDto.setPersonName(PersonDto.getPersonName());
			 Result.setData(planDto);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("获取报名所需信息成功！");
			 return Result;

				}catch(Exception e) {
					logger.error(e.getMessage());
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员处理~");
			 return Result;
				}
	}

	@GetMapping("/person/toAddMain")
	@RequiresRoles("assistant")
	public ResponseResult toAddMain() {
		try{
			int perId=PersonSer.queryPersonInfoByStudentId( Status.getNum()).getPersonId();
			PersonDto=PersonSer.queryPersonInfoById(perId);
			Result.setData(PersonDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取添加维修申请所需信息成功！");
			return Result;
		}catch(Exception e) {
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
		}
	}

	@GetMapping("/person")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryPersonById(PersonInfoDto pdto) {
		try{
				 int pid=enlistService.queryEnlistBypersonId(pdto.getPersonId()).getPlanId();
				 planDto=planservice.queryPlanById(pid);
				 LabDto=LabSer.queryLaboratoryById(planDto.getLabId());
				 PersonDto=PersonSer.queryPersonInfoById(pdto.getPersonId());
				 Result.setData(PersonDto);
				 Result.setDataBackUp(LabDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取助理档案成功！");
			return Result;
		}catch(Exception e) {
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
		}
	}

	@PostMapping("/person/tech/AddPerson")
	@RequiresRoles("technician")
	public ResponseResult TechAddPerson( PersonInfoDto person ) {

		//技术员录入助理
		 try{
			 if(person.getHirSta()==null||("未录入").equals(person.getHirSta())) {
				 person.setHirSta("实验室助理");
			 }
			 if(person.getPfile()!=null){
				 DiskFileItem fi = (DiskFileItem) person.getPfile().getFileItem();
				 File result = fi.getStoreLocation();
				 handle= PersonSer.updatePersonInfo(person, result);
			 }else {
				 handle= PersonSer.updatePersonInfo(person, null);
			 }

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


@PostMapping("/person")
	public ResponseResult addPerson(PersonInfoDto person) {
		 try{
			 UserDto userDto=new UserDto();
			 userDto.setUnum(person.getStudentId());
			 userDto.setUpwd(person.getPassword());
			 userDto.setUtype(person.getUtype());
			 luser=userService.register(userDto);
			 if(("success").equals(luser.getRegisterResult())) {
				 person.setLabId(0);
				 person.setHirSta("未录入");
				 DiskFileItem fi = (DiskFileItem) person.getPfile().getFileItem();
				 File result = fi.getStoreLocation();
				 PersonSer.addPersonInfo(person,result);
				 Result.setData(luser);
				 Result.setStatus(ResponseCode.getOkcode());
				 Result.setMessage("注册成功！");
			 }else if ("noThisOne".equals(luser.getRegisterResult())){
				 Result.setStatus(ResponseCode.getFailcode());
				 Result.setMessage("仅本校学生可注册，请检查学号是否正确！");
			 }else if ("exist".equals(luser.getRegisterResult())){
				 Result.setStatus(ResponseCode.getFailcode());
				 Result.setMessage("该学号已注册！");
			 }
			 return Result;
				}catch(Exception e) {
					logger.error(e.getMessage());
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员处理~");
			 return Result;
				}
	}


	@GetMapping("/person/lab/hirsta/page")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryPerPageBylabIdAndHsta() {
		//////档案管理分页////////
		try{
			PerDtos.clear();
			int lID=LabSer.queryLaboratorysByTechId(Status.getNum()).get(0).getLabId() ;  //�ҳ��뼼��ԱIDƥ���ʵ����ID
			//再根据实验室ID与录入状态找出对应的实验室助理
				PerDtos=PersonSer.queryPersonByplabIdAndSta(lID,"实验室助理");
			/////初始值设置++++++++++++++++++++
			 if(PerDtos!=null) {
				 Perpage=PersonSer.PersonPaging(PerDtos, pInfodto.getNowPage(), pInfodto.getPageSize());
				}else {
					Perpage=null;
				}
			Result.setData(Perpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取助理档案成功！");
			return Result;

				}catch(Exception e) {
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}
	}


@GetMapping("/person/plan/page")
@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryPersonPageByPlanId(PersonInfoDto pdto) {
	//////录入分页////////
		try{
			PerDtos.clear();
			EnlistDtos=enlistService.queryEnlistByplanIdAndSta(pdto.getPlanId(),"已通过面试") ;
			for(EnlistDto edto:EnlistDtos) {
				//遍历取出人员ID
				PersonInfoDto pIDto=new PersonInfoDto();
				pIDto=PersonSer.queryPersonInfoById(edto.getPersonId());
				if(pIDto!=null) {
					String sta=pIDto.getHirSta();
					if(sta==null||sta.equals("未录入")) {
						pIDto.setHirSta("未录入");
						PerDtos.add(pIDto);
					}
				}
			}
			 if(PerDtos!=null) {
				 Perpage=PersonSer.PersonPaging(PerDtos, pdto.getNowPage(), pdto.getPageSize());
				}else {
					Perpage=null;
				}
			Result.setData(Perpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取助理档案成功！");
			return Result;

				}catch(Exception e) {
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}
	}


	@DeleteMapping("/person")
	@RequiresRoles("technician")
	public ResponseResult deletePersonById(PersonInfoDto pDto) {

		 try{
				planId=enlistService.queryEnlistBypersonId(pDto.getPersonId()).getPlanId();
				tempLID = planservice.queryPlanById(planId).getLabId();
			 handle= PersonSer.deletePersonInfoById(pDto.getPersonId());
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


	@GetMapping("/person/getPhotoById")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public String getPhotoById(PersonInfoDto pdto,HttpServletResponse response) throws IOException {
		PersonDto=PersonSer.queryPersonInfoById(pdto.getPersonId());
		byte[] img = PersonDto.getPhoto();
		response.setContentType("image/jpeg");
		ServletOutputStream os = null;
		os = response.getOutputStream();
		if ( img != null && img.length != 0 )
		{
			for (int i = 0; i < img.length; i++)
			{
				os.write(img[i]);
			}
			os.flush();
		}
		 return null;
	}


	@GetMapping("/person/details")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult PersonDetails(PersonInfoDto pDto) {
		try{
			try{
				//查出实验室设为默认值
					 int pid=enlistService.queryEnlistBypersonId((int)pDto.getPersonId()).getPlanId();
					 planDto=planservice.queryPlanById(pid);
					 LabDto=LabSer.queryLaboratoryById(planDto.getLabId());
					 PersonDto=PersonSer.queryPersonInfoById(pDto.getPersonId());
				Result.setData(PersonDto);
				Result.setDataBackUp(LabDto);
				Result.setStatus(ResponseCode.getOkcode());
				Result.setMessage("获取助理档案成功！");
				return Result;
			}catch(Exception e) {
				logger.error(e.getMessage());
				Result.setStatus(ResponseCode.getErrorcode());
				Result.setMessage("服务器出错了！请联系管理员处理~");
				return Result;
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
		}
	}


////////////////////////////////////////////////////////////////////////////////

	public void setPersonSer(PersonInfoServiceImpl personSer) {
		PersonSer = personSer;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public PersonInfoDto getpInfodto() {
		return pInfodto;
	}

	public void setpInfodto(PersonInfoDto pInfodto) {
		this.pInfodto = pInfodto;
	}


	public File getPhotofile() {
		return Photofile;
	}


	public void setPhotofile(File photofile) {
		Photofile = photofile;
	}


	public void setUtype(String utype) {
		this.utype = utype;
	}

	public void setPlanservice(PlanServiceImpl planservice) {
		this.planservice = planservice;
	}

	public void setEnlistService(EnlistServiceImpl enlistService) {
		this.enlistService = enlistService;
	}

	public void setLabSer(LaboratoryServiceImpl labSer) {
		LabSer = labSer;
	}

	public String getSourceFlag() {
		return SourceFlag;
	}

	public void setSourceFlag(String sourceFlag) {
		SourceFlag = sourceFlag;
	}


}
