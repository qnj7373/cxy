package org.wzxy.breeze.service.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.enlistMapper;
import org.wzxy.breeze.mapper.planMapper;
import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.dto.PlanDto;
import org.wzxy.breeze.model.po.Enlist;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IEnlistService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class EnlistServiceImpl   implements IEnlistService {


	@Autowired
	private Page<EnlistDto> Enlistpage ;
	@Autowired
	private Enlist enlist ;
	@Autowired
	private List<EnlistDto> EnlistDtos ;
	@Autowired
	private List<Enlist> Enlists ;
	@Autowired
	private Page<PlanDto> Planpage ;
	@Autowired
	private List<PlanDto> PlanDtos ;
	@Autowired
	private PersonInfoDto PersonDto ;
	@Autowired
	private PlanDto planDto;
	private String signResult;

	@Resource
	private enlistMapper enlistDao;
	@Resource
	private planMapper planDao;
	@Autowired
	private HandleResult handle ;
	private int exist=-1;

	@Override
	@CacheEvict(cacheNames = "enlistZone",allEntries = true)
	public HandleResult addEnlist(EnlistDto enlistDto) {
		String result="fail";
		int sugnSum=enlistDao.countSign(enlistDto.getPlanId());
		int Number=planDao.queryPlanById(enlistDto.getPlanId()).getNumber();
		Enlist en=enlistDao.queryEnlistBypersonId(enlistDto.getPersonId());
		if(en!=null) {
			result="exist";
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("报名失败!您已经报名了其他岗位!");
			return handle;
		}else  if(sugnSum==Number) {                        //判断人数够了没、该学生是否应聘过岗位
			result="enough";
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("报名失败!该岗位人数已满!");
			return handle;
		}else {
			enlistDto.setSignSta("已报名");
			Date date = new Date();
			enlistDto.setAppDate(date);
			Enlist ed=new  Enlist();
			ed=new Enlist(enlistDto);
			exist=enlistDao.isExist(ed.getEnlistId());
			if(exist==0){
				if(enlistDao.addEnlist(ed)){
					handle.setStatus(ResponseCode.getOkcode());
					handle.setMessage("恭喜你，报名成功!");
				}else{
					handle.setStatus(ResponseCode.getFailcode());
					handle.setMessage("报名失败!");
				}
				return handle;
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("报名失败，报名信息已存在!");
				return handle;
			}

		}
	}

	@Override
	@CacheEvict(cacheNames = "enlistZone",allEntries = true)
	public HandleResult deleteEnlistById(int enlistId) {

		exist=enlistDao.isExist(enlistId);
		if(exist==1){
			enlist=enlistDao.queryEnlistById(enlistId);

			if(enlistDao.deleteEnlistById(enlist)){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("删除报名信息成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("删除报名信息失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("删除失败，报名信息不存在!");
			return handle;
		}

	}

	@Override
	@Cacheable(value = "enlistZone" , key = "'queryEnlistById'+#enlistId")
	public EnlistDto queryEnlistById(int enlistId) {

		return new EnlistDto(enlistDao.queryEnlistById(enlistId));
	}

	@Override
	@Cacheable(value = "enlistZone" , key = "'queryEnlistByplanId'+#planId")
	public List<EnlistDto> queryEnlistByplanId(int planId) {
		EnlistDtos.clear();
		List<Enlist> enlist=enlistDao.queryEnlistByplanId(planId);
		if(enlist!=null) {
			for(Enlist e:enlist) {
				EnlistDtos.add(new EnlistDto(e));
			}
		}
		return EnlistDtos;
	}

	@Override
	@Cacheable(value = "enlistZone" , key = "'queryEnlistByplanIdAndSta'+#planId+','+#signSta")
	public List<EnlistDto> queryEnlistByplanIdAndSta(int planId,String signSta) {
		EnlistDtos.clear();
		List<Enlist> enlist=enlistDao.queryEnlistByplanIdAndSta(planId,signSta);
		if(enlist!=null) {
			for(Enlist e:enlist) {
				EnlistDtos.add(new EnlistDto(e));
			}
		}
		return EnlistDtos;
	}

	@Override
	@Cacheable(value = "enlistZone" , key = "'queryEnlistBypersonId'+#personId")
	public EnlistDto queryEnlistBypersonId(int personId) {
		enlist=enlistDao.queryEnlistBypersonId(personId);
		if(enlist!=null) {
			return  new EnlistDto(enlist);
		}else {
			return null;
		}

	}

	@Override
	@Cacheable(value = "enlistZone" , key = "'queryAllEnlist'")
	public List<EnlistDto> queryAllEnlist() {
		EnlistDtos.clear();
		for(Enlist e:enlistDao.getAllEnlist()) {
			EnlistDtos.add(new EnlistDto(e));
		}
		return EnlistDtos;
	}

	@Override
	@Cacheable(value = "enlistZone" , key = "'queryEnlistBysignSta'+#signSta")
	public List<EnlistDto> queryEnlistBysignSta(String signSta) {
		EnlistDtos.clear();
		for(Enlist e:enlistDao.queryEnlistBysignSta(signSta)) {
			EnlistDtos.add(new EnlistDto(e));
		}
		return EnlistDtos;
	}


	@Override
	@CacheEvict(cacheNames = "enlistZone",allEntries = true)
	public HandleResult updatePlan(EnlistDto enlistDto) {

		exist=enlistDao.isExist(enlistDto.getEnlistId());
		if(exist==1){
			if(enlistDao.updateEnlist(new Enlist(enlistDto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("更新报名信息成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("更新报名信息失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("更新失败，报名信息不存在!");
			return handle;
		}

	}

	@Override
	@Cacheable(value = "enlistZone" , key = "'EnlistPaging'+#nowPage+','+#pageSize")
	public Page<EnlistDto> EnlistPaging(List<EnlistDto> enlistDtos, int nowPage, int pageSize) {
		if(pageSize==0) {
			pageSize=3;
		}
		Enlistpage.setDataTotalCount(enlistDtos.size());
		Enlistpage.setPageSize(pageSize);
		Enlistpage.setPageTotalCount(enlistDtos.size()%pageSize==0?enlistDtos.size()/pageSize:(enlistDtos.size()/pageSize)+1);
		if(nowPage==Enlistpage.getPageTotalCount()) {      ///如果删除的是最后一条数据则当前页数等于页面总数减1
			if(nowPage!=0) {
				nowPage=Enlistpage.getPageTotalCount()-1;
			}
		}
		Enlistpage.setNowPage(nowPage+1);
		int errorfix=nowPage*pageSize;
		int wsize=enlistDtos.size();
		int fixTo=(nowPage*pageSize)+pageSize;
		if(nowPage<0) {
			errorfix=0;
			wsize=3;
			fixTo=3;
			Enlistpage.setNowPage(errorfix+1);
			Enlistpage.setPageSize(fixTo);
		}

		if(enlistDtos.size()>=pageSize) {   //判断页内数据能否构成满页的if
			if((nowPage+1)==Enlistpage.getPageTotalCount()) {              //判断下一页是否是最后一页
				enlistDtos=  new ArrayList(enlistDtos.subList(errorfix,wsize)) ;
			}else {
				enlistDtos= new ArrayList(enlistDtos.subList(errorfix,fixTo))  ;
			}
		}//判断页内数据能否构成满页的if
		else {
			enlistDtos=new ArrayList(enlistDtos.subList(errorfix,enlistDtos.size()))  ;
		}
		Enlistpage.setDatas(enlistDtos);
		if(enlistDtos.size()!=0) {
			Enlistpage.setCommonObject(enlistDtos.get(0));
		}
		return Enlistpage;

	}



}
