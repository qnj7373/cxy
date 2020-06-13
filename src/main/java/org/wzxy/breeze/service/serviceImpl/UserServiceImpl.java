package org.wzxy.breeze.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.personInfoMapper;
import org.wzxy.breeze.mapper.rolesMapper;
import org.wzxy.breeze.mapper.studentMapper;
import org.wzxy.breeze.mapper.userMapper;
import org.wzxy.breeze.model.dto.ClassDto;
import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.po.*;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.loginUser;
import org.wzxy.breeze.service.Iservice.IUserService;

import javax.annotation.Resource;
import java.util.*;

@Service
public class UserServiceImpl   implements IUserService{
	@Autowired
	private loginUser luser ;
	@Autowired
	private List<User> UserList  ;
	@Autowired
	private Page<User> Userpage ;
	@Autowired
	private User user ;
	@Autowired
	private List<ClassDto> classDtos ;	//////////////
	@Autowired
	private List<Department> DepList ;
	@Resource
	private userMapper userDao;
	@Resource
	private rolesMapper rolesDao;
	@Resource
	private studentMapper studentDao;
	@Resource
	private personInfoMapper Persondao;
	@Autowired
	private List<User> userList ;
	@Autowired
	private HandleResult handle ;
	private int exist=-1;

	@Override
	public User login(User user) {
		userList.clear();
		Set<menu> menuSet = new HashSet<>();
		Set<role> roleSet = new HashSet<>();
		Map<String,User> map = new HashMap<>();
		userList=userDao.findUserByFactor(user);
		if(userList!=null){
			for (role r:
					userList.get(0).getRoles()) {
				roleSet.add(r);
				for (menu m:
						r.getMenus()) {
					menuSet.add(m);
				}
			}
			map.put(String.valueOf(userList.get(0).getUnum()), userList.get(0));
			return userList.get(0);
		}
		return map.get(userList.get(0).getUnum());
	}

	@Cacheable(value = "userZone" , key = "'getPowerSwitch'+#num")
	public loginUser getPowerSwitch(int num) {
		loginUser luser = new loginUser();
		PersonInfo Info = Persondao.queryPersonInfoByStudentId(num);
		String sta = Info.getHirSta();
		if (sta.equals("实验室助理")) {
			luser.setPowerSwitch(true);
		} else {
			luser.setPowerSwitch(false);
		}
		return luser;
	}


	@Override
	public List<User> findUserByFactor(User user) {
		userList=userDao.findUserByFactor(user);
		return userList;
	}



	//////////////////////
	@Override
	public loginUser register(UserDto udto) {
		//根据单选框的值确定用户类型，查找对应的表
		//校验相对应的表内是否有输入的编号存在，没有相对应的编号则返回提示只有本校人员可使用
		if(udto.getUtype().equals("3")) {
			Student student = studentDao.queryStudentById(udto.getUnum());
			if(student==null) {
				luser.setRegisterResult("noThisOne");
				return luser;
			}
		}
		//如果有编号则根据编号及用户类型开始校验是否已经注册过了
		user=userDao.queryUserByUnumAndUtype(udto.getUnum(), udto.getUtype());
		if(user==null) {
			//如果没有注册则向user中写入数据
			userDao.addUser(new User(udto));
			user=userDao.queryUserByUnumAndUtype(udto.getUnum(), udto.getUtype());
			luser.setUid(String.valueOf(user.getUid()));
			//////给新助理角色赋值
			rolesDao.addRoleRelation(user.getUid(), 7);
			luser.setRegisterResult("success");
			return luser;
		}else {
			luser.setRegisterResult("exist");
			return luser;
		}

	}
//////////////////////============+++++++++=============//////////////////////////
/////系统用户的业务逻辑

	@Override
	@CacheEvict(cacheNames = "userZone",allEntries = true)
	public HandleResult deleteUserById(int uid) {
		user=userDao.queryUserById(uid);
		exist=userDao.isExist(uid);
		if(exist==1){
			if(userDao.deleteUserById(user)){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("删除用户成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("删除用户失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("删除失败，用户不存在!");
			return handle;
		}


	}

	@Override
	@Cacheable(value = "userZone" , key = "'queryUserById'+#uid")
	public UserDto queryUserById(int uid) {
		user=userDao.queryUserById(uid);
		if(user!=null) {
			return new UserDto(user);
		}else {
			return null;
		}

	}

	@Override
	@CacheEvict(cacheNames = "userZone",allEntries = true)
	public HandleResult updateUser(UserDto userdto)
	{
		exist=userDao.isExist(userdto.getUid());
		if(exist==1){
			if(userDao.updateUser(new User(userdto))){
				handle.setStatus(ResponseCode.getOkcode());
				handle.setMessage("更新用户成功!");
			}else{
				handle.setStatus(ResponseCode.getFailcode());
				handle.setMessage("更新用户失败!");
			}
			return handle;
		}else{
			handle.setStatus(ResponseCode.getFailcode());
			handle.setMessage("更新失败，用户不存在!");
			return handle;
		}


	}

	@Override
	@Cacheable(value = "userZone" , key = "'UserPaging'+#nowPage+','+#pageSize")
	public Page<User> UserPaging(int nowPage, int pageSize) {
		Userpage.setNowPage(nowPage+1);
		Userpage.setDataTotalCount(userDao.getTotalCount());
		Userpage.setPageSize(pageSize);
		Userpage.setPageTotalCount(Userpage.getPageTotalCount());
		Userpage.setDatas((userDao.getUsersByPage(nowPage, pageSize)));
		return Userpage;
	}



	/////系统用户的业务逻辑末

}
