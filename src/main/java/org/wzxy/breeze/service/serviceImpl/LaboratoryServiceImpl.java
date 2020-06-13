package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.departmentMapper;
import org.wzxy.breeze.mapper.laboratoryMapper;
import org.wzxy.breeze.mapper.technicianMapper;
import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.Laboratory;
import org.wzxy.breeze.model.po.Technician;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.ILaboratoryService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class LaboratoryServiceImpl   implements ILaboratoryService {

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
	private HandleResult handle ;
	private int exist=-1;
	@Resource
	private laboratoryMapper laboratoryDao;
	@Resource
	private technicianMapper technicianDao;
	@Resource
	private departmentMapper Departmentdao;
	@Override
	@CacheEvict(cacheNames = "laboratoryZone",allEntries = true)
	public HandleResult addLaboratory(LaboratoryDto lDto) {

		exist=laboratoryDao.isExist(lDto.getLabId());
		if(exist==0){
			if(laboratoryDao.addLaboratory(new Laboratory(lDto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("新增实验室成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("新增实验室失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("新增失败，实验室已存在!");
			return handle;
		}

	}

	@Override
	@CacheEvict(cacheNames = "laboratoryZone",allEntries = true)
	public HandleResult deleteLaboratoryById(int labId) {

		exist=laboratoryDao.isExist(labId);
		if(exist==1){
			Lab=laboratoryDao.queryLaboratoryById(labId);
			if(laboratoryDao.deleteLaboratoryById(Lab)){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("删除实验室成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("删除实验室失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("删除失败，实验室不存在!");
			return handle;
		}

	}

	@Override
	@Cacheable(value = "laboratoryZone" , key = "'queryLaboratoryById'+#labId")
	public LaboratoryDto queryLaboratoryById(int labId) {
		Lab=laboratoryDao.queryLaboratoryById(labId);
		if(Lab!=null) {
			return 	new LaboratoryDto(Lab);
		}else {
			return null;
		}
	}

	@Override
	@Cacheable(value = "laboratoryZone" , key = "'queryLabBylabNameAndDepId'+#labName+','+#depId")
	public List<LaboratoryDto> queryLabBylabNameAndDepId(String labName,int depId) {
		labDtos.clear();
		labList.clear();
		labList=laboratoryDao.queryLabBylabNameAndDepId(labName, depId);
		if(labList!=null) {
			for(Laboratory lab:
					labList
			) {
				LaboratoryDto laboratoryDto = new LaboratoryDto(lab);
				laboratoryDto.setDepName(Departmentdao.queryDepById(lab.getDepId()).getDepName());
				laboratoryDto.setTechName(technicianDao.queryTechnicianById(lab.getTechId()).getTechName());
				labDtos.add(laboratoryDto);
			}
			return labDtos;
		}else {
			return null;
		}


	}

	@Override
	@CacheEvict(cacheNames = "laboratoryZone",allEntries = true)
	public HandleResult updateLaboratory(LaboratoryDto lDto) {
		exist=laboratoryDao.isExist(lDto.getLabId());
		if(exist==1){
			if(laboratoryDao.updateLaboratory(new Laboratory(lDto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("更新实验室成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("更新实验室失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("更新失败，实验室不存在!");
			return handle;
		}

	}

	@Override
	@Cacheable(value = "laboratoryZone" , key = "'LabPaging'+#nowPage+','+#pageSize")
	public Page<LaboratoryDto> LabPaging(List<LaboratoryDto> labDtos,int nowPage, int pageSize) {
		if(labDtos!=null) {
			for(int i=0;i<labDtos.size();i++) {
				String name=technicianDao.queryTechnicianById(labDtos.get(i).getTechId()).getTechName();
				labDtos.get(i).setTechName(name);
				labDtos.get(i).setDepName(Departmentdao.queryDepById(labDtos.get(i).getDepId()).getDepName());
			}
		}
		if(pageSize==0) {
			pageSize=3;
		}
		labpage.setDataTotalCount(labDtos.size());
		labpage.setPageSize(pageSize);
		labpage.setPageTotalCount(labDtos.size()%pageSize==0?labDtos.size()/pageSize:(labDtos.size()/pageSize)+1);
		if(nowPage==labpage.getPageTotalCount()) {      ///如果删除的是最后一条数据则当前页数等于页面总数减1
			if(nowPage!=0) {
				nowPage=labpage.getPageTotalCount()-1;
			}
		}
		labpage.setNowPage(nowPage+1);
		int errorfix=nowPage*pageSize;
		int wsize=labDtos.size();
		int fixTo=(nowPage*pageSize)+pageSize;
		if(nowPage<0) {
			errorfix=0;
			wsize=3;
			fixTo=3;
			labpage.setNowPage(errorfix+1);
			labpage.setPageSize(fixTo);
		}
		if(labDtos.size()>=pageSize) {   //判断页内数据能否构成满页的if
			if((nowPage+1)==labpage.getPageTotalCount()) {              //判断下一页是否是最后一页
				labDtos= new ArrayList(labDtos.subList(errorfix,wsize)) ;
			}else {
				labDtos= new ArrayList(labDtos.subList(errorfix,fixTo)) ;
			}
		}//判断页内数据能否构成满页的if
		else {
			labDtos= new ArrayList(labDtos.subList(errorfix,labDtos.size()))  ;
		}
		labpage.setDatas(labDtos);
		labpage.setCommonObject(labDtos.get(0));
		return labpage;
	}

	@Override
	@Cacheable(value = "laboratoryZone" , key = "'getAllLaboratory'")
	public List<LaboratoryDto> getAllLaboratory() {

		labDtos.clear();
		for(Laboratory lab:laboratoryDao.getAllLaboratory()) {
			LaboratoryDto laboratoryDto = new LaboratoryDto(lab);
			laboratoryDto.setDepName(Departmentdao.queryDepById(lab.getDepId()).getDepName());
			labDtos.add(laboratoryDto);
		}
		return labDtos;
	}

	@Override
	@Cacheable(value = "laboratoryZone" , key = "'queryLaboratorysByTechId'+#techId")
	public List<LaboratoryDto> queryLaboratorysByTechId(int techId) {
		labDtos.clear();
		lablist=laboratoryDao.queryLaboratorysByTechId(techId);
		if(lablist!=null) {
			for(Laboratory L:lablist) {

				labDtos.add(new LaboratoryDto(L));
			}
		}
		return labDtos;
	}

	@Override
	@Cacheable(value = "laboratoryZone" , key = "'queryLaboratorysBydepId'+#depId")
	public List<LaboratoryDto> queryLaboratorysBydepId(int depId) {
		labDtos.clear();
		lablist=laboratoryDao.queryLaboratorysByDepId(depId);
		if(lablist!=null) {
			for(Laboratory L:lablist) {
				labDtos.add(new LaboratoryDto(L));
			}
		}
		return labDtos;
	}



}
