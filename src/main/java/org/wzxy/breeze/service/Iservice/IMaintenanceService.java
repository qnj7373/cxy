package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.MaintenanceDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.io.IOException;
import java.util.List;

public interface IMaintenanceService {

	public String export(List<MaintenanceDto> mainlist) throws IOException;


	  public HandleResult addMainten(MaintenanceDto maindto) ;

	   public  HandleResult deleteMaintenById(int mainId);

	   public MaintenanceDto  queryMaintenById(int mainId) ;

	   public List<MaintenanceDto> queryAllMainten() ;

	   public List<MaintenanceDto> queryMaintenByLabId(int labId);

	   public List<MaintenanceDto> queryMaintenByPerId(int personId);

	   public HandleResult updateMainten(MaintenanceDto maindto) ;

	   public Page<MaintenanceDto> MainPaging(List<MaintenanceDto> mainDtos, int nowPage, int pageSize) ;







}
