package org.wzxy.breeze.config;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 覃能健
 * @create 2020-05
 */
public class CustomDate implements WebBindingInitializer {
    @Override
    public void initBinder(WebDataBinder webDataBinder) {
        // TODO Auto-generated method stub
        //转换日期
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }


}
