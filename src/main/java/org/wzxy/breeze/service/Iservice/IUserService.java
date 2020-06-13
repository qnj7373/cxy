package org.wzxy.breeze.service.Iservice;

import org.wzxy.breeze.model.dto.UserDto;
import org.wzxy.breeze.model.po.HandleResult;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.Page;
import org.wzxy.breeze.model.vo.loginUser;

import java.util.List;

public interface IUserService {

	public loginUser register(UserDto udto);

    public HandleResult deleteUserById(int uid);

   public UserDto queryUserById(int uid) ;

   public HandleResult updateUser(UserDto userdto) ;

   public Page<User> UserPaging(int nowPage, int pageSize) ;

    List<User> findUserByFactor(User user);

    User login(User user);

    loginUser getPowerSwitch(int num) ;

}
