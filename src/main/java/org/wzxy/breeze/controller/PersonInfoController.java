package org.wzxy.breeze.controller;

import org.apache.commons.fileupload.disk.DiskFileItem;
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


	public  String toSign() {

		 try{
			 planDto=planservice.queryPlanById(planId);
			 PersonDto=PersonSer.queryPersonInfoByStudentId(pInfodto.getStudentId());
			 return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

	@GetMapping("/queryPersonById")
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



	public String addPerson() {
		 try{
			 UserDto userDto=new UserDto();
			 userDto.setUnum(pInfodto.getStudentId());
			 userDto.setUpwd(pInfodto.getPassword());
			 userDto.setUtype(utype);
			 luser=userService.register(userDto);
			 if(luser.getRegisterResult().equals("success")) {
				 pInfodto.setLabId(0);
				 pInfodto.setHirSta("未录入");
				 PersonSer.addPersonInfo(pInfodto,Photofile);
			 }
			return luser.getRegisterResult();
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}


	@GetMapping("/queryPerPageBylabIdAndHsta")
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
