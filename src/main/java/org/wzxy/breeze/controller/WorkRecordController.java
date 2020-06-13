package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.dto.WorkRecordDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.WorkRecord;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.ILaboratoryService;
import org.wzxy.breeze.service.Iservice.IPersonInfoService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.Iservice.IWorkRecordService;
import org.wzxy.breeze.service.serviceImpl.LaboratoryServiceImpl;
import org.wzxy.breeze.service.serviceImpl.PersonInfoServiceImpl;
import org.wzxy.breeze.service.serviceImpl.WorkRecordServiceImpl;
import org.wzxy.breeze.service.serviceImpl.getStatusService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/workRecord")
public class WorkRecordController  {
	@Autowired
	private ILaboratoryService LabSer;
	@Autowired
	private IWorkRecordService workSer;
	@Autowired
	private IUserService UserService;

	@Autowired
	private List<WorkRecord> workList ;
	@Autowired
	private Page<WorkRecordDto> Wrecordpage ;
	@Autowired
	private WorkRecord workRecord ;
	@Autowired
	private List<WorkRecordDto> workDtos ;
	@Autowired
	private PersonInfoDto PersonDto ;
	@Autowired
	private IPersonInfoService PersonSer;
	@Autowired
	private HandleResult handle;
	private int techId;
	@Autowired
	private WorkRecordDto WordrecordDto ;
	@Autowired
	private  ResponseResult Result ;
	@Autowired
	private Logger logger;
	@Autowired
	private getStatusService Status;
	@GetMapping("/toaddRecord")
	@RequiresRoles("assistant")
	public ResponseResult toaddRecord() {

		 try{
			 PersonDto=PersonSer.queryPersonInfoById(PersonSer.queryPersonInfoByStudentId(Status.getNum()).getPersonId());
			 Result.setData(PersonDto);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("加载所需信息成功！");
			 return Result;
				}catch(Exception e) {
					logger.error(e.getMessage());
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员处理~");
			 return Result;
				}
	}

@PostMapping("/addWorkRecord")
@RequiresRoles("assistant")
	public ResponseResult addWorkRecord(WorkRecordDto wDto) {

		 try{
			 handle=workSer.addWorkRecord(wDto);
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


	@GetMapping("/deleteWorkRecordById")
	@RequiresRoles("assistant")
	public ResponseResult deleteWorkRecordById(WorkRecordDto wDto) {

		 try{
			 handle=workSer.deleteWorkRecordById(wDto.getRecordId());
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

@GetMapping("/queryWorkRecordById")
@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryWorkRecordById(WorkRecordDto wDto) {
		try{
			workDtos.clear();
			WordrecordDto=workSer.queryWorkRecordById(wDto.getRecordId());
			Result.setData(WordrecordDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("加载记录信息成功！");
			return Result;
		}catch(Exception e) {
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
		}
	}



	@GetMapping("/getRcordsDateBylabId")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult getRcordsDateBylabId() {
			////技术员按日期查看记录的日期导航
  		try{
			workDtos.clear();
			//techId=getNum()
  			int lId=LabSer.queryLaboratorysByTechId(Status.getNum()).get(0).getLabId();
			WordrecordDto.setLabId(lId);
			 queryWorkRecordBylabId();
			workDtos=workSer.getRcordsdate(workDtos);
			Result.setData( workDtos);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取导航信息成功！");
			return Result;
			}catch(Exception e) {
				logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
			}
  	}


@GetMapping("/queryRecordBylabIdAndDate")
@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult TechqueryRecordBylabIdAndDate(WorkRecordDto WDto) {
		try{
			queryWorkRecordBylabId();///workDtos里已经有数据了
			SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date =simpleFormat.parse(WDto.getRecordDate());
			String format = simpleFormat.format(date);

			 if(workDtos!=null) {
				 List<WorkRecordDto> workDtoList =workSer.queryWorkRecordByDate(workDtos, format);
				 if( workDtoList.size()!=0){
					 Result.setData(workDtoList);
					 Result.setStatus(ResponseCode.getOkcode());
					 Result.setMessage("查询成功！");
				 }else{
					 Result.setData(null);
					 Result.setStatus(ResponseCode.getFailcode());
					 Result.setMessage("不存在该日期的记录！");
				 }

			 }else {
				 Result.setData(null);
				 Result.setStatus(ResponseCode.getFailcode());
				 Result.setMessage("不存在该日期的记录！");
				}
			return Result;
		}catch(Exception e) {
			logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
		}
	}
	//###################################

	public String queryWorkRecordBylabId() {
		try{
			workDtos.clear();
			int lId=LabSer.queryLaboratorysByTechId(Status.getNum()).get(0).getLabId();
			workDtos=workSer.queryWorkRecordBylabId(lId);
			return "success";
		}catch(Exception e) {
			logger.error(e.getMessage());
			return "error";
		}
	}

	public String queryWorkRecordBypersonId() {
		try{
			int pId=PersonSer.queryPersonInfoByStudentId(Status.getNum()).getPersonId();
			workDtos=workSer.queryWorkRecordBypersonId(pId);
			return "success";
		}catch(Exception e) {
			logger.error(e.getMessage());
			return "error";
		}
	}

	@PostMapping("/updateWorkRecord")
	@RequiresRoles("assistant")
	public ResponseResult updateWorkRecord(WorkRecordDto WDto) {

		try{
			handle=workSer.updateWorkRecord(WDto);
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

@GetMapping("/queryWorkRecordPageBypersonId")
@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryWorkRecordPageBypersonId(WorkRecordDto WDto) {
	//////助理分页////////
		try{
			queryWorkRecordBypersonId();
			/////初始值设置++++++++++++++++++++
			 if(workDtos!=null) {
				 Wrecordpage=workSer.WorkPaging(workDtos, WDto.getNowPage(),WDto.getPageSize());
				}else {
					Wrecordpage=null;
				}
			Result.setData(Wrecordpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取工作记录成功！");
			return Result;

				}catch(Exception e) {
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}
	}

	@GetMapping("/queryWorkRecordPageBylabId")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryWorkRecordPageBylabId(WorkRecordDto wDto) { //////����Ա�鿴��¼��ҳ////////
		try{

			queryWorkRecordBylabId();
			 /////��ʼֵ����++++++wDto++++++++++++++
			 if(workDtos!=null) {
				 Wrecordpage=workSer.WorkPaging(workDtos, wDto.getNowPage(),wDto.getPageSize());
				}else {
					Wrecordpage=null;
				}
			Result.setData(Wrecordpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取工作记录成功！");
			return Result;

				}catch(Exception e) {
					logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
				}
	}



	//将前台的date数据进行转换
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {
		//转换日期 注意这里的转化要和传进来的字符串的格式一直 如2015-9-9 就应该为yyyy-MM-dd
		DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}


	public WorkRecordDto getWordrecordDto() {
		return WordrecordDto;
	}

	public void setWordrecordDto(WorkRecordDto wordrecordDto) {
		WordrecordDto = wordrecordDto;
	}

	public void setWorkSer(WorkRecordServiceImpl workSer) {
		this.workSer = workSer;
	}

	public void setPersonSer(PersonInfoServiceImpl personSer) {
		PersonSer = personSer;
	}
	public void setLabSer(LaboratoryServiceImpl labSer) {
		LabSer = labSer;
	}

	public int getTechId() {
		return techId;
	}

	public void setTechId(int techId) {
		this.techId = techId;
	}

}
