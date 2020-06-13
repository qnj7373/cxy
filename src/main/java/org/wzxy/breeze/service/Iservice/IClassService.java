package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.ClassDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

public interface IClassService {

	 ///////////�༶
	   public HandleResult addClass(ClassDto classdto) ;

	   public  HandleResult deleteClassById(int classId);

	   public ClassDto  queryClassById(int classId) ;

	   public List<ClassDto> queryAllClass() ;

	   public List<ClassDto> queryClassBydepId(int depId);

	   public HandleResult updateClass(ClassDto classdto) ;

	   public Page<ClassDto> ClaPaging(List<ClassDto> classDtos, int nowPage, int pageSize) ;

}
