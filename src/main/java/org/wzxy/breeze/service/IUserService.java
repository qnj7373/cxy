package org.wzxy.breeze.service;

import org.wzxy.breeze.model.po.users;

import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
public interface IUserService {

     List<users> findUserByFactor(users user);

     List<users> findAllUser();

     List<users> delect();

     users login(users user);

     int deleteUser(users user);
}
