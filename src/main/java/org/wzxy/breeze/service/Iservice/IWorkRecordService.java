package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.WorkRecordDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.util.List;

public interface IWorkRecordService {

    public HandleResult addWorkRecord(WorkRecordDto WorkDto);

	public HandleResult deleteWorkRecordById(int recordId);

	public WorkRecordDto queryWorkRecordById(int recordId);

	public List<WorkRecordDto> queryWorkRecordByDate(List<WorkRecordDto> workDtos, String date) ;

	public List<WorkRecordDto> queryWorkRecordBylabId(int labId);

	public List<WorkRecordDto> getRcordsdate(List<WorkRecordDto> WRdtos);

	public List<WorkRecordDto> getRcordsByLAndD(List<WorkRecordDto> WRdtos, String WRDate);

	public List<WorkRecordDto> queryWorkRecordBypersonId(int personId);

	public HandleResult updateWorkRecord(WorkRecordDto WorkDto);

	public List<WorkRecordDto> getAllWorkRecord();

	public Page<WorkRecordDto> WorkPaging(List<WorkRecordDto> Worklist, int nowPage, int pageSize) ;

}
