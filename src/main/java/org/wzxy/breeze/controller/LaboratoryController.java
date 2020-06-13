package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.Laboratory;
import org.wzxy.breeze.model.po.Technician;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IDepartmentService;
import org.wzxy.breeze.service.Iservice.ILaboratoryService;
import org.wzxy.breeze.service.Iservice.ITechnicianService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.serviceImpl.getStatusService;

import java.util.List;

@RestController
@RequestMapping("/laboratory")
public class LaboratoryController  {
	@Autowired
	private List<Laboratory> labList  ;
	@Autowired
	private Page<LaboratoryDto> labpage ;
	@Autowired
	private Laboratory Lab ;
	@Autowired
	private LaboratoryDto LabDto ;
	@Autowired
	private List<LaboratoryDto> labDtos ;
	@Autowired
	private List<Laboratory> lablist ;
	@Autowired
	private List<Technician> Techlist ;
	@Autowired
	private DepartmentDto DepDto;
	@Autowired
	private TechnicianDto TechDto;
	@Autowired
	private ILaboratoryService LabSer;
	@Autowired
	private IDepartmentService DepSer;
	@Autowired
	private ITechnicianService TechSer;
	@Autowired
	private IUserService UserService;
	@Autowired
	private  ResponseResult Result  ;
	private String SourceFlag;
	@Autowired
	private HandleResult handle;
	@Autowired
	private Logger logger;
	@Autowired
	private getStatusService Status;
//////////����Lab��action����
@GetMapping("/toAddLab")
@RequiresRoles("technician")
	public ResponseResult toadd(LaboratoryDto LabDto) {
		 try{
			 LabDto.setTechId(Status.getNum());
			 int dId=TechSer.queryTechById(LabDto.getTechId()).getDepId();
			 DepDto=DepSer.queryDepartmentById(dId);
			 Techlist=TechSer.getNoLabTechByDepId(dId);
			 DepDto.setTechlist(Techlist);
			 Result.setData(DepDto);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("获取实验室信息成功！");
			 return Result;
				}catch(Exception e) {
					logger.error(e.getMessage());
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员处理~");
			 return Result;
				}
	}



@PostMapping("/addLab")
@RequiresRoles("technician")
public  ResponseResult addLab(LaboratoryDto LabDto) {
	 try{

		 TechSer.updateworkStaById(LabDto.getTechId(), "1");
		 handle=LabSer.addLaboratory(LabDto);
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

@GetMapping("/deleteLabById")
@RequiresRoles("technician")
public ResponseResult deleteLabById(LaboratoryDto LabDto) {

	 try{
		 int tid=LabSer.queryLaboratoryById(LabDto.getLabId()).getTechId();
		 TechSer.updateworkStaById(tid, "0");
		 handle=LabSer.deleteLaboratoryById(LabDto.getLabId());
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
		logger.error(e.getMessage());
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
		logger.error(e.getMessage());
		Result.setStatus(ResponseCode.getErrorcode());
		Result.setMessage("服务器出错了！请联系管理员处理~");
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
		logger.error(e.getMessage());
		Result.setStatus(ResponseCode.getErrorcode());
		Result.setMessage("服务器出错了！请联系管理员处理~");
		return Result;
	}
}


@GetMapping("/queryLabByPage")
@RequiresRoles("technician")
public ResponseResult queryLabByPage(LaboratoryDto LabDto) {
	try{
		labDtos.clear();
		LabDto.setTechId(Status.getNum());
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
		logger.error(e.getMessage());
		Result.setStatus(ResponseCode.getErrorcode());
		Result.setMessage("服务器出错了！请联系管理员处理~");
		return Result;
	}
}

@RequiresRoles("technician")
@PostMapping("/updateLab")
public ResponseResult updateLab(LaboratoryDto LabDto) {

	 try{
		 handle=LabSer.updateLaboratory(LabDto);
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
