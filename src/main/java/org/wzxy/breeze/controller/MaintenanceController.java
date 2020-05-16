package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.wzxy.breeze.BaseStore.MaintenanceBase;
import org.wzxy.breeze.model.dto.MaintenanceDto;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.ILaboratoryService;
import org.wzxy.breeze.service.Iservice.IMaintenanceService;
import org.wzxy.breeze.service.Iservice.IPersonInfoService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.serviceImpl.LaboratoryServiceImpl;
import org.wzxy.breeze.service.serviceImpl.MaintenanceServiceImpl;
import org.wzxy.breeze.utils.getUId;
import org.wzxy.breeze.utils.wordUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/maintenance")

public class MaintenanceController extends MaintenanceBase {
	@Autowired
	private ILaboratoryService LabSer;
	@Autowired
	private IMaintenanceService MainService;
	@Autowired
	private IUserService UserService;
	@Autowired
	private IPersonInfoService PersonSer;

	private MaintenanceDto MaintenDto;
	private  ResponseResult Result = new ResponseResult();
	private int techId;

	@GetMapping("/exportOne")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
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
			int perId = PersonSer.queryPersonInfoByStudentId(getNum()).getPersonId();
			MainDtos=MainService.queryMaintenByPerId(perId);
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
			MainDtos=MainService.queryMaintenByLabId(lId);
			return "success";
		}catch(Exception e) {
			e.printStackTrace();
			return "error";
		}
	}

	@GetMapping("/queryMainPageBypersonId")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryMainPageBypersonId(MaintenanceDto mDto) {
		try{
			 queryMainBypersonId();
			 if(MainDtos!=null) {
				 Maintenpage=MainService.MainPaging(MainDtos, mDto.getNowPage(),mDto.getPageSize());
				}else {
					Maintenpage=null;
				}
			Result.setData(Maintenpage);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取维修信息列表成功！");
			return Result;
				}catch(Exception e) {
					e.printStackTrace();
				Result.setStatus(ResponseCode.getErrorcode());
				Result.setMessage("服务器出错了！请联系管理员修理~");
				return Result;
				}
	}

	@GetMapping("/queryMainPageByLabId")    /////////////////////////////////////
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryMainPageByLabId(MaintenanceDto mDto) { //////����Ա��ά�޷�ҳ////////
		try{
			queryMainByLabId();
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


	@PostMapping("/addMainten")
	@RequiresRoles("assistant")
	public ResponseResult addMainten(MaintenanceDto mDto) {

		 try{
			 MainService.addMainten(mDto);
			 Result.setStatus(ResponseCode.getOkcode());
			 Result.setMessage("添加维修申请成功！");
			 return Result;
				}catch(Exception e) {
					e.printStackTrace();
			 Result.setStatus(ResponseCode.getErrorcode());
			 Result.setMessage("服务器出错了！请联系管理员修理~");
			 return Result;
				}
	}

/////////////////////////查找维修单信息
	@GetMapping("/queryMaintenById")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
	public ResponseResult queryMaintenById(MaintenanceDto mDto) {
		try{
			MaintenDto=MainService.queryMaintenById(mDto.getMainId());
			Result.setData(MaintenDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取维修申请成功！");
			return Result;
		}catch(Exception e) {
			e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
		}
	}

	@GetMapping("/TechqueryMaintenById")
	@RequiresRoles(value={"assistant","technician"},logical = Logical.OR)
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

	@PostMapping("/updateMainten")
	@RequiresRoles("assistant")
	public ResponseResult updateMainten(MaintenanceDto mDto) {
		try{
			MainService.updateMainten(mDto);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("修改维修申请信息成功！");
			return Result;
				}catch(Exception e) {
					e.printStackTrace();
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员修理~");
			return Result;
				}
	}

@GetMapping("/deleteMaintenById")
@RequiresRoles("assistant")
		public ResponseResult deleteMaintenById(MaintenanceDto mDto) {
			try{
				MainService.deleteMaintenById(mDto.getMainId());
				Result.setStatus(ResponseCode.getOkcode());
				Result.setMessage("删除维修申请成功！");
				return Result;
					}catch(Exception e) {
						e.printStackTrace();
				Result.setStatus(ResponseCode.getErrorcode());
				Result.setMessage("服务器出错了！请联系管理员修理~");
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
