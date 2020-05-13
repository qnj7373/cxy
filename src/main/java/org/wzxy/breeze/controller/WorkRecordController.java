package org.wzxy.breeze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.WorkRecordBase;
import org.wzxy.breeze.model.dto.WorkRecordDto;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.ILaboratoryService;
import org.wzxy.breeze.service.Iservice.IPersonInfoService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.Iservice.IWorkRecordService;
import org.wzxy.breeze.service.serviceImpl.LaboratoryServiceImpl;
import org.wzxy.breeze.service.serviceImpl.PersonInfoServiceImpl;
import org.wzxy.breeze.service.serviceImpl.WorkRecordServiceImpl;
import org.wzxy.breeze.utils.getUId;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/workRecord")
public class WorkRecordController extends WorkRecordBase {
	@Autowired
	private ILaboratoryService LabSer;
	@Autowired
	private IWorkRecordService workSer;
	@Autowired
	private IUserService UserService;
	@Autowired
	private IPersonInfoService PersonSer;
	private int techId;
	private WorkRecordDto WordrecordDto=new WorkRecordDto();
	private  ResponseResult Result = new ResponseResult();

	public String toaddRecord() {

		 try{
			 PersonDto=PersonSer.queryPersonInfoById(WordrecordDto.getPersonId());
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}


	public String addWorkRecord() {

		 try{
			 workSer.addWorkRecord(WordrecordDto);
			 queryWorkRecordPageBypersonId(); //����ȥ
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}


	public String deleteWorkRecordById() {

		 try{
			 workSer.deleteWorkRecordById(WordrecordDto.getRecordId());
			 queryWorkRecordPageBypersonId(); //����ȥ
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

@GetMapping("/queryWorkRecordById")
	public ResponseResult queryWorkRecordById(WorkRecordDto wDto) {
		try{
			workDtos.clear();
			WordrecordDto=workSer.queryWorkRecordById(wDto.getRecordId());
			Result.setData(WordrecordDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("加载记录信息成功！");
			return Result;
		}catch(Exception e) {
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}

	/*@GetMapping("/TechqueryRecordBylabIdAndDate")
	public String TechqueryWorkRecordById() {
		try{
			WordrecordDto=workSer.queryWorkRecordById(WordrecordDto.getRecordId());
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}*/


	@GetMapping("/getRcordsDateBylabId")
	public ResponseResult getRcordsDateBylabId() {
			////技术员按日期查看记录的日期导航

  		try{
			workDtos.clear();
			//techId=getNum()
  			int lId=LabSer.queryLaboratorysByTechId(getNum()).get(0).getLabId();
			WordrecordDto.setLabId(lId);
			 queryWorkRecordBylabId();
			workDtos=workSer.getRcordsdate(workDtos);
			Result.setData( workDtos);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取导航信息成功！");
			return Result;
			}catch(Exception e) {
				e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
			}
  	}


@GetMapping("/queryRecordBylabIdAndDate")
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
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}
	//###################################

	public String queryWorkRecordBylabId() {
		try{
			workDtos.clear();
			int lId=LabSer.queryLaboratorysByTechId(getNum()).get(0).getLabId();
			workDtos=workSer.queryWorkRecordBylabId(lId);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String queryWorkRecordBypersonId() {
		try{
			workDtos=workSer.queryWorkRecordBypersonId(WordrecordDto.getPersonId());
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String updateWorkRecord() {

		try{
			workSer.updateWorkRecord(WordrecordDto);
			 queryWorkRecordPageBypersonId(); //����ȥ
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}


	public String queryWorkRecordPageBypersonId() { //////�����ҳ////////
		try{
			queryWorkRecordBypersonId();
			 /////��ʼֵ����++++++++++++++++++++
			 if(workDtos!=null) {
				 Wrecordpage=workSer.WorkPaging(workDtos, WordrecordDto.getNowPage(),WordrecordDto.getPageSize());
				}else {
					Wrecordpage=null;
				}
			     return "success";

				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

	@GetMapping("/queryWorkRecordPageBylabId")
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
