package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.MaintenanceDto;
import org.wzxy.breeze.model.po.Maintenance;
import org.wzxy.breeze.model.vo.Page;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceBase {

	protected List<Maintenance> MainList = new ArrayList<Maintenance>();

	protected Page<MaintenanceDto> Maintenpage=new Page<MaintenanceDto>();

	protected Maintenance Mainten=new Maintenance();

	protected List<MaintenanceDto> MainDtos=new ArrayList<MaintenanceDto>();

	protected LaboratoryDto LabDto=new LaboratoryDto();

	protected List<LaboratoryDto> labDtos=new ArrayList<LaboratoryDto>();

	////////////////
	public Maintenance createMaintenance(MaintenanceDto Maindto) {
		if(Maindto!=null) {
			Mainten.setDescription(Maindto.getDescription());
			Mainten.setEquId(Maindto.getEquId());
			Mainten.setEquName(Maindto.getEquName());
			Mainten.setEqutype(Maindto.getEqutype());
			Mainten.setLabId(Maindto.getLabId());
			Mainten.setMainId(Maindto.getMainId());
			Mainten.setPersonId(Maindto.getPersonId());
			Mainten.setPersonName(Maindto.getPersonName());
			Mainten.setReportTime(Maindto.getReportTime());
		}

		return Mainten;
		}

	public MaintenanceDto createMaintenanceDto(Maintenance MT) {
		MaintenanceDto MTdto=new MaintenanceDto();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(MT!=null) {
			MTdto.setDescription(MT.getDescription());
			MTdto.setEquId(MT.getEquId());
			MTdto.setEquName(MT.getEquName());
			MTdto.setEqutype(MT.getEqutype());
			MTdto.setLabId(MT.getLabId());
			MTdto.setMainId(MT.getMainId());
			MTdto.setPersonId(MT.getPersonId());
			MTdto.setPersonName(MT.getPersonName());
			MTdto.setReportTime(MT.getReportTime());
			MTdto.setReportDate(sdf.format(MT.getReportTime()));
		}

		return MTdto;

		}















	public List<Maintenance> getMainList() {
		return MainList;
	}

	public void setMainList(List<Maintenance> mainList) {
		MainList = mainList;
	}

	public Page<MaintenanceDto> getMaintenpage() {
		return Maintenpage;
	}

	public void setMaintenpage(Page<MaintenanceDto> maintenpage) {
		Maintenpage = maintenpage;
	}


	public Maintenance getMainten() {
		return Mainten;
	}

	public void setMainten(Maintenance mainten) {
		Mainten = mainten;
	}

	public List<MaintenanceDto> getMainDtos() {
		return MainDtos;
	}

	public void setMainDtos(List<MaintenanceDto> mainDtos) {
		MainDtos = mainDtos;
	}

	public LaboratoryDto getLabDto() {
		return LabDto;
	}

	public void setLabDto(LaboratoryDto labDto) {
		LabDto = labDto;
	}

	public List<LaboratoryDto> getLabDtos() {
		return labDtos;
	}

	public void setLabDtos(List<LaboratoryDto> labDtos) {
		this.labDtos = labDtos;
	}



}
