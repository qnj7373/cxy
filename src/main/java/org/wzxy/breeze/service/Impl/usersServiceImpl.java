package org.wzxy.breeze.service.Impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.usersMapper;
import org.wzxy.breeze.model.po.menu;
import org.wzxy.breeze.model.po.role;
import org.wzxy.breeze.model.po.users;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 覃能健
 * @create 2020-03
 */
@Service
public class usersServiceImpl implements IUsersService {

    @Resource
    private usersMapper usersmapper;


    List<users> userList= new ArrayList<users>();

    @Override
    public users login(users user) {
        userList.clear();
        Set<menu> menuSet = new HashSet<>();
        Set<role> roleSet = new HashSet<>();
        Map<String,users> map = new HashMap<>();
        userList=usersmapper.findUserByFactor(user);
        if(userList!=null){
            for (role r:
                    userList.get(0).getRoles()) {
                roleSet.add(r);
                for (menu m:
                        r.getMenus()) {
                    menuSet.add(m);
                }
            }
            map.put(userList.get(0).getName(), userList.get(0));
            return userList.get(0);
        }
        return map.get(userList.get(0).getName());
    }

    @Override
    public List<users> findUserByFactor(users user) {
        userList=usersmapper.findUserByFactor(user);
        return userList;

    }


    @Cacheable(value = "userZone" , key = "'findAllUser'")
    @Override
    public List<users> findAllUser() {
        System.out.println("进入了业务逻辑层");
        userList=usersmapper.findAllUser();
        return userList;

    }

    @CacheEvict(value = "userZone" , key = "'findAllUser'")
    @Override
    public List<users> delete() {
        System.out.println("进入了业务逻辑层");
        userList=usersmapper.findAllUser();
        return userList;

    }


    @Override
    public int deleteUser(users user) {
        return usersmapper.deleteUser(user);
    }

    public void setUsersmapper(usersMapper usersmapper) {
        this.usersmapper = usersmapper;
    }
}
