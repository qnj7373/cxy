package org.wzxy.breeze.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 覃能健
 * @create 2020-04
 */
@Controller
public class urlController {

    @GetMapping("/TechnicianIndex")
    public String TechnicianIndex(){
        return "Technician/TechnicianIndex";
        //当浏览器输入/TechnicianIndex时，会返回 /static/pages/TechnicianIndex.html页面
    }




}
