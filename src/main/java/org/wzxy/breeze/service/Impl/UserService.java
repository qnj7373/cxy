package org.wzxy.breeze.service.Impl;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.userMapper;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.po.menu;
import org.wzxy.breeze.model.po.role;
import org.wzxy.breeze.service.IUserService;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author 覃能健
 * @create 2020-03
 */

@Service
public class UserService implements IUserService {

    @Resource
    private userMapper usermapper;


    private List<User> userList= new ArrayList<User>();

    @Override
    public User login(User user) {
        userList.clear();
        Set<menu> menuSet = new HashSet<>();
        Set<role> roleSet = new HashSet<>();
        Map<String,User> map = new HashMap<>();
        userList=usermapper.findUserByFactor(user);
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

    @Override
    public List<User> findUserByFactor(User user) {
        userList=usermapper.findUserByFactor(user);
        return userList;

    }


    @Cacheable(value = "userZone" , key = "'findAllUser'")
    @Override
    public List<User> findAllUser() {
        System.out.println("进入了业务逻辑层");
        userList=usermapper.findAllUser();
        return userList;

    }

    @CacheEvict(value = "userZone" , key = "'findAllUser'")
    @Override
    public List<User> delete() {
        System.out.println("进入了业务逻辑层");
        userList=usermapper.findAllUser();
        return userList;

    }


    @Override
    public int deleteUser(User user) {
       // return usermapper.deleteUser(user);
        return 0;
    }

    public void setUsersmapper(userMapper usermapper)
    {
        this.usermapper = usermapper;
    }
}
