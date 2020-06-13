package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.workRecordMapper;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.dto.WorkRecordDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.WorkRecord;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.service.Iservice.IWorkRecordService;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkRecordServiceImpl   implements IWorkRecordService {

	@Autowired
	private List<WorkRecord> workList ;
	@Autowired
	private Page<WorkRecordDto> Wrecordpage ;
	@Autowired
	private WorkRecord workRecord ;
	@Autowired
	private List<WorkRecordDto> workDtos ;
	@Autowired
	private PersonInfoDto PersonDto ;
	@Autowired
	private HandleResult handle ;
	private int exist=-1;
	@Resource
	private workRecordMapper workRecordDao ;

	@Override
	@CacheEvict(cacheNames = "workRecordZone",allEntries = true)
	public HandleResult addWorkRecord(WorkRecordDto WorkDto) {
		Timestamp t = new Timestamp(new Date().getTime());
		WorkDto.setSysTime(t);
		exist=workRecordDao.isExist(WorkDto.getRecordId());
		if(exist==0){
			if(workRecordDao.addWorkRecord(new WorkRecord(WorkDto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("新增工作记录成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("新增工作记录失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("新增失败，工作记录已存在!");
			return handle;
		}

	}

	@Override
	@CacheEvict(cacheNames = "workRecordZone",allEntries = true)
	public HandleResult deleteWorkRecordById(int recordId) {
		exist=workRecordDao.isExist(recordId);
		if(exist==1){
			workRecord=workRecordDao.queryWorkRecordById(recordId);
			if(workRecordDao.deleteWorkRecord(workRecord)){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("删除工作记录成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("删除工作记录失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("删除失败，工作记录不存在!");
			return handle;
		}

	}

	@Override
	@Cacheable(value = "workRecordZone" , key = "'queryWorkRecordById'+#recordId")
	public WorkRecordDto queryWorkRecordById(int recordId) {
		workRecord=workRecordDao.queryWorkRecordById(recordId);
		if(workRecord!=null) {
			return new WorkRecordDto(workRecord);
		}else {
			return null;
		}
	}



	@Override
	@Cacheable(value = "workRecordZone" , key = "'queryWorkRecordBylabId'+#labId")
	public List<WorkRecordDto> queryWorkRecordBylabId(int labId) {

		workDtos.clear();
		List<WorkRecord> wrlist=workRecordDao.queryWorkRecordBylabId(labId);
		if(wrlist!=null) {
			for(WorkRecord w:wrlist) {
				workDtos.add(new WorkRecordDto(w));
			}
		}
		return workDtos;
	}


	@Override
	@Cacheable(value = "workRecordZone" , key = "'queryWorkRecordByDate'+#date")
	public List<WorkRecordDto> queryWorkRecordByDate(List<WorkRecordDto> workDtos, String date) {
		List<WorkRecordDto> wrlist= new ArrayList<>();
		wrlist.clear();
		for(WorkRecordDto wdto:workDtos) {  /////////////////根据条件筛选
			if(date.equals(wdto.getRecordDate())){
				wrlist.add(wdto);
			}

		}    /////////////////根据条件筛选 end
		if(wrlist!=null) {
			return wrlist;
		}
		return null;

	}



	//查看记录前获取时间作为目录
	public List<WorkRecordDto> getRcordsdate(List<WorkRecordDto> WRdtos) {
		List<WorkRecordDto> FinalyDtos=new ArrayList<WorkRecordDto>();

		for(WorkRecordDto wdto:WRdtos) {  /////////////////日期目录查重
			if(FinalyDtos.size()==0) {
				FinalyDtos.add(wdto);
			}
			for(int i=0;i<FinalyDtos.size();i++) { /////////////////分两个箱子比一比
				String tempstr=FinalyDtos.get(i).getRecordDate();
				int tempNum=FinalyDtos.get(i).getLabId();
				if(tempstr.equals(wdto.getRecordDate())&&tempNum==(wdto.getLabId())) {

				}else {
					FinalyDtos.add(wdto);
				}
			}   /////////////////分两个箱子比一比

		}    /////////////////日期目录查重

		return FinalyDtos;
	}

	@Override
	@Cacheable(value = "workRecordZone" , key = "'getRcordsByLAndD'+#WRDate")
	public List<WorkRecordDto> getRcordsByLAndD(List<WorkRecordDto> WRdtos, String WRDate) {
		List<WorkRecordDto> FinalyDtos=new ArrayList<WorkRecordDto>();

		for(WorkRecordDto wdto:WRdtos) {  /////////////////按日期取出
			if(WRDate.equals(wdto.getRecordDate())) {
				FinalyDtos.add(wdto);
			}
		}    /////////////////按日期取出

		return FinalyDtos;
	}

	@Override
	@Cacheable(value = "workRecordZone" , key = "'queryWorkRecordBypersonId'+#personId")
	public List<WorkRecordDto> queryWorkRecordBypersonId(int personId) {
		workDtos.clear();
		List<WorkRecord> wrlist=workRecordDao.queryWorkRecordBypersonId(personId);
		if(wrlist!=null) {
			for(WorkRecord w:wrlist) {
				workDtos.add(new WorkRecordDto(w));
			}
		}
		return workDtos;
	}

	@Override
	@CacheEvict(cacheNames = "workRecordZone",allEntries = true)
	public HandleResult updateWorkRecord(WorkRecordDto WorkDto) {
		WorkRecord workRecord = workRecordDao.queryWorkRecordById(WorkDto.getRecordId());
		Date tempdate=workRecord.getDate();
		Timestamp sysTime=workRecord.getSysTime();
		WorkDto.setDate(tempdate);
		WorkDto.setSysTime(sysTime);

		exist=workRecordDao.isExist(WorkDto.getRecordId());
		if(exist==1){
			if(workRecordDao.updateWorkRecord(new WorkRecord(WorkDto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("更新维修单成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("更新维修单失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("更新失败，维修单不存在!");
			return handle;
		}
	}

	@Override
	@Cacheable(value = "workRecordZone" , key = "'getAllWorkRecord'")
	public List<WorkRecordDto> getAllWorkRecord() {
		workDtos.clear();
		for(WorkRecord w:workRecordDao.getAllWorkRecord()) {
			workDtos.add(new WorkRecordDto(w));
		}
		return workDtos;
	}


	@Override
	@Cacheable(value = "workRecordZone" , key = "'WorkPaging'+#nowPage+','+#pageSize")
	public Page<WorkRecordDto> WorkPaging(List<WorkRecordDto> Worklist, int nowPage, int pageSize) {

		if(pageSize==0) {
			pageSize=3;
		}
		Wrecordpage.setDataTotalCount(Worklist.size());
		Wrecordpage.setPageSize(pageSize);
		Wrecordpage.setPageTotalCount(Worklist.size()%pageSize==0?Worklist.size()/pageSize:(Worklist.size()/pageSize)+1);
		if(nowPage==Wrecordpage.getPageTotalCount()) {      ///如果删除的是最后一条数据则当前页数等于页面总数减1
			if(nowPage!=0) {
				nowPage=Wrecordpage.getPageTotalCount()-1;
			}
		}
		Wrecordpage.setNowPage(nowPage+1);
		int errorfix=nowPage*pageSize;
		int wsize=Worklist.size();
		int fixTo=(nowPage*pageSize)+pageSize;
		if(nowPage<0) {
			errorfix=0;
			wsize=3;
			fixTo=3;
			Wrecordpage.setNowPage(errorfix+1);
			Wrecordpage.setPageSize(fixTo);
		}
		if(Worklist.size()>=pageSize) {   //判断页内数据能否构成满页的if

			if((nowPage+1)==Wrecordpage.getPageTotalCount()) {
				//判断下一页是否是最后一页
				Worklist=new ArrayList(Worklist.subList(errorfix,wsize)) ;
			}else {
				Worklist=new ArrayList(Worklist.subList(errorfix,fixTo))  ;
			}
		}//判断页内数据能否构成满页的if
		else {
			Worklist=new ArrayList( Worklist.subList(errorfix,Worklist.size())) ;
		}
		Wrecordpage.setDatas(Worklist);
		if(Worklist.size()!=0) {
			Wrecordpage.setCommonObject(Worklist.get(0));
		}
		return Wrecordpage;
	}





}
