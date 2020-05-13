package org.wzxy.breeze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.MaintenanceBase;
import org.wzxy.breeze.model.dto.MaintenanceDto;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.ILaboratoryService;
import org.wzxy.breeze.service.Iservice.IMaintenanceService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.serviceImpl.LaboratoryServiceImpl;
import org.wzxy.breeze.service.serviceImpl.MaintenanceServiceImpl;
import org.wzxy.breeze.utils.getUId;
import org.wzxy.breeze.utils.wordUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/maintenance")

public class MaintenanceController extends MaintenanceBase {
	@Autowired
	private ILaboratoryService LabSer;
	@Autowired
	private IMaintenanceService MainService;
	@Autowired
	private IUserService UserService;

	private MaintenanceDto MaintenDto;
	private  ResponseResult Result = new ResponseResult();
	private int techId;

	@GetMapping("/exportOne")
	public ResponseResult exportOne(MaintenanceDto mDto,HttpServletRequest request, HttpServletResponse response) throws IOException{    //����һ����
		//
		try{
			Map<String,Object> params = new HashMap<>();
			////////////////////
			MaintenDto=MainService.queryMaintenById(mDto.getMainId());
			params.put("depName",MaintenDto.getDepName());
			params.put("labName",MaintenDto.getLabName());
			params.put("personName",MaintenDto.getPersonName());
			params.put("reportDate",MaintenDto.getReportDate());
			params.put("equName",MaintenDto.getEquName());
			params.put("equId",MaintenDto.getEquId());
			params.put("equtype",MaintenDto.getEqutype());
			params.put("description",MaintenDto.getDescription());
			wordUtils.exportWord("static/work/maintenance.docx","实验室维修申请表.docx",params,request,response);
			Result.setData(null);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("导出成功！");
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			Result.setData(null);
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}

	   }

	public String exportAll() throws IOException{    //����ȫ����
		MainDtos.clear();
		queryMainByLabId();
		return MainService.export(MainDtos);
	}

	public String queryMainBypersonId() {
		try{
			MainDtos=MainService.queryMaintenByPerId(MaintenDto.getPersonId());
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String queryMainByLabId() {
		try{
			labDtos.clear();
			MainDtos.clear();
			int lId=LabSer.queryLaboratorysByTechId(getNum()).get(0).getLabId();
			/*if(techId!=0) {
				labDtos=LabSer.queryLaboratorysByTechId(techId);
				if(labDtos.size()!=0) {
					LabDto=labDtos.get(0);
				}
			}*/
			MainDtos=MainService.queryMaintenByLabId(lId);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	public String queryMainPageBypersonId() { //////�����ҳ////////
		try{
			 queryMainBypersonId();
			 /////��ʼֵ����++++++++++++++++++++
			 if(MainDtos!=null) {
				 Maintenpage=MainService.MainPaging(MainDtos, MaintenDto.getNowPage(),MaintenDto.getPageSize());
				}else {
					Maintenpage=null;
				}
			     return "success";

				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

	@GetMapping("/queryMainPageByLabId")    /////////////////////////////////////
	public ResponseResult queryMainPageByLabId(MaintenanceDto mDto) { //////����Ա��ά�޷�ҳ////////
		try{
			queryMainByLabId();
			/////��ʼֵ����++++++++++++++++++++
			if(MainDtos!=null) {
				Maintenpage=MainService.MainPaging(MainDtos, mDto.getNowPage(),mDto.getPageSize());
			}else {
				Maintenpage=null;
			}
			Result.setData(Maintenpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取维修申请列表成功！");
			return Result;

		}catch(Exception e) {
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}


	public String addMainten() {

		 try{
			 MainService.addMainten(MaintenDto);
			 queryMainPageBypersonId(); //����ȥ
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}

/////////////////////////查找维修单信息
	public String queryMaintenById() {
		try{
			MaintenDto=MainService.queryMaintenById(MaintenDto.getMainId());
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping("/TechqueryMaintenById")
	public ResponseResult TechqueryMaintenById(MaintenanceDto mDto) {
		try{
			MaintenDto=MainService.queryMaintenById(mDto.getMainId());
			Result.setData(MaintenDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取维修申请详细信息成功！");
			return Result;
		}catch(Exception e) {
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}

	public String updateMainten() {
		try{
			MainService.updateMainten(MaintenDto);
			queryMainPageBypersonId(); //����ȥ
				return "success";
				}catch(Exception e) {
					e.printStackTrace();
					return "error";
				}
	}


		public String deleteMaintenById() {
			try{
				MainService.deleteMaintenById(MaintenDto.getMainId());
				queryMainPageBypersonId(); //����ȥ
					return "success";
					}catch(Exception e) {
						e.printStackTrace();
						return "error";
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
	public void setMainService(MaintenanceServiceImpl mainService) {
		MainService = mainService;
	}
	public void setLabSer(LaboratoryServiceImpl labSer) {
		LabSer = labSer;
	}
	public MaintenanceDto getMaintenDto() {
		return MaintenDto;
	}

	public void setMaintenDto(MaintenanceDto maintenDto) {
		MaintenDto = maintenDto;
	}

	public int getTechId() {
		return techId;
	}

	public void setTechId(int techId) {
		this.techId = techId;
	}



}
