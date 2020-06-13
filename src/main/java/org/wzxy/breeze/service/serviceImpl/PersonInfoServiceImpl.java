package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.personInfoMapper;
import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.dto.LaboratoryDto;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.PersonInfo;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.loginUser;
import org.wzxy.breeze.service.Iservice.IPersonInfoService;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonInfoServiceImpl   implements IPersonInfoService {

	@Autowired
	private loginUser luser ;
	@Autowired
	private List<PersonInfo> PersonList  ;
	@Autowired
	private Page<PersonInfo> Personpage ;
	private int planId;
	private int techId;
	private int tempLID;
	@Autowired
	private PlanDto planDto;
	@Autowired
	private PersonInfo Person ;
	@Autowired
	private PersonInfoDto PersonDto ;
	@Autowired
	private List<PersonInfoDto> PerDtos ;
	@Autowired
	private Page<PersonInfoDto> Perpage ;
	@Autowired
	private List<EnlistDto> EnlistDtos ;
	@Autowired
	private List<PlanDto> PlanDtos ;
	@Autowired
	private LaboratoryDto LabDto ;
	@Resource
	private personInfoMapper Persondao;
	@Autowired
	private HandleResult handle ;
	private int exist=-1;
	@Override
	@CacheEvict(cacheNames = "personZone",allEntries = true)
	public HandleResult addPersonInfo(PersonInfoDto pInfodto, File Pfile) {

		try {
			exist=Persondao.isExist(pInfodto.getPersonId());
			if(exist==0){
				Person=Persondao.queryPersonInfoById(pInfodto.getPersonId());
				if(Persondao.addPersonInfo(new PersonInfo(pInfodto,Pfile))){
					handle.setStatus(ResponseCode.getOkcode());
					handle.setMessage("注册成功!");
				}else{
					handle.setStatus(ResponseCode.getFailcode());
					handle.setMessage("注册失败!");
				}
				return handle;
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("注册失败，个人档案已存在!");
				return handle;
			}

		} catch (IOException e) {
			e.printStackTrace();
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("程序出错!");
			return handle;
		}

	}

	@Override
	@CacheEvict(cacheNames = "personZone",allEntries = true)
	public HandleResult deletePersonInfoById(int personId) {
		exist=Persondao.isExist(personId);
		if(exist==1){
			Person=Persondao.queryPersonInfoById(personId);
			if(Persondao.deletePersonInfoById(Person)){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("删除个人档案成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("删除个人档案失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("删除失败，个人档案不存在!");
			return handle;
		}
	}

	@Override
	@Cacheable(value = "personZone" , key = "'queryPersonInfoById'+#personId")
	public PersonInfoDto queryPersonInfoById(int personId) {
		Person=Persondao.queryPersonInfoById(personId);
		if(Person!=null) {
			return new PersonInfoDto(Person);
		}else {
			return null;
		}

	}

	@Override
	@Cacheable(value = "personZone" , key = "'queryPersonInfoBydepId'+#depId")
	public List<PersonInfoDto> queryPersonInfoBydepId(int depId) {
		PerDtos.clear();
		PersonList=Persondao.queryPersonInfosByDepId(depId);
		if(PersonList!=null) {
			for(PersonInfo P:PersonList) {
				PerDtos.add(new PersonInfoDto(P));
			}
		}
		return PerDtos;
	}

	@Override
	@Cacheable(value = "personZone" , key = "'queryAllPersonInfo'")
	public List<PersonInfoDto> queryAllPersonInfo() {
		PerDtos.clear();
		for(PersonInfo P:Persondao.getAllPersonInfo()) {
			PerDtos.add(new PersonInfoDto(P));
		}
		return PerDtos;
	}

	@Override
	@Cacheable(value = "personZone" , key = "'queryPersonInfoByStudentId'+#StudentId")
	public PersonInfoDto queryPersonInfoByStudentId(int StudentId) {
		return new PersonInfoDto(Persondao.queryPersonInfoByStudentId(StudentId));
	}

	@Override
	@CacheEvict(cacheNames = "personZone",allEntries = true)
	public HandleResult updatePersonInfo(PersonInfoDto pInfodto,File Pfile) {
		try {
			if(Pfile==null) {
				exist=Persondao.isExist(pInfodto.getPersonId());
				if(exist==1){
					if(Persondao.updatePersonInfo(new PersonInfo(pInfodto,null))){
						handle.setStatus(ResponseCode.getOkcode());
						handle.setMessage("录入助理档案成功!");
					}else{
						handle.setStatus(ResponseCode.getFailcode());
						handle.setMessage("录入助理档案失败!");
					}
					return handle;
				}else{
					handle.setStatus(ResponseCode.getFailcode());
					handle.setMessage("录入助理档案失败，助理档案不存在!");
					return handle;
				}
			}else {
				exist=Persondao.isExist(pInfodto.getPersonId());
				if(exist==1){
					if(Persondao.updatePersonInfo(new PersonInfo(pInfodto,Pfile))){
						handle.setStatus(ResponseCode.getOkcode());
						handle.setMessage("录入助理档案成功!");
					}else{
						handle.setStatus(ResponseCode.getFailcode());
						handle.setMessage("录入助理档案失败!");
					}
					return handle;
				}else{
					handle.setStatus(ResponseCode.getFailcode());
					handle.setMessage("录入助理档案失败，助理档案不存在!");
					return handle;
				}

			}

		} catch (IOException e) {
			e.printStackTrace();
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("程序出错!");
			return handle;
		}
	}

	@Override
	public List<PersonInfoDto> queryPersonInfoBysignSta(List<EnlistDto> EnDtos) {
		PerDtos.clear();
		for(EnlistDto ed:EnDtos) {
			PersonInfoDto pdt=new PersonInfoDto();
			pdt=queryPersonInfoById(ed.getPersonId());
			PerDtos.add(pdt);
		}
		return PerDtos;
	}


	@Override
	@Cacheable(value = "personZone" , key = "'PersonPaging'+#nowPage+','+#pageSize")
	public Page<PersonInfoDto> PersonPaging(List<PersonInfoDto> personDtos, int nowPage, int pageSize) {

		if(pageSize==0) {
			pageSize=3;
		}
		Perpage.setDataTotalCount(personDtos.size());
		Perpage.setPageSize(pageSize);
		Perpage.setPageTotalCount(personDtos.size()%pageSize==0?personDtos.size()/pageSize:(personDtos.size()/pageSize)+1);
		if(nowPage==Perpage.getPageTotalCount()) {      ///如果删除的是最后一条数据则当前页数等于页面总数减1
			if(nowPage!=0) {
				nowPage=Perpage.getPageTotalCount()-1;
			}
		}
		Perpage.setNowPage(nowPage+1);
		int errorfix=nowPage*pageSize;
		int wsize=personDtos.size();
		int fixTo=(nowPage*pageSize)+pageSize;
		if(nowPage<0) {
			errorfix=0;
			wsize=3;
			fixTo=3;
			Perpage.setNowPage(errorfix+1);
			Perpage.setPageSize(fixTo);
		}

		if(personDtos.size()>=pageSize) {   //判断页内数据能否构成满页的if
			if((nowPage+1)==Perpage.getPageTotalCount()) {              //判断下一页是否是最后一页
				personDtos=new ArrayList(personDtos.subList(errorfix,wsize)) ;
			}else {
				personDtos=new ArrayList(personDtos.subList(errorfix,fixTo)) ;
			}
		}//判断页内数据能否构成满页的if
		else {
			personDtos= new ArrayList(personDtos.subList(errorfix,personDtos.size())) ;
		}
		if(personDtos.size()!=0) {
			Perpage.setCommonObject(personDtos.get(0));
		}
		Perpage.setDatas(personDtos);
		return Perpage;
	}


	@Override
	@Cacheable(value = "personZone" , key = "'queryPersonByplabIdAndSta'+#labId+','+#hirSta")
	public List<PersonInfoDto> queryPersonByplabIdAndSta(int labId, String hirSta) {
		PerDtos.clear();
		PersonList=Persondao.queryPersonByplabIdAndSta(labId, hirSta);
		if(PersonList!=null) {
			for(PersonInfo P:PersonList) {
				PerDtos.add(new PersonInfoDto(P));
			}
		}
		return PerDtos;
	}


}
