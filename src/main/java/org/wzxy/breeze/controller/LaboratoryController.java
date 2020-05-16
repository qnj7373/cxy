package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.LaboratoryBase;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IDepartmentService;
import org.wzxy.breeze.service.Iservice.ILaboratoryService;
import org.wzxy.breeze.service.Iservice.ITechnicianService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.utils.getUId;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/laboratory")
public class LaboratoryController extends LaboratoryBase {
	@Autowired
	private ILaboratoryService LabSer;
	@Autowired
	private IDepartmentService DepSer;
	@Autowired
	private ITechnicianService TechSer;
	@Autowired
	private IUserService UserService;
	private  ResponseResult Result = new ResponseResult();
	private String SourceFlag;


//////////����Lab��action����
@GetMapping("/toAddLab")
@RequiresRoles("technician")
	public ResponseResult toadd(LaboratoryDto LabDto) {
		 try{
			 LabDto.setTechId(getNum());
			 int dId=TechSer.queryTechById(LabDto.getTechId()).getDepId();
			 DepDto=DepSer.queryDepartmentById(dId);
			 Techlist=TechSer.getNoLabTechByDepId(dId);
			 DepDto.setTechlist(Techlist);
			 Result.setData(DepDto);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("获取实验室信息成功！");
			 return Result;
				}catch(Exception e) {
					e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}



@PostMapping("/addLab")
@RequiresRoles("technician")
public  ResponseResult addLab(LaboratoryDto LabDto) {
	 try{
		 LabSer.addLaboratory(LabDto);
		 TechSer.updateworkStaById(LabDto.getTechId(), "1");
		 Result.setStatus(ResponseCode.getOkcode());
		 Result.setMessage("添加实验室信息成功！");
		 return Result;
			}catch(Exception e) {
				e.printStackTrace();
		 Result.setStatus(ResponseCode.getErrorcode());
		 Result.setMessage("服务器出错了！请联系管理员修理~");
		 return Result;
			}
}

@GetMapping("/deleteLabById")
@RequiresRoles("technician")
public ResponseResult deleteLabById(LaboratoryDto LabDto) {

	 try{
		 System.out.println("删除  "+ LabDto.getLabId());
		 int tid=LabSer.queryLaboratoryById(LabDto.getLabId()).getTechId();
		 LabSer.deleteLaboratoryById(LabDto.getLabId());
		 TechSer.updateworkStaById(tid, "0");
		 Result.setStatus(ResponseCode.getOkcode());
		 Result.setMessage("删除实验室信息成功！");
		 return Result;
			}catch(Exception e) {
				e.printStackTrace();
		 Result.setStatus(ResponseCode.getErrorcode());
		 Result.setMessage("服务器出错了！请联系管理员修理~");
		 return Result;
			}
}

@RequiresRoles("technician")
public String queryLabBydId(LaboratoryDto LabDto) {         //��
	try{
		labDtos=LabSer.queryLaboratorysByTechId(LabDto.getTechId());
		 if(labDtos!=null) {
			 labpage=LabSer.LabPaging(labDtos,LabDto.getNowPage(),LabDto.getPageSize());
			}else {
				labpage=null;
			}
		return "success";
	}catch(Exception e) {
		e.printStackTrace();
		return "error";
	}
}

@GetMapping("/queryLabById")
@RequiresRoles("technician")
public ResponseResult queryLabById(LaboratoryDto LabDto) {
	try{
		LabDto=LabSer.queryLaboratoryById(LabDto.getLabId());
		DepDto=DepSer.queryDepartmentById(LabDto.getDepId());
		LabDto.setDepName(DepDto.getDepName());
		TechDto=TechSer.queryTechById(LabDto.getTechId());
		LabDto.setTechName(TechDto.getTechName());
		Result.setData(LabDto);
		Result.setStatus(ResponseCode.getOkcode());
		Result.setMessage("获取实验室信息成功！");
		return Result;
	}catch(Exception e) {
		e.printStackTrace();
		Result.setStatus(ResponseCode.getErrorcode());
		Result.setMessage("服务器出错了！请联系管理员修理~");
		return Result;
	}
}

@GetMapping("/queryLabByNameAndDep")
@RequiresRoles("technician")
public ResponseResult queryLabByLabNameAndDepId(LaboratoryDto LabDto) {
	try{
		//###################
		labDtos.clear();
		labDtos=LabSer.queryLabBylabNameAndDepId(LabDto.getLabName(), LabDto.getDepId());
		if (labDtos==null){
			Result.setData(null);
			Result.setStatus(ResponseCode.getFailcode());
			Result.setMessage("不存在该实验室！");
			return Result;
		}else{
			Result.setData(labDtos);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("查询成功！");
			return Result;
		}

	}catch(Exception e) {
		e.printStackTrace();
		Result.setStatus(ResponseCode.getErrorcode());
		Result.setMessage("服务器出错了！请联系管理员修理~");
		return Result;
	}
}


@GetMapping("/queryLabByPage")
@RequiresRoles("technician")
public ResponseResult queryLabByPage(LaboratoryDto LabDto) {
	try{
		labDtos.clear();
		LabDto.setTechId(getNum());
		int did=0;
		if(LabDto.getTechId()!=0) {
			 did=TechSer.queryTechById(LabDto.getTechId()).getDepId();
			labDtos=LabSer.queryLaboratorysBydepId(did);
		}else {
			labDtos=LabSer.queryLaboratorysBydepId(LabDto.getDepId());
		}
		 if(labDtos!=null) {
			 labpage=LabSer.LabPaging(labDtos,LabDto.getNowPage(),LabDto.getPageSize());
			}else {
				labpage=null;
			}
		labpage.setCommonFlag(String.valueOf(did));
		Result.setData(labpage);
		Result.setStatus(ResponseCode.getOkcode());
		Result.setMessage("获取实验室信息成功！");
		return Result;
	}catch(Exception e) {
		e.printStackTrace();
		Result.setStatus(ResponseCode.getErrorcode());
		Result.setMessage("服务器出错了！请联系管理员修理~");
		return Result;
	}
}

@RequiresRoles("technician")
@PostMapping("/updateLab")
public ResponseResult updateLab(LaboratoryDto LabDto) {

	 try{
		    LabSer.updateLaboratory(LabDto);
				 Result.setStatus(ResponseCode.getOkcode());
				 Result.setMessage("修改实验室信息成功！");
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




//////////����Class��action����

	//////////////////////


	public String getSourceFlag() {
		return SourceFlag;
	}

	public void setSourceFlag(String sourceFlag) {
		SourceFlag = sourceFlag;
	}

	public LaboratoryDto getLabDto() {
		return LabDto;
	}

	public void setLabDto(LaboratoryDto labDto) {
		LabDto = labDto;
	}

}
