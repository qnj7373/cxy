package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.departmentMapper;
import org.wzxy.breeze.mapper.laboratoryMapper;
import org.wzxy.breeze.mapper.maintenanceMapper;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.MaintenanceDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.Laboratory;
import org.wzxy.breeze.model.po.Maintenance;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IMaintenanceService;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MaintenanceServiceImpl   implements IMaintenanceService {
	@Autowired
	private List<Maintenance> MainList  ;
	@Autowired
	private Page<MaintenanceDto> Maintenpage ;
	@Autowired
	private Maintenance Mainten ;
	@Autowired
	private List<MaintenanceDto> MainDtos ;
	@Autowired
	private LaboratoryDto LabDto ;
	@Autowired
	private List<LaboratoryDto> labDtos ;
	@Autowired
	private HandleResult handle ;
	private int exist=-1;
	@Resource
	private maintenanceMapper maintenDao;
	@Resource
	private laboratoryMapper laboratoryDao;
	@Resource
	private departmentMapper Departmentdao;

	public String export(List<MaintenanceDto> mainlist) throws IOException{    //导出表
		//DownloadUtils.download(mainlist);
		return null;
	}


	@Override
	@CacheEvict(cacheNames = "maintenanceZone",allEntries = true)
	public HandleResult addMainten(MaintenanceDto maindto) {
		exist=maintenDao.isExist(maindto.getMainId());
		if(exist==0){
			if(maintenDao.addMaintenance(new Maintenance(maindto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("新增维修单成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("新增维修单失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("新增维修单失败，维修单已存在!");
			return handle;
		}

	}

	@Override
	public HandleResult deleteMaintenById(int mainId) {

		exist=maintenDao.isExist(mainId);
		if(exist==0){
			Mainten=maintenDao.queryMaintenById(mainId);
			if(maintenDao.deleteMaintenance(Mainten)){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("删除维修单成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("删除维修单失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("删除维修单失败，维修单不存在!");
			return handle;
		}
	}

	////////////根据mainId查找单个维修单信息
	@Override
	@Cacheable(value = "maintenanceZone" , key = "'queryMaintenById'+#mainId")
	public MaintenanceDto queryMaintenById(int mainId) {
		Mainten=maintenDao.queryMaintenById(mainId);
		if(Mainten!=null) {
			MaintenanceDto maintenanceDto = new MaintenanceDto(Mainten);
			Laboratory laboratory = laboratoryDao.queryLaboratoryById(Mainten.getLabId());
			maintenanceDto.setLabName(
					laboratory.getLabName()
			);
			maintenanceDto.setDepName(
					Departmentdao.queryDepById(laboratory.getDepId())
					.getDepName()
			);
			return maintenanceDto;
		}else {
			return null;
		}
	}

	@Override
	@Cacheable(value = "maintenanceZone" , key = "'queryAllMainten'")
	public List<MaintenanceDto> queryAllMainten() {
		MainDtos.clear();
		for(Maintenance M:maintenDao.getAllMaintenance()) {
			MainDtos.add(new MaintenanceDto(M));
		}
		return MainDtos;
	}

	@Override
	@Cacheable(value = "maintenanceZone" , key = "'queryMaintenByLabId'+#labId")
	public List<MaintenanceDto> queryMaintenByLabId(int labId) {
		MainDtos.clear();
		List<Maintenance> mtlist=maintenDao.queryMaintenByLabId(labId);
		if(mtlist!=null) {
			for(Maintenance M:mtlist) {
				MainDtos.add(new MaintenanceDto(M));
			}
		}
		return MainDtos;
	}

	@Override
	@Cacheable(value = "maintenanceZone" , key = "'queryMaintenByPerId'+#personId")
	public List<MaintenanceDto> queryMaintenByPerId(int personId) {
		MainDtos.clear();
		List<Maintenance> mtlist=maintenDao.queryMaintenBypersonId(personId);
		if(mtlist!=null) {
			for(Maintenance M:mtlist) {
				MainDtos.add(new MaintenanceDto(M));
			}
		}
		return MainDtos;
	}

	@Override
	@CacheEvict(cacheNames = "maintenanceZone",allEntries = true)
	public HandleResult updateMainten(MaintenanceDto maindto) {
		Date tempdate=maintenDao.queryMaintenById(maindto.getMainId()).getReportTime();
		maindto.setReportTime(tempdate);

		exist=maintenDao.isExist(maindto.getMainId());
		if(exist==1){
			Mainten=maintenDao.queryMaintenById(maindto.getMainId());
			if(maintenDao.updateMainten(new Maintenance(maindto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("更新维修单成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("更新维修单失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("更新维修单失败，维修单不存在!");
			return handle;
		}
	}


	@Override
	@Cacheable(value = "maintenanceZone" , key = "'MainPaging'+#nowPage+','+#pageSize")
	public Page<MaintenanceDto> MainPaging(List<MaintenanceDto> mainDtos, int nowPage, int pageSize) {
		if(pageSize==0) {
			pageSize=3;
		}
		Maintenpage.setDataTotalCount(mainDtos.size());
		Maintenpage.setPageSize(pageSize);
		Maintenpage.setPageTotalCount(mainDtos.size()%pageSize==0?mainDtos.size()/pageSize:(mainDtos.size()/pageSize)+1);
		if(nowPage==Maintenpage.getPageTotalCount()) {      ///如果删除的是最后一条数据则当前页数等于页面总数减1
			if(nowPage!=0) {
				nowPage=Maintenpage.getPageTotalCount()-1;
			}
		}
		Maintenpage.setNowPage(nowPage+1);
		int errorfix=nowPage*pageSize;
		int wsize=mainDtos.size();
		int fixTo=(nowPage*pageSize)+pageSize;
		if(nowPage<0) {
			errorfix=0;
			wsize=3;
			fixTo=3;
			Maintenpage.setNowPage(errorfix+1);
			Maintenpage.setPageSize(fixTo);
		}
		if(mainDtos.size()>=pageSize) {   //判断页内数据能否构成满页的if

			if((nowPage+1)==Maintenpage.getPageTotalCount()) {
				//判断下一页是否是最后一页
				mainDtos= new ArrayList(mainDtos.subList(errorfix,wsize)) ;
			}else {
				mainDtos= new ArrayList(mainDtos.subList(errorfix,fixTo)) ;
			}
		}//判断页内数据能否构成满页的if
		else {
			mainDtos=new ArrayList(mainDtos.subList(errorfix,mainDtos.size()))  ;
		}
		Maintenpage.setDatas(mainDtos);
		if(mainDtos.size()!=0) {
			Maintenpage.setCommonObject(mainDtos.get(0));
		}
		return Maintenpage;

	}














}
