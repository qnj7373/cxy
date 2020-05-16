package org.wzxy.breeze.controller;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.PersonInfoBase;
import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.*;
import org.wzxy.breeze.service.serviceImpl.*;
import org.wzxy.breeze.utils.getUId;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/personInfo")
public class PersonInfoController extends PersonInfoBase  {

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


	private  ResponseResult Result = new ResponseResult();
	private PersonInfoDto pInfodto =  new PersonInfoDto();
	private File Photofile;
	private String utype;
	private String SourceFlag;


	@GetMapping("/toSign")
	@RequiresRoles("assistant")
	public  ResponseResult toSign(PersonInfoDto pdto) {

		 try{
			 planDto=planservice.queryPlanById(pdto.getPlanId());
			 PersonDto=PersonSer.queryPersonInfoByStudentId(getNum());
			 planDto.setPersonId(PersonDto.getPersonId());
			 planDto.setPersonName(PersonDto.getPersonName());
			 Result.setData(planDto);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("获取报名所需信息成功！");
			 return Result;

				}catch(Exception e) {
					e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}

	@GetMapping("/toAddMain")
	@RequiresRoles("assistant")
	public ResponseResult toAddMain() {
		try{
			int perId=PersonSer.queryPersonInfoByStudentId(getNum()).getPersonId();
			//int pid=enlistService.queryEnlistBypersonId(perId).getPlanId();
			//planDto=planservice.queryPlanById(pid);
			//LabDto=LabSer.queryLaboratoryById(planDto.getLabId());
			PersonDto=PersonSer.queryPersonInfoById(perId);
			Result.setData(PersonDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取添加维修申请所需信息成功！");
			return Result;
		}catch(Exception e) {
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}

	@GetMapping("/queryPersonById")
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
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}

	@PostMapping("/techAddPerson")
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
				 PersonSer.updatePersonInfo(person, result);
			 }else {
				 PersonSer.updatePersonInfo(person, null);
			 }

			 Result.setData(null);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("操作助理档案成功！");
			 return Result;
				}catch(Exception e) {
					e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}


@PostMapping("/addPerson")
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
					e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}


	@GetMapping("/queryPerPageBylabIdAndHsta")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryPerPageBylabIdAndHsta() {
		//////档案管理分页////////
		try{
			PerDtos.clear();
			int lID=LabSer.queryLaboratorysByTechId(getNum()).get(0).getLabId() ;  //�ҳ��뼼��ԱIDƥ���ʵ����ID
			//再根据实验室ID与录入状态找出对应的实验室助理
			if(lID!=0) {
				PerDtos=PersonSer.queryPersonByplabIdAndSta(lID,"实验室助理");
			}else {
				PerDtos=PersonSer.queryPersonByplabIdAndSta(tempLID,"实验室助理");
			}
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
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}
	}


@GetMapping("/queryPersonPageByPlanId")
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
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}
	}


	@GetMapping("/deletePersonById")
	@RequiresRoles("technician")
	public ResponseResult deletePersonById(PersonInfoDto pDto) {

		 try{
				planId=enlistService.queryEnlistBypersonId(pDto.getPersonId()).getPlanId();
				tempLID = planservice.queryPlanById(planId).getLabId();
			    PersonSer.deletePersonInfoById(pDto.getPersonId());

			 Result.setData(null);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("删除助理档案成功！");
			 return Result;
				}catch(Exception e) {
			 e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}


	@GetMapping("/getPhotoById")
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


	@GetMapping("/personDetails")
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
				e.printStackTrace();
				Result.setStatus(ResponseCode.getErrorcode());
				Result.setMessage("服务器出错了！请联系管理员修理~");
				return Result;
			}
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
