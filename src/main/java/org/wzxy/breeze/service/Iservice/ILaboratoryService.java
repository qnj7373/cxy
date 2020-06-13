package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

public interface ILaboratoryService {

	   public HandleResult addLaboratory(LaboratoryDto LabDto) ;

	   public  HandleResult deleteLaboratoryById(int labId);

	   public LaboratoryDto queryLaboratoryById(int labId) ;

	   public List<LaboratoryDto> queryLabBylabNameAndDepId(String labName,int depId) ;

	   public List<LaboratoryDto> queryLaboratorysBydepId(int depId) ;

	   public List<LaboratoryDto> queryLaboratorysByTechId(int techId) ;

	   public HandleResult updateLaboratory(LaboratoryDto LabDto) ;

	   public Page<LaboratoryDto> LabPaging(List<LaboratoryDto> labDtos, int nowPage, int pageSize) ;

	   public List<LaboratoryDto> getAllLaboratory();

}
