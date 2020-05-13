package org.wzxy.breeze.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.wzxy.breeze.BaseStore.UserBase;
import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.vo.loginUser;
import org.wzxy.breeze.service.Iservice.IClassService;
import org.wzxy.breeze.service.Iservice.IDepartmentService;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.service.serviceImpl.ClassServiceImpl;
import org.wzxy.breeze.service.serviceImpl.DepartmentServiceImpl;
import org.wzxy.breeze.service.serviceImpl.UserServiceImpl;

@RestController
public class UserController extends UserBase  {
	 private UserDto userDto;
	 private loginUser luser=new loginUser();
	@Autowired
     private IUserService userService;
	@Autowired
     private IDepartmentService DepSer;
	@Autowired
     private IClassService ClaSer;




	public String login() {
		try{
			luser.setUid(userDto.getUserid());
			luser.setUpwd(userDto.getUpwd());
			luser=userService.login(luser);
			return luser.getLoginResult();
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
	}

	public String toregister() {
		try{
			DepList = DepSer.getAllDep();
			classDtos=ClaSer.queryAllClass();
			return "success";
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
	}

	public String register() {
		try{
			luser=userService.register(userDto);
			return luser.getRegisterResult();
			}catch(Exception e) {
				e.printStackTrace();
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
					e.printStackTrace();
					return "error";
				}
		}


		public String queryUserById() {
			try{
				userDto=userService.queryUserById(userDto.getUid());
			return "success";
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
		}

		public String queryUsersByPage() { //////��ҳ////////
			try{
				Userpage=userService.UserPaging(userDto.getNowPage(), userDto.getPageSize());
				return "success";
			}catch(Exception e) {
				e.printStackTrace();
				return "error";
			}
		}


		public String updateUser() {

			 try{
				 userService.updateUser(userDto);
				 Userpage=userService.UserPaging(userDto.getNowPage(), userDto.getPageSize());
					return "success";
					}catch(Exception e) {
						e.printStackTrace();
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
