package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.Technician;

import java.util.ArrayList;
import java.util.List;

public class TechnicianBase {
	protected List<Technician> TechList =new ArrayList<>();
	protected Technician technician=new Technician();

	////////////////
	public Technician createDepartment(TechnicianDto Techdto) {

		if(Techdto!=null) {
			technician.setDepId(Techdto.getDepId());
			technician.setTechId(Techdto.getTechId());
			technician.setTechName(Techdto.getTechName());
			technician.setWorkSta(Techdto.getWorkSta());
		}

		return technician;
		}

	public TechnicianDto createDepartmentDto(Technician Tech) {
		TechnicianDto Techdto=new TechnicianDto();
		if(Tech!=null) {
			Techdto.setDepId(Tech.getDepId());
			Techdto.setTechId(Tech.getTechId());
			Techdto.setTechName(Tech.getTechName());
			Techdto.setWorkSta(Tech.getWorkSta());
		}

		return Techdto;
		}



	public List<Technician> getTechList() {
		return TechList;
	}

	public void setTechList(List<Technician> techList) {
		TechList = techList;
	}

	public Technician getTechnician() {
		return technician;
	}

	public void setTechnician(Technician technician) {
		this.technician = technician;
	}



}
