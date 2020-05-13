package org.wzxy.breeze.BaseStore;

import org.wzxy.breeze.model.dto.ClassDto;
import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.po.Department;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.loginUser;

import java.util.ArrayList;
import java.util.List;

public class UserBase {
	protected loginUser luser=new loginUser();
	protected List<User> UserList =new ArrayList<>();
	protected Page<User> Userpage=new Page<User>();
	protected User user=new User();
	protected List<ClassDto> classDtos=new ArrayList<ClassDto>();	//////////////
	protected List<Department> DepList =new ArrayList<>();

	public User createUser(UserDto userdto) {
		User user=new User();
		if(user!=null) {
			user.setUid(userdto.getUid());
			user.setUnum(userdto.getUnum());
			user.setUpwd(userdto.getUpwd());
			user.setUtype(userdto.getUtype());
		}

		return user;
	}

	public UserDto createUserDto(User user) {
		UserDto userdto=new UserDto();
		if(userdto!=null) {
			userdto.setUid(user.getUid());
			userdto.setUnum(user.getUnum());
			userdto.setUpwd(user.getUpwd());
			userdto.setUtype(user.getUtype());
		}
		return userdto;
	}

////////////////


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<User> getUserList() {
		return UserList;
	}
	public void setUserList(List<User> userList) {
		UserList = userList;
	}
	public Page<User> getUserpage() {
		return Userpage;
	}
	public void setUserpage(Page<User> userpage) {
		Userpage = userpage;
	}


	public List<ClassDto> getClassDtos() {
		return classDtos;
	}

	public void setClassDtos(List<ClassDto> classDtos) {
		this.classDtos = classDtos;
	}

	public List<Department> getDepList() {
		return DepList;
	}

	public void setDepList(List<Department> depList) {
		DepList = depList;
	}

	public loginUser getLuser() {
		return luser;
	}

	public void setLuser(loginUser luser) {
		this.luser = luser;
	}



}
