package org.wzxy.breeze.service.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.technicianMapper;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.Technician;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.ITechnicianService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TechnicianServiceImpl   implements ITechnicianService {
	@Autowired
	private List<Technician> TechList  ;
	@Autowired
	private Technician technician ;
	@Autowired
	private HandleResult handle ;
	private int exist=-1;

	@Resource
	private technicianMapper technicianDao;
	@Override
	@CacheEvict(cacheNames = "technicianZone",allEntries = true)
	public HandleResult addTech(TechnicianDto Techdto) {



		exist=technicianDao.isExist(Techdto.getTechId());
		if(exist==0){
			if(technicianDao.addTechnician(new Technician(Techdto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("新增实验技术员成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("新增实验技术员失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("新增失败，实验室技术员已存在!");
			return handle;
		}

	}

	@Override
	@CacheEvict(cacheNames = "technicianZone",allEntries = true)
	public HandleResult deleteTechById(int techId) {
		// TODO Aut o-generated method stub
      return  null;
	}

	@Override
	@Cacheable(value = "technicianZone" , key = "'queryTechById'+#techId")
	public TechnicianDto queryTechById(int techId) {
		technician=technicianDao.queryTechnicianById(techId);
		if(technician!=null) {
			return new TechnicianDto(technician);
		}
		return null;
	}

	@Override
	@CacheEvict(cacheNames = "technicianZone",allEntries = true)
	public HandleResult updateTech(TechnicianDto Techdto) {
		// TODO Auto-generated method stub
          return null;
	}

	@Override
	@Cacheable(value = "technicianZone" , key = "'TechPaging'+#nowPage+','+#pageSize")
	public Page<Technician> TechPaging(int nowPage, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable(value = "technicianZone" , key = "'getAllTech'")
	public List<Technician> getAllTech() {
		// TODO Auto-generated method stub
		return null;
	}

	@Cacheable(value = "technicianZone" , key = "'getNoLabTechByDepId'+#depId")
	public List<Technician> getNoLabTechByDepId(int depId) {

		return technicianDao.queryTechnicianByworkSta("0",depId);
	}

	@Override
	@CacheEvict(cacheNames = "technicianZone",allEntries = true)
	public HandleResult updateworkStaById(int techId,String workSta) {
		technician=technicianDao.queryTechnicianById(techId);
		technician.setWorkSta(workSta);

		exist=technicianDao.isExist(techId);
		if(exist==1){
			if(technicianDao.updateTechnician(technician)){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("更新实验技术员成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("更新实验技术员失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("更新失败，实验室技术员不存在!");
			return handle;
		}


	}
	/////////////////////////////////////



}
