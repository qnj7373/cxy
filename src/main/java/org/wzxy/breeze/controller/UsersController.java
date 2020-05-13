package org.wzxy.breeze.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wzxy.breeze.model.po.menu;
import org.wzxy.breeze.model.po.role;
import org.wzxy.breeze.model.po.users;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Impl.IUsersService;
import org.wzxy.breeze.service.Impl.usersServiceImpl;
import org.wzxy.breeze.utils.getOutDistinct;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private IUsersService UserService;
    private ModelAndView mv = new ModelAndView();
    private ResponseResult Result = new ResponseResult();


    @GetMapping("/getUserByFactor")
    public List<users> getUserByFactor(users user){
        user.setUserId(0);
        mv.addObject("users", UserService.findUserByFactor(user));
        mv.setViewName("usersList");
        return UserService.findUserByFactor(user);

    }

    @RequiresRoles("admin")
    @RequiresPermissions("/medical")
    @GetMapping("/getAllUser")
    public List<users> getAllUser(){
        return UserService.findAllUser();

    }

    @RequiresRoles("admin")
    @RequiresPermissions("/medical")
    @GetMapping("/delect")
    public List<users> delete(){
        return UserService.delete();

    }

    @GetMapping("/deleteUser")
    public ModelAndView deleteUser(users user){
        UserService.deleteUser(user);
        mv.addObject("users", UserService.findUserByFactor(user));
        mv.setViewName("usersList");
        return mv;

    }
    @RequiresRoles("admin")
    @RequiresPermissions("/medical")
    @GetMapping("/getMenu")
    public ResponseResult getMenu(){
        Subject subject = SecurityUtils.getSubject();
        int userId = (int) subject.getPrincipal();
        users user= new users();
        user.setUserId(userId);
        user = UserService.findUserByFactor(user).get(0);
        List<menu> menus = new ArrayList<menu>();
        menus.clear();
        if(user!=null){
            for (role r:
                    user.getRoles()) {
                for (menu m:
                        r.getMenus()) {
                    menus.add(m);
                }
            }
        }
        getOutDistinct getDistinct = new getOutDistinct();
        menus=getDistinct.distinct(menus);
        Result.setData(menus);
        Result.setMessage("获取菜单成功");
        Result.setStatus(ResponseCode.getOkcode());
        return Result;

    }



    public void setUserService(usersServiceImpl userService) {
        UserService = userService;
    }
}
