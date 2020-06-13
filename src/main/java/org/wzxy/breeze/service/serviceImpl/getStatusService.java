package org.wzxy.breeze.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.wzxy.breeze.mapper.userMapper;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.utils.getUId;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Service
public class getStatusService {

    @Resource
    private userMapper userDao;


    public int getNum(){
        User u = new User();
        u.setUid(getUId.getid());
        List<User> users = new ArrayList<>();
        users= userDao.findUserByFactor(u);
        if (users!=null){
            return  users.get(0).getUnum();
        }else{
            return 0;
        }
    }



}
