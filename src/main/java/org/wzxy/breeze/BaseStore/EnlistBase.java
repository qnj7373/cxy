package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.po.Enlist;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

public class EnlistBase {


	protected Page<EnlistDto> Enlistpage=new Page<EnlistDto>();
	protected Enlist enlist=new Enlist();
	protected List<EnlistDto> EnlistDtos=new ArrayList<EnlistDto>();

	protected List<Enlist> Enlists=new ArrayList<Enlist>();
	protected Page<PlanDto> Planpage=new Page<PlanDto>();
	protected List<PlanDto> PlanDtos=new ArrayList<PlanDto>();
	protected PersonInfoDto PersonDto=new PersonInfoDto();
	protected PlanDto planDto;
	protected String signResult;

	public Enlist createEnlist(EnlistDto  enlistDto) {
		Enlist enl=new Enlist();
		if(enlistDto!=null) {
				 enl.setAppDate(enlistDto.getAppDate());
				 enl.setEnlistId(enlistDto.getEnlistId());
				 enl.setPersonId(enlistDto.getPersonId());
				 enl.setPlanId(enlistDto.getPlanId());
				 enl.setPostName(enlistDto.getPostName());
				 enl.setSignSta(enlistDto.getSignSta());
				 enl.setSkill(enlistDto.getSkill());
				 enl.setPersonName(enlistDto.getPersonName());
		}

		return enl;
	}

	////////////�������ݴ������

	public EnlistDto createEnlistDto(Enlist en) {
		EnlistDto enDto=new EnlistDto ();
		if(en!=null) {
			enDto.setAppDate(en.getAppDate());
			enDto.setEnlistId(en.getEnlistId());
			enDto.setPersonId(en.getPersonId());
			enDto.setPlanId(en.getPlanId());
			enDto.setPostName(en.getPostName());
			enDto.setSignSta(en.getSignSta());
			enDto.setSkill(en.getSkill());
			enDto.setPersonName(en.getPersonName());
		}
		return enDto;
	}

	public Page<EnlistDto> getEnlistpage() {
		return Enlistpage;
	}

	public void setEnlistpage(Page<EnlistDto> enlistpage) {
		Enlistpage = enlistpage;
	}

	public Enlist getEnlist() {
		return enlist;
	}

	public void setEnlist(Enlist enlist) {
		this.enlist = enlist;
	}

	public List<EnlistDto> getEnlistDtos() {
		return EnlistDtos;
	}

	public void setEnlistDtos(List<EnlistDto> enlistDtos) {
		EnlistDtos = enlistDtos;
	}

	public List<Enlist> getEnlists() {
		return Enlists;
	}

	public void setEnlists(List<Enlist> enlists) {
		Enlists = enlists;
	}

	public Page<PlanDto> getPlanpage() {
		return Planpage;
	}

	public void setPlanpage(Page<PlanDto> planpage) {
		Planpage = planpage;
	}

	public List<PlanDto> getPlanDtos() {
		return PlanDtos;
	}

	public void setPlanDtos(List<PlanDto> planDtos) {
		PlanDtos = planDtos;
	}

	public PlanDto getPlanDto() {
		return planDto;
	}

	public void setPlanDto(PlanDto planDto) {
		this.planDto = planDto;
	}

	public String getSignResult() {
		return signResult;
	}

	public void setSignResult(String signResult) {
		this.signResult = signResult;
	}

	public PersonInfoDto getPersonDto() {
		return PersonDto;
	}

	public void setPersonDto(PersonInfoDto personDto) {
		PersonDto = personDto;
	}


}
