package org.wzxy.breeze.service.Impl;

import org.wzxy.breeze.model.po.users;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
public interface IUsersService {

     List<users> findUserByFactor(users user);

     List<users> findAllUser();

     List<users> delete();

     users login(users user);

     int deleteUser(users user);

}
