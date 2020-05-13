package org.wzxy.breeze.service;

import org.wzxy.breeze.model.po.User;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
public interface IUserService {

     List<User> findUserByFactor(User user);

     List<User> findAllUser();

     List<User> delete();

     User login(User user);

     int deleteUser(User user);
}
