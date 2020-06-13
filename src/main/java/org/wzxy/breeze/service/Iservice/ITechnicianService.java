package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.TechnicianDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.Technician;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

public interface ITechnicianService {

	 public  HandleResult addTech(TechnicianDto Techdto) ;

	   public HandleResult deleteTechById(int techId);

	   public TechnicianDto queryTechById(int techId) ;

	   public List<Technician> getNoLabTechByDepId(int depId);

	   public HandleResult updateTech(TechnicianDto Techdto) ;

	   public HandleResult  updateworkStaById(int techId, String workSta) ;

	   public Page<Technician> TechPaging(int nowPage, int pageSize) ;

	   public List<Technician> getAllTech();


}
