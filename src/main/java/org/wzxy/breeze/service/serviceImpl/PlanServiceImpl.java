package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.laboratoryMapper;
import org.wzxy.breeze.mapper.planMapper;
import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.Plan;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IPlanService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanServiceImpl   implements IPlanService {

	@Resource
	private planMapper planDao;
	@Resource
	private laboratoryMapper laboratoryDao;
	@Autowired
	private Page<PlanDto> Planpage ;
	private int uNum;
	@Autowired
	private Plan plan ;
	@Autowired
	private List<PlanDto> PlanDtos ;
	@Autowired
	private List<Plan> Planlist ;
	@Autowired
	private DepartmentDto depmentDto ;
	@Autowired
	private TechnicianDto technicianDto ;
	@Autowired
	private List<LaboratoryDto> labDtos ;
	@Autowired
	private HandleResult handle ;
	private int exist=-1;

	@Override
	@CacheEvict(cacheNames = "planZone",allEntries = true)
	public HandleResult addPlan(PlanDto plandto) {
		plandto.setPlanSta("未审核");
		plandto.setLabName(laboratoryDao.queryLaboratoryById(plandto.getLabId()).getLabName());
		exist=planDao.isExist(plandto.getPlanId());
		if(exist==0){
			if(planDao.addPlan(new Plan(plandto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("新增计划成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("新增计划失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("新增失败，计划已存在!");
			return handle;
		}
	}

	@Override
	@Cacheable(value = "planZone" , key = "'queryPlanByPlanSta'+#planSta")
	public List<PlanDto> queryPlanByPlanSta(String planSta) {
		PlanDtos.clear();
		List<Plan> plans=planDao.queryPlanByPlanSta(planSta);
		if(plans!=null) {
			for(Plan plan:plans) {
				PlanDtos.add(new PlanDto(plan));
			}
		}
		return PlanDtos;
	}

	@Override
	@CacheEvict(cacheNames = "planZone",allEntries = true)
	public HandleResult deletePlanById(int planId) {

		exist=planDao.isExist(planId);
		if(exist==1){
			plan=planDao.queryPlanById(planId);
			if(planDao.deletePlanById(plan)){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("删除计划成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("删除计划失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("删除失败，计划不存在!");
			return handle;
		}
	}

	@Override
	@Cacheable(value = "planZone" , key = "'queryPlanById'+#planId")
	public PlanDto queryPlanById(int planId) {
		plan=planDao.queryPlanById(planId);
		if(plan!=null) {
			return new PlanDto(plan);
		}else {
			return null;
		}

	}

	@Override
	@Cacheable(value = "planZone" , key = "'queryPlanBydepId'+#depId")
	public List<PlanDto> queryPlanBydepId(int depId) {
		PlanDtos.clear();
		List<Plan> plans=planDao.queryPlanBydepId(depId);
		if(plans!=null) {
			for(Plan plan:plans) {
				PlanDtos.add(new PlanDto(plan));
			}
		}
		return PlanDtos;
	}


	@Override
	@Cacheable(value = "planZone" , key = "'queryPlanByTechId'+#techId")
	public List<PlanDto> queryPlanByTechId(int techId) {
		PlanDtos.clear();
		List<Plan> plans=planDao.queryPlanByTechId(techId);
		if(plans!=null) {
			for(Plan plan:plans) {
				PlanDtos.add(new PlanDto(plan));
			}
		}
		return PlanDtos;
	}

	@Override
	@Cacheable(value = "planZone" , key = "'queryPlanByLabId'+#labId")
	public List<PlanDto> queryPlanByLabId(int labId) {
		PlanDtos.clear();
		List<Plan> plans=planDao.queryPlanByLabId(labId);
		if(plans!=null) {
			for(Plan plan:plans) {
				PlanDtos.add(new PlanDto(plan));
			}
		}
		return PlanDtos;
	}



	@Override
	@Cacheable(value = "planZone" , key = "'queryAllPlan'")
	public List<PlanDto> queryAllPlan() {
		PlanDtos.clear();
		for(Plan plan:planDao.getAllPlan()) {
			PlanDtos.add(new PlanDto(plan));
		}
		return PlanDtos;
	}


	@Override
	@CacheEvict(cacheNames = "planZone",allEntries = true)
	public HandleResult updatePlan(PlanDto plandto) {
		plandto.setLabName(laboratoryDao.queryLaboratoryById(plandto.getLabId()).getLabName());

		exist=planDao.isExist(plandto.getPlanId());
		if(exist==1){
			if(planDao.updatePlan(new Plan(plandto)) ){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("更新计划成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("更新计划失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("更新失败，计划不存在!");
			return handle;
		}
	}

	@Override
	@Cacheable(value = "planZone" , key = "'PlanPaging'+#nowPage+','+#pageSize")
	public Page<PlanDto> PlanPaging(List<PlanDto> planDtos, int nowPage, int pageSize) {
		if(pageSize==0) {
			pageSize=3;
		}
		Planpage.setDataTotalCount(planDtos.size());
		Planpage.setPageSize(pageSize);
		Planpage.setPageTotalCount(planDtos.size()%pageSize==0?planDtos.size()/pageSize:(planDtos.size()/pageSize)+1);
		if(nowPage==Planpage.getPageTotalCount()) {      ///如果删除的是最后一条数据则当前页数等于页面总数减1
			if(nowPage!=0) {
				nowPage=Planpage.getPageTotalCount()-1;
			}
		}
		Planpage.setNowPage(nowPage+1);
		int errorfix=nowPage*pageSize;
		int wsize=planDtos.size();
		int fixTo=(nowPage*pageSize)+pageSize;
		if(nowPage<0) {
			errorfix=0;
			wsize=3;
			fixTo=3;
			Planpage.setNowPage(errorfix+1);
			Planpage.setPageSize(fixTo);
		}
		if(planDtos.size()>=pageSize) {   //判断页内数据能否构成满页的if
			if((nowPage+1)==Planpage.getPageTotalCount()) {              //判断下一页是否是最后一页
				planDtos= new ArrayList(planDtos.subList(errorfix,wsize)) ;
			}else {
				planDtos= new ArrayList(planDtos.subList(errorfix,fixTo)) ;
			}
		}//判断页内数据能否构成满页的if
		else {
			planDtos=new ArrayList(planDtos.subList(errorfix,planDtos.size())) ;
		}
		Planpage.setDatas(planDtos);
		if(planDtos.size()!=0) {
			Planpage.setCommonObject(planDtos.get(0));
		}
		return Planpage;

	}



}
