package org.wzxy.breeze.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

/**
 * @author 覃能健
 * @create 2020-04
 */
public class getUId {

    public static int getid(){
        Subject subject = SecurityUtils.getSubject();
        int id= Integer.parseInt(subject.getPrincipal().toString());
        return id;
    }

}
