package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.Plan;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

public class PlanBase {

	protected Page<PlanDto> Planpage=new Page<PlanDto>();
	protected int uNum;
	protected Plan plan=new Plan();
	protected List<PlanDto> PlanDtos=new ArrayList<PlanDto>();
	protected List<Plan> Planlist=new ArrayList<Plan>();

	protected DepartmentDto depmentDto=new DepartmentDto();
	protected TechnicianDto technicianDto=new TechnicianDto();
	protected List<LaboratoryDto> labDtos=new ArrayList<LaboratoryDto>();

	////////////////////////===============================�����
	//�����������������ԵĿ�ݺ���


	public Plan createLaboratory(PlanDto planDto) {

		if(planDto!=null) {
			plan.setDepId(planDto.getDepId());
			plan.setDepName(planDto.getDepName());
			plan.setNumber(planDto.getNumber());
			plan.setPlanId(planDto.getPlanId());
			plan.setPlanName(planDto.getPlanName());
			plan.setRequirement(planDto.getRequirement());
			plan.setTechId(planDto.getTechId());
			plan.setTechName(planDto.getTechName());
			plan.setPlanSta(planDto.getPlanSta());
			plan.setLabId(planDto.getLabId());
			plan.setLabName(planDto.getLabName());
		}

		return plan;
	}

	////////////�������ݴ������

	public PlanDto createLaboratoryDto(Plan plan) {
		PlanDto planDto=new PlanDto ();
		if(plan!=null) {
			planDto.setDepId(plan.getDepId());
			planDto.setDepName(plan.getDepName());
			planDto.setNumber(plan.getNumber());
			planDto.setPlanId(plan.getPlanId());
			planDto.setPlanName(plan.getPlanName());
			planDto.setRequirement(plan.getRequirement());
			planDto.setTechId(plan.getTechId());
			planDto.setTechName(plan.getTechName());
			planDto.setLabId(plan.getLabId());
			planDto.setLabName(plan.getLabName());
			planDto.setPlanSta(plan.getPlanSta());
		}

		return planDto;
	}




	public Page<PlanDto> getPlanpage() {
		return Planpage;
	}

	public void setPlanpage(Page<PlanDto> planpage) {
		Planpage = planpage;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}


	public List<PlanDto> getPlanDtos() {
		return PlanDtos;
	}

	public void setPlanDtos(List<PlanDto> planDtos) {
		PlanDtos = planDtos;
	}

	public List<Plan> getPlanlist() {
		return Planlist;
	}

	public void setPlanlist(List<Plan> planlist) {
		Planlist = planlist;
	}

	public DepartmentDto getDepmentDto() {
		return depmentDto;
	}

	public void setDepmentDto(DepartmentDto depmentDto) {
		this.depmentDto = depmentDto;
	}

	public TechnicianDto getTechnicianDto() {
		return technicianDto;
	}

	public void setTechnicianDto(TechnicianDto technicianDto) {
		this.technicianDto = technicianDto;
	}

	public int getuNum() {
		return uNum;
	}

	public void setuNum(int uNum) {
		this.uNum = uNum;
	}

	public List<LaboratoryDto> getLabDtos() {
		return labDtos;
	}

	public void setLabDtos(List<LaboratoryDto> labDtos) {
		this.labDtos = labDtos;
	}



}
