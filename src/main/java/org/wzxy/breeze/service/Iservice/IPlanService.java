package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

public interface IPlanService {

	  public HandleResult addPlan(PlanDto plandto) ;

	   public  HandleResult deletePlanById(int planId);

	   public PlanDto  queryPlanById(int planId) ;


	   public List<PlanDto> queryPlanByLabId(int labId);

	   public List<PlanDto> queryPlanBydepId(int depId);

	   public List<PlanDto> queryPlanByTechId(int techId);

	   public List<PlanDto> queryAllPlan() ;

	   public List<PlanDto> queryPlanByPlanSta(String planSta);

	   public HandleResult updatePlan(PlanDto plandto) ;

	   public Page<PlanDto> PlanPaging(List<PlanDto> planDtos, int nowPage, int pageSize) ;

}
