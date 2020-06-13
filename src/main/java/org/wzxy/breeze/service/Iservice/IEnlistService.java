package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

public interface IEnlistService {

	 public  HandleResult addEnlist(EnlistDto enlistDto) ;

	   public HandleResult deleteEnlistById(int enlistId);

	   public EnlistDto queryEnlistById(int enlistId) ;

	   public EnlistDto queryEnlistBypersonId(int personId) ;

	   public List<EnlistDto> queryEnlistByplanId(int planId);

	   public List<EnlistDto> queryEnlistByplanIdAndSta(int planId, String signSta);

	   public List<EnlistDto> queryEnlistBysignSta(String signSta);

	   public List<EnlistDto> queryAllEnlist() ;

	   public HandleResult updatePlan(EnlistDto enlistDto) ;

	   public Page<EnlistDto> EnlistPaging(List<EnlistDto> enlistDtos, int nowPage, int pageSize) ;


}
