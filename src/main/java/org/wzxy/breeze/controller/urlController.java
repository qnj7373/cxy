package org.wzxy.breeze.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 覃能健
 * @create 2020-04
 */
@Controller
public class urlController {


    @GetMapping("/TechnicianIndex")
    @RequiresRoles("technician")
    public String TechnicianIndex(){
        return "Technician/TechnicianIndex";
        //当浏览器输入/TechnicianIndex时，会返回 /static/pages/TechnicianIndex.html页面
    }

    @GetMapping("/InstituteIndex")
    @RequiresRoles("institute")
    public String InstituteIndex(){
        return "Institute/InstituteIndex";
        //当浏览器输入......时，会返回 .......html页面
    }

    @GetMapping("/StudentIndex")
    @RequiresRoles("assistant")
    public String StudentIndex(){
        return "student/StudentIndex";
        //当浏览器输入......时，会返回 .......html页面
    }


}
