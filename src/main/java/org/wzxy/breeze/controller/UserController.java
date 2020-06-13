package org.wzxy.breeze.controller;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.mapper.personInfoMapper;
import org.wzxy.breeze.mapper.rolesMapper;
import org.wzxy.breeze.mapper.studentMapper;
import org.wzxy.breeze.mapper.userMapper;
import org.wzxy.breeze.model.dto.ClassDto;
import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.po.Department;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.model.vo.loginUser;
import org.wzxy.breeze.service.Iservice.IClassService;
import org.wzxy.breeze.service.Iservice.IDepartmentService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.serviceImpl.ClassServiceImpl;
import org.wzxy.breeze.service.serviceImpl.DepartmentServiceImpl;
import org.wzxy.breeze.service.serviceImpl.UserServiceImpl;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController   {
	@Autowired
	 private UserDto userDto;
	@Autowired
     private IUserService userService;
	@Autowired
     private IDepartmentService DepSer;
	@Autowired
     private IClassService ClaSer;
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
	private Logger logger;
	@Autowired
	 private  ResponseResult Result  ;



@GetMapping("/toregister")
	public ResponseResult toregister() {
		try{
			DepList = DepSer.getAllDep();
			classDtos=ClaSer.queryAllClass();
			Result.setData(DepList);
			Result.setDataBackUp(classDtos);
			Result.setStatus(ResponseCode.getOkcode());
			Result.setMessage("获取注册所需信息成功！");
			return Result;
			}catch(Exception e) {
				logger.error(e.getMessage());
			Result.setStatus(ResponseCode.getErrorcode());
			Result.setMessage("服务器出错了！请联系管理员处理~");
			return Result;
			}
	}

	@PostMapping("/register")
	public String register(UserDto uDto) {
		try{
			luser=userService.register(uDto);
			return luser.getRegisterResult();
			}catch(Exception e) {
				logger.error(e.getMessage());
				return "error";
			}
	}

////////////////////
//////////����user��action����

		public String deleteUserById() {

			try{
				userService.deleteUserById(userDto.getUid());
				Userpage=userService.UserPaging(userDto.getNowPage(), userDto.getPageSize());
				return "success";
				}catch(Exception e) {
					logger.error(e.getMessage());
					return "error";
				}
		}


		public String queryUserById() {
			try{
				userDto=userService.queryUserById(userDto.getUid());
			return "success";
			}catch(Exception e) {
				logger.error(e.getMessage());
				return "error";
			}
		}

		public String queryUsersByPage() { //////��ҳ////////
			try{
				Userpage=userService.UserPaging(userDto.getNowPage(), userDto.getPageSize());
				return "success";
			}catch(Exception e) {
				logger.error(e.getMessage());
				return "error";
			}
		}


		public String updateUser() {

			 try{
				 userService.updateUser(userDto);
				 Userpage=userService.UserPaging(userDto.getNowPage(), userDto.getPageSize());
					return "success";
					}catch(Exception e) {
						logger.error(e.getMessage());
						return "error";
					}
			 }
//////////����user��action����



	///////////////////////////

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public loginUser getLuser() {
		return luser;
	}

	public void setLuser(loginUser luser) {
		this.luser = luser;
	}


	public UserDto getUserDto() {
		return userDto;
	}

	public void setUserDto(UserDto userDto) {
		this.userDto = userDto;
	}


	public void setDepSer(DepartmentServiceImpl depSer) {
		DepSer = depSer;
	}


	public void setClaSer(ClassServiceImpl claSer) {
		ClaSer = claSer;
	}

}
