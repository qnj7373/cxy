package org.wzxy.breeze.service.serviceImpl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.BaseStore.ClassBase;
import org.wzxy.breeze.mapper.classMapper;
import org.wzxy.breeze.model.dto.ClassDto;
import org.wzxy.breeze.model.po.Class;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.service.Iservice.IClassService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ClassServiceImpl extends ClassBase implements IClassService {

    @Resource
	private classMapper classdao;
	/////班级的业务逻辑  ========================

	@Override
	/*@Caching(evict = {
			@CacheEvict(value = "classZone" , key = "'queryAllClass'"),
			@CacheEvict(value = "classZone" , key = "'ClaPaging'+#nowPage+','+#pageSize")
	})*/
	@CacheEvict(cacheNames = "classZone",allEntries = true)
	public void addClass(ClassDto classto) {
		Class cla= new Class();
		classdao.addClass(cla);

	}

	@Override
	@CacheEvict(cacheNames = "classZone",allEntries = true)
	public void deleteClassById(int classId) {
		cla=classdao.queryClassById(classId);
		classdao.deleteClassById(cla);
	}

	@Override
	@Cacheable(value = "classZone" , key = "'queryClassById'+#classId")
	public ClassDto  queryClassById(int classId) {
		cla=classdao.queryClassById(classId);
		if(cla!=null) {
			return createClassDto(cla)	;
		}else {
			return null;
		}
	}


	@Override
	@Cacheable(value = "classZone" , key = "'queryAllClass'")
	public List<ClassDto> queryAllClass() {
		classDtos.clear();
		for(Class cla:classdao.getAllClass()) {
			classDtos.add(createClassDto(cla));
		}
		return classDtos;
	}

	@Override
	@Cacheable(value = "classZone" , key = "'queryClassBydepId'+#depId")
	public List<ClassDto> queryClassBydepId(int depId) {
		classDtos.clear();
		if(classdao.getClassBydepId(depId)==null) {
			return null;
		}
		for(Class cla:classdao.getClassBydepId(depId)) {
			classDtos.add(createClassDto(cla));
		}
		return classDtos;
	}


	@Override
	@CacheEvict(cacheNames = "classZone",allEntries = true)
	public void updateClass(ClassDto classdto) {
		classdao.updateClass(createClass(classdto));

	}



	@Override
	@Cacheable(value = "classZone" , key = "'ClaPaging'+#nowPage+','+#pageSize")
	public Page<ClassDto> ClaPaging(List<ClassDto> classDtos,int nowPage, int pageSize) {
		if(pageSize==0) {
			pageSize=3;
		}
		Cpage.setDataTotalCount(classDtos.size());
		Cpage.setPageSize(pageSize);
		Cpage.setPageTotalCount(classDtos.size()%pageSize==0?classDtos.size()/pageSize:(classDtos.size()/pageSize)+1);
		if(nowPage==Cpage.getPageTotalCount()) {      ///如果删除的是最后一条数据则当前页数等于页面总数减1
			if(nowPage!=0) {
				nowPage=Cpage.getPageTotalCount()-1;
			}
		}
		Cpage.setNowPage(nowPage);
		int errorfix=nowPage*pageSize;
		int wsize=classDtos.size();
		int fixTo=(nowPage*pageSize)+pageSize;
		if(nowPage<0) {
			errorfix=0;
			wsize=3;
			fixTo=3;
			Cpage.setNowPage(errorfix);
			Cpage.setPageSize(fixTo);
		}
		if(classDtos.size()>=pageSize) {   //判断页内数据能否构成满页的if
			if((nowPage+1)==Cpage.getPageTotalCount()) {              //判断下一页是否是最后一页
				classDtos=new ArrayList(classDtos.subList(errorfix,wsize));
			}else {
				classDtos=new ArrayList(classDtos.subList(errorfix,fixTo));
			}
		}//判断页内数据能否构成满页的if
		else {
			classDtos=new ArrayList(classDtos.subList(errorfix,classDtos.size())) ;
		}
		Cpage.setDatas(classDtos);
		return Cpage;

	}




	/////班级的业务逻辑


}
