package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.DepartmentDto;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.Laboratory;
import org.wzxy.breeze.model.po.Technician;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

public class LaboratoryBase {

	protected List<Laboratory> labList =new ArrayList<>();
	protected Page<LaboratoryDto> labpage=new Page<LaboratoryDto>();
	protected Laboratory Lab=new Laboratory();
	protected LaboratoryDto LabDto=new LaboratoryDto();
	protected List<LaboratoryDto> labDtos=new ArrayList<LaboratoryDto>();
	protected List<Laboratory> lablist=new ArrayList<Laboratory>();
	protected List<Technician> Techlist=new ArrayList<Technician>();
	protected DepartmentDto DepDto;
	protected TechnicianDto TechDto;
		////////////////////////===============================�����
		//�����������������ԵĿ�ݺ���


		public Laboratory createLaboratory(LaboratoryDto LabDto) {
			if(LabDto!=null) {
				Lab.setLabId(LabDto.getLabId());
				Lab.setLabName(LabDto.getLabName());
				Lab.setDepId(LabDto.getDepId());
				Lab.setTechId(LabDto.getTechId());
			}
			return Lab;
		}

		////////////�������ݴ������

		public LaboratoryDto createLaboratoryDto(Laboratory lab) {
			LaboratoryDto labdto=new LaboratoryDto();

			if(lab!=null) {
				labdto.setLabId(lab.getLabId());
				labdto.setLabName(lab.getLabName());
				labdto.setDepId(lab.getDepId());
				labdto.setTechId(lab.getTechId());
			}

			return labdto;
		}



	public List<Laboratory> getLabList() {
		return labList;
	}
	public void setLabList(List<Laboratory> labList) {
		this.labList = labList;
	}
	public Page<LaboratoryDto> getLabpage() {
		return labpage;
	}
	public void setLabpage(Page<LaboratoryDto> labpage) {
		this.labpage = labpage;
	}
	public Laboratory getLab() {
		return Lab;
	}
	public void setLab(Laboratory lab) {
		Lab = lab;
	}
	public List<LaboratoryDto> getLabDtos() {
		return labDtos;
	}
	public void setLabDtos(List<LaboratoryDto> labDtos) {
		this.labDtos = labDtos;
	}

	public List<Laboratory> getLablist() {
		return lablist;
	}

	public void setLablist(List<Laboratory> lablist) {
		this.lablist = lablist;
	}

	public LaboratoryDto getLabDto() {
		return LabDto;
	}

	public void setLabDto(LaboratoryDto labDto) {
		LabDto = labDto;
	}

	public DepartmentDto getDepDto() {
		return DepDto;
	}

	public void setDepDto(DepartmentDto depDto) {
		DepDto = depDto;
	}

	public TechnicianDto getTechDto() {
		return TechDto;
	}

	public void setTechDto(TechnicianDto techDto) {
		TechDto = techDto;
	}

	public List<Technician> getTechlist() {
		return Techlist;
	}

	public void setTechlist(List<Technician> techlist) {
		Techlist = techlist;
	}


}
