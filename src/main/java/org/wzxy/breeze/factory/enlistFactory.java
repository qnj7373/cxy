package org.wzxy.breeze.factory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wzxy.breeze.model.dto.EnlistDto;
import org.wzxy.breeze.model.po.Enlist;
import org.wzxy.breeze.model.vo.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 覃能健
 * @create 2020-06
 */
@Configuration
public class enlistFactory {




    @Bean
    public Page<EnlistDto> createEnlistDto() {

        return new Page<EnlistDto>();

    }

    @Bean
    public Enlist createEnlist () {

        return new Enlist();

    }

    @Bean
    public List<Enlist> createListEnlist() {

        return new ArrayList<Enlist>();

    }

    @Bean
    public List<EnlistDto> createListEnlistDto() {

        return new ArrayList<EnlistDto>();

    }






}
