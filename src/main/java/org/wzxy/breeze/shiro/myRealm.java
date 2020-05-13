package org.wzxy.breeze.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.wzxy.breeze.model.po.User;
import org.wzxy.breeze.model.po.menu;
import org.wzxy.breeze.model.po.role;
import org.wzxy.breeze.service.IUserService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-04
 */
public class myRealm extends AuthorizingRealm {

    @Autowired
    @Lazy
    private IUserService UserService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
      String userId = principalCollection.getPrimaryPrincipal().toString();
        //users user=new users();
        User user=new User();
        List<User> userList =new ArrayList<>();
        user.setUid(Integer.parseInt(userId));
       //UserId(Integer.parseInt(userId));
        userList=UserService.findUserByFactor(user);
        if(userList!=null){
            user= userList.get(0);
        }
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (role r:
                user.getRoles()) {
            simpleAuthorizationInfo.addRole(r.getRoleName());
            for (menu m:
            r.getMenus()) {
                //simpleAuthorizationInfo.addStringPermission(m.getMenuName());
                simpleAuthorizationInfo.addStringPermission(m.getUrl());
            }
        }

        return simpleAuthorizationInfo;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        if(authenticationToken.getPrincipal()==null){
            System.out.println("未登录或登录状态已过期，请重新登录");
            return  null;
        }
        String id = authenticationToken.getPrincipal().toString();
        String pwd = new String((char[]) authenticationToken.getCredentials());
        //User容器
        User user = new User();
        //users user = new users();
       // List<users> userList = new ArrayList<>();
        List<User> userList = new ArrayList<>();
       // user.setUserId(Integer.parseInt(id));
       // user.setPassword(pwd);
        user.setUid(Integer.parseInt(id));
        user.setUpwd(pwd);
        System.out.println("认证 "+user.getUid()+"  "+user.getUpwd());
        userList = UserService.findUserByFactor(user);
        if (userList==null){
            //抛出异常
            return null;

        }else{
            user=userList.get(0);
         //这里验证authenticationToken和simpleAuthenticationInfo的信息
           /* SimpleAuthenticationInfo simpleAuthenticationInfo =
                    new SimpleAuthenticationInfo//(user,user.getPassword(),this.getName());
                    (user.getUserId(),user.getPassword(), this.getName());
                    //String.valueOf(user.getUserId())*/


            SimpleAuthenticationInfo simpleAuthenticationInfo =
                    new SimpleAuthenticationInfo//(user,user.getPassword(),this.getName());
                    (user.getUid(),user.getUpwd(), this.getName());
                    //String.valueOf(user.getUserId())
            return simpleAuthenticationInfo;
        }
    }
}
