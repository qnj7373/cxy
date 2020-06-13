package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.ClassDto;
import org.wzxy.breeze.model.po.Class;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class classFactory {

    @Bean
    public Class createClass() {

        return new Class ();

    }
    @Bean
    public List<ClassDto> createListClassDto() {

        return new ArrayList<ClassDto>();

    }

    @Bean
    public List<Class> createListClass() {

        return new ArrayList<Class>();

    }

    @Bean
    public Page<ClassDto> createPageClassDto() {

        return new Page<ClassDto>();

    }

    @Bean
    public Page<Class> createPageClass() {

        return new Page<Class>();

    }



}
