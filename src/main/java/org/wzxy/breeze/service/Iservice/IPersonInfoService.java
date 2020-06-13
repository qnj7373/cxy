package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.dto.PersonInfoDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.vo.Page;

import java.io.File;
import java.util.List;

public interface IPersonInfoService {

	public  HandleResult addPersonInfo(PersonInfoDto pInfodto, File Pfile) ;

	public HandleResult deletePersonInfoById(int personId);

	public PersonInfoDto  queryPersonInfoById(int personId) ;

	public List<PersonInfoDto> queryPersonInfoBydepId(int depId);

	public List<PersonInfoDto>  queryPersonByplabIdAndSta(int labId, String hirSta);

	public List<PersonInfoDto> queryPersonInfoBysignSta(List<EnlistDto> EnDtos);

	public PersonInfoDto queryPersonInfoByStudentId(int StudentId);

	public List<PersonInfoDto> queryAllPersonInfo() ;

	public HandleResult updatePersonInfo(PersonInfoDto pInfodto, File Pfile) ;

	public Page<PersonInfoDto> PersonPaging(List<PersonInfoDto> personDtos, int nowPage, int pageSize) ;

}
