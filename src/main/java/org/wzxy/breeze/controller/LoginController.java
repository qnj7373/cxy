package org.wzxy.breeze.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.vo.ResponseCode;
import org.wzxy.breeze.model.vo.ResponseResult;
import org.wzxy.breeze.service.Iservice.IUserService;
import org.wzxy.breeze.utils.getUId;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-03
 */
@RestController
@RequestMapping("/system")
public class LoginController {
    @Autowired
    private IUserService UserService;
    private  ResponseResult Result = new ResponseResult();
    private ModelAndView mv = new ModelAndView();

    @GetMapping("/notLogin")
    public ModelAndView notLogin(){
        mv.setViewName("login");
        return mv;
    }

    @PostMapping("/login")
    public ResponseResult login(User loginUser){
        //添加用户认证信息
        UsernamePasswordToken upToken = new UsernamePasswordToken(
               String.valueOf(loginUser.getUid())  ,
                loginUser.getUpwd()
        );
        try{
            Subject subject = SecurityUtils.getSubject();
            //进行验证，这里可以捕获异常，然后返回相应的信息
            subject.login(upToken);
            subject.getSession().setTimeout(600000L);
            User u = new User();
            u.setUid(loginUser.getUid());
            List<User> userByFactor = UserService.findUserByFactor(u);
            if(userByFactor!=null){
                u=userByFactor.get(0);
                if("1".equals(u.getUtype())){
                    Result.setUrl("TechnicianIndex");
                }else if ("2".equals(u.getUtype())){
                    Result.setUrl("InstituteIndex");
                }else if ("3".equals(u.getUtype())){
                    Result.setUrl("StudentIndex");
                }
            }

        }catch (AuthenticationException e){
               e.printStackTrace();
            Result.setStatus(ResponseCode.getErrorcode());
            Result.setMessage("账号或密码错误");
               return Result;
        }catch (AuthorizationException e){
            e.printStackTrace();
            Result.setStatus(ResponseCode.getErrorcode());
            Result.setMessage("无权限访问");
            return Result;
        }
        Result.setStatus(ResponseCode.getOkcode());
        Result.setMessage("登录成功了~欢迎你!");
        return Result;
    }


    @GetMapping("/getPowerSwitch")
    public ResponseResult getPowerSwitch() {
        try{

            Result.setData(UserService.getPowerSwitch(getNum()));
            Result.setStatus(ResponseCode.getOkcode());
            Result.setMessage("分页获取计划列表成功！");
            return Result;

        }catch(Exception e) {
            e.printStackTrace();
            Result.setStatus(ResponseCode.getErrorcode());
            Result.setMessage("服务器出错了！请联系管理员修理~");
            return Result;
        }
    }



    private int getNum(){
        User u = new User();
        u.setUid(getUId.getid());
        List<User> users = new ArrayList<>();
        users= UserService.findUserByFactor(u);
        if (users!=null){
            return  users.get(0).getUnum();
        }else{
            return 0;
        }
    }

    @RequiresRoles("admin")
    @GetMapping("/testShiro")
    public  String test(){
        return "权限控制测试成功了";
    }

}
